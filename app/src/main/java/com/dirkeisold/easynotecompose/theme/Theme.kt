package com.dirkeisold.easynotecompose.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = com.dirkeisold.easynotecompose.theme.Green90,
    onPrimaryContainer = com.dirkeisold.easynotecompose.theme.Green10,
    secondary = com.dirkeisold.easynotecompose.theme.DarkGreen40,
    onSecondary = Color.White,
    secondaryContainer = com.dirkeisold.easynotecompose.theme.DarkGreen90,
    onSecondaryContainer = com.dirkeisold.easynotecompose.theme.DarkGreen10,
    tertiary = com.dirkeisold.easynotecompose.theme.Teal40,
    onTertiary = Color.White,
    tertiaryContainer = com.dirkeisold.easynotecompose.theme.Teal90,
    onTertiaryContainer = com.dirkeisold.easynotecompose.theme.Teal10,
    error = com.dirkeisold.easynotecompose.theme.Red40,
    onError = Color.White,
    errorContainer = com.dirkeisold.easynotecompose.theme.Red90,
    onErrorContainer = com.dirkeisold.easynotecompose.theme.Red10,
    background = com.dirkeisold.easynotecompose.theme.DarkGreenGray99,
    onBackground = com.dirkeisold.easynotecompose.theme.DarkGreenGray10,
    surface = com.dirkeisold.easynotecompose.theme.DarkGreenGray99,
    onSurface = com.dirkeisold.easynotecompose.theme.DarkGreenGray10,
    surfaceVariant = com.dirkeisold.easynotecompose.theme.GreenGray90,
    onSurfaceVariant = com.dirkeisold.easynotecompose.theme.GreenGray30,
    inverseSurface = com.dirkeisold.easynotecompose.theme.DarkGreenGray20,
    inverseOnSurface = com.dirkeisold.easynotecompose.theme.DarkGreenGray95,
    outline = com.dirkeisold.easynotecompose.theme.GreenGray50
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = com.dirkeisold.easynotecompose.theme.Green80,
    onPrimary = com.dirkeisold.easynotecompose.theme.Green20,
    primaryContainer = com.dirkeisold.easynotecompose.theme.Green30,
    onPrimaryContainer = com.dirkeisold.easynotecompose.theme.Green90,
    secondary = com.dirkeisold.easynotecompose.theme.DarkGreen80,
    onSecondary = com.dirkeisold.easynotecompose.theme.DarkGreen20,
    secondaryContainer = com.dirkeisold.easynotecompose.theme.DarkGreen30,
    onSecondaryContainer = com.dirkeisold.easynotecompose.theme.DarkGreen90,
    tertiary = com.dirkeisold.easynotecompose.theme.Teal80,
    onTertiary = com.dirkeisold.easynotecompose.theme.Teal20,
    tertiaryContainer = com.dirkeisold.easynotecompose.theme.Teal30,
    onTertiaryContainer = com.dirkeisold.easynotecompose.theme.Teal90,
    error = com.dirkeisold.easynotecompose.theme.Red80,
    onError = com.dirkeisold.easynotecompose.theme.Red20,
    errorContainer = com.dirkeisold.easynotecompose.theme.Red30,
    onErrorContainer = com.dirkeisold.easynotecompose.theme.Red90,
    background = com.dirkeisold.easynotecompose.theme.DarkGreenGray10,
    onBackground = com.dirkeisold.easynotecompose.theme.DarkGreenGray90,
    surface = com.dirkeisold.easynotecompose.theme.DarkGreenGray10,
    onSurface = com.dirkeisold.easynotecompose.theme.DarkGreenGray90,
    surfaceVariant = com.dirkeisold.easynotecompose.theme.GreenGray30,
    onSurfaceVariant = com.dirkeisold.easynotecompose.theme.GreenGray80,
    inverseSurface = com.dirkeisold.easynotecompose.theme.DarkGreenGray90,
    inverseOnSurface = com.dirkeisold.easynotecompose.theme.DarkGreenGray10,
    outline = com.dirkeisold.easynotecompose.theme.GreenGray60
)

/**
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 */
@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Color scheme
    val colorScheme = if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
    // Gradient colors
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface
    )

    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp
    )

    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides defaultGradientColors,
        LocalBackgroundTheme provides defaultBackgroundTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MyTypography,
            content = content
        )
    }
}
