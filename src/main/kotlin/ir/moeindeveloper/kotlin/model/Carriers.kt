package ir.moeindeveloper.kotlin.model

import com.google.gson.annotations.SerializedName

data class Carriers(
    @SerializedName("carriers") val carriers: List<Carrier>
)
