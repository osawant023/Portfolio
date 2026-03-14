package com.app.ui.sections

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.GradientButton
import com.app.ui.components.OutlineButton
import com.app.ui.components.TechBadge
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData
import kotlinx.coroutines.delay

@Composable
fun HeroSection(
    onContactClick: () -> Unit = {},
    onViewWorkClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    // Entry animations
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

    // Bouncing arrow
    val arrowOffset by rememberInfiniteTransition(label = "arrow").animateFloat(
        initialValue = 0f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "arrow_bounce"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0E1A),
                        Color(0xFF0F1629),
                        Color(0xFF111827)
                    )
                )
            )
            // Decorative grid pattern drawn on canvas
            .drawBehind {
                val gridColor = Color(0xFF1A2235)
                val spacing = 40f
                var x = 0f
                while (x < size.width) {
                    drawLine(gridColor, Offset(x, 0f), Offset(x, size.height), 0.5f)
                    x += spacing
                }
                var y = 0f
                while (y < size.height) {
                    drawLine(gridColor, Offset(0f, y), Offset(size.width, y), 0.5f)
                    y += spacing
                }

                // Glow circle
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0x3000D4AA),
                            Color.Transparent
                        ),
                        center = Offset(size.width * 0.7f, size.height * 0.3f),
                        radius = 400f
                    ),
                    radius = 400f,
                    center = Offset(size.width * 0.7f, size.height * 0.3f)
                )
            }
            .padding(horizontal = spacing.screenHorizontal)
            .padding(top = 40.dp, bottom = spacing.section)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = slideAnim)
                .alpha(alphaAnim),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TechBadge — "Built with Jetpack Compose"
            TechBadge(text = "Built with Jetpack Compose")

            Spacer(Modifier.height(spacing.xxlarge))

            // Name
            Text(
                text = PortfolioData.name,
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(spacing.small))

            // Role — gradient text effect via Box overlay trick
            Text(
                text = PortfolioData.role,
                style = MaterialTheme.typography.headlineMedium,
                color = colors.primary,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(spacing.medium))

            // Availability badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFF0D2E1F))
                    .padding(horizontal = 12.dp, vertical = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        Modifier
                            .size(6.dp)
                            .background(Color(0xFF22C55E), RoundedCornerShape(50.dp))
                    )
                    Text(
                        text = "Open to opportunities",
                        color = Color(0xFF4ADE80),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(Modifier.height(spacing.large))

            // Tagline
            Text(
                text = PortfolioData.heroTagline,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF8B9CBF),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 360.dp)
            )

            Spacer(Modifier.height(spacing.xlarge))

            // CTA Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GradientButton(
                    text = "Get in Touch",
                    onClick = onContactClick
                )
                OutlineButton(
                    text = "View Work",
                    onClick = onViewWorkClick
                )
            }

            Spacer(Modifier.height(spacing.xlarge))

            // Quick Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PortfolioData.aboutStats.forEach { (value, label) ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = value,
                            color = colors.primary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = label,
                            color = Color(0xFF8B9CBF),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(Modifier.height(spacing.section))

            // Scroll indicator
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = arrowOffset.dp)
            ) {
                Text(
                    text = "Scroll to explore",
                    color = Color(0xFF4A5568),
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                )
                Spacer(Modifier.height(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Scroll down",
                    tint = Color(0xFF4A5568),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
