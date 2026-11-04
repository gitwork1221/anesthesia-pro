# ANESTHESIA PRO — Clinical Education & Reference Platform

## Overview
**ANESTHESIA PRO** is a native Android application built with Kotlin, Jetpack Compose, and Material 3. It serves as an offline-first educational curriculum, clinical pharmacology reference, perioperative surgical guide, equipment and monitoring manual, and clinical calculator suite for anesthesia students (Nurse Anesthesia & Physician Anesthesiology tracks) and practicing professionals.

## Core Features Implemented

### 1. Dual Learning Pathways & Training Levels
- **Nurse Anesthesia Pathway**: Tailored focus on perioperative vigilance, practical airway management, and nursing anesthesia considerations.
- **Physician Anesthesiology Pathway**: Consultative physiology, perioperative medicine, advanced critical care, and invasive monitoring.
- **Training Levels**: Year 1 (Foundational Sciences), Year 2 (Clinical Foundations), Year 3 (Advanced Specialties), Resident / Registrar, Practicing Clinician.

### 2. High-Yield Educational Curriculum
- Structured modules spanning Foundational Sciences, Applied Physiology, Anesthesia Foundations, Specialty Anesthesia, and Crisis Management.
- Systematic lesson reader with:
  - Clear learning objectives & prerequisites
  - Beginner conceptual overviews
  - Cellular, receptor, and physiological mechanisms
  - Step-by-step clinical protocols
  - Common errors and misconceptions highlighted in alert styling
  - High-yield examination points
  - Real-world clinical case studies
  - Authoritative medical references (ASA, DAS, AAGBI, MHAUS)
  - Interactive font-scaling toggle (Normal, Large, Extra Large)
  - Lesson completion tracking and bookmarking

### 3. Comprehensive Pharmacology Reference
- Detailed monographs for induction agents, neuromuscular blockers, reversals, local anesthetics, opioids, and vasoactive drugs:
  - **Propofol**, **Ketamine**, **Etomidate**, **Succinylcholine**, **Rocuronium**, **Sugammadex**, **Neostigmine**, **Bupivacaine**, **Lidocaine**, **Fentanyl**, **Ephedrine**, **Phenylephrine**, **Epinephrine**, **Tranexamic Acid**.
- Exact receptor targets, adult & pediatric dosing, onset/duration, metabolism, contraindications, cautions, reversal agents, and practical clinical pearls.

### 4. Applied Anatomy & Physiology Module
- Labelled medical illustration of the sagittal human airway.
- Anatomical landmarks: Pharynx, Vallecula, Epiglottis, Vocal Cords, Cricoid Cartilage, Trachea, Carina, and Neuraxial Ligaments.
- Clinical significance: 3-3-2 rule, sniffing position alignment, Sellick's maneuver, and pediatric vs adult anatomical differences.

### 5. Surgical Anesthesia Guides
- Protocols for **Cesarean Delivery**, **Emergency Laparotomy / Appendectomy**, and **Femoral Neck Fracture Repair**.
- Covering preoperative evaluation, anesthetic selection (General vs Neuraxial), airway plans, positioning & nerve safety, hemodynamics, analgesia, and PACU recovery.

### 6. Anesthesia Equipment & Monitoring
- In-depth modules on **Anesthesia Workstation Physics** (high, intermediate, and low pressure systems; fail-safe valves, oxygen flush hazard prevention, proportioning systems).
- **Capnography Interpretation**: Waveform analysis (normal Phase I-IV, bronchospasm shark fin, curare cleft, rebreathing, cardiac arrest).
- **Airway Devices Comparison**: Supraglottic Airways (LMA Classic / ProSeal / Supreme) vs Cuffed Endotracheal Tubes.

### 7. Deterministic Clinical Calculators
1. **BMI, Ideal Body Weight (Devine Formula), and Adjusted Body Weight**
2. **Mean Arterial Pressure (MAP)** with perfusion status warnings (< 60 mmHg)
3. **Estimated Blood Volume (EBV) & Maximum Allowable Blood Loss (MABL)**
4. **Pediatric ETT Size & Insertion Depth** (Khine & Motoyama cuffed/uncuffed formulas)
5. **Local Anesthetic Maximum Safe Dose & Volume** with LAST alerts & 20% Lipid Emulsion dosages
6. **Maintenance IV Fluid Rate (4-2-1 Rule / Holliday-Segar)**
7. **Oxygen Cylinder (E-Tank) Remaining Duration** (factor 0.28 L/psi with low-pressure warnings)
8. **Serum Anion Gap & Acidosis Classification** (HAGMA vs Normal AG / HARDUP differentials)

### 8. Interactive Clinical Crisis Simulator
- Staged branching scenarios with digital OR vitals telemetry:
  - **Case 1: Severe Post-Spinal Hypotension in Emergency Cesarean**
  - **Case 2: Cannot Intubate, Cannot Oxygenate (CICO) Airway Crisis**
- Real-time physiological feedback, consequences of unsafe decisions, and structured debriefing.

### 9. Multiple-Choice Examination Practice
- Board-style clinical MCQs with single best answers and in-depth rationales for correct and incorrect answers.
- Score tracking and examination review.

### 10. Local Persistence & Offline-First Storage
- SQLite backed by **Room Database**:
  - `BookmarkEntity`: Instant offline bookmarking
  - `UserNoteEntity`: Personal clinical notes tied to lessons or topics
  - `UserPreferenceEntity`: Learning pathway, study streak, and completed lessons
- Zero internet or cloud backend dependency.

## Build & Tech Stack
- **Language**: Kotlin 2.2.10
- **UI Framework**: Jetpack Compose with Material Design 3 (M3)
- **Local Persistence**: Room Database (`androidx.room`) with KSP
- **Architecture**: MVVM with Repository pattern and StateFlow
- **Theme**: Medical Dark Theme (Obsidian `#090D16`, Slate Navy `#0F172A`, Surgical Cyan `#00E5FF`, Clinical Teal `#14B8A6`)
- **Compatibility**: Android SDK 24+ (Android 7.0 through Android 15/16)
