package com.paufont.ocagame

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage: ImageView
    private lateinit var player1TextView: EditText
    private lateinit var player2TextView: EditText
    private lateinit var errorTextView: TextView
    private lateinit var player1Counter: TextView
    private lateinit var player2Counter: TextView
    private lateinit var infoTextView: TextView
    private lateinit var turnIndicator1: LinearLayout
    private lateinit var turnIndicator2: LinearLayout

    private lateinit var player1: Jugador
    private lateinit var player2: Jugador
    private lateinit var joc: Joc

    private var currentPlayer: Jugador? = null

    private val oneImage = R.drawable._one
    private val twoImage = R.drawable._two
    private val threeImage = R.drawable._three
    private val fourImage = R.drawable._four
    private val fiveImage = R.drawable._five
    private val sixImage = R.drawable._six
    private val defaultImage = R.drawable._perspective

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1TextView = findViewById(R.id.Player1TextView)
        player2TextView = findViewById(R.id.Player2TextView)
        errorTextView = findViewById(R.id.ErrorCreatingPlayers)
        player1Counter = findViewById(R.id.Player1Counter)
        player2Counter = findViewById(R.id.Player2Counter)
        infoTextView = findViewById(R.id.Info)
        diceImage = findViewById(R.id.diceImage)
        turnIndicator1 = findViewById(R.id.TurnIndicator1)
        turnIndicator2 = findViewById(R.id.TurnIndicator2)

        diceImage.setImageResource(defaultImage)
        diceImage.visibility = View.GONE // Initially hide the dice image

        findViewById<View>(R.id.ComençarPartidaButton).setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        val player1Name = player1TextView.text.toString()
        val player2Name = player2TextView.text.toString()

        if (player1Name.isBlank() || player2Name.isBlank()) {
            errorTextView.text = "Els noms dels jugadors han d'estar omplerts."
            return
        }

        player1 = Jugador(player1Name, "Red", player1Name)
        player2 = Jugador(player2Name, "Blue", player2Name)
        joc = Joc(listOf(player1, player2))
        currentPlayer = player1

        errorTextView.text = ""
        updateUI()

        diceImage.visibility = View.VISIBLE // Show the dice image when the game starts

        updateTurnIndicators() // Highlight Player 1 on the first turn

        diceImage.setOnClickListener {
            diceRoll()
        }
    }

    private fun updateTurnIndicators() {
        // Highlight Player 1 and reset Player 2
        turnIndicator1.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))
        turnIndicator2.setBackgroundColor(resources.getColor(android.R.color.transparent))
    }

    private fun diceRoll() {
        val dice = Random.nextInt(1, 7)
        val currentPlayer = currentPlayer ?: return

        when (dice) {
            1 -> diceImage.setImageResource(oneImage)
            2 -> diceImage.setImageResource(twoImage)
            3 -> diceImage.setImageResource(threeImage)
            4 -> diceImage.setImageResource(fourImage)
            5 -> diceImage.setImageResource(fiveImage)
            6 -> diceImage.setImageResource(sixImage)
        }

        val message = joc.movimentJugador(currentPlayer, dice)
        infoTextView.text = message

        updateUI()

        if (currentPlayer.puntuacioTotal == 63) {
            diceImage.isEnabled = false
            diceImage.setImageResource(defaultImage) // Change the image to default when the game ends
            infoTextView.text = "${currentPlayer.nickname} ha guanyat la partida!"
        } else {
            this.currentPlayer = if (currentPlayer == player1) {
                turnIndicator2.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light)) // Highlight Player 2
                turnIndicator1.setBackgroundColor(resources.getColor(android.R.color.transparent)) // Reset Player 1
                player2
            } else {
                turnIndicator1.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light)) // Highlight Player 1
                turnIndicator2.setBackgroundColor(resources.getColor(android.R.color.transparent)) // Reset Player 2
                player1
            }
            infoTextView.append("\nLi toca a ${this.currentPlayer?.nickname}")
        }
    }

    private fun updateUI() {
        player1Counter.text = player1.puntuacioTotal.toString()
        player2Counter.text = player2.puntuacioTotal.toString()
    }
}
