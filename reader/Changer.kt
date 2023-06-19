package calculator.reader

object Changer {

    internal fun changeVariable(text: String, variables: VariablesMap): List<String> {
        val newText = changeSpaces(text)
        val varList = newText.split(" = ").toMutableList()
        val list = deleteSpaces(varList)
        if (list.size >= 2) list[1] = variables.getValueOrNull(list[1]) ?: list[1]
        return list
    }

    internal fun changeExpression(text: String, variables: VariablesMap): List<String> {
        val newText = changeSpaces(text)
        val varList = newText.split(" ").toMutableList()
        val list = deleteSpaces(varList)
        for (i in list.indices step 2) {
            list[i] = variables.getValueOrNull(list[i]) ?: list[i]
        }
        val newList = changeList(list)
        return changeOperators(newList)
    }

    private fun changeSpaces(text: String): String {
        val newText = if (text.contains("=")) text.replace("=", " = ") else text
        return newText.replace("\\s+".toRegex(), " ")
    }

    private fun changeList(list: List<String>): MutableList<String> {
        val mList = list.toMutableList()
        if (mList.size < 2) {
            mList.add("+")
            mList.add("0")
        }
        return mList
    }

    private fun changeOperators(list: MutableList<String>):List<String> {
        if (list.size < 2) return list
        for (i in 1..list.lastIndex step 2) {
            val j = list[i].replace("--", "+")
            if (j.first() == '+' && j.last() == '-') list[i + 1] = "-${list[i + 1]}"
            list[i] = j[0].toString()
        }
        return list
    }

    private fun deleteSpaces(list: MutableList<String>) = list.map { it.trim() }.toMutableList()
}