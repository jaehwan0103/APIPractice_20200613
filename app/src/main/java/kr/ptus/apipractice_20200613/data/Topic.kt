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

            return topic
        }

    }

    var id = 0
    var title = ""
    var imageUrl = ""
    val sides = ArrayList<TopicSide>()

}