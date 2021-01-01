package ir.moeindeveloper.kotlin.model

import com.google.gson.annotations.SerializedName

data class Landlines(
    @SerializedName("landlines") val landlines: List<Landline>
)