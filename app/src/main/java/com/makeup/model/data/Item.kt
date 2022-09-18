package com.makeup.model.data

/* This is the data class for the values gotten from the api response
   */
data class Item(
    var name: String? = null,
    var brand: String? = null,
    var description: String? = null,
    var price: String? = null,
    var price_sign: String? = null,
    var image_link: String? = null,
    var product_type: String? = null
)
