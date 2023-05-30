package com.example.proyekcomposehantop.data

import com.example.proyekcomposehantop.model.Person
import com.example.proyekcomposehantop.model.PersonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PersonRepository {
    private val persons = mutableListOf<Person>()

    init {
        if (persons.isEmpty()) {
            PersonData.persons.forEach {
                persons.add(Person(it.id, it.name, it.hobby, it.birthday, it.domicile, it.photo))
            }
        }
    }

    companion object {
        @Volatile
        private var instance: PersonRepository? = null

        fun getInstance(): PersonRepository =
            instance ?: synchronized(this) {
                PersonRepository().apply {
                    instance = this
                }
            }
    }

    fun getPersons(): Flow<List<Person>> {
        return flowOf(PersonData.persons)
    }

    fun getPersonById(id: Long): Person {
        return persons.first {
            it.id == id
        }
    }
}