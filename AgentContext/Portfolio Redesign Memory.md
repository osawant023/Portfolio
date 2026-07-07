# Portfolio Project Overview & Redesign Memory

**Date:** 2026-07-07
**Project:** Cross-Platform Developer Portfolio
**Developer:** Omkar Sawant
**Target Platforms:** Web (Kotlin/Wasm-JS), Android, iOS (Compose Multiplatform)
**Design Source:** Stitch Design System — "Obsidian Console Portfolio" (Projects, Skills, Timeline, Dashboard screens)

---

## 🛠️ Technology Stack
- **Framework:** Jetpack Compose Multiplatform (CMP) / Kotlin Multiplatform (KMP) 1.9.3
- **Programming Language:** Kotlin
- **Build System:** Gradle (Kotlin DSL) with JDK 21
- **UI Architecture:** Custom Theme utilizing Material 3 foundation components + Stitch design tokens
- **Routing/Navigation:** Modular Single Page Layout via scrollable LazyColumn
- **Wasm Runtime:** Kotlin/Wasm compiler toolchain deploying to web

---

## 📂 Project Architecture & Codebase Structure

```
Portfolio/
├── composeApp/
│   └── src/
│       └── commonMain/
│           └── kotlin/
│               └── com/app/
│                   ├── core/
│                   │   └── Util.kt              # Core utilities
│                   ├── data/
│                   │   └── PortfolioData.kt     # Typed models and static developer profiles
│                   ├── portfolio/
│                   │   └── App.kt               # Main entry point (calls PortfolioScreen)
│                   └── ui/
│                       ├── components/
│                       │   └── Components.kt    # GlassCard, LabelCapsText, LabelMonoText, SkillChip, StatValue, etc.
│                       ├── design/
│                       │   ├── PortfolioColors.kt # Mint-green semantic color tokens
│                       │   └── Spacing.kt         # Layout spacing tokens (4dp-80dp)
│                       ├── sections/
│                       │   ├── HeroSection.kt     # Magazine-style hero with typewriter & radial dot-grid
│                       │   ├── AboutSection.kt    # Bio card, stat cards, location/contact info
│                       │   ├── SkillsSection.kt   # Bento grid: Languages, Frameworks, Architecture, Tools + Certifications
│                       │   ├── ExperienceSection.kt # Pulse-animated timeline with right-side stats column
│                       │   ├── ProjectsSection.kt   # Bento grid: full-width + half-width + stats + next-up
│                       │   ├── EducationSection.kt  # Degree cards with icon
│                       │   ├── ContactSection.kt    # CTA card + contact rows (email, linkedin, github, phone)
│                       │   └── FooterSection.kt     # Copyright, social links
│                       ├── theme/
│                       │   ├── Color.kt         # Mint-green palette constants (#5FDE8E, #80FFAB, #7CFBA8)
│                       │   ├── Shapes.kt        # Rounded corner shape tokens
│                       │   ├── Theme.kt         # CompositionLocal mapping for PortfolioTheme
│                       │   ├── Type.kt          # General text styles
│                       │   └── Typography.kt    # Stitch font sizes (display-lg 72sp → label-sm 11sp)
│                       └── PortfolioScreen.kt   # LazyColumn container with sticky nav bar
├── stitch_assets/
│   ├── html/                                      # Stitch screen HTML (dashboard, projects, skills, timeline)
│   └── images/                                    # Stitch screen screenshots
└── docs/                                          # Wasm JS build output
```

---

## 🎨 Redesign Visual Specifications (Stitch "Obsidian Console Portfolio")

- **Color Theme:** Dark-only with Mint-green accent palette.
  - Background: `#0E1510` (dark green-black)
  - Accent: `#5FDE8E` (primary-fixed-dim, mint)
  - Accent glow: `#80FFAB`, `#7CFBA8` (mint-accent, primary-fixed)
  - Surface container: `#1A211B`, `#252C26`, `#303630`
  - Text primary: `#DDE4DB`, Text secondary: `#BCCABC`
  - Glass card: `rgba(48,54,48,0.6)` with `rgba(255,255,255,0.1)` border
  - Timeline line: `#3D4A3F`

- **Typography (Stitch Design System):**
  - Display/Headline: Plus Jakarta Sans (sans-serif, extrabold/bold)
    - display-lg: 72sp, -0.04em tracking
    - headline-lg: 40sp, headline-md: 32sp
  - Body: Inter (sans-serif, normal weight)
    - body-lg: 18sp, body-md: 16sp, line-height 1.6
  - Label Mono: JetBrains Mono (monospace, medium 500)
    - 14sp, 0.05em tracking
  - Label Caps: Plus Jakarta Sans (extrabold 800, uppercase)
    - 12sp, 0.1em tracking (*Note: custom fonts not bundled; uses FontFamily fallbacks*)

- **Key Design Elements:**
  - **GlassCard:** Frosted glass effect via `rgba(48,54,48,0.6)` background + 1px border, 8dp rounded corners
  - **Bento Grid:** Multi-column layouts using Row/Column weight composition (CSS grid unavailable in Compose)
  - **Pulse Timeline:** Animated dot nodes with 2s pulse cycle, number indicators (01, 02, 03), right-side stats column
  - **LabelCapsText:** Uppercase, 12sp, extrabold, 0.1em letter spacing
  - **LabelMonoText:** JetBrains Mono style, 14sp, medium weight
  - **Magazine Hero:** Large display text with mint accent glow, subtitle with left accent border
  - **Monochromatic Mesh:** Dot grid background pattern on hero section (drawBehind)
  - **SkillChip:** label-caps style chips with 2dp rounded corners, surface-container-highest background

- **Section Architecture (in LazyColumn order):**
  1. **HeroSection** — Name with typewriter effect, role, tagline, CTA buttons, stats row, scroll indicator
  2. **AboutSection** — Bio glass card, stat cards (3 items), location/availability card
  3. **SkillsSection** — Bento grid: Languages, Frameworks, Architecture (dot-dash), Tools + Certifications card + full skills cloud
  4. **ExperienceSection** — Pulse timeline (3 roles) with right-side: experience level, core stack, decorative tile
  5. **ProjectsSection** — Bento grid: full-width card + half-width card + stats tile + next-up section
  6. **EducationSection** — Degree cards with school icon
  7. **ContactSection** — CTA glass card + contact rows (email, LinkedIn, GitHub, phone)
  8. **FooterSection** — Copyright + social links (GITHUB, LINKEDIN, EMAIL)

---

## 📱 Mobile Responsiveness Optimization Memory

To make the magazine-style console design look pristine on mobile viewports (e.g. `< 768.dp`), several key refactoring steps were performed to handle unconstrained horizontal layout clipping:

### 1. HeroSection.kt
- Reduced top margin padding to `16.dp` and changed desktop container Row vertical alignment to `Alignment.Top` to keep headers pinned closely to the top.
- Inserted symmetric vertical spacing (`32.dp` Spacers) above and below the profile picture to center it vertically.
- Increased the mobile profile picture circle size from `160.dp` to `200.dp`.
- Added vertical spacing (`48.dp` on mobile / `80.dp` on desktop) between the header block and main content to prevent overlaps.

### 2. AboutSection.kt
- Converted the horizontal Row of 4 metric cards into a **2x2 Grid** on mobile viewports to prevent squishing and horizontal character wrapping.
- Stacked the "Current Base" location information and "Available for..." metadata block vertically on mobile screens.

### 3. SkillsSection.kt & ExperienceSection.kt
- Changed the horizontal tech stack tag layout in timeline items from a horizontal `Row` to a wrapping `FlowRow` to ensure tags wrap on small screens rather than stretching text columns vertically.

### 4. ProjectsSection.kt
- Refactored the projects grid to display a **Staggered Grid Layout** (even/odd indices split in side-by-side vertical columns) on desktop and a single column vertical stack on mobile.
- Set both horizontal and vertical gap spacing to a uniform `24.dp`.
- Stacked the impact metrics card and "Next Up" card vertically on mobile screens.

### 5. ContactSection.kt & FooterSection.kt
- Grouped the four Quick Connect social buttons (Email, LinkedIn, GitHub, Call) into a **2x2 grid** on mobile viewports.
- Restructured the footer layout to center and stack copyrights and social links vertically on mobile screens, avoiding text clip.

---

## 🔒 Build & Deployment

### ✅ Successful Builds
- **Wasm JS:** `./gradlew composeApp:compileKotlinWasmJs` — compiles successfully
- **Development server:** `./gradlew composeApp:wasmJsBrowserDevelopmentRun`

### ❌ Known Issues
- **Android target** (`./gradlew :composeApp:compileDebugKotlinAndroid`) — fails due to pre-existing AGP/environment issue, unrelated to code changes
- **Custom fonts** (Plus Jakarta Sans, Inter, JetBrains Mono) — not bundled; current code uses `FontFamily.SansSerif`/`Monospace` as fallback
- **Stitch Design System asset** (ID `asset-stub-assets_7f28bf0faf8f49eba1786dcbeb5879c9`) — returned invalid argument error
