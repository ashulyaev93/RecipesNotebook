package com.example.demo.controllers

import com.example.demo.models.Recipe
import com.example.demo.repositories.RecipeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/recipe")
class RecipeController (private val recipeRepository: RecipeRepository) {

    @GetMapping("/all")
    fun getAllRecipes(): List<Recipe> =
        recipeRepository.findAll()


    @PostMapping
    fun createNewRecipe(@Valid @RequestBody recipe: Recipe): Recipe =
        recipeRepository.save(recipe)


    @GetMapping("/{id}")
    fun getRecipeById(@PathVariable(value = "id") recipeId: Long): ResponseEntity<Recipe> {
        return recipeRepository.findById(recipeId).map { recipe ->
            ResponseEntity.ok(recipe)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun updateRecipe(@PathVariable(value = "id") recipeId: Long,
                          @Valid @RequestBody newRecipe: Recipe): ResponseEntity<Recipe> {

        return recipeRepository.findById(recipeId).map { existingRecipe ->
            val updatedRecipe: Recipe = existingRecipe
                .copy(title = newRecipe.title, content = newRecipe.content)

            ResponseEntity.ok().body(recipeRepository.save(updatedRecipe))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable(value = "id") recipeId: Long): ResponseEntity<Void> {

        return recipeRepository.findById(recipeId).map { recipe  ->
            recipeRepository.delete(recipe)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}