package com.app.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.data.PortfolioData
import com.app.ui.components.GlassCard
import com.app.ui.components.GradientButton
import com.app.ui.components.LabelMonoText
import com.app.ui.components.SectionHeader
import com.app.ui.design.PortfolioColors
import com.app.ui.design.Spacing
import com.app.ui.theme.PortfolioTheme
import com.app.util.sendDirectEmail
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.profile_pic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    onEmailClick: () -> Unit = {},
    onOpenUrl: (String) -> Unit = {},
    onPhoneClick: () -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing
    val clipboardManager = LocalClipboardManager.current

    var nameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var messageInput by remember { mutableStateOf("") }
    var selectedIntent by remember { mutableStateOf("Hire Me") }
    var showCopiedText by remember { mutableStateOf(false) }

    var isSending by remember { mutableStateOf(false) }
    var sendStatus by remember { mutableStateOf<String?>(null) } // "success", "error", or null

    LaunchedEffect(showCopiedText) {
        if (showCopiedText) {
            delay(1500)
            showCopiedText = false
        }
    }

    val onSubmitMessage: () -> Unit = {
        if (nameInput.isNotBlank() && messageInput.isNotBlank()) {
            val key = PortfolioData.web3FormsAccessKey
            if (!key.isNullOrBlank()) {
                isSending = true
                sendStatus = null
                sendDirectEmail(
                    name = nameInput,
                    email = emailInput,
                    intent = selectedIntent,
                    message = messageInput,
                    accessKey = key,
                    onSuccess = {
                        isSending = false
                        sendStatus = "success"
                        nameInput = ""
                        emailInput = ""
                        messageInput = ""
                    },
                    onError = {
                        isSending = false
                        sendStatus = "error"
                    }
                )
            } else {
                // Fallback to mailto client opening if key is not configured
                val subject = "[$selectedIntent] Message from $nameInput"
                val body = "Name: $nameInput\nEmail: $emailInput\nIntent: $selectedIntent\n\nMessage:\n$messageInput"
                val mailtoUrl = "mailto:osawant023@gmail.com" +
                        "?subject=${subject.replace(" ", "%20").replace("\n", "%0A")}" +
                        "&body=${body.replace(" ", "%20").replace("\n", "%0A")}"
                onOpenUrl(mailtoUrl)
            }
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isMobile = maxWidth < 992.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenHorizontal)
                .padding(vertical = spacing.section),
            horizontalAlignment = Alignment.Start
        ) {
            SectionHeader(
                title = "Connect",
                subtitle = "let's build the next generation of mobile apps",
                labelPrefix = "COLLABORATE"
            )

            Spacer(Modifier.height(spacing.large))

            // Main Layout (Grid-based on desktop, stacked on mobile)
            if (isMobile) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(spacing.large)
                ) {
                    // Profile Info Card
                    DeveloperProfileBadge(
                        colors = colors,
                        spacing = spacing,
                        showCopiedText = showCopiedText,
                        onCopyEmail = {
                            clipboardManager.setText(AnnotatedString(PortfolioData.email))
                            showCopiedText = true
                        }
                    )

                    // Form Card
                    InteractiveFormCard(
                        colors = colors,
                        spacing = spacing,
                        nameInput = nameInput,
                        emailInput = emailInput,
                        messageInput = messageInput,
                        selectedIntent = selectedIntent,
                        isSending = isSending,
                        sendStatus = sendStatus,
                        onNameChange = { nameInput = it },
                        onEmailChange = { emailInput = it },
                        onMessageChange = { messageInput = it },
                        onIntentSelect = { selectedIntent = it },
                        onSubmit = onSubmitMessage
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.large)
                ) {
                    // Left Column: Interactive Form
                    Column(modifier = Modifier.weight(1.3f)) {
                        InteractiveFormCard(
                            colors = colors,
                            spacing = spacing,
                            nameInput = nameInput,
                            emailInput = emailInput,
                            messageInput = messageInput,
                            selectedIntent = selectedIntent,
                            isSending = isSending,
                            sendStatus = sendStatus,
                            onNameChange = { nameInput = it },
                            onEmailChange = { emailInput = it },
                            onMessageChange = { messageInput = it },
                            onIntentSelect = { selectedIntent = it },
                            onSubmit = onSubmitMessage
                        )
                    }

                    // Right Column: Profile Card
                    Column(modifier = Modifier.weight(1f)) {
                        DeveloperProfileBadge(
                            colors = colors,
                            spacing = spacing,
                            showCopiedText = showCopiedText,
                            onCopyEmail = {
                                clipboardManager.setText(AnnotatedString(PortfolioData.email))
                                showCopiedText = true
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(spacing.large))

            // Quick Connect Social Buttons (2x2 Grid on mobile, horizontal row of 4 on desktop)
            if (isMobile) {
                val socialChunks = listOf(
                    Triple(Icons.Default.Email, "Email", { onEmailClick() }),
                    Triple(Icons.Default.Person, "LinkedIn", { onOpenUrl(PortfolioData.linkedInUrl) }),
                    Triple(Icons.Default.Code, "GitHub", { onOpenUrl(PortfolioData.githubUrl) }),
                    Triple(Icons.Default.Phone, "Call", { onPhoneClick() })
                ).chunked(2)

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    socialChunks.forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.medium)
                        ) {
                            rowItems.forEach { (icon, text, onClick) ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(colors.glassCardBg)
                                        .border(1.dp, colors.glassCardBorder, RoundedCornerShape(8.dp))
                                        .clickable(onClick = onClick)
                                        .padding(vertical = 12.dp, horizontal = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = text,
                                            tint = colors.accent,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = text,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = colors.textSecondary,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    listOf(
                        Triple(Icons.Default.Email, "Email", { onEmailClick() }),
                        Triple(Icons.Default.Person, "LinkedIn", { onOpenUrl(PortfolioData.linkedInUrl) }),
                        Triple(Icons.Default.Code, "GitHub", { onOpenUrl(PortfolioData.githubUrl) }),
                        Triple(Icons.Default.Phone, "Call", { onPhoneClick() })
                    ).forEach { (icon, text, onClick) ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(colors.glassCardBg)
                                .border(1.dp, colors.glassCardBorder, RoundedCornerShape(8.dp))
                                .clickable(onClick = onClick)
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = text,
                                    tint = colors.accent,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.textSecondary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DeveloperProfileBadge(
    colors: PortfolioColors,
    spacing: Spacing,
    showCopiedText: Boolean,
    onCopyEmail: () -> Unit
) {
    GlassCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile image with glowing rings
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0x405FDE8E),
                                    Color.Transparent
                                )
                            ),
                            shape = CircleShape
                        )
                )
                Image(
                    painter = painterResource(Res.drawable.profile_pic),
                    contentDescription = "Omkar Sawant Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(3.dp, colors.accent, CircleShape)
                )
            }

            Text(
                text = PortfolioData.name,
                style = MaterialTheme.typography.headlineSmall,
                color = colors.primary,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "SENIOR ANDROID ARCHITECT",
                style = MaterialTheme.typography.labelSmall,
                color = colors.accent,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )

            // Dynamic Clipboard Copy Row (Awwwards design pattern)
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF141C16))
                    .border(1.dp, colors.border.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                    .clickable(onClick = onCopyEmail)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = PortfolioData.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textSecondary,
                    fontFamily = FontFamily.Monospace
                )
                Icon(
                    imageVector = if (showCopiedText) Icons.Default.Check else Icons.Default.ContentCopy,
                    contentDescription = "Copy Email",
                    tint = colors.accent,
                    modifier = Modifier.size(14.dp)
                )
            }

            if (showCopiedText) {
                Text(
                    text = "COPIED_TO_CLIPBOARD!",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.accent,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // Authentic Response / Time details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.surfaceContainerHighest.copy(alpha = 0.4f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "CONSOLE_METADATA",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.accent,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(colors.border.copy(alpha = 0.1f)))
                InfoBadgeLine(label = "TIMEZONE", value = "GMT+5:30 (IST)")
                InfoBadgeLine(label = "LOC", value = PortfolioData.location)
                InfoBadgeLine(label = "STATUS", value = "Open to Offers")
                InfoBadgeLine(label = "LATENCY", value = "< 24 Hours Response")
            }
        }
    }
}

@Composable
private fun InteractiveFormCard(
    colors: PortfolioColors,
    spacing: Spacing,
    nameInput: String,
    emailInput: String,
    messageInput: String,
    selectedIntent: String,
    isSending: Boolean,
    sendStatus: String?,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onMessageChange: (String) -> Unit,
    onIntentSelect: (String) -> Unit,
    onSubmit: () -> Unit
) {
    GlassCard {
        // Window Title Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.surfaceContainerHighest)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(8.dp).background(Color(0xFFFF5F56), CircleShape))
            Box(modifier = Modifier.size(8.dp).background(Color(0xFFFFBD2E), CircleShape))
            Box(modifier = Modifier.size(8.dp).background(Color(0xFF27C93F), CircleShape))
            Spacer(Modifier.width(8.dp))
            Text(
                text = "message_composer.sh",
                style = MaterialTheme.typography.labelSmall,
                color = colors.textSecondary.copy(alpha = 0.5f),
                fontFamily = FontFamily.Monospace
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.large),
            verticalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            Text(
                text = "guest@osawant:~$ ./select_intent --list",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.accent,
                fontFamily = FontFamily.Monospace
            )

            // Intent Buttons segment selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf("Hire Me", "Collab", "Say Hello").forEach { intent ->
                    val isSelected = selectedIntent == intent
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) colors.accent.copy(alpha = 0.15f) else Color(0xFF141C16))
                            .border(
                                width = 1.dp,
                                color = if (isSelected) colors.accent else colors.border.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable(enabled = !isSending) { onIntentSelect(intent) }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = intent.uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isSelected) colors.accent else colors.textSecondary,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(Modifier.height(4.dp))

            OutlinedTextField(
                value = nameInput,
                onValueChange = onNameChange,
                enabled = !isSending,
                label = { Text("Your Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.accent,
                    unfocusedBorderColor = colors.border.copy(alpha = 0.3f),
                    focusedLabelColor = colors.accent,
                    unfocusedLabelColor = colors.textSecondary.copy(alpha = 0.6f),
                    focusedTextColor = colors.textPrimary,
                    unfocusedTextColor = colors.textPrimary
                )
            )

            OutlinedTextField(
                value = emailInput,
                onValueChange = onEmailChange,
                enabled = !isSending,
                label = { Text("Your Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.accent,
                    unfocusedBorderColor = colors.border.copy(alpha = 0.3f),
                    focusedLabelColor = colors.accent,
                    unfocusedLabelColor = colors.textSecondary.copy(alpha = 0.6f),
                    focusedTextColor = colors.textPrimary,
                    unfocusedTextColor = colors.textPrimary
                )
            )

            OutlinedTextField(
                value = messageInput,
                onValueChange = onMessageChange,
                enabled = !isSending,
                label = { Text("Brief description of your project or idea...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                maxLines = 6,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.accent,
                    unfocusedBorderColor = colors.border.copy(alpha = 0.3f),
                    focusedLabelColor = colors.accent,
                    unfocusedLabelColor = colors.textSecondary.copy(alpha = 0.6f),
                    focusedTextColor = colors.textPrimary,
                    unfocusedTextColor = colors.textPrimary
                )
            )

            // Submit Button or Status message
            if (sendStatus == "success") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(colors.accent.copy(alpha = 0.12f))
                        .border(1.dp, colors.accent, RoundedCornerShape(6.dp))
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "SUCCESS: Message sent directly from console!",
                        color = colors.accent,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (sendStatus == "error") {
                        Text(
                            text = "ERROR: Failed to deliver directly. Falling back to email client...",
                            color = Color(0xFFFF5F56),
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    GradientButton(
                        text = if (isSending) "transmitting..." else "compile_and_send",
                        onClick = onSubmit,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoBadgeLine(label: String, value: String) {
    val colors = PortfolioTheme.colors
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = colors.textSecondary.copy(alpha = 0.5f),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = colors.textPrimary,
            fontWeight = FontWeight.SemiBold
        )
    }
}
