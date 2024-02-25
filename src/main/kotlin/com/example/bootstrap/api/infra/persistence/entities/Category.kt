package com.example.bootstrap.api.infra.persistence.entities

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var name: String,

    var createdAt: LocalDateTime = LocalDateTime.now()
)