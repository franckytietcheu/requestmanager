package com.example.requestmanager.models.entities.admin_poste

import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.poste.Poste
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AdminPosteRepository : JpaRepository<AdminPoste, String>{

    fun findByPosteAndStillValid(poste: Poste, stillValid : Boolean) : Optional<AdminPoste>

    fun findByAdminAndStillValid(admin: Admin, stillValid : Boolean) : List<AdminPoste>

}