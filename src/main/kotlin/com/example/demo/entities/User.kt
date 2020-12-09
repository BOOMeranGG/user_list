package com.example.demo.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "\"user\"")
@Entity(name = "user")
class User: BaseEntity() {

    @Column(name = "first_name")
    var firstName: String = ""

    @Column(name = "last_name")
    var lastName: String = ""

    @Column(name = "middle_name")
    var middleName: String = ""

    @Column(name = "position")
    var position: String = ""

    @Column(name = "email")
    var email: String = ""

    @Column(name = "password")
    var password: String = ""

    @Column(name = "phone")
    var phone: String = ""

    @Column(name = "deleted")
    var isDeleted: Boolean = false
}