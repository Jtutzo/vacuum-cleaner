package com.jtutzo.ihoover.vacuumcleaner.domain

enum class Orientation(private val code: String, private val value: Int) {
    NORTH("N", 0),
    WEST("O", 1),
    SOUTH("S", 2),
    EAST("E", 3);

    companion object {

        private const val MIN = -1
        private const val MAX = 4

        fun from(code: String) = values().find { code == it.code } ?: throw IllegalArgumentException()

    }

    fun previous(): Orientation = value.minus(1).let(::fromValue)

    fun next(): Orientation = value.plus(1).let(::fromValue)

    private fun fromValue(value: Int) = when(value) {
        MIN -> EAST
        MAX -> NORTH
        else -> values().find { value == it.value } ?: throw IllegalArgumentException()
    }

}