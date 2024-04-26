package com.lytmoon.rvdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lytmoon.rvdemo.bean.RvData

/**
 *  author : lytMoon
 *  email : yytds@foxmail.com
 *  date: 2024/4/25 14:37
 *  version : 1.0
 *  description :
 *  saying : 这世界天才那么多，也不缺我一个
 */
class MainViewModel : ViewModel() {

    private val mutableList = mutableListOf<RvData>()


    private val _rvList: MutableLiveData<List<RvData>> = MutableLiveData<List<RvData>>()
    val rvList: LiveData<List<RvData>>
        get() = _rvList


    private var num = 21

    init {
        initList()
    }

    /**
     * 初始化Rv的列表集合，初始化20个数据。
     */
    private fun initList() {
        for (i in 1..20) {
            mutableList.add((RvData((i))))
        }
        _rvList.postValue(mutableList)
    }


    /**
     * 当每次Rv滑动到底部的时候调用该方法，每次都在下面添加5个数据项目
     */

    fun addNum() {
        for (i in 1..5) {
            mutableList.add(RvData(num))
            num++
        }
        Log.d("LogTest", "测试数据${mutableList}")

        _rvList.postValue(mutableList)
        Log.d("wefweafweafwefweaf", "测试数据${System.identityHashCode(mutableList)}")


    }

}