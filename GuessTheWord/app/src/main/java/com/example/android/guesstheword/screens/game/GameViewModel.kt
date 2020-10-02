package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _word = MutableLiveData<String>("")
    private val _score = MutableLiveData(0)
    private val _eventGameFinished = MutableLiveData<Boolean>(false)
    private val currentTime = MutableLiveData<Long>()

    val word: LiveData<String>
        get() = _word

    val score: LiveData<Int>
        get() = _score

    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished

    val currentTimeString: LiveData<String> = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    val wordHint = Transformations.map(word) { word ->
        val randomPosition = (1..word.length).random()
        val letter = word[randomPosition - 1].toUpperCase()

        "Current word has ${word.length} letters\nThe letter at position $randomPosition is $letter"
    }

    private val timer: CountDownTimer

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel", "GameViewModel created")

        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_INTERVAL) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                currentTime.value = COUNTDOWN_DONE

                onGameFinished()
            }
        }

        timer.start()
    }

    /**
     * Resets the list of words and randomizes the order
     */
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

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        } else {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishedComplete() {
        _eventGameFinished.value = false
    }

    fun onGameFinished() {
        _eventGameFinished.value = true
    }

    override fun onCleared() {
        super.onCleared()

        timer.cancel()

        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    companion object {
        private const val ONE_SECOND = 1000L

        private const val COUNTDOWN_DONE = 0L
        private const val COUNTDOWN_INTERVAL = 1000L
        private const val COUNTDOWN_TIME = 60000L
    }
}