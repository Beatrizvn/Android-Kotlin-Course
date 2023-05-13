package com.beatriz.convidados.repository

import android.content.Context
import android.content.ContentValues
import com.beatriz.convidados.constants.DataBaseConstants
import com.beatriz.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {

    // Acesso ao banco de dados
    private var guestDataBase: GuestDataBase = GuestDataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(guest: GuestModel): Boolean {
        return try {
            /*
            Comando direto no mysql:
            UPDATE Customers
            SET ContactName = 'Alfred Schmidt', City = 'Frankfurt'
            WHERE CustomerID = 1;
             */
            val presence = if (guest.presence) 1 else 0
            val db = guestDataBase.writableDatabase

            // esse é o SET
            // primeiro parametro 'colunas' segundo parametro 'dados'
            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            //esse é o WHERE
            // selection 'coluna' args 'argumento da coluna'
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(guestID: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guestID.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val presence = cursor.getInt(2)

                    val guest = GuestModel(id, name, presence == 1)

                    list.add(guest)
                }
            }

            cursor.close()
            return list
        } catch (e: Exception) {
            return list
        }
    }

    // aqui é usado o rawQuery, ele é menos recomendado por mais que seja escrito menos codigo
    fun getPresent(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val presence = cursor.getInt(2)

                    val guest = GuestModel(id, name, presence == 1)

                    list.add(guest)
                }
            }

            cursor.close()
            return list
        } catch (e: Exception) {
            return list
        }
    }

    /*
    Isso aqui é criminoso pois poderiamos juntar a fun getPresent e getAbsent em uma só
    Ja que a unica alteração entre eles é o parametro presence em val curso.
    Porem como no curso ele disse que por enquanto preferia deixar separado, também vou deixar
     */
    fun getAbsent(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val presence = cursor.getInt(2)

                    val guest = GuestModel(id, name, presence == 1)

                    list.add(guest)
                }
            }

            cursor.close()
            return list
        } catch (e: Exception) {
            return list
        }
    }

}
