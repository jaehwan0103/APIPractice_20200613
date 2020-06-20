package kr.ptus.apipractice_20200613.data

import org.json.JSONObject

class User {

    var id = 0
    var email = ""
    var nickName = ""

    companion object{

        fun getuserFromJson (json : JSONObject) : User {

            val u = User()

            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickName = json.getString("nick_name")



            return u

        }

    }

}