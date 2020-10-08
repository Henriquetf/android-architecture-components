/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.entities.SleepQuality
import com.example.android.trackmysleepquality.databinding.FragmentSleepQualityBinding

/**
 * Fragment that displays a list of clickable icons,
 * each representing a sleep quality rating.
 * Once the user taps an icon, the quality is set in the current sleepNight
 * and the database is updated.
 */
class SleepQualityFragment : Fragment() {

    private lateinit var viewModel: SleepQualityViewModel
    private lateinit var binding: FragmentSleepQualityBinding

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_quality, container, false)

        val application = requireNotNull(this.activity).application
        val database = SleepDatabase.getInstance(application).sleepDatabaseDao

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        val sleepQualityViewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, database)
        viewModel = ViewModelProvider(this, sleepQualityViewModelFactory)
                .get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            qualityZeroImage.setOnClickListener { viewModel.onSetSleepQuality(SleepQuality.VERY_BAD) }
            qualityOneImage.setOnClickListener { viewModel.onSetSleepQuality(SleepQuality.POOR) }
            qualityTwoImage.setOnClickListener { viewModel.onSetSleepQuality(SleepQuality.SO_SO) }
            qualityThreeImage.setOnClickListener { viewModel.onSetSleepQuality(SleepQuality.OK) }
            qualityFourImage.setOnClickListener { viewModel.onSetSleepQuality(SleepQuality.PRETTY_GOOD) }
            qualityFiveImage.setOnClickListener { viewModel.onSetSleepQuality(SleepQuality.EXCELLENT) }
        }

        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, { navigate ->
            if (navigate) {
                findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                viewModel.doneNavigating()
            }
        })
    }
}
