package com.ganeshbsub.example.bowlingscorecard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ganeshbsub.example.bowlingscorecard.ui.BowlingGameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class BowlingViewModelTest {

    private lateinit var viewModel: BowlingGameViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = BowlingGameViewModel()
    }

    @Test
    fun resetAllTest() {
        viewModel.resetAll()

        assertEquals(1, viewModel.scorecard.value!!.frameInPlay.value)
        assertEquals(0, viewModel.scorecard.value!!.frames.value!!.size)
        assertEquals(10, viewModel.remainingPinsInFrame.value)
        assertEquals(false, viewModel.gameOver.value)
    }

    @Test
    fun pinsKnockDownTest() {
        viewModel.resetAll()

        viewModel.doKnockDownPins(2)

        assertEquals(1, viewModel.scorecard.value!!.pinsKnockedList.value!!.size)
        assertEquals(1, viewModel.scorecard.value!!.frameInPlay.value)
    }

    @Test
    fun pinsKnockDownNormalFrameCompletedTest() {
        viewModel.scorecard.value!!.frameInPlay.value = 3

        viewModel.doKnockDownPins(3)
        viewModel.doKnockDownPins(4)

        assertEquals(4, viewModel.scorecard.value!!.frameInPlay.value)
    }

    @Test
    fun pinsKnockDownStrikeFrameCompletedTest() {
        viewModel.scorecard.value!!.frameInPlay.value = 1

        viewModel.doKnockDownPins(3)
        viewModel.doKnockDownPins(4)
        viewModel.doKnockDownPins(10)

        assertEquals(3, viewModel.scorecard.value!!.frameInPlay.value)
    }

    @Test
    fun bonusTenthFrameTest() {
        viewModel.scorecard.value!!.frameInPlay.value = 10

        viewModel.doKnockDownPins(3)
        viewModel.doKnockDownPins(7)

        assertEquals(false, viewModel.gameOver.value!!)
    }

    @Test
    fun tenthFrameCompletedTest() {
        viewModel.scorecard.value!!.frameInPlay.value = 10

        viewModel.doKnockDownPins(1)
        viewModel.doKnockDownPins(7)

        assertEquals(true, viewModel.gameOver.value!!)
    }

    @Test
    fun tenthFrameCompletedAfterBonusTest() {
        viewModel.scorecard.value!!.frameInPlay.value = 10

        viewModel.doKnockDownPins(10)
        viewModel.doKnockDownPins(10)

        assertEquals(false, viewModel.gameOver.value!!)

        viewModel.doKnockDownPins(10)
        assertEquals(true, viewModel.gameOver.value!!)
    }

    @Test
    fun calculateTestScoreTest() {
        viewModel.scorecard.value!!.pinsKnockedList.value!!.addAll(listOf(1, 8, 9, 0, 1, 2))

        val initialCalculatedFramesSize = viewModel.scorecard.value!!.frames.value!!.size
        viewModel.calculateScore()

        val newCalculatedFramesSize = viewModel.scorecard.value!!.frames.value!!.size
        assertTrue(newCalculatedFramesSize > initialCalculatedFramesSize)
        assertEquals(3, newCalculatedFramesSize)
    }

    @Test
    fun gameOverTest() {
        assertEquals(false, viewModel.gameOver.value!!)

        val listOfScores = mutableListOf<Int>()
        for (i in 1..20) {
            listOfScores.add(i.rem(4) )
            viewModel.doKnockDownPins(i.rem(4) )
        }

        assertEquals(true, viewModel.gameOver.value!!)
    }
}
