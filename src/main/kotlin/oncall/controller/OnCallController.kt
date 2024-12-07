package oncall.controller

import oncall.controller.domain.UserInteractionController
import oncall.controller.validator.MonthAndDaysValidator
import oncall.model.Days
import oncall.model.Month
import oncall.util.splitByComma

class OnCallController(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
    private val monthAndDaysValidator: MonthAndDaysValidator = MonthAndDaysValidator(),
) {
    fun run() {
        val workingMonth = getMonthAndDays()
        val weekdaysPeople = getWeekdaysPeople()
    }

    private fun getMonthAndDays(): Month {
        val monthAndDays = userInteractionController.handleMonthAndDays()
        monthAndDaysValidator(monthAndDays)
        val (month, day) = monthAndDays.splitByComma()
        val workingMonth = Month(month.toInt(), Days.valueOf(day))
        return workingMonth
    }

    private fun getWeekdaysPeople(): String {
        val weekdaysPeople = userInteractionController.handleWeekdaysPeople()
        return weekdaysPeople
    }
}