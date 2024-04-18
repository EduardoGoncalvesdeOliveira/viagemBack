package com.example.viagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viagem.repositorio.ViagemRepositorio
import com.example.viagem.ui.theme.ViagemTheme
import com.example.viagem.utilitarios.encurtaDatas
import kotlin.time.Duration.Companion.days

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViagemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val viagens = ViagemRepositorio().listarTodasAsViagens()

    LazyColumn {
        items(viagens) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier
                            .border(2.dp, color = Color.Gray, shape = RectangleShape)
                            .padding(2.dp)
                            .fillMaxWidth(),
                        painter = if (it.imagem == null) painterResource(id = R.drawable.placeholderimage) else it.imagem!!,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                Text(text = "${it.destino}, ${it.dataChegada.dayOfWeek} ${it.dataChegada.dayOfMonth}")
                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp), text = "${it.descricao}"
                )
                Text(text = encurtaDatas(it.dataChegada, it.dataPartida))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ViagemTheme {
        Greeting("Android")
    }
}