package oncall.controller.validator

class WorkingPeopleValidator {
    operator fun invoke(input: String) {
        checkEmpty(input)
        val people = input.split(",")
        for (person in people) {
            checkEmpty(person)
            checkNickName(person)
        }
        checkDuplicate(people)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { WorkingPeopleErrorType.EMPTY_INPUT }
    }

    private fun checkNickName(input: String) {
        require(input.length <= 5) { WorkingPeopleErrorType.EMPTY_INPUT }
    }

    private fun checkDuplicate(input: List<String>) {
        require(input.size == input.toSet().size) { WorkingPeopleErrorType.EMPTY_INPUT }
    }

    fun checkPeople(weekdayPeople: List<String>, weekendPeople: List<String>) {
        val joinPeople = weekdayPeople.union(weekendPeople)
        require(joinPeople.size in 5..35) { WorkingPeopleErrorType.EMPTY_INPUT }
    }
}