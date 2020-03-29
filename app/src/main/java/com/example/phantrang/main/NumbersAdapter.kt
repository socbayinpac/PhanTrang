package com.example.phantrang.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.phantrang.R
import com.example.phantrang.data.db.model.NumberItem
import kotlinx.android.synthetic.main.item_number.view.*

class NumbersAdapter: PagedListAdapter<NumberItem, NumbersAdapter.NumberHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberHolder {
        return NumberHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NumberHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }

    }

    class NumberHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(numberItem: NumberItem) {
            itemView.textView2.setText(numberItem.content.toString())
        }


        companion object {
            fun from(parent: ViewGroup): NumberHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
                return NumberHolder(view)
            }
        }
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<NumberItem>() {
            override fun areItemsTheSame(oldItem: NumberItem, newItem: NumberItem): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NumberItem, newItem: NumberItem): Boolean {
               return oldItem == newItem
            }

        }
    }
}