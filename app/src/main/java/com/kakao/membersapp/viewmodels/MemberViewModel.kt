package com.kakao.membersapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.membersapp.interfaces.MemberRepositoryInterface
import com.kakao.membersapp.model.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemberViewModel @Inject constructor(
    // hier übergeben wir automatisch (durch Hilt) eine Instanz von MemberRepository (mithilfe von @Inject)
    // private val memberRepository: MemberRepository,
    private val memberRepositoryInterface: MemberRepositoryInterface,

    ) : ViewModel() {
    // hier erstellen wir ein veränderbares StateFlow-Objekt für einen zufälligen Member NAME (am Anfang is null)
    private val _randomName = MutableStateFlow<Member?>(null)

    // hier bekommen wir ein öffentlich lesbares StateFlow, das nur beobachtet werden kann (nicht veränderbar von außen)
    val randomName: StateFlow<Member?> = _randomName

    private val _membersList = MutableStateFlow<List<Member>>(emptyList())
    val membersList: StateFlow<List<Member>> = _membersList

    private val _isRandomNameReceived = MutableStateFlow(false)
    val isRandomNameReceived: StateFlow<Boolean> = _isRandomNameReceived


    // hier haben wir eine private Funktion, um einen zufälligen Member NAMEN aus einer übergebenen Liste zu holen
    private suspend fun getRandomName(namesList: List<Member>) {
        // hier holen wir einen zufälligen Member NAMEN aus dem Repository und speichert ihn im StateFlow
        _randomName.value = memberRepositoryInterface.getRandomName(namesList)
        // hier setzen wir den Status auf true, um anzuzeigen, dass ein Member NAME empfangen wurde
        _isRandomNameReceived.value = true
    }

    // hier haben wir eine öffentliche Funktion, die von der UI aufgerufen werden kann, um einen zufälligen Members NAMEN zu bekommen
    fun clickGetRandomName(namesList: List<Member>) {
        // es startet eine Coroutine im ViewModelScope (Lebensdauer wie ViewModel)
        viewModelScope.launch {
            // es ruft die suspend Funktion zum Holen eines zufälligen Namens auf
            getRandomName(namesList)
        }
    }

    // hier haben wir eine öffentliche Funktion, um die Member-Daten zu holen (z.b beim Start der App)
    fun fetchMembersData() {
        // hier holen wir die Liste aus dem Repository und speichern sie im StateFlow (.value)
        _membersList.value = memberRepositoryInterface.fetchMembersData()
    }

}


