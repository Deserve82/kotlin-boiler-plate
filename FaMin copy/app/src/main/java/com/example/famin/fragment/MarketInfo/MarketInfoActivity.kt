package com.example.famin.fragment.MarketInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.example.famin.R
import com.example.famin.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_market_info.*

class MarketInfoActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

        lecture_text.text = intent.getStringExtra("title")

        zzim.setOnClickListener {
            FirebaseUtils.db
                .collection("zzim")
                .document(FirebaseUtils.getUid())
                .update(intent.getStringExtra("title"), true)
                .addOnSuccessListener { Toast.makeText(this, "성공", Toast.LENGTH_LONG).show() }
                .addOnFailureListener { Toast.makeText(this, "실패", Toast.LENGTH_LONG).show() }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area, ContentFragment())
            .commit()

        figure_1.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ContentFragment())
                .commit()
        }
        figure_2.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, InfoFragment())
                .commit()
        }
        figure_3.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ReviewFragment())
                .commit()
        }

    }
}