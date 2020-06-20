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

            val userJson = json.getJSONObject("user")
            tr.user = User.getuserFromJson(userJson)

            tr.selectedSide = TopicSide.getTopicSideFromJson(json.getJSONObject("selected_side"))


            tr.replyCount = json.getInt("reply_count")
            tr.likeCount = json.getInt("like_count")
            tr.disLikeCount = json.getInt("dislike_count")


            tr.isMyLike = json.getBoolean("my_like")
            tr.isMydisLike = json.getBoolean("my_dislike")




            return tr


        }

    }


    var id = 0
    var content = ""
    var topicId = 0
    var sideId = 0
    var userId = 0

    lateinit var user : User

    lateinit var selectedSide : TopicSide

    var replyCount = 0
    var likeCount = 0
    var disLikeCount = 0

    var isMyLike = false
    var isMydisLike = false


}