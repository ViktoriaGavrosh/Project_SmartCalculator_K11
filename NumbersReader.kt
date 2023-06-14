package calculator

class NumbersReader(private val info: Info) {

    internal fun readNumbers(): List<String> {
        while (true) {
            val listString = fixSpaces(readFromConsole()).split(" ")
            try {
                return fixList(listString)
            } catch (e: Exception) {
                continue
            }
        }
    }

    private fun fixSpaces(text: String) = text.replace("\\s+".toRegex(), " ")

    private fun readFromConsole(): String {
        var text: String
        while (true) {
            text = readln()
            when (text) {
                "/exit" -> {
                    println("Bye!")
                    throw Exception()
                }
                "/help" -> {
                    info.printInfo()
                    continue
                }
            }
            if (checkString(text)) break
        }
        return text
    }

    private fun checkString(text: String) = !(text == "" || Regex("[a-zA-Z]+").containsMatchIn(text))

    /*private fun toListDouble(list: List<String>): List<Double> {
        val listDouble = mutableListOf<Double>()
        for (i in list) listDouble.add(i.toDouble())
        return fixList(listDouble).toList()
    }*/

    private fun fixList(list: List<String>): List<String> {
        val mList = list.toMutableList()
        if (mList.size < 2) {
            mList.add("+")
            mList.add("0")
        }                                          //change, if not + and -
        val resultList = changeOperators(mList)
        return resultList.toList()
    }

    private fun changeOperators(list: MutableList<String>): MutableList<String> {
        for (i in 1..list.lastIndex step 2) {
            val j = list[i].replace("--", "+")
            if (j.first() == '+' && j.last() == '-') list[i + 1] = "-${list[i + 1]}"
            list[i] = j[0].toString()
        }
        return list
    }
}