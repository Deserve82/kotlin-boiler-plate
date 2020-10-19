package com.example.famin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.famin.Auth.LoginActivity
import com.example.famin.Auth.MyCommentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val img = arrayOf(
            R.drawable.chicken1,
            R.drawable.chicken2,
            R.drawable.chicken1,
            R.drawable.chicken2,
            R.drawable.chicken1,
            R.drawable.chicken2,
            R.drawable.chicken1,
            R.drawable.chicken1,
            R.drawable.chicken1,
            R.drawable.chicken1,
            R.drawable.chicken1,
            R.drawable.chicken1
            )
        val text = arrayOf(
            "chicken1",
            "chicken2",
            "chicken1",
            "chicken2",
            "chicken1",
            "chicken2",
            "chicken1",
            "chicken2",
            "chicken1",
            "chicken1",
            "chicken1",
            "chicken1",
            "chicken1"
        )

        val gridviewAdapter = GridviewAdapter(this, img, text)
        gridview.adapter =gridviewAdapter

        gridview.setOnItemClickListener { adapterView, view, i, l ->
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