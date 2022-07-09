package org.polik.springkotlin.repository

import org.polik.springkotlin.exception.NotFoundException
import org.polik.springkotlin.model.Student
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentRepositoryImpl : StudentRepository {
    private  val students: HashMap<Int, Student> = HashMap()

    override fun get(id: Int): Student {
        val student = Optional.ofNullable(students[id])
        return student.orElseThrow { NotFoundException() }
    }

    override fun create(student: Student): Student {
        val studentOpt = Optional.ofNullable(
            students.put(student.id, student)
        )
        return studentOpt.orElseThrow { IllegalStateException() }
    }

    override fun update(student: Student, id: Int) {
        students[id] = student
    }

    override fun delete(id: Int) {
        students.remove(id)
    }
}