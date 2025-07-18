package com.kakao.membersapp.interfaces

import com.kakao.membersapp.MembersAppKeys
import com.kakao.membersapp.model.MemberRest
import retrofit2.Call
import retrofit2.http.GET

interface MembersInterface {

    @GET(MembersAppKeys.MEMBERS_URL)
    fun getMembers(): Call<List<MemberRest>>

}