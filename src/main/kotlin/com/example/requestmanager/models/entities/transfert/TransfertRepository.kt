package com.example.requestmanager.models.entities.transfert

import com.example.requestmanager.models.entities.request.Request
import org.springframework.data.jpa.repository.JpaRepository

interface TransfertRepository : JpaRepository<Transfert, String>{

    fun findByRequest(request: Request) : List<Transfert>

}