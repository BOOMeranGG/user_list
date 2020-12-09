package com.example.demo

import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import com.example.demo.services.impl.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BeansConfig(
    private val userRepository: UserRepository
) {

    @Bean
    fun bCrypt(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userService(): UserService = UserServiceImpl(userRepository, bCrypt())
}