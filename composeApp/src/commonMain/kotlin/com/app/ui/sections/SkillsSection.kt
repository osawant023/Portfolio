package com.app.ui.sections

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.data.PortfolioData
import com.app.ui.components.ChipVariant
import com.app.ui.components.GlassCard
import com.app.ui.components.LabelCapsText
import com.app.ui.components.LabelMonoText
import com.app.ui.components.SkillChip
import com.app.ui.theme.PortfolioTheme

@Composable
fun SkillsSection(
    modifier: Modifier = Modifier,
    onOpenUrl: (String) -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.section)
    ) {
        Column(modifier = Modifier.padding(horizontal = spacing.screenHorizontal)) {
            LabelMonoText(
                text = "ENGINEERING_INFRASTRUCTURE",
                color = colors.textSecondary.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                Text(
                    text = "CORE_STACK",
                    style = MaterialTheme.typography.displaySmall,
                    color = colors.primary,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-2).sp
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(colors.accent, RoundedCornerShape(1.5.dp))
                )
            }

            Spacer(Modifier.height(spacing.small))

            // Subtitle with left border accent
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(60.dp)
                        .background(colors.accent, RoundedCornerShape(2.dp))
                )
                Spacer(Modifier.width(spacing.compact))
                Text(
                    text = "Engineering high-performance Android applications through architectural precision and reactive design patterns. Specializing in Kotlin Multiplatform and Modern Android Development.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                    lineHeight = 24.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(spacing.xlarge))

        // Infinite Skills Marquee (moved above certification block)
        SkillsMarquee(skills = PortfolioData.skills)

        Spacer(Modifier.height(spacing.xlarge))

        // Bento Skills Grid (Two-column Masonry vertical layout to eliminate vertical gaps)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenHorizontal),
            horizontalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            // Left Column (Categories 0, 2, 4, 6)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(spacing.large)
            ) {
                PortfolioData.skillCategories.filterIndexed { i, _ -> i % 2 == 0 }.forEachIndexed { index, pair ->
                    SkillBentoCard(
                        index = index * 2 + 1,
                        title = pair.first,
                        skills = pair.second
                    )
                }
            }

            // Right Column (Categories 1, 3, 5, 7)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(spacing.large)
            ) {
                PortfolioData.skillCategories.filterIndexed { i, _ -> i % 2 != 0 }.forEachIndexed { index, pair ->
                    SkillBentoCard(
                        index = index * 2 + 2,
                        title = pair.first,
                        skills = pair.second
                    )
                }
            }
        }

        // Certifications Section
        Spacer(Modifier.height(spacing.xxlarge))

        Column(modifier = Modifier.padding(horizontal = spacing.screenHorizontal)) {
            // Section divider
            Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                Text(
                    text = "Certifications",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(colors.accent, RoundedCornerShape(1.5.dp))
                )
            }

            Spacer(Modifier.height(spacing.large))

            // Certification card
            PortfolioData.certifications.forEach { cert ->
                GlassCard(modifier = Modifier.padding(bottom = 16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.cardPadding),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.medium)
                    ) {
                        // Placeholder image area
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(90.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(colors.surfaceContainerHighest)
                                .border(1.dp, colors.border.copy(alpha = 0.3f), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "CERT",
                                style = MaterialTheme.typography.labelLarge,
                                color = colors.textSecondary.copy(alpha = 0.5f),
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(colors.accent.copy(alpha = 0.15f))
                                    .padding(horizontal = 10.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "CERTIFIED MASTER",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = colors.accent,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 1.sp,
                                    fontSize = 10.sp
                                )
                            }
                            Spacer(Modifier.height(spacing.small))
                            Text(
                                text = cert.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Issued by ${cert.issuer} • Focusing on building shared business logic and animations.",
                                style = MaterialTheme.typography.bodySmall,
                                color = colors.textSecondary
                            )
                        }

                        if (cert.credentialUrl != null) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "View credential",
                                tint = colors.accent,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { onOpenUrl(cert.credentialUrl) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillBentoCard(
    index: Int,
    title: String,
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    GlassCard(modifier = modifier) {
        Column(modifier = Modifier.padding(spacing.cardPadding)) {
            LabelMonoText(
                text = "${index.toString().padStart(2, '0')} / ${title.uppercase()}",
                color = colors.accent,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = colors.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = spacing.medium)
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                skills.forEach { skill ->
                    SkillChip(text = skill, variant = ChipVariant.Default)
                }
            }
        }
    }
}

@Composable
private fun SkillsMarquee(skills: List<String>) {
    val infiniteTransition = rememberInfiniteTransition(label = "marquee")
    val animOffset1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -600f,
        animationSpec = infiniteRepeatable(
            animation = tween(28000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "row1"
    )
    val animOffset2 by infiniteTransition.animateFloat(
        initialValue = -600f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(32000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "row2"
    )

    val mid = skills.size / 2
    val list1 = skills.subList(0, mid)
    val list2 = skills.subList(mid, skills.size)

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF070B08))
            .padding(vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = animOffset1.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.wrapContentSize(unbounded = true)
            ) {
                (list1 + list1 + list1).forEach { skill ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF141C16))
                            .border(1.dp, Color(0xFF3D4A3F).copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = skill.uppercase(),
                            color = Color(0xFF5FDE8E).copy(alpha = 0.8f),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = animOffset2.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.wrapContentSize(unbounded = true)
            ) {
                (list2 + list2 + list2).forEach { skill ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF141C16))
                            .border(1.dp, Color(0xFF3D4A3F).copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = skill.uppercase(),
                            color = Color(0xFFDDE4DB).copy(alpha = 0.6f),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }
}
