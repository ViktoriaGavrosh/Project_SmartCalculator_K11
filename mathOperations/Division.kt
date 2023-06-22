package calculator.mathOperations

import java.math.BigDecimal

object Division : Runner {
    override fun run(firstNum: BigDecimal, secondNum: BigDecimal): BigDecimal {
        if (secondNum == BigDecimal("0.0")) {
            println("Invalid expression")
            throw Exception()
        }
        return firstNum / secondNum
    }
}