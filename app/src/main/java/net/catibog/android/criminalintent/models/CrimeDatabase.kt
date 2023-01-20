package net.catibog.android.criminalintent.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.catibog.android.criminalintent.util.CrimeTypeConverters

@Database(entities = [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase: RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}