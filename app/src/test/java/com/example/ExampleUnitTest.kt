package com.example

import com.example.data.repository.CalculatorsEngine
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testBmiAndIdealBodyWeightDevine() {
        // 6 foot tall male (72 inches, 12 inches over 60) -> 50 + 2.3*12 = 77.6 kg
        val result = CalculatorsEngine.calculateBmiAndIbw(weightKg = 80.0, heightCm = 182.88, isMale = true)
        assertNotNull(result)
        assertEquals(77.6, result!!.ibwKg, 0.5)
        assertEquals(23.9, result.bmi, 0.5)

        // Female 5 ft 4 in (162.56 cm, 4 in over 60) -> 45.5 + 2.3*4 = 54.7 kg
        val femaleResult = CalculatorsEngine.calculateBmiAndIbw(weightKg = 60.0, heightCm = 162.56, isMale = false)
        assertNotNull(femaleResult)
        assertEquals(54.7, femaleResult!!.ibwKg, 0.5)

        // Obese patient Adjusted Body Weight calculation: IBW + 0.4 * (TBW - IBW)
        // Male: 120 kg, 175 cm (68.9 in -> 50 + 2.3*8.9 = 70.47 kg IBW)
        // AdjBW = 70.47 + 0.4*(120 - 70.47) = 90.28 kg
        val obeseResult = CalculatorsEngine.calculateBmiAndIbw(weightKg = 120.0, heightCm = 175.0, isMale = true)
        assertNotNull(obeseResult)
        assertEquals(70.5, obeseResult!!.ibwKg, 0.5)
        assertEquals(90.3, obeseResult.adjBwKg, 0.5)
        assertTrue(obeseResult.bmiCategory.contains("Obesity"))
    }

    @Test
    fun testMeanArterialPressure() {
        // BP 120/80 -> MAP = 80 + (40/3) = 93.3 mmHg
        val result = CalculatorsEngine.calculateMap(sbp = 120.0, dbp = 80.0)
        assertNotNull(result)
        assertEquals(93.3, result!!.mapMmhg, 0.2)
        assertEquals(40.0, result.pulsePressure, 0.1)

        // Critical low perfusion
        val lowResult = CalculatorsEngine.calculateMap(sbp = 75.0, dbp = 45.0)
        assertNotNull(lowResult)
        assertEquals(55.0, lowResult!!.mapMmhg, 0.2)
        assertTrue(lowResult.clinicalStatus.contains("Critical Hypoperfusion"))

        // Invalid inputs check
        assertNull(CalculatorsEngine.calculateMap(sbp = 70.0, dbp = 90.0)) // SBP < DBP
        assertNull(CalculatorsEngine.calculateMap(sbp = -10.0, dbp = 50.0)) // Negative input
    }

    @Test
    fun testPediatricEttFormulas() {
        // 4-year-old child:
        // Cuffed = 4/4 + 3.5 = 4.5 mm
        // Uncuffed = 4/4 + 4 = 5.0 mm
        // Oral depth = 4/2 + 12 = 14 cm
        val result = CalculatorsEngine.calculatePediatricEtt(ageYears = 4.0)
        assertNotNull(result)
        assertEquals(4.5, result!!.cuffedSizeMm, 0.1)
        assertEquals(5.0, result.uncuffedSizeMm, 0.1)
        assertEquals(14.0, result.oralDepthCm, 0.1)
        assertEquals(16.5, result.nasalDepthCm, 0.1)
    }

    @Test
    fun testNeonatePediatricEtt() {
        // Neonate age < 1 month (0.02 years)
        val result = CalculatorsEngine.calculatePediatricEtt(ageYears = 0.02)
        assertNotNull(result)
        assertEquals(2.5, result!!.cuffedSizeMm, 0.1)
        assertEquals(3.0, result.uncuffedSizeMm, 0.1)
        assertEquals(9.0, result.oralDepthCm, 0.1)
    }

    @Test
    fun testLocalAnestheticDoseLimitsAndIbwCap() {
        // 70 kg adult, plain lidocaine (4.5 mg/kg, ceiling 300 mg)
        // 70 * 4.5 = 315 mg -> capped at 300 mg ceiling
        val result = CalculatorsEngine.calculateLocalAnestheticLimit(
            weightKg = 70.0,
            drugType = CalculatorsEngine.LocalAnestheticType.LIDOCAINE_PLAIN,
            selectedConcentrationPercent = 1.0 // 1% = 10 mg/mL
        )
        assertNotNull(result)
        assertEquals(300.0, result!!.maxDoseMg, 0.1)
        assertEquals(30.0, result.maxVolumeMl, 0.1) // 300 mg / 10 mg/mL = 30 mL

        // Obese patient: Actual weight 110 kg, IBW 65 kg
        // Bupivacaine Plain: 2.0 mg/kg, ceiling 175 mg
        // Actual wt calculation: 110 * 2.0 = 220 mg -> capped at 175 mg
        // IBW calculation: 65 * 2.0 = 130 mg (< 175 mg ceiling!)
        val obeseLa = CalculatorsEngine.calculateLocalAnestheticLimit(
            weightKg = 110.0,
            drugType = CalculatorsEngine.LocalAnestheticType.BUPIVACAINE_PLAIN,
            selectedConcentrationPercent = 0.5, // 0.5% = 5 mg/mL
            ibwKg = 65.0
        )
        assertNotNull(obeseLa)
        assertEquals(175.0, obeseLa!!.maxDoseMg, 0.1)
        assertEquals(130.0, obeseLa.ibwMaxDoseMg!!, 0.1)
        assertEquals(26.0, obeseLa.ibwMaxVolumeMl!!, 0.1) // 130 mg / 5 mg/mL = 26 mL
        assertTrue(obeseLa.weightBasisGuidance.contains("ASRA Practice Advisory recommends"))
    }

    @Test
    fun testFluidMaintenance421Rule() {
        // 70 kg adult: 10*4 + 10*2 + 50*1 = 40 + 20 + 50 = 110 mL/hr
        val result = CalculatorsEngine.calculate421Fluid(weightKg = 70.0)
        assertNotNull(result)
        assertEquals(110.0, result!!.hourlyRateMlHr, 0.1)
        assertEquals(2640.0, result.dailyVolumeMlDay, 1.0)

        // Pediatric 15 kg: 10*4 + 5*2 = 50 mL/hr
        val pedsResult = CalculatorsEngine.calculate421Fluid(weightKg = 15.0)
        assertNotNull(pedsResult)
        assertEquals(50.0, pedsResult!!.hourlyRateMlHr, 0.1)
    }

    @Test
    fun testOxygenCylinderDuration() {
        // 1000 psi, 4 L/min flow on E-Cylinder (factor 0.28).
        // Usable psi = 1000 - 200 = 800 psi.
        // Usable liters = 800 * 0.28 = 224 L.
        // Duration = 224 / 4 = 56 minutes.
        val result = CalculatorsEngine.calculateOxygenDuration(
            gaugePsi = 1000.0,
            flowRateLpm = 4.0,
            cylinderType = CalculatorsEngine.OxygenCylinderType.E_CYLINDER
        )
        assertNotNull(result)
        assertEquals(56.0, result!!.remainingMinutes, 0.5)
        assertEquals(224.0, result.usableLiters, 0.5)

        // H-Cylinder (Bulk, factor 3.14): 1000 psi at 10 L/min
        // Usable psi = 800 -> 800 * 3.14 = 2512 L -> 2512 / 10 = 251.2 min (4.2 hrs)
        val hResult = CalculatorsEngine.calculateOxygenDuration(
            gaugePsi = 1000.0,
            flowRateLpm = 10.0,
            cylinderType = CalculatorsEngine.OxygenCylinderType.H_K_CYLINDER
        )
        assertNotNull(hResult)
        assertEquals(251.2, hResult!!.remainingMinutes, 1.0)

        // Critical low pressure (at or below 200 psi reserve)
        val emptyResult = CalculatorsEngine.calculateOxygenDuration(
            gaugePsi = 180.0,
            flowRateLpm = 4.0
        )
        assertNotNull(emptyResult)
        assertEquals(0.0, emptyResult!!.remainingMinutes, 0.1)
        assertTrue(emptyResult.alert.contains("CRITICAL EMPTY"))
    }

    @Test
    fun testAnionGap() {
        // Na 140, Cl 104, HCO3 24 -> AG = 140 - (104 + 24) = 12 mEq/L
        val result = CalculatorsEngine.calculateAnionGap(na = 140.0, cl = 104.0, hco3 = 24.0)
        assertNotNull(result)
        assertEquals(12.0, result!!.anionGap, 0.1)

        // Negative Anion Gap (e.g. severe bromide toxicity / lab artifact)
        val negResult = CalculatorsEngine.calculateAnionGap(na = 135.0, cl = 120.0, hco3 = 20.0)
        assertNotNull(negResult)
        assertEquals(-5.0, negResult!!.anionGap, 0.1)
        assertTrue(negResult.interpretation.contains("Negative Anion Gap"))

        // High AGMA (>12)
        val hagmaResult = CalculatorsEngine.calculateAnionGap(na = 140.0, cl = 95.0, hco3 = 15.0)
        assertNotNull(hagmaResult)
        assertEquals(30.0, hagmaResult!!.anionGap, 0.1)
        assertTrue(hagmaResult.interpretation.contains("High Anion Gap"))
    }

    @Test
    fun testMaximumAllowableBloodLoss() {
        // 70 kg adult male (EBV 75 mL/kg = 5250 mL), start Hct 40%, target Hct 30%
        // MABL = 5250 * (40 - 30)/40 = 5250 * 0.25 = 1312.5 mL
        val result = CalculatorsEngine.calculateMabl(
            weightKg = 70.0,
            category = CalculatorsEngine.PatientCategory.ADULT_MALE,
            initialHct = 40.0,
            targetHct = 30.0
        )
        assertNotNull(result)
        assertEquals(5250.0, result!!.ebvMl, 0.5)
        assertEquals(1312.5, result.mablMl, 0.5)
    }

    @Test
    fun testInfusionRateNorepinephrine() {
        // Patient 70 kg, Norepinephrine 0.1 mcg/kg/min
        // Solution: 4 mg in 250 mL D5W -> conc = 4 / 250 = 0.016 mg/mL = 16 mcg/mL
        // Dose delivery = 0.1 * 70 = 7.0 mcg/min = 420 mcg/hr = 0.42 mg/hr
        // Pump rate = 420 mcg/hr / 16 mcg/mL = 26.25 mL/hr
        val result = CalculatorsEngine.calculateInfusionRate(
            doseRate = 0.1,
            doseUnit = CalculatorsEngine.InfusionDoseUnit.MCG_KG_MIN,
            weightKg = 70.0,
            drugAmountMg = 4.0,
            bagVolumeMl = 250.0
        )
        assertNotNull(result)
        assertEquals(26.25, result!!.rateMlHr, 0.05)
        assertEquals(7.0, result.drugDeliveryMcgMin, 0.1)
        assertEquals(0.42, result.drugDeliveryMgHr, 0.01)
        assertEquals(16.0, result.concentrationMcgMl, 0.1)
    }

    @Test
    fun testInfusionRateMgHrAndMcgMin() {
        // Propofol 50 mg/hr
        // Solution: 1000 mg in 100 mL (1% = 10 mg/mL)
        // Rate = 50 / 10 = 5.0 mL/hr
        val propofol = CalculatorsEngine.calculateInfusionRate(
            doseRate = 50.0,
            doseUnit = CalculatorsEngine.InfusionDoseUnit.MG_HR,
            weightKg = 70.0,
            drugAmountMg = 1000.0,
            bagVolumeMl = 100.0
        )
        assertNotNull(propofol)
        assertEquals(5.0, propofol!!.rateMlHr, 0.01)
        assertEquals(50.0, propofol.drugDeliveryMgHr, 0.01)
        assertEquals(10.0, propofol.concentrationMgMl, 0.1)
    }

    @Test
    fun testCorrectedSodiumKatzAndHillier() {
        // Measured Na = 130 mEq/L, Glucose = 500 mg/dL (excess = 400 mg/dL = 4 units of 100)
        // Katz (+1.6 per 100): 130 + 1.6*4 = 136.4 mEq/L
        // Hillier (+2.4 per 100): 130 + 2.4*4 = 139.6 mEq/L
        val result = CalculatorsEngine.calculateCorrectedSodium(
            measuredNa = 130.0,
            glucoseValue = 500.0,
            isMmolL = false
        )
        assertNotNull(result)
        assertEquals(136.4, result!!.katzCorrectedNa, 0.2)
        assertEquals(139.6, result.hillierCorrectedNa, 0.2)
        assertEquals(9.6, result.osmoticShiftDelta, 0.2)

        // mmol/L test: 27.75 mmol/L ≈ 500 mg/dL
        val mmolResult = CalculatorsEngine.calculateCorrectedSodium(
            measuredNa = 130.0,
            glucoseValue = 27.75,
            isMmolL = true
        )
        assertNotNull(mmolResult)
        assertEquals(136.4, mmolResult!!.katzCorrectedNa, 0.3)
        assertEquals(139.6, mmolResult.hillierCorrectedNa, 0.3)
    }

    @Test
    fun testAlveolarGasEquationAndAaGradient() {
        // Room air at sea level: FiO2 = 21%, PaCO2 = 40 mmHg, PaO2 = 95 mmHg, Age = 40 yr
        // PiO2 = 0.21 * (760 - 47) = 0.21 * 713 = 149.73 mmHg
        // PAO2 = 149.73 - (40 / 0.8) = 149.73 - 50 = 99.7 mmHg
        // A-a Gradient = 99.7 - 95 = 4.7 mmHg
        // Expected Normal for age 40 = (40 / 4) + 4 = 14 mmHg
        // 4.7 <= 14 -> Normal A-a gradient
        val normalAir = CalculatorsEngine.calculateAlveolarGas(
            fio2Percent = 21.0,
            paco2 = 40.0,
            measuredPao2 = 95.0,
            patientAge = 40.0,
            barometricPressureMmhg = 760.0
        )
        assertNotNull(normalAir)
        assertEquals(99.7, normalAir!!.pao2Calculated, 0.5)
        assertEquals(4.7, normalAir.aaGradient!!, 0.5)
        assertEquals(14.0, normalAir.expectedAaGradient!!, 0.1)
        assertFalse(normalAir.isElevatedAaGradient!!)

        // Elevated A-a gradient (Intrapulmonary Shunt / Atelectasis):
        // FiO2 = 100%, PaCO2 = 40 mmHg, PaO2 = 150 mmHg (severe defect)
        // PiO2 = 1.0 * 713 = 713 mmHg
        // PAO2 = 713 - 50 = 663 mmHg
        // A-a Gradient = 663 - 150 = 513 mmHg (massively elevated!)
        val shuntGas = CalculatorsEngine.calculateAlveolarGas(
            fio2Percent = 100.0,
            paco2 = 40.0,
            measuredPao2 = 150.0,
            patientAge = 40.0
        )
        assertNotNull(shuntGas)
        assertEquals(663.0, shuntGas!!.pao2Calculated, 0.5)
        assertEquals(513.0, shuntGas.aaGradient!!, 0.5)
        assertTrue(shuntGas.isElevatedAaGradient!!)
        assertTrue(shuntGas.clinicalDifferentials.contains("ELEVATED A-a Gradient"))
    }

    @Test
    fun testBoundaryAndInvalidInputs() {
        // Zero or negative weights
        assertNull(CalculatorsEngine.calculateBmiAndIbw(weightKg = 0.0, heightCm = 170.0, isMale = true))
        assertNull(CalculatorsEngine.calculate421Fluid(weightKg = -5.0))
        assertNull(CalculatorsEngine.calculateMabl(0.0, CalculatorsEngine.PatientCategory.ADULT_MALE, 40.0, 30.0))

        // Pediatric ETT out of range (>18 yr)
        assertNull(CalculatorsEngine.calculatePediatricEtt(25.0))

        // Alveolar Gas invalid FiO2
        assertNull(CalculatorsEngine.calculateAlveolarGas(fio2Percent = 15.0, paco2 = 40.0))

        // Infusion rate missing weight for weight-based unit
        assertNull(CalculatorsEngine.calculateInfusionRate(
            doseRate = 0.1,
            doseUnit = CalculatorsEngine.InfusionDoseUnit.MCG_KG_MIN,
            weightKg = null,
            drugAmountMg = 4.0,
            bagVolumeMl = 250.0
        ))
    }

    @Test
    fun testAllTwelveDrugCategoriesCovered() {
        val allDrugs = com.example.data.repository.PharmacologyData.drugs
        assertTrue("Drug count should be at least 30", allDrugs.size >= 30)

        // Verify each of the 12 required categories is populated with verified drugs
        for (category in com.example.data.model.DrugCategory.values()) {
            val categoryDrugs = com.example.data.repository.PharmacologyData.getDrugsByCategory(category)
            assertTrue("Category ${category.displayName} must contain at least 2 monographs", categoryDrugs.size >= 2)
        }
    }

    @Test
    fun testPharmacologySearchAndSynonymMatching() {
        val allDrugs = com.example.data.repository.PharmacologyData.drugs

        // Search by generic
        val propofol = com.example.data.repository.PharmacologyData.searchDrugs("Propofol")
        assertTrue(propofol.any { it.id == "drug_propofol" })

        // Search by trade/brand name
        val dilaudid = com.example.data.repository.PharmacologyData.searchDrugs("Dilaudid")
        assertTrue(dilaudid.any { it.id == "drug_hydromorphone" })

        // Search by synonym
        val levophed = com.example.data.repository.PharmacologyData.searchDrugs("Levophed")
        assertTrue(levophed.any { it.id == "drug_norepinephrine" })

        val versed = com.example.data.repository.PharmacologyData.searchDrugs("Versed")
        assertTrue(versed.any { it.id == "drug_midazolam" })

        val ryanodex = com.example.data.repository.PharmacologyData.searchDrugs("Ryanodex")
        assertTrue(ryanodex.any { it.id == "drug_dantrolene" })

        // Search by indication
        val pphDrugs = com.example.data.repository.PharmacologyData.searchDrugs("postpartum hemorrhage")
        assertTrue(pphDrugs.any { it.id == "drug_oxytocin" })
        assertTrue(pphDrugs.any { it.id == "drug_tranexamic_acid" })

        // Search by target receptor
        val gabaDrugs = com.example.data.repository.PharmacologyData.searchDrugs("GABA-A")
        assertTrue(gabaDrugs.any { it.id == "drug_propofol" })
        assertTrue(gabaDrugs.any { it.id == "drug_etomidate" })
    }

    @Test
    fun testClinicalDosingAccuracyAndUnitsIntegrity() {
        val fentanyl = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_fentanyl" }
        // Verify Fentanyl is strictly dosed in MICROGRAMS (mcg), not mg
        assertTrue("Fentanyl must use microgram (mcg) dosing", fentanyl.adultDosing.contains("mcg/kg"))

        val morphine = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_morphine" }
        // Verify Morphine is dosed in milligrams (mg)
        assertTrue("Morphine must use milligram (mg) dosing", morphine.adultDosing.contains("mg"))

        val epinephrine = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_epinephrine" }
        // Cardiac arrest 1.0 mg vs anaphylaxis 0.3-0.5 mg
        assertTrue(epinephrine.adultDosing.contains("1.0 mg"))
        assertTrue(epinephrine.adultDosing.contains("0.3 - 0.5 mg"))

        val sugammadex = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_sugammadex" }
        // Emergency CICO rescue is 16 mg/kg
        assertTrue(sugammadex.adultDosing.contains("16.0 mg/kg"))

        val dantrolene = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_dantrolene" }
        // MH dose is 2.5 mg/kg IV
        assertTrue(dantrolene.adultDosing.contains("2.5 mg/kg"))

        val succinylcholine = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_succinylcholine" }
        // Succinylcholine dosed on Total Body Weight (TBW)
        assertTrue(succinylcholine.adultDosing.contains("Total Body Weight"))

        val rocuronium = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_rocuronium" }
        // Rocuronium dosed on Ideal Body Weight (IBW)
        assertTrue(rocuronium.adultDosing.contains("Ideal Body Weight"))
    }

    @Test
    fun testToxicityAntidotesAndReversals() {
        val bupivacaine = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_bupivacaine" }
        assertTrue("Bupivacaine LAST antidote must specify 20% Lipid Emulsion", bupivacaine.reversalAgent!!.contains("Lipid Emulsion"))

        val neostigmine = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_neostigmine" }
        assertTrue("Neostigmine must be paired with Glycopyrrolate or Atropine", neostigmine.adultDosing.contains("Glycopyrrolate"))

        val midazolam = com.example.data.repository.PharmacologyData.drugs.first { it.id == "drug_midazolam" }
        assertTrue("Midazolam reversal must specify Flumazenil", midazolam.reversalAgent!!.contains("Flumazenil"))
    }

    @Test
    fun testAnatomyNineSystemsCompleteCoverage() {
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics
        assertEquals("Must contain 9 complete anatomy topics", 9, topics.size)

        val categories = topics.map { it.category }.toSet()
        assertEquals("All 9 AnatomySystemCategory entries must be represented", com.example.data.model.AnatomySystemCategory.entries.size, categories.size)
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.GENERAL_CELLULAR))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.RESPIRATORY_AIRWAY))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.CARDIOVASCULAR_HEMODYNAMICS))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.NERVOUS_SYSTEM))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.RENAL_ELECTROLYTES))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.HEPATIC_METABOLISM))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.ENDOCRINE_SYSTEM))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.PREGNANCY_NEONATAL))
        assertTrue(categories.contains(com.example.data.model.AnatomySystemCategory.SURGICAL_PROCEDURAL))
    }

    @Test
    fun testAnatomyTopicRequiredFields() {
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics
        for (topic in topics) {
            assertTrue("Topic ${topic.id} title must not be blank", topic.title.isNotBlank())
            assertTrue("Topic ${topic.id} subtitle must not be blank", topic.subtitle.isNotBlank())
            assertTrue("Topic ${topic.id} must have >= 3 learning objectives", topic.learningObjectives.size >= 3)
            assertTrue("Topic ${topic.id} must have >= 3 anatomical structures", topic.structures.size >= 3)
            assertTrue("Topic ${topic.id} normal physiology must not be blank", topic.normalPhysiology.isNotBlank())
            assertTrue("Topic ${topic.id} clinical significance must not be blank", topic.clinicalSignificance.isNotBlank())
            assertTrue("Topic ${topic.id} anesthesia implications must not be blank", topic.anesthesiaImplications.isNotBlank())
            assertTrue("Topic ${topic.id} must have >= 3 exam distinctions", topic.commonExamDistinctions.size >= 3)
            assertTrue("Topic ${topic.id} must have >= 2 review questions", topic.reviewQuestions.size >= 2)
        }
    }

    @Test
    fun testAnatomyReviewQuestionsValidation() {
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics
        for (topic in topics) {
            for (q in topic.reviewQuestions) {
                assertTrue("Question stem must not be blank", q.question.isNotBlank())
                assertTrue("Question must have at least 3 options", q.options.size >= 3)
                assertTrue("Correct index must be within options range", q.correctIndex in 0 until q.options.size)
                assertTrue("Explanation must be provided", q.explanation.isNotBlank())
            }
        }
    }

    @Test
    fun testAnatomySurgicalGuideCrossLinks() {
        val allGuides = com.example.data.repository.SurgicalAndEquipmentData.surgicalGuides.map { it.id }.toSet()
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics

        for (topic in topics) {
            for (guideId in topic.relatedSurgicalGuideIds) {
                assertTrue(
                    "Surgical guide link '$guideId' in topic ${topic.id} must exist in SurgicalAndEquipmentData",
                    allGuides.contains(guideId)
                )
            }
        }
    }

    @Test
    fun testAnatomyAssetLicensingMetadata() {
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics
        for (topic in topics) {
            val meta = topic.assetMetadata
            assertTrue("Asset title must not be blank in ${topic.id}", meta.assetTitle.isNotBlank())
            assertTrue("Creator or institution must not be blank in ${topic.id}", meta.creatorOrInstitution.isNotBlank())
            assertTrue("Source URL must not be blank in ${topic.id}", meta.sourceUrl.isNotBlank())
            assertTrue("License & attribution must not be blank in ${topic.id}", meta.licenseAndAttribution.isNotBlank())
            assertTrue("Verification date must not be blank in ${topic.id}", meta.verificationDate.isNotBlank())
            assertTrue("Intrinsic aspect ratio must be positive in ${topic.id}", meta.intrinsicAspectRatio > 1.0f)
        }
    }

    @Test
    fun testAnatomyAssetVerificationStatusDistribution() {
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics
        val verifiedTopics = topics.filter { it.assetMetadata.status == com.example.data.model.AssetVerificationStatus.CLINICALLY_REVIEWED }
        val missingTopics = topics.filter { it.assetMetadata.status == com.example.data.model.AssetVerificationStatus.MISSING }

        // All 9 topics now have verified clinical drawables (100% full coverage in Visual Learning Expansion)
        assertEquals("All 9 topics must have verified clinical drawables", 9, verifiedTopics.size)
        assertEquals("Zero topics flagged as MISSING local asset", 0, missingTopics.size)

        for (t in verifiedTopics) {
            assertNotNull("Verified topic ${t.id} must have a non-null local drawable resource", t.assetMetadata.localDrawableRes)
            assertTrue(t.assetMetadata.isVerified)
            assertFalse(t.assetMetadata.isMissing)
        }
    }

    @Test
    fun testPharmacologyVisualDiagramsCoverage() {
        val drugs = com.example.data.repository.PharmacologyData.drugs
        val visualDrugs = drugs.filter { it.diagramType != null || it.pkCurveType != null }
        assertTrue("At least 6 representative drugs must have visual mechanism diagrams", visualDrugs.size >= 6)

        val propofol = drugs.first { it.id == "drug_propofol" }
        assertEquals("GABA_A_RECEPTOR", propofol.diagramType)
        assertEquals("MULTI_COMPARTMENT_DISTRIBUTION", propofol.pkCurveType)

        val fentanyl = drugs.first { it.id == "drug_fentanyl" }
        assertEquals("MU_OPIOID_GPCR", fentanyl.diagramType)

        val bupivacaine = drugs.first { it.id == "drug_bupivacaine" }
        assertEquals("VOLTAGE_GATED_SODIUM_CHANNEL", bupivacaine.diagramType)

        val succinylcholine = drugs.first { it.id == "drug_succinylcholine" }
        assertEquals("NMJ_NICOTINIC_ACHR", succinylcholine.diagramType)

        val rocuronium = drugs.first { it.id == "drug_rocuronium" }
        assertEquals("NMJ_NICOTINIC_ACHR", rocuronium.diagramType)

        val dantrolene = drugs.first { it.id == "drug_dantrolene" }
        assertEquals("NMJ_NICOTINIC_ACHR", dantrolene.diagramType)

        val epinephrine = drugs.first { it.id == "drug_epinephrine" }
        assertEquals("ADRENERGIC_ALPHA_BETA", epinephrine.diagramType)
    }

    @Test
    fun testLetterboxCoordinateMappingFormula() {
        // Test Case 1: Container wider than image (Pillarboxed)
        // Container = 500 x 250 (aspect 2.0). Image = 1376 x 768 (aspect ~1.7917)
        val imgAspect = 1376f / 768f
        val containerW1 = 500f
        val containerH1 = 250f
        val containerAspect1 = containerW1 / containerH1 // 2.0 > 1.7917 -> Height-constrained
        val displayedH1 = containerH1
        val displayedW1 = containerH1 * imgAspect // 250 * 1.7917 = 447.92
        val offsetLeft1 = (containerW1 - displayedW1) / 2f // (500 - 447.92)/2 = 26.04
        val offsetTop1 = 0f

        // Pin at normalized (0.5, 0.5) must be centered on the rendered image
        val pinX1 = offsetLeft1 + (0.5f * displayedW1)
        val pinY1 = offsetTop1 + (0.5f * displayedH1)
        assertEquals(250f, pinX1, 0.1f) // Centered horizontally in container
        assertEquals(125f, pinY1, 0.1f) // Centered vertically in container

        // Test Case 2: Container narrower/taller than image (Letterboxed)
        // Container = 300 x 300 (aspect 1.0). Image = 1376 x 768 (aspect ~1.7917)
        val containerW2 = 300f
        val containerH2 = 300f
        val containerAspect2 = containerW2 / containerH2 // 1.0 < 1.7917 -> Width-constrained
        val displayedW2 = containerW2 // 300
        val displayedH2 = containerW2 / imgAspect // 300 / 1.7917 = 167.44
        val offsetLeft2 = 0f
        val offsetTop2 = (containerH2 - displayedH2) / 2f // (300 - 167.44)/2 = 66.28

        val pinX2 = offsetLeft2 + (0.5f * displayedW2)
        val pinY2 = offsetTop2 + (0.5f * displayedH2)
        assertEquals(150f, pinX2, 0.1f) // Centered horizontally
        assertEquals(150f, pinY2, 0.1f) // Centered vertically
    }

    @Test
    fun testAnatomyStructureCoordinatesNormalized() {
        val topics = com.example.data.repository.anatomy.AnatomyRepository.allTopics
        for (topic in topics) {
            for (struct in topic.structures) {
                assertTrue("X coordinate must be between 0 and 1 in ${struct.id}", struct.normalizedCoordinateX in 0.0f..1.0f)
                assertTrue("Y coordinate must be between 0 and 1 in ${struct.id}", struct.normalizedCoordinateY in 0.0f..1.0f)
                assertTrue("Structure name must not be blank in ${struct.id}", struct.name.isNotBlank())
                assertTrue("Clinical pearl must not be blank in ${struct.id}", struct.clinicalPearl.isNotBlank())
            }
        }
    }

    @Test
    fun testAnatomySearchAndCategoryFiltering() {
        // Filter by Respiratory category
        val airwayTopics = com.example.data.repository.anatomy.AnatomyRepository.getTopicsByCategory(
            com.example.data.model.AnatomySystemCategory.RESPIRATORY_AIRWAY
        )
        assertEquals(1, airwayTopics.size)
        assertEquals("anatomy_respiratory_airway", airwayTopics.first().id)

        // Search by keyword
        val larynxResults = com.example.data.repository.anatomy.AnatomyRepository.searchTopics("laryngeal")
        assertTrue(larynxResults.any { it.id == "anatomy_respiratory_airway" })

        val nephronResults = com.example.data.repository.anatomy.AnatomyRepository.searchTopics("nephron")
        assertTrue(nephronResults.any { it.id == "anatomy_renal_electrolytes" })

        val pvtResults = com.example.data.repository.anatomy.AnatomyRepository.searchTopics("Pressure-Volume")
        assertTrue(pvtResults.any { it.id == "anatomy_cardiovascular_hemodynamics" })
    }

    @Test
    fun testSurgicalGuidesComprehensiveCoverage() {
        val guides = com.example.data.repository.SurgicalAndEquipmentData.surgicalGuides
        assertTrue("Must have at least 12 surgical guides, found ${guides.size}", guides.size >= 12)

        // Verify all 11 core surgical specialties are represented
        for (specialty in com.example.data.model.SurgicalSpecialty.values()) {
            val count = guides.count {
                it.surgicalSpecialty.contains(specialty.displayName, ignoreCase = true) ||
                        (specialty == com.example.data.model.SurgicalSpecialty.EMERGENCY_SURGERY && it.surgicalSpecialty.contains("Emergency", ignoreCase = true))
            }
            assertTrue("Specialty ${specialty.displayName} must have at least 1 guide", count >= 1)
        }

        // Verify all 14 clinical sections and governance fields
        val anatomyTopicIds = com.example.data.repository.anatomy.AnatomyRepository.allTopics.map { it.id }.toSet()
        val drugIds = com.example.data.repository.PharmacologyData.drugs.map { it.id }.toSet()
        val equipIds = com.example.data.repository.SurgicalAndEquipmentData.equipmentModules.map { it.id }.toSet()

        for (guide in guides) {
            assertTrue("Procedure name must not be blank in ${guide.id}", guide.procedureName.isNotBlank())
            assertTrue("Overview must not be blank in ${guide.id}", guide.overview.isNotBlank())
            assertTrue("Surgical position must not be blank in ${guide.id}", (guide.surgicalPositionAndPhysiology ?: guide.positioningAndNerveSafety).isNotBlank())
            assertTrue("Relevant anatomy must not be blank in ${guide.id}", guide.relevantAnatomyPhysiology?.isNotBlank() == true)
            assertTrue("Preoperative assessment must not be blank in ${guide.id}", guide.preoperativeAssessment.isNotBlank())
            assertTrue("Airway considerations must not be blank in ${guide.id}", guide.airwayConsiderations.isNotBlank())
            assertTrue("Anesthetic technique must not be blank in ${guide.id}", guide.anestheticTechnique.isNotBlank())
            assertTrue("Monitoring and access must not be blank in ${guide.id}", guide.monitoringAndAccess?.isNotBlank() == true)
            assertTrue("Drug and fluid strategy must not be blank in ${guide.id}", (guide.drugAndFluidStrategy ?: guide.hemodynamicManagement).isNotBlank())
            assertTrue("Blood loss and transfusion must not be blank in ${guide.id}", guide.expectedBloodLossAndTransfusion?.isNotBlank() == true)
            assertTrue("Complications must not be empty in ${guide.id}", guide.potentialComplications.isNotEmpty())
            assertTrue("Analgesia plan must not be blank in ${guide.id}", guide.analgesiaPlan.isNotBlank())
            assertTrue("Postoperative recovery must not be blank in ${guide.id}", guide.postoperativeRecovery.isNotBlank())
            assertTrue("High yield points must not be empty in ${guide.id}", guide.highYieldExamPoints.isNotEmpty())
            assertTrue("Case questions must not be empty in ${guide.id}", guide.caseQuestions.isNotEmpty())
            assertTrue("References must not be empty in ${guide.id}", guide.references.isNotEmpty())

            // Verify cross-module links integrity (zero broken links)
            for (topicId in guide.relatedAnatomyTopicIds) {
                assertTrue("Anatomy topic $topicId in ${guide.id} must exist in AnatomyRepository", anatomyTopicIds.contains(topicId))
            }
            for (drugId in guide.relatedDrugIds) {
                assertTrue("Drug $drugId in ${guide.id} must exist in PharmacologyData", drugIds.contains(drugId))
            }
            for (equipId in guide.relatedEquipmentIds) {
                assertTrue("Equipment $equipId in ${guide.id} must exist in SurgicalAndEquipmentData", equipIds.contains(equipId))
            }
        }
    }

    @Test
    fun testGuideCaseQuestionsIntegrity() {
        val guides = com.example.data.repository.SurgicalAndEquipmentData.surgicalGuides
        for (guide in guides) {
            for (q in guide.caseQuestions) {
                assertTrue("Question text must not be blank in ${q.id}", q.question.isNotBlank())
                assertTrue("Options must have at least 3 items in ${q.id}", q.options.size >= 3)
                assertTrue("Correct index ${q.correctIndex} must be valid for options (${q.options.size}) in ${q.id}", q.correctIndex in q.options.indices)
                assertTrue("Explanation must not be blank in ${q.id}", q.explanation.isNotBlank())
                assertTrue("Exam tag must not be blank in ${q.id}", q.examHighYieldTag.isNotBlank())
            }
        }
    }

    @Test
    fun testEthiopianCurriculumModulesIntegrity() {
        val modules = com.example.data.repository.CurriculumData.modules
        assertTrue("Must have at least 8 curriculum modules", modules.size >= 8)

        // All 4 academic years must be represented
        for (year in com.example.data.model.AcademicYear.values()) {
            val count = modules.count { it.academicYear == year }
            assertTrue("Academic year ${year.romanNumeral} must have modules", count >= 1)
        }

        val allLessons = com.example.data.repository.CurriculumData.lessons
        val allLessonIds = allLessons.map { it.id }.toSet()

        for (mod in modules) {
            assertTrue("Module name must not be blank in ${mod.id}", mod.name.isNotBlank())
            assertTrue("Description must not be blank in ${mod.id}", mod.description.isNotBlank())
            assertTrue("Outcomes must not be empty in ${mod.id}", mod.learningOutcomes.isNotEmpty())
            assertNotNull("Mapping status must not be null in ${mod.id}", mod.mappingStatus)

            // Any lessonIds referenced must exist in CurriculumData.lessons
            for (lessonId in mod.lessonIds) {
                assertTrue("Lesson $lessonId in module ${mod.id} must exist in CurriculumData.lessons", allLessonIds.contains(lessonId))
            }
        }
    }

    @Test
    fun testLessonKnowledgeChecksIntegrity() {
        val lessons = com.example.data.repository.CurriculumData.lessons
        for (lesson in lessons) {
            assertNotNull("AcademicYear must not be null in ${lesson.id}", lesson.academicYear)
            assertTrue("ModuleId must not be blank in ${lesson.id}", lesson.moduleId?.isNotBlank() == true)

            val q = lesson.knowledgeCheckQuestion
            if (q != null) {
                assertTrue("Knowledge check question text must not be blank in ${lesson.id}", q.question.isNotBlank())
                assertTrue("Options must have at least 3 items in ${lesson.id}", q.options.size >= 3)
                assertTrue("Correct index ${q.correctIndex} must be within bounds in ${lesson.id}", q.correctIndex in q.options.indices)
                assertTrue("Explanation must not be blank in ${lesson.id}", q.explanation.isNotBlank())
            }
        }
    }
}



