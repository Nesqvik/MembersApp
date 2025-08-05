package com.kakao.membersapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.kakao.membersapp.R
import com.kakao.membersapp.interfaces.MemberViewModelContract
import com.kakao.membersapp.model.Member
import com.kakao.membersapp.ui.MemberItem
import com.kakao.membersapp.viewmodels.MemberViewModel

@Composable
fun MembersScreen(memberViewModel: MemberViewModelContract = hiltViewModel<MemberViewModel>()) {

    // hier beobachten wir die StateFlows aus dem ViewModel (MemberViewModel)
    //by ... collectAsState() macht aus dem Flow einen beobachtbaren Compose-Wert, der UI automatisch aktualisiert
    val membersList by memberViewModel.membersList.collectAsState()
    val randomName by memberViewModel.randomName.collectAsState()
    val isRandomNameReceived by memberViewModel.isRandomNameReceived.collectAsState()

    var selectedMember by remember { mutableStateOf<Member?>(null) }


    // es wird einmal beim Start ausgeführt
    // hier rufen wie die Funktion im ViewModel auf, um eine feste MembersList aus dem Repository zu laden und im StateFlow zu speichern
    LaunchedEffect(Unit) {
        memberViewModel.fetchMembersData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 65.dp),
                contentAlignment = Alignment.Center
            ) {
                AskQuestionField(memberViewModel, membersList, randomName, isRandomNameReceived)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                MembersList(membersList, { member ->
                    selectedMember = member
                })
            }
        }
        if (selectedMember != null) {
            Dialog(onDismissRequest = { selectedMember = null }) {
                MemberInfo(name = selectedMember!!.name, age = selectedMember!!.age.toString())
            }
        }

        // wenn ein RandomName empfangen wurde, zeigen wir das Popup an (mit Random Name)
        if (isRandomNameReceived) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(R.color.teal_200),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(60.dp),
                ) {
                    Text(
                        text = "Random Name: ${randomName?.name ?: "N/A"}",
                        color = Color.Black,
                    )
                }
            }
        }
    }
}


// hier ist eine eigene Composable Funktion, z.b um Fragen zu stellen
@Composable
fun AskQuestionField(
    memberViewModel: MemberViewModelContract,
    list: List<Member>,
    randomName: Member?,
    isRandomNameReceived: Boolean
) {
    var text by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("Name") }

    if (isRandomNameReceived && randomName != null) {
        name = randomName.name // Update name
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                color = colorResource(id = R.color.light_gray_color),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("…") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { memberViewModel.clickGetRandomName(list) },
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = colorResource(R.color.teal_200),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_circle_24),
                    contentDescription = "Send",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun MembersList(list: List<Member>, onItemClick: (Member) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { member ->
            MemberItem(member = member, onMemberItemClick = {
                onItemClick(member)
            })
        }
    }
}


@Composable
fun MemberInfo(name: String, age: String) {

    Box(
        modifier = Modifier
            .width(500.dp)
            .height(200.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(12.dp) // rounded corners
            )
            .padding(16.dp), // inner padding
        contentAlignment = Alignment.Center // center the content inside the Box
    ) {
        Column {
            Text(
                text = "Member Name: $name",
                //modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Member Age: $age",
                //modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

