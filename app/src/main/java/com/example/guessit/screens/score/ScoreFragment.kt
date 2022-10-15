

package com.example.guessit.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guessit.R
import com.example.guessit.databinding.ScoreFragmentBinding
import com.example.guessit.screens.game.GameViewModel

/**
 * Fragment where the final score is shown, after the game is over
 */
class ScoreFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        viewModelFactory= ScoreViewModelFactory(scoreFragmentArgs.score)
        viewModel = ViewModelProvider(this,viewModelFactory)[ScoreViewModel::class.java]
        viewModel.score.observe(viewLifecycleOwner, Observer { score->
            binding.scoreText.text = scoreFragmentArgs.score.toString()
        })
        binding.playAgainButton.setOnClickListener { onPlayAgain() }

        return binding.root
    }

    private fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionRestart())
    }


}
