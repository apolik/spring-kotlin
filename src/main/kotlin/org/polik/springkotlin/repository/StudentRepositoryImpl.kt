package org.polik.springkotlin.repository

import org.polik.springkotlin.exception.NotFoundException
import org.polik.springkotlin.model.Student
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentRepositoryImpl : StudentRepository {
    private val students: HashMap<Int?, Student> = HashMap()
    private val log = LoggerFactory.getLogger(this.javaClass)
    private var id = 1

    override fun get(id: Int): Student {
        log.info("get {}", id)
        return students[id] ?: throw NotFoundException()
    }

    override fun create(student: Student): Student {
        log.info("create {}", student)
        if (!student.isNew()) {
            throw IllegalStateException("Id cannot be null!")
        } else {
            student.id = id++
            students[student.id] = student

            return student
        }
    }

    override fun getAll(): Collection<Student> {
        log.info("getAll")
        return students.values
    }

    override fun update(student: Student, id: Int) {
        log.info("update {} with id {}", student, id)
        if (student.isNew()) {
            throw IllegalStateException("Id of the student $student cannot be null!")
        }
        students[id] = student
    }

    override fun delete(id: Int) {
        log.info("delete {}", id)
        students.remove(id)
    }
}