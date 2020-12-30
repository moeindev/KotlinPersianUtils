package ir.moeindeveloper.kotlin.validators


object IranCodeUtils {
    private val iranianMobileNumber1: Regex = Regex("^(((98)|(\\+98)|(0098)|0)(9)[0-9]{9})+$",RegexOption.IGNORE_CASE)
    private val iranianMobileNumber2: Regex = Regex("^(9)[0-9]{9}$",RegexOption.IGNORE_CASE)
    private val iranianPhoneNumber1: Regex = Regex("^[2-9][0-9]{7}$",RegexOption.IGNORE_CASE)
    private val iranianPhoneNumber2: Regex = Regex("^0[0-9]{2,}[0-9]{8,}\$", RegexOption.IGNORE_CASE)
    private val iranianPostalCode: Regex = Regex("^(\\d{5}-?\\d{5})$",RegexOption.IGNORE_CASE)

    /**
     * validate iranian mobile numbers
     *
     * @param number String Mobile number
     * @return Boolean
     */
    fun isValidIranianMobileNumber(number: String): Boolean
        = number.isNotBlank() &&
                (number.matches(iranianMobileNumber1) || number.matches(iranianMobileNumber2))


    /**
     * validate iranian landline numbers
     *
     * @param number String Landline number
     * @return Boolean
     */
    fun isValidIranianPhoneNumber(number: String): Boolean = number.isNotBlank() &&
            (number.matches(iranianPhoneNumber1) || number.matches(iranianPhoneNumber2))


    /**
     * Checks if the input is a phone number
     *
     * @param number String Number(Mobile/Landline)
     * @return Boolean
     */
    fun isPhoneNumber(number: String): Boolean = isValidIranianMobileNumber(number) || isValidIranianPhoneNumber(number)

    /**
     * Validate iranian postal code
     *
     * @param code String PostalCode
     * @return Boolean
     */
    fun isValidPostalCode(code: String): Boolean = code.isNotBlank() && code.matches(iranianPostalCode)


}