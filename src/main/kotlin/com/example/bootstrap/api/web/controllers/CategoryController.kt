package com.example.bootstrap.api.web.controllers

import com.example.bootstrap.api.domain.dtos.CategoryDTO
import com.example.bootstrap.api.domain.services.CategoryService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/categories")
class CategoryController(private val categoryService: CategoryService) {
    @PostMapping
    fun save(@RequestBody @Valid categoryDTO: CategoryDTO, uriBuilder: UriComponentsBuilder):
            ResponseEntity<CategoryDTO> {
        val newCategoryDTO = categoryService.save(categoryDTO)

        val uri = uriBuilder.path("/categories/{id}").buildAndExpand(newCategoryDTO.id).toUri()

        return ResponseEntity.created(uri).body(newCategoryDTO)
    }
}