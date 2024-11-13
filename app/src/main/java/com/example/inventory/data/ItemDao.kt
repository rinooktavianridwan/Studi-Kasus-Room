package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Interface ItemDao adalah bagian dari komponen Room yang berfungsi sebagai Data Access Object (DAO).
 * DAO bertujuan untuk menyediakan antarmuka abstrak untuk mengakses database, sehingga memisahkan
 * logika database dari bagian aplikasi lainnya dan menjaga prinsip tanggung jawab tunggal.
 */
@Dao
interface ItemDao {
    /**
     * Menambahkan @Insert yang berfungsi menyisipkan item baru ke database dengan strategi OnConflictStrategy.IGNORE.
     * Jika terdapat konflik (seperti ID yang sama), data yang baru akan diabaikan.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Menambahkan @Update yang berfungsi untuk memperbarui data item yang ada berdasarkan nilai properti yang diberikan.
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Menambahkan @Delete yang berfungsi untuk menghapus item dari database.
     */
    @Delete
    suspend fun delete(item: Item)


    /**
     * Menambahkan @Query yang digunakan untuk menjalankan kueri SQL khusus.
     * - getItem(id: Int) berfungsi mengambil data item berdasarkan ID uniknya,
     *   memberikan hasil sebagai Flow yang otomatis ter-update ketika ada perubahan.
     * - getAllItems() berfungsi mengambil semua item dari tabel dan mengurutkannya berdasarkan nama secara ascending.
     *   Hasil ditampilkan sebagai Flow<List<Item>>, yang mempermudah aplikasi untuk memonitor dan merefleksikan setiap perubahan data.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}