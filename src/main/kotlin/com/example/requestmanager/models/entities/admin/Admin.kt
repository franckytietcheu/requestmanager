package com.example.requestmanager.models.entities.admin

import com.example.requestmanager.models.entities.poste.Poste
import javax.persistence.*

@Entity
@Table(name = "admin")
data class Admin(

    @Id
    @Column(name = "admin_id")
    val adminId : String = "",

    @Column(name = "admin_names")
    var adminNames : String = "",

    @Column(name = "admin_email_address")
    var emailAddress : String = "",

    @Column(name = "admin_phone_number")
    var phoneNumber : String = "",

    @Column(name = "admin_password")
    var password : String = ""
)
