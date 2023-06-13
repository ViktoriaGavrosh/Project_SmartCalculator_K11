package calculator

import calculator.mathOperations.Addition
import calculator.mathOperations.Runner

class Calculator {
    private val info = Info()
    private val reader = NumbersReader(info)
    private val writer = ResultWriter()

    enum class Operations(val operationName: String, val operation: Runner) {
        ADDITION("+", Addition)
    }

    internal fun start() {
        var listNumbers: List<Double>
        var result = 0.0
        while (true) {
            try {
                listNumbers = reader.readNumbers()
            } catch (e: Exception) {
                break
            }
            for (i in listNumbers.indices) {
                result = Operations.ADDITION.operation.run(result, listNumbers[i])
            }
            writer.writeResult(result)
            result = 0.0
        }
    }
}