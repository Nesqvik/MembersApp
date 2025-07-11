package com.kakao.membersapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.kakao.membersapp.R
import com.kakao.membersapp.model.Member


@Composable
fun MemberItem(member: Member, onMemberItemClick: (Member) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  onMemberItemClick(member)}
            .padding(horizontal = 16.dp, vertical = 8.dp) // space between items
            .background(
                color = colorResource(id = R.color.member_item_back_color),
                shape = RoundedCornerShape(12.dp) // rounded corners
            )
            .padding(16.dp) // inner padding
    ) {
        Text(
            text = member.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}



