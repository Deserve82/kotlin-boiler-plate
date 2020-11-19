package com.example.famin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.greedview_item.view.*

/*
그리드뷰 어댑
각각의 그리드 뷰아이템들을 모아서 엮어주는 페이지 입니다.
*/


class GridviewAdapter(val context: Context, val img_list: Array<Int>, val text_list: Array<String>): BaseAdapter(){
    override fun getCount(): Int {
        return img_list.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.greedview_item, null)
        view.gridview_text.text = text_list[p0]
        view.gridview_img.setImageResource(img_list[p0])
        return view
    }

}