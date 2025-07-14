package jp.ikanoshiokara.vooooote_client

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource
import vooooote_client.composeapp.generated.resources.NotoSansJP_Black
import vooooote_client.composeapp.generated.resources.NotoSansJP_Bold
import vooooote_client.composeapp.generated.resources.NotoSansJP_ExtraBold
import vooooote_client.composeapp.generated.resources.NotoSansJP_ExtraLight
import vooooote_client.composeapp.generated.resources.NotoSansJP_Light
import vooooote_client.composeapp.generated.resources.NotoSansJP_Medium
import vooooote_client.composeapp.generated.resources.NotoSansJP_Regular
import vooooote_client.composeapp.generated.resources.NotoSansJP_SemiBold
import vooooote_client.composeapp.generated.resources.NotoSansJP_Thin
import vooooote_client.composeapp.generated.resources.Res

private val fontSets: List<Pair<FontResource, FontWeight>> = listOf(
    Pair(Res.font.NotoSansJP_Medium, FontWeight.Normal),
    Pair(Res.font.NotoSansJP_Regular, FontWeight.Normal),
    Pair(Res.font.NotoSansJP_Black, FontWeight.Black),
    Pair(Res.font.NotoSansJP_Bold, FontWeight.Bold),
    Pair(Res.font.NotoSansJP_ExtraBold, FontWeight.ExtraBold),
    Pair(Res.font.NotoSansJP_ExtraLight, FontWeight.ExtraLight),
    Pair(Res.font.NotoSansJP_Light, FontWeight.Light),
    Pair(Res.font.NotoSansJP_SemiBold, FontWeight.SemiBold),
    Pair(Res.font.NotoSansJP_Thin, FontWeight.Thin)
)

@Composable
fun rememberFontFamily() = FontFamily(
    fontSets.map { (res, weight) -> Font(res, weight) },
)