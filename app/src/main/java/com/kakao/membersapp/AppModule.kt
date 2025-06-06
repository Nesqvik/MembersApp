package com.kakao.membersapp

import android.content.Context
import com.kakao.membersapp.repositories.MemberRepository
import com.kakao.membersapp.viewmodels.MemberViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Provides
    @Singleton
    fun provideMemberRepository(
        context: Context
    ): MemberRepository {
        return com.kakao.membersapp.repositories.MemberRepository()
    }
    @Provides
    fun provideUserViewModel(memberRepository: MemberRepository)= MemberViewModel(memberRepository)
}
