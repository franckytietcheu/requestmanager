package com.example.requestmanager.models.entities.admin

import com.example.requestmanager.models.entities.etudiant.Etudiant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AdminRepository : JpaRepository<Admin, String>{

    fun findByEmailAddressAndPassword(emailAddress : String, password : String) : Optional<Admin>

}