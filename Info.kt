package calculator

class Info {
    private val mes = "The program calculates the sum and the difference of numbers"
    private val mes2 = "If several identical statements are entered, following one another, the program still should work"
    private val mes3 = "Two adjacent minus signs turn into a plus (-- -> +)"
    internal fun printInfo() {
        println("$mes\n$mes2\n$mes3")
    }
}