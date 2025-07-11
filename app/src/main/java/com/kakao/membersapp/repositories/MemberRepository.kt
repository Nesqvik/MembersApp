package com.kakao.membersapp.repositories

import android.content.Context
import com.kakao.membersapp.interfaces.MemberRepositoryInterface
import com.kakao.membersapp.model.Member
import javax.inject.Inject


class MemberRepository @Inject constructor(
    context: Context
) : MemberRepositoryInterface {

    override suspend fun getRandomName(membersList: List<Member>): Member {
            // Wenn die Liste nicht leer ist, wählen wir ein zufälliges Element
            return if (membersList.isNotEmpty()) {
                membersList.random()
            } else {
                // Wenn Liste leer, gib ein Dummy-Objekt zurück (mit ID -1 und leerem Namen)
                Member(-1, "", 0)
            }


        }

    override fun fetchMembersData(): List<Member> {
        return listOf(
            Member(1, "Alice", 30),
            Member(2, "Bob", 20),
            Member(3, "Charlie", 18),
            Member(4, "Diana", 65),
            Member(5, "Eve", 34)
        )
    }
}
