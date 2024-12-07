package oncall.controller.validator

import oncall.util.splitByComma

class MonthAndDaysValidator {
    operator fun invoke(input: String) {
        checkEmpty(input)
        val (month, day) = input.split(",")
        checkEmpty(month)
        checkEmpty(day)
        checkInteger(month)
        checkBetween(month)
        checkDays(day)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { MonthAndDaysErrorType.EMPTY_INPUT }
    }

    private fun checkInteger(input: String) {
        requireNotNull(input.toIntOrNull()) { MonthAndDaysErrorType.EMPTY_INPUT }
    }

    private fun checkBetween(input: String) {
        require(input.toInt() in 1..12) { MonthAndDaysErrorType.EMPTY_INPUT }
    }

    private fun checkDays(input: String) {
        val days = listOf("월", "화", "수", "목", "금", "토", "일")
        require(input in days) { MonthAndDaysErrorType.EMPTY_INPUT }
    }
}
