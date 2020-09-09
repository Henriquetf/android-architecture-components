package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.diceroller.databinding.FragmentDiceRollerBinding

class DiceRollerFragment : Fragment(R.layout.fragment_dice_roller) {

    private var currentCounter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDiceRollerBinding.bind(view)

        binding.rollButton.setOnClickListener {
            rollDice(binding.diceImage)
            rollDice(binding.secondDiceImage)

            countUp(binding.counterText)
        }

        binding.resetCounterButton.setOnClickListener {
            resetDice(binding.diceImage)
            resetDice(binding.secondDiceImage)

            resetCounter(binding.counterText)
        }

        resetCounter(binding.counterText)
    }

    private fun rollDice(diceImage: ImageView) {
        val randomNumber = (1..6).random()

        val drawableResource = when (randomNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val contentDescription = when (randomNumber) {
            1 -> R.string.dice_1
            2 -> R.string.dice_2
            3 -> R.string.dice_3
            4 -> R.string.dice_4
            5 -> R.string.dice_5
            else -> R.string.dice_6
        }

        updateDice(diceImage, drawableResource, contentDescription)
    }

    private fun resetDice(diceImage: ImageView) {
        updateDice(diceImage, R.drawable.empty_dice, R.string.empty_dice)
    }

    private fun updateDice(diceImage: ImageView, imageResource: Int, contentDescription: Int) {
        diceImage.setImageResource(imageResource)
        diceImage.contentDescription = getString(contentDescription)
    }

    private fun countUp(counterTextView: TextView) {
        updateCounter(currentCounter + 1, counterTextView)
    }

    private fun resetCounter(counterTextView: TextView) {
        updateCounter(0, counterTextView)
    }

    private fun updateCounter(newValue: Int, counterTextView: TextView) {
        currentCounter = newValue
        counterTextView.text = newValue.toString()
    }
}