package com.ganeshbsub.example.bowlingscorecard.model

import com.ganeshbsub.example.bowlingscorecard.model.Frame.Type.SPARE
import com.ganeshbsub.example.bowlingscorecard.model.Frame.Type.STRIKE

/*
* A model representation of all frames already played/in-play
* in the current Bowling Game.
 */
data class Scorecard(
    val pinsKnockedList: List<Int> = mutableListOf(),
    val frames: MutableMap<Int, Frame> = HashMap(),
    var frameInPlay: Int = 1
) {

    var latestScoredFrameIndex = 0

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

        try {
            pinsKnockedList.forEachIndexed { index, pinsKnocked ->
                fun incrementFrameAndResetCounters() {
                    latestScoredFrameIndex ++
                    tempScore = 0
                    ballCount = 0
                }

                if (latestScoredFrameIndex > 9) return@forEachIndexed //We have already reached the last frame

                when {
                    frames[index] != null -> {
                        //Score for this frame ahs already been calculated
                        incrementFrameAndResetCounters()
                    }
                    pinsKnocked == 10 -> {
                        //When it's a Strike
                        val score = (frames[latestScoredFrameIndex -1]?.score ?: 0) + 10 + pinsKnockedList[index + 1] + pinsKnockedList[index + 2]
                        frames[latestScoredFrameIndex] = Frame(pinsKnockedList = listOf(pinsKnocked), score = score, type = STRIKE)
                        incrementFrameAndResetCounters()
                    }
                    pinsKnocked + tempScore == 10 -> {
                        //When it's a Spare
                        val score = (frames[latestScoredFrameIndex -1]?.score ?: 0) + 10 + pinsKnockedList[index + 1]
                        frames[latestScoredFrameIndex] = Frame(pinsKnockedList = listOf(tempScore, pinsKnocked), score = score, type = SPARE)
                        incrementFrameAndResetCounters()
                    }
                    ballCount == 0 -> {
                        //First roll of the ball for the current frame
                        tempScore += pinsKnocked
                        ballCount++
                    }
                    else -> {
                        //Second roll of the ball for the current frame
                        tempScore += pinsKnocked
                        val score = (frames[latestScoredFrameIndex -1]?.score ?: 0) + tempScore
                        frames[latestScoredFrameIndex] = Frame(listOf(tempScore, pinsKnocked), score)
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
