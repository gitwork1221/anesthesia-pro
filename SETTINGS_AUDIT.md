# ANESTHESIA PRO — ADVANCED SETTINGS SYSTEM AUDIT

**Version:** 1.5.0  
**Target Platform:** Native Android (Kotlin + Jetpack Compose + Material 3)  
**Status:** FULLY IMPLEMENTED & INTEGRATED

---

## 1. Executive Summary

The Advanced Settings System in ANESTHESIA PRO provides an integrated, fully functional configuration hub for Ethiopian anesthesia trainees, clinical officers, and instructors. It provides deep personalization across appearance, typography, educational curriculum, study reminders, device storage auditing, learning data governance, deterministic algorithm disclosure, and academic citations.

All preferences are persisted across app restarts via the local Room SQLite database (`app_preferences` table in `AnesthesiaDatabase`), ensuring zero loss of state and 100% offline functionality.

---

## 2. Seven Specialized Settings Categories

### Category 1: Appearance & Accessibility
* **Theme Selection**:
  * **Clinical Dark** (Default): High-contrast slate and dark navy backgrounds (`#0B0F19`, `#111827`) optimized for low-light clinical operating theaters, PACU, and night study.
  * **Medical Light**: Clean clinical light palette (`#F8FAFC`, `#FFFFFF`, `#0F172A`) for bright daytime classroom study.
  * **System Default**: Automatically reacts to device-level system dark/light mode via Jetpack Compose `isSystemInDarkTheme()`.
* **Font Scaling**:
  * **Small (85%)**: High-density screen layout for comprehensive monograph review.
  * **Standard (100%)**: Default Material 3 typography.
  * **Large (115%)**: Enhanced readability for bedside/clinical checks.
  * **Extra Large (130%)**: Maximum accessibility for visual clarity.
* **Line Spacing Multiplier**:
  * **Compact (1.15x)**: Maximizes screen content density.
  * **Standard (1.35x)**: Balanced pedagogical readability.
  * **Relaxed (1.60x)**: Extended reading comfort for textbooks and long lessons.
* **High Contrast Text & Borders**: Toggle switch providing prominent borders and high-contrast text layers.
* **Language Disclosure**: English (Clinical Standard). Honest notice that Afaan Oromoo and Amharic terminology dictionaries are currently awaiting official curricular harmonization.

### Category 2: Learning Preferences
* **Primary Curriculum Pathway**: Permanently anchored to the *Ethiopian National Harmonized Modular Curriculum for Generic BSc in Anesthesia (February 2021)*.
* **Academic Year Focus**: Quick-filter by Year I (Pre-Clinical Foundations), Year II (Principles & Basic Sciences), Year III (Specialty Anesthesia & Critical Care), or Year IV (Comprehensive Clinical Internship).
* **Explanation Acuity Level**:
  * **Beginner**: Emphasizes physiological foundations, definitions, and standard normal ranges.
  * **Intermediate**: Balanced clinical reasoning, mechanism of action, and perioperative management.
  * **Advanced**: High-acuity crisis algorithms, hemodynamic titration, and board-level exam nuances.
* **MCQ Practice Difficulty**:
  * **Easy**: Recall and foundational knowledge.
  * **Moderate**: Typical Ethiopian BSc licensing board difficulty.
  * **Hard**: Multi-step clinical vignettes and crisis troubleshooting.
  * **Mixed**: Realistic board simulation with distributed difficulty.
* **Default Question Set Size**: Configurable slider/stepper (5, 10, 15, 20, 30, or 50 questions per exam session).
* **Immediate Rationale Toggle**: Shows full clinical explanation and textbook citations immediately upon option selection or only at exam completion.
* **Learning Objectives Display Toggle**: Displays or collapses formal competencies at the beginning of lesson monographs.

### Category 3: Study Reminders
* **Daily Reminder Toggle**: Enables or disables scheduled study prompts.
* **Reminder Time Selector**: Interactive 24-hour hour/minute picker with preset shortcuts (06:00, 12:00, 18:00, 20:00, 21:00).
* **Active Days Selection**: Multi-select chip group for all 7 days of the week (Mon, Tue, Wed, Thu, Fri, Sat, Sun).
* **Local On-Device Notification Engine**: Strictly relies on local device alarm/notification APIs (`POST_NOTIFICATIONS`), requiring zero cloud servers or remote tracking.

### Category 4: Offline Content & Storage Management
* **Real Device Storage Audit**:
  * Computes actual byte sizes directly from the device's filesystem (`context.filesDir`, `context.cacheDir`, and SQLite database files).
  * Converts real bytes into human-readable format (`KB`, `MB`).
* **Pre-Bundled Clinical Inventory**:
  * 10 Ethiopian BSc Curriculum Modules.
  * 24 Comprehensive Step-by-Step Lessons.
  * 9 Anatomy & Physiology Topics with Interactive Canvas Diagrams & Visual Letterbox Coordinate Mapping.
  * 30+ Pharmacology Monographs with Reversal Protocols and Receptor Diagrams.
  * 12 Surgical Anesthesia Procedure Guides across 11 Surgical Specialties.
  * 10 Clinical Anesthesia Equipment Guides.
  * 10 Clinical Calculators with IBW/AdjBW formulas and ASRA dose limits.
  * 10 Interactive Simulation Scenarios & High-Yield MCQs.
* **Safe Cache Clearance**:
  * Dedicated button to safely wipe temporary cached bitmaps and ephemeral files in `cacheDir`.
  * **Zero Data Loss Guarantee**: Preserves databases, user bookmarks, notes, and preferences.

### Category 5: Learning Progress & Personal Data Governance
* **Real-Time Learner Summary**:
  * Completed lessons count and percentage.
  * Personal clinical notes count.
  * Bookmarked topics and pearls count.
  * Case simulations completed count.
  * Active study streak days.
* **Portfolio Export to Clipboard**:
  * Exports formatted clinical portfolio summary to the Android system clipboard for academic documentation or instructor review.
* **Decoupled Data Reset Operations**:
  * **Reset Preferences to Default**: Reverts appearance, typography, and question count back to initial defaults without deleting any user notes or lesson progress.
  * **Clear Notes Only**: Cleans personal notes while retaining bookmarks and progress.
  * **Clear Bookmarks Only**: Cleans saved bookmarks while retaining notes and progress.
  * **Reset All Progress**: Dedicated high-severity dialog requiring explicit user confirmation to reset lesson completion records.

### Category 6: AI Learning Preferences & Clinical Disclaimer
* **100% Deterministic Offline Logic**:
  * Transparent disclosure that ANESTHESIA PRO uses deterministic medical calculation algorithms, verified decision trees, and curated evidence-based question banks.
  * Zero generative hallucination risk: All clinical drug dosages, formulas, and emergency protocols are hardcoded from verified textbooks.
  * Zero external telemetry, zero tracking, zero remote transmission of student data.
* **Clinical Safety & Legal Disclaimer**:
  * Explicit declaration that the application is an educational aid designed for Ethiopian BSc students and anesthesia practitioners.
  * Clarifies that educational recommendations do not replace institutional hospital protocols, direct supervising consultant judgment, or individualized patient evaluation.

### Category 7: About, Curriculum Sources & Open-Source Licenses
* **Application Metadata**:
  * Version 1.5.0 (Build 2026.02).
  * Application ID: `com.aistudio.anesthesiapro.cmnxkd`.
* **Primary Curriculum Reference**:
  * Federal Democratic Republic of Ethiopia Ministry of Health (MoH) & Ministry of Science and Higher Education (MoSHE).
  * *National Harmonized Modular Curriculum for Generic Bachelor of Science in Anesthesia*, February 2021.
* **Core Textbook Sources**:
  * Morgan & Mikhail's *Clinical Anesthesiology* (7th Ed.).
  * Miller's *Anesthesia* (9th Ed.).
  * Stoelting's *Pharmacology & Physiology in Anesthetic Practice* (6th Ed.).
  * Barash, Cullen, and Stoelting's *Clinical Anesthesia* (9th Ed.).
  * WHO *Surgical Safety Checklist & Guidelines for Safe Surgery*.
* **Open-Source Licenses**:
  * Apache License 2.0 (Android Jetpack, Compose, Room, Kotlin Coroutines).

---

## 3. Architecture & File Structure

| File | Purpose | Line Count | Status |
|---|---|---|---|
| `data/model/SettingsModels.kt` | Data enums and structures for themes, typography, categories, and storage | 74 | Verified |
| `data/local/AnesthesiaDao.kt` | Database operations for preferences and batch clearing | 110 | Verified |
| `data/repository/AnesthesiaRepository.kt` | Key constants and data management for settings | 280 | Verified |
| `ui/viewmodel/SettingsHelper.kt` | Real storage calculation, cache clearance, and export formatting | 86 | Verified |
| `ui/viewmodel/AnesthesiaViewModel.kt` | StateFlows and actions for all settings categories | ~480 | Verified |
| `ui/screens/SettingsScreen.kt` | Primary Settings dashboard with 7 category cards and badges | 280 | Verified |
| `ui/screens/SettingsPreferenceDialogs.kt` | Dialogs for Appearance, Learning Preferences, and Study Reminders | 350 | Verified |
| `ui/screens/SettingsDataAndAboutDialogs.kt` | Dialogs for Storage, Progress & Data, AI Disclaimer, and About | 350 | Verified |
| `ui/theme/Color.kt` | Light and dark medical color definitions | ~100 | Verified |
| `ui/theme/Theme.kt` | Dynamic Theme reacting to `AppThemeMode` (System, Dark, Light) | 88 | Verified |
| `ui/components/CommonComponents.kt` | TopBar with settings action button | 340 | Verified |
| `MainActivity.kt` | Root theme provider and routing for `NavigationDestination.Settings` | 230 | Verified |
| `test/java/com/example/SettingsUnitTest.kt` | Automated test suite for settings models, calculations, and keys | 150 | Verified |

All files strictly comply with the sub-500 line rule.
