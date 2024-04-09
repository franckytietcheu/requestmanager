package com.example.requestmanager.models.entities.transfert

import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.poste.Poste
import com.example.requestmanager.models.entities.request.Request
import com.fasterxml.jackson.databind.node.LongNode
import javax.persistence.*

@Entity
@Table(name = "transfert")
data class Transfert(

    @Id
    @Column(name = "transfert_id")
    val transfertId : String = "",

    @Column(name = "transfert_date")
    val dateTransfert : Long = 0,

    @ManyToOne
    val admin: Admin  = Admin(),

    @ManyToOne
    val poste: Poste = Poste(),

    @ManyToOne
    val request: Request = Request(),

    @Column(name = "transfert_motif")
    var motifTransfert : String = "",

    @Column(name = "transfert_message", columnDefinition="TEXT")
    var messageTransfert : String = ""
)