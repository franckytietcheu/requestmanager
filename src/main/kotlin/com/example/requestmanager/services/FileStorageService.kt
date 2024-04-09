package com.example.requestmanager.services

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

@Service
class FileStorageService {

    companion object {
        private const val DOCUMENT_SUB_DIRECTORY = "documents"
    }

    /**
     * Le dossier racine de tous les autres fichiers de l'application
     */
    private val filesDefaultParent = File(System.getProperty("user.home"), "RequestManager")

    fun saveFile(inputStream: InputStream, fileName: String): String {
        return inputStream.use {
            val file = File(filesDefaultParent, DOCUMENT_SUB_DIRECTORY)
            if (!file.exists()) {
                file.mkdirs()
            }
            Files.copy(inputStream, Paths.get(file.absolutePath).resolve(fileName))
            File(filesDefaultParent, "$DOCUMENT_SUB_DIRECTORY/$fileName").absolutePath
        }
    }


    /**
     * Supprime un fichier
     * @param filePath le chemin vers le fichier à supprimer
     * @return un booleen. Vrai si le fichier a bien été supprimé ou s'il n'existait pas et false si le fichier n'a pas pu etre supprimé
     */
    fun deleteFile(filePath: String): Boolean {
        val path = Paths.get(filePath)
        if (Files.exists(path)) {
            return Files.deleteIfExists(path)
        }

        return true
    }

    /**
     * Charge un fichier en fonction de son nom et de son type. S'il n'existe pas il est créé
     * @param filePath le chemin d'acces vers le fichier
     * @return le fichier demandé
     * @throws [FileNotFoundException] au cas où le type de fichier demandé n'est pas connu
     */
    fun loadFile(filePath: String): File? {

        val file = File(filePath)
        return if (file.exists())
            file
        else
            null

    }

}