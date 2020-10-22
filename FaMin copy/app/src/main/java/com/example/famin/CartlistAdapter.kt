package com.example.famin

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi

class CartlistAdapter(val context: Context, val cart_list: ArrayList<CartsListModel>): BaseAdapter(){

    var checkedCartItems : ArrayList<CartsListModel> = ArrayList<CartsListModel>()

    override fun getCount(): Int {
        return cart_list.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun getChcekdItems() : ArrayList<CartsListModel> {
        return checkedCartItems
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.cartlist_item, null)
        val holder : ViewHolder = ViewHolder()

        holder.cart_item_img = view.findViewById(R.id.cart_item_img)
        holder.cart_item_name = view.findViewById(R.id.cart_item_name)
        holder.cart_item_quantity = view.findViewById(R.id.cart_item_quantity)
        holder.cart_item_price = view.findViewById(R.id.cart_item_price)
        holder.cart_selected = view.findViewById(R.id.cart_selected)

        view.tag = holder

        val item = cart_list[position]
        holder.cart_item_img?.setImageResource(item.image!!.toInt())
        holder.cart_item_name?.text = item.title
        holder.cart_item_quantity?.text = item.quantity.toString() + " 개"
        holder.cart_item_price?.text = "총 " + (item.price!!.toInt() * item.quantity!!.toInt()).toString() + " 원"

        holder.cart_selected?.setOnClickListener {
            holder.is_selected = holder.cart_selected!!.isChecked
            if (holder.is_selected) {
                val c = CartsListModel(item.image, item.title, item.quantity, item.price)
                checkedCartItems.add(c)
            }
            else{
                checkedCartItems.removeIf{cart: CartsListModel -> cart.title == item.title}
            }
        }

        return view
    }
    class ViewHolder {
        var cart_item_img : ImageView? = null
        var cart_item_name : TextView? = null
        var cart_item_quantity : TextView? = null
        var cart_item_price : TextView? = null
        var cart_selected : CheckBox? = null
        var is_selected : Boolean = false
    }

}