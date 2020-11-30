package com.example.famin.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.famin.MainActivity
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_join_info.*

/*
정보를 기입하는 액티비티 입니다. 닉네임만 넣을 수 있고 이미지는 아직 기본이미지만 삽입이 가능합니다.
*/

class JoinInfoActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_info)

        auth = FirebaseAuth.getInstance()

        join_info_login_button.setOnClickListener {
            val user = hashMapOf(
                "nickname" to join_info_email_area.text.toString(),
                // 기본 이미지 설정
                "user_image" to "https://firebasestorage.googleapis.com/v0/b/famin-7a69f.appspot.com/o/test.png?alt=media&token=676f20e9-4d06-4717-9141-92fb2b302423"
            )

            db.collection("users").document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener { Log.e("JoinInfoActivity", "성공")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { Log.e("JoinInfoActivity", "실패") }
        }
    }
}