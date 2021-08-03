package com.cengizhan.doruyanlsapp

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTİONS: String = "total_question"
    const val CORRCT_ANSWER: String = "correct_answer"


    fun getQuestions(): ArrayList<Question>{
        val quesitonlist = ArrayList<Question>()

        val que1 = Question(1,"Kükürt oda koşullarında katıdır ?","Doğru","Yanlış",1)

        quesitonlist.add(que1)

        val que2 = Question(2,"Kaşolat bir balina cinsidir ?","Doğru","Yanlış",1)

        quesitonlist.add(que2)

        val que3 = Question(3,"Titreşimin yaptığı yayılma hareketine dalga adı verilir ?","Doğru","Yanlış",1)

        quesitonlist.add(que3)

        val que4 = Question(4,"Şilep bir hava taşıtıdır ?","Doğru","Yanlış",2)

        quesitonlist.add(que4)

        val que5 = Question(5,"İstiklal marşının yazarı Mustafa Kemal Atatürk'tür ?","Doğru","Yanlış",2)

        quesitonlist.add(que5)
        return quesitonlist
    }
}