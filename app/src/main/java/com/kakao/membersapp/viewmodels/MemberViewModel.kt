package com.kakao.membersapp.viewmodels

import androidx.lifecycle.ViewModel
import com.kakao.membersapp.model.Member
import com.kakao.membersapp.repositories.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberRepository: MemberRepository
    ) : ViewModel() {

    private val _membersList = MutableStateFlow<List<Member>>(emptyList())
    val membersList: StateFlow<List<Member>> = _membersList

    fun fetchMembers() {
        _membersList.value = memberRepository.fetchMembers()
    }

}


