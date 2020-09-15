package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private var myName: MyName = MyName("Garfield The Cat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            doneButton.setOnClickListener {
                addNickname()
            }

            nicknameText.setOnClickListener {
                updateNickname()
            }
        }
    }

    private fun addNickname() {
        binding.apply {
            myName?.nickname = binding.nicknameEdit.text.toString()

            invalidateAll()

            nicknameEdit.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
            doneButton.visibility = View.GONE
        }


        hideSoftInput()
    }

    private fun updateNickname() {
        binding.apply {
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nicknameText.visibility = View.GONE

            nicknameEdit.requestFocus()
        }

        showSoftInput(binding.nicknameEdit)
    }

    private fun showSoftInput(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }

    private fun hideSoftInput() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}