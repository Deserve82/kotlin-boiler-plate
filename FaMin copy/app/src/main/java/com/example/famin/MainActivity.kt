package com.example.famin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.famin.Auth.LoginActivity
import com.example.famin.Auth.MyCommentActivity
import com.example.famin.fragment.ListFragment.ContentsListModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewpager : ViewPager


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val list_array = arrayListOf<ContentsListModel>(
            ContentsListModel(R.drawable.chicken1, "후라이드 치킨", 17000, "빠삭한 후리이드 치킨!"),
            ContentsListModel(R.drawable.chicken2, "간장 치킨", 18000, "간장에 맛있게 졸인 치킨!")
            )

        val listviewAdater = ListviewAdapter(this, list_array)
        listview.adapter =listviewAdater

        listview.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, LectureActivity::class.java)
            startActivity(intent)
        }

        viewpager = findViewById(R.id.viewpager) as ViewPager

        val adaptor = ViewPagerAdaptor(this)
        viewpager.adapter = adaptor

        my_page.setOnClickListener {

            if(auth.currentUser == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this, MyCommentActivity::class.java)
                startActivity(intent)
            }
        }
    }
}