package com.paufont.ocagame

class Joc(private val jugadors: List<Jugador>) {

    private val maxPosition = 63
    private val ocaSpaces = listOf(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59)
    private val mortSpaces = listOf(58) // Example death space

    fun tirarDau(): Int {
        return (1..6).random() // Simulate a dice roll
    }

    fun movimentJugador(jugador: Jugador, dau: Int): String {
        var novaPosicio = jugador.puntuacioTotal + dau
        var message = "${jugador.nickname} ha tret un $dau i es mou a la casella $novaPosicio"

        if (novaPosicio > maxPosition) {
            val rebot = novaPosicio - maxPosition
            novaPosicio = maxPosition - rebot
            message += ". Ha passat de la casella $maxPosition i tornes enrere a la casella $novaPosicio"
        }

        jugador.puntuacioTotal = novaPosicio

        if (novaPosicio == maxPosition) {
            return "${jugador.nickname} ha guanyat la partida!"
        }

        if (ocaSpaces.contains(novaPosicio)) {
            message += " - De oca en oca i tiro perque em toca!"
            val novaOcaPosicio = moveToNextOca(novaPosicio)
            jugador.puntuacioTotal = novaOcaPosicio
            message += " Es mou a la casella $novaOcaPosicio"
        } else if (mortSpaces.contains(novaPosicio)) {
            message += " - Ha caigut a la casella de la Mort! Puntuació a 0."
            jugador.reset()
        }

        return message
    }

    private fun moveToNextOca(currentPosition: Int): Int {
        return ocaSpaces.firstOrNull { it > currentPosition } ?: currentPosition
    }
}
