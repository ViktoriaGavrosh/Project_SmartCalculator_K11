package calculator.mathOperations

object Division : Runner {
    override fun run(firstNum: Double, secondNum: Double): Double {
        if (secondNum == 0.0) {
            println("Invalid expression")
            throw Exception()
        }
        val result = firstNum. toInt() / secondNum.toInt()
        return result.toDouble()
    }
}