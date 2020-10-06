package com.mp.mistplaychallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mp.mistplaychallenge.model.GameCategory
import androidx.recyclerview.widget.RecyclerView
import com.mp.mistplaychallenge.R
import kotlinx.android.synthetic.main.game_category.view.*

/**
 *  ParentAdapter
 *      - This class acts as the adapter class for the Vertical Recyclerview in the app
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
 *      - sets the category textView with the category title from the current position in the list
 *      - sets the horizontal recycler view within that respective in the list by creating LinearLayout with Horizontal orientation
 *      - ChildAdapter called and the sub list within the GameCategory data class is passed as the list object to the child adapter
 */

class ParentAdapter(private var allCategory: List<GameCategory>, val context: Context) : RecyclerView.Adapter<ParentAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.game_category, parent, false)
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: ParentAdapter.ViewHolder, position: Int) {
        val category = allCategory[position]
        holder.bindCategory(category)
    }

    override fun getItemCount(): Int {
        return allCategory.size
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView){

        fun bindCategory(items: GameCategory){
            itemView.category.text = items.list_title
            itemView.horizontalView.apply {
                layoutManager = LinearLayoutManager(horizontalView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ChildAdapter(items.games!!, context)
            }
        }
    }
}