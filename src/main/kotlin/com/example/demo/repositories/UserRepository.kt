package com.example.demo.repositories

import com.example.demo.entities.User
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, String> {

    @Query("SELECT u FROM user u WHERE u.id = ?1 AND u.isDeleted = FALSE")
    fun getById(id: Int): User?

    @Query("SELECT u from user u WHERE u.isDeleted = FALSE")
    fun findAll(pageRequest: PageRequest): List<User>
}