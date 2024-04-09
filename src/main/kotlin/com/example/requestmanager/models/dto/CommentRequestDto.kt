package com.example.requestmanager.models.dto

data class CommentRequestDto(
    val idRequest : String,
    val idAdmin : String = "",
    val message : String  = "",
    val idEtudiant : String = ""
)