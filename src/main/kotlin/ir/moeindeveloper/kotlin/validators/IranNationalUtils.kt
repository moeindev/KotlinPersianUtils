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

        if (!nationalCode.isNumber()) return false

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

    /**
     * Validate national legal code
     * @param code String national legal code
     * @return Boolean
     */
    fun isValidNationalLegalNumber(code: String): Boolean {

        if (code.isBlank()) return false

        val nationalLegalCode = code.padStart(11,'0')

        val nationalLegalCodeLength = 11

        if (nationalLegalCode.length != nationalLegalCodeLength) return false

        if (!nationalLegalCode.isNumber()) return false

        val beforeControlNumber = Character.getNumericValue(nationalLegalCode[9]) + 2

        val coefficientStatic = arrayOf(29, 27, 23, 19, 17, 29, 27, 23, 19, 17)

        var sum = 0

        for(i in 0 until nationalLegalCode.length - 1){
            sum += (Character.getNumericValue(nationalLegalCode[i]) + beforeControlNumber) * coefficientStatic[i]
        }

        var remainder = sum % 11

        val controlNumber = Character.getNumericValue(nationalLegalCode[10])

        remainder = if (remainder == 10) 0 else remainder

        return controlNumber == remainder
    }
}