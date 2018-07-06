package acme.com.roomkotlin

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class TareaDaoTest {

    lateinit var mDb : AppDatabase
    lateinit var mTareaDao: TareaDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getContext()
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        mTareaDao = mDb.tareaDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertarYLeerTarea(){
        val tarea = Tarea(0, "Tarea 1", Date())
        mTareaDao.insert(tarea)
        val tareaById = mTareaDao.getById(1)
        assertThat(tareaById.descripcion, equalTo("Tarea 1"))
    }
}