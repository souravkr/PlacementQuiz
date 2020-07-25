package com.example.android.placementquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.placementquiz.databinding.FragmentScoreBinding
import com.example.android.placementquiz.databinding.FragmentTitleBinding


class ScoreFragment  : Fragment() {
    lateinit var score :Integer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentScoreBinding

                = DataBindingUtil.inflate(inflater , R.layout.fragment_score,container,false)

        var args = arguments?.let { ScoreFragmentArgs.fromBundle(it) }
        binding.names = this

        var string : String ="You scored ${args?.score} out of 5"
        binding.scoreTextView.setText(string)

        if (args != null) {
            Log.i("same","${args.score}")
        }

        return binding.root

    }

}

