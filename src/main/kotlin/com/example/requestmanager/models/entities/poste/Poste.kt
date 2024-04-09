package com.example.requestmanager.models.entities.poste

import com.example.requestmanager.models.entities.admin.Admin
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "poste_admin")
data class Poste(

    @Id
    @Column(name  = "poste_id")
    val posteId : String = "",

    @Column(name  = "poste_name")
    var posteName : String = "",

    @Column(name  = "poste_creation_date")
    var posteCreationDate : Long = 0
)
