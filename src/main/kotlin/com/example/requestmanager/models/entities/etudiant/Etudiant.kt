package com.example.requestmanager.models.entities.etudiant

import com.example.requestmanager.models.entities.filiere.Filiere
import com.example.requestmanager.models.entities.niveau.Niveau
import javax.persistence.*

@Entity
@Table(name = "students")
data class Etudiant(

    @Id
    @Column(name = "student_id")
    val studentId : String = "",

    @Column(name = "pseudo")
    val studentPseudo : String = "",

    @Column(name = "student_names")
    var studentNames : String = "",

    @ManyToOne
    var filiere : Filiere = Filiere(),

    @ManyToOne
    var niveau : Niveau = Niveau(),

    @Column(name = "student_phone_number")
    var phoneNumber : String = "",

    @Column(name = "student_email")
    var email : String = "",

    @Column(name = "student_password")
    var password : String = ""
)