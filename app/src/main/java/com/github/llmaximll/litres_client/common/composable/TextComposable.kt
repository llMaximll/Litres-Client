package com.github.llmaximll.litres_client.common.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.llmaximll.litres_client.R

@Composable
fun LogoText(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 48.sp
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append(stringResource(id = R.string.splash_logo_1))
            }
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(stringResource(id = R.string.splash_logo_2))
            }
        },
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
    )
}