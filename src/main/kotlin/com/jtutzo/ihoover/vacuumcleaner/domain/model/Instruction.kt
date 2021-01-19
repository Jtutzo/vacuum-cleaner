package com.jtutzo.ihoover.vacuumcleaner.domain.model

enum class Instruction(private val code: String) {
    LEFT("G"),
    RIGHT("D"),
    ADVANCE("A");

    companion object {
        fun from(code: String) = values().find { code == it.code } ?: throw IllegalArgumentException()
    }
}