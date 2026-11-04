package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.data.model.AppThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = SurgicalCyan,
    onPrimary = DeepObsidian,
    primaryContainer = NavySurfaceVariant,
    onPrimaryContainer = SurgicalCyan,
    secondary = ClinicalTeal,
    onSecondary = DeepObsidian,
    secondaryContainer = NavySurfaceVariant,
    onSecondaryContainer = ClinicalTeal,
    tertiary = ElectricBlue,
    onTertiary = DeepObsidian,
    background = DeepObsidian,
    onBackground = TextPrimary,
    surface = NavySurface,
    onSurface = TextPrimary,
    surfaceVariant = NavySurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = BorderSubtle,
    outlineVariant = BorderHighlight,
    error = AlarmRose,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = SurgicalCyanDark,
    onPrimary = Color.White,
    primaryContainer = MedicalLightSurfaceVariant,
    onPrimaryContainer = SurgicalCyanDark,
    secondary = ClinicalTeal,
    onSecondary = Color.White,
    secondaryContainer = MedicalLightSurfaceVariant,
    onSecondaryContainer = ClinicalTeal,
    tertiary = ElectricBlue,
    onTertiary = Color.White,
    background = MedicalLightBg,
    onBackground = MedicalLightTextPrimary,
    surface = MedicalLightSurface,
    onSurface = MedicalLightTextPrimary,
    surfaceVariant = MedicalLightSurfaceVariant,
    onSurfaceVariant = MedicalLightTextSecondary,
    outline = MedicalLightBorder,
    outlineVariant = BorderSubtle,
    error = AlarmRose,
    onError = Color.White
)

@Composable
fun AnesthesiaProTheme(
    themeMode: AppThemeMode = AppThemeMode.DARK,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
