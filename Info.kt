package calculator

class Info {
    private val mes = "The program calculates the sum and the difference of numbers"
    private val mes2 = "If several identical statements are entered, following one another, the program still should work"
    private val mes3 = "Two adjacent minus signs turn into a plus (-- -> +)"
    private val mes4 = "Valid user input - only numbers, a plus sign, a minus sign, and two commands:"
    private val mes5 = "\"/help\" - prints information about program"
    private val mes6 = "\"/exit\" - the program stops"
    internal fun printInfo() {
        println("$mes\n$mes2\n$mes3\n$mes4\n$mes5\n$mes6")
    }
}