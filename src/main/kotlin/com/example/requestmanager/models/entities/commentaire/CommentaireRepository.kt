package com.example.requestmanager.models.entities.commentaire

import com.example.requestmanager.models.entities.request.Request
import com.example.requestmanager.models.entities.transfert.Transfert
import org.springframework.data.jpa.repository.JpaRepository

interface CommentaireRepository : JpaRepository<Commentaire, String>{
    fun findByRequest(request: Request) : List<Commentaire>
}