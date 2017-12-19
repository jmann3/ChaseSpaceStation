package com.example.mannj.chase_jrm.mvvm.viewmodel

import com.example.mannj.chase_jrm.model.Response
import com.example.mannj.chase_jrm.networking.SpaceStationApiService
import io.reactivex.Observable

/**
 * Created by mannj on 12/17/17.
 */
class SpaceStationRepository {

    fun getResponseList(latitude: Int, longitude: Int): Observable<List<Response>> {
        return SpaceStationApiService.create().getResult(latitude, longitude)
                .flatMap { result -> Observable.just(result.response) }

    }
}