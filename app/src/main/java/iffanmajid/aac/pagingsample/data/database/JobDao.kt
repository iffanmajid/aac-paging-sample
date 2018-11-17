package iffanmajid.aac.pagingsample.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iffanmajid.aac.pagingsample.data.Job

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jobs: List<Job>)

    @Query("SELECT * FROM Job")
    fun posts(): DataSource.Factory<Int, Job>

    @Query("SELECT * FROM Job")
    fun allJobs(): Array<Job>
}