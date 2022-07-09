package org.polik.springkotlin.controller

import lombok.AllArgsConstructor
import org.polik.springkotlin.model.Student
import org.polik.springkotlin.repository.StudentRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
class StudentController(private val repository: StudentRepository) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): Student {
        return repository.get(id)
    }

    @PostMapping
    fun create(@RequestBody student: Student): Student {
        return repository.create(student)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody student: Student) {
        repository.update(student, id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        repository.delete(id)
    }
}