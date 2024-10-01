package com.paufont.ocagame

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage: ImageView
    private val oneImage = R.drawable._one
    private val twoImage = R.drawable._two
    private val threeImage = R.drawable._three
    private val fourImage = R.drawable._four
    private val fiveImage = R.drawable._five
    private val sixImage = R.drawable._six
    private val defaultImage = R.drawable._perspective

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize the diceImage here after setContentView
        diceImage = findViewById(R.id.diceImage)
        diceImage.setImageResource(defaultImage)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun diceRoll(view: View) {
        val dice = Random.nextInt(1, 7)
        when (dice) {
            1 -> {
                diceImage.setImageResource(oneImage)
            }
            2 -> {
                diceImage.setImageResource(twoImage)
            }
            3 -> {
                diceImage.setImageResource(threeImage)
            }
            4 -> {
                diceImage.setImageResource(fourImage)
            }
            5 -> {
                diceImage.setImageResource(fiveImage)
            }
            6 -> {
                diceImage.setImageResource(sixImage)
            }
        }
    }
}
