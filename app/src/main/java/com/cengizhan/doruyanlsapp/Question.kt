package com.cengizhan.doruyanlsapp

data class Question (
    val id: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val correctAns: Int
)