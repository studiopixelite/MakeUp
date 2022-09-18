package com.makeup.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.makeup.databinding.ActivityMainBinding
import com.makeup.viewmodel.MainViewModel

/*
The MainActivity houses the ItemsFragment(), it is the first screen
that shows to the user
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}