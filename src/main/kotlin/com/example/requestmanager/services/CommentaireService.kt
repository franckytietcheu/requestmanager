package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.dto.CommentRequestDto
import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.admin.AdminRepository
import com.example.requestmanager.models.entities.commentaire.Commentaire
import com.example.requestmanager.models.entities.commentaire.CommentaireRepository
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.etudiant.EtudiantRepository
import com.example.requestmanager.models.entities.request.Request
import com.example.requestmanager.models.entities.request.RequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentaireService {

    @Autowired
    private lateinit var commentaireRepository: CommentaireRepository

    @Autowired
    private lateinit var etudiantRepository: EtudiantRepository

    @Autowired
    private lateinit var adminRepository: AdminRepository

    @Autowired
    private lateinit var requestRepository: RequestRepository


    fun createCommentaire(commentaire: CommentRequestDto): RequestResult<Commentaire> {

        val commentaireToCreate = Commentaire(
            commentaireId = System.currentTimeMillis().toString(),
            request = Request(requestId = commentaire.idRequest),
            message = commentaire.message,
            etudiant = null,
            admin = null,
            dateCommentaire = System.currentTimeMillis()
        )

        val idRequest = commentaireToCreate.request.requestId
        if (idRequest.isBlank() || !requestRepository.findById(idRequest).isPresent) {
            return RequestResult.error(message = "La requête fournie n'est pas correcte")
        }

        val idAdmin = commentaire.idAdmin
        val idEtudiant = commentaire.idEtudiant

        if(idAdmin.isBlank() && idEtudiant.isBlank()){
            return RequestResult.error(message = "Vous devez definir si c'est un admin ou un étudiant qui fait ce commentaire")
        }


        if(idAdmin.isNotBlank()){
            val admin = adminRepository.findById(idAdmin)
            if(admin.isPresent){
                commentaireToCreate.admin = admin.get()
            }else{
                return RequestResult.error(message = "L'administrateur fourni n'est pas correct")
            }
        }


        if(idEtudiant.isNotBlank()){
            val etudiant = etudiantRepository.findById(idEtudiant)
            if(etudiant.isPresent){
                commentaireToCreate.etudiant = etudiant.get()
            }else{
                return RequestResult.error(message = "L'étudiant fourni n'est pas correct")
            }
        }


        if (commentaireToCreate.message.isBlank()) {
            return RequestResult.error(message = "Il est impossible de faire un commentaire vide")
        }

        return RequestResult.success(data = commentaireRepository.save(commentaireToCreate))

    }

}