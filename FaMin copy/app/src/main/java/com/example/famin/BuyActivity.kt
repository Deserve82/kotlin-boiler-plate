package com.example.famin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.famin.Auth.LoginActivity
import com.example.famin.Auth.MyCommentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.bottom.*

class BuyActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)


        auth = FirebaseAuth.getInstance()
        var buyItems: ArrayList<CartsListModel> = intent.getParcelableArrayListExtra<CartsListModel>("buy_items")
        var totalPrice: Int = 0
        for (item in buyItems){
            totalPrice += (item.price?.toInt()?.times(item.quantity?.toInt()!!)!!)
        }

        var buylistAdapter = BuyListAdapter(this, buyItems)
        buy_list_view.adapter = buylistAdapter

        buy_info.text = "총 금액은 $totalPrice 원 입니다."

        buy_all_btn.setOnClickListener {
            finish()
            Toast.makeText(this, "구매가 완료 되었습니다.", Toast.LENGTH_LONG).show()
        }
    }
}