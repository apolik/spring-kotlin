package org.polik.springkotlin.bootstrap

import org.polik.springkotlin.model.Student
import org.polik.springkotlin.repository.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class StudentBootstrap(private val repository: StudentRepository) : CommandLineRunner {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun run(vararg args: String?) {
        log.info("Creating students...")

        val student1 = Student("Melchol Nicolaus", 29, 2, 9.8)
        val student2 = Student("Vergilius Mara", 21, 3, 7.1)
        val student3 = Student("Solange Rohit", 26, 4, 3.0)
        val student4 = Student("Berta Dechen", 23, 777, 9.6)
        val student5 = Student("Alex Urania", 28, 1, 5.6)

        repository.create(student1)
        repository.create(student2)
        repository.create(student3)
        repository.create(student4)
        repository.create(student5)

        log.info("Students have been created!")
    }
}