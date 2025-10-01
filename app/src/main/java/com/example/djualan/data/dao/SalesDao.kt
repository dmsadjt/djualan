import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.djualan.data.dao.Product
import java.time.LocalDateTime

interface SalesDao {
    @Insert
    suspend fun addSale(sale: SaleData)
    @Query("SELECT * FROM Sales ORDER BY dtmSalesOccurrence DESC")
    suspend fun getSalesForToday(): List<SaleData>
}

@Entity(tableName = "Sales")
data class SaleData (
    @PrimaryKey(autoGenerate = false) val szSalesId : String,
    val dtmSalesOccurrence : LocalDateTime,
    val salesItemList : List<Product>,
    val decAmount : Double
)

