package kr.ptus.apipractice_20200613.data

import org.json.JSONObject

class TopicReply {

    companion object{

        fun getTopicReplyFromJson(json : JSONObject)  : TopicReply {




        }

    }


    var id = 0
    var content = ""
    var topicId = 0
    var sideId = 0
    var userId = 0

    lateinit var user : User


}