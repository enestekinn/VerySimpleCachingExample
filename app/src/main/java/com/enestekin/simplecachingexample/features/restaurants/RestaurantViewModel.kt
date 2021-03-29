package com.enestekin.simplecachingexample.features.restaurants

import androidx.lifecycle.*
import com.enestekin.simplecachingexample.api.RestaurantApi
import com.enestekin.simplecachingexample.data.Restaurant
import com.enestekin.simplecachingexample.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
  repository: RestaurantRepository
) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()


 /*   private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = restaurantsLiveData


    init {
        println("ViewModel init started")
        viewModelScope.launch {
            val restaurants = api.getRestaurants()

            delay(2000)

            restaurantsLiveData.value = restaurants

        }
    }*/
}