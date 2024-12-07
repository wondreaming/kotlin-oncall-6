package oncall.model

data class WorkingMonth(
    private val month: Months,
    private val day: Days,
    private val weekdaysPeople: List<String>,
    private val weekendPeople: List<String>,
) {
    fun showTimeTable(): List<String> {
        val workingTimeTable = getTimeTable()
        val timeTable = mutableListOf<String>()
        var realDay = day
        for (one in 0 until month.days) {
            if ((month.holiday != null && one in month.holiday) && realDay.index != 5 && realDay.index != 6) {
                timeTable.add("${month.month}월 ${one + 1}일 ${realDay.day}(휴일) ${workingTimeTable[one]}")
                realDay.index++
            } else {
                timeTable.add("${month.month}월 ${one + 1}일 ${realDay.day} ${workingTimeTable[one]}")
            }
        }
        return timeTable
    }

    private fun getTimeTable(): List<String> {
        val timeTable = mutableListOf<String>()
        var weekdayIndex = 0
        var weekendIndex = 0
        var specialWeekdayIndex = 0
        var specialWeekendIndex = 0
        var realDay = day
        // 3월 31일 쉬는 날 1일

        for (one in 1..month.days) {
            if (realDay.index == 5 || realDay.index == 6 || (month.holiday != null && one in month.holiday) ) {
                if (timeTable.isNotEmpty() && timeTable.last() == weekendPeople[weekendIndex]) {
                    timeTable.add(weekendPeople[weekendIndex + 1])
                    specialWeekendIndex = weekendIndex + 2
                } else if (specialWeekendIndex != 0) {
                    timeTable.add(weekendPeople[weekendIndex])
                    weekendIndex = specialWeekendIndex
                    specialWeekendIndex = 0
                } else {
                    timeTable.add(weekendPeople[weekendIndex])
                    weekendIndex++
                }

                realDay.index++
                if (weekdayIndex > weekendPeople.size) {
                    weekendIndex = 0
                }
                if (realDay.index > 6) {
                    realDay.index = 0
                }
            } else {
                if (timeTable.isNotEmpty() && timeTable.last() == weekdaysPeople[weekdayIndex]) {
                    timeTable.add(weekdaysPeople[weekdayIndex + 1])
                    specialWeekdayIndex = weekendIndex + 2
                } else if (specialWeekdayIndex != 0) {
                    timeTable.add(weekdaysPeople[weekdayIndex])
                    weekdayIndex = specialWeekdayIndex
                    specialWeekdayIndex = 0
                } else {
                    timeTable.add(weekdaysPeople[weekdayIndex])
                    weekdayIndex++
                }

                realDay.index++
                if (weekdayIndex > weekdaysPeople.size) {
                    weekdayIndex = 0
                }
                if (realDay.index > 6) {
                    realDay.index = 0
                }
            }
        }
        return timeTable
    }
}