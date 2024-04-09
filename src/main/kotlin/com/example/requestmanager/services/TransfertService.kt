package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.helper.RequestStatus
import com.example.requestmanager.models.entities.admin.AdminRepository
import com.example.requestmanager.models.entities.etudiant.EtudiantRepository
import com.example.requestmanager.models.entities.poste.PosteRepository
import com.example.requestmanager.models.entities.request.RequestRepository
import com.example.requestmanager.models.entities.transfert.Transfert
import com.example.requestmanager.models.entities.transfert.TransfertRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransfertService {

    @Autowired
    private lateinit var transfertRepository: TransfertRepository

    @Autowired
    private lateinit var adminRepository: AdminRepository

    @Autowired
    private lateinit var posteRepository: PosteRepository

    @Autowired
    private lateinit var requestRepository: RequestRepository


    fun createTransfer(transfert: Transfert) : RequestResult<Transfert>{
        val transfertToCreate = transfert.copy(transfertId = System.currentTimeMillis().toString(), dateTransfert = System.currentTimeMillis())

        val idAdmin = transfertToCreate.admin.adminId
        if(idAdmin.isBlank() || !adminRepository.findById(idAdmin).isPresent){
            return RequestResult.error(message = "L'administrateur fourni n'est pas correct")
        }

        val idPoste = transfertToCreate.poste.posteId
        if(idPoste.isBlank() || !posteRepository.findById(idPoste).isPresent){
            return RequestResult.error(message = "Le poste fourni n'est pas correct")
        }

        val request = requestRepository.findById(transfertToCreate.request.requestId)
        if(!request.isPresent){
            return RequestResult.error(message = "La requête fournie n'est pas correct")
        }

        if(transfertToCreate.motifTransfert.isBlank()){
            return RequestResult.error(message = "Vous devez absolument préciser pourquoi vous transférez cette requête")
        }

        val transfertCreated = RequestResult.success(data = transfertRepository.save(transfertToCreate))

        requestRepository.save(request.get().apply {
            status = RequestStatus.SENDED
        })

        return transfertCreated

    }

}