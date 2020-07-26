package com.example.android.placementquiz

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.placementquiz.databinding.FragmentScoreBinding


class ScoreFragment  : Fragment() {
    lateinit var score :Integer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentScoreBinding

                = DataBindingUtil.inflate(inflater , R.layout.fragment_score,container,false)


        var args = ScoreFragmentArgs.fromBundle(requireArguments())

        var score = args.score

        //Check the score and adust the messeage accordingly
        when {
            score <= 2 -> {
                binding.textView2.setText(getString(R.string.opps))
                binding.layout.setBackgroundColor(Color.RED)
            }
            score <= 4 -> {
                binding.textView2.setText(getString(R.string.GreatWork))
                binding.layout.setBackgroundColor(Color.YELLOW)
            }
            score == 5 -> {
                binding.textView2.setText(getString(R.string.congo))
                binding.layout.setBackgroundColor(Color.GREEN)
            }

        }

        (activity as AppCompatActivity).supportActionBar?.title = (getString(R.string.javaScore))
        var string : String ="You scored ${args?.score} out of 5"
        binding.scoreTextView.setText(string)

        binding.playAgainButton.setOnClickListener { view: View? ->

            view?.findNavController()?.navigate(R.id.action_scoreFragment_to_titleFragment)
        }

        return binding.root

    }

}

