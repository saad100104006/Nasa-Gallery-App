package com.nasa.gallery.local.mapper

import com.nasa.gallery.data.model.NasaItemData
import com.nasa.gallery.local.model.NasaItemLocal
import javax.inject.Inject


class NasaItemLocalMapper @Inject constructor() : Mapper<NasaItemLocal, NasaItemData> {
    override fun mapLocalToData(data: NasaItemLocal): NasaItemData {
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

    override fun mapDataToLocal(data: NasaItemData): NasaItemLocal {
        return with(data) {
            NasaItemLocal(
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