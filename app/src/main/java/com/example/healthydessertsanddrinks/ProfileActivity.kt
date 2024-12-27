package com.example.healthydessertsanddrinks

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameField = findViewById<EditText>(R.id.profile_name)
        val emailField = findViewById<EditText>(R.id.profile_email)
        val phoneField = findViewById<EditText>(R.id.profile_phone)
        val passwordField = findViewById<EditText>(R.id.profile_password)
        val saveButton = findViewById<Button>(R.id.save_button)
        val logoutButton = findViewById<Button>(R.id.logout_button)

        // Пример заполнения данных
        nameField.setText("Пример имени")
        emailField.setText("example@example.com")

        saveButton.setOnClickListener {
            // Логика сохранения данных
            val name = nameField.text.toString()
            val email = emailField.text.toString()
            val phone = phoneField.text.toString()
            val password = passwordField.text.toString()

            Toast.makeText(this, "Данные сохранены: $name, $email", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            // Логика выхода из аккаунта
            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
