package com.kakao.membersapp.repositories

import android.content.Context
import android.util.Log
import com.kakao.membersapp.ApiClient
import com.kakao.membersapp.interfaces.MemberRepositoryInterface
import com.kakao.membersapp.interfaces.MembersInterface
import com.kakao.membersapp.model.Member
import com.kakao.membersapp.model.MemberRest
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MemberRepository @Inject constructor(
    context: Context
) : MemberRepositoryInterface {

    private var membersInterface: MembersInterface
    val membersList: ArrayList<MemberRest> = arrayListOf()

    init {
        this.membersInterface = ApiClient.getInstance()!!.getClient().create(MembersInterface::class.java)
        requestMembers()
    }

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

    override fun requestMembers() {

        //hier starten wir eine HTTP-Anfrage, um eine Liste von Members zu bekommen
        val call: retrofit2.Call<List<MemberRest>> = membersInterface.getMembers()

        //hier führen wir die Anfrage asynchron aus (enqueue)
        call.enqueue(object : Callback<List<MemberRest>> {

            //wenn die Anfrage erfolgreich war
            override fun onResponse(
                call: retrofit2.Call<List<MemberRest>> ,
                response: Response<List<MemberRest>>
            ) {
                Log.d("MEMBERS_REST", "Erfolg")
                Log.d("Members_RESPONSE", response.body()!!.toString())


            }
            override fun onFailure(call: retrofit2.Call<List<MemberRest>> , t: Throwable) {
                Log.d("Members_RESPONSE", t.message.toString())

            }
        })
    }
}


