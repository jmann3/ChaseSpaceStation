package com.example.mannj.chase_jrm.mvvm.viewmodel

import com.example.mannj.chase_jrm.model.Response
import com.example.mannj.chase_jrm.model.Result
import io.reactivex.Observable

/**
 * Created by mannj on 12/17/17.
 */
class SpaceStationViewModel {

    private val spaceStationRespository = SpaceStationRepository()

    fun getResponseList(latitude: Int, longitude: Int): Observable<List<Response>> = spaceStationRespository.getResponseList(latitude, longitude)

}