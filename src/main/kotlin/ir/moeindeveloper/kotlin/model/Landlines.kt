package ir.moeindeveloper.kotlin.model

import com.google.gson.annotations.SerializedName

data class Landlines(
    @SerializedName("Landlines") val landlines: List<Landline>
)