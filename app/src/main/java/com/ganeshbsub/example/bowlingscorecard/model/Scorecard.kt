package com.ganeshbsub.example.bowlingscorecard.model

data class Scorecard (
    val frames: List<FrameTry>,
    val frameScores: MutableList<Int>,
    var currentFrame: Int = 1
) {

}
