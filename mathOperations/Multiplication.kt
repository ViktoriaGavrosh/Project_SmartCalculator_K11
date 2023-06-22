package calculator.mathOperations

import java.math.BigDecimal

object Multiplication: Runner {
    override fun run(firstNum: BigDecimal, secondNum: BigDecimal) = firstNum * secondNum
}