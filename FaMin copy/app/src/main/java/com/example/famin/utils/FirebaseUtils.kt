package com.example.famin.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/*
파이어 베이스를 조금 더 쉽고 간결하게 사용하기 위해 만든 유틸 페이지 입니다.
*/


class FirebaseUtils{
    companion object {
        private var auth = FirebaseAuth.getInstance()
        var db = FirebaseFirestore.getInstance()

        fun getUid() : String {
            return auth.currentUser?.uid.toString()
        }
    }
}