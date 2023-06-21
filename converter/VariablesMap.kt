package calculator.converter

class VariablesMap {
    private var map = mutableMapOf<String, String>()

    internal fun addVariable(list: List<String>) {
        map += list[0] to list[1]
    }

    internal fun getValueOrNull(key: String): String? = map[key]
}