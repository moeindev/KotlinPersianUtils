package ir.moeindeveloper.kotlin.model

import com.google.gson.annotations.SerializedName

data class LandLine(
    @SerializedName("code") val code: String,
    @SerializedName("province") val province: String
)
