package com.nasa.gallery.data.mapper

import com.nasa.gallery.data.model.NasaItemData
import com.nasa.gallery.domain.model.NasaItemDomain
import javax.inject.Inject


class NasaItemMapper @Inject constructor() : Mapper<NasaItemData, NasaItemDomain> {
    override fun mapDataToDomain(data: NasaItemData): NasaItemDomain {
        return with(data) {
            NasaItemDomain(
                copyright,
                date,
                explanation,
                hdurl,
                media_type,
                service_version,
                title,
                url
            )
        }
    }

    override fun mapDomainToData(data: NasaItemDomain): NasaItemData {
        return with(data) {
            NasaItemData(
                copyright,
                date,
                explanation,
                hdurl,
                media_type,
                service_version,
                title,
                url
            )
        }
    }
}