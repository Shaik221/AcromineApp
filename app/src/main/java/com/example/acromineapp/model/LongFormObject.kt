package com.example.acromineapp.model

data class LongFormObject (
    var lf: String,
    var freq: Int,
    var since: Int,
    var vars: List<VariationObject>
)
