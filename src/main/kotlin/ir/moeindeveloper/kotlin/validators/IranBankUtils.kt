package ir.moeindeveloper.kotlin.validators

import ir.moeindeveloper.kotlin.extensions.strings.isBlank
import ir.moeindeveloper.kotlin.extensions.strings.empty
/**
 * This Object is containing all the Iranian bank utils
 */
object IranBankUtils {

    private val ibanRegex: Regex = Regex("IR[0-9]{24}",RegexOption.IGNORE_CASE)

    private val shetabRegex: Regex = Regex("[0-9]{16}",RegexOption.IGNORE_CASE)

    /**
     * Validate IBAN(Sheba) number
     * @param iban Sheba number
     * @return Boolean
     *
     * @author moeinDeveloper
     */
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
        iban.forEachIndexed{charIndex, _ ->
            if (iban[charIndex].isBlank()) return@forEachIndexed
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

    /**
     * Validate iranian shetab number
     *
     * @param creditCardNumber String Credit card number
     * @return Boolean
     *
     * @author MoeinDeveloper
     */
    fun isValidShetabNumber(creditCardNumber: String): Boolean {
        var cardNumber = creditCardNumber
        if (cardNumber.isBlank()) {
            return false
        }
        cardNumber = cardNumber.replace("-", String.empty()).replace(" ", String.empty())
        if (cardNumber.length != 16) {
            return false
        }
        if (!cardNumber.matches(shetabRegex)) {
            return false
        }
        var sumOfDigits = 0
        var result: Int
        for (i in 1..cardNumber.length) {
            val number = (cardNumber[i - 1].toString() + String.empty()).toInt()
            result = (number * (if (i % 2 == 0) 1 else 2))
            sumOfDigits += if(result > 9) result - 9 else result
        }
        return sumOfDigits % 10 == 0
    }

    //TODO get credit card info based on number

    //TODO get IBAN(SHEBA) info based on number
}