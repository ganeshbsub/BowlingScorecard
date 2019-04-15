package com.ganeshbsub.example.bowlingscorecard.ui.scorecard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ganeshbsub.example.bowlingscorecard.R

class ScorecardFragment : Fragment() {

    companion object {
        fun newInstance() = ScorecardFragment()
    }

    private lateinit var viewModel: ScorecardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_frame, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScorecardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
