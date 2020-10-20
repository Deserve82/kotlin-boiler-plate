package com.example.famin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.famin.fragment.ListFragment.ContentsListModel

class ListviewAdapter(val context: Context, val list: ArrayList<ContentsListModel>): BaseAdapter(){
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, null)
            holder = ViewHolder()

            holder.lv_image_area = view.findViewById(R.id.lv_image_area)
            holder.product_title = view.findViewById(R.id.product_title)
            holder.product_price = view.findViewById(R.id.product_price)
            holder.product_text = view.findViewById(R.id.product_text)

            view.tag = holder
        }
        else{
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]
        holder.lv_image_area?.setImageResource(item.image)
        holder.product_title?.text = item.title
        holder.product_price?.text = item.price.toString() + " 원"
        holder.product_text?.text = item.text
        return view
    }

    private class ViewHolder {
        var lv_image_area : ImageView? = null
        var product_title : TextView? = null
        var product_price : TextView? = null
        var product_text : TextView? = null
    }

}