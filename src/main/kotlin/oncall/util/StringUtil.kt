package oncall.util

fun String.splitByComma() = this.split(",").filter { it.isNotBlank() }.map { it.trim() }