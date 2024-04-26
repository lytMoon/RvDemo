package com.lytmoon.rvdemo.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lytmoon.rvdemo.R
import com.lytmoon.rvdemo.bean.RvData
import com.lytmoon.rvdemo.ui.MainActivity

/**
 *  author : lytMoon
 *  email : yytds@foxmail.com
 *  date: 2024/4/25 18:50
 *  version : 1.0
 *  description :
 *  saying : 这世界天才那么多，也不缺我一个
 */

class AdvancedRvAdapter : ListAdapter<RvData, ViewHolder>(object : DiffUtil.ItemCallback<RvData>() {

    override fun areItemsTheSame(oldItem: RvData, newItem: RvData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RvData, newItem: RvData): Boolean {
        return oldItem.number == newItem.number
    }

}) {

    //设置函数类型参数，接受一个int类型参数，这里我们加入要回调的参数是int
    private var mItemClick: ((Int) -> Unit?)? = null

    //activity中可以调用的方法，模拟点击事件
    //将外部activity中的具体逻辑传入，因为是函数参数，支持lambda表达式
    fun setOnItemClickListener(cl: ((Int) -> Unit?)) {
        this.mItemClick = cl
    }

    companion object {
        const val LEFT_VH_TYPE = 1
        const val RIGHT_VH_TYPE = 2
    }


    override fun getItemViewType(position: Int) =
        if (currentList[position].number % 2 == 0) RIGHT_VH_TYPE else LEFT_VH_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            LEFT_VH_TYPE -> LeftViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_rv_left, parent, false)
            )


            /**
             * 因为只制定了两种类型，所以不是left就是right
             * 等效于
             *       RIGHT_VH_TYPE -> RightViewHolder(
             *                 LayoutInflater.from(parent.context)
             *                     .inflate(R.layout.item_rv_right, parent, false)
             *             )
             */
            else -> RightViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_rv_right, parent, false)
            )
        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //拿到相应位置的数据项，进行绑定。
        val itemData = getItem(position)
        when (holder) {
            is LeftViewHolder -> holder.bind(itemData)
            is RightViewHolder -> holder.bind(itemData)
        }
    }

    inner class LeftViewHolder(itemView: View) : ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_item_left)

        fun bind(itemData: RvData) {

            textView.text = itemData.number.toString()
        }

    }

    inner class RightViewHolder(itemView: View) : ViewHolder(itemView) {
        private val textView: TextView =
            itemView.findViewById<TextView?>(R.id.tv_item_right).apply {
                setOnClickListener {
                    //每次回调参数
                    mItemClick?.invoke(adapterPosition)
                }
            }

        fun bind(itemData: RvData) {
            textView.text = itemData.number.toString()
        }
    }

}


