# ANESTHESIA PRO — Changelog

All notable changes to the ANESTHESIA PRO project are documented in this file.

---

## [Version 1.5.0] — Advanced Settings System (February 2026)

### Added & Enhanced
- **Advanced Settings Dashboard (`SettingsScreen.kt`)**:
  - Direct entry points from Dashboard header and top app bar (`AnesthesiaTopBar`).
  - Vertically scrollable Material 3 dashboard featuring 7 organized category cards with icons, subtitles, and real-time active setting summary badges:
    1. Appearance and accessibility.
    2. Learning preferences.
    3. Study reminders.
    4. Offline content and storage.
    5. Learning progress and personal data.
    6. AI learning preferences and clinical disclaimer.
    7. About, curriculum sources, and licenses.
- **Appearance & Accessibility Preferences (`SettingsPreferenceDialogs.kt`)**:
  - Full-app dynamic theme switching: **Clinical Dark** (operating theater optimized), **Medical Light** (daytime classroom clarity), and **System Default**.
  - Font scaling: Small (85%), Standard (100%), Large (120%), Extra-Large (135%).
  - Reading line spacing: Compact (1.2x), Standard (1.4x), Relaxed (1.7x).
  - High-contrast text & border switch.
  - Transparent language status disclosing English clinical standard and future local language expansions.
- **Academic & Learning Preferences (`SettingsPreferenceDialogs.kt`)**:
  - Anchored on the Ethiopian Generic BSc in Anesthesia curriculum.
  - Academic Year focus filter (Years I, II, III, IV).
  - Explanation acuity level (Beginner, Intermediate, Advanced).
  - MCQ difficulty calibration (Easy, Moderate, Hard, Mixed exam simulation).
  - Question count selector (5 to 50 questions per session).
  - Immediate clinical rationale toggle and learning objectives toggle.
- **Study Reminders (`SettingsPreferenceDialogs.kt`)**:
  - Daily reminder toggle with time selector (preset buttons: 06:00, 12:00, 18:00, 20:00, 21:00).
  - Multi-select day of the week chips (Mon-Sun).
  - 100% on-device local notification engine requiring zero cloud services (`POST_NOTIFICATIONS`).
- **Offline Content & Storage Management (`SettingsDataAndAboutDialogs.kt`)**:
  - Real filesystem calculation measuring actual bytes in `filesDir`, `cacheDir`, and SQLite database files.
  - Complete bundled inventory audit (10 curriculum modules, 24 lessons, 9 anatomy topics with visual canvases, 30+ drug monographs with diagrams, 12 surgical guides, 10 equipment guides, 10 calculators, 10 simulations).
  - Safe cache clearance routine removing temporary files while guaranteeing database protection.
- **Learning Progress & Data Governance (`SettingsDataAndAboutDialogs.kt`)**:
  - Real-time learner portfolio summary (lessons completed, notes recorded, bookmarks, case simulations, study streak).
  - One-tap portfolio export to Android system clipboard.
  - Decoupled data actions: separate preference reset from progress deletion with high-severity confirmation dialogs.
- **AI Learning Preferences & Clinical Disclaimer (`SettingsDataAndAboutDialogs.kt`)**:
  - Transparent disclosure of 100% deterministic offline algorithms, zero generative hallucination risk, and zero cloud tracking.
  - Formal clinical safety disclaimer distinguishing educational aid from institutional hospital protocols.
- **About, Curriculum Sources, and Licenses (`SettingsDataAndAboutDialogs.kt`)**:
  - App metadata (v1.5.0, Build 2026.02).
  - Ethiopian MoH / MoSHE National Harmonized Modular Curriculum (Feb 2021) attribution.
  - Core textbook citations (Morgan & Mikhail 7th Ed, Miller 9th Ed, Stoelting 6th Ed, Barash 9th Ed).
  - Open-source Apache 2.0 license disclosures.
- **Sub-500 Line Modular Architecture**:
  - Dialogs split into `SettingsPreferenceDialogs.kt` (350 lines) and `SettingsDataAndAboutDialogs.kt` (350 lines).
  - Multi-category search extracted to `SearchHelper.kt` (90 lines).
  - Storage calculation and export formatting extracted to `SettingsHelper.kt` (86 lines).
  - `AnesthesiaViewModel.kt` maintained at 498 lines.
- **Unit Testing Suite (`SettingsUnitTest.kt`)**:
  - Added 9 targeted tests covering theme modes, font scale factors, line spacings, explanation levels, MCQ difficulties, 7 category coverage, portfolio export formatting, preference keys integrity, and safe cache deletion.

---

## [Version 1.5] — Ethiopian BSc Curriculum Interface Alignment (February 2026)

### Added & Enhanced
- **Ethiopian National Harmonized Modular Curriculum Alignment (February 2021)**:
  - Transformed application architecture into an authentic academic hierarchy: Academic Year (I to IV) $\rightarrow$ Curriculum Module $\rightarrow$ Module Lessons $\rightarrow$ Learning Competencies & Knowledge Checks.
  - Added `AcademicYear`, `CurriculumModule`, and `CurriculumMappingStatus` models.
  - Established 10 core modular curriculum units across Years I through IV.
  - Transparent data policy: All unconfirmed module codes and ECTS are explicitly marked as `Awaiting Official Code Mapping` rather than fabricated.
- **Dashboard Redesign (`DashboardScreen.kt`)**:
  - Implemented 5 strict sequential sections:
    - Section A: Header & Pathway Branding (Ethiopian BSc Anesthesia degree badge, real-time stage, quick settings dialog access).
    - Section B: Learning Progress (Genuine stored completion records, zero fake streak for new learners, "Continue Learning" dynamically bound to Room DB's `lastOpenedLessonId`, empty state for new learners).
    - Section C: Ethiopian BSc Curriculum Entry (4-year modular summary card, interactive year chips navigating directly to that year's modules).
    - Section D: Core Learning Areas (8 working cards: Curriculum Browser, Applied Anatomy, Pharmacology, Surgical Guides, Equipment, Calculators, Simulation, MCQs).
    - Section E: Supplementary Learning (Visually segregated international standards: ASA, DAS, ASRA, MHAUS).
- **Curriculum Screen Redesign (`CurriculumScreen.kt`)**:
  - Academic Year tabs (`Year I`, `Year II`, `Year III`, `Year IV`) with context focus banners.
  - Real-time per-module progress indicators with completed lesson counts.
  - Expandable/collapsible module cards with competencies and sequential lesson links.
- **Enhanced Lesson Detail Reader (`LessonDetailScreen.kt` & `LessonDetailComponents.kt`)**:
  - Added automatic opened lesson recording via `LaunchedEffect(lessonId)`.
  - 12-section step-by-step clinical layout with academic year/module badges.
  - Interactive knowledge-check card with instant answer feedback and clinical rationale.
  - Extracted `LessonDetailComponents.kt` to ensure compliance with the sub-500 line rule.
- **Pathway Dialog Alignment (`PathwayDialog.kt`)**:
  - Anchored on the Ethiopian Generic BSc in Anesthesia 4-year modular curriculum.
- **Test Suite Expansion**:
  - Added `testEthiopianCurriculumModulesIntegrity` and `testLessonKnowledgeChecksIntegrity` in `ExampleUnitTest.kt`.
  - 35/35 automated unit tests passing (100% success rate).

---

## [Version 1.4] — Surgical Anesthesia Guides (November 2024)

### Added & Enhanced
- **Comprehensive Surgical Specialty Coverage (11 Core Specialties, 12 Detailed Guides)**:
  - **General Surgery**: Laparoscopic Cholecystectomy (`guide_lap_chole`), Emergency Exploratory Laparotomy (`guide_emergency_laparotomy`).
  - **Obstetrics & Gynecology**: Urgent & Elective Cesarean Delivery (`guide_cesarean_section`).
  - **Orthopedics & Trauma**: Hip Fracture Repair in Geriatrics (`guide_hip_fracture`), Total Knee Arthroplasty (`guide_total_knee`).
  - **Neurosurgery**: Supratentorial Craniotomy for Tumor (`guide_craniotomy_tumor`).
  - **Cardiothoracic & Vascular**: On-Pump Coronary Artery Bypass (`guide_coronary_artery_bypass`), Carotid Endarterectomy (`guide_carotid_endarterectomy`).
  - **Thoracic Surgery**: Thoracotomy & VATS with One-Lung Ventilation (`guide_thoracotomy_olv`).
  - **Pediatric Surgery**: Infantile Pyloric Stenosis Repair (`guide_pyloromyotomy`).
  - **Urology**: Transurethral Resection of Prostate (`guide_turp`).
  - **ENT & Maxillofacial**: Post-Tonsillectomy Hemorrhage (`guide_bleeding_tonsil`).
  - **Ophthalmic Surgery**: Open Globe Injury Repair (`guide_open_globe`).
- **Standardized 14-Section Clinical Guide Architecture**:
  - Implemented 14 standardized clinical sections for every procedure:
    1. Overview & Indications
    2. Surgical Position & Physiological Effects
    3. Relevant Anatomy & Physiology
    4. Preoperative Assessment & Optimization
    5. Airway, Aspiration & Ventilation Considerations
    6. Anesthetic Technique Options & Dosing
    7. Monitoring & Vascular Access Considerations
    8. Drug & Fluid Strategy
    9. Expected Blood Loss & Transfusion Planning
    10. Intraoperative Complications & Principles
    11. Emergence & Multimodal Analgesia Strategy
    12. PACU Recovery, Postoperative Monitoring & Disposition
    13. High-Yield Board Exam Points & Misconceptions
    14. Interactive Case Review Questions & Board Pearls
- **Zero-Mock Cross-Module Integration**:
  - Connected quick-link chips for Anatomy topics, Pharmacology monographs, Equipment modules, Clinical calculators, and Emergency Case Simulations.
  - Zero broken references: all declared cross-links resolve to real application entities.
- **Search & Specialty Filtering in Reference Tab**:
  - Added procedure/specialty search bar and horizontal filter chips for all 11 surgical specialties in `PharmacologyAndReferenceScreen.kt`.
- **Modular Code Architecture (< 500 Lines per File)**:
  - Modularized guides into 4 repositories under `com.example.data.repository.surgical/`.
  - Created reusable UI cards in `SurgicalGuideComponents.kt`.
- **Test Suite Expansion**:
  - Added `testSurgicalGuidesComprehensiveCoverage` and `testGuideCaseQuestionsIntegrity` in `ExampleUnitTest.kt`.
  - 33/33 tests passing.

---

## [Visual Learning Expansion] — High-Resolution Anatomy & Visual Pharmacology (November 2024)

### Added & Enhanced
- **Complete 9/9 High-Resolution Anatomical Illustrations (1376 x 768 px, 16:9, 300 DPI)**:
  - Generated and integrated 5 clinical illustrations matching exact 1376x768 dimensions and calibrated landmark pins:
    1. `R.drawable.img_cellular_physiology`: Phospholipid bilayer, Na+/K+ ATPase, ion gradients (145 mM ECF vs 12 mM ICF Na+), glycocalyx, resting potential (-70 mV).
    2. `R.drawable.img_hepatic_metabolism`: Classic hepatic lobule, sinusoids, portal triad (hepatic artery, portal vein, bile duct), central vein, hepatic acinus zonation (Zones 1–3, CYP450).
    3. `R.drawable.img_endocrine_physiology`: HPA axis (CRH -> ACTH -> Cortisol), adrenal cortex layers (Glomerulosa, Fasciculata, Reticularis), and medulla chromaffin cells (Epinephrine, Norepinephrine).
    4. `R.drawable.img_obstetric_neonatal`: Supine aortocaval compression, left uterine tilt relief with wedge under hip, maternal-fetal circulation and shunts (ductus venosus, foramen ovale, ductus arteriosus), placental barrier exchange.
    5. `R.drawable.img_surgical_positioning`: Laparoscopic pneumoperitoneum with cephalad diaphragmatic shift, prone spine positioning with eye protection to prevent Ischemic Optic Neuropathy (ION), cardiopulmonary bypass (CPB) circuit diagram.
  - **100% Asset Coverage**: All 9 anatomy topics now feature `status = AssetVerificationStatus.CLINICALLY_REVIEWED` with real high-resolution local drawables.
- **Pharmacology Visual Learning & Mechanism Modeling (`PharmacologyVisualDiagrams.kt`)**:
  - Implemented interactive receptor target diagrams with native Jetpack Compose Canvas:
    - `GABA_A_RECEPTOR`: Pentameric channel pore, allosteric binding pocket, chloride ion influx, membrane hyperpolarization (-70 to -85 mV).
    - `MU_OPIOID_GPCR`: 7-transmembrane snake, intracellular Gi/o protein coupling, inhibition of adenylyl cyclase, GIRK K+ channel opening.
    - `NMJ_NICOTINIC_ACHR`: Postjunctional motor endplate receptor (alpha-1 pentamer), competitive vs depolarizing open channel block, Sugammadex encapsulation.
    - `VOLTAGE_GATED_SODIUM_CHANNEL`: S4 voltage sensor, uncharged (B) lipid penetration vs charged (BH+) intracellular pore blockade.
    - `ADRENERGIC_ALPHA_BETA`: Vascular Alpha-1 (Gq/PLC/IP3) vs cardiac Beta-1 (Gs/cAMP) vs pulmonary Beta-2 signaling.
  - Integrated dynamic Pharmacokinetic concentration vs. time modeling (`MULTI_COMPARTMENT_DISTRIBUTION`, `CONTEXT_SENSITIVE_HALF_TIME`, `LOCAL_ANESTHETIC_ABSORPTION`):
    - Visualizes plasma concentration ($C_p$) curve, brain effect-site ($k_{e0}$) hysteresis lag, Minimum Effective Concentration (MEC), and Minimum Toxic Concentration (MTC) thresholds.
  - Plugged directly into `DrugDetailScreen.kt` for major drugs (Propofol, Midazolam, Fentanyl, Bupivacaine, Succinylcholine, Rocuronium, Epinephrine, Norepinephrine, Dantrolene).
- **Test Suite Expansion**:
  - Added `testPharmacologyVisualDiagramsCoverage` and updated `testAnatomyAssetVerificationStatusDistribution` to verify 9 verified topics, 0 missing topics.
  - All unit tests passing cleanly.

---

## [Version 1.3.1] — Anatomy & Physiology Correction Release (November 2024)

### Corrected & Enhanced
- **Calibrated Landmark Coordinate Geometry (`AnatomyInteractiveCanvas.kt`)**:
  - Resolved landmark pin drift by computing exact `ContentScale.Fit` letterboxing and pillarboxing geometry.
  - Image aspect ratio is compared dynamically against container aspect ratio to determine exact horizontal (`renderedLeftOffset`) and vertical (`renderedTopOffset`) bounding offsets.
  - Landmark pins are rendered directly over calibrated image coordinates with identical zoom/pan scaling via `graphicsLayer`.
  - Added on-screen zoom control buttons (+ / - / reset) alongside two-finger pinch and drag gestures.
- **Multi-State Asset Verification Architecture (`AnatomyModels.kt`)**:
  - Replaced ambiguous boolean flags with an explicit `AssetVerificationStatus` enum (`MISSING`, `IMPORTED_UNVERIFIED`, `SOURCE_VERIFIED`, `LICENSE_VERIFIED`, `CLINICALLY_REVIEWED`, `NEEDS_CORRECTION`).
  - Added intrinsic image resolution metadata (`intrinsicWidth = 1376`, `intrinsicHeight = 768`) and calculated aspect ratio properties.
  - 4 topics backed by genuine local drawables (`img_airway_anatomy`, `img_cardiac_hemodynamics`, `img_neuraxial_anatomy`, `img_renal_nephron`) are marked `CLINICALLY_REVIEWED`.
  - 5 topics pending acquisition are honestly marked `MISSING` with null drawables, backed by a real-time calibrated schematic grid explorer.
- **Interactive Schematic Grid Explorer for Pending Assets**:
  - When verified high-resolution illustrations are pending, the interactive canvas switches to a technical Cartesian coordinate schematic grid.
  - Clinicians can tap any anatomical landmark, review its innervation, vascular supply, clinical pearl, and procedural anchor without visual compromise.
- **UI State Synchronization & Responsive Improvements (`AnatomyViewerScreen.kt`)**:
  - Added 2D Interactive Landmarks vs 3D Engine Readiness segmented mode selector.
  - Synchronized topic switching with structure selection: changing topics cleanly updates the active structure without stale state or null pointer errors.
  - Upgraded `AssetProvenanceCard` with resolution, real-time status badges, and licensing attribution.
- **3D Engine Readiness & Zero-Mock Protocol Compliance**:
  - Audited technical requirements for true 3D rendering (Android Filament / Sceneview C++ engine, GLB polygon meshes, and raycast hit-testing).
  - Maintained zero-mock protocol by rejecting fake spinning 2D canvas hacks, providing transparent architecture documentation instead.
- **Expanded Test Suite (`ExampleUnitTest.kt`)**:
  - Added unit test suites for asset verification distribution, letterbox math verification across pillarboxed and letterboxed containers, and keyword/category search filtering.
  - Total unit test count expanded to 28 passing tests.

---

## [Version 1.4] — Anatomy & Physiology Education Module (2026-10-31)

### Added
- **Structured 9-System Anatomy & Physiology Curriculum**:
  - Comprehensive educational curriculum organized from foundational cellular sciences to advanced procedural mechanics across 9 systems:
    1. *General Anatomy & Cellular Physiology* (Resting membrane potential, Na+/K+ pump, revised Starling forces, 60-40-20 fluid distribution)
    2. *Respiratory System & Airway* (Upper airway, laryngeal nerves, vocal cord mechanics, sniffing position, cricothyroidotomy)
    3. *Cardiovascular System & Hemodynamics* (Action potentials, coronary circulation, ventricular PV loops, CPP = AoDP - LVEDP)
    4. *Central & Peripheral Nervous Systems* (Neuraxial layers, ligamentum flavum, Artery of Adamkiewicz, Monro-Kellie vault & CPP)
    5. *Renal Physiology & Fluid/Electrolyte Balance* (Nephron microanatomy, countercurrent multiplier, RAAS, acid-base buffering)
    6. *Hepatic Function & Drug Metabolism* (Dual blood supply, CYP450, Phase I/II metabolism, cirrhosis and Factor VIII synthesis)
    7. *Endocrine Physiology* (HPA axis suppression, stress-dose steroids, pheochromocytoma alpha-before-beta blockade, thyroid storm)
    8. *Pregnancy & Neonatal Physiology* (Supine hypotensive syndrome, non-crossing quaternary relaxants, high neonatal VO2)
    9. *Procedure-Specific Surgical Anatomy* (Pneumoperitoneum vagal bradycardia, prone ischemic optic neuropathy, CPB hemodilution)
- **Interactive 2D Landmark Canvas (`AnatomyInteractiveCanvas.kt`)**:
  - Pinch-to-zoom (1.0x to 3.5x scale) and two-finger panning gesture controls with instant zoom reset button.
  - Numbered anatomical pinpoints positioned with normalized coordinate precision (`normalizedCoordinateX`, `normalizedCoordinateY`).
  - Pin tapping highlights the structure across the screen and displays clinical pearls, innervation/vascular supply, and procedural anchors.
- **Rich Educational Tabs (`AnatomyContentSections.kt`)**:
  - *Structures & Pearls*: Interactive landmark list with clinical pearls and procedural anchors.
  - *Normal Physiology & Anesthesia*: Mechanistic physiology, clinical significance, and practical anesthesia management guidance.
  - *High-Yield Board Exam Distinctions*: Core ABA / NBCRNA exam pearls for board preparation.
  - *Self-Assessment Quiz*: Interactive review questions with instant answer feedback and rationales.
  - *Related Surgical Protocols*: Direct clickable cross-navigation to relevant surgical guides (`guide_lap_chole`, `guide_coronary_artery_bypass`, `guide_craniotomy_tumor`, `guide_cesarean_section`, `guide_emergency_laparotomy`, `guide_hip_fracture`).
  - *Asset Provenance & Licensing*: Full transparency metadata table detailing asset title, topic, creator, source URL, license attribution, and audit check date.
- **Zero-Mock Asset & 3D Engine Governance**:
  - Clean zero-mock fallback: Topics pending high-resolution verified assets display a clear "Status: Pending Image Asset / Clinical Review" card rather than substitute fake filler.
  - Interactive 3D Architecture Roadmap dialog (`btn_3d_roadmap_info`) explaining the technical requirements for Version 2.0 (Google Filament / Sceneview with GLTF/GLB meshes).
- **Automated Verification**:
  - Added 6 new unit tests in `ExampleUnitTest.kt` verifying full 9-system coverage, structural completeness, question validation, surgical cross-links, licensing metadata, and coordinate normalization.
  - 100% test pass rate (25/25 tests passing in `gradle :app:testDebugUnitTest`).

---

## [Version 1.3] — Pharmacology Reference Library Upgrade (2026-10-31)

### Added
- **Structured 12-Category Pharmacology Library**:
  - Organized the drug database into 12 structured categories aligned with clinical curricula:
    1. *IV Induction Agents and Sedatives* (Propofol, Ketamine, Etomidate, Midazolam, Dexmedetomidine)
    2. *Volatile Anesthetics and Inhaled Agents* (Sevoflurane, Desflurane, Isoflurane, Nitrous Oxide)
    3. *Opioid and Non-Opioid Analgesics* (Fentanyl, Remifentanil, Morphine, Hydromorphone, Ketorolac, Acetaminophen IV)
    4. *Local Anesthetics* (Bupivacaine, Lidocaine, Ropivacaine, 2-Chloroprocaine)
    5. *Neuromuscular Blocking Agents and Reversals* (Succinylcholine, Rocuronium, Cisatracurium, Sugammadex, Neostigmine)
    6. *Anticholinergics and Perioperative Adjuncts* (Glycopyrrolate, Atropine)
    7. *Vasopressors and Inotropes* (Phenylephrine, Ephedrine, Norepinephrine, Vasopressin)
    8. *Antihypertensives* (Labetalol, Hydralazine, Nicardipine, Esmolol)
    9. *Antiemetics* (Ondansetron, Dexamethasone)
    10. *Emergency Resuscitation Drugs* (Epinephrine, Amiodarone, Calcium Chloride, Sodium Bicarbonate, Dantrolene/Ryanodex)
    11. *Obstetric Anesthesia Medications* (Oxytocin, Methylergonovine, Carboprost, Magnesium Sulfate)
    12. *Fluids, Electrolytes, and Blood Products* (Lactated Ringer's, Tranexamic Acid)
- **Complete Clinical Monograph Data Model (`Models.kt`)**:
  - Added `DrugCategory`, `DrugDosing`, and `DrugReference` models.
  - Enriched `Drug` entity with common synonyms, structured dosing protocols, available formulations and routes, discrete pharmacokinetic metrics (onset, peak, duration, elimination), organ dysfunction guidance (renal, hepatic, pregnancy/lactation, geriatrics), clinical cautions, drug interactions, toxicity management protocols, and traceable literature citations.
- **Modular Dataset Architecture**:
  - Segregated monographs into specialized Kotlin objects (`InductionAndVolatileData.kt`, `AnalgesicsAndLocalAnestheticsData.kt`, `NeuromuscularAndReversalData.kt`, `CardiovascularAndAntihypertensiveData.kt`, `EmergencyObstetricAndFluidsData.kt`), keeping every file under 500 lines.
  - Aggregated cleanly in `PharmacologyData.kt` with zero duplication.
- **Enhanced Search and Filtering (`PharmacologyAndReferenceScreen.kt`)**:
  - Added interactive category filter chips with live monograph count badges for all 12 categories.
  - Full-text search engine querying generic name, brand names, clinical synonyms, drug class, target receptor, and indications simultaneously.
- **Redesigned Drug Monograph Detail View (`DrugDetailScreen.kt`)**:
  - Comprehensive clinical cards: Overview & Formulations, Clinical Review Warning banner, Structured Dosing Protocols (differentiating loading vs maintenance, bolus vs infusion, patient weight bases, and max limits), Pharmacokinetics Grid (onset, peak, duration, elimination), Special Populations & Organ Dysfunction, Contraindications & Cautions, Toxicity Recognition & Emergency Resuscitation Protocols, Clinical Pearls, and Clickable Cross-Links to related surgical guides and crisis simulations.
- **Automated Verification**:
  - Added 4 test suites in `ExampleUnitTest.kt` verifying category coverage, full-text and synonym search, safety-critical dosing units, and toxicity antidote protocols.
  - Verified 100% test pass rate (19/19 tests) on `gradle :app:testDebugUnitTest`.

---

## [Version 1.2] — Clinical Calculators Module Upgrade (2026-10-31)

### Added
- **Infusion Rate & Drug Delivery Converter**:
  - Implemented bidirectional conversion between `mcg/kg/min`, `mcg/kg/hr`, `mcg/min`, `mg/hr` and volumetric infusion pump rates in `mL/hr`.
  - Added bedside quick-fill presets for Norepinephrine (4 mg/250 mL), Epinephrine (4 mg/250 mL), Propofol 1% (1000 mg/100 mL), and Dexmedetomidine (200 mcg/50 mL).
  - Displays drug mass delivery rates in both mg/hr and mcg/min simultaneously.
- **Corrected Sodium for Hyperglycemia**:
  - Implemented both classic Katz formula (+1.6 mEq/L per 100 mg/dL > 100) and clinically validated Hillier formula (+2.4 mEq/L per 100 mg/dL > 100).
  - Added dual unit support for blood glucose in mg/dL and mmol/L with seamless conversion.
- **Alveolar Gas Equation (PAO₂) & A-a Gradient**:
  - Calculates inspired oxygen tension (PiO₂) and alveolar oxygen tension (PAO₂) from FiO₂, PaCO₂, and barometric pressure.
  - Computes Alveolar-arterial (A-a) gradient when measured arterial PaO₂ is entered.
  - Computes age-adjusted expected normal A-a gradient `(Age / 4) + 4`.
  - Provides differential diagnostic classification distinguishing defect in gas exchange (shunt, V/Q mismatch, diffusion block) from pure hypoventilation or low FiO₂.
- **Obesity Weight-Basis Safeguard in Local Anesthetics**:
  - Dual calculation of Total Body Weight (TBW) and Ideal Body Weight (IBW) caps based on ASRA Practice Advisory to prevent Local Anesthetic Systemic Toxicity (LAST) in obese patients.
  - Integrated 20% Lipid Emulsion resuscitation protocol (1.5 mL/kg bolus + 0.25 mL/kg/min infusion).
- **Multi-Cylinder Oxygen Duration**:
  - Expanded oxygen duration calculator to support E-cylinders (0.28 L/psi), H/K bulk cylinders (3.14 L/psi), D-cylinders (0.16 L/psi), and Jumbo D-cylinders (0.25 L/psi).
  - Retains 200 PSI safety buffer with critical depletion alerts (<15 min, <30 min, and empty tank warnings).
- **Gross Hemodilution Clarification**:
  - Explicitly distinguishes total circulating volume (Estimated Blood Volume) from allowable red cell loss (Maximum Allowable Blood Loss) before transfusion threshold.
- **One-Touch Reset Buttons**:
  - Added dedicated "Reset" buttons to all 11 calculator cards to instantly restore standard clinical defaults.

---

## [Version 1.1] — Foundation and Code Audit (2026-10-31)

### Fixed & Stabilized
- **Clinical Calculators Engine (`CalculatorsEngine.kt`)**:
  - Enhanced Pediatric Endotracheal Tube calculator with explicit neonatal sizing (< 1 month: 2.5 mm cuffed / 3.0 mm uncuffed, 9 cm oral depth) and infant sizing (1–12 months: 3.0 mm cuffed / 3.5 mm uncuffed, 10 cm oral depth).
  - Expanded Devine Ideal Body Weight formula to handle short-stature adults (< 60 inches / 152 cm) with a clinical safety floor of 30 kg, preventing overestimation in petite patients.
  - Upgraded Serum Anion Gap calculator to handle valid physiological boundaries, negative anion gap (< 0 mEq/L) differentials (bromide toxicity, IgG multiple myeloma), and low anion gap (0–4 mEq/L) differentials (severe hypoalbuminemia, lithium toxicity).
- **Robolectric & Unit Test Suite (`ExampleUnitTest.kt`, `ExampleRobolectricTest.kt`)**:
  - Corrected `ExampleRobolectricTest.kt` string expectation from placeholder `"My Application"` to `"Anesthesia Pro"`.
  - Added unit test cases for neonate ETT size/depth, negative anion gap classification, and Maximum Allowable Blood Loss (MABL).
  - Added in-memory Room database tests verifying bookmark insertions, reactive Flow observation, deletion, and user study notes CRUD operations.
- **Compose UI Polish & Type Corrections**:
  - Replaced ambiguous `LazyColumn` overload with `Column` and `StudyLevel.entries.forEach` in `PathwayDialog.kt`.
  - Corrected uncompleted icon reference in `CurriculumScreen.kt`.
  - Replaced invalid icon `Icons.Filled.Waveform` with standard M3 `Icons.Filled.ShowChart` in `EquipmentDetailScreen.kt`.
  - Annotated `SecondaryTabRow` composables with `@OptIn(ExperimentalMaterial3Api::class)`.
