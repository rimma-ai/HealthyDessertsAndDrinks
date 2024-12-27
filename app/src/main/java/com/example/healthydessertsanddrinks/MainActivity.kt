package com.example.healthydessertsanddrinks

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Подключение базы данных
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        userDao = db.userDao()

        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)


        loginButton.setOnClickListener {
            showLoginDialog()
        }

        registerButton.setOnClickListener {
            showRegisterDialog()
        }
    }

    private fun showLoginDialog() {
        val dialog = Dialog(this, R.style.AppTheme_Dialog)
        dialog.setContentView(R.layout.dialog_login)

        val closeBtn = dialog.findViewById<ImageView>(R.id.close_login)
        val usernameInput = dialog.findViewById<EditText>(R.id.login_username)
        val passwordInput = dialog.findViewById<EditText>(R.id.login_password)
        val loginSubmit = dialog.findViewById<Button>(R.id.login_submit)

        closeBtn.setOnClickListener { dialog.dismiss() }

        loginSubmit.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            lifecycleScope.launch {
                val user = userDao.getUser(username, password)
                if (user != null) {
                    Toast.makeText(this@MainActivity, "Вход успешен", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity, "Неверные данные", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun showRegisterDialog() {
        val dialog = Dialog(this, R.style.AppTheme_Dialog)
        dialog.setContentView(R.layout.dialog_register)

        val closeBtn = dialog.findViewById<ImageView>(R.id.close_register)
        val usernameInput = dialog.findViewById<EditText>(R.id.register_email)
        val passwordInput = dialog.findViewById<EditText>(R.id.register_password)
        val registerSubmit = dialog.findViewById<Button>(R.id.register_submit)

        closeBtn.setOnClickListener { dialog.dismiss() }

        registerSubmit.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            lifecycleScope.launch {
                userDao.insertUser(User(username = username, password = password))
                Toast.makeText(this@MainActivity, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                val intent = Intent(this@MainActivity, MenuActivity::class.java)
                startActivity(intent)
            }
        }

        dialog.show()
    }
}


