package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.fichier.Fichier
import com.example.requestmanager.models.entities.fichier.FichierRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody

@Service
class FichierService {

    @Autowired
    private lateinit var fileStorageService: FileStorageService

    @Autowired
    private lateinit var fichierRepository: FichierRepository


    fun createFichier(file: MultipartFile) :RequestResult<Fichier>{

        val code = System.currentTimeMillis().toString()
        val extension = file.originalFilename?.split(".")?.last()
        if (extension.isNullOrBlank() || extension != "pdf") {
            return RequestResult.error("impossible de sauvegarder le fichier fourni, nous ne prenons que des pdf")
        }
        val filePath = fileStorageService.saveFile(
            file.inputStream,
            "$code.$extension"
        )

        if (filePath.isNotEmpty()) {
            val fichier = Fichier(
                fileId = code,fileLink = filePath, fileName = file.originalFilename!!
            )
            return RequestResult.success(data = fichierRepository.save(fichier))
        }

        return RequestResult.error(message = "impossible de sauvegarder le fichier")
    }


    fun getAllFichiers() : RequestResult<List<Fichier>>{
        return RequestResult.success(data = fichierRepository.findAll())
    }

}