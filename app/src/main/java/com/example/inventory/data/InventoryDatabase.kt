package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * InventoryDatabase adalah kelas RoomDatabase yang menyediakan akses ke database dan DAO
 * untuk aplikasi. Database diatur sebagai singleton untuk memastikan hanya ada
 * satu instance database yang berjalan pada waktu yang sama, menghindari pemborosan sumber daya.
 *
 * Kelas diatur dengan anotasi @Database yang menghubungkan database dengan entity yang ada,
 * yaitu Item. Versi database adalah 1, dan exportSchema diatur ke false untuk menghindari
 * penyimpanan schema cadangan.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // Fungsi abstrak yang mengembalikan instance ItemDao untuk berinteraksi dengan data item
    abstract fun itemDao(): ItemDao

    companion object {
        // Variabel Instance bertindak sebagai cache untuk instance database yang sedang berjalan.
        @Volatile
        private var Instance: InventoryDatabase? = null

        // Fungsi getDatabase(context) mengembalikan instance database yang sudah dibuat
        // atau membuat yang baru jika belum ada. Menggunakan synchronized untuk memastikan thread-safe.
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    //membuat instance database dangn cara memanggil .build(). Panggilan ini akan menghapus error Android Studio.
                    .build()
                    .also { Instance = it }
            }
        }
    }
}