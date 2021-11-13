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
import com.udacity.shoestore.databinding.LoginFragmentBinding
import androidx.appcompat.app.AppCompatActivity

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.login_fragment,container,
        false)
        /* return inflater.inflate(R.layout.login_fragment, container, false) */
        (activity as MainActivity).binding.toolbar?.title = "FootLoose"
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        binding.loginButton.setOnClickListener {
            Log.i("LoginFragment", "Button Pressed")
            viewModel.setButtonPressedTrue()
        }

        binding.signupButton.setOnClickListener {
            Log.i("LoginFragment", "Button Pressed")
            viewModel.setButtonPressedTrue()
        }

        viewModel.buttonPressed.observe(viewLifecycleOwner, Observer { newValue ->
            Log.i("LoginFragment","Value of button pressed changed")
            Log.i("LoginFragment",newValue.toString())
            if (newValue == true) {
                Log.i("LoginFragment", "You should navigate to next screen now")
                // We've won!  Navigate to the gameWonFragment.
                view?.findNavController()?.navigate(R.id.action_loginFragment_to_welcomeFragment)
//                view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                viewModel.setButtonPressedFalse()
            }

        })

    }

}