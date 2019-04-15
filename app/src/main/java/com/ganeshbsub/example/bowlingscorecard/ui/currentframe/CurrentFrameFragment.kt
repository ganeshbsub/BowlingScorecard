package com.ganeshbsub.example.bowlingscorecard.ui.currentframe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ganeshbsub.example.bowlingscorecard.R

class CurrentFrameFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentFrameFragment()
    }

    private lateinit var viewModel: CurrentFrameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_frame, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentFrameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
