package com.kakao.membersapp.repositories

import com.kakao.membersapp.model.Member

class MemberRepository {

    fun fetchMembers(): List<Member> {
        return listOf(
            Member(1, "Alice", 30),
            Member(2, "Bob", 20),
            Member(3, "Charlie", 18),
            Member(4, "Diana", 65),
            Member(5, "Eve", 34)
        )
    }
}
