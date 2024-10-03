package com.paufont.ocagame

class Jugador(val nom: String, val color: String, val nickname: String) {
    var puntuacioTotal: Int = 0 // Player's score

    fun reset() {
        puntuacioTotal = 0
    }

    override fun toString(): String {
        return "Jugador(nom='$nom', color='$color', nickname='$nickname', puntuacioTotal=$puntuacioTotal)"
    }
}
