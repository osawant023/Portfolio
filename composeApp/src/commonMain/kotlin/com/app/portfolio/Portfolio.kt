import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PortfolioApp() {
    val scrollState = rememberScrollState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Home", "Projects", "Skills", "Experience", "Contact")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Navigation
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.shadow(4.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title, fontWeight = FontWeight.Medium) }
                    )
                }
            }

            // Content based on selected tab
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(bottom = 16.dp)
            ) {
                when (selectedTabIndex) {
                    0 -> HomeScreen()
                    1 -> ProjectsScreen()
                    2 -> SkillsScreen()
                    3 -> ExperienceScreen()
                    4 -> ContactScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HeroSection()
        Spacer(modifier = Modifier.height(32.dp))
        AboutMeSection()
        Spacer(modifier = Modifier.height(32.dp))
        FeaturedProjectsSection()
        Spacer(modifier = Modifier.height(32.dp))
        HighlightedSkillsSection()
    }
}

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f))
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Image",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "OMKAR SAWANT",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Senior Android Developer",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                SocialButton(
                    icon = Icons.Outlined.Link,
                    contentDescription = "GitHub"
                )
                Spacer(modifier = Modifier.width(16.dp))
                SocialButton(
                    icon = Icons.Outlined.Link,
                    contentDescription = "LinkedIn"
                )
                Spacer(modifier = Modifier.width(16.dp))
                SocialButton(
                    icon = Icons.Default.Email,
                    contentDescription = "Email"
                )
            }
        }
    }
}

@Composable
fun SocialButton(icon: androidx.compose.ui.graphics.vector.ImageVector, contentDescription: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f))
            .clickable { /* Handle click */ },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun AboutMeSection() {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ABOUT ME",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Senior Android Developer with 5 years of experience crafting high-quality applications using Kotlin, Java, and Git. Proven expertise in leveraging RESTful APIs, Firebase, and Android Jetpack libraries to deliver seamless user experiences.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InfoItem(
                    icon = Icons.Default.Work,
                    title = "5+ Years",
                    subtitle = "Experience"
                )

                InfoItem(
                    icon = Icons.Outlined.Code,
                    title = "10+",
                    subtitle = "Projects"
                )

                InfoItem(
                    icon = Icons.Outlined.Devices,
                    title = "Android",
                    subtitle = "Expert"
                )
            }
        }
    }
}

@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FeaturedProjectsSection() {
    val projects = listOf(
        Project("Mawada", "Social networking app", "Kotlin, MVVM, Firebase"),
        Project("The Curl Nation", "E-commerce platform", "Kotlin, Coroutines, REST API"),
        Project("PIQR", "QR code scanner app", "Kotlin, CameraX, ML Kit"),
        Project("Salamtek", "Healthcare application", "Kotlin, Room, GraphQL")
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "FEATURED PROJECTS",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(projects) { project ->
                ProjectCard(project)
            }
        }
    }
}

data class Project(
    val name: String,
    val description: String,
    val technologies: String
)

@Composable
fun ProjectCard(project: Project) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = project.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = project.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column {
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = project.technologies,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { /* Handle click */ },
                    modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("View Details")
                }
            }
        }
    }
}

@Composable
fun HighlightedSkillsSection() {
    val highlightedSkills = listOf(
        Skill("Kotlin", 0.95f),
        Skill("MVVM Architecture", 0.9f),
        Skill("Jetpack Compose", 0.85f),
        Skill("Coroutines", 0.9f)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "KEY SKILLS",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        highlightedSkills.forEach { skill ->
            SkillItemModern(skill)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        FilledTonalButton(
            onClick = { /* Navigate to Skills tab */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("View All Skills")
        }
    }
}

@Composable
fun ProjectsScreen() {
    val projects = listOf(
        ProjectDetail(
            name = "Mawada",
            description = "A social networking application focused on connecting people with similar interests",
            technologies = "Kotlin, MVVM, Firebase, Coroutines",
            features = listOf(
                "Real-time messaging with Firebase",
                "User profile customization",
                "Content sharing and discovery",
                "Push notifications"
            ),
            role = "Lead Android Developer"
        ),
        ProjectDetail(
            name = "The Curl Nation",
            description = "E-commerce platform specialized in hair care products",
            technologies = "Kotlin, MVVM, REST API, Room Database",
            features = listOf(
                "Product catalog with filtering",
                "Secure payment integration",
                "User reviews and ratings",
                "Order tracking"
            ),
            role = "Senior Android Developer"
        ),
        ProjectDetail(
            name = "PIQR",
            description = "Advanced QR code scanner with additional features",
            technologies = "Kotlin, CameraX, ML Kit, MVVM",
            features = listOf(
                "Fast QR code scanning",
                "History of scanned codes",
                "Generate custom QR codes",
                "Share functionality"
            ),
            role = "Android Developer"
        ),
        ProjectDetail(
            name = "Salamtek",
            description = "Healthcare application for patient-doctor communication",
            technologies = "Kotlin, GraphQL, Jetpack Compose, MVVM",
            features = listOf(
                "Appointment scheduling",
                "Secure messaging",
                "Medical records access",
                "Medication reminders"
            ),
            role = "Senior Android Developer"
        ),
        ProjectDetail(
            name = "IDBI Insurance",
            description = "Insurance management application",
            technologies = "Java, SQLite, REST API",
            features = listOf(
                "Policy management",
                "Claim filing and tracking",
                "Document scanning with ML Kit",
                "Premium payment"
            ),
            role = "Android Developer"
        ),
        ProjectDetail(
            name = "LOTUS",
            description = "Business management application",
            technologies = "Java, SOAP API, MVC",
            features = listOf(
                "Inventory management",
                "Sales tracking",
                "Employee management",
                "Reporting dashboard"
            ),
            role = "Android Developer"
        ),
        ProjectDetail(
            name = "HAFELE",
            description = "Home automation control application",
            technologies = "Java, REST API, SQLite",
            features = listOf(
                "Device control interface",
                "Automation scheduling",
                "User permission management",
                "Usage analytics"
            ),
            role = "Android Developer"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "MY PROJECTS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
        )

        projects.forEach { project ->
            ProjectDetailCard(project)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

data class ProjectDetail(
    val name: String,
    val description: String,
    val technologies: String,
    val features: List<String>,
    val role: String
)

@Composable
fun ProjectDetailCard(project: ProjectDetail) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { expanded = !expanded },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Role: ${project.role}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = fadeOut() + shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Column(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        text = "Technologies:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = project.technologies,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Key Features:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    project.features.forEach { feature ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = "•",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                            )
                            Text(
                                text = feature,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SkillsScreen() {
    val technicalSkills = listOf(
        Skill("Kotlin", 0.95f),
        Skill("Java", 0.9f),
        Skill("MVVM Architecture", 0.9f),
        Skill("Jetpack Compose", 0.8f),
        Skill("Coroutines", 0.85f),
        Skill("Room Database", 0.9f),
        Skill("REST API", 0.9f),
        Skill("Firebase", 0.8f),
        Skill("GraphQL", 0.75f),
        Skill("Git & GitHub", 0.85f),
        Skill("Navigation Component", 0.9f),
        Skill("Google APIs", 0.8f)
    )

    val softSkills = listOf(
        Skill("UI/UX Design", 0.8f),
        Skill("Team Collaboration", 0.9f),
        Skill("Agile Methodology", 0.85f),
        Skill("Time Management", 0.9f),
        Skill("Clean Architecture", 0.85f),
        Skill("SOLID Principles", 0.8f),
        Skill("Design Patterns", 0.85f)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "MY SKILLS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
        )

        Text(
            text = "Technical Skills",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        technicalSkills.forEach { skill ->
            SkillItemModern(skill)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Soft Skills",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        softSkills.forEach { skill ->
            SkillItemModern(skill)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

data class Skill(val name: String, val proficiency: Float)

@Composable
fun SkillItemModern(skill: Skill) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = skill.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "${(skill.proficiency * 100).toInt()}%",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { skill.proficiency },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeCap = StrokeCap.Round
        )
    }
}

@Composable
fun ExperienceScreen() {
    val experiences = listOf(
        Experience(
            role = "Senior Android Developer",
            company = "Inovant Solutions",
            location = "Navi Mumbai",
            duration = "July 2021 — Present",
            responsibilities = listOf(
                "Utilized the Model View View Model (MVVM) architecture to ensure a clean separation of concerns and improve code maintainability.",
                "Developed custom components to reduce development time and allow for faster iterations.",
                "Coordinated with the UI/UX team to ensure the app's design was consistent and aligned with the company's branding.",
                "Developed and maintained documentation for Android applications, resulting in a 50% reduction in on boarding time for new team members.",
                "Mentored a team of 4 junior developers, improving team productivity.",
                "Collaborated with product manager and designers to create a user-friendly interface that resulted in a 15% increase in user satisfaction scores.",
                "Developed custom animations and transitions for a visually appealing user experience",
                "Deployed over 7+ scratch projects in sector like E-Commerce, Health Care etc."
            ),
            projects = listOf("Mawada", "The Curl nation", "PIQR", "Salamtek")
        ),
        Experience(
            role = "Android Developer",
            company = "Sudesi Infotech",
            location = "Navi Mumbai",
            duration = "July 2019 — July 2021",
            responsibilities = listOf(
                "Collaborated effectively as part of a team environment.",
                "Successfully implemented document scanning via camera with Google ML Kit and created an auto-update feature on login.",
                "Experienced working with local storage using SQLite.",
                "Completed given challenges with REST and SOAP API architectures.",
                "Strong knowledge in handling Complex JSON responses."
            ),
            projects = listOf("IDBI Insurance", "LOTUS", "HAFELE")
        )
    )

    val educations = listOf(
        Education(
            degree = "Bachelor of Computer Application",
            institution = "Navinchandra Mehta Institute of Technology & Department",
            location = "Dadar, Mumbai",
            duration = "July 2016 — May 2019"
        ),
        Education(
            degree = "HSC",
            institution = "Manjunath Collage of Commerce",
            location = "Thakurli",
            duration = "June 2014 — March 2016"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "EXPERIENCE & EDUCATION",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
        )

        // Work Experience
        Text(
            text = "Work Experience",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        experiences.forEach { experience ->
            ExperienceCard(experience)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Education
        Text(
            text = "Education",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        educations.forEach { education ->
            EducationCard(education)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Languages
        Text(
            text = "Languages",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LanguageChip("English")
            LanguageChip("Hindi")
            LanguageChip("Marathi")
        }
    }
}

data class Experience(
    val role: String,
    val company: String,
    val location: String,
    val duration: String,
    val responsibilities: List<String>,
    val projects: List<String> = emptyList()
)

@Composable
fun ExperienceCard(experience: Experience) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = experience.role,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${experience.company}, ${experience.location}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = experience.duration,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { expanded = !expanded },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        text = "Responsibilities:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    experience.responsibilities.forEach { responsibility ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = "•",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                            )
                            Text(
                                text = responsibility,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 20.sp
                            )
                        }
                    }

                    if (experience.projects.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Projects:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            experience.projects.forEach { project ->
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                ) {
                                    Text(
                                        text = project,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Education(
    val degree: String,
    val institution: String,
    val location: String,
    val duration: String
)

@Composable
fun EducationCard(education: Education) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.School,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = education.degree,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${education.institution}, ${education.location}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = education.duration,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun LanguageChip(language: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Language,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = language,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ContactScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GET IN TOUCH",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
        )

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "I'm always open to discussing new projects, opportunities or partnerships.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                ContactItem(
                    icon = Icons.Default.Email,
                    title = "Email",
                    value = "osawant023@gmail.com"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactItem(
                    icon = Icons.Default.Call,
                    title = "Phone",
                    value = "8779985574"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactItem(
                    icon = Icons.Default.LocationOn,
                    title = "Location",
                    value = "Mumbai, India"
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { /* Handle click */ },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.7f),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "SEND MESSAGE",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "FOLLOW ME",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            SocialIconButton(
                icon = Icons.Outlined.Link,
                contentDescription = "GitHub"
            )
            Spacer(modifier = Modifier.width(24.dp))
            SocialIconButton(
                icon = Icons.Outlined.Link,
                contentDescription = "LinkedIn"
            )
            Spacer(modifier = Modifier.width(24.dp))
            SocialIconButton(
                icon = Icons.Default.Email,
                contentDescription = "Email"
            )
        }
    }
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = value,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SocialIconButton(icon: androidx.compose.ui.graphics.vector.ImageVector, contentDescription: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { /* Handle click */ },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
    }
}
