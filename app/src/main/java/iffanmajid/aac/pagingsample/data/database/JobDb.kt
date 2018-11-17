package iffanmajid.aac.pagingsample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import iffanmajid.aac.pagingsample.data.Job

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
@Database(
    entities = [Job::class],
    version = 1,
    exportSchema = false
)
abstract class JobDb : RoomDatabase() {

    companion object {
        fun create(context: Context): JobDb {
            val databaseBuilder = Room.databaseBuilder(context, JobDb::class.java, "job.db")
            return databaseBuilder.build()
        }
    }

    abstract fun jobDao(): JobDao
}