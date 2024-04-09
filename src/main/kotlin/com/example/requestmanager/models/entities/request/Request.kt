package com.example.requestmanager.models.entities.request

import com.example.requestmanager.helper.RequestStatus
import com.example.requestmanager.models.entities.commentaire.Commentaire
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.poste.Poste
import javax.persistence.*

@Entity
@Table(name = "requests")
data class Request(

    @Id
    @Column(name = "request_id")
    val requestId : String = "",

    val objet : String = "",

    @ManyToOne
    val etudiant: Etudiant = Etudiant(),

    @Column(name = "request_date_creation")
    val dateCreation : Long = 0,

    @Column(name = "request_message", columnDefinition="TEXT")
    val message : String = "",

    @Column(name = "request_status")
    var status : RequestStatus = RequestStatus.SENDED,

    @ManyToOne
    val poste: Poste = Poste()

)
