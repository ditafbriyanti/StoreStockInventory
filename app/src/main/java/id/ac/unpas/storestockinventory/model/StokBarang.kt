package id.ac.unpas.storestockinventory.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StokBarang (
    @PrimaryKey val id: String,
    val namaBarang: String,
    val harga: String,
    val stok: String,
    val tanggalKadaluarsa: String
)