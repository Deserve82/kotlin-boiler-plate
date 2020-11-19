package com.example.famin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
/*
뷰페이저 페이지
이미지 리스트에서 홈에서 프로모션 관련 페이지를 보여준다.
*/


class ViewPagerAdaptor(private val context : Context) : PagerAdapter(){

    private var layoutInflater : LayoutInflater? = null

    val Image = arrayOf(
        R.drawable.mac_new_release,
        R.drawable.papa_new_release
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return Image.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.viewpager_activity, null)
        val image = v.findViewById<View>(R.id.imageview) as ImageView

        image.setImageResource(Image[position])

        val vp = container as ViewPager
        vp.addView(v, 0)

        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}