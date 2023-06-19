package calculator.reader

import calculator.Info
import java.lang.IllegalArgumentException

class NumbersReader(private val info: Info) {
    private val converter = Converter()

    internal fun read(): List<String> {
        var listResult: List<String>
        while (true) {
            val text = readFromConsole()
            if (text == "") continue
            if (runCommand(text)) continue
            try {
                listResult = converter.convert(text)
                break
            } catch (e: Exception) {
                continue
            }
        }
        return listResult
    }

    private fun readFromConsole() = readln()

    private fun runCommand(text: String): Boolean {
        if (text.first() != '/') return false
        return when(text){
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
}