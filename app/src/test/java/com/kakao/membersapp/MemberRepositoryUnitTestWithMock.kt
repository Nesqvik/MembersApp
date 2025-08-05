package com.kakao.membersapp

import com.kakao.membersapp.interfaces.MemberRepositoryInterface
import com.kakao.membersapp.model.Member
import com.kakao.membersapp.viewmodels.MemberViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class MemberRepositoryUnitTestWithMock {

    private lateinit var memberRepositoryInterface: MemberRepositoryInterface
    private lateinit var memberViewModel: MemberViewModel

    @Before
    fun setup() {
        memberRepositoryInterface = mock()
        memberViewModel = MemberViewModel(memberRepositoryInterface)
    }

    @Test
    fun fetchMembersData(): Unit = runTest {

        val expectedMembers = listOf(
            Member(1, "Alice", 30),
            Member(2, "Bob", 29)
        )

        whenever(memberRepositoryInterface.fetchMembersData()).thenReturn(expectedMembers)

        memberViewModel.fetchMembersData()

        assertEquals(expectedMembers, memberViewModel.membersList.value)
        verify(memberRepositoryInterface).fetchMembersData()
    }
}



/*
runBlocking

fun example() {
    runBlocking{

        delay(2000)
        printLn("Inside")
}
    println("Outside")

}


fun example2() {
    runBlocking{
        launch{
        delay(2000)
        println("1 Launch)
        }
        delay(1000)
        println("Inside runBlocking")
        }
     println("Outside runBlocking")
}

Ergebnis: Inside runBlocking
          1 Launch
          Outside runBlocking

 */















