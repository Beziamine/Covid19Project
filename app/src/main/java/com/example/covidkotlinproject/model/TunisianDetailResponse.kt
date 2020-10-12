package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class TunisianDetailResponse (
    @SerializedName("countrytimelinedata") val countrytimelinedata : List<CountrytimeLinedata>,
    @SerializedName("timelineitems") val timelineitems : List<TimelineItems>
)