package com.udacity.shoestore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import timber.log.Timber
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        Timber.plant(Timber.DebugTree())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_item_logout ->
            if (binding.fragmentContainerView.findNavController().currentDestination?.id.toString() != R.id.loginFragment.toString()) {
                binding.fragmentContainerView.findNavController().popBackStack(R.id.loginFragment,false)
            }else {
                
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
