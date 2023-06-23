package calculator

class Info {
    private val mes = """
        The calculator has operations: addition, subtraction, multiplication and division
        A general expression can contain many parentheses and operations with different priorities
        If several identical statements(+, -) are entered, following one another, the program still work
        Two adjacent minus signs turn into a plus (-- -> +)
        Valid user input - only numbers, +, -, *, /, () and two commands:
        "/help" - prints information about program
        "/exit" - the program stops
    """.trimIndent()

    internal fun printInfo() {
        println(mes)
    }
}
