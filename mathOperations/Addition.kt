package calculator.mathOperations

import java.math.BigDecimal

object Addition : Runner {
    override fun run(firstNum: BigDecimal, secondNum: BigDecimal) = firstNum + secondNum
}