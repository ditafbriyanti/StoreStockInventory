package id.ac.unpas.storestockinventory.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import id.ac.unpas.storestockinventory.model.StokBarang

@Dao
interface StokBarangDao {
    @Query("SELECT * FROM StokBarang")
    fun loadAll(): LiveData<List<StokBarang>>

    @Query("SELECT * FROM StokBarang WHERE id = :id")
    fun find(id: String): StokBarang?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: StokBarang)

    @Delete
    fun delete(item: StokBarang)
}