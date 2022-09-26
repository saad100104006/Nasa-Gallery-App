package com.nasa.gallery.local.source

import android.content.Context
import com.google.gson.Gson
import com.nasa.gallery.local.model.NasaItemLocal
import org.json.JSONArray
import java.nio.charset.Charset
import javax.inject.Inject

class NasaBusinessLogic @Inject constructor(private val context: Context) {

    operator fun invoke(): List<NasaItemLocal> {
        val inputStream = context.assets.open("data.json")
        val nasaInfo = mutableListOf<NasaItemLocal>()
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.forName("UTF-8"))
        val items = JSONArray(json)
        val gson = Gson()
        for (i in 0 until items.length()) {
            val obj = items.getJSONObject(i)
            val info = gson.fromJson(obj.toString(), NasaItemLocal::class.java)
            nasaInfo.add(info)
        }
        return nasaInfo
    }
}