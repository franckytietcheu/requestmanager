package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.dto.CommentRequestDto
import com.example.requestmanager.models.entities.commentaire.Commentaire
import com.example.requestmanager.services.CommentaireService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("commentaires")
class CommentaireController {

    @Autowired
    private lateinit var commentaireService: CommentaireService

    @PostMapping("create")
    fun createCommentaire(@RequestBody commentaire: CommentRequestDto) : RequestResult<Commentaire>{
        return commentaireService.createCommentaire(commentaire)
    }

}