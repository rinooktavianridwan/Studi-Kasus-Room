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
 * OfflineItemsRepository adalah implementasi dari ItemsRepository.
 * Class ini berfungsi sebagai repositori yang berinteraksi dengan database melalui DAO (Data Access Object),
 * yaitu ItemDao. Semua operasi yang didefinisikan dalam ItemsRepository akan diimplementasikan dengan memanggil fungsi DAO.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    // Mengambil semua item dari database melalui fungsi DAO getAllItems().
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    // Mengambil item berdasarkan ID dari database dengan memanggil fungsi DAO getItem().
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    // Memasukkan item baru ke dalam database dengan memanggil fungsi DAO insert().
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    // Menghapus item dari database dengan memanggil fungsi DAO delete().
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    // Memperbarui item di dalam database dengan memanggil fungsi DAO update().
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
