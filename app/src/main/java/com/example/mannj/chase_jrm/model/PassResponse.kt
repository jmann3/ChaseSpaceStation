package com.example.mannj.chase_jrm.model

import android.support.annotation.IntegerRes
import java.time.LocalDateTime
import java.util.*

/**
 * Created by mannj on 12/16/17.
 */

data class Response(
    val duration: Int,
    val risetime: Long
    )

data class Request(
        val altitude: Int,
        val datetime: Long,
        val latitude: Int,
        val longitude: Int,
        val passes: Int
)

data class Result (
        val message: String,
        val request: Request,
        val response: List<Response>
)