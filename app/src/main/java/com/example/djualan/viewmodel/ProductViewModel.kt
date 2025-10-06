package com.example.djualan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.djualan.data.dao.Product
import com.example.djualan.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products
    fun addProduct(product: Product, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                if(product.szProductName.isBlank()) {
                    onComplete(false, "Product name cannot be empty.")
                    return@launch
                }
                if(product.decPrice.isNaN()) {
                    onComplete(false, "Product price cannot be empty.")
                    return@launch
                }
                productRepository.addProduct(product)
                onComplete(true, null)
            } catch (exception: Exception){
                onComplete(false, exception.message ?: "Failed to save product.")
            }
        }
    }
}