package com.example.djualan

import android.app.Application
import androidx.room.Room
import com.example.djualan.data.database.AppDatabase
import com.example.djualan.data.repository.ProductRepository
import com.example.djualan.data.repository.SalesRepository

class Djualan : Application() {
    lateinit var database: AppDatabase
        private set

    lateinit var salesRepository: SalesRepository
        private set

    lateinit var productRepository: ProductRepository
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()

        salesRepository = SalesRepository(database.salesDao())
        productRepository = ProductRepository(database.productDao())
    }
}