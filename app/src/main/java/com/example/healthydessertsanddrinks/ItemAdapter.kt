package com.example.healthydessertsanddrinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private var dessertList: List<DessertItem>,
    private val onItemClicked: (DessertItem) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Добавляем callback для обработки клика на иконку избранного
    var onFavoriteClicked: ((DessertItem) -> Unit)? = null

    // Метод для обновления списка данных
    fun updateItems(newItems: List<DessertItem>) {
        dessertList = newItems
        notifyDataSetChanged() // Обновляет интерфейс
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.dessert_name)
        val calories: TextView = view.findViewById(R.id.dessert_calories)
        val image: ImageView = view.findViewById(R.id.dessert_image)
        val favoriteIcon: ImageView = view.findViewById(R.id.favorite_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dessert, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dessert = dessertList[position]
        holder.name.text = dessert.name
        holder.calories.text = "Калории: ${dessert.calories} ккал"
        holder.image.setImageResource(dessert.imageResId)

        // Устанавливаем состояние иконки "избранное"
        if (dessert.isFavorite) {
            holder.favoriteIcon.setImageResource(R.drawable.ic_favorite) // Используйте подходящий ресурс
        } else {
            holder.favoriteIcon.setImageResource(R.drawable.ic_favorite_border) // Используйте подходящий ресурс
        }

        // Обработка клика на элемент
        holder.itemView.setOnClickListener {
            onItemClicked(dessert)
        }

        // Обработка клика на иконку избранного
        holder.favoriteIcon.setOnClickListener {
            dessert.isFavorite = !dessert.isFavorite // Меняем состояние
            notifyItemChanged(position) // Обновляем элемент
            onFavoriteClicked?.invoke(dessert) // Вызываем callback
        }
    }

    override fun getItemCount(): Int = dessertList.size
}
