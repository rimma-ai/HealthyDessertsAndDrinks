package com.example.healthydessertsanddrinks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Настройка Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Убираем текстовый заголовок
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Включение кнопки "Назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_favorites)

        // Получаем список избранного из Intent
        val favoriteItems = intent.getParcelableArrayListExtra<DessertItem>("FAVORITES_LIST") ?: emptyList()

        // Настройка RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ItemAdapter(favoriteItems) { dessert ->
            // Действие при нажатии на элемент (если нужно)
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish() // Закрыть текущую Activity и вернуться назад
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}


