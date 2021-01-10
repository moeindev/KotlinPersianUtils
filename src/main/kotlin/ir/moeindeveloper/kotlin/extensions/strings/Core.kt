package ir.moeindeveloper.kotlin.extensions.strings

fun Char.isBlank(): Boolean = this == ' '

fun String.Companion.empty(): String = ""