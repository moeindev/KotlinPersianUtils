package ir.moeindeveloper.kotlin.model

import com.google.gson.annotations.SerializedName

data class Carrier(
    @SerializedName("in1") val international1: String,
    @SerializedName("in2") val international2: String,
    @SerializedName("in3") val international3: String,
    @SerializedName("lo1") val local1: String,
    @SerializedName("lo2") val local2: String,
    @SerializedName("carrier") val carrierName: String
)