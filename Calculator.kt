package calculator

import calculator.mathOperations.Addition
import calculator.mathOperations.Multiplication
//import calculator.mathOperations.Runner
import calculator.mathOperations.Subtraction
import calculator.reader.NumbersReader

class Calculator {
    private val info = Info()
    private val reader = NumbersReader(info)
    private val writer = ResultWriter()

    /*enum class Operations(val operationName: String, val operation: Runner) {
        ADDITION("+", Addition),
        SUBTRACTION("-", Subtraction)
    }*/

    internal fun start() {
        var listNumbers: List<String>
        var result = 0.0
        while (true) {
            try {
                listNumbers = reader.read()
            } catch (e: IllegalArgumentException) {
                break
            }
            for (i in listNumbers.indices step 2) {           //does not work, if "1 + + 2 "
                if (i == 0) {
                    result = listNumbers[i].toDouble()
                    continue
                }
                result = runOperation(result, listNumbers[i].toDouble(), listNumbers[i - 1])
            }
            writer.writeResult(result)
            result = 0.0
        }
    }

    private fun runOperation(result: Double, num: Double, operation: String): Double {
        val runner = when(operation) {
            "+" -> Addition
            "-" -> Subtraction
            else -> Multiplication
        }
        return runner.run(result, num)
    }
}