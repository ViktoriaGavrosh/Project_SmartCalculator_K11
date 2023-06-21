package calculator.converter

object Changer {

    internal fun changeVariable(text: String, variables: VariablesMap): List<String> {
        val newText = changeSpaces(changeOperators(text))
        val varList = newText.split(" = ").toMutableList()
        val list = deleteSpaces(varList)
        if (list.size >= 2) list[1] = variables.getValueOrNull(list[1]) ?: list[1]
        return list
    }

    internal fun changeExpression(text: String, variables: VariablesMap): List<String> {
        val newText = changeSpaces(changeOperators(text))
        val varList = newText.split(" ").toMutableList()
        val list = deleteSpaces(varList)
        for (i in list.indices step 2) {
            list[i] = variables.getValueOrNull(list[i]) ?: list[i]
        }
        return changeList(list)
    }

    private fun changeSpaces(text: String) = text.replace("\\s+".toRegex(), " ")

    private fun changeList(list: List<String>): MutableList<String> {
        val mList = list.toMutableList()
        if (mList.size < 2) {
            mList.add("+")
            mList.add("0")
        }
        return mList
    }

    private fun changeOperators(text: String): String {
        var newText: String
        if (text.contains("=")) {
            return text.replace("=", " = ")
        } else {
            newText = text.replace("*", " * ")
            newText = newText.replace("/", " / ")
            newText = newText.replace("--", "+")
            newText = newText.replace(Regex("[+]+- ?"), " + &")
            newText = newText.replace(Regex("[+]+"), " + ")
            newText = newText.replace(Regex("-+"), " - ")
            newText  = newText.replace("&", "-")
            if (newText[0] == ' ') {
                newText = newText.trim()
                newText = "-${newText.substring(2)}"
            }
        }
        return newText
    }

    private fun deleteSpaces(list: MutableList<String>) = list.map { it.trim() }.toMutableList()
}