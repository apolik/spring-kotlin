package org.polik.springkotlin.repository

import org.polik.springkotlin.model.Student

interface StudentRepository {
    fun get(id: Int): Student

    fun create(student: Student): Student

    fun update(student: Student, id: Int)

    fun delete(id: Int)
}