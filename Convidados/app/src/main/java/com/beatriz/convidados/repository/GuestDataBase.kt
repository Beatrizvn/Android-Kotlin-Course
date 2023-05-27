package com.beatriz.convidados.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.beatriz.convidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1, exportSchema = false)
abstract class GuestDataBase : RoomDatabase() {
    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDataBase

        fun getDatabase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations() // dentro dos colchetes colocaria a fun da att ex: MIGRATION_1_2
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        //Aqui seria a att do banco
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                TODO("Not yet implemented")
            }

        }
    }

}