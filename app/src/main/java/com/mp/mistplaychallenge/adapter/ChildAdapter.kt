package com.mp.mistplaychallenge.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mp.mistplaychallenge.model.GameDetail
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mp.mistplaychallenge.R
import kotlinx.android.synthetic.main.game_detail.view.*

/**
 *  ChildAdapter
 *      - This class acts as the adapter class for the Horizontal Recyclerview containing list of games
 *      - It has 3 class member functions implemented as well as a viewholder class declared
 *
 *  onCreateViewHolder
 *      - this function is responsible for creating the viewholder that will contain each item using the layout defined Resources
 *
 *  onBindViewHolder
 *      - this function is called for each viewHolder and the data for that respective item is passed to the viewHolder as well
 *
 *  getItemCount
 *      - returns the size of the data
 *
 *  bindCategory
 *      - sets the textView with game title
 *      - using Glide library sets the imageView with game image icon retrieved from the URL
 */

class ChildAdapter(private var gameDetail: List<GameDetail>, val context: Context) : RecyclerView.Adapter<ChildAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.game_detail, parent,false)
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameList = gameDetail[position]
        holder.bindCategory(gameList)
    }

    override fun getItemCount(): Int {
        return gameDetail.size
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView){

        fun bindCategory(items: GameDetail){
            itemView.gameTitle.text = items.title
            var url = items.img
            var uri = Uri.parse(url)
            Glide.with(context)
                .load(uri)
                .into(itemView.imageView)
        }

    }

}