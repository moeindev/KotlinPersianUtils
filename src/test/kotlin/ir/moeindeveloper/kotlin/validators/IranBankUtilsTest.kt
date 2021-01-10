package ir.moeindeveloper.kotlin.validators

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IranBankUtilsTest {

    @Nested
    inner class IbanNumberTest {

        @ParameterizedTest
        @ValueSource(strings = ["IR460540201370000357345005"])
        fun `IBAN number is supported`(number: String) {
            assertThat(IranBankUtils.isValidIBAN(number)).isTrue()
        }


        @ParameterizedTest
        @ValueSource(strings = [
            "IR111111111111111111111111",
            "IR12345678910111213"
        ])
        fun `IBAN number is not supported`(number: String) {
            assertThat(IranBankUtils.isValidIBAN(number)).isFalse()
        }
    }

    @Nested
    inner class ShetabNumberTest {

        @ParameterizedTest
        @ValueSource(strings = [
            "6221061106498670",
            "636214-1075165358",
            "6274129005473742" ])
        fun `Shetab number is supported`(number: String) {
            assertThat(IranBankUtils.isValidShetabNumber(number)).isTrue()
        }


        @ParameterizedTest
        @ValueSource(strings = [
            "622106106761305155",
            "6274129107473842",
            "627412900742",
            "62741290054737423252"
        ])
        fun `Shetab number is not supported`(number: String) {
            assertThat(IranBankUtils.isValidShetabNumber(number)).isFalse()
        }
    }
}