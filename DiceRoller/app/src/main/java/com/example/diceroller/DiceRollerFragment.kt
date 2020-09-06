package com.example.diceroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DiceRollerFragment : Fragment() {

    private lateinit var rollButton: MaterialButton
    private lateinit var countUpButton: MaterialButton
    private lateinit var resetCounterButton: MaterialButton
    private lateinit var diceImage: ShapeableImageView
    private lateinit var counterTextView: MaterialTextView

    private var currentCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice_roler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rollButton = view.findViewById(R.id.roll_button)
        diceImage = view.findViewById(R.id.dice_image)
        countUpButton = view.findViewById(R.id.count_up_button)
        counterTextView = view.findViewById(R.id.counter_text)
        resetCounterButton = view.findViewById(R.id.reset_counter_button)

        rollButton.setOnClickListener {
            rollDice()
        }

        countUpButton.setOnClickListener {
            countUp()
        }

        resetCounterButton.setOnClickListener {
            resetCounter()
        }

        resetCounter()
    }

    private fun rollDice() {
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

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = getString(contentDescription)
    }

    private fun countUp() {
        val nextNumber = currentCounter + 1

        if (nextNumber <= MAX_NUMBER) {
            updateCounter(nextNumber)
        }
    }

    private fun resetCounter() {
        updateCounter(0)
    }

    private fun updateCounter(newValue: Int) {
        currentCounter = newValue
        counterTextView.text = newValue.toString()
    }

    companion object {
        const val MAX_NUMBER = 6
    }
}