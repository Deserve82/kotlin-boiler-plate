package com.example.famin.fragment.MarketInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.famin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.menu_item.view.*
import kotlinx.android.synthetic.main.review_item.view.*

/*
뷰페이저 페이지
이미지 리스트에서 홈에서 프로모션 관련 페이지를 보여준다.
*/

class ReviewListAdaptor(val context: Context,
                        val list_nickname : ArrayList<String>,
                        val list_text : ArrayList<String>, val list_rating : ArrayList<String>,
                        val profileImageArray : ArrayList<String>
                        ) : BaseAdapter()
{
    override fun getCount(): Int {
        return list_nickname.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.review_item, null)
        view.review_nickname.text = list_nickname[p0]
        view.review_content.text = list_text[p0]
        view.review_rating.text = list_rating[p0]
        Picasso.get().load(profileImageArray[p0]).into(view.profile_image)
        return view
    }

}