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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.database.dao.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.entities.SleepQuality
import kotlinx.coroutines.launch

class SleepQualityViewModel(
        private val sleepNightKey: Long = 0L,
        private val database: SleepDatabaseDao
) : ViewModel() {

    private val _navigateToSleepTracker = MutableLiveData<Boolean>(false)
    val navigateToSleepTracker: LiveData<Boolean>
        get() = _navigateToSleepTracker

    fun onSetSleepQuality(quality: SleepQuality) {
        viewModelScope.launch {
            updateSleepQuality(quality)

            _navigateToSleepTracker.value = true
        }
    }

    fun doneNavigating() {
        _navigateToSleepTracker.value = false
    }

    private suspend fun updateSleepQuality(sleepQuality: SleepQuality) {
        val tonight = database.get(sleepNightKey) ?: return

        tonight.sleepQuality = sleepQuality.value
        database.update(tonight)
    }
}