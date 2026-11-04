package com.example.data.model

enum class LearningPathway(val displayName: String, val description: String) {
    ETHIOPIAN_BSC_ANESTHESIA(
        "Ethiopian BSc Anesthesia",
        "National Harmonized Modular Curriculum for Generic BSc in Anesthesia (February 2021)."
    ),
    NURSE_ANESTHESIA(
        "Ethiopian BSc Anesthesia (Harmonized)",
        "Generic 4-Year Modular Curriculum for BSc in Anesthesia, accredited by MoH / MoHE Ethiopia."
    ),
    PHYSICIAN_ANESTHESIOLOGY(
        "Supplementary Clinical Track",
        "International consultative anesthesia, advanced critical care, and reference guidelines."
    )
}

enum class AcademicYear(val yearNumber: Int, val romanNumeral: String, val title: String, val description: String) {
    YEAR_1(1, "Year I", "Foundational Sciences & General Modules", "Anatomy, physiology, biochemistry, pharmacology basics, and professional ethics"),
    YEAR_2(2, "Year II", "Pre-Clinical Anesthesia & Core Foundations", "Workstation physics, clinical airway management, principles of general & regional anesthesia"),
    YEAR_3(3, "Year III", "Clinical Anesthesia Specialty Blocks", "Obstetric, pediatric, trauma, neuro, and critical care anesthesia"),
    YEAR_4(4, "Year IV", "Clinical Internship & Senior Practice", "Comprehensive clinical internship, rural hospital placement, qualification exam prep")
}

enum class StudyLevel(val displayName: String, val academicYear: AcademicYear = AcademicYear.YEAR_2) {
    YEAR_1("Year I — Foundational Sciences", AcademicYear.YEAR_1),
    YEAR_2("Year II — Pre-Clinical Anesthesia", AcademicYear.YEAR_2),
    YEAR_3("Year III — Clinical Specialty Blocks", AcademicYear.YEAR_3),
    YEAR_4("Year IV — Clinical Internship", AcademicYear.YEAR_4),
    RESIDENT("Supplementary Specialist Practice", AcademicYear.YEAR_4),
    GRADUATED("Practicing Anesthesia Professional", AcademicYear.YEAR_4)
}

enum class CurriculumCategory(val displayName: String, val shortDesc: String) {
    FOUNDATIONAL_SCIENCES("Foundational Sciences", "Anatomy, physiology, cellular biochemistry & biostatistics"),
    APPLIED_PHYSIOLOGY("Applied Physiology", "Hemodynamics, gas exchange, acid-base, neuro & renal"),
    ANESTHESIA_FOUNDATIONS("Anesthesia Foundations", "Airway, induction, regional, monitoring & machine physics"),
    SPECIALTY_ANESTHESIA("Specialty Anesthesia", "Obstetric, pediatric, trauma, cardiac & neuroanesthesia"),
    CRISIS_AND_ADVANCED("Crisis & Advanced Topics", "Difficult airway, LAST, malignant hyperthermia & arrest")
}

enum class CurriculumMappingStatus(val displayName: String) {
    VERIFIED_HARMONIZED("Verified Harmonized"),
    AWAITING_OFFICIAL_MAPPING("Awaiting Official Code Mapping"),
    SUPPLEMENTARY("Supplementary Clinical Module")
}

data class CurriculumModule(
    val id: String,
    val name: String,
    val moduleCode: String? = null,
    val ects: Int? = null,
    val durationWeeks: Int? = null,
    val academicYear: AcademicYear,
    val semesterOrBlock: String? = null,
    val category: CurriculumCategory,
    val description: String,
    val learningOutcomes: List<String> = emptyList(),
    val mappingStatus: CurriculumMappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
    val lessonIds: List<String> = emptyList()
)

data class Lesson(
    val id: String,
    val title: String,
    val category: CurriculumCategory,
    val estimatedMinutes: Int,
    val pathwayApplicability: String = "Ethiopian BSc Anesthesia & Clinical Practice",
    val learningObjectives: List<String>,
    val prerequisites: List<String>,
    val beginnerExplanation: String,
    val physiologicalMechanisms: String,
    val clinicalApplications: String,
    val commonErrors: List<String>,
    val highYieldExamPoints: List<String>,
    val clinicalCaseStudy: String,
    val references: List<String>,
    val reviewStatus: String = "Source Verified - Clinical Reference",
    val academicYear: AcademicYear = AcademicYear.YEAR_2,
    val moduleId: String? = null,
    val knowledgeCheckQuestion: ReviewQuestion? = null
)

enum class DrugCategory(val displayName: String, val shortDesc: String) {
    IV_INDUCTION_SEDATIVES("IV Induction & Sedatives", "Intravenous induction agents, hypnotics, and sedatives"),
    VOLATILE_INHALED("Volatile & Inhaled", "Volatile halogenated anesthetics and inhaled gases"),
    OPIOID_NON_OPIOID_ANALGESICS("Opioids & Analgesics", "Systemic opioids, non-opioids, NSAIDs, and multimodal agents"),
    LOCAL_ANESTHETICS("Local Anesthetics", "Aminoamides and aminoesters for neuraxial, regional, and infiltration"),
    NEUROMUSCULAR_BLOCKERS_REVERSAL("NMBAs & Reversals", "Depolarizing, non-depolarizing paralytics, and specific antagonists"),
    ANTICHOLINERGICS_ADJUNCTS("Anticholinergics & Adjuncts", "Muscarinic antagonists, antisialagogues, and perioperative adjuncts"),
    VASOPRESSORS_INOTROPES("Vasopressors & Inotropes", "Direct and indirect alpha/beta sympathomimetics and non-adrenergic pressors"),
    ANTIHYPERTENSIVES("Antihypertensives", "Beta-blockers, vasodilators, calcium-channel blockers for acute hemodynamic control"),
    ANTIEMETICS("Antiemetics", "5-HT3 antagonists, corticosteroids, and dopamine antagonists for PONV prophylaxis"),
    EMERGENCY_DRUGS("Emergency Drugs", "ACLS resuscitation, antiarrhythmics, calcium salts, and crisis rescue agents"),
    OBSTETRIC_MEDICATIONS("Obstetric Medications", "Uterotonics, tocolytics, and neuroprotective agents for labor and delivery"),
    FLUIDS_ELECTROLYTES_BLOOD("Fluids & Blood Products", "Crystalloids, colloids, transfusion components, and hemostatic antifibrinolytics")
}

data class DrugDosing(
    val route: String, // e.g. "IV Bolus", "IV Infusion", "Epidural", "Spinal", "IM"
    val indication: String, // e.g. "General Anesthesia Induction", "Postop Analgesia"
    val adultDose: String,
    val pediatricDose: String? = null,
    val obstetricDose: String? = null,
    val elderlyDose: String? = null,
    val maxDose: String? = null,
    val unitNotes: String? = null // e.g. "Dosed on Lean Body Weight (LBW) in obesity"
)

data class DrugReference(
    val sourceTitle: String,
    val editionOrDate: String,
    val organizationOrJournal: String,
    val reviewStatus: String = "Source Verified - Authoritative Clinical Literature"
)

data class Drug(
    val id: String,
    val genericName: String,
    val brandNames: String,
    val synonyms: List<String> = emptyList(),
    val category: DrugCategory = DrugCategory.IV_INDUCTION_SEDATIVES,
    val drugClass: String,
    val mechanismOfAction: String,
    val targetReceptor: String,
    val indications: List<String>,
    val onsetAndDuration: String,
    val adultDosing: String,
    val pediatricDosing: String = "Consult pediatric dosing section",
    val obstetricDosing: String = "See Obstetric & Perinatal section",
    val dosingProtocols: List<DrugDosing> = emptyList(),
    val formulationsAndRoutes: List<String> = emptyList(),
    val pharmacokinetics: String,
    val onset: String = "",
    val peakEffect: String = "",
    val duration: String = "",
    val elimination: String = "",
    val contraindications: List<String>,
    val cautions: List<String>,
    val adverseReactions: List<String>,
    val interactions: List<String> = emptyList(),
    val renalAdjustment: String = "Standard dosing with cautious titration unless specified",
    val hepaticAdjustment: String = "Standard dosing with cautious titration unless specified",
    val pregnancyAndLactation: String = "Evaluate maternal-fetal benefit vs risk",
    val elderlyConsiderations: String = "Reduce initial dose by 20-50% and titrate slowly",
    val anesthesiaConsiderations: String = "",
    val reversalAgent: String?,
    val toxicityManagement: String? = null,
    val clinicalPearls: String,
    val reviewStatus: String = "Clinically Reviewed",
    val references: List<DrugReference> = emptyList(),
    val relatedGuideIds: List<String> = emptyList(),
    val relatedCaseIds: List<String> = emptyList(),
    val diagramTitle: String? = null,
    val diagramType: String? = null,
    val pkCurveType: String? = null
)

enum class SurgicalSpecialty(val displayName: String, val shortCode: String) {
    GENERAL_SURGERY("General Surgery", "GS"),
    OBSTETRICS_GYNECOLOGY("Obstetrics & Gynecology", "OB/GYN"),
    ORTHOPEDICS_TRAUMA("Orthopedics & Trauma", "ORTHO"),
    NEUROSURGERY("Neurosurgery", "NEURO"),
    CARDIOTHORACIC_VASCULAR("Cardiothoracic & Vascular", "CV"),
    PEDIATRIC_SURGERY("Pediatric Surgery", "PEDS"),
    UROLOGY("Urology", "URO"),
    ENT_MAXILLOFACIAL("ENT & Maxillofacial", "ENT"),
    OPHTHALMIC_SURGERY("Ophthalmic Surgery", "EYE"),
    THORACIC_SURGERY("Thoracic Surgery", "THOR"),
    EMERGENCY_SURGERY("Emergency & Trauma", "EMERG")
}

data class SurgicalGuide(
    val id: String,
    val procedureName: String,
    val surgicalSpecialty: String,
    val overview: String,
    val preoperativeAssessment: String,
    val anestheticTechnique: String,
    val airwayConsiderations: String,
    val positioningAndNerveSafety: String,
    val hemodynamicManagement: String,
    val analgesiaPlan: String,
    val potentialComplications: List<String>,
    val postoperativeRecovery: String,
    val reviewStatus: String = "Guidelines Aligned",
    val surgicalPositionAndPhysiology: String? = null,
    val relevantAnatomyPhysiology: String? = null,
    val monitoringAndAccess: String? = null,
    val drugAndFluidStrategy: String? = null,
    val expectedBloodLossAndTransfusion: String? = null,
    val highYieldExamPoints: List<String> = emptyList(),
    val caseQuestions: List<ReviewQuestion> = emptyList(),
    val references: List<DrugReference> = emptyList(),
    val relatedAnatomyTopicIds: List<String> = emptyList(),
    val relatedDrugIds: List<String> = emptyList(),
    val relatedEquipmentIds: List<String> = emptyList(),
    val relatedCalculatorIds: List<String> = emptyList(),
    val relatedCaseId: String? = null
)

data class EquipmentModule(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val mechanism: String,
    val normalValuesAndWaveforms: String,
    val troubleshootingAndFailures: List<String>,
    val clinicalPearls: String
)

data class QuizQuestion(
    val id: String,
    val stem: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: String = "Intermediate",
    val topic: String
)

data class CaseStage(
    val stageTitle: String,
    val scenario: String,
    val vitals: String, // e.g. "BP: 72/40 | HR: 118 | SpO2: 94% | EtCO2: 28"
    val options: List<String>,
    val correctIndex: Int,
    val immediateFeedback: String,
    val rationale: String
)

data class ClinicalCase(
    val id: String,
    val title: String,
    val category: String,
    val patientBrief: String,
    val stages: List<CaseStage>,
    val debriefSummary: String
)
