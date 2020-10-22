package com.example.famin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.famin.ContentsListModel
import com.example.famin.utils.FirebaseUtils

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
        var qu : Int = 0;
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
        holder.decrease_btn = view.findViewById(R.id.decrease_btn)
        holder.increase_btn = view.findViewById(R.id.increase_btn)
        holder.quantity = view.findViewById(R.id.quantity)
        holder.buy_btn = view.findViewById(R.id.buy_btn)
        holder.add_cart_btn = view.findViewById(R.id.add_cart_btn)

        holder.add_cart_btn?.setOnClickListener{
            FirebaseUtils.db
                .collection("cart")
                .document(item.title)
                .set(CartsListModel(image =item.image.toString(), title =item.title, price =item.price.toString(), quantity =qu.toString()))
                .addOnSuccessListener { Toast.makeText(context, "성공", Toast.LENGTH_LONG).show() }
                .addOnFailureListener { Toast.makeText(context, "실패", Toast.LENGTH_LONG).show() }
        }

        holder.buy_btn?.setOnClickListener {
            var buy_item = ArrayList<CartsListModel>()
            var a  = CartsListModel(item.image.toString(), item.title, item.price.toString(), qu.toString())
            buy_item.add(a)
            val intent = Intent(context, BuyActivity::class.java)
            intent.putExtra("buy_items", buy_item)
            context.startActivity(intent)
        }

        holder.decrease_btn?.setOnClickListener{
            if (qu > 0){
                qu -= 1
                holder.quantity?.text = qu.toString()
            }
        }

        holder.increase_btn?.setOnClickListener{
                qu += 1
                holder.quantity?.text = qu.toString()
        }


        return view
    }

    private class ViewHolder {
        var lv_image_area : ImageView? = null
        var product_title : TextView? = null
        var product_price : TextView? = null
        var product_text : TextView? = null
        var decrease_btn : Button? = null
        var increase_btn : Button? = null
        var quantity : TextView? = null
        var buy_btn : Button? = null
        var add_cart_btn : Button? = null
    }

}