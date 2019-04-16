package com.ganeshbsub.example.bowlingscorecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ganeshbsub.example.bowlingscorecard.ui.currentframe.CurrentFrameFragment
import com.ganeshbsub.example.bowlingscorecard.ui.scorecard.ScorecardFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentSelectedTabId: Int? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_current_game -> {
                if (currentSelectedTabId == R.id.navigation_current_game) return@OnNavigationItemSelectedListener true
                inflateFragment(CurrentFrameFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_current_score -> {
                if (currentSelectedTabId == R.id.navigation_current_score) return@OnNavigationItemSelectedListener true
                inflateFragment(ScorecardFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun inflateFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityMainContentContainer, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        inflateFragment(CurrentFrameFragment.newInstance())
        activityMainBottomNavigationView.selectedItemId = R.id.navigation_current_game


    }
}
