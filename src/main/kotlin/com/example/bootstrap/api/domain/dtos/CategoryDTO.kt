package com.example.bootstrap.api.domain.dtos

import com.example.bootstrap.api.infra.persistence.entities.Category
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

data class CategoryDTO(
    var id: Long,

    //Bean validation
    @field:NotNull @field:Size(min = 3, max = 50) var name: String = "",

    var createdAt: LocalDateTime = LocalDateTime.now()
)

// Extension Functions
fun CategoryDTO.toEntity() = Category(
    id = id,
    name = name,
    createdAt = createdAt
)