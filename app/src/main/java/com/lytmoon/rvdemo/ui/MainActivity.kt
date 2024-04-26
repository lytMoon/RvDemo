package com.lytmoon.rvdemo.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lytmoon.rvdemo.adapter.SimpleRvAdapter
import com.lytmoon.rvdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val _rvList = mutableListOf<Int>()
    val rvList: List<Int>
        get() = _rvList

    //懒加载获取viewBinding对象
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mAdapter: SimpleRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //这里一定要返回mBinding.root，如果不改变的话会发现mBinding没有效果
        setContentView(mBinding.root)
        initData()
        initRv()
        initClick()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initClick() {
        //前面添加到了20，所以这里从21开始
        var num = 21
        mBinding.btMainSum.setOnClickListener {
            _rvList.add(num++)
            mAdapter.notifyDataSetChanged()
        }


        mBinding.btMainIntent.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
        }
        mBinding.btMainIntentAct3.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity3::class.java))
        }

    }

    //本地mock数据
    @SuppressLint("NotifyDataSetChanged")
    private fun initData() {
        // 使用for循环添加1到20
        for (i in 1..20) {
            _rvList.add(i)
        }

    }

    /**
     * 初始化Rv
     */
    private fun initRv() {
        //把数据集合传入Adapter里面。
        mAdapter = SimpleRvAdapter(rvList)
        mBinding.rvMain.apply {
            /**
             * LinearLayoutManager构造函数里面默认不指定就是垂直布局
             * 当然你也可以这么写
             * layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
             *第三个参数为是否反向布局，默认不指定就是false。
             */
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

    }

}
