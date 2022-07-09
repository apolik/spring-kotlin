package org.polik.springkotlin.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class Student(
    var id: Int?,
    var name: String,
    var age: Int,
    var grade: Int,
    var degree: Double
) {

    constructor(name: String, age: Int, grade: Int, degree: Double) : this(null, name, age, grade, degree)

    @JsonIgnore
    fun isNew(): Boolean {
        return id == null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }


}
