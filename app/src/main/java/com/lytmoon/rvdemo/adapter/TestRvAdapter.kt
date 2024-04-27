package com.lytmoon.rvdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lytmoon.rvdemo.R
import com.lytmoon.rvdemo.bean.RvData


/**
 *  author : lytMoon
 *  email : yytds@foxmail.com
 *  date: 2024/4/25 14:38
 *  version : 1.0
 *  description :
 *  saying : 这世界天才那么多，也不缺我一个
 */
class TestRvAdapter :
    ListAdapter<RvData, TestRvAdapter.FirstViewHolder>(object : DiffUtil.ItemCallback<RvData>() {

        override fun areItemsTheSame(oldItem: RvData, newItem: RvData): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: RvData, newItem: RvData): Boolean {
            return oldItem.number == newItem.number
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main, parent, false)
        return FirstViewHolder((itemView))

    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        //拿到相应位置的数据项，进行绑定。
        val itemData = getItem(position)
        holder.bind(itemData)

    }

    inner class FirstViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_item_rv)

        fun bind(itemData: RvData) {
            textView.text = itemData.number.toString()
        }

    }

}