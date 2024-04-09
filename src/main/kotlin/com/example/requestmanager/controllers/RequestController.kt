package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.helper.RequestStatus
import com.example.requestmanager.models.dto.RequestDto
import com.example.requestmanager.models.dto.RequestResponseDto
import com.example.requestmanager.services.RequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("requests")
class RequestController {

    @Autowired
    private lateinit var requestService: RequestService


    @PostMapping("create")
    fun createRequest(
        @RequestBody requestDto: RequestDto
    ) : RequestResult<RequestResponseDto>{
        return requestService.createRequest(requestDto)
    }

    @GetMapping("etudiant/{idEtudiant}")
    fun getRequestOfEtudiant(
        @PathVariable(value = "idEtudiant") idEtudiant : String
    ) : RequestResult<List<RequestResponseDto>>{
        return requestService.getRequestOfEtudiant(idEtudiant)
    }

    @GetMapping("admin/{poste}")
    fun getRequestOfAdmin(
        @PathVariable(value = "poste") poste : String
    ) : RequestResult<List<RequestResponseDto>>{
        return requestService.getRequestOfAdmin(poste.split(";"))
    }

    @GetMapping("{idRequest}")
    fun getDetailsOfRequest(
        @PathVariable(value = "idRequest") idRequest : String
    ) : RequestResult<RequestResponseDto>{
        return requestService.getRequestById(idRequest)
    }

    @PostMapping("changeState")
    fun changeRequestState(
        @RequestParam("requestId") requestId : String,
        @RequestParam("requestStatus") requestStatus: String
    ) : RequestResult<RequestResponseDto>{
        return requestService.changeRequestStatus(requestId = requestId, requestStatus = RequestStatus.valueOf(requestStatus))
    }

}