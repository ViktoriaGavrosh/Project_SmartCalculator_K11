package calculator.reader

class Converter {
    private val variables = VariablesMap()

    internal fun convert(text: String): List<String> {
        if (text.contains("=")) {
            toVariable(text)
            throw Exception()
        } else return toExpression(text)
    }

    private fun toVariable(text: String) {
        val list = Changer.changeVariable(text, variables)
        if (!Checker.isValidVariable(list)) return
        variables.addVariable(list)
    }

    private fun toExpression(text: String): List<String> {
        val list = Changer.changeExpression(text, variables)
        if (!Checker.isValidExpression(list)) {
            throw Exception()
        } else {
            return list
        }
    }
}