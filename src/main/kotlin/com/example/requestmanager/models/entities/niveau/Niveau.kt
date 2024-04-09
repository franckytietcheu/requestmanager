package com.example.requestmanager.models.entities.niveau

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "niveau")
data class Niveau(

    @Id
    @Column(name= "niveau_id")
    val niveauId : String = "",

    @Column(name = "niveau_name")
    val name : String = "",

    @Column(name = "niveau_creation_date")
    val creationDate : Long = 0
)
