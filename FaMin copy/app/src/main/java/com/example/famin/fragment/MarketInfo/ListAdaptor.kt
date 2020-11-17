package com.example.famin.fragment.MarketInfo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.famin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.menu_item.view.*

class ListAdaptor(val context : Context,
                  private val menus : ArrayList<MenuListModel>
                  ) : BaseAdapter(){
    override fun getCount(): Int {
        return menus.size
    }


    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.menu_item, null)
        Picasso.get().load(menus[p0].image).into(view.menu_image)
        view.menu_price.text = menus[p0].price.toString()
        view.menu_info.text = menus[p0].info
        view.menu_title.text = menus[p0].name
        view.setOnClickListener {
            val intent = Intent(context, MenuReviewActivity::class.java)
            context.startActivity(intent)
        }
        return  view
    }


}