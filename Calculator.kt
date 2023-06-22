package calculator

import calculator.converter.Converter
import calculator.mathOperations.Addition
import calculator.mathOperations.Division
import calculator.mathOperations.Multiplication
//import calculator.mathOperations.Runner
import calculator.mathOperations.Subtraction
import java.math.BigDecimal

class Calculator {
    private val info = Info()
    private val converter = Converter()

    /*enum class Operations(val operationName: String, val operation: Runner) {
        ADDITION("+", Addition),
        SUBTRACTION("-", Subtraction)
    }*/

    internal fun start() {
        var result: BigDecimal
        while (true) {
            val text = readln()
            try {
                if (text == "" || runCommand(text)) continue
                result = calculate(text)
            } catch (e: IllegalArgumentException) {
                break
            } catch (e: Exception) {
                continue
            }
            println(result.toBigInteger())
        }
    }

    private fun runCommand(text: String): Boolean {
        if (text.first() != '/') return false
        return when (text) {
            "/exit" -> {
                println("Bye!")
                throw IllegalArgumentException()
            }

            "/help" -> {
                info.printInfo()
                true
            }

            else -> {
                println("Unknown command")
                true
            }
        }
    }

    private fun calculate(text: String): BigDecimal {
        if (!isValidBrackets(text)) {
            println("Invalid expression")
            throw Exception()
        }
        var newText = text
        var result = BigDecimal("0.0")
        val countOpenBrackets = text.filter { it == '(' }.length
        repeat(countOpenBrackets + 1) {
            val onePart = newText.split("(")
            val twoPart = onePart.last().split(")")
            val listExpression = converter.convert(twoPart[0].trim())
            result = calculateExpression(listExpression)
            newText = newText.replace("(${twoPart[0]})", result.toString())
        }
        return result
    }

    private fun isValidBrackets(text: String) = text.filter { it == '(' }.length == text.filter { it == ')' }.length

    private fun calculateExpression(list: List<String>): BigDecimal {
        val newList = runPriorityOperations(list)
        var result = BigDecimal("0.0")
        for (i in newList.indices step 2) {           //does not work, if "1 + + 2 "
            if (i == 0) {
                result = newList[i].toBigDecimal()
                continue
            }
            try {
                result = runOperation(result, newList[i].toBigDecimal(), newList[i - 1])
            } catch (e: Exception) {
                continue
            }
        }
        return result
    }

    private fun runPriorityOperations(list: List<String>): List<String> {
        val mutableList = list.toMutableList()
        for (i in 1..list.lastIndex step 2) {
            if(list[i] == "*" || list[i] == "/") {
                mutableList[i] = if (i != 1 && list[i - 2] == "-") "-" else "+"
                mutableList[i + 1] = runOperation(mutableList[i - 1].toBigDecimal(), list[i + 1].toBigDecimal(), list[i]).toString()
                mutableList[i - 1] = "0"
            }
        }
        return mutableList
    }

    private fun runOperation(result: BigDecimal, num: BigDecimal, operation: String): BigDecimal {
        val runner = when(operation) {
            "+" -> Addition
            "-" -> Subtraction
            "*" -> Multiplication
            else -> Division
        }
        return runner.run(result, num)
    }
}