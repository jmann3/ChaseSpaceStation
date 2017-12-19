package com.example.mannj.chase_jrm.mvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mannj.chase_jrm.R
import com.example.mannj.chase_jrm.model.Response

/**
 * Created by mannj on 12/17/17.
 */
class PassesAdapter(var responseList: List<Response>) : RecyclerView.Adapter<SinglePassViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePassViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.single_pass_row, parent, false).
                also {response -> return SinglePassViewHolder(response)
        }

        return SinglePassViewHolder(null)
    }

    override fun getItemCount(): Int = responseList.size

    override fun onBindViewHolder(holder: SinglePassViewHolder?, position: Int) {
        holder?.bind(responseList[position])
    }
}