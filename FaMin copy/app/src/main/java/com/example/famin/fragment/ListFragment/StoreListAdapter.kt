package com.example.famin.fragment.ListFragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.famin.R
import com.example.famin.utils.FirebaseUtils
import kotlinx.android.synthetic.main.listview_item.view.*

/*
가게 리스트를 뿌려주는 어댑터 - 가게 리스트 뷰를 holder에 담아 뿌려주는 역할을 합니다.
*/

class StoreListAdapter(val context : Context, val list : ArrayList<ContentsListModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder
        var reviewCount : Int = 0
        var menuCount: Int = 0
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, null)

            holder = ViewHolder()

            holder.view_image1 = view.findViewById(R.id.lv_image_area)
            holder.title = view.findViewById(R.id.title)
            holder.menu_count = view.findViewById(R.id.store_menu_count)
            holder.review_count = view.findViewById(R.id.store_review_count)

            view.tag = holder
        }
        else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]
        holder.view_image1?.setImageResource(item.image)
        holder.title?.text = item.title
        FirebaseUtils.db
            .collection("menu")
            .whereEqualTo("store", item.title)
            .get()
            .addOnSuccessListener {
                results ->
                menuCount = results.size()
                holder.menu_count?.text = "$menuCount 개의 메뉴가 있습니다."
            }
            .addOnFailureListener {
                e ->
                Log.w("TAG", e)
            }
        FirebaseUtils.db
            .collection("reviews")
            .whereEqualTo("store", item.title)
            .get()
            .addOnSuccessListener {
                results ->
                reviewCount = results.size()
                holder.review_count?.text = "$reviewCount 개의 리뷰가 있습니다."
            }
            .addOnFailureListener {
                    e ->
                Log.w("TAG", e)
            }
        return view
    }

    private class ViewHolder{
        var view_image1 : ImageView? = null
        var title : TextView? = null
        var menu_count : TextView? = null
        var review_count : TextView? = null

    }
}