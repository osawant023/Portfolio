package com.app.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.sections.AboutSection
import com.app.ui.sections.ContactSection
import com.app.ui.sections.ExperienceSection
import com.app.ui.sections.FooterSection
import com.app.ui.sections.HeroSection
import com.app.ui.sections.ProjectsSection
import com.app.ui.sections.SkillsSection
import com.app.ui.theme.PortfolioTheme

@Composable
fun PortfolioScreen(
    onEmailClick: () -> Unit = {},
    onOpenUrl: (String) -> Unit = {},
    onPhoneClick: () -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    var activeTab by remember { mutableStateOf("Dashboard") }
    val scrollState = rememberScrollState()

    // Reset scroll when tab changes
    LaunchedEffect(activeTab) {
        scrollState.scrollTo(0)
    }

    val navItems = listOf(
        "Dashboard",
        "Timeline",
        "Projects",
        "Skills",
        "Contact"
    )

    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(colors.background)) {
        val isMobile = maxWidth < 600.dp

        // Main Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 100.dp) // Space for bottom floating navigation bar
        ) {
            // Smooth horizontal slider animation when switching tab contents
            AnimatedContent(
                targetState = activeTab,
                transitionSpec = {
                    val targetIndex = navItems.indexOf(targetState)
                    val initialIndex = navItems.indexOf(initialState)
                    if (targetIndex > initialIndex) {
                        (slideInHorizontally { width -> width } + fadeIn(animationSpec = tween(400))).togetherWith(
                            slideOutHorizontally { width -> -width } + fadeOut(animationSpec = tween(400)))
                    } else {
                        (slideInHorizontally { width -> -width } + fadeIn(animationSpec = tween(400))).togetherWith(
                            slideOutHorizontally { width -> width } + fadeOut(animationSpec = tween(400)))
                    }.using(
                        SizeTransform(clip = false)
                    )
                },
                label = "tab_content_transition"
            ) { tab ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    when (tab) {
                        "Dashboard" -> {
                            HeroSection(
                                onContactClick = { activeTab = "Contact" },
                                onViewWorkClick = { activeTab = "Projects" }
                            )
                            AboutSection()
                        }
                        "Skills" -> {
                            SkillsSection(onOpenUrl = onOpenUrl)
                        }
                        "Timeline" -> {
                            ExperienceSection()
                        }
                        "Projects" -> {
                            ProjectsSection(onOpenUrl = onOpenUrl)
                        }
                        "Contact" -> {
                            ContactSection(
                                onEmailClick = onEmailClick,
                                onOpenUrl = onOpenUrl,
                                onPhoneClick = onPhoneClick
                            )
                        }
                    }
                }
            }
            FooterSection(onOpenUrl = onOpenUrl)
        }

        // Floating Bottom Navigation Bar Container with gradient shadow backdrop
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            colors.background.copy(alpha = 0.85f),
                            colors.background
                        )
                    )
                )
                .padding(bottom = 24.dp, top = 40.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(colors.glassCardBg)
                    .border(1.dp, colors.glassCardBorder, RoundedCornerShape(30.dp))
                    .padding(horizontal = if (isMobile) 10.dp else 20.dp, vertical = if (isMobile) 8.dp else 12.dp),
                horizontalArrangement = Arrangement.spacedBy(if (isMobile) 8.dp else 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItems.forEach { text ->
                    NavLink(
                        text = text,
                        isActive = activeTab == text,
                        isMobile = isMobile,
                        onClick = { activeTab = text }
                    )
                }
            }
        }
    }
}

@Composable
private fun NavLink(
    text: String,
    isActive: Boolean,
    isMobile: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors

    // Smooth tab highlight animations
    val textColor by animateColorAsState(
        targetValue = if (isActive) colors.primary else colors.textSecondary.copy(alpha = 0.6f),
        animationSpec = tween(300),
        label = "nav_text_color"
    )

    val scale by animateFloatAsState(
        targetValue = if (isActive) 1.08f else 1.0f,
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "nav_text_scale"
    )

    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .padding(horizontal = if (isMobile) 4.dp else 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = textColor,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
            letterSpacing = if (isMobile) 0.3.sp else 1.sp,
            fontSize = if (isMobile) 9.sp else 11.sp
        )

        Spacer(Modifier.height(4.dp))

        // Animated expanding/collapsing indicator line underneath the tab text
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(if (isActive) (if (isMobile) 8.dp else 14.dp) else 0.dp)
                .background(colors.accent, RoundedCornerShape(1.dp))
                .animateContentSize(animationSpec = tween(300))
        )
    }
}
