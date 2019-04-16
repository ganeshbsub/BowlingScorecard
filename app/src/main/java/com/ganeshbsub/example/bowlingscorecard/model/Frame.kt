package com.ganeshbsub.example.bowlingscorecard.model

import com.ganeshbsub.example.bowlingscorecard.model.Frame.Type.NORMAL
/*
* A model to keep state of each frame
* and help with UI representation for the most part
 */
data class Frame(
    val pinsKnockedList: List<Int> = mutableListOf(),
    var score: Int = 0,
    val type: Type = NORMAL
) {
    enum class Type {
        STRIKE, SPARE, NORMAL
    }
}
