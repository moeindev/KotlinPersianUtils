package ir.moeindeveloper.kotlin.validators

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IranCodeUtilsTest {

    @Nested
    inner class MobileNumbersTest {

        @ParameterizedTest
        @ValueSource(strings = ["09037850955", "+989188888821", "9122223322", "00989135556644"])
        fun `Mobile number is supported`(number: String) {
            assertThat(IranCodeUtils.isValidIranianMobileNumber(number)).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["0903 7850 955", "090378509555", "11111111111", "+981111111111"])
        fun `Mobile Number is Not Supported`(number: String) {
            assertThat(IranCodeUtils.isValidIranianMobileNumber(number)).isFalse()
        }
    }

    @Nested
    inner class PhoneNumberTest {

        @ParameterizedTest
        @ValueSource(strings = ["34221116", "08334225558", "02166663333"])
        fun `Phone number is supported`(number: String) {
            assertThat(IranCodeUtils.isValidIranianPhoneNumber(number)).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["07236445", "7236445", "17236445"])
        fun `Phone number is not supported`(number: String) {
            assertThat(IranCodeUtils.isValidIranianPhoneNumber(number)).isFalse()
        }
    }

    @Nested
    inner class PhoneNumberDetection {
        @ParameterizedTest
        @ValueSource(strings = ["09037850955", "+989188888821", "9122223322",
            "00989135556644", "34221116", "08334225558", "02166663333"])
        fun `Valid phone number`(number: String) {
            assertThat(IranCodeUtils.isPhoneNumber(number)).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["This Is not a phone number", " ", "", "12345678910", "64514"])
        fun `Invalid phone number`(string: String) {
            assertThat(IranCodeUtils.isValidIranianMobileNumber(string)).isFalse()
        }
    }

    @Nested
    inner class PostalCodeTest {

        @ParameterizedTest
        @ValueSource(strings = ["1619735744","16197-35744","6715947375"])
        fun `Valid postal code`(code: String) {
            assertThat(IranCodeUtils.isValidPostalCode(code)).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["11619735744","116197-35744", "11619735744","37236445"])
        fun `Invalid postal code`(code: String) {
            assertThat(IranCodeUtils.isValidPostalCode(code)).isFalse()
        }
    }

    @Nested
    inner class CarrierTest {

        @ParameterizedTest
        @CsvSource(value = [
            "09037850955,ایرانسل",
            "+989188888821,همراه اول",
            "00989318885544,اسپادان",
            "989325554466,تالیا",
            "9345559911, تله کیش",
            "+989944555223,انارستان",
            "989990045522,لوتوس تل",
            "09357850955,ایرانسل"])
        fun `carrier names`(number: String, carrier: String) {
            assertThat(IranCodeUtils.getCarrierName(number)).isEqualTo(carrier)
        }

    }
}