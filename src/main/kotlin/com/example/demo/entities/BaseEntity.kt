package com.example.demo.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Int = 1

    @CreationTimestamp
    @Column(name = "create_ts")
    lateinit var createTs: LocalDateTime

    @UpdateTimestamp
    @Column(name = "update_ts")
    lateinit var updateTs: LocalDateTime
}