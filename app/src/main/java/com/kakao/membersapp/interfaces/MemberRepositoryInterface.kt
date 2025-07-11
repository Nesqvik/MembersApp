package com.kakao.membersapp.interfaces

import com.kakao.membersapp.model.Member

interface MemberRepositoryInterface {
    // suspend: Diese Funktion kann pausiert werden und sollte in einer Coroutine aufgerufen werden
    // sie übergibt eine Liste von Member-Objekten und gibt einen zufälligen Member zurück
    suspend fun getRandomName(membersList: List<Member>): Member
    // Diese Funktion gibt eine Liste von Member-Objekten zurück und wird verwendet, um Mitglieder-Daten zu bekommen
    // (in unserem Fall wird es nichts aus dem Internet geladen, bei uns es ist eine statische, fest kodierte Liste)
    fun fetchMembersData(): List<Member>
}