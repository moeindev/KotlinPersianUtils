package ir.moeindeveloper.kotlin.validators

import ir.moeindeveloper.kotlin.extensions.strings.isBlank

/**
 * This Object is containing all the Iranian bank utils
 */
object IranBankUtils {

    private val ibanRegex: Regex = Regex("IR[0-9]{24}",RegexOption.IGNORE_CASE)

    fun isValidIBAN(iban: String): Boolean {
        if (iban.isBlank()) {
            return false
        }
        if (iban.length < 4 || iban[0].isBlank() || iban[1].isBlank() || iban[2].isBlank()|| iban[3].isBlank()) {
            return false
        }
        if (iban.length != 26) {
            return false
        }
        if (!iban.matches(ibanRegex)) {
            return false
        }
        var checksum = 0
        val ibanLength = iban.length
        for (charIndex in 0 until ibanLength) {
            if (iban[charIndex].isBlank()) {
                continue
            }
            var value: Int
            when (val c = iban[(charIndex + 4) % ibanLength]) {
                in '0'..'9' -> {
                    value = c - '0'
                }
                in 'A'..'Z' -> {
                    value = c - 'A'
                    checksum = (checksum * 10 + (value / 10 + 1)) % 97
                    value %= 10
                }
                in 'a'..'z' -> {
                    value = c - 'a'
                    checksum = (checksum * 10 + (value / 10 + 1)) % 97
                    value %= 10
                }
                else -> {
                    return false
                }
            }
            checksum = (checksum * 10 + value) % 97
        }
        return checksum == 1
    }

    //TODO validate credit card numbers

    //TODO get credit card info based on number

    //TODO get IBAN(SHEBA) info based on number
}