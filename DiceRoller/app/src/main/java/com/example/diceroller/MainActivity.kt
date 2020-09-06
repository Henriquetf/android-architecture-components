package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById<Button>(R.id.roll_button)
        val diceImage: ImageView = findViewById(R.id.dice_image)

        rollButton.setOnClickListener {
            rollDice(diceImage)
        }

        val countUpButton: Button = findViewById(R.id.count_up_button)
        val counterText: TextView = findViewById(R.id.counter_text)

        countUpButton.setOnClickListener {
            countUp(counterText)
        }

        val resetCounterButton: Button = findViewById(R.id.reset_counter_button)

        resetCounterButton.setOnClickListener {
            resetCounter(counterText)
        }

        resetCounter(counterText)
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

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = getString(contentDescription)
    }

    private fun countUp(counterTextView: TextView) {
        val currentNumber: Int = counterTextView.text.toString().toIntOrNull() ?: 0
        val nextNumber = currentNumber + 1
        val maxNumber = 6

        if (nextNumber > maxNumber) {
            return
        }

        counterTextView.text = nextNumber.toString()
    }

    private fun resetCounter(counterTextView: TextView) {
        val newValue = 0

        counterTextView.text = newValue.toString()
    }
}