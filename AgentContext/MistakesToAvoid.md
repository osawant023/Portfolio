# Mistakes to Avoid in Future Compose Multiplatform Development

This document outlines key layout, compiler, and integration pitfalls encountered during the redesign of this portfolio, along with the correct strategies to prevent them.

---

## 1. Composable Compiler Constraints

### ❌ The Mistake: Nesting `try-catch` Around Composable Calls
Placing a `try-catch` block directly around a Composable function call (e.g., trying to catch crashes on rendering) will trigger a compiler error:
`Try catch is not supported around composable function invocations`.

### ✅ The Solution
- Never wrap Composable function invocations in `try-catch` blocks.
- Perform all error-prone operations (such as APIs, database calls, or state parsing) outside the UI render loop.
- Use state flow handlers, `LaunchedEffect` exceptions, or handle try-catches within event callbacks (e.g., `onClick`).

---

## 2. Layout Nesting & Constraints

### ❌ The Mistake: Lazy Layouts Inside Scrollable Containers
Placing a lazy layout (like `LazyColumn` or `LazyVerticalStaggeredGrid`) inside a parent container that is already vertically scrollable (like a `Column` with `.verticalScroll(scrollState)`) causes a crash:
`Nest scroll is not supported for infinite height`.

### ✅ The Solution
- For single-scroll screens, keep the main layout inside a scrollable container, but render child lists dynamically using simple `forEach` loops instead of `Lazy` layouts.
- To achieve a **Staggered Grid** inside a scrollable screen: split the list into two lists (even indices / odd indices) and place them side-by-side inside a `Row` containing two vertical `Columns` with `Modifier.weight(1f)` each.

---

## 3. Dynamic Tag Lists & Chips

### ❌ The Mistake: Horizontal `Row` for Unknown/Dynamic Chip Lists
Using a horizontal `Row` to display tag lists (like skill badges, tech stack highlights, or categories) will result in layout clipping or vertical distortion (squeezing words to single-letter vertical text columns) when the items overflow the screen width.

### ✅ The Solution
- Always wrap dynamic tag or chip layouts in a **`FlowRow`** (imported from `androidx.compose.foundation.layout.FlowRow`).
- This allows cards and chips to reflow and wrap to the next line automatically.

---

## 4. Mobile Viewport Reflows (Grid Cards & Buttons)

### ❌ The Mistake: Packing Multiple Items Horizontally Without Screen Queries
Relying on a single horizontal layout for cards or buttons (such as metric cards, social links, or quick-connect bars) squeezes buttons to unusable sizes on mobile screens.

### ✅ The Solution
- Use `BoxWithConstraints` to detect screen size dynamically.
- For 4-element groups: split them into a **2x2 grid** on mobile viewports (`maxWidth < 600.dp` or `700.dp`) using `.chunked(2)` and nested `Row`s.
- For wider cards: stack them vertically in a `Column` with `.fillMaxWidth()` on mobile, and display them side-by-side using `Row` and `.weight(1f)` on desktop.

---

## 5. Visual Spacing & Margins

### ❌ The Mistake: Inconsistent Grid Spacing
Setting vertical spacing between grid rows separate from horizontal spacing between cards can lead to asymmetric gutters, making cards look closer together vertically than they are horizontally.

### ✅ The Solution
- Set both `verticalArrangement = Arrangement.spacedBy(D)` and `horizontalArrangement = Arrangement.spacedBy(D)` to the same value `D` (e.g., `24.dp`) when defining grids and staggered layouts to maintain perfect symmetry.

---

## 6. API Forms & External Integrations

### ❌ The Mistake: Launching System Mail Clients When API Keys Are Present
Triggering default system mail client links (`mailto:`) instead of calling the configured API gateway (like Web3Forms) leads to a bad user experience.

### ✅ The Solution
- Explicitly check for the presence of target API keys (e.g., `web3FormsAccessKey`).
- Execute a direct HTTP network post request inside a coroutine if the key exists, and fallback to `mailto:` only if the key is missing or blank.
- Make sure parameters match the exact API requirements (e.g. naming the payload body parameter correctly when building JS `RequestInit` arguments).
