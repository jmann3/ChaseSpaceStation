package com.example.mannj.chase_jrm.mvvm.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.mannj.chase_jrm.model.Response
import kotlinx.android.synthetic.main.single_pass_row.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mannj on 12/17/17.
 */
class SinglePassViewHolder(private val view: View?) : RecyclerView.ViewHolder(view) {

    fun bind(response: Response){
        view?.timeValue?.text = "Time: " + readableDateFromCurrentTime(response.risetime)
        view?.durationValue?.text = "Duration: " + response.duration + " sec"
    }

    companion object {
        fun readableDateFromCurrentTime(dateMillis: Long) : String {
            val sdf = SimpleDateFormat("MM/dd/yy HH:mm:ss")
            return sdf.format(Date(dateMillis * 1000))
        }
    }
}