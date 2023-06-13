package calculator

import calculator.mathOperations.Addition
import calculator.mathOperations.Runner

class Calculator {
    private val reader = NumbersReader()
    private val writer = ResultWriter()
    enum class Operations(val operationName: String, val operation: Runner) {
        ADDITION("+", Addition)
    }

    internal fun start() {
        var listNumbers: List<Double>
        var result: Double
        while (true) {
            try {
                listNumbers = reader.readNumbers()
            } catch (e: Exception) {
                break
            }
            result = Operations.ADDITION.operation.run(listNumbers[0], listNumbers[1])
            writer.writeResult(result)
        }
    }
}