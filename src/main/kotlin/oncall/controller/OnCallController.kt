package oncall.controller

import oncall.controller.domain.UserInteractionController

class OnCallController(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
) {
    fun run() {
        val monthAndDays = getMonthAndDays()
    }

    private fun getMonthAndDays(): String {
        val monthAndDays = userInteractionController.handleMonthAndDays()
        return monthAndDays
    }
}