package com.example.data.repository

import kotlin.math.max
import kotlin.math.roundToInt

object CalculatorsEngine {

    // 1. BMI, Ideal Body Weight (Devine), and Adjusted Body Weight
    data class BmiIbwResult(
        val bmi: Double,
        val bmiCategory: String,
        val ibwKg: Double,
        val adjBwKg: Double,
        val equationUsed: String,
        val clinicalNote: String
    )

    fun calculateBmiAndIbw(
        weightKg: Double,
        heightCm: Double,
        isMale: Boolean
    ): BmiIbwResult? {
        if (weightKg <= 0.0 || heightCm <= 0.0 || heightCm > 250.0 || weightKg > 400.0) return null

        val heightM = heightCm / 100.0
        val bmi = (weightKg / (heightM * heightM) * 10.0).roundToInt() / 10.0

        val bmiCategory = when {
            bmi < 18.5 -> "Underweight (<18.5 kg/m²)"
            bmi < 25.0 -> "Normal weight (18.5 - 24.9 kg/m²)"
            bmi < 30.0 -> "Overweight (25.0 - 29.9 kg/m²)"
            bmi < 35.0 -> "Obesity Class I (30.0 - 34.9 kg/m²)"
            bmi < 40.0 -> "Obesity Class II (35.0 - 39.9 kg/m²)"
            else -> "Obesity Class III / Morbid (≥40.0 kg/m²)"
        }

        // Devine Formula (Height in inches, 60 inches = 5 feet)
        val heightInches = heightCm / 2.54
        val inchesOffset = heightInches - 60.0
        val baseIbw = if (isMale) {
            50.0 + (2.3 * inchesOffset)
        } else {
            45.5 + (2.3 * inchesOffset)
        }
        val ibw = max(30.0, baseIbw) // Clinical minimum floor for adult calculation
        val roundedIbw = (ibw * 10.0).roundToInt() / 10.0

        // Adjusted Body Weight = IBW + 0.4 * (Actual - IBW) if obese
        val adjBw = if (weightKg > ibw) {
            ibw + 0.4 * (weightKg - ibw)
        } else {
            ibw
        }
        val roundedAdjBw = (adjBw * 10.0).roundToInt() / 10.0

        val equation = if (isMale) {
            "Devine Formula (Male): 50 kg + 2.3 kg per inch over 5 feet"
        } else {
            "Devine Formula (Female): 45.5 kg + 2.3 kg per inch over 5 feet"
        }

        val note = "Clinical pearl: Hydrophilic drugs (e.g. Vecuronium, Rocuronium, Neostigmine) should be dosed on IBW to prevent prolonged paralysis in obese patients. Lipophilic drugs (e.g. Propofol induction) generally use TBW or Adjusted BW."

        return BmiIbwResult(
            bmi = bmi,
            bmiCategory = bmiCategory,
            ibwKg = roundedIbw,
            adjBwKg = roundedAdjBw,
            equationUsed = equation,
            clinicalNote = note
        )
    }

    // 2. Mean Arterial Pressure (MAP)
    data class MapResult(
        val mapMmhg: Double,
        val pulsePressure: Double,
        val equationUsed: String,
        val clinicalStatus: String
    )

    fun calculateMap(sbp: Double, dbp: Double): MapResult? {
        if (sbp <= 0 || dbp <= 0 || sbp <= dbp || sbp > 300 || dbp > 200) return null
        val map = dbp + ((sbp - dbp) / 3.0)
        val roundedMap = (map * 10.0).roundToInt() / 10.0
        val pp = sbp - dbp

        val status = when {
            roundedMap < 60.0 -> "Critical Hypoperfusion (MAP < 60 mmHg). Renal, coronary and cerebral autoregulation compromised."
            roundedMap < 65.0 -> "Borderline Perfusion (MAP 60-64 mmHg). Consider vasopressor or volume expansion."
            roundedMap <= 100.0 -> "Normal Anesthetic Perfusion Target (MAP 65 - 100 mmHg)."
            else -> "Elevated MAP (> 100 mmHg). Assess depth of anesthesia, analgesia, and surgical stimulation."
        }

        return MapResult(
            mapMmhg = roundedMap,
            pulsePressure = pp,
            equationUsed = "MAP = DBP + 1/3 (SBP - DBP)",
            clinicalStatus = status
        )
    }

    // 3. Estimated Blood Volume & Maximum Allowable Blood Loss (MABL)
    data class MablResult(
        val ebvMl: Double,
        val mablMl: Double,
        val equationUsed: String,
        val clinicalNote: String
    )

    enum class PatientCategory(val label: String, val mlPerKg: Double) {
        ADULT_MALE("Adult Male (75 mL/kg)", 75.0),
        ADULT_FEMALE("Adult Female (65 mL/kg)", 65.0),
        OBESE_ADULT("Obese Adult (60 mL/kg)", 60.0),
        INFANT("Infant (80 mL/kg)", 80.0),
        NEONATE("Full-term Neonate (85 mL/kg)", 85.0),
        PREMATURE("Premature Neonate (95 mL/kg)", 95.0)
    }

    fun calculateMabl(
        weightKg: Double,
        category: PatientCategory,
        initialHct: Double,
        targetHct: Double
    ): MablResult? {
        if (weightKg <= 0 || initialHct <= 0 || targetHct <= 0 || initialHct <= targetHct || initialHct > 65 || targetHct < 15) return null

        val ebv = weightKg * category.mlPerKg
        val mabl = ebv * ((initialHct - targetHct) / initialHct)

        val roundedEbv = (ebv * 10.0).roundToInt() / 10.0
        val roundedMabl = (mabl * 10.0).roundToInt() / 10.0

        val equation = "EBV = Weight ($weightKg kg) × ${category.mlPerKg} mL/kg = $roundedEbv mL\n" +
                "MABL = EBV × (Initial Hct - Target Hct) / Initial Hct"

        val note = "Gross Formula: MABL calculates permissible red cell loss before reaching target Hct ($targetHct%). This assumes isovolemic crystalloid/colloid replacement. Trigger transfusion protocols or cell salvage if blood loss approaches MABL ($roundedMabl mL)."

        return MablResult(
            ebvMl = roundedEbv,
            mablMl = roundedMabl,
            equationUsed = equation,
            clinicalNote = note
        )
    }

    // 4. Pediatric Endotracheal Tube Size & Depth Calculator
    data class PediatricEttResult(
        val cuffedSizeMm: Double,
        val uncuffedSizeMm: Double,
        val oralDepthCm: Double,
        val nasalDepthCm: Double,
        val backupSizes: String,
        val clinicalNote: String
    )

    fun calculatePediatricEtt(ageYears: Double): PediatricEttResult? {
        if (ageYears < 0.0 || ageYears > 18.0) return null

        val (uncuffed, cuffed, depthOral) = when {
            ageYears < 0.08 -> Triple(3.0, 2.5, 9.0) // Neonate (< 1 month)
            ageYears < 1.0 -> Triple(3.5, 3.0, 10.0) // Infant (1 - 12 months)
            else -> Triple((ageYears / 4.0) + 4.0, (ageYears / 4.0) + 3.5, (ageYears / 2.0) + 12.0)
        }
        val depthNasal = depthOral + 2.5

        val roundUncuffed = (uncuffed * 2.0).roundToInt() / 2.0
        val roundCuffed = (cuffed * 2.0).roundToInt() / 2.0
        val roundDepthOral = (depthOral * 10.0).roundToInt() / 10.0
        val roundDepthNasal = (depthNasal * 10.0).roundToInt() / 10.0

        val backup = "Always prepare 0.5 mm smaller (${roundCuffed - 0.5}) and 0.5 mm larger (${roundCuffed + 0.5}) tubes immediately available."
        val note = "Formulas: Khine/Motoyama Cuffed = (Age/4) + 3.5; Uncuffed = (Age/4) + 4. Oral depth at mid-incisors = (Age/2) + 12 cm or ID × 3. For neonates (<1 mo), standard size is 3.0 uncuffed / 2.5 cuffed. Verify bilateral breath sounds and keep cuff leak pressure 20-25 cmH2O."

        return PediatricEttResult(
            cuffedSizeMm = roundCuffed,
            uncuffedSizeMm = roundUncuffed,
            oralDepthCm = roundDepthOral,
            nasalDepthCm = roundDepthNasal,
            backupSizes = backup,
            clinicalNote = note
        )
    }

    // 5. Local Anesthetic Maximum Safe Dose & Volume Calculator
    data class LocalAnestheticMaxResult(
        val drugName: String,
        val maxDoseMg: Double,
        val maxVolumeMl: Double,
        val doseMgPerKg: Double,
        val ceilingCapMg: Double,
        val ibwMaxDoseMg: Double?,
        val ibwMaxVolumeMl: Double?,
        val weightBasisGuidance: String,
        val toxicityWarning: String
    )

    enum class LocalAnestheticType(
        val displayName: String,
        val mgPerKg: Double,
        val absoluteMaxMg: Double,
        val typicalConcentrationPercent: Double,
        val referenceSource: String
    ) {
        LIDOCAINE_PLAIN("Lidocaine Plain (1% or 2%)", 4.5, 300.0, 1.0, "ASRA & FDA: 4.5 mg/kg, max 300 mg"),
        LIDOCAINE_WITH_EPI("Lidocaine with Epinephrine 1:200k", 7.0, 500.0, 1.0, "ASRA & FDA: 7.0 mg/kg, max 500 mg"),
        BUPIVACAINE_PLAIN("Bupivacaine Plain (0.25% or 0.5%)", 2.0, 175.0, 0.25, "ASRA & FDA: 2.0 mg/kg, max 175 mg"),
        BUPIVACAINE_WITH_EPI("Bupivacaine with Epinephrine", 2.5, 225.0, 0.25, "ASRA & FDA: 2.5 mg/kg, max 225 mg"),
        ROPIVACAINE("Ropivacaine (0.2% - 0.75%)", 3.0, 200.0, 0.5, "ASRA: 3.0 mg/kg, max 200 mg (surgical anesthesia)")
    }

    fun calculateLocalAnestheticLimit(
        weightKg: Double,
        drugType: LocalAnestheticType,
        selectedConcentrationPercent: Double,
        ibwKg: Double? = null
    ): LocalAnestheticMaxResult? {
        if (weightKg <= 0 || weightKg > 300 || selectedConcentrationPercent <= 0) return null

        val weightBasedDose = weightKg * drugType.mgPerKg
        val finalMaxMg = minOf(weightBasedDose, drugType.absoluteMaxMg)

        // Concentration: 1% = 10 mg/mL, 0.5% = 5 mg/mL, 0.25% = 2.5 mg/mL
        val mgPerMl = selectedConcentrationPercent * 10.0
        val maxMl = finalMaxMg / mgPerMl

        val roundedMaxMg = (finalMaxMg * 10.0).roundToInt() / 10.0
        val roundedMaxMl = (maxMl * 10.0).roundToInt() / 10.0

        var roundedIbwMaxMg: Double? = null
        var roundedIbwMaxMl: Double? = null
        if (ibwKg != null && ibwKg > 0 && ibwKg < weightKg) {
            val ibwDose = minOf(ibwKg * drugType.mgPerKg, drugType.absoluteMaxMg)
            val ibwMl = ibwDose / mgPerMl
            roundedIbwMaxMg = (ibwDose * 10.0).roundToInt() / 10.0
            roundedIbwMaxMl = (ibwMl * 10.0).roundToInt() / 10.0
        }

        val weightGuidance = if (roundedIbwMaxMg != null) {
            "Weight Basis Warning: In overweight/obese patients, ASRA Practice Advisory recommends calculating dose based on Ideal Body Weight ($ibwKg kg: max $roundedIbwMaxMg mg / $roundedIbwMaxMl mL) to prevent systemic toxicity (LAST), as local anesthetics have limited lipophilic penetration into excess adipose."
        } else {
            "Weight Basis: Based on actual weight ($weightKg kg). If patient has elevated BMI, cap dose using Ideal Body Weight."
        }

        val warning = "LAST Alert: Maximum safe limit is $roundedMaxMg mg ($roundedMaxMl mL of $selectedConcentrationPercent%). If LAST occurs: Stop injection, call for help, secure airway (100% O2, avoid hyperventilation), give 20% Lipid Emulsion bolus 1.5 mL/kg over 2-3 min followed by infusion 0.25 mL/kg/min."

        return LocalAnestheticMaxResult(
            drugName = drugType.displayName,
            maxDoseMg = roundedMaxMg,
            maxVolumeMl = roundedMaxMl,
            doseMgPerKg = drugType.mgPerKg,
            ceilingCapMg = drugType.absoluteMaxMg,
            ibwMaxDoseMg = roundedIbwMaxMg,
            ibwMaxVolumeMl = roundedIbwMaxMl,
            weightBasisGuidance = weightGuidance,
            toxicityWarning = warning
        )
    }

    // 6. Maintenance IV Fluid Rate (4-2-1 Rule / Holliday-Segar)
    data class FluidRateResult(
        val hourlyRateMlHr: Double,
        val dailyVolumeMlDay: Double,
        val explanation: String,
        val clinicalCaveat: String
    )

    fun calculate421Fluid(weightKg: Double): FluidRateResult? {
        if (weightKg <= 0 || weightKg > 300) return null

        val hourly = when {
            weightKg <= 10.0 -> weightKg * 4.0
            weightKg <= 20.0 -> (10.0 * 4.0) + ((weightKg - 10.0) * 2.0)
            else -> (10.0 * 4.0) + (10.0 * 2.0) + ((weightKg - 20.0) * 1.0)
        }
        val roundedHourly = (hourly * 10.0).roundToInt() / 10.0
        val daily = (roundedHourly * 24.0 * 10.0).roundToInt() / 10.0

        val explanation = "4-2-1 Rule Breakdown:\n" +
                "• First 10 kg: 4 mL/kg/hr\n" +
                "• 11 to 20 kg: 2 mL/kg/hr\n" +
                "• Each kg > 20 kg: 1 mL/kg/hr"

        val caveat = "Limitations: 4-2-1 reflects baseline maintenance only. Adjust separately for fasting deficit (Hours NPO × Maintenance), third space losses (2-8 mL/kg/hr depending on surgical trauma), and measured blood loss."

        return FluidRateResult(
            hourlyRateMlHr = roundedHourly,
            dailyVolumeMlDay = daily,
            explanation = explanation,
            clinicalCaveat = caveat
        )
    }

    // 7. Oxygen Cylinder Remaining Duration
    enum class OxygenCylinderType(
        val displayName: String,
        val factorLPerPsi: Double,
        val fullPressurePsi: Double,
        val nominalCapacityL: Double
    ) {
        E_CYLINDER("E-Cylinder (Anesthesia Machine / Transport)", 0.28, 2000.0, 680.0),
        H_K_CYLINDER("H / K-Cylinder (Bulk / Hospital Manifold)", 3.14, 2200.0, 6900.0),
        D_CYLINDER("D-Cylinder (Small Portable / EMS)", 0.16, 2000.0, 400.0),
        JUMBO_D("Jumbo D-Cylinder (Ambulance Transport)", 0.25, 2000.0, 640.0)
    }

    data class OxygenCylinderResult(
        val cylinderName: String,
        val remainingMinutes: Double,
        val remainingHours: Double,
        val usableLiters: Double,
        val reservePressurePsi: Double,
        val formulaUsed: String,
        val alert: String
    )

    fun calculateOxygenDuration(
        gaugePsi: Double,
        flowRateLpm: Double,
        cylinderType: OxygenCylinderType = OxygenCylinderType.E_CYLINDER,
        reserveBufferPsi: Double = 200.0
    ): OxygenCylinderResult? {
        if (gaugePsi < 0 || flowRateLpm <= 0 || gaugePsi > 3000 || flowRateLpm > 30 || reserveBufferPsi < 0) return null

        // Safe residual buffer: change tank when pressure drops to reserve threshold
        val usablePressure = max(0.0, gaugePsi - reserveBufferPsi)
        val usableVolumeLiters = usablePressure * cylinderType.factorLPerPsi
        val minutesRemaining = usableVolumeLiters / flowRateLpm
        val hoursRemaining = minutesRemaining / 60.0

        val roundedMinutes = (minutesRemaining * 10.0).roundToInt() / 10.0
        val roundedHours = (hoursRemaining * 10.0).roundToInt() / 10.0
        val roundedLiters = (usableVolumeLiters * 10.0).roundToInt() / 10.0

        val alert = if (roundedMinutes <= 0.0) {
            "CRITICAL EMPTY: Gauge pressure is at or below safe reserve buffer ($reserveBufferPsi psi). Switch to wall supply or new cylinder immediately!"
        } else if (roundedMinutes < 15.0) {
            "CRITICAL: Less than 15 minutes of O₂ remaining! Replace cylinder immediately."
        } else if (roundedMinutes < 30.0) {
            "WARNING: Low oxygen cylinder level (<30 minutes remaining)."
        } else {
            "Adequate oxygen reserve for immediate intraoperative or transport use."
        }

        val formula = "Cylinder: ${cylinderType.displayName}\n" +
                "Tank Factor: ${cylinderType.factorLPerPsi} L/psi\n" +
                "Usable Liters = (Gauge PSI - $reserveBufferPsi reserve) × ${cylinderType.factorLPerPsi} = $roundedLiters L\n" +
                "Duration = $roundedLiters L ÷ $flowRateLpm L/min = $roundedMinutes min"

        return OxygenCylinderResult(
            cylinderName = cylinderType.displayName,
            remainingMinutes = roundedMinutes,
            remainingHours = roundedHours,
            usableLiters = roundedLiters,
            reservePressurePsi = reserveBufferPsi,
            formulaUsed = formula,
            alert = alert
        )
    }

    // 8. Anion Gap Calculator
    data class AnionGapResult(
        val anionGap: Double,
        val interpretation: String,
        val formulaUsed: String,
        val differentials: String
    )

    fun calculateAnionGap(na: Double, cl: Double, hco3: Double): AnionGapResult? {
        if (na !in 70.0..190.0 || cl !in 40.0..150.0 || hco3 !in 1.0..60.0) return null

        val ag = na - (cl + hco3)
        val roundedAg = (ag * 10.0).roundToInt() / 10.0

        val interpretation = when {
            roundedAg > 12.0 -> "High Anion Gap Metabolic Acidosis (HAGMA > 12 mEq/L)"
            roundedAg < 0.0 -> "Negative Anion Gap (< 0 mEq/L) - Exceptional / Laboratory Artifact"
            roundedAg < 4.0 -> "Low Anion Gap (< 4 mEq/L) - Severe Hypoalbuminemia or Paraproteinemia"
            else -> "Normal Anion Gap (4 - 12 mEq/L)"
        }

        val differentials = when {
            roundedAg > 12.0 -> "Differential Diagnosis (GOLD MARK / MUDPILES):\nGlycols (ethylene/propylene), Oxoproline (chronic acetaminophen), L-Lactate (shock, mesenteric ischemia, sepsis), D-Lactate (short bowel), Methanol, Aspirin/Salicylates, Renal failure (Uremia), Ketoacidosis (Diabetic, Alcoholic, Starvation)."
            roundedAg < 0.0 -> "Causes of Negative Anion Gap:\n1. Severe Bromide or Iodide intoxication (falsely measured as excessive chloride on auto-analyzers)\n2. High cationic paraproteinemia (IgG Multiple Myeloma)\n3. Extreme hypercalcemia or hypermagnesemia\n4. Profound laboratory measurement artifact."
            roundedAg < 4.0 -> "Causes of Low Anion Gap (0 to 4 mEq/L):\n1. Hypoalbuminemia: Each 1 g/dL decline in serum albumin below 4 g/dL lowers expected normal anion gap by ~2.5 mEq/L\n2. Multiple Myeloma (cationic paraproteins)\n3. Lithium toxicity\n4. Severe hyperkalemia or hypercalcemia."
            else -> "Differential Diagnosis for Normal AG / Hyperchloremic Acidosis (HARDUP):\nHyperalimentation, Acetazolamide, Renal tubular acidosis (Type 1, 2, 4), Diarrhea (gastrointestinal HCO3- loss), Uretero-enterostomy, Pancreatic fistula, Rapid 0.9% Normal Saline resuscitation (hyperchloremic metabolic acidosis)."
        }

        return AnionGapResult(
            anionGap = roundedAg,
            interpretation = interpretation,
            formulaUsed = "Anion Gap = Na⁺ - (Cl⁻ + HCO₃⁻)",
            differentials = differentials
        )
    }

    // 9. Infusion Rate Converter (Bidirectional IV Pump & Drug Delivery)
    enum class InfusionDoseUnit(val label: String, val isWeightBased: Boolean) {
        MCG_KG_MIN("mcg/kg/min", true),
        MCG_KG_HR("mcg/kg/hr", true),
        MCG_MIN("mcg/min", false),
        MG_HR("mg/hr", false)
    }

    data class InfusionRateResult(
        val rateMlHr: Double,
        val drugDeliveryMgHr: Double,
        val drugDeliveryMcgMin: Double,
        val drugDeliveryMcgKgMin: Double?,
        val concentrationMgMl: Double,
        val concentrationMcgMl: Double,
        val calculationSteps: String,
        val safetyWarning: String
    )

    fun calculateInfusionRate(
        doseRate: Double,
        doseUnit: InfusionDoseUnit,
        weightKg: Double?,
        drugAmountMg: Double,
        bagVolumeMl: Double
    ): InfusionRateResult? {
        if (doseRate <= 0.0 || drugAmountMg <= 0.0 || bagVolumeMl <= 0.0) return null
        if (doseUnit.isWeightBased && (weightKg == null || weightKg <= 0.0 || weightKg > 350.0)) return null

        val concMgMl = drugAmountMg / bagVolumeMl
        val concMcgMl = concMgMl * 1000.0

        data class InfusionCalculatedValues(
            val rateMlHr: Double,
            val deliveryMgHr: Double,
            val deliveryMcgMin: Double,
            val deliveryMcgKgMin: Double?
        )

        val values = when (doseUnit) {
            InfusionDoseUnit.MCG_KG_MIN -> {
                val wt = weightKg!!
                val mcgMin = doseRate * wt
                val mcgHr = mcgMin * 60.0
                val mgHr = mcgHr / 1000.0
                val mlHr = mcgHr / concMcgMl
                val mcgKgMin = doseRate
                InfusionCalculatedValues(mlHr, mgHr, mcgMin, mcgKgMin)
            }
            InfusionDoseUnit.MCG_KG_HR -> {
                val wt = weightKg!!
                val mcgHr = doseRate * wt
                val mcgMin = mcgHr / 60.0
                val mgHr = mcgHr / 1000.0
                val mlHr = mcgHr / concMcgMl
                val mcgKgMin = doseRate / 60.0
                InfusionCalculatedValues(mlHr, mgHr, mcgMin, mcgKgMin)
            }
            InfusionDoseUnit.MCG_MIN -> {
                val mcgMin = doseRate
                val mcgHr = doseRate * 60.0
                val mgHr = mcgHr / 1000.0
                val mlHr = mcgHr / concMcgMl
                val mcgKgMin = if (weightKg != null && weightKg > 0) mcgMin / weightKg else null
                InfusionCalculatedValues(mlHr, mgHr, mcgMin, mcgKgMin)
            }
            InfusionDoseUnit.MG_HR -> {
                val mgHr = doseRate
                val mcgHr = mgHr * 1000.0
                val mcgMin = mcgHr / 60.0
                val mlHr = mgHr / concMgMl
                val mcgKgMin = if (weightKg != null && weightKg > 0) mcgMin / weightKg else null
                InfusionCalculatedValues(mlHr, mgHr, mcgMin, mcgKgMin)
            }
        }

        val rateMlHr = values.rateMlHr
        val deliveryMgHr = values.deliveryMgHr
        val deliveryMcgMin = values.deliveryMcgMin
        val deliveryMcgKgMin = values.deliveryMcgKgMin

        val roundMlHr = (rateMlHr * 100.0).roundToInt() / 100.0
        val roundMgHr = (deliveryMgHr * 1000.0).roundToInt() / 1000.0
        val roundMcgMin = (deliveryMcgMin * 10.0).roundToInt() / 10.0
        val roundMcgKgMin = deliveryMcgKgMin?.let { (it * 100.0).roundToInt() / 100.0 }
        val roundConcMgMl = (concMgMl * 1000.0).roundToInt() / 1000.0
        val roundConcMcgMl = (concMcgMl * 10.0).roundToInt() / 10.0

        val steps = buildString {
            append("1. Solution Concentration:\n")
            append("   $drugAmountMg mg ÷ $bagVolumeMl mL = $roundConcMgMl mg/mL ($roundConcMcgMl mcg/mL)\n")
            append("2. Dose Conversion:\n")
            when (doseUnit) {
                InfusionDoseUnit.MCG_KG_MIN -> {
                    append("   Dose = $doseRate mcg/kg/min × $weightKg kg = $roundMcgMin mcg/min\n")
                    append("   Hourly Delivery = $roundMcgMin mcg/min × 60 min = ${(roundMcgMin * 60 * 10).roundToInt() / 10.0} mcg/hr\n")
                    append("3. Pump Infusion Rate:\n")
                    append("   Rate = Hourly Delivery ÷ Concentration ($roundConcMcgMl mcg/mL) = $roundMlHr mL/hr")
                }
                InfusionDoseUnit.MCG_KG_HR -> {
                    val wt = weightKg ?: 0.0
                    append("   Hourly Delivery = $doseRate mcg/kg/hr × $wt kg = ${(doseRate * wt * 10).roundToInt() / 10.0} mcg/hr\n")
                    append("3. Pump Infusion Rate:\n")
                    append("   Rate = Hourly Delivery ÷ Concentration ($roundConcMcgMl mcg/mL) = $roundMlHr mL/hr")
                }
                InfusionDoseUnit.MCG_MIN -> {
                    append("   Hourly Delivery = $doseRate mcg/min × 60 min = ${(doseRate * 60 * 10).roundToInt() / 10.0} mcg/hr\n")
                    append("3. Pump Infusion Rate:\n")
                    append("   Rate = Hourly Delivery ÷ Concentration ($roundConcMcgMl mcg/mL) = $roundMlHr mL/hr")
                }
                InfusionDoseUnit.MG_HR -> {
                    append("   Hourly Delivery = $doseRate mg/hr\n")
                    append("3. Pump Infusion Rate:\n")
                    append("   Rate = $doseRate mg/hr ÷ $roundConcMgMl mg/mL = $roundMlHr mL/hr")
                }
            }
        }

        val safety = "Safety Alert: Always perform independent double-check of smart infusion pump drug library, concentration, and rate setting prior to opening clamp or starting infusion."

        return InfusionRateResult(
            rateMlHr = roundMlHr,
            drugDeliveryMgHr = roundMgHr,
            drugDeliveryMcgMin = roundMcgMin,
            drugDeliveryMcgKgMin = roundMcgKgMin,
            concentrationMgMl = roundConcMgMl,
            concentrationMcgMl = roundConcMcgMl,
            calculationSteps = steps,
            safetyWarning = safety
        )
    }

    // 10. Corrected Sodium for Hyperglycemia
    data class CorrectedSodiumResult(
        val measuredNa: Double,
        val glucoseMgDl: Double,
        val glucoseMmolL: Double,
        val katzCorrectedNa: Double,
        val hillierCorrectedNa: Double,
        val osmoticShiftDelta: Double,
        val formulaExplanation: String,
        val clinicalGuidance: String
    )

    fun calculateCorrectedSodium(
        measuredNa: Double,
        glucoseValue: Double,
        isMmolL: Boolean = false
    ): CorrectedSodiumResult? {
        if (measuredNa !in 80.0..190.0 || glucoseValue <= 0.0) return null

        val glucoseMgDl = if (isMmolL) glucoseValue * 18.0182 else glucoseValue
        if (glucoseMgDl > 2500.0) return null

        val glucoseMmolL = glucoseMgDl / 18.0182
        val excessGlucosePer100 = max(0.0, (glucoseMgDl - 100.0) / 100.0)

        // Katz formula: Na + 1.6 * ((Glucose - 100) / 100)
        val katzNa = measuredNa + (1.6 * excessGlucosePer100)
        // Hillier formula: Na + 2.4 * ((Glucose - 100) / 100)
        val hillierNa = measuredNa + (2.4 * excessGlucosePer100)

        val roundKatz = (katzNa * 10.0).roundToInt() / 10.0
        val roundHillier = (hillierNa * 10.0).roundToInt() / 10.0
        val roundMgDl = (glucoseMgDl * 10.0).roundToInt() / 10.0
        val roundMmolL = (glucoseMmolL * 10.0).roundToInt() / 10.0
        val delta = ((roundHillier - measuredNa) * 10.0).roundToInt() / 10.0

        val formula = "Osmotic Translocation:\n" +
                "• Measured Na: $measuredNa mEq/L\n" +
                "• Blood Glucose: $roundMgDl mg/dL ($roundMmolL mmol/L)\n" +
                "• Excess Glucose: ${(excessGlucosePer100 * 100.0).roundToInt()} mg/dL above 100\n\n" +
                "Formulas Applied:\n" +
                "1. Katz (Classic 1973): Na + 1.6 × ((Glucose - 100) / 100) = $roundKatz mEq/L\n" +
                "2. Hillier et al. (1999): Na + 2.4 × ((Glucose - 100) / 100) = $roundHillier mEq/L"

        val guidance = "Physiologic Context: Hyperglycemia exerts an osmotic gradient drawing free water from intracellular to extracellular space, diluting serum sodium (translocational pseudohyponatremia). Hillier's 2.4 factor is clinically validated for glucose > 400 mg/dL (Am J Med 1999). If corrected sodium remains < 135 mEq/L, true hyponatremia co-exists."

        return CorrectedSodiumResult(
            measuredNa = measuredNa,
            glucoseMgDl = roundMgDl,
            glucoseMmolL = roundMmolL,
            katzCorrectedNa = roundKatz,
            hillierCorrectedNa = roundHillier,
            osmoticShiftDelta = delta,
            formulaExplanation = formula,
            clinicalGuidance = guidance
        )
    }

    // 11. Alveolar Gas Equation (PAO2) and A-a Gradient
    data class AlveolarGasResult(
        val pao2Calculated: Double,
        val aaGradient: Double?,
        val expectedAaGradient: Double?,
        val isElevatedAaGradient: Boolean?,
        val pio2: Double,
        val formulaSteps: String,
        val clinicalDifferentials: String
    )

    fun calculateAlveolarGas(
        fio2Percent: Double,
        paco2: Double,
        measuredPao2: Double? = null,
        patientAge: Double? = null,
        barometricPressureMmhg: Double = 760.0,
        waterVaporPressureMmhg: Double = 47.0,
        respiratoryQuotient: Double = 0.8
    ): AlveolarGasResult? {
        if (fio2Percent !in 21.0..100.0 || paco2 !in 5.0..150.0 || barometricPressureMmhg !in 400.0..850.0) return null
        if (measuredPao2 != null && (measuredPao2 <= 0.0 || measuredPao2 > 800.0)) return null
        if (patientAge != null && (patientAge < 0.0 || patientAge > 125.0)) return null

        val fio2Fraction = fio2Percent / 100.0
        val pio2 = fio2Fraction * (barometricPressureMmhg - waterVaporPressureMmhg)
        val pao2 = pio2 - (paco2 / respiratoryQuotient)
        val roundedPao2 = (pao2 * 10.0).roundToInt() / 10.0
        val roundedPio2 = (pio2 * 10.0).roundToInt() / 10.0

        var aaGrad: Double? = null
        var expectedGrad: Double? = null
        var isElevated: Boolean? = null

        if (measuredPao2 != null) {
            val grad = pao2 - measuredPao2
            aaGrad = (grad * 10.0).roundToInt() / 10.0

            if (patientAge != null) {
                val exp = (patientAge / 4.0) + 4.0
                expectedGrad = (exp * 10.0).roundToInt() / 10.0
                isElevated = aaGrad > expectedGrad
            } else {
                isElevated = aaGrad > 15.0
            }
        }

        val steps = buildString {
            append("1. Inspired O₂ Tension (PiO₂):\n")
            append("   PiO₂ = FiO₂ ($fio2Percent%) × (P_atm [$barometricPressureMmhg mmHg] - P_H2O [$waterVaporPressureMmhg mmHg]) = $roundedPio2 mmHg\n\n")
            append("2. Alveolar O₂ Tension (PAO₂):\n")
            append("   PAO₂ = PiO₂ - (PaCO₂ [$paco2 mmHg] ÷ R [$respiratoryQuotient]) = $roundedPao2 mmHg\n")
            if (measuredPao2 != null) {
                append("\n3. A-a Oxygen Gradient:\n")
                append("   A-a Gradient = PAO₂ ($roundedPao2) - PaO₂ ($measuredPao2) = $aaGrad mmHg\n")
                if (expectedGrad != null) {
                    append("   Expected Normal for Age $patientAge yr = (Age ÷ 4) + 4 = $expectedGrad mmHg")
                }
            }
        }

        val diff = if (isElevated == true) {
            "ELEVATED A-a Gradient (Defect in Gas Exchange):\n" +
                    "• Right-to-Left Shunt: Atelectasis, intracardiac shunt (PFO/ASD), severe ARDS, pulmonary arteriovenous malformation (does NOT correct with 100% O2).\n" +
                    "• V/Q Mismatch: Pulmonary embolism, bronchospasm, mucus plugging, pneumonia, COPD exacerbation (corrects with supplementary O2).\n" +
                    "• Diffusion Impairment: Interstitial pulmonary fibrosis, pulmonary edema."
        } else if (isElevated == false) {
            "NORMAL A-a Gradient (Pure Hypoventilation or Low FiO2):\n" +
                    "• Hypoventilation: Opioid overdose, residual neuromuscular blockade, central sleep apnea, chest wall trauma.\n" +
                    "• High Altitude / Low Atmospheric Pressure: Reduced ambient PiO2 with intact alveolar-capillary membrane."
        } else {
            "Enter arterial PaO₂ from blood gas to evaluate alveolar-arterial oxygen gradient and distinguish V/Q mismatch/shunt from pure hypoventilation."
        }

        return AlveolarGasResult(
            pao2Calculated = roundedPao2,
            aaGradient = aaGrad,
            expectedAaGradient = expectedGrad,
            isElevatedAaGradient = isElevated,
            pio2 = roundedPio2,
            formulaSteps = steps,
            clinicalDifferentials = diff
        )
    }
}
