package com.lytmoon.rvdemo.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lytmoon.rvdemo.adapter.AdvancedRvAdapter
import com.lytmoon.rvdemo.bean.RvData
import com.lytmoon.rvdemo.databinding.ActivityMain3Binding
import com.lytmoon.rvdemo.viewmodel.MainViewModel

class MainActivity3 : AppCompatActivity() {

    private val mBinding: ActivityMain3Binding by lazy {
        ActivityMain3Binding.inflate(layoutInflater)
    }

    private val advancedRvAdapter: AdvancedRvAdapter by lazy { AdvancedRvAdapter() }

    private val mViewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        iniRv()
        iniListener()

    }


    private fun iniRv() {
        mBinding.rvMain3.apply {
            layoutManager = LinearLayoutManager(this@MainActivity3)
            adapter = advancedRvAdapter.apply {
                setOnItemClickListener {
                    Log.d("498464468", "测试数据${it}")
                    upData(it)

                }
            }
        }
        mViewModel.rvList.observe(this@MainActivity3) {
            val mList = (it as MutableList<RvData>).toMutableList()
            advancedRvAdapter.submitList(mList)
        }
    }

    private fun upData(it: Int) {

    }

    private fun iniListener() {
        mBinding.rvMain3.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    mViewModel.addNum()
                }
            }
        })
    }
}