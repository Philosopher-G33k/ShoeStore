package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_details.view.*


class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_details, container, false)
        binding.lifecycleOwner = this
        (activity as MainActivity).binding.toolbar?.title = "Add Shoe"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.shoeDetailsSaveButton.setOnClickListener {view ->
            if ((view.parent as View).shoeNameTextView.text.toString() != "" && (view.parent as View).shoeCompanyTextView.text.toString() != "" && (view.parent as View).shoePriceTextView.text.toString() != "" && (view.parent as View).shoeDescriptionTextView.text.toString() != "") {
                val shoe = Shoe(name = (view.parent as View).shoeNameTextView.text.toString(), price = (view.parent as View).shoePriceTextView.text.toString(), company = (view.parent as View).shoeCompanyTextView.text.toString(), description = (view.parent as View).shoeDescriptionTextView.text.toString())
                shoe.name = binding.shoeNameTextView.text.toString()
                viewModel.addShoe(shoe)
                viewModel.setPopStackTrue()
            }
            else {
                Toast.makeText(requireActivity(), "Please fill all details before saving", Toast.LENGTH_SHORT).show()
            }
        }

        binding.shoeDetailsCancelButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        viewModel.popStack.observe(viewLifecycleOwner, Observer {
            if (it) {
                view?.findNavController()?.popBackStack()
                viewModel.setPopStackFalse()
            }
        })
    }


}