package com.jtutzo.ihoover.vacuumcleaner.domain

enum class Orientation(private val code: String, private val value: Int) {
    NORTH("N", 0),
    WEST("O", 1),
    SOUTH("S", 2),
    EAST("E", 3);

    fun previous(): Orientation = value.minus(1).let(::fromValue)

    fun next(): Orientation = value.plus(1).let(::fromValue)

    companion object {

        fun from(code: String) = values().find { code == it.code } ?: throw IllegalArgumentException()

        private fun fromValue(value: Int) = let {
            if (value < 0) WEST
            if (value > 3) NORTH
            else values().find { value == it.value }!!
        }
    }
}