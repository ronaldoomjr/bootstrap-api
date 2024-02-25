package com.example.bootstrap.api.domain.services

import com.example.bootstrap.api.domain.dtos.CategoryDTO
import com.example.bootstrap.api.domain.dtos.toEntity
import com.example.bootstrap.api.infra.persistence.entities.toDTO
import com.example.bootstrap.api.infra.persistence.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    fun save(categoryDTO: CategoryDTO): CategoryDTO
            = categoryRepository.save(categoryDTO.toEntity()).toDTO()
}