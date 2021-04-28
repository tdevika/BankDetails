package com.example.bankdetails.database

import androidx.room.*
import com.example.bankdetails.model.FavoriteBank
import kotlinx.coroutines.flow.Flow

@Dao
interface BanksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavoriteBank(value: FavoriteBank)

    @Query("select * from favorite_banks")
    fun getFavoriteBank(): Flow<List<FavoriteBank>>

    @Query("delete from favorite_banks where ifsc is (:ifsc)")
    fun deleteFavoriteBank(ifsc:String)

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_banks WHERE ifsc is (:ifsc))")
    fun isBankAddedToFav(ifsc: String): Boolean
}