package com.example.android.navigation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding
import timber.log.Timber

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.i("onAttach called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTitleBinding.inflate(inflater, container, false)

        Timber.i("onCreateView called")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.i("onViewCreated called")

        setHasOptionsMenu(true)

        val navController = findNavController()

        binding.playButton.setOnClickListener {
            navController.navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        binding.rulesButton.setOnClickListener {
            navController.navigate(TitleFragmentDirections.actionTitleFragmentToRulesFragment())
        }

        binding.aboutButton.setOnClickListener {
            navController.navigate(TitleFragmentDirections.actionTitleFragmentToAboutFragment())
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("onDetach called")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        Timber.i("onCreateOptionsMenu called")

        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected called")

        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
