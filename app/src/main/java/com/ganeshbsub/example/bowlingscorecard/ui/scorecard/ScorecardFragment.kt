package com.ganeshbsub.example.bowlingscorecard.ui.scorecard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ganeshbsub.example.bowlingscorecard.R
import com.ganeshbsub.example.bowlingscorecard.ui.BowlingGameViewModel

class ScorecardFragment : Fragment() {

    companion object {
        fun newInstance() = ScorecardFragment()
    }

    private lateinit var viewModel: BowlingGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scorecard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BowlingGameViewModel::class.java)
        
    }

}
