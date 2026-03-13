package com.app.data

// ============================================================
// Data Models
// ============================================================
data class Experience(
    val company: String,
    val location: String,
    val role: String,
    val period: String,
    val highlights: List<String>,
    val isCurrent: Boolean = false
)

data class Project(
    val title: String,
    val description: String,
    val bullets: List<String>,
    val techStack: List<String>,
    val playStoreUrl: String? = null
)

data class Education(
    val institution: String,
    val degree: String,
    val period: String,
    val location: String
)

data class Certification(
    val title: String,
    val issuer: String,
    val credentialUrl: String? = null
)

// ============================================================
// Resume Content
// ============================================================
object PortfolioData {

    val name = "Omkar Sawant"
    val role = "Senior Android Developer"
    val location = "Mumbai, India"
    val phone = "8779985574"
    val email = "osawant023@gmail.com" // placeholder
    val linkedIn = "Omkar Sawant"
    val linkedInUrl = "https://www.linkedin.com/in/omkarsawant2103"
    val github = "osawant023"
    val githubUrl = "https://www.github.com/osawant023"
    val portfolio = "Portfolio"
    val yearsOfExperience = "6+"

    val heroTagline = "Building high-quality Android apps with Kotlin & Jetpack Compose"
    val aboutSummary = """
        Senior Android Developer with $yearsOfExperience years of experience building and shipping 
        high-quality Android apps using Kotlin, Java, Jetpack Compose, and modern architecture patterns 
        (MVVM/MVI). Known for improving team performance through reusable components, clear documentation, 
        and mentoring, while partnering closely with product, design, and engineering to deliver reliable, 
        user-friendly experiences.
    """.trimIndent()

    val aboutStats = listOf(
        Pair("6+", "Years Experience"),
        Pair("10+", "Apps Shipped"),
        Pair("4", "Devs Mentored"),
        Pair("15%", "UX Score ↑")
    )

    val skills = listOf(
        // Languages
        "Kotlin", "Java",
        // UI
        "Jetpack Compose", "Material 3", "XML Layouts",
        // Architecture
        "MVVM", "MVI", "SOLID", "Hexagonal",
        // Networking
        "REST APIs", "GraphQL", "Ktor", "Retrofit",
        // Storage
        "Room", "SQLite",
        // DI
        "Koin", "Hilt",
        // Firebase
        "Firebase", "FCM", "Crashlytics",
        // AR
        "ARCore", "SceneView",
        // Testing
        "Unit Testing", "Mockito",
        // Tools
        "Git", "CI/CD", "SonarQube", "ProGuard/R8",
        // Other
        "KMP/CMP", "SDK Development", "Coroutines", "Agile"
    )

    val skillCategories = listOf(
        Pair("Languages", listOf("Kotlin", "Java")),
        Pair("UI", listOf("Jetpack Compose", "Material 3", "XML Layouts", "Custom Animations")),
        Pair("Architecture", listOf("MVVM", "MVI", "SOLID", "Hexagonal", "Clean Architecture")),
        Pair("Networking", listOf("REST APIs", "GraphQL", "Ktor", "Retrofit")),
        Pair("Storage", listOf("Room", "SQLite", "Offline-First")),
        Pair("DI & Tools", listOf("Koin", "Hilt", "Git", "CI/CD", "SonarQube")),
        Pair("Firebase", listOf("Firebase Analytics", "FCM", "Crashlytics")),
        Pair("Advanced", listOf("ARCore", "SceneView", "KMP/CMP", "SDK Development", "Coroutines"))
    )

    val experiences = listOf(
        Experience(
            company = "Neosoft",
            location = "Mumbai",
            role = "Senior Software Engineer",
            period = "Jan 2025 – Present",
            isCurrent = true,
            highlights = listOf(
                "Developed Link QR Merchant and Create Insurance features for Suryoday Finance",
                "Conducted ARCore R&D to build AR \"Try-On\" feature (glasses, lipstick, eyelashes)",
                "Worked on EMS project using KMP/CMP for cross-platform support",
                "Developed UPI Plugin SDK for BillDesk under Hatio",
                "Integrated NPCI Common Library: Change UPI PIN, UPI payments, balance checks",
                "Led project architecture, dependency injection, and mentored developers",
                "Drove code refactoring and rigorous code reviews"
            )
        ),
        Experience(
            company = "Inovant Solutions",
            location = "Navi Mumbai",
            role = "Senior Android Developer",
            period = "Jul 2021 – Jan 2025",
            highlights = listOf(
                "Implemented MVVM architecture improving maintainability and separation of concerns",
                "Built reusable custom components to reduce development time and speed up iteration",
                "Created custom animations and transitions enhancing user experience",
                "Maintained Android documentation, reducing onboarding time for new members by 50%",
                "Mentored 4 junior developers, improving team productivity and delivery",
                "Collaborated with product & design, increasing user satisfaction scores by 15%",
                "Shipped 7+ greenfield projects across e-commerce, healthcare, and other domains",
                "Published apps: Mawada, The Curl Nation, PIQR, Salamtek on Google Play"
            )
        ),
        Experience(
            company = "Sudesi Infotech",
            location = "Navi Mumbai",
            role = "Android Developer",
            period = "Jul 2019 – Jul 2021",
            highlights = listOf(
                "Collaborated in a cross-functional team to deliver Android application features",
                "Implemented document scanning via camera using Google ML Kit",
                "Built auto-update feature triggered on user login",
                "Managed local data storage using SQLite",
                "Integrated REST and SOAP APIs with complex JSON parsing",
                "Projects: IDBI Insurance, LOTUS, HAFELE"
            )
        )
    )

    val projects = listOf(
        Project(
            title = "Hatio / BillDesk — UPI Plugin SDK",
            description = "A production-ready UPI SDK enabling payments, PIN management, and balance checks for third-party apps.",
            bullets = listOf(
                "Developed UI screens using Jetpack Compose with MVI architecture",
                "Integrated NPCI Common Library: balance check, MPIN reset, UPI PIN change",
                "Built transaction history and transaction details modules",
                "Improved code quality through refactoring and SonarQube compliance",
                "Implemented REST APIs using Ktor with centralized CL object initialization"
            ),
            techStack = listOf("Kotlin", "Jetpack Compose", "MVI", "Hexagonal", "Ktor", "Koin", "SDK")
        ),
        Project(
            title = "TATA CLIQ — AR Try-On",
            description = "AR-based virtual try-on experience for fashion products using ARCore on the TATA CLIQ platform.",
            bullets = listOf(
                "Led R&D to migrate from Sceneform to SceneView for better AR support",
                "Architected scalable try-on module for simultaneous AR experiences",
                "Supported try-on for sunglasses, lipstick, eyelashes, and blush",
                "Enhanced virtual preview realism and real-time rendering performance"
            ),
            techStack = listOf("Kotlin", "Jetpack Compose", "MVVM", "ARCore", "SceneView", "Hexagonal")
        ),
        Project(
            title = "Salamtek",
            description = "Healthcare super-app with doctor consultations, lab tests, pharmacy, and video appointments.",
            bullets = listOf(
                "Led architecture applying SOLID principles and MVVM",
                "Integrated Twilio SDK for real-time video consultations",
                "Implemented offline-first support using Room with background sync",
                "Integrated Firebase analytics, crash reporting, and push notifications",
                "Managed complete release lifecycle including Play Store deployment"
            ),
            techStack = listOf("Kotlin", "Jetpack Compose", "MVVM", "Room", "Twilio", "FCM", "Retrofit", "GoogleMaps"),
            playStoreUrl = "https://play.google.com/store/apps/details?id=com.app.salamtek"
        ),
        Project(
            title = "Mawada",
            description = "E-commerce app with rich cart, checkout, gifting, and personalized order experiences.",
            bullets = listOf(
                "Architected end-to-end purchase and order journey",
                "Implemented split payments and secure payment gateway integration",
                "Built customizable gifting with media-rich card notes (text/image/video)",
                "Integrated ExoPlayer for in-app media playback"
            ),
            techStack = listOf("Kotlin", "MVVM", "Room", "Firebase", "GoogleMaps", "ExoPlayer", "REST"),
            playStoreUrl = "https://play.google.com/store/apps/details?id=com.app.mawada"
        ),
        Project(
            title = "PIQR",
            description = "Event booking platform with real-time seat selection and access-controlled private events.",
            bullets = listOf(
                "Developed public & private event creation with invite management",
                "Built BookMyShow-style real-time seat selection system",
                "Integrated end-to-end payment flow and order management",
                "Managed full QA validation and Play Store release cycle"
            ),
            techStack = listOf("Kotlin", "MVVM", "Room", "Firebase", "GoogleMaps", "ExoPlayer"),
            playStoreUrl = "https://play.google.com/store/apps/details?id=com.app.piqr"
        )
    )

    val education = listOf(
        Education(
            institution = "Navinchandra Mehta Institute of Technology & Development",
            degree = "Bachelor of Computer Applications (BCA)",
            period = "Jul 2016 – May 2019",
            location = "Dadar, Mumbai"
        )
    )

    val certifications = listOf(
        Certification(
            title = "Kotlin Multiplatform Masterclass",
            issuer = "KMP, KMM — Android, iOS",
            credentialUrl = "https://www.udemy.com/certificate/UC-75d8a833-3544-41f1-a32f-a14bf7943e60/"
        )
    )
}
