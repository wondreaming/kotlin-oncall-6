package oncall.controller.domain

import oncall.View.InputView
import oncall.View.OutputView

class UserInteractionController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun handleMonthAndDays(): String {
        outputView.showMsg("비상 근무를 배정할 월과 시작 요일을 입력하세요>")
        return inputView.getInput()
    }
}