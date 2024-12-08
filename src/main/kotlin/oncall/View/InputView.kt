package oncall.View

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun getInput(): String {
        return Console.readLine().trim()
    }
}