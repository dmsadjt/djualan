package com.example.djualan.data.database
import SalesDao
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.djualan.data.dao.Product
import com.example.djualan.data.dao.ProductDao
import java.time.LocalDateTime

@Database(entities = [SaleData::class, Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun salesDao() : SalesDao
    abstract fun productDao() : ProductDao
}

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimeStamp(value: String?): LocalDateTime? {
       return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimeStamp(date: LocalDateTime?) : String? {
        return date?.toString()
    }

}