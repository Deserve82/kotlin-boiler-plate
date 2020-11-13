package com.example.famin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.famin.fragment.ListFragment.FragmentAdaptor
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_lecture.*
import kotlinx.android.synthetic.main.custom_tab.view.*

class LectureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture)

        val fragmentAdapter = FragmentAdaptor(supportFragmentManager)
        list_viewpager.adapter = fragmentAdapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("치킨")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("도넛")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("아이스크림")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("커피")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("햄버거")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("피자")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("샐러드")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("와플")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }

    private fun createTabView(tabName: String) : View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)

        tabView.txt_name.text = tabName

        return tabView
    }
}