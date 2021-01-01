package ir.moeindeveloper.kotlin.validators

import com.google.gson.Gson
import ir.moeindeveloper.kotlin.model.Carrier
import ir.moeindeveloper.kotlin.model.Carriers
import ir.moeindeveloper.kotlin.model.Landline
import ir.moeindeveloper.kotlin.model.Landlines


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

    private fun getCarriers(): List<Carrier>? {
        this::class.java.classLoader.getResource("carrier.json")?.let { url->
            val carriersJsonString = url.readText()
            val carriers = Gson().fromJson(carriersJsonString,Carriers::class.java)
            return carriers.carriers
        }
        return null
    }


    fun getCarrierName(number: String): String? {

        if (!isPhoneNumber(number)) return null

        if (!isValidIranianMobileNumber(number)) return null

        var carrierName: String? = null

        val carriers = getCarriers()

        carriers?.let {
            if (number.matches(iranianMobileNumber1)) {
                carrierName = when(number.take(2)) {
                    "+9" -> carriers.firstOrNull { it.international1 == number.take(it.international1.length) }?.carrierName
                    "00" -> carriers.firstOrNull { it.international2 == number.take(it.international2.length) }?.carrierName
                    "98" -> carriers.firstOrNull { it.international3 == number.take(it.international3.length) }?.carrierName
                    "09" -> carriers.firstOrNull { it.local1 == number.take(it.local1.length) }?.carrierName
                    else -> null
                }
            } else if (number.matches(iranianMobileNumber2)) {
                carrierName = carriers.firstOrNull { it.local2 == number.take(it.local2.length) }?.carrierName
            }
        }

        return carrierName
    }

    private fun getLandlines(): List<Landline>? {
        this::class.java.classLoader.getResource("landline.json")?.let { url->
            val landlinesJsonString = url.readText()
            val landlines = Gson().fromJson(landlinesJsonString, Landlines::class.java)
            return landlines.landlines
        }
        return null
    }

    fun getProvinceName(number: String): String? {

        if (!isPhoneNumber(number)) return null

        if (!isValidIranianPhoneNumber(number)) return null

        var provinceName: String? = null

        val landlines = getLandlines()

        print(landlines)
        landlines?.let { list ->
            if (number.matches(iranianPhoneNumber2)) {
                provinceName = list.firstOrNull { it.code == number.take(it.code.length) }?.province
            }
        }

        return provinceName
    }
}