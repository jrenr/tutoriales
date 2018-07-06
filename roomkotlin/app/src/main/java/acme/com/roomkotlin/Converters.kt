package acme.com.roomkotlin

import android.arch.persistence.room.TypeConverter
import java.util.*

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimeStamp(valor : Long?) : Date? {
        return if (valor==null) null else Date(valor)
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(fecha : Date?) : Long? {
        return if (fecha == null) null else fecha.time
    }
}