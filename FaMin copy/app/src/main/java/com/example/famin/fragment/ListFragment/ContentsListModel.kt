package com.example.famin.fragment.ListFragment
/*
가게에 대한 정보가 담겨있는 모델입니다.
*/

data class ContentsListModel(
    var image : Int,
    var title : String,
    var number : Int,
    var info : String
)