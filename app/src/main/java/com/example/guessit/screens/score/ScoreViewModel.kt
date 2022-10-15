package com.example.guessit.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel () :ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() =  _score

    constructor(score:Int) : this(){
        this._score.value=score
    }
    init {

    }
}