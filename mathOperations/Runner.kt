package calculator.mathOperations

import java.math.BigDecimal

interface Runner {
    fun run(firstNum: BigDecimal, secondNum: BigDecimal): BigDecimal
}