package com.pdproject.abudget.model

class Budget (

    var id: String = ObjectId.get().toHexString(),
    var name: String = "",
    var income: Float = 0f,
    var outcome: Float = 0f

)