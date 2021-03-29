package com.enestekin.simplecachingexample.data

import androidx.room.withTransaction
import com.enestekin.simplecachingexample.api.RestaurantApi
import com.enestekin.simplecachingexample.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase
) {

    private val restaurantDao = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()
        },
        fetch = {
            delay(2000)
            api.getRestaurants()
        },
        saveFetchResult = { restaurant ->
            // update database
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(restaurant)

            }

        }
    )
}