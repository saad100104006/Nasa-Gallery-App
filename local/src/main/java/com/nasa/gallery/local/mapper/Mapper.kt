package com.nasa.gallery.local.mapper


interface Mapper<I, O> {
    fun mapLocalToData(data: I): O
    fun mapDataToLocal(data: O): I
}