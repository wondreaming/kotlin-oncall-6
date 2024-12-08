package oncall.model

enum class Months(
    val month: Int,
    val days: Int,
    val holiday: List<Int>?,
) {
    JAN(1, 31, listOf(1)),
    FEB(2, 28, null),
    MAR(3, 31, listOf(1)),
    APR(4, 30, null),
    MAY(5, 31, listOf(5)),
    JUNE(6, 30, listOf(6)),
    JUL(7, 31, null),
    AUG(8, 31, listOf(15)),
    SEP(9, 30, null),
    OCT(10, 31, listOf(3, 9)),
    NOV(11, 30, null),
    DEC(12,31, listOf(25));
}