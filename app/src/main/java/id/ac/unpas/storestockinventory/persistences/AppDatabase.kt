package id.ac.unpas.storestockinventory.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.storestockinventory.model.StokBarang

@Database(entities = [StokBarang::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stokBarangDao(): StokBarangDao
}
