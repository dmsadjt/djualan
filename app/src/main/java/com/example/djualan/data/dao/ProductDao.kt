package com.example.djualan.data.dao

import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

interface ProductDao {
    @Insert
    suspend fun addProduct(product: Product)
    @Query("SELECT * FROM Product")
    suspend fun getAllProduct(): List<Product>
    @Query("SELECT * FROM Product WHERE szProductId = :szProductId")
    suspend fun getProduct(szProductId: String): Product
    @Update
    suspend fun updateProduct(product: Product)
    @Delete
    suspend fun deleteProduct(szProductId: String)
}

@Entity(tableName = "Product")
data class Product (
    @PrimaryKey(autoGenerate = false) val szProductId : String,
    val szProductName : String,
    val decPrice : Double,
    val bActive : Boolean
)
