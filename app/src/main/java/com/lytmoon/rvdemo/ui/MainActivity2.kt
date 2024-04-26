package com.lytmoon.rvdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lytmoon.rvdemo.adapter.TestRvAdapter
import com.lytmoon.rvdemo.bean.RvData
import com.lytmoon.rvdemo.databinding.ActivityMain2Binding
import com.lytmoon.rvdemo.viewmodel.MainViewModel

class MainActivity2 : AppCompatActivity() {

    //懒加载获取viewBinding对象
    private val mBinding: ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }

    //懒加载生成对象
    private val testRvAdapter: TestRvAdapter by lazy { TestRvAdapter() }

    //懒加载通过ViewModelProvider反射生成MainViewModel，优先从缓存中拿，拿不到就生成同时放入缓存，在activity重建的时候缓存也会重建，这也就是view model的生命周期为什么比较长的原因
    private val mViewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        iniListener()
        iniRv()
    }


    private fun iniRv() {


        mBinding.rvMain2.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
            adapter = testRvAdapter
        }
        mViewModel.rvList.observe(this@MainActivity2) {
            val mList = (it as MutableList<RvData>).toMutableList()
            testRvAdapter.submitList(mList)


        }


    }

    private fun iniListener() {
        mBinding.rvMain2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //Rv滑动到底部会回调这里。
                if (!recyclerView.canScrollVertically(1)) {
                    /**
                     * 调用ViewModel中的方法，更新liveData中的数据，通过观察者模式自动触发liveData中的observe回调，也就是
                     *   mViewModel.rvList.observe(this@MainActivity2) {
                     *             testRvAdapter.submitList(it)
                     *         }
                     *         ListAdapter通过差分刷新来更新我们的数据，实现自动刷新
                     */

                    //向liveData中再添加5个数字的方法
                    mViewModel.addNum()

                }
            }
        })

    }
}