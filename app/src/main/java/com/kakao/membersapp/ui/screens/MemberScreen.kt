package com.kakao.membersapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.kakao.membersapp.R
import com.kakao.membersapp.model.Member
import com.kakao.membersapp.ui.MemberItem
import com.kakao.membersapp.viewmodels.MemberViewModel

@Composable
fun MembersScreen(memberViewModel: MemberViewModel = hiltViewModel()) {

    // hier beobachten wir die StateFlows aus dem ViewModel (MemberViewModel)
    //by ... collectAsState() macht aus dem Flow einen beobachtbaren Compose-Wert, der UI automatisch aktualisiert
    val membersList by memberViewModel.membersList.collectAsState()

    // es wird einmal beim Start ausgeführt
    // hier rufen wie die Funktion im ViewModel auf, um eine feste MembersList aus dem Repository zu laden und im StateFlow zu speichern
    LaunchedEffect(Unit) {
        memberViewModel.fetchMembers()
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
                AskQuestionField()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                MembersList(membersList)
            }
        }
    }
}

// hier ist eine eigene Composable Funktion, z.b um Fragen zu stellen
@Composable
fun AskQuestionField() {

    var text by remember { mutableStateOf("") }

    Box {
        TextField(
            value = text,
            onValueChange = { text = it },
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
    }
    IconButton(
        onClick = { },
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

@Composable
fun MembersList(list: List<Member>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { member ->
            MemberItem(member = member)
        }
    }
}




