package com.ganeshbsub.example.bowlingscorecard.ui.scorecard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.ganeshbsub.example.bowlingscorecard.R
import com.ganeshbsub.example.bowlingscorecard.ui.BowlingGameViewModel
import kotlinx.android.synthetic.main.fragment_scorecard.*

class ScorecardFragment : Fragment() {

    private lateinit var viewModel: BowlingGameViewModel
    private lateinit var adapter: ScoreCardItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scorecard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(BowlingGameViewModel::class.java)
        setupList()
        setDataObservers()
        viewModel.calculateScore()
    }

    private fun setupList() {
        adapter = ScoreCardItemListAdapter()
        fragmentScorecardRecyclerView.adapter = adapter
        fragmentScorecardRecyclerView.layoutManager = StaggeredGridLayoutManager(5, VERTICAL)
    }

    private fun setDataObservers() {
        viewModel.frames.observe(this, Observer {
            adapter.clear()
            adapter.addAll(it.values.toList())
        })
    }

    companion object {
        fun newInstance() = ScorecardFragment()
    }
}
