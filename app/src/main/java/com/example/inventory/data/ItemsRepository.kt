/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
/**
 * Interface ItemsRepository bertindak sebagai abstraksi untuk akses data item.
 * Sehingga memungkinkan implementasi detail dari sumber data untuk diubah dengan mudah tanpa
 * memengaruhi antarmuka pengguna. ItemsRepository menyediakan operasi dasar
 * seperti mengambil (retrieve), memasukkan (insert), memperbarui (update), dan menghapus (delete) item.
 */
interface ItemsRepository {
    /**
     * Mengambil semua item dari sumber data dan mengembalikannya sebagai aliran (stream) data.
     * Flow digunakan untuk memungkinkan pengamatan data secara reaktif.
     */
    fun getAllItemsStream(): Flow<List<Item>>


    /**
     * Mengambil item tertentu berdasarkan id-nya dari sumber data dan mengembalikannya
     * sebagai aliran (stream). Data yang diambil berupa objek Item atau null jika tidak ditemukan.
     */
    fun getItemStream(id: Int): Flow<Item?>


    /**
     * Memasukkan item baru ke dalam sumber data secara asynchronous.
     * Digunakan suspend agar bisa dipanggil dalam coroutine.
     */
    suspend fun insertItem(item: Item)


    /**
     * Menghapus item yang sudah ada dari sumber data secara asynchronous.
     * Digunakan suspend untuk operasi yang berjalan di luar main thread.
     */
    suspend fun deleteItem(item: Item)


    /**
     * Memperbarui item yang sudah ada di sumber data secara asynchronous.
     * Sehingga memungkinkan perubahan item yang sudah tersimpan.
     */
    suspend fun updateItem(item: Item)
}
