package com.example.data.model

import androidx.annotation.DrawableRes

enum class AnatomySystemCategory(val displayName: String, val shortDesc: String) {
    GENERAL_CELLULAR("General & Cellular", "Membrane potentials, fluid compartments & receptor biology"),
    RESPIRATORY_AIRWAY("Respiratory & Airway", "Upper airway, laryngeal nerves, gas exchange & mechanics"),
    CARDIOVASCULAR_HEMODYNAMICS("Cardiovascular", "Electrophysiology, hemodynamics, PV loops & coronary flow"),
    NERVOUS_SYSTEM("Nervous System", "Neuraxial planes, cerebral blood flow, ICP & nerve plexuses"),
    RENAL_ELECTROLYTES("Renal & Electrolytes", "Nephron mechanics, GFR, fluid balance & acid-base buffer"),
    HEPATIC_METABOLISM("Hepatic & Metabolism", "Dual blood supply, Cytochrome P450 & pharmacokinetics"),
    ENDOCRINE_SYSTEM("Endocrine Physiology", "HPA axis, thyroid crisis, pheochromocytoma & insulin"),
    PREGNANCY_NEONATAL("Obstetric & Neonatal", "Maternal cardiopulmonary changes, uteroplacental & fetal"),
    SURGICAL_PROCEDURAL("Surgical Anatomy", "Pneumoperitoneum, prone positioning & cardiopulmonary bypass")
}

enum class AnatomyDifficulty(val displayName: String) {
    FOUNDATIONAL("Foundational (Year 1)"),
    INTERMEDIATE("Intermediate (Clinical)"),
    ADVANCED("Advanced (Consultative / Exam)")
}

enum class AssetVerificationStatus(val displayName: String) {
    MISSING("Missing Local Asset"),
    IMPORTED_UNVERIFIED("Imported - Unverified"),
    SOURCE_VERIFIED("Source Verified"),
    LICENSE_VERIFIED("License Verified"),
    CLINICALLY_REVIEWED("Clinically Reviewed & Active"),
    NEEDS_CORRECTION("Needs Correction")
}

data class AnatomicalStructure(
    val id: String,
    val name: String,
    val normalizedCoordinateX: Float = 0.5f, // 0.0f to 1.0f on calibrated image canvas
    val normalizedCoordinateY: Float = 0.5f, // 0.0f to 1.0f on calibrated image canvas
    val description: String,
    val innervationOrVascular: String = "",
    val clinicalPearl: String,
    val relatedIntervention: String = "" // e.g. "Macintosh blade placement", "Lumbar puncture L3-L4", "Radial arterial line"
)

data class ReviewQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val examHighYieldTag: String = "ABA / NBCRNA Core"
)

data class AnatomyAssetMetadata(
    val assetTitle: String,
    val topicName: String,
    val creatorOrInstitution: String,
    val sourceUrl: String,
    val licenseAndAttribution: String,
    @field:DrawableRes val localDrawableRes: Int? = null,
    val localFileName: String? = null,
    val intrinsicWidth: Int = 1376,
    val intrinsicHeight: Int = 768,
    val status: AssetVerificationStatus = AssetVerificationStatus.CLINICALLY_REVIEWED,
    val verificationDate: String = "November 2024",
    val statusNote: String = "Active Verified Asset"
) {
    val isVerified: Boolean
        get() = status == AssetVerificationStatus.CLINICALLY_REVIEWED ||
                status == AssetVerificationStatus.LICENSE_VERIFIED ||
                status == AssetVerificationStatus.SOURCE_VERIFIED

    val isMissing: Boolean
        get() = status == AssetVerificationStatus.MISSING || localDrawableRes == null

    val intrinsicAspectRatio: Float
        get() = if (intrinsicHeight > 0) intrinsicWidth.toFloat() / intrinsicHeight.toFloat() else 1.79167f
}

data class AnatomyTopic(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: AnatomySystemCategory,
    val difficulty: AnatomyDifficulty,
    val learningObjectives: List<String>,
    val structures: List<AnatomicalStructure>,
    val normalPhysiology: String,
    val clinicalSignificance: String,
    val anesthesiaImplications: String,
    val commonExamDistinctions: List<String>,
    val reviewQuestions: List<ReviewQuestion>,
    val relatedSurgicalGuideIds: List<String> = emptyList(),
    val assetMetadata: AnatomyAssetMetadata
)
