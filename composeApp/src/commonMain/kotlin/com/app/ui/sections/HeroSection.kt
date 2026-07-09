package com.app.ui.sections

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.GradientButton
import com.app.ui.components.OutlineButton
import com.app.ui.design.PortfolioColors
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData
import com.app.ui.design.Spacing
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.profile_pic

@Composable
fun HeroSection(
    onContactClick: () -> Unit = {},
    onViewWorkClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(800),
        label = "hero_alpha"
    )
    val slideAnim by animateDpAsState(
        targetValue = if (visible) 0.dp else 40.dp,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "hero_slide"
    )

    val fullName = PortfolioData.name
    var displayedName by remember { mutableStateOf("") }
    LaunchedEffect(visible) {
        if (visible) {
            displayedName = ""
            for (i in fullName.indices) {
                delay(80)
                displayedName = fullName.substring(0, i + 1)
            }
        }
    }

    val cursorVisible by rememberInfiniteTransition(label = "cursor").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursor_blink"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0E1510))
            .drawBehind {
                val dotColor = Color(0xFF1A211B)
                val dotSpacing = 32f
                val dotRadius = 1f
                var x = dotSpacing
                while (x < size.width) {
                    var y = dotSpacing
                    while (y < size.height) {
                        drawCircle(dotColor, dotRadius, Offset(x, y))
                        y += dotSpacing
                    }
                    x += dotSpacing
                }

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0x205FDE8E),
                            Color.Transparent
                        ),
                        center = Offset(size.width * 0.5f, size.height * 0.35f),
                        radius = 500f
                    ),
                    radius = 500f,
                    center = Offset(size.width * 0.5f, size.height * 0.35f)
                )
            }
            .padding(horizontal = spacing.screenHorizontal)
            .padding(top = 16.dp, bottom = spacing.section)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = slideAnim)
                .alpha(alphaAnim)
        ) {
            val isMobile = maxWidth < 768.dp

            if (isMobile) {
                // Stack vertically on mobile screen configurations
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    MagazineHeaderBlock(
                        colors = colors,
                        displayedName = displayedName,
                        cursorVisible = cursorVisible,
                        isMobile = true
                    )

                    Spacer(Modifier.height(32.dp))

                    // Centered circular profile image with glow
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileImageGlow(colors = colors, size = 200)
                    }

                    Spacer(Modifier.height(32.dp))

                    MainTextAndButtonsBlock(
                        colors = colors,
                        spacing = spacing,
                        onContactClick = onContactClick,
                        onViewWorkClick = onViewWorkClick,
                        isMobile = true
                    )
                }
            } else {
                // Row layout (Side-by-side) on desktop
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        MagazineHeaderBlock(
                            colors = colors,
                            displayedName = displayedName,
                            cursorVisible = cursorVisible,
                            isMobile = false
                        )

                        Spacer(Modifier.height(80.dp))

                        MainTextAndButtonsBlock(
                            colors = colors,
                            spacing = spacing,
                            onContactClick = onContactClick,
                            onViewWorkClick = onViewWorkClick,
                            isMobile = false
                        )
                    }

                    // Large circular profile image at right center
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = spacing.large),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileImageGlow(colors = colors, size = 360)
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileImageGlow(colors: PortfolioColors, size: Int) {
    Box(contentAlignment = Alignment.Center) {
        // Glow Backdrop
        Box(
            modifier = Modifier
                .size((size + 30).dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0x605FDE8E),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Circle Profile Image
        Image(
            painter = painterResource(Res.drawable.profile_pic),
            contentDescription = "Omkar Sawant Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
                .border(3.dp, colors.accent, CircleShape)
        )
    }
}

@Composable
private fun MagazineHeaderBlock(
    colors: PortfolioColors,
    displayedName: String,
    cursorVisible: Float,
    isMobile: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(if (isMobile) 12.dp else 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: OS Emblem
        Text(
            text = "OS",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = if (isMobile) 70.sp else 100.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                color = colors.textSecondary.copy(alpha = 0.05f),
                letterSpacing = (-4).sp
            )
        )

        // Right: Content Column (uses weight so it sits side-by-side cleanly)
        Column(
            modifier = Modifier
                .weight(1f)
                .drawBehind {
                    drawLine(
                        color = colors.border.copy(alpha = 0.2f),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                .padding(bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            // Name Reveal Animation
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = displayedName.uppercase(),
                    style = if (isMobile) MaterialTheme.typography.headlineSmall else MaterialTheme.typography.headlineLarge,
                    color = colors.accent,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
                Box(
                    modifier = Modifier
                        .size(if (isMobile) 8.dp else 10.dp, if (isMobile) 14.dp else 20.dp)
                        .background(colors.accent.copy(alpha = cursorVisible))
                )
            }

            Text(
                text = "THE ARCHITECT",
                style = if (isMobile) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleLarge,
                color = colors.primary,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            Text(
                text = PortfolioData.role.uppercase(),
                style = if (isMobile) MaterialTheme.typography.labelSmall else MaterialTheme.typography.labelMedium,
                color = colors.textSecondary.copy(alpha = 0.8f),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.5.sp
            )
        }
    }
}

@Composable
private fun MainTextAndButtonsBlock(
    colors: PortfolioColors,
    spacing: Spacing,
    onContactClick: () -> Unit,
    onViewWorkClick: () -> Unit,
    isMobile: Boolean
) {
    Column {
        // Hero Main Title
        Text(
            text = "Engineering Human-Centric Android Ecosystems.",
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = if (isMobile) 32.sp else 40.sp,
                lineHeight = if (isMobile) 40.sp else 48.sp
            ),
            color = colors.primary,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.widthIn(max = 800.dp)
        )

        Spacer(Modifier.height(spacing.large))

        // Tagline block with left accent border
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(54.dp)
                    .background(colors.accent, RoundedCornerShape(2.dp))
            )
            Spacer(Modifier.width(spacing.medium))
            Text(
                text = "Specializing in high-performance architectures, Jetpack Compose, and Kotlin Multiplatform for enterprise-scale mobile experiences.",
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
                lineHeight = 24.sp,
                modifier = Modifier.widthIn(max = 600.dp)
            )
        }
    }
}
