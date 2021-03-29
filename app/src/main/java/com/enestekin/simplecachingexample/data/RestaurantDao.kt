package com.enestekin.simplecachingexample.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): Flow<List<Restaurant>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurant: List<Restaurant>)

    @Query("DELETE  FROM restaurants")
    suspend fun  deleteAllRestaurants()
}