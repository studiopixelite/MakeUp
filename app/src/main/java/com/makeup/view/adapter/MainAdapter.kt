package com.makeup.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.makeup.R
import com.makeup.model.data.Item
import com.makeup.view.activity.ItemDetailsActivity
import com.makeup.view.fragment.ItemsFragment

class MainAdapter: RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // Each UI component is declared to be passed into the Adapter
        val gridImage : ImageView = itemView.findViewById(R.id.gridImage)
        val nameText : TextView = itemView.findViewById(R.id.nameText)
        val brandName : TextView = itemView.findViewById(R.id.brandName)
        val productType : TextView = itemView.findViewById(R.id.productType)
        val priceText: TextView = itemView.findViewById(R.id.priceText)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        var description: String? = null
        var imgUrl: String? = null

        // This function is called immediatly the MainAdapter() is initialised
        init {
            cardView.setOnClickListener {
                val i = Intent(itemView.context, ItemDetailsActivity::class.java)
                i.putExtra("name", nameText.text)
                i.putExtra("brand", brandName.text)
                i.putExtra("price", priceText.text)
                i.putExtra("description", description)
                i.putExtra("image", imgUrl)
                itemView.context.startActivity(i)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.gridImage.load(currentItem.image_link){
            crossfade(true)
            placeholder(R.drawable.ic_no_image)
        }

        holder.nameText.text = currentItem.name
        holder.productType.text = currentItem.product_type
        holder.brandName.text = currentItem.brand
        holder.description = currentItem.description
        holder.imgUrl = currentItem.image_link
        holder.priceText.text = "${currentItem.price_sign} ${currentItem.price}"
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    /* setData() puts newList of type "List" into the adapter
    then notify in case any changes have been made
     */

    fun setData(newList: List<Item>){
        itemList = newList
        notifyDataSetChanged()
    }
}