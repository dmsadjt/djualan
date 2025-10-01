package com.example.djualan.data.repository

import com.example.djualan.data.dao.Product
import com.example.djualan.data.dao.ProductDao

class ProductRepository (
    private val productDao: ProductDao
) {
    suspend fun getProduct(szProductId: String): Result<Product> {
        return try {
            Result.success(productDao.getProduct(szProductId = szProductId))
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllProduct() : Result<List<Product>> {
        return try {
            Result.success(productDao.getAllProduct())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addProduct(product: Product) : Result<Unit> {
        return try {
            productDao.addProduct(product = product)
            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProduct(product: Product) : Result<Unit> {
        return try {
            productDao.updateProduct(product)
            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteProduct(szProductId: String) : Result<Unit> {
        return try {
            productDao.deleteProduct(szProductId = szProductId)
            Result.success(Unit)
        } catch(e:Exception) {
            Result.failure(e)
        }
    }
}