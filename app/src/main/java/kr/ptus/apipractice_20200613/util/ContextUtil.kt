package kr.ptus.apipractice_20200613.util

import android.content.Context

class ContextUtil {

    companion object {
        private val prefName = "APIPracticePreference"

        private val USER_TOKEN = "USER_TOKEN"
        private val AUTO_LOGIN = "AUTO_LOGIN"

        fun setAutoLogin(context: Context , autoLogin : Boolean){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply()

        }

        fun isAutoLogin(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)
        }

        fun setuserToken(context: Context, token: String) {
//            저장할때 사용할 메모장 파일을 열자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            열어준 메모장의 user_Token 항목에 받아온 token에 든 값을 저장.
            pref.edit().putString(USER_TOKEN, token).apply()
        }

        //          저장된 토큰 불러오기
        fun getUserToken(context: Context) : String {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN,"")!!


        }

    }

}