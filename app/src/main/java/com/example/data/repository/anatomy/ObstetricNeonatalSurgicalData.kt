package com.example.data.repository.anatomy

import com.example.R
import com.example.data.model.*

object ObstetricNeonatalSurgicalData {

    val pregnancyNeonatalTopic = AnatomyTopic(
        id = "anatomy_pregnancy_neonatal",
        title = "Maternal-Fetal Physiology & Transitional Neonatal Adaptations",
        subtitle = "Aortocaval compression, placental transfer, fetal shunts & neonatal respiratory mechanics",
        category = AnatomySystemCategory.PREGNANCY_NEONATAL,
        difficulty = AnatomyDifficulty.ADVANCED,
        learningObjectives = listOf(
            "Quantify maternal cardiovascular, respiratory, gastrointestinal, and hematological adaptations of term pregnancy.",
            "Explain the pathophysiology and prevention of Supine Hypotensive Syndrome (aortocaval compression).",
            "Detail the factors governing placental drug transfer according to Fick's law of diffusion.",
            "Trace the circulatory transitions at birth (closure of ductus arteriosus, foramen ovale, and ductus venosus) and neonatal respiratory mechanics."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "gravid_uterus_aortocaval",
                name = "Gravid Uterus & Inferior Vena Cava Compression",
                normalizedCoordinateX = 0.25f,
                normalizedCoordinateY = 0.28f,
                description = "By week 20 of gestation, the enlarged gravid uterus compresses both the Inferior Vena Cava (IVC) and distal abdominal aorta when the mother is in the supine position.",
                innervationOrVascular = "Reduces venous return to the right atrium by up to 70%, decreasing stroke volume and cardiac output.",
                clinicalPearl = "Supine Hypotensive Syndrome causes maternal hypotension, diaphoresis, nausea, and severe fetal bradycardia. Mandatory intervention: Left Uterine Displacement (LUD) of 15° using a wedge under the right hip or manual displacement.",
                relatedIntervention = "Left Uterine Displacement (LUD) on operating table for all parturients beyond 20 weeks gestation"
            ),
            AnatomicalStructure(
                id = "lud_wedge_relief",
                name = "Left Uterine Displacement (LUD) & Venous Relief",
                normalizedCoordinateX = 0.25f,
                normalizedCoordinateY = 0.75f,
                description = "15-degree wedge placed under maternal right hip displaces gravid uterus off IVC and aorta, restoring preload and cardiac output.",
                innervationOrVascular = "Increases venous return by >50%, stabilizing maternal systolic BP and preserving uteroplacental perfusion.",
                clinicalPearl = "Crucial first-line maneuver during maternal CPR and Cesarean section delivery under regional anesthesia.",
                relatedIntervention = "Place 15° wedge under right hip or manual leftward displacement of uterus"
            ),
            AnatomicalStructure(
                id = "fetal_shunts_transition",
                name = "Fetal Circulatory Shunts (Ductus Venosus & Arteriosus)",
                normalizedCoordinateX = 0.72f,
                normalizedCoordinateY = 0.35f,
                description = "Three physiological shunts bypass the non-ventilated lungs: Ductus Venosus (bypasses liver), Foramen Ovale (right-to-left atrial shunt), and Ductus Arteriosus (pulmonary trunk to aorta).",
                innervationOrVascular = "Umbilical vein carries oxygenated blood (SaO2 ~80-85%, PO2 ~30-35 mmHg) from placenta; two umbilical arteries return deoxygenated blood to placenta.",
                clinicalPearl = "At birth, clamping umbilical cord increases SVR; first breath lowers PVR, reversing flow across ductus arteriosus and functionally closing foramen ovale.",
                relatedIntervention = "Maintain adequate neonatal oxygenation and avoid acidosis to prevent persistent pulmonary hypertension of the newborn (PPHN)"
            ),
            AnatomicalStructure(
                id = "placental_syncytiotrophoblast_barrier",
                name = "Placental Syncytiotrophoblast Barrier",
                normalizedCoordinateX = 0.82f,
                normalizedCoordinateY = 0.72f,
                description = "Maternal blood in intervillous space bathes fetal syncytiotrophoblast villi. Drug transfer follows Fick's law of diffusion.",
                innervationOrVascular = "Dependent on maternal uterine arterial inflow and fetal umbilical blood flow.",
                clinicalPearl = "Low molecular weight (< 500 Da), high lipid solubility, and non-ionized state cross rapidly (e.g. propofol, opioids, local anesthetics). Highly ionized, large molecules cross minimally (Neuromuscular blockers: Succinylcholine, Rocuronium; Heparin; Glycopyrrolate).",
                relatedIntervention = "Fetal drug safety: Succinylcholine and non-depolarizing relaxants do not cross the placenta in clinically significant amounts due to high quaternary ammonium ionization"
            )
        ),
        normalPhysiology = "Maternal adaptations: Cardiac output increases 40-50%, plasma volume increases 45-50% (physiologic anemia of pregnancy), minute ventilation increases 50% (progesterone-mediated respiratory alkalosis, normal PaCO2 ~30-32 mmHg), and minimum alveolar concentration (MAC) decreases by ~30-40% due to elevated progesterone and endorphins.",
        clinicalSignificance = "Gastric emptying is delayed during active labor and with opioid administration. Lower esophageal sphincter (LES) tone is decreased by progesterone while intragastric pressure is increased by the gravid uterus, creating an obligatory full stomach aspiration risk for all surgical deliveries.",
        anesthesiaImplications = "Obstetric airway is 8-10 times more likely to result in difficult or failed intubation due to friable, edematous pharyngeal mucosa, engorged breasts, and shortened neck. Rapid sequence induction (RSI) with a smaller endotracheal tube (size 6.5-7.0 mm) is standard for general anesthesia.",
        commonExamDistinctions = listOf(
            "Maternal blood gas values at term: pH 7.40-7.44, PaO2 100-105 mmHg, PaCO2 30-32 mmHg, HCO3- 20-22 mEq/L (compensated respiratory alkalosis).",
            "Functional Residual Capacity (FRC) decreases by 20% in late pregnancy and by 30-35% when supine; combined with elevated O2 consumption, parturients desaturate with alarming rapidity during induction.",
            "Fetal hemoglobin (HbF) has a left-shifted dissociation curve (P50 = 19 mmHg vs 26.8 mmHg for adult HbA) because it binds 2,3-DPG poorly, allowing it to extract oxygen from maternal blood across the placenta.",
            "Neonate cardiac output is heart-rate dependent because the neonatal left ventricle is non-compliant with fixed stroke volume; bradycardia severely decreases cardiac output."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_ob_1",
                question = "Which class of anesthetic medications does NOT cross the placenta in significant amounts due to high ionization at physiological pH?",
                options = listOf(
                    "Quaternary ammonium neuromuscular blockers (Rocuronium, Vecuronium)",
                    "Volatile halogenated anesthetics (Sevoflurane, Isoflurane)",
                    "Highly lipophilic opioids (Fentanyl, Sufentanil)",
                    "Benzodiazepines (Midazolam, Diazepam)"
                ),
                correctIndex = 0,
                explanation = "Neuromuscular blocking agents possess quaternary ammonium nitrogen atoms that render them completely ionized and water-soluble at physiologic pH. According to Fick's law, ionized polar molecules cannot easily penetrate the lipid bilayer of the placental syncytiotrophoblast.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_ob_2",
                question = "Why does an infant or neonate experience precipitously rapid arterial desaturation during induction apnea compared to an adult?",
                options = listOf(
                    "Doubled metabolic oxygen consumption (6-8 mL/kg/min) combined with a low FRC relative to body mass",
                    "A right-shifted oxyhemoglobin dissociation curve",
                    "Decreased cardiac output relative to body weight",
                    "Immature peripheral chemoreceptors unresponsive to hypoxemia"
                ),
                correctIndex = 0,
                explanation = "Neonates have more than double the resting metabolic oxygen consumption of adults (6-8 mL/kg/min vs 3-4 mL/kg/min) with a reduced functional residual capacity (FRC) and a highly compliant chest wall, causing oxygen reserves to deplete within seconds of apnea.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_cesarean_section"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Gravid Uterus Aortocaval Anatomy & Fetal Shunt Circulation",
            topicName = "Pregnancy & Neonatal Physiology",
            creatorOrInstitution = "Clinical Medical Visualization / Anesthesia Pro",
            sourceUrl = "local://res/drawable/img_obstetric_neonatal.jpg",
            licenseAndAttribution = "Anesthesia Pro Medical Visual Education (High-Yield Clinical Reference)",
            localDrawableRes = R.drawable.img_obstetric_neonatal,
            localFileName = "img_obstetric_neonatal.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Clinically reviewed high-resolution multi-panel diagram of aortocaval compression, LUD positioning relief, fetal shunt circulation, and placental barrier transfer."
        )
    )

    val surgicalProceduralTopic = AnatomyTopic(
        id = "anatomy_surgical_procedural",
        title = "Procedure-Specific Surgical Anatomy & Positioning",
        subtitle = "Pneumoperitoneum, prone position mechanics, cardiopulmonary bypass & one-lung ventilation",
        category = AnatomySystemCategory.SURGICAL_PROCEDURAL,
        difficulty = AnatomyDifficulty.ADVANCED,
        learningObjectives = listOf(
            "Analyze the cardiopulmonary alterations of laparoscopic carbon dioxide pneumoperitoneum and steep Trendelenburg positioning.",
            "Identify the physiological compromises of the prone position: venous return, chest wall compliance, and ischemic optic neuropathy (ION).",
            "Detail the physiology of cardiopulmonary bypass (CPB): hemodilution, hypothermia, non-pulsatile flow, and anticoagulation.",
            "Explain the physiology of one-lung ventilation (OLV) and hypoxic pulmonary vasoconstriction (HPV) modulation."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "pneumoperitoneum_co2_diaphragm",
                name = "Pneumoperitoneum & Diaphragmatic Cephalad Shift",
                normalizedCoordinateX = 0.25f,
                normalizedCoordinateY = 0.38f,
                description = "Insufflation of carbon dioxide (CO2) into the peritoneal cavity to an intra-abdominal pressure (IAP) of 12-15 mmHg pushes the diaphragm cephalad.",
                innervationOrVascular = "Peritoneal stretch stimulates vagus nerve, causing sudden sinus bradycardia, nodal rhythms, or asystole. Systemic absorption of CO2 leads to hypercapnia and respiratory acidosis.",
                clinicalPearl = "Cephalad diaphragmatic displacement reduces FRC by 20-30%, decreases pulmonary compliance, increases peak inspiratory pressure (PIP), and promotes atelectasis. Trendelenburg positioning exacerbates these effects and increases intracranial and intraocular pressures.",
                relatedIntervention = "Controlled positive-pressure ventilation with PEEP (5-8 cmH2O), increase minute ventilation by 20-30% to maintain normocapnia; alert surgeon immediately if severe bradycardia occurs"
            ),
            AnatomicalStructure(
                id = "prone_position_ocular_nerve_hazards",
                name = "Prone Spine Position & Ischemic Optic Neuropathy",
                normalizedCoordinateX = 0.72f,
                normalizedCoordinateY = 0.28f,
                description = "Prone positioning on a Jackson table or chest rolls. Abdominal compression must be strictly avoided by supporting iliac crests and chest wall.",
                innervationOrVascular = "Compression of the abdomen compresses the IVC, forcing blood into Batson's vertebral venous plexus, causing torrential epidural venous bleeding during spine surgery.",
                clinicalPearl = "Perioperative Ischemic Optic Neuropathy (PION): catastrophic painless blindness following prolonged prone spine surgery (> 6 hours, EBL > 1000 mL, hypotension, large crystaloid shifts). Any direct external pressure on the globe causes central retinal artery occlusion (CRAO). Head must be checked every 15-30 minutes.",
                relatedIntervention = "Head positioning in neutral alignment using ProneView or Mayfield pins; keep MAP near baseline, avoid excessive crystalloid hemodilution, document eye checks"
            ),
            AnatomicalStructure(
                id = "cpb_cannulation_hypothermia",
                name = "Cardiopulmonary Bypass (CPB) & Aortic Cannulation",
                normalizedCoordinateX = 0.75f,
                normalizedCoordinateY = 0.75f,
                description = "Venous cannula in right atrium or bicaval drains deoxygenated blood to reservoir; membrane oxygenator exchanges O2 and CO2; roller or centrifugal pump returns oxygenated blood through ascending aorta cannula.",
                innervationOrVascular = "Anticoagulation: Heparin 300-400 units/kg (target Activated Clotting Time [ACT] > 400-480 seconds). Hypothermia (28-34°C) reduces CMRO2 by ~7% per 1°C temperature drop.",
                clinicalPearl = "Initiation of CPB causes sudden profound hemodilution (pump prime ~1000-1500 mL crystalloid), dropping hemoglobin to 7-8 g/dL and causing acute hypotension. Non-pulsatile flow alters baroreceptor feedback, increasing systemic vascular resistance post-bypass.",
                relatedIntervention = "Heparinization confirmation before cannulation; Protamine titration (1 mg per 100 units heparin) post-weaning to reverse anticoagulation"
            )
        ),
        normalPhysiology = "One-lung ventilation (OLV) during thoracic surgery creates an obligatory right-to-left transpulmonary shunt through the non-ventilated lung. Hypoxic Pulmonary Vasoconstriction (HPV) redistributes blood flow away from the non-ventilated hypoxic lung to the ventilated lung, decreasing shunt fraction from ~50% down to ~20-25%.",
        clinicalSignificance = "Volatile anesthetics > 1-1.5 MAC, vasodilators (nitroprusside, nitroglycerin), calcium channel blockers, and hypocapnia inhibit HPV, exacerbating hypoxemia during one-lung ventilation.",
        anesthesiaImplications = "Managing hypoxemia during OLV: 1. Confirm tube position with fiberoptic bronchoscope; 2. Increase FiO2 to 1.0; 3. Apply CPAP (2-5 cmH2O) with oxygen to the non-ventilated operative lung; 4. Add PEEP (5 cmH2O) to the ventilated lung; 5. Intermittent two-lung ventilation if desaturation persists.",
        commonExamDistinctions = listOf(
            "Hypoxic Pulmonary Vasoconstriction (HPV) is inhibited by volatile halogenated anesthetics at concentrations > 1 MAC; TIVA (propofol) does NOT inhibit HPV.",
            "Prone positioning: Abdominal compression transmits pressure to Batson's valveless vertebral venous plexus, markedly increasing surgical field hemorrhage and reducing IVC venous return.",
            "CO2 embolism during laparoscopy: Triad of sudden severe hypotension, mill-wheel precordial murmur, and sharp decline in EtCO2 (due to acute right ventricular outflow obstruction and dead space ventilation). Treatment: Deflate abdomen, 100% O2, left lateral decubitus Trendelenburg position (Durant maneuver), aspirate gas via central line.",
            "Postoperative visual loss (POVL) in spine surgery: Ischemic optic neuropathy (ION) is most common cause, linked to prolonged operative time (> 6 hrs), massive blood loss, relative hypotension, and anemia. Central retinal artery occlusion (CRAO) is caused by direct pressure on the globe."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_surg_1",
                question = "During laparoscopic cholecystectomy, the surgeon initiates rapid CO2 insufflation. The patient suddenly develops profound sinus bradycardia (HR 28 bpm). What is the immediate physiological etiology?",
                options = listOf(
                    "Vagal reflex triggered by rapid peritoneal stretch",
                    "Sympathetic hyperstimulation from systemic CO2 absorption",
                    "Carotid sinus compression from pneumoperitoneum",
                    "Acute hyperkalemia from diaphragmatic splinting"
                ),
                correctIndex = 0,
                explanation = "Rapid peritoneal stretch during abdominal insufflation stimulates tension-sensitive peritoneal vagal afferents, eliciting a powerful vagal bradycardic reflex that can progress to sinus arrest or asystole.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_surg_2",
                question = "Which anesthetic technique preserves Hypoxic Pulmonary Vasoconstriction (HPV) during one-lung ventilation without pharmacological inhibition?",
                options = listOf(
                    "Total Intravenous Anesthesia (TIVA) with Propofol and Remifentanil",
                    "Desflurane at 1.5 MAC with nitrous oxide",
                    "Isoflurane at 1.2 MAC with intravenous nitroglycerin",
                    "Sevoflurane at 1.4 MAC with sodium nitroprusside"
                ),
                correctIndex = 0,
                explanation = "Intravenous anesthetics, including propofol and remifentanil, do not inhibit hypoxic pulmonary vasoconstriction (HPV). In contrast, volatile halogenated agents inhibit HPV in a dose-dependent manner, particularly at concentrations exceeding 1 MAC.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_lap_chole", "guide_coronary_artery_bypass"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Pneumoperitoneum Diaphragmatic Mechanics & Cardiopulmonary Bypass Circuit",
            topicName = "Procedure-Specific Surgical Anatomy",
            creatorOrInstitution = "Clinical Medical Visualization / Anesthesia Pro",
            sourceUrl = "local://res/drawable/img_surgical_positioning.jpg",
            licenseAndAttribution = "Anesthesia Pro Medical Visual Education (High-Yield Clinical Reference)",
            localDrawableRes = R.drawable.img_surgical_positioning,
            localFileName = "img_surgical_positioning.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Clinically verified high-resolution multi-panel schematic of pneumoperitoneum diaphragmatic displacement, prone spine ocular protection, and cardiopulmonary bypass circuit."
        )
    )
}
