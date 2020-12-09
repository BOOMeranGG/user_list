package com.example.demo.services

import com.example.demo.BaseOperationResult
import com.example.demo.dto.request.UserRequest
import com.example.demo.dto.response.UserResponse

interface UserService {

    fun create(userRequest: UserRequest)

    fun get(id: Int): UserResponse?

    fun getUsers(size: Int, page: Int): List<UserResponse>

    fun update(id: Int, userRequest: UserRequest): BaseOperationResult

    fun delete(id: Int): BaseOperationResult
}