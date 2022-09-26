package com.nasa.gallery.data.mapper

interface Mapper<I, O> {
    fun mapDataToDomain(data: I): O
    fun mapDomainToData(data: O): I
}