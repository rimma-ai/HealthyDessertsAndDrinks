package com.example.healthydessertsanddrinks

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // Настройка Toolbar
        setupToolbar()

        // Инициализация элементов
        val recipeImage = findViewById<ImageView>(R.id.recipe_image)
        val recipeName = findViewById<TextView>(R.id.recipe_name)
        val recipeCalories = findViewById<TextView>(R.id.recipe_calories)
        val recipeDetails = findViewById<TextView>(R.id.recipe_details)

        // Установка данных из Intent
        setRecipeData(recipeImage, recipeName, recipeCalories, recipeDetails)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Показываем стрелку "Назад"
            title = "" // Убираем текст заголовка
        }
        toolbar.setNavigationOnClickListener {
            finish() // Возврат на предыдущую Activity
        }
    }

    private fun setRecipeData(
        recipeImage: ImageView,
        recipeName: TextView,
        recipeCalories: TextView,
        recipeDetails: TextView
    ) {
        val name = intent.getStringExtra("RECIPE_NAME") ?: ""
        val calories = intent.getIntExtra("RECIPE_CALORIES", 0)
        val imageResId = intent.getIntExtra("RECIPE_IMAGE", 0)

        recipeImage.setImageResource(imageResId)
        recipeName.text = name
        recipeCalories.text = "Калории: $calories ккал"

        recipeDetails.text = getRecipeDetails(name)
    }

    private fun getRecipeDetails(name: String): String {
        return when (name) {
            "Шоколадный хруст" -> """
                Рецепт "Фитнес-печенье" (низкокалорийный вариант)

                Ингредиенты (на 12 штук):
                - 1 спелый банан (примерно 120 г)
                - 100 г овсяных хлопьев
                - 30 г темного шоколада (70% какао, нарезанного кусочками)
                - 1 ч. л. ванильного экстракта
                - Щепотка соли
                - 1 ч. л. разрыхлителя

                Приготовление:
                1. Разомните банан вилкой до состояния пюре.
                2. Добавьте овсяные хлопья, разрыхлитель, ванильный экстракт и щепотку соли. Перемешайте.
                3. Добавьте нарезанный шоколад и перемешайте.
                4. На противень выложите тесто ложкой, формируя круглые печенья.
                5. Выпекайте при 180°C 12-15 минут до золотистого цвета.

                Питательная ценность (на 1 штуку):
                - Калории: 55 ккал
                - Белки: 1.2 г (9%)
                - Жиры: 1.5 г (25%)
                - Углеводы: 10 г (66%)
            """.trimIndent()
            // Добавьте текст для других рецептов
            else -> "Рецепт не найден."
        }
    }
}

