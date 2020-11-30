package com.example.famin.fragment.ListFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/*
탭을 눌렀을 때에 어떤 프래그먼트를 보여줄지 결정하는 어댑터 입니다.
*/

class FragmentAdaptor(fm : FragmentManager, var tabCount: Int) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            3 -> FourthFragment()
            4 -> FifthFragment()
            5 -> SixthFragment()
            6 -> SeventhFragment()
            else -> {
                return EighthFragment()
            }
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}