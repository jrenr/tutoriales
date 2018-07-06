package acme.com.roomkotlin

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity
data class Tarea(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val descripcion: String,
        val fecha : Date
)