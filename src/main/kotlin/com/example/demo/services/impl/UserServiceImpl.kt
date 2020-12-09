package com.example.demo.services.impl

import com.example.demo.BaseOperationResult
import com.example.demo.dto.request.UserRequest
import com.example.demo.dto.response.UserResponse
import com.example.demo.entities.User
import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import com.example.demo.utils.StringUtil.phoneRusStandardization
import com.example.demo.utils.StringUtil.sanitizePhone
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserServiceImpl(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
): UserService {

    override fun create(userRequest: UserRequest) {
        val user = User()
        fillUserData(user, userRequest)

        userRepository.save(user)
    }

    override fun get(id: Int): UserResponse? {
        val user = userRepository.getById(id) ?: return null
        return buildUserResponse(user)
    }

    override fun getUsers(size: Int, page: Int): List<UserResponse> {
        val users = userRepository.findAll(PageRequest.of(page, size))

        return users.map {
            buildUserResponse(it)
        }
    }

    override fun update(id: Int, userRequest: UserRequest): BaseOperationResult {
        val user = userRepository.getById(id) ?: return BaseOperationResult("Объект с id = $id не найден")
        fillUserData(user, userRequest)

        userRepository.save(user)
        return BaseOperationResult()
    }

    override fun delete(id: Int): BaseOperationResult {
        val user = userRepository.getById(id) ?: return BaseOperationResult("Объект с id = $id не найден")
        user.isDeleted = true
        userRepository.save(user)

        return BaseOperationResult()
    }

    // -----------------------------------------------------------------------------------------------------------------

    private fun fillUserData(user: User, userRequest: UserRequest) {
        user.also {
            it.firstName = userRequest.firstName
            it.lastName = userRequest.lastName
            it.middleName = userRequest.middleName
            it.position = userRequest.position
            it.email = userRequest.email
            it.phone = phoneRusStandardization(sanitizePhone(userRequest.phone))
            it.password = bCryptPasswordEncoder.encode(userRequest.password)
        }
    }

    private fun buildUserResponse(user: User): UserResponse =
        UserResponse(
            firstName = user.firstName,
            lastName = user.lastName,
            middleName = user.middleName,
            position = user.position,
            email = user.email,
            phone = user.phone
        )
}