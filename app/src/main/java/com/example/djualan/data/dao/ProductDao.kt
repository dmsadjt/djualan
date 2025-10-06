package com.example.djualan.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
@Dao
interface ProductDao {
    @Insert
    suspend fun addProduct(productData: Product)
    @Query("SELECT * FROM Product")
    suspend fun getAllProduct(): List<Product>
    @Query("SELECT * FROM Product WHERE szProductId = :szProductId")
    suspend fun getProduct(szProductId: String): Product
    @Update
    suspend fun updateProduct(productData: Product)
    @Delete
    suspend fun deleteProduct(productData: Product)
}

@Entity(tableName = "Product")
data class Product (
    @PrimaryKey(autoGenerate = false) val szProductId : String,
    val szProductName : String,
    val decPrice : Double,
    val bActive : Boolean,
    val szImageUri : String?
)
