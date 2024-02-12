package com.mikhailapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailapp.domain.models.Details
import com.mikhailapp.domain.usecases.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val useCase: ProductUseCase
) : ViewModel() {

    private val _detailsLiveData = MutableLiveData<Details>()
    val detailsLiveData: LiveData<Details> = _detailsLiveData

    fun loadProduct(itemId: String?) {
        if (itemId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                useCase.execute(itemId)?.let {
                    _detailsLiveData.postValue(it)
                }
            }
        }
    }

}