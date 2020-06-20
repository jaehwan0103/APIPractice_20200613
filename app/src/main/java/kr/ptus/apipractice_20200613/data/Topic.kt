package kr.ptus.apipractice_20200613.data

import org.json.JSONObject

class Topic {


    companion object {

        fun getTopicFromJson(json: JSONObject): Topic {

            val topic = Topic()

            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")

            val sides = json.getJSONArray("sides")

            for (i in 0..sides.length() - 1) {
                val sideJson = sides.getJSONObject(i)

                val side = TopicSide.getTopicSideFromJson(sideJson)

                topic.sides.add(side)

            }

            val replies = json.getJSONArray("replies")

            for (i in 0..replies.length() - 1) {
                val repltJson = replies.getJSONObject(i)
                val reply = TopicReply.getTopicReplyFromJson(repltJson)
                topic.replies.add(reply)
            }

            topic.mySideId = json.getInt("my_side_id")

            if (!json.isNull("my_side")) {
                topic.mySelectedSide = TopicSide.getTopicSideFromJson(json.getJSONObject("my_side"))
            }
            return topic
        }

    }

    var id = 0
    var title = ""
    var imageUrl = ""
    val sides = ArrayList<TopicSide>()

    val replies = ArrayList<TopicReply>()


    var mySideId = -1

    var mySelectedSide: TopicSide? = null

}