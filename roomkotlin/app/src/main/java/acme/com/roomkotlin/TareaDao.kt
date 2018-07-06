package acme.com.roomkotlin

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface TareaDao : BaseDao<Tarea> {

    @Query("SELECT * FROM Tarea WHERE id=:id")
    fun getById(id: Long) : Tarea
}