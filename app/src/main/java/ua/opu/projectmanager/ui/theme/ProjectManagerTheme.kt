package ua.opu.projectmanager.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Colors = lightColorScheme(
    primary = Color(0xFF315FA6),
    secondary = Color(0xFF006B5A),
    tertiary = Color(0xFF785900),
)

@Composable
fun ProjectManagerTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, content = content)
}
