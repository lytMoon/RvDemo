package com.lytmoon.rvdemo.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lytmoon.rvdemo.R

/**
 *  author : lytMoon
 *  email : yytds@foxmail.com
 *  date: 2024/4/23 11:36
 *  version : 1.0
 *  description :
 *  saying : 这世界天才那么多，也不缺我一个
 */


class SimpleRvAdapter(private val dataSet: List<Int>) :
    RecyclerView.Adapter<SimpleRvAdapter.SimpleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_main, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {

        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    inner class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.tv_item_rv)

        fun bind(value: Int) {
            textView.text = value.toString()
        }


    }


}
