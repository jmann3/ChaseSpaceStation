package com.example.mannj.chase_jrm

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import com.example.mannj.chase_jrm.model.Response
import com.example.mannj.chase_jrm.mvvm.view.PassesAdapter
import com.example.mannj.chase_jrm.mvvm.viewmodel.SpaceStationViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private var spaceStationViewModel = SpaceStationViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCoordinatesButton()
        setupKeyboardEnterKey()
    }

    private fun setupCoordinatesButton() {
        retrieveResultButton.setOnClickListener({
            displayResults()
        })
    }

    private fun setupKeyboardEnterKey() {
        longitudeText.setOnKeyListener({_, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                displayResults()
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        })

        // TODO: setup separate listener class to avoid duplication
        latitudeText.setOnKeyListener({_, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                displayResults()
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        })
    }

    private fun displayResults() {
        val latitude = latitudeText.text.toString()
        val longitude = longitudeText.text.toString()

        if (!TextUtils.isEmpty(latitude) && !TextUtils.isEmpty((longitude))) {
            //val apiService = SpaceStationApiService.create()
            disposable?.dispose()
            disposable = spaceStationViewModel.getResponseList(latitudeText.text.toString().toInt(), longitudeText.text.toString().toInt())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            { responseList ->
                                settupRecyclerView(responseList)
                            }, { error ->
                        Log.d("Result", "Error is " + error.localizedMessage)
                    })

            closeKeyboard()
        }
    }

    private fun closeKeyboard() {
        val view = this.getCurrentFocus();
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    private fun settupRecyclerView(responseList: List<Response>) {
        resultsRecyclerView.layoutManager
        with (resultsRecyclerView) {
            setLayoutManager(LinearLayoutManager(this@MainActivity))
            setAdapter(PassesAdapter(responseList))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
