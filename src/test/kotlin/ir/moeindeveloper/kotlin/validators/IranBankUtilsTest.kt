package ir.moeindeveloper.kotlin.validators

import com.google.common.base.Strings
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
}