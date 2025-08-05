package com.kakao.membersapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.kakao.membersapp.interfaces.MemberViewModelContract
import com.kakao.membersapp.model.Member
import com.kakao.membersapp.ui.screens.MembersScreen
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MemberScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var fakeMemberViewModel: FakeMemberViewModel

    @Before
    fun setup() {
        fakeMemberViewModel = FakeMemberViewModel()
    }

    @Test
    fun testRandomNameDisplayedWhenReceived() {
        composeTestRule.setContent {
            MembersScreen(memberViewModel = fakeMemberViewModel)
        }
        composeTestRule
            .onNodeWithText("Random Name: Jack")
            .assertExists()
    }

    @Test
    fun testFetchMembersData() {

    }
}


class FakeMemberViewModel: MemberViewModelContract {
    override val randomName = MutableStateFlow(Member(name = "Jack", age = 29))
    override val membersList = MutableStateFlow(listOf<Member>())
    override val isRandomNameReceived = MutableStateFlow(true)

    override fun clickGetRandomName(namesList: List<Member>) {
    }

    override fun fetchMembersData() {
        /*membersList.value = listOf(
            Member(name = "Fake Alice", age = 30),
            Member(name = "Fake Bob", age = 29)
        )*/
    }

}