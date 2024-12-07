package oncall.controller

import oncall.controller.domain.UserInteractionController
import oncall.controller.validator.MonthAndDaysValidator
import oncall.controller.validator.WorkingPeopleValidator
import oncall.model.Days
import oncall.model.Months
import oncall.model.WorkingMonth
import oncall.util.retryWhenNoException
import oncall.util.splitByComma

class OnCallController(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
    private val monthAndDaysValidator: MonthAndDaysValidator = MonthAndDaysValidator(),
    private val workingPeopleValidator: WorkingPeopleValidator = WorkingPeopleValidator(),
) {
    fun run() {
        val workingMonth = getWorkingMonth()
        val workingTimeTable = workingMonth.showTimeTable()
        userInteractionController.handleTimeTable(workingTimeTable)
    }

    private fun getMonthAndDays(): String = retryWhenNoException {
        val monthAndDays = userInteractionController.handleMonthAndDays()
        monthAndDaysValidator(monthAndDays)
        monthAndDays
    }

    private fun getWeekdaysPeople(): List<String> = retryWhenNoException {
        val weekdaysPeople = userInteractionController.handleWeekdaysPeople()
        workingPeopleValidator(weekdaysPeople)
        val weekdaysWorkingPeople = weekdaysPeople.splitByComma()
        weekdaysWorkingPeople
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
        val realDay = Days.entries.find { it.day == day }
        val realMonth = Months.entries.find { it.month == month.toInt() }
        val workingMonth = retryWhenNoException {
            val weekdaysWorkingPeople = getWeekdaysPeople()
            val weekendWorkingPeople = getWeekendPeople()
            workingPeopleValidator.checkPeople(weekdaysWorkingPeople, weekendWorkingPeople)
            WorkingMonth(realMonth!!, realDay!!, weekdaysWorkingPeople, weekendWorkingPeople)
        }
        return workingMonth
    }
}