package com.kakao.membersapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kakao.membersapp.MembersAppKeys

@Entity
data class MemberRest(
    //@PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey val id: Int,

    @SerializedName(MembersAppKeys.MEMBERS_NAME)
    var memberName: String = "",

    @SerializedName(MembersAppKeys.MEMBERS_AGE)
    var memberAge: Int = 0
)