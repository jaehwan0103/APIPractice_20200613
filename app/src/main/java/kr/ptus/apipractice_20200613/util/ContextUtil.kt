package kr.ptus.apipractice_20200613.util

import android.content.Context

class ContextUtil {

    companion object{
        val prefName = "APIPracticePreference"

        val USER_TOKEN = "USER_TOKEN"

        fun setuserToken(context : Context, token :String){
//            저장할때 사용할 메모장 파일을 열자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            열어준 메모장의 user_Token 항목에 받아온 token에 든 값을 저장.
            pref.edit().putString(USER_TOKEN,token).apply()
        }

    }

}