package com.example.requestmanager.models.dto

import com.example.requestmanager.models.entities.commentaire.Commentaire
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.poste.Poste
import com.example.requestmanager.models.entities.transfert.Transfert
import javax.persistence.*

data class RequestDto(
    val idEtudiant: String,
    val dateCreation : Long = 0,
    val message : String = "",
    val idPoste: String,
    val objet : String
)
