package iffanmajid.aac.pagingsample

import android.util.Log
import androidx.paging.PagedList
import iffanmajid.aac.pagingsample.data.Job
import iffanmajid.aac.pagingsample.data.database.JobDb
import iffanmajid.aac.pagingsample.utils.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

/**
 * Created by Iffan-mbp13 on 18/11/18.
 * ilutfimajid@gmail.com
 */
class JobBoundaryCallback(private val db: JobDb)
    : PagedList.BoundaryCallback<Job>() {

    private val api = JobService.createService()
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    var jobSize: Int = 0

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        //1
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {callback ->
            api.getJobs()
            //2
                .enqueue(object : Callback<List<Job>> {
                    override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                        //3
                        Log.e("JobBoundaryCallback", "Failed to load data!")
                        callback.recordFailure(t)
                    }

                    override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                        //4
                        executor.execute {
                            db.jobDao().insert(response.body()?: listOf())
                            callback.recordSuccess()
                        }
                    }

                })
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Job) {
        super.onItemAtEndLoaded(itemAtEnd)

        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {callback ->
            executor.execute {
                jobSize = db.jobDao().allJobs().size
                api.getJobs(from = jobSize)
                    .enqueue(object : Callback<List<Job>> {
                        override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                            Log.e("JobBoundaryCallback", "Failed to load data!")
                            callback.recordFailure(t)
                        }

                        override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                            executor.execute {
                                db.jobDao().insert(response.body()?: listOf())
                                callback.recordSuccess()
                            }
                        }

                    })
            }
        }
    }
}