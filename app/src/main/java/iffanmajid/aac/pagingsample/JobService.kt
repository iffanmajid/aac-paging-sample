package iffanmajid.aac.pagingsample

import iffanmajid.aac.pagingsample.data.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
interface JobService {

    @GET("/jobs/search.json")
    fun getJobs(@Query("query") query: String? = null,
                @Query("size") size: Int = 10,
                @Query("from") from: Int = 0) : Call<List<Job>>

    companion object {
        private const val BASE_URL = "https://jobs.search.gov"
        fun createService(): JobService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JobService::class.java)
        }

    }
}