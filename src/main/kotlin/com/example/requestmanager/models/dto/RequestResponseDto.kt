package com.example.requestmanager.models.dto

import com.example.requestmanager.helper.RequestStatus
import com.example.requestmanager.models.entities.commentaire.Commentaire
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.poste.Poste
import com.example.requestmanager.models.entities.request.Request
import com.example.requestmanager.models.entities.transfert.Transfert
import javax.persistence.*

data class RequestResponseDto(
    val requestId : String = "",
    val etudiant: Etudiant = Etudiant(),
    val dateCreation : Long = 0,
    val message : String = "",
    val objet : String = "",
    val poste: Poste = Poste(),
    val status : RequestStatus = RequestStatus.SENDED,
    var transferts : List<Transfert> = listOf(),
    var commentaires : List<Commentaire> = listOf()
) {

    companion object {

        fun fromRequest(request: Request) : RequestResponseDto{
            return RequestResponseDto(
                requestId = request.requestId,etudiant = request.etudiant,dateCreation = request.dateCreation,message = request.message,poste = request.poste, objet = request.objet, status = request.status
            )
        }

    }

}
