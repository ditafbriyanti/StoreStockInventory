package id.ac.unpas.storestockinventory.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.storestockinventory.model.StokBarang
import id.ac.unpas.storestockinventory.persistences.StokBarangDao
import id.ac.unpas.storestockinventory.ui.theme.LightBlue700
import id.ac.unpas.storestockinventory.ui.theme.Blue700
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanBarang(stokBarangDao: StokBarangDao) {
    val namaBarang = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val stok = remember { mutableStateOf(TextFieldValue("")) }
    val tanggalKadaluarsa = remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "Nama Barang") },
            value = namaBarang.value,
            onValueChange = {
                namaBarang.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Nama Barang") }
        )

        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Number),
            placeholder = { Text(text = "Harga") }
        )

        OutlinedTextField(
            label = { Text(text = "Stok") },
            value = stok.value,
            onValueChange = {
                stok.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Number),
            placeholder = { Text(text = "Stok") }
        )

        OutlinedTextField(
            label = { Text(text = "Tanggal Kadaluarsa") },
            value = tanggalKadaluarsa.value,
            onValueChange = {
                tanggalKadaluarsa.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )

        val saveButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = LightBlue700,
            contentColor = Blue700
        )

        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Blue700,
            contentColor = LightBlue700
        )

        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = StokBarang(id, namaBarang.value.text,
                    harga.value.text, stok.value.text, tanggalKadaluarsa.value.text)
                scope.launch {
                        stokBarangDao.insertAll(item)
                }
                namaBarang.value = TextFieldValue("")
                harga.value = TextFieldValue("")
                stok.value = TextFieldValue("")
                tanggalKadaluarsa.value = TextFieldValue("")
            },  colors = saveButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                namaBarang.value = TextFieldValue("")
                harga.value = TextFieldValue("")
                stok.value = TextFieldValue("")
                tanggalKadaluarsa.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}