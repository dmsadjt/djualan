package com.example.djualan.data.repository

import SaleData
import SalesDao

class SalesRepository(
    private val salesDao: SalesDao
) {
    suspend fun getSalesForToday(): Result<List<SaleData>> {
       return try {
           Result.success(salesDao.getSalesForToday())
       } catch (e: Exception) {
           Result.failure(e)
       }
    }

    suspend fun addSales(sale:SaleData) : Result<Unit> {
        return try {
            salesDao.addSale(sale)
            Result.success(Unit)
        } catch(e:Exception) {
            Result.failure(e)
        }
    }
}