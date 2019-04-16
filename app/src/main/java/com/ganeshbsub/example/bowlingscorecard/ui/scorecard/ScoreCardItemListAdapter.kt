package com.ganeshbsub.example.bowlingscorecard.ui.scorecard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ganeshbsub.example.bowlingscorecard.R
import com.ganeshbsub.example.bowlingscorecard.model.Frame
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_scorecard_list_item.*

class ScoreCardItemListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val frames = mutableListOf<Frame>()

    fun clear() {
        frames.clear()
        notifyDataSetChanged()
    }

    fun addAll(items: List<Frame>) {
        frames.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_scorecard_list_item, parent, false)
        return FrameViewHolder(view)
    }

    override fun getItemCount(): Int = frames.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val frame = frames[position]
        bindFrameViewHolder(holder as FrameViewHolder, frame)
    }

    private fun bindFrameViewHolder(holder: FrameViewHolder, frame: Frame) {
        holder.ballOne.text = frame.pinsKnockedList[0].toString()
        when {
            frame.type == Frame.Type.STRIKE -> holder.ballTwo.setBackgroundResource(R.drawable.background_strike)
            frame.type == Frame.Type.SPARE -> holder.ballTwo.setBackgroundResource(R.drawable.background_spare)
            else -> {
                holder.ballTwo.text = frame.pinsKnockedList[1].toString()
            }
        }
        if (frame.pinsKnockedList.size == 3) {
            holder.ballThree.visibility = View.VISIBLE
            holder.ballThree.text = frame.pinsKnockedList[2].toString()
        }
        holder.frameScore.text = frame.score.toString()

    }

    class FrameViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        internal val ballOne = viewScorecardListItemBallOne
        internal val ballTwo = viewScorecardListItemBallTwo
        internal val ballThree = viewScorecardListItemBallExtra
        internal val frameScore = viewScorecardListItemFrameScore
    }
}
