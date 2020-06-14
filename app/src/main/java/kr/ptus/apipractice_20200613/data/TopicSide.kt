package kr.ptus.apipractice_20200613.data

import org.json.JSONObject

class TopicSide {

    companion object {

        fun getTopicSideFromJson(json: JSONObject): TopicSide {

            val ts = TopicSide()

            ts.id = json.getInt("id")
            ts.topicId = json.getInt("topic_id")
            ts.title = json.getString("title")
            ts.votecount = json.getInt("vote_count")

            return ts
        }

    }

    var id = 0
    var topicId = 0
    var title = ""
    var votecount = 0
}

