package oncall.model

data class WorkingMonth(
    private val month: Int,
    private val day: Days,
    private val weekdaysPeople: List<String>,
    private val weekendPeople: List<String>,
) {
}