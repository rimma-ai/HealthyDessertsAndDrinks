package com.example.healthydessertsanddrinks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")  // Аннотируем класс как сущность для Room
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // id автоматически генерируется
    val username: String,  // Логин пользователя
    val password: String   // Пароль пользователя
)

