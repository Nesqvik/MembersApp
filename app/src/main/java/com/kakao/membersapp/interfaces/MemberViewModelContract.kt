package com.kakao.membersapp.interfaces

import com.kakao.membersapp.model.Member
import kotlinx.coroutines.flow.StateFlow

interface MemberViewModelContract {
    val randomName: StateFlow<Member?>
    val membersList: StateFlow<List<Member>>
    val isRandomNameReceived: StateFlow<Boolean>
    fun clickGetRandomName(namesList: List<Member>)
    fun fetchMembersData()
}