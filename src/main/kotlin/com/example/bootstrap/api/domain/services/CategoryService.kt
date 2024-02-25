package com.example.bootstrap.api.domain.services

import com.example.bootstrap.api.infra.persistence.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
}