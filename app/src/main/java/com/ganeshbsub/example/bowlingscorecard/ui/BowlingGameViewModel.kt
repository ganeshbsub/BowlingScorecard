package com.ganeshbsub.example.bowlingscorecard.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.ganeshbsub.example.bowlingscorecard.model.Scorecard

class BowlingGameViewModel : ViewModel() {

    private val scorecard = MutableLiveData<Scorecard>()
    var remainingPinsInFrame = MutableLiveData<Int>()
    private var ballRollsInFrame = 0
    var gameOver = MutableLiveData<Boolean>()

    val frames = switchMap(scorecard) { it.frames }!!
    val pinsKnockedList = switchMap(scorecard) { it.pinsKnockedList }!!
    val frameInPlay = switchMap(scorecard) { it.frameInPlay }!!

    init {
        resetAll()
    }

    fun doKnockDownPins(numberOfPins: Int) {
        scorecard.value!!.pinsKnockedList.value!!.add(numberOfPins)

        ballRollsInFrame++
        remainingPinsInFrame.value = remainingPinsInFrame.value!! - numberOfPins

        if (remainingPinsInFrame.value == 0 || ballRollsInFrame == 2) {
            resetFrame()
        }
    }

    fun calculateScore() {
        scorecard.value!!.calculateAllScores()
    }

    private fun resetFrame() {
        if (frameInPlay.value!! + 1 == 11) {
            gameOver.value = true
        }
        scorecard.value!!.frameInPlay.value = frameInPlay.value!! + 1
        remainingPinsInFrame.value = 10
        ballRollsInFrame = 0
    }

    fun resetAll() {
        scorecard.value = Scorecard()
        remainingPinsInFrame.value = 10
        gameOver.value = false
    }
}
