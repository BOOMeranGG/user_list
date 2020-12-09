package com.example.demo.controllers

import com.example.demo.dto.request.UserRequest
import com.example.demo.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/user")
    fun addUser(@RequestBody user: UserRequest) {
        userService.create(user)
    }

    @GetMapping("/user")
    fun getUser(@RequestParam("id") id: Int): ResponseEntity<Any> {
        userService.get(id)?.let {
            return ResponseEntity.ok(it)
        } ?: return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/users")
    fun getUsers(
        @RequestParam("size", required = false) size: Int?,
        @RequestParam("page", required = false) page: Int?
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.getUsers(size ?: 20, page ?: 0))
    }

    @PutMapping("/user")
    fun updateUser(@RequestParam("id") id: Int, @RequestBody user: UserRequest): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.update(id, user))
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam("id") id: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.delete(id))
    }
}
