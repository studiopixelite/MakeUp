package com.makeup.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.makeup.R
import com.makeup.model.data.Item

class MainAdapter: RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gridImage : ImageView = itemView.findViewById(R.id.gridImage)
        val nameText : TextView = itemView.findViewById(R.id.nameText)
        val productType : TextView = itemView.findViewById(R.id.productType)
        var priceText: TextView = itemView.findViewById(R.id.priceText)
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
        holder.priceText.text = "${currentItem.price_sign} ${currentItem.price}"
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(newList: List<Item>){
        itemList = newList
        notifyDataSetChanged()
    }
}