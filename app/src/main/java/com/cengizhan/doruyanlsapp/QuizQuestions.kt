package com.cengizhan.doruyanlsapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import org.w3c.dom.Text

class QuizQuestions : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition:Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()

        setQuestion()

        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)

        btnNext.setOnClickListener(this)


    }
    private fun setQuestion(){

        val question: Question? = mQuestionList!![mCurrentPosition-1]

        defaultoptionsView()

        if(mCurrentPosition == mQuestionList!!.size){
            btnNext.text = "FİNİSH"
        }else{
            btnNext.text = "SUBMİT"
            isWorking=false
            startTimeCounter()
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition"+"/" + progressBar.max

        tv_question.text = question!!.question
        tv_optionOne.text = question!!.optionOne
        tv_optionTwo.text = question!!.optionTwo
    }
    private fun defaultoptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_optionOne)
        options.add(1,tv_optionTwo)

        for(option in options){
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_optionOne ->{
                selectedOptionView(tv_optionOne,1)
            }
            R.id.tv_optionTwo ->{
                selectedOptionView(tv_optionTwo,2)
            }
            R.id.btnNext ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{
                            val intent = Intent(this,Result::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRCT_ANSWER, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTİONS, mQuestionList!!.size)
                            startActivity(intent)
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if(question!!.correctAns != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAns, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btnNext.text = "FİNİSH"
                    }else{
                        btnNext.text = "GO TO NEXT QUESTİON"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                tv_optionOne.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2 ->{
                tv_optionTwo.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultoptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    var isWorking = false


     fun startTimeCounter() {

        if (isWorking == false){
            isWorking = true
            var counter = 10
            val countTime: TextView = findViewById(R.id.countTime)

             object : CountDownTimer(50000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    countTime.text = counter.toString()
                    counter--
                    if(counter < 0){
                        counter = 0
                    }
                }
                override fun onFinish() {
                    countTime.text = "Finished"
                    counter = 10
                    isWorking = false
                }
            }.start()
        }
    }
}