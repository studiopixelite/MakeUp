package com.makeup.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.makeup.R
import com.makeup.databinding.ActivityItemDetailsBinding
import com.makeup.databinding.ActivityMainBinding

/*
The ItemDetailsActivity displays more information about the selected item
including a better view of the description and prices.
 */

/**
 * Note: Some items may show an empty image, this is due to the image
 * from the url being empty or having no image.
 */
class ItemDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setValues()
    }
/*
The setValues() get the value passed from intent.putExtra (in MainAdapter.kt)
and place it into the UI listed below to be publicly displayed for the users
 */
    fun setValues(){
        binding.name.text = intent.getStringExtra("name")
        binding.brand.text = intent.getStringExtra("brand")
        binding.price.text = intent.getStringExtra("price")
        binding.description.text = intent.getStringExtra("description")
        binding.itemImage.load(intent.getStringExtra("image")){
            crossfade(true)
            placeholder(R.drawable.ic_no_image)
        }
    }

}