package com.irzstudio.latihanpraeznote.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.irzstudio.latihanpraeznote.Data.Item
import com.irzstudio.latihanpraeznote.R
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerAdapter(val listItem: ArrayList<Item>) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    var onClickListener: OnItemListener? = null

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : Item){
            itemView.tv_item.text = item.nameitem
            if (adapterPosition % 2 == 0){
                itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.white))

            }else{
                itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.grey))
            }

            //untuk menghapus list dengan menggunakan ic_delete yg terhubung dengan interface di OnItemListener
            itemView.ic_delete.setOnClickListener {
                onClickListener?.onDelete(item)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ItemViewHolder, position: Int) {
       holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


}