package calculator.reader

object Checker {

    internal fun isValidVariable(list: List<String>): Boolean {
        if (!isNameValid(list[0])) {
            println("Invalid identifier")
            return false
        }
        if (list.size != 2 || !isValueValid(list[1])) {
            println("Invalid assignment")
            return false
        }

        return true
    }

    internal fun isValidExpression(list: List<String>): Boolean {
        if (list.size < 3 || !isValidOperations(list)) {
            println("Invalid expression")
            return false
        }
        return isCanToDouble(list)
    }

    private fun isCanToDouble(list: List<String>): Boolean {
        for (i in list.indices step 2) {
            if (isValueValid(list[i])) continue
            if (isNameValid(list[i])) {
                println("Unknown variable")
                return false
            }
            println("Invalid identifier")
            return false
        }
        return true
    }

    private fun isValidOperations(list: List<String>): Boolean {
        for (i in 1..list.lastIndex step 2) {
            if (Regex("[^+-]").containsMatchIn(list[i])) {
                return false
            }
        }
        return true
    }

    private fun isNameValid(name: String) = Regex("[a-zA-Z]+").matches(name)

    private fun isValueValid(value: String) = try {
            value.toDouble()
            true
        } catch (e: Exception){
            false
        }
}

