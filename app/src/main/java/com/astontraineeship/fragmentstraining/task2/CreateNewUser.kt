package com.astontraineeship.fragmentstraining.task2

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig

object CreateNewUser {
    fun getNextUser() : User {
        val config = fakerConfig { locale = "ru" }
        val faker = Faker(config)
        val id = CreateNewId.getNext()
        return User(
            id,
            faker.name.firstName(),
            faker.name.lastName(),
            faker.phoneNumber.phoneNumber(),
            "https://api.slingacademy.com/public/sample-photos/$id.jpeg"
        )
    }
}