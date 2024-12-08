package oncall.util

fun String.splitByComma() = this.split(",").filter { it.isNotBlank() }.map { it.trim() }

fun List<String>.getNextPerson(index: Int): String {
    return this[index % this.size]
}