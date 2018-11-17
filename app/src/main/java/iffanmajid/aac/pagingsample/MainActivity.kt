package iffanmajid.aac.pagingsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import iffanmajid.aac.pagingsample.data.Job
import iffanmajid.aac.pagingsample.data.database.JobDb
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
class MainActivity : AppCompatActivity() {

    private val adapter = JobAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeList()
    }

    private fun initializeList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        //1
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()

        //2
        val liveData = initializedPagedListBuilder(config).build()

        //3
        liveData.observe(this, Observer<PagedList<Job>> { pagedList ->
            adapter.submitList(pagedList)
        })
    }


    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Job> {

        val database = JobDb.create(this)
        val livePageListBuilder = LivePagedListBuilder<Int, Job>(
            database.jobDao().posts(),
            config)
        livePageListBuilder.setBoundaryCallback(JobBoundaryCallback(database))
        return livePageListBuilder
    }

}