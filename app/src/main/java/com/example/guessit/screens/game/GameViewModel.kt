package com.example.guessit.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel :ViewModel(){
    companion object {

        const val DONE = 0L

        const val ONE_SECOND = 1000L

        const val COUNTDOWN_TIME = 60000L
    }

    private val countDownTimer:CountDownTimer

    private val _timer = MutableLiveData<Long>()
    val timer :LiveData<Long>
        get() = _timer

    private val _hasFinished = MutableLiveData<Boolean>()
    val hasFinished :LiveData<Boolean>
        get() = _hasFinished

    private val _word = MutableLiveData<String>()
    val word :LiveData<String>
    get() = _word

    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
    get() =  _score

    lateinit var wordList: MutableList<String>

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    private fun nextWord() {
        if (wordList.isEmpty()) {
//            _hasFinished.value=true
            resetList()

        }
        _word.value = wordList.removeAt(0)

    }
    fun onSkip() {
        _score.value=_score.value!! - 1
        nextWord()
    }

    fun onCorrect() {
        _score.value=_score.value!! + 1

        nextWord()
    }
    init{
        _score.value = 0
        _word.value = ""
        _hasFinished.value = false

        countDownTimer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _timer.value=millisUntilFinished/1000
            }

            override fun onFinish() {
                _hasFinished.value=true
            }
        }
        countDownTimer.start()
        println("GameViewModel Created")
        resetList()
        nextWord()

    }
    fun finishedEvent(){
        _hasFinished.value=false
    }
    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
        println("GameViewModel Closed")
    }
}