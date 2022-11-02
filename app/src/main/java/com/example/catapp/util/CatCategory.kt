package com.example.catapp.util

import com.example.catapp.util.CatCategory.*

enum class CatCategory(val value: Int, val named :String){
    HAT(1,"Hats"),
    SPACE(2,"Space"),
    SUNGLASSES(4,"Sunglasses"),
    BOXES(5,"Boxes"),
    TIES(7,"Ties"),
    SINKS(14,"Sinks"),
    CLOTHES(15,"Clothes")
}

fun getAllCatCategories(): List<CatCategory>{
    return listOf(
        HAT, SPACE, SUNGLASSES, BOXES, TIES, SINKS, CLOTHES)
}

fun getCatCategory(value: Int): CatCategory? {
    val map = values().associateBy(CatCategory::value)
    return map[value]
}
