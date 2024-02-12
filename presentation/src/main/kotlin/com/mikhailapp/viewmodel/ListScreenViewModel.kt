package com.mikhailapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailapp.domain.models.ProductItems
import com.mikhailapp.domain.usecases.ProductItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val useCase: ProductItemsUseCase
) : ViewModel() {

    private val _liveData = MutableLiveData<ProductItems>()
    val liveData: LiveData<ProductItems> = _liveData

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun getProductItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productItems = useCase.execute()
                _liveData.postValue(productItems)
            } catch (e: Exception) {
                _errorFlow.emit(e.message ?: "Unknown error")
            }
        }
    }
}