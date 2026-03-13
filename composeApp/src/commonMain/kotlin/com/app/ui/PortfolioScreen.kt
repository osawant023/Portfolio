package com.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.app.ui.sections.AboutSection
import com.app.ui.sections.CertificationsSection
import com.app.ui.sections.ContactSection
import com.app.ui.sections.EducationSection
import com.app.ui.sections.ExperienceSection
import com.app.ui.sections.FooterSection
import com.app.ui.sections.HeroSection
import com.app.ui.sections.ProjectsSection
import com.app.ui.sections.SkillsSection
import com.app.ui.theme.PortfolioTheme
import kotlinx.coroutines.launch

/**
 * Main Portfolio Screen — single scrollable page composing all sections.
 *
 * Sections (in order):
 *  1. HeroSection
 *  2. AboutSection
 *  3. SkillsSection
 *  4. ExperienceSection
 *  5. ProjectsSection
 *  6. EducationSection
 *  7. CertificationsSection
 *  8. ContactSection
 *  9. FooterSection
 */
@Composable
fun PortfolioScreen(
    onEmailClick: () -> Unit = {},
    onOpenUrl: (String) -> Unit = {},
    onPhoneClick: () -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    val listState = rememberLazyListState()

    // Scroll-to-contact index (index 8 in lazy list)
    val coroutineScope = rememberCoroutineScope()
    val contactIndex = 8

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        // 1. Hero
        item {
            HeroSection(
                onContactClick = {
                    // Scroll to contact section
                    coroutineScope.launch {
                        listState.animateScrollToItem(contactIndex)
                    }
                },
                onViewWorkClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(5) // Projects section
                    }
                }
            )
        }

        // 2. About
        item { AboutSection() }

        // 3. Skills
        item { SkillsSection() }

        // 4. Experience
        item { ExperienceSection() }

        // 5. Projects
        item { ProjectsSection(onOpenUrl = onOpenUrl) }

        // 6. Education
        item { EducationSection() }

        // 7. Certifications
        item { CertificationsSection(onOpenUrl = onOpenUrl) }

        // 8. Contact
        item {
            ContactSection(
                onEmailClick = onEmailClick,
                onOpenUrl = onOpenUrl,
                onPhoneClick = onPhoneClick
            )
        }

        // 9. Footer
        item { FooterSection() }
    }
}
