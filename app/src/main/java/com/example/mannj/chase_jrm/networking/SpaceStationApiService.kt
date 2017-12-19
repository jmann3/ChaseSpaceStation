package com.example.mannj.chase_jrm.networking

import com.example.mannj.chase_jrm.model.Result
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mannj on 12/16/17.
 */
interface SpaceStationApiService {

    @GET("iss-pass.json")
    fun getResult(@Query("lat") lat: Int,
                  @Query("lon") lon: Int) : Observable<Result>

    companion object Factory {
        fun create(): SpaceStationApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.open-notify.org/")
                    .build()

            return retrofit.create(SpaceStationApiService::class.java)
        }
    }
}

