package kr.ptus.apipractice_20200613.data

import org.json.JSONObject

class TopicReply {

    companion object{

        fun getTopicReplyFromJson(json : JSONObject)  : TopicReply {

            val  tr = TopicReply()

            tr.id = json.getInt("id")
            tr.content = json.getString("content")
            tr.topicId = json.getInt("topic_id")
            tr.sideId = json.getInt("side_id")
            tr.userId = json.getInt("user_id")



            return tr


        }

    }


    var id = 0
    var content = ""
    var topicId = 0
    var sideId = 0
    var userId = 0

    lateinit var user : User


}