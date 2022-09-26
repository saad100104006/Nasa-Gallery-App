package com.nasa.gallery.mapper

import com.nasa.gallery.domain.model.NasaItemDomain
import com.nasa.gallery.model.NasaItem
import javax.inject.Inject

class NasaItemMapper @Inject constructor(): Mapper<NasaItemDomain, NasaItem> {
    override fun mapDomainToAppLayer(data: NasaItemDomain): NasaItem {
        return with(data) {
            NasaItem(copyright, date, explanation, hdurl, media_type, service_version, title, url)
        }
    }

    override fun mapAppToDomainLayer(data: NasaItem): NasaItemDomain {
        return with(data) {
            NasaItemDomain(copyright, date, explanation, hdurl, media_type, service_version, title, url)
        }
    }
}