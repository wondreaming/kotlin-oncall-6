package oncall.model

import oncall.util.getNextPerson

data class WorkingMonth(
    private val month: Months,
    private val startDay: Days,
    private val weekdaysPeople: List<String>,
    private val weekendPeople: List<String>,
) {
    fun getTimeTable(): List<String> {
        val timeTable = mutableListOf<String>()
        val holidays = month.holiday ?: emptyList()
        val totalDays = month.days
        var currentDayIndex = startDay.index
        var weekdayIndex = 0
        var weekendIndex = 0

        for (day in 1..totalDays) {
            val isHoliday =
                currentDayIndex == Days.SATURDAY.index || currentDayIndex == Days.SUNDAY.index || day in holidays
            val peopleList = if (isHoliday) weekendPeople else weekdaysPeople
            val person = peopleList.getNextPerson(if (isHoliday) weekendIndex else weekdayIndex)

            val dayInfo = "${month.month}월 ${day}일 ${Days.entries[currentDayIndex].day}"
            val formattedDayInfo = if (isHoliday && currentDayIndex < Days.SATURDAY.index) "$dayInfo(휴일)" else dayInfo
            timeTable.add("$formattedDayInfo $person")

            if (isHoliday) weekendIndex++ else weekdayIndex++
            currentDayIndex = (currentDayIndex + 1) % Days.entries.size
        }
        return timeTable
    }
}

