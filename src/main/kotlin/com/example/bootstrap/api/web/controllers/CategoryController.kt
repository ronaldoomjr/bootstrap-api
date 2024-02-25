package com.example.bootstrap.api.web.controllers

import com.example.bootstrap.api.domain.services.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(private val categoryService: CategoryService) {

}