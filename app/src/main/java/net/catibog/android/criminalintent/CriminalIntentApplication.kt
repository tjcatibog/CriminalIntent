package net.catibog.android.criminalintent

import android.app.Application
import net.catibog.android.criminalintent.models.CrimeRepository

class CriminalIntentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}