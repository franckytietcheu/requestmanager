package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.helper.RequestStatus
import com.example.requestmanager.models.dto.RequestDto
import com.example.requestmanager.models.dto.RequestResponseDto
import com.example.requestmanager.models.entities.commentaire.CommentaireRepository
import com.example.requestmanager.models.entities.etudiant.EtudiantRepository
import com.example.requestmanager.models.entities.poste.Poste
import com.example.requestmanager.models.entities.poste.PosteRepository
import com.example.requestmanager.models.entities.request.Request
import com.example.requestmanager.models.entities.request.RequestRepository
import com.example.requestmanager.models.entities.transfert.TransfertRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RequestService {

    @Autowired
    private lateinit var requestRepository: RequestRepository

    @Autowired
    private lateinit var etudiantRepository: EtudiantRepository

    @Autowired
    private lateinit var posteRepository: PosteRepository

    @Autowired
    private lateinit var transfertRepository: TransfertRepository

    @Autowired
    private lateinit var commentaireRepository: CommentaireRepository


    fun createRequest(request: RequestDto): RequestResult<RequestResponseDto> {

        val idPoste = request.idPoste
        val posteOptionnal = posteRepository.findById(idPoste)
        if (idPoste.isBlank() || !posteOptionnal.isPresent) {
            return RequestResult.error(message = "Vous devez définir à qui est destiné cette requête")
        }

        val idEtudiant = request.idEtudiant
        val etudiantOptionnal = etudiantRepository.findById(idEtudiant)
        if (idEtudiant.isBlank() || !etudiantOptionnal.isPresent) {
            return RequestResult.error(message = "Vous devez définir quel étudiant émet cette requête")
        }

        if (request.message.isBlank()) {
            return RequestResult.error(message = "Votre requête doit absolument avoir un message")
        }

        if (request.objet.isBlank()) {
            return RequestResult.error(message = "Votre requête doit absolument avoir un objet")
        }

        val requestToCreate = Request(
            requestId = System.currentTimeMillis().toString(),
            etudiant = etudiantOptionnal.get(),
            poste = posteOptionnal.get(),
            status = RequestStatus.SENDED,
            dateCreation = System.currentTimeMillis(),
            message = request.message,
            objet = request.objet
        )

        return RequestResult.success(data = buildRequestResponse(requestRepository.save(requestToCreate)))
    }

    fun getRequestById(requestId: String): RequestResult<RequestResponseDto> {
        val requestOptionnal = requestRepository.findById(requestId)
        return if (requestOptionnal.isPresent) {
            RequestResult.success(data = buildRequestResponse(requestOptionnal.get()))
        } else {
            RequestResult.error(message = "Aucune requête trouvée avec cet identifiant")
        }
    }

    fun getRequestOfEtudiant(idEtudiant: String): RequestResult<List<RequestResponseDto>> {
        val etudiantOptionnal = etudiantRepository.findById(idEtudiant)
        return if (etudiantOptionnal.isPresent) {
            val requestOfStudent = requestRepository.findByEtudiant(etudiantOptionnal.get())
            RequestResult.success(data = requestOfStudent.map { buildRequestResponse(it) })
        } else {
            RequestResult.error(message = "Aucun étudiant trouvé avec cet identifiant")
        }
    }

    fun buildRequestResponse(request: Request): RequestResponseDto {
        val transferts = transfertRepository.findByRequest(request)
        val commentaires = commentaireRepository.findByRequest(request)
        val response = RequestResponseDto.fromRequest(request)
        response.commentaires = commentaires
        response.transferts = transferts

        return response
    }

    fun getRequestOfAdmin(postes: List<String>): RequestResult<List<RequestResponseDto>> {
        postes.forEach {
            if(!posteRepository.findById(it).isPresent){
                return RequestResult.error(message = "Aucun poste trouvé avec l'identifiant $it")
            }
        }
        val requestsOfAdmin = mutableListOf<Request>()
        postes.forEach {
            val requests = requestRepository.findByPoste(Poste(posteId = it))
            requestsOfAdmin.addAll(requests)
        }

        return RequestResult.success(data = requestsOfAdmin.map { buildRequestResponse(it) })
    }

    fun changeRequestStatus(requestId: String, requestStatus: RequestStatus) : RequestResult<RequestResponseDto>{
        val requestOptionnal = requestRepository.findById(requestId)
        return if (requestOptionnal.isPresent) {
            RequestResult.success(data = buildRequestResponse(requestRepository.save(requestOptionnal.get().apply {
                status = requestStatus
            })))
        } else {
            RequestResult.error(message = "Aucune requête trouvée avec cet identifiant")
        }
    }

}