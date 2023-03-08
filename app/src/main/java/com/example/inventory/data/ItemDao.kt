package com.example.inventory.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {
    // La stratégie OnConflictStrategy.IGNORE ignore le nouvel élément si sa clé primaire figure déjà dans la base de données.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
}