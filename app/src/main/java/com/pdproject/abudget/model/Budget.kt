package com.pdproject.abudget.model

class Budget (

    var id: String = ObjectId.get().toHexString(),
    var name: String = "",
    var price: Float = 0f

)