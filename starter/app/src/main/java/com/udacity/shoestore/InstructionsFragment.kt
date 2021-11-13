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
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import kotlinx.coroutines.newSingleThreadContext

class InstructionsFragment : Fragment() {

    companion object {
        fun newInstance() = InstructionsFragment()
    }

    private lateinit var binding: InstructionsFragmentBinding
    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.instructions_fragment,container,false)
        (activity as MainActivity).binding.toolbar?.title = "Instructions"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        // TODO: Use the ViewModel
        binding.instructionsButton.setOnClickListener {
            Log.i("InstructionsFragment", "button clicked")
            viewModel.setButtonPressedTrue()
        }

        viewModel.button_pressed.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue == true) {
                view?.findNavController()?.navigate(R.id.action_instructionsFragment_to_shoeListFragment)
                viewModel.setButtonPressedFalse()
            }
        })
    }

}