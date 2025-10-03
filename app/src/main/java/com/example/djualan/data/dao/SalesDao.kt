import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
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
    val decAmount : Double
)

@Entity(tableName = "SaleItem", primaryKeys = ["szSalesId", "szProductId"])
data class SaleItem (
    val szSalesId: String,
    val szProductId: String,
    val quantity: Int
)

data class SaleWithProducts(
    @Embedded val sale: SaleData,
    @Relation(
        parentColumn = "szSalesId",
        entityColumn = "szProductId",
        associateBy = Junction(SaleItem::class)
    )
    val products: List<Product>
)

