package com.example.requestmanager.models.entities.fichier

import com.example.requestmanager.models.entities.admin.Admin
import javax.persistence.*

@Entity
@Table(name = "fichiers")
data class Fichier(

    @Id
    @Column(name = "fichier_id")
    val fileId : String = "",

    @Column(name = "fichier_link")
    val fileLink : String = "",

    @Column(name = "fichier_name")
    val fileName : String = ""
)
