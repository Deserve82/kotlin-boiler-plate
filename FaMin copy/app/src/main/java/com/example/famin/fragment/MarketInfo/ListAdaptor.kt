package com.example.famin.fragment.MarketInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.famin.R

class ListAdaptor(val context : Context,
                  private val menuImage : ArrayList<String>,
                  val menuTitle : ArrayList<String>,
                  val menuInfo : ArrayList<String>,
                  val menuPrice : ArrayList<Int>) : BaseAdapter(){
    override fun getCount(): Int {
        return menuImage.size
    }


    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.menu_item, null)

        return  view
    }


}