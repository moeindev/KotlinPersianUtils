package ir.moeindeveloper.kotlin.validators

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IranNationalUtilsTest {

    @Nested
    inner class NationalCodeTest {

        @ParameterizedTest
        @ValueSource(strings = [
            "7731689956",
            "45768676",
            "15768643",
            "15758648",
            "0013542419",
            "3240175800",
            "3240164175",
            "3370075024",
            "0010532129",
            "0860170470",
            "1292794021"
        ])
        fun `Valid Iranian national code`(code: String) {
            assertThat(IranNationalUtils.isValidNationalCode(code)).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = [
            "555444466",
            "12345678",
            "7731685956",
            "c9xk9dkd",
            "15758448",
            "324011122",
            "3213213"
        ])
        fun `Invalid Iranian national code`(code: String) {
            assertThat(IranNationalUtils.isValidNationalCode(code)).isFalse()
        }
    }

    @Nested
    inner class NationalLegalCodeTest {

        @ParameterizedTest
        @ValueSource(strings = [
            "00103508290",
            "10101780644",
            "10101780644",
            "10860953980",
            "10340046788",
            "10480020857",
        ])
        fun `Valid Iranian national legal code`(code: String) {
            assertThat(IranNationalUtils.isValidNationalLegalNumber(code)).isTrue()
        }


        @ParameterizedTest
        @ValueSource(strings = [
            " 10480020857  ",
            "0254",
            "14006876134",
            "c25da28w45fa",
            "10740086430",
            "11111111211",
            "10320876496"
        ])
        fun `Invalid Iranian national legal code`(code: String) {
            assertThat(IranNationalUtils.isValidNationalLegalNumber(code)).isFalse()
        }
    }
}