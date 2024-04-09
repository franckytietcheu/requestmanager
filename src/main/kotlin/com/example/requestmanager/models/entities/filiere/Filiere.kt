package com.example.requestmanager.models.entities.filiere

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "filiere")
data class Filiere(

    @Id
    @Column(name= "filiere_id")
    val filiereId : String = "",

    @Column(name= "filiere_name")
    val name : String = "",

    @Column(name= "filiere_date_creation")
    val dateCreation : Long = 0
)
