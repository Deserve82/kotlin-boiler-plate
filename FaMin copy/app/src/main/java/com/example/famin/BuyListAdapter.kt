package com.example.famin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class BuyListAdapter(val context: Context, val buy_list: ArrayList<CartsListModel>): BaseAdapter(){
    override fun getCount(): Int {
        return buy_list.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.buylist_item, null)
        val holder : ViewHolder = ViewHolder()

        holder.buy_item_img = view.findViewById(R.id.buy_item_img)
        holder.buy_item_name = view.findViewById(R.id.buy_item_name)
        holder.buy_item_price = view.findViewById(R.id.buy_item_price)
        holder.buy_item_quantity = view.findViewById(R.id.buy_item_quantity)

        view.tag = holder

        val item = buy_list[position]
        holder.buy_item_img?.setImageResource(item.image!!.toInt())
        holder.buy_item_name?.text = item.title
        holder.buy_item_quantity?.text = item.quantity + " 개"
        holder.buy_item_price?.text = "총 " + (item.price!!.toInt() * item.quantity!!.toInt()).toString() + " 원"
        return view
    }
    class ViewHolder {
        var buy_item_img : ImageView? = null
        var buy_item_name : TextView? = null
        var buy_item_quantity : TextView? = null
        var buy_item_price : TextView? = null
    }
}