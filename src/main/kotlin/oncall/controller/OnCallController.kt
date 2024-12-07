package oncall.controller

import oncall.controller.domain.UserInteractionController
import oncall.controller.validator.MonthAndDaysValidator
import oncall.controller.validator.WorkingPeopleValidator
import oncall.model.Days
import oncall.model.WorkingMonth
import oncall.util.splitByComma

class OnCallController(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
    private val monthAndDaysValidator: MonthAndDaysValidator = MonthAndDaysValidator(),
    private val workingPeopleValidator: WorkingPeopleValidator = WorkingPeopleValidator(),
) {
    fun run() {
        val workingMonth = getWorkingMonth()
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

    private fun getWeekendPeople(): List<String> {
        val weekendPeople = userInteractionController.handleWeekendPeople()
        workingPeopleValidator(weekendPeople)
        val weekendWorkingPeople = weekendPeople.splitByComma()
        return weekendWorkingPeople
    }

    private fun getWorkingMonth(): WorkingMonth {
        val monthAndDays = getMonthAndDays()
        val (month, day) = monthAndDays.splitByComma()
        val weekdaysWorkingPeople = getWeekdaysPeople()
        val weekendWorkingPeople = getWeekendPeople()
        workingPeopleValidator.checkPeople(weekdaysWorkingPeople, weekendWorkingPeople)
        val workingMonth = WorkingMonth(month.toInt(), Days.valueOf(day), weekdaysWorkingPeople, weekendWorkingPeople)
        return workingMonth
    }
}