package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.transfert.Transfert
import com.example.requestmanager.services.TransfertService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transferts")
class TransfertController {

    @Autowired
    private lateinit var transfertService: TransfertService


    @PostMapping("create")
    fun createTransfert(@RequestBody transfert: Transfert) : RequestResult<Transfert>{
        return transfertService.createTransfer(transfert)
    }

}