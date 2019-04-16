package com.ganeshbsub.example.bowlingscorecard.model

import androidx.lifecycle.MutableLiveData
import com.ganeshbsub.example.bowlingscorecard.model.Frame.Type.SPARE
import com.ganeshbsub.example.bowlingscorecard.model.Frame.Type.STRIKE

/*
* A model representation of all frames already played/in-play
* in the current Bowling Game.
 */
data class Scorecard(
    val pinsKnockedList: MutableLiveData<MutableList<Int>> = MutableLiveData(),
    val frames: MutableLiveData<HashMap<Int, Frame>> = MutableLiveData(),
    var frameInPlay: MutableLiveData<Int> = MutableLiveData()
) {
    var latestScoredFrameIndex = 0

    init {
        pinsKnockedList.value = mutableListOf()
        frames.value = HashMap()
        frameInPlay.value = 1
    }

    /*
    * Call this function to calculate the current score, present in frames
    * Also updates previously scores which were not calculated
    *
    * Current
     */
    fun calculateAllScores() {
        if (latestScoredFrameIndex == TOTAL_POSSIBLE_FRAMES - 1) return //This means that all frames have already been scored

        var tempScore = 0
        var ballCount = 0

        val listPinsKnocked = pinsKnockedList.value!!

        try {
            listPinsKnocked.forEachIndexed { index, pins ->
                fun incrementFrameAndResetCounters() {
                    latestScoredFrameIndex ++
                    tempScore = 0
                    ballCount = 0
                }

                if (latestScoredFrameIndex > 9) return@forEachIndexed //We have already reached the last frame

                when {
                    frames.value!![index] != null -> {
                        //Score for this frame has already been calculated
                        incrementFrameAndResetCounters()
                    }
                    pins == 10 -> {
                        //When it's a Strike
                        val score = (frames.value!![latestScoredFrameIndex -1]?.score ?: 0) + 10 + listPinsKnocked[index + 1] + listPinsKnocked[index + 2]
                        frames.value!![latestScoredFrameIndex] = Frame(pinsKnockedList = listOf(pins), score = score, type = STRIKE)
                        incrementFrameAndResetCounters()
                    }
                    pins + tempScore == 10 -> {
                        //When it's a Spare
                        val score = (frames.value!![latestScoredFrameIndex -1]?.score ?: 0) + 10 + listPinsKnocked[index + 1]
                        frames.value!![latestScoredFrameIndex] = Frame(pinsKnockedList = listOf(tempScore, pins), score = score, type = SPARE)
                        incrementFrameAndResetCounters()
                    }
                    ballCount == 0 -> {
                        //First roll of the ball for the current frame
                        tempScore += pins
                        ballCount++
                    }
                    else -> {
                        //Second roll of the ball for the current frame
                        val score = (frames.value!![latestScoredFrameIndex -1]?.score ?: 0) + tempScore + pins
                        frames.value!![latestScoredFrameIndex] = Frame(listOf(tempScore, pins), score)
                        incrementFrameAndResetCounters()
                    }
                }
            }
        } catch (e: Exception) {
            // This is expected, so handled gracefully
            // We reached the end of the pinsKnockedList list while still calculating a Spare or Strike frame.
            // This means that there are still some rolls of the ball remaining in the game.
        }
    }
}

const val TOTAL_POSSIBLE_FRAMES = 10
