package calculator

class NumbersReader {

    internal fun readNumbers(): List<Double> {
        while (true) {
            val listString = readFromConsole().split(" ")
            try {
                return toListDouble(listString)
            } catch (e: Exception) {
                println("Wrong input")
                continue
            }
        }
    }

    private fun readFromConsole(): String {
        var text: String
        while (true) {
            text = readln()
            if (checkString(text)) break
            println("Wrong input")
        }
        return text
    }

    private fun checkString(text: String) = !(text == "" || Regex("[a-zA-Z]+").containsMatchIn(text))

    private fun toListDouble(list: List<String>): List<Double> {
        if (list.size > 2) throw Exception()
        val listDouble = mutableListOf<Double>()
        for (i in list) listDouble.add(i.toDouble())
        return listDouble.toList()
    }
}