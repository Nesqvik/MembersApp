package com.kakao.membersapp

import android.content.Context
import com.kakao.membersapp.interfaces.MemberRepositoryInterface
import com.kakao.membersapp.repositories.MemberRepository
import com.kakao.membersapp.viewmodels.MemberViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Diese Datei definiert ein Dagger-Hilt-Modul zur Bereitstellung von Abhängigkeiten
@Module
// Dieses Modul wird im SingletonComponent installiert, also während der gesamten App-Lebensdauer aktiv
@InstallIn(SingletonComponent::class)
object AppModule {

    // Diese Funktion stellt den Anwendungskontext zur Verfügung
    @Provides
    // Es wird nur eine Instanz (Singleton) erstellt
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        // Gibt den aktuellen Anwendungskontext zurück
        return context
    }
    // Diese Funktion stellt eine Instanz des MemberRepository bereit
    @Provides
    // Wieder nur eine Instanz für die gesamte App
    @Singleton
    fun provideMemberRepository(
        context: Context
    ): MemberRepositoryInterface {
        // Erstellt und gibt ein konkretes MemberRepository-Objekt zurück
        return MemberRepository(context)
    }
    // Diese Funktion stellt ein ViewModel bereit, das das Repository verwendet
    @Provides
    fun provideUserViewModel(memberRepositoryInterface: MemberRepositoryInterface)= MemberViewModel(memberRepositoryInterface)
}


