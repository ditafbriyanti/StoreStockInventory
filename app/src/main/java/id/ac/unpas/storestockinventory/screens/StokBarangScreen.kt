package id.ac.unpas.storestockinventory.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.storestockinventory.model.StokBarang
import id.ac.unpas.storestockinventory.persistences.AppDatabase

@Composable
fun StokBarangScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pencatatan-barang"
    ).build()
    val stokBarangDao = db.stokBarangDao()

    val list : LiveData<List<StokBarang>> = stokBarangDao.loadAll()
    val items: List<StokBarang> by list.observeAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanBarang(stokBarangDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama Barang", fontSize = 14.sp)
                        Text(text = item.namaBarang, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Harga", fontSize = 14.sp)
                        Text(text = item.harga, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Stok", fontSize = 14.sp)
                        Text(text = item.stok, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Kadaluarsa", fontSize = 14.sp)
                        Text(text = item.tanggalKadaluarsa, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }

                }

                Divider(modifier = Modifier.fillMaxWidth())

            })
        }
    }
}