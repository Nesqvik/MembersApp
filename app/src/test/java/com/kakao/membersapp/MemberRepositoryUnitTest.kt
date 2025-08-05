package com.kakao.membersapp

import com.kakao.membersapp.model.Member
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*


class MemberRepositoryUnitTest {

    private val memberRepository = TestMemberRepository()

    @Test
    fun testGetRandomName_withNonEmptyList() = runBlocking {
        val members = listOf(
            Member(1, "Alice", 30),
            Member(2, "Bob", 29)
        )

        val randomMember = memberRepository.getRandomName(members)
        assertTrue(members.contains(randomMember))
    }

    @Test
    fun testGetRandom_withEmptyList() = runBlocking {
        val emptyList = emptyList<Member>()

        val randomMember = memberRepository.getRandomName(emptyList)

        assertEquals(-1, randomMember.id)
        assertEquals("", randomMember.name)
        assertEquals(0, randomMember.age)
    }

}

class TestMemberRepository {

    fun getRandomName(membersList: List<Member>): Member {
        return if(membersList.isNotEmpty()) {
            membersList.random()
        }
        else {
            Member(-1, "", 0)
        }
    }
}


