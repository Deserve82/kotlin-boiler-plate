package com.example.famin.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_join.*

/*
회원가입을 진행하는 액티비티입니다. 이메일과 비밀번호만 입력하게 됩니다.
*/


class JoinActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = FirebaseAuth.getInstance()

        join_login_button.setOnClickListener{
            auth.createUserWithEmailAndPassword(join_email_area.text.toString(), join_password_area.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this, JoinInfoActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}