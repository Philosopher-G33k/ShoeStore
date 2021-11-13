package com.udacity.shoestore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var binding: WelcomeFragmentBinding
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.welcome_fragment,container,false)
        (activity as MainActivity).binding.toolbar?.title = "Welcome"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
        binding.enterButton.setOnClickListener {
            Log.i("WelcomeFragment", "enter button clicked")
            viewModel.setButtonClickTrue()
        }

        viewModel.button_clicked.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue == true) {
                // Navigate to next screen
                view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_instructionsFragment)
                viewModel.setButtonClickedFalse()
            }
        })
    }

}