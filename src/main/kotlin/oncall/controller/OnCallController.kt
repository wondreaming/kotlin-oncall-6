package oncall.controller

import oncall.controller.domain.UserInteractionController
import oncall.controller.validator.MonthAndDaysValidator
import oncall.controller.validator.WorkingPeopleValidator
import oncall.model.Days
import oncall.model.Month
import oncall.util.splitByComma

class OnCallController(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
    private val monthAndDaysValidator: MonthAndDaysValidator = MonthAndDaysValidator(),
    private val workingPeopleValidator: WorkingPeopleValidator = WorkingPeopleValidator(),
) {
    fun run() {
        val monthAndDays = getMonthAndDays()
        val (month, day) = monthAndDays.splitByComma()
        val weekdaysWorkingPeople = getWeekdaysPeople()
//        val workingMonth = Month(month.toInt(), Days.valueOf(day), weekdaysWorkingPeople)

    }

    private fun getMonthAndDays(): String {
        val monthAndDays = userInteractionController.handleMonthAndDays()
        monthAndDaysValidator(monthAndDays)

        return monthAndDays
    }

    private fun getWeekdaysPeople(): List<String> {
        val weekdaysPeople = userInteractionController.handleWeekdaysPeople()
        workingPeopleValidator(weekdaysPeople)
        val weekdaysWorkingPeople = weekdaysPeople.splitByComma()
        return weekdaysWorkingPeople
    }
}