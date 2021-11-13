package com.udacity.shoestore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ShoeListFragmentBinding

import com.udacity.shoestore.databinding.ShoeDetailsViewBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    companion object {
        fun newInstance() = ShoeListFragment()
    }


    private lateinit var binding: ShoeListFragmentBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.shoe_list_fragment, container, false)
        binding.lifecycleOwner = this
        (activity as MainActivity).binding.toolbar?.title = "Shoe List"
        return binding.root
    }

    override fun onResume() {
        super.onResume()
//        if (viewModel.shoeList.value?.isNotEmpty() == true) {
//            Log.i("ShoeListFragment", "Shoelist not empty")
//            populateViewWithShoes()
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        // TODO: Use the ViewModel
        if (viewModel.shoeList.value?.isNotEmpty() == true) {
            Log.i("ShoeListFragment", "Shoelist not empty")
            populateViewWithShoes()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()?.navigate(R.id.action_shoeListFragment_to_loginFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        binding.shoelistFloatingActionButton.setOnClickListener {
            //val shoe = Shoe("Springer", 21.0, "Adidas","Made for active athletes")
            //Log.i("ShoeListFragment", shoe.name)
           // viewModel.addShoe(shoe)
            view?.findNavController()?.navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            if (shoeList.isEmpty()) {
                binding.shoeListTextView.isVisible = true
            }
            else {
                populateViewWithShoes()
            }

        })
    }

    private fun populateViewWithShoes() {
        binding.shoeListTextView.isVisible = false
        binding.shoelistScrollLinearlayout.removeAllViews()
        viewModel.shoeList.value?.forEach{ shoe ->
            val view = ShoeDetailsViewBinding.inflate(layoutInflater)
            view.shoeNameTextView.text = shoe.name
            view.shoeCompanyTextview.text = shoe.company
            view.shoeDescriptionTextview.text = shoe.description
            binding.shoelistScrollLinearlayout.addView(view.root)

        }
    }



}