package ir.moeindeveloper.kotlin.validators

import ir.moeindeveloper.kotlin.extensions.strings.isNumber

object IranNationalUtils {

    /**
     * Validates Iranian national code
     *
     * @param code String nationalCode
     *
     * @return Boolean
     */
    fun isValidNationalCode(code: String): Boolean {

        if (code.isBlank()) return false

        val nationalCode = code.padStart(10,'0')

        val nationalCodeLength = 10

        if (nationalCode.length != nationalCodeLength) {
            return false
        }

        println("Passed length limit")
        if (!nationalCode.isNumber()) return false

        println("Passed number")
        var j = nationalCodeLength
        var sum = 0

        for (i in 0 until nationalCode.length - 1) {
            sum += Character.getNumericValue(nationalCode[i]) * j--
        }

        val remainder = sum % 11

        val controlNumber = Character.getNumericValue(nationalCode[9])

        return ((remainder < 2) && (controlNumber == remainder)) ||
                ((remainder >= 2) && (controlNumber == (11 - remainder)))
    }

    //TODO National legal number
}