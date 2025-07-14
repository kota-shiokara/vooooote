package jp.ikanoshiokara.vooooote_client

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import vooooote_client.composeapp.generated.resources.Res
import vooooote_client.composeapp.generated.resources.compose_multiplatform
import vooooote_client.composeapp.generated.resources.tatemono_jinja

@Composable
@Preview
fun App() {
    MaterialTheme(
        typography = rememberTypography()
    ) {
        var isOmikuji by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (isOmikuji) {
                    var reflesh by remember { mutableStateOf(0) }
                    var omikuji by remember { mutableStateOf(listOf<Omikuji>()) }

                    LaunchedEffect(reflesh) {
                        omikuji = (1..10).map {
                            when ((0 until 10).random()) {
                                0 -> Omikuji.TyoDaikiti()
                                1, 2 -> Omikuji.Daikiti()
                                3, 4 -> Omikuji.Tyukiti()
                                5, 6 -> Omikuji.Kiti()
                                7, 8, 9 -> Omikuji.Kyo()
                                else -> Omikuji.Kyo()
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            items(items = omikuji) { kuji ->
                                if (kuji == Omikuji.TyoDaikiti()) {
                                    Card(
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.size(150.dp),
                                        colors = CardDefaults.cardColors(contentColor = Color.Red)
                                    ) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            text = kuji.name,
                                            modifier = Modifier.align(Alignment.CenterHorizontally),
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                } else {
                                    Card(
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.size(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            text = kuji.name,
                                            modifier = Modifier.align(Alignment.CenterHorizontally),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(onClick = {
                            reflesh++
                        }) {
                            Text("もう一度引く")
                        }
                    }

                } else {
                    Image(
                        painter = painterResource(Res.drawable.tatemono_jinja),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = "美流怒神社",
                        modifier =
                            Modifier
                                .background(Color.White.copy(0.3f))
                                .clickable {
                                    isOmikuji = !isOmikuji
                                },
                        fontSize = 200.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

sealed interface Omikuji {
    val name: String
    data class TyoDaikiti(
        override val name: String = "超大吉"
    ): Omikuji

    data class Daikiti(
        override val name: String = "大吉"
    ): Omikuji

    data class Tyukiti(
        override val name: String = "中吉"
    ): Omikuji

    data class Kiti(
        override val name: String = "吉"
    ): Omikuji

    data class Kyo(
        override val name: String = "凶"
    ): Omikuji
}