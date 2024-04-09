package com.example.requestmanager.models.entities.commentaire

import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.request.Request
import javax.persistence.*

@Entity
@Table(name = "commentaires")
data class Commentaire(

    @Id
    @Column(name = "commentaire_id")
    val commentaireId : String  = "",

    @ManyToOne
    val request : Request = Request(),

    @Column(name = "commentaire_message", columnDefinition="TEXT")
    val message : String = "",

    @ManyToOne
    var etudiant: Etudiant? = null,

    @ManyToOne
    var admin: Admin? = null,

    @Column(name = "commentaire_date_creation")
    val dateCommentaire : Long = 0,
)
