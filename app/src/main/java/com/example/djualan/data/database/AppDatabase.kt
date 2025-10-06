package com.example.djualan.data.database
import android.content.Context
import com.example.djualan.data.dao.SalesDao
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.djualan.data.dao.Product
import com.example.djualan.data.dao.ProductDao
import com.example.djualan.data.dao.SaleData
import java.time.LocalDateTime

@Database(entities = [SaleData::class, Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun salesDao() : SalesDao
    abstract fun productDao() : ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "djualan_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
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