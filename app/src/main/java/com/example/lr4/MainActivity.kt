package com.example.lr4

import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lr4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isCustomThemeApplied = false

    // Переменные для хранения оригинальных фонов кнопок
    private lateinit var originalButton1Bg: Drawable
    private lateinit var originalButton2Bg: Drawable
    private lateinit var originalButton3Bg: Drawable
    private lateinit var originalButton4Bg: Drawable
    private lateinit var originalButton5Bg: Drawable
    private lateinit var originalButton6Bg: Drawable
    private lateinit var originalThemedButtonBg: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        originalButton1Bg = binding.button1.background
        originalButton2Bg = binding.button2.background
        originalButton3Bg = binding.button3.background
        originalButton4Bg = binding.button4.background
        originalButton5Bg = binding.button5.background
        originalButton6Bg = binding.button6.background
        originalThemedButtonBg = binding.themedButton.background

        // Кнопка для вывода ориентации
        binding.button1.setOnClickListener {
            val orientation = getScreenOrientation()
            Toast.makeText(this, "Ориентация: $orientation", Toast.LENGTH_SHORT).show()
            binding.textView.text = "Ориентация: $orientation"
        }

        binding.themedButton.setOnClickListener {
            toggleCustomTheme()
        }
    }

    // Функция определения текущей ориентации экрана
    private fun getScreenOrientation(): String {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> "Портретная ориентация"
            Configuration.ORIENTATION_LANDSCAPE -> "Альбомная ориентация"
            else -> "Неизвестная ориентация"
        }
    }

    // Функция переключения темы
    private fun toggleCustomTheme() {
        if (!isCustomThemeApplied) {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor))
            binding.textView.setTextColor(ContextCompat.getColor(this, R.color.accentColor))

            val tint = ContextCompat.getColor(this, R.color.accentColor)
            binding.button1.backgroundTintList = ColorStateList.valueOf(tint)
            binding.button2.backgroundTintList = ColorStateList.valueOf(tint)
            binding.button3.backgroundTintList = ColorStateList.valueOf(tint)
            binding.button4.backgroundTintList = ColorStateList.valueOf(tint)
            binding.button5.backgroundTintList = ColorStateList.valueOf(tint)
            binding.button6.backgroundTintList = ColorStateList.valueOf(tint)

            binding.themedButton.text = "Сбросить тему"
            isCustomThemeApplied = true
        } else {
            // Возвращаем значения по умолчанию
            binding.root.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
            binding.textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))

            binding.button1.backgroundTintList = null
            binding.button2.backgroundTintList = null
            binding.button3.backgroundTintList = null
            binding.button4.backgroundTintList = null
            binding.button5.backgroundTintList = null
            binding.button6.backgroundTintList = null

            binding.themedButton.text = "Тематическая кнопка"
            isCustomThemeApplied = false
        }
    }

}
