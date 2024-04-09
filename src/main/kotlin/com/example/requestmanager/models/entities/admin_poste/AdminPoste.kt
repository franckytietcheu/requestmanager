package com.example.requestmanager.models.entities.admin_poste

import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.poste.Poste
import javax.persistence.*

@Entity
@Table(name = "join_admin_poste")
data class AdminPoste(

    @Id
    @Column(name = "id")
    val adminPosteId : String = "",

    @ManyToOne
    var admin: Admin = Admin(),

    @ManyToOne
    var poste: Poste = Poste(),

    @Column(name = "date_affectation")
    val dateAffectation : Long = 0,

    @Column(name = "still_valid")
    var stillValid : Boolean = false

)
