package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById<Button>(R.id.roll_button)
        val resultText: TextView = findViewById(R.id.result_text)

        rollButton.setOnClickListener {
            rollDice(resultText)
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

    private fun rollDice(textView: TextView) {
        val randomNumber = (1..6).random()

        textView.text = randomNumber.toString()
    }

    private fun countUp(textView: TextView) {
        val currentNumber: Int = textView.text.toString().toIntOrNull() ?: 0
        val nextNumber = currentNumber + 1
        val maxNumber = 6

        if (nextNumber > maxNumber) {
            return
        }

        textView.text = nextNumber.toString()
    }

    private fun resetCounter(textView: TextView) {
        val newValue = 0

        textView.text = newValue.toString()
    }
}