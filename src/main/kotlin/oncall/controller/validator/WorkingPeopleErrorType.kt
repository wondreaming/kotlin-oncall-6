package oncall.controller.validator

enum class WorkingPeopleErrorType(
    private val errorMessage: String
) {
    EMPTY_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}