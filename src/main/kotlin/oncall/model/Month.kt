package oncall.model

data class Month(
    private val month: Int,
    private val day: Days,
    private val weekdaysPeople: List<String>,
    private val weekendPeople: List<String>,
) {
}