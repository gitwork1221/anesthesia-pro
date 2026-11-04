package com.example.data.repository.surgical

import com.example.data.model.DrugReference
import com.example.data.model.ReviewQuestion
import com.example.data.model.SurgicalGuide
import com.example.data.model.SurgicalSpecialty

object OrthopedicAndNeuroGuides {

    val guides: List<SurgicalGuide> = listOf(
        SurgicalGuide(
            id = "guide_hip_fracture",
            procedureName = "Femoral Neck Fracture Repair / Hip Hemiarthroplasty",
            surgicalSpecialty = SurgicalSpecialty.ORTHOPEDICS_TRAUMA.displayName,
            overview = "Common in frail elderly patients with multiple co-morbidities (cardiac disease, dementia, COPD, chronic renal disease, dehydration). Prompt surgical fixation within 24-48 hours drastically reduces 30-day mortality.",
            preoperativeAssessment = "Cognitive baseline, cardiac assessment (aortic stenosis is prevalent and hazardous), anticoagulant status, baseline hemoglobin, and volume status.",
            anestheticTechnique = "Neuraxial anesthesia (Low-dose unilateral spinal with 6-8 mg isobaric/hyperbaric bupivacaine + opioid) OR General Anesthesia with supraglottic or ETT.\n• Regional nerve blocks: PENG (Pericapsular Nerve Group) block or Femoral Nerve / Fascia Iliaca Compartment block provides superior perioperative pain relief and reduces opioid delirium.",
            airwayConsiderations = "Often edentulous; mask seal may require leaving dentures in during preoxygenation or using two-hand jaw-thrust.",
            positioningAndNerveSafety = "Lateral decubitus or fracture table with traction. Gentle handling required to prevent severe pain and dislocation before anesthesia. Pad peroneal nerve at fibular head.",
            hemodynamicManagement = "Bone Cement Implantation Syndrome (BCIS): sudden severe hypotension, hypoxemia, cardiac arrest upon methylmethacrylate cementing of femoral canal. Anesthetist MUST inform surgeon, increase FiO2 to 100%, and optimize filling pressure prior to cement pressurization.",
            analgesiaPlan = "Fascia Iliaca block with 30-40 mL dilute local anesthetic (0.2% Ropivacaine or 0.125% Bupivacaine) markedly decreases systemic opioid requirements and delirium.",
            potentialComplications = listOf(
                "Bone Cement Implantation Syndrome (BCIS Class 1 to 3)",
                "Postoperative delirium and cognitive decline",
                "Deep vein thrombosis (DVT) and pulmonary embolism",
                "Hypothermia (active warming required)"
            ),
            postoperativeRecovery = "PACU monitoring for normothermia, pain control, early mobilization. Systematic thromboprophylaxis and avoidance of anticholinergic/sedative delirium triggers.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Lateral decubitus or fracture table. Positioning an un-anesthetized fractured hip causes excruciating pain and dangerous catecholamine spikes; perform ultrasound-guided PENG or Fascia Iliaca block BEFORE moving patient.",
            relevantAnatomyPhysiology = "Sensory innervation of the anterior hip capsule is supplied by the femoral nerve, obturator nerve, and accessory obturator nerve. Bone marrow fat embolization and acrylic monomer toxicity trigger BCIS.",
            monitoringAndAccess = "Standard ASA monitors + continuous arterial line in patients with severe aortic stenosis or severe ventricular dysfunction. 18G peripheral IV with blood warmer ready.",
            drugAndFluidStrategy = "Conservative crystalloid hydration to avoid congestive heart failure while maintaining adequate preload before cement insertion. Phenylephrine or Ephedrine ready on syringe pump prior to reaming.",
            expectedBloodLossAndTransfusion = "Average EBL: 200-500 mL (can be occult retroperitoneal bleeding). Tranexamic Acid (TXA 1 g IV) significantly reduces transfusion requirements in hip arthroplasty.",
            highYieldExamPoints = listOf(
                "Bone Cement Implantation Syndrome (BCIS) Class 3: Arterial collapse (SBP drop > 40%), cardiovascular collapse or cardiac arrest occurring during cement insertion or prosthesis impaction.",
                "PENG (Pericapsular Nerve Group) block targets the articular branches of the femoral, obturator, and accessory obturator nerves on the iliopubic eminence, providing motor-sparing analgesia.",
                "Neuraxial anesthesia in elderly hip fracture patients reduces deep vein thrombosis and pulmonary complications compared to general anesthesia without regional blocks.",
                "In patients with severe aortic stenosis undergoing hip repair, fixed cardiac output cannot tolerate acute vasodilation from a high-level spinal; a carefully titrated GA or continuous catheter technique is preferred."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_hip_guide_1",
                    question = "During femoral canal cementing for a hemiarthroplasty, the patient's EtCO2 drops from 38 to 14 mmHg and systolic BP falls from 125 to 55 mmHg. What is the immediate diagnosis?",
                    options = listOf(
                        "Bone Cement Implantation Syndrome (BCIS) Class 3 with acute right ventricular strain / microembolism",
                        "Accidental dural puncture with total spinal anesthesia",
                        "Acute malignant hyperthermia triggered by methylmethacrylate",
                        "Pseudo-shock from transducer leveling error"
                    ),
                    correctIndex = 0,
                    explanation = "Pressurization of methylmethacrylate cement drives intramedullary fat, air, and bone debris into the venous circulation, precipitating acute pulmonary microembolism, acute right ventricular failure, precipitous drop in EtCO2 (loss of pulmonary perfusion), and profound hypotension (BCIS Class 3).",
                    examHighYieldTag = "ABA / AAGBI Guidelines"
                )
            ),
            references = listOf(
                DrugReference("AAGBI Safety Guideline: Reducing the Risk from Bone Cement Implantation Syndrome", "2020", "Anaesthesia"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_nervous_system", "anatomy_cardiovascular_hemodynamics"),
            relatedDrugIds = listOf("drug_bupivacaine", "drug_fentanyl", "drug_hydromorphone", "drug_acetaminophen_iv"),
            relatedEquipmentIds = listOf("equip_capnography_analysis", "equip_anesthesia_machine_physics"),
            relatedCalculatorIds = listOf("calc_bmi_ibw", "calc_local_anesthetic"),
            relatedCaseId = "case_post_spinal_hypotension"
        ),
        SurgicalGuide(
            id = "guide_total_knee",
            procedureName = "Total Knee Arthroplasty (TKA)",
            surgicalSpecialty = SurgicalSpecialty.ORTHOPEDICS_TRAUMA.displayName,
            overview = "Elective joint replacement for end-stage osteoarthritis. Key challenges include pneumatically inflated surgical tourniquet management, rapid rehabilitation pathways, and motor-sparing regional analgesia.",
            preoperativeAssessment = "Functional status, cardiovascular reserve, OSA screening, antithrombotic timing, and baseline mobility.",
            anestheticTechnique = "Neuraxial anesthesia (Spinal with 10-12 mg isobaric/hyperbaric bupivacaine) is standard of care.\n• Regional analgesia: Adductor Canal Block (ACB) + Infiltration between the Popliteal Artery and Capsule of the Knee (iPACK block) provides outstanding motor-sparing analgesia preserving quadriceps strength for same-day ambulation.",
            airwayConsiderations = "Standard airway assessment; spontaneous ventilation under spinal with light propofol/dexmedetomidine sedation.",
            positioningAndNerveSafety = "Supine position. Padded tourniquet cuff on upper thigh. Avoid prolonged tourniquet inflation (> 120 minutes) to prevent irreversible muscular necrosis and nerve palsy.",
            hemodynamicManagement = "Tourniquet inflation elevates SVR; tourniquet deflation releases accumulated acidic, hyperkalemic, hypoxic metabolites into systemic circulation, causing transient hypotension, tachycardia, and a rise in EtCO2.",
            analgesiaPlan = "Adductor Canal Block (15-20 mL 0.2% Ropivacaine) blocks the saphenous nerve and nerve to vastus medialis without blocking the femoral motor trunk. Multimodal oral analgesia (Celecoxib, Acetaminophen, Gabapentin).",
            potentialComplications = listOf(
                "Tourniquet pain and systemic metabolic shock upon deflation",
                "Peroneal nerve neuropraxia from surgical retractors or compression",
                "Deep vein thrombosis and pulmonary embolism",
                "Postoperative quadriceps weakness leading to early patient falls"
            ),
            postoperativeRecovery = "Same-day discharge or 23-hour observation. Rapid physical therapy ambulation within 2-4 hours post-block.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine position. Pneumatic tourniquet inflated to 100 mmHg above systolic BP (typically 250 mmHg). Tourniquet deflation causes a sudden wash-in of ischemic metabolites (lactate, potassium, adenosine), transiently lowering MAP by 15-20% and raising EtCO2 by 5-10 mmHg.",
            relevantAnatomyPhysiology = "Knee joint sensory innervation: Anterior capsule via femoral and obturator nerves; posterior capsule via tibial nerve branches and sciatic plexus. Adductor canal boundaries: Vastus medialis anterolaterally, Sartorius anteriorly, Adductor longus/magnus posteromedially.",
            monitoringAndAccess = "Standard ASA monitoring. One 18G peripheral IV line. Continuous monitoring of tourniquet inflation timer.",
            drugAndFluidStrategy = "Tranexamic acid (TXA 1 g IV pre-incision and 1 g at closure) is standard protocol, reducing perioperative blood loss by > 50%. Cautious fluid management to minimize tissue edema.",
            expectedBloodLossAndTransfusion = "Minimal intraoperative blood loss due to tourniquet (< 100 mL), but postoperative drain output and hidden tissue extravasation can reach 300-600 mL. TXA minimizes transfusion rate to < 2%.",
            highYieldExamPoints = listOf(
                "Adductor Canal Block (ACB) is preferred over Femoral Nerve Block for knee replacement because ACB preserves quadriceps motor strength, facilitating early ambulation while preventing fall injury.",
                "Tourniquet pain that arises 45-60 minutes after inflation is mediated by unmyelinated C-fibers that are resistant to local anesthetic blockade.",
                "Upon tourniquet release, anticipate an increase in End-Tidal CO2 and a temporary decrease in blood pressure and core body temperature.",
                "Tranexamic Acid (TXA) is a synthetic lysine analog that competitively inhibits plasminogen activation; it does not increase venous thromboembolism risk in orthopedic patients."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_tka_guide_1",
                    question = "Why has the Adductor Canal Block largely replaced the conventional Femoral Nerve Block for Total Knee Arthroplasty analgesia?",
                    options = listOf(
                        "Adductor Canal Block provides sensory analgesia while preserving quadriceps muscle motor strength",
                        "Femoral nerve blocks cannot block the saphenous nerve distribution",
                        "Adductor canal blocks eliminate the need for surgical tourniquets",
                        "Femoral nerve blocks carry a 10-fold higher risk of LAST"
                    ),
                    correctIndex = 0,
                    explanation = "The adductor canal contains the saphenous nerve and articular branches of the nerve to vastus medialis. Blocking within the canal provides potent analgesia to the anterior knee while sparing the main motor branches to the rectus femoris and vastus intermedius, allowing safe early ambulation.",
                    examHighYieldTag = "ABA / ASRA Core"
                )
            ),
            references = listOf(
                DrugReference("ASRA Practice Advisory on Regional Anesthesia in Orthopedics", "2022", "RAPM"),
                DrugReference("Campbell's Operative Orthopaedics: Anesthesia Considerations, 14th Edition", "2021", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_nervous_system", "anatomy_cardiovascular_hemodynamics"),
            relatedDrugIds = listOf("drug_bupivacaine", "drug_acetaminophen_iv", "drug_ketorolac", "drug_fentanyl"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_local_anesthetic", "calc_bmi_ibw"),
            relatedCaseId = "case_post_spinal_hypotension"
        ),
        SurgicalGuide(
            id = "guide_craniotomy_tumor",
            procedureName = "Supratentorial Craniotomy for Mass Excision",
            surgicalSpecialty = SurgicalSpecialty.NEUROSURGERY.displayName,
            overview = "Intracranial tumor resection requiring rigid neuro-navigation pinning, strict brain relaxation, tight ICP control, and rapid emergence for neurological examination.",
            preoperativeAssessment = "Assess signs of raised ICP (headache, vomiting, papilledema, Cushing triad), baseline focal deficits, seizure history, steroid dosing (Dexamethasone).",
            anestheticTechnique = "TIVA (Propofol + Remifentanil) preferred to preserve cerebral autoregulation and facilitate intraoperative neurophysiological monitoring (SSEP/MEP).",
            airwayConsiderations = "Reinforced cuffed endotracheal tube securely taped. Avoid coughing or straining on intubation/extubation (prevents ICP spikes and brain herniation).",
            positioningAndNerveSafety = "Mayfield skull clamp pinning. Ensure no jugular vein compression by head rotation/flexion (prevents cerebral venous engorgement and brain swelling).",
            hemodynamicManagement = "Maintain Cerebral Perfusion Pressure (CPP = MAP - ICP > 60-70 mmHg). Mannitol (0.5-1 g/kg) or 3% hypertonic saline to reduce brain bulk.",
            analgesiaPlan = "Scalp nerve block (supraorbital, supratrochlear, zygomaticotemporal, auriculotemporal, lesser & greater occipital nerves) with 0.5% Ropivacaine.",
            potentialComplications = listOf(
                "Acute brain herniation / malignant brain swelling",
                "Venous air embolism (VAE) if surgical site elevated above right atrium",
                "Intraoperative seizure activity",
                "Postoperative intracranial hemorrhage / hematoma"
            ),
            postoperativeRecovery = "Smooth wake-up with IV labetalol/nicardipine to prevent hypertension and surgical hematoma; immediate neurological assessment.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine with head elevated 15-30° in Mayfield skull pins. Head elevation promotes cerebral venous drainage via the internal jugular veins, lowering ICP. Ensure neck is not hyperflexed or rotated excessively (causes jugular compression).",
            relevantAnatomyPhysiology = "Monro-Kellie doctrine: Skull is a rigid box containing brain parenchyma (80%), blood (10%), and CSF (10%). Cerebral blood flow (CBF) is coupled to PaCO2 (changes ~1-2 mL/100g/min per 1 mmHg change in PaCO2 between 20-60 mmHg). Target PaCO2 32-35 mmHg.",
            monitoringAndAccess = "Arterial line zeroed at external auditory meatus (level of circle of Willis / foramen of Monro). Two large-bore IVs. Processed EEG / BIS monitoring for TIVA depth. Core temperature.",
            drugAndFluidStrategy = "Avoid hypotonic crystalloids (e.g. 0.45% NS, Ringer's Lactate with osmolarity 273 mOsm/L); use Plasmalyte or 0.9% NS (osmolarity 308 mOsm/L) to prevent cerebral edema. Administer Mannitol 0.5-1.0 g/kg or 3% Hypertonic Saline (250 mL).",
            expectedBloodLossAndTransfusion = "Average EBL: 200-800 mL (can be rapid if dural venous sinuses or meningioma vessels are breached). 2 units PRBC on crossmatch.",
            highYieldExamPoints = listOf(
                "Mayfield skull pin insertion elicits a profound sympathetic hypertensive response; blunt with scalp block, IV lidocaine, or remifentanil bolus.",
                "Volatile halogenated anesthetics at > 1 MAC cause cerebral vasodilation and increase ICP; TIVA with propofol and remifentanil decreases both CBF and CMRO2, preserving autoregulation.",
                "Arterial pressure transducer must be leveled at the external auditory meatus to calculate true Cerebral Perfusion Pressure (CPP = MAP - ICP).",
                "Venous Air Embolism (VAE) can occur whenever the surgical field is elevated above the level of the right atrium; precordial Doppler and capnography (sudden drop in EtCO2) are highly sensitive."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_neuro_guide_1",
                    question = "Why must the arterial line pressure transducer be leveled at the external auditory meatus rather than the phlebostatic axis during sitting or head-elevated craniotomy?",
                    options = listOf(
                        "To accurately calculate Cerebral Perfusion Pressure at the level of the Circle of Willis",
                        "To prevent baroreceptor artifacts on the ECG",
                        "Because cerebral venous pressure is measured at the ear canal",
                        "To avoid false-positive alarms for Venous Air Embolism"
                    ),
                    correctIndex = 0,
                    explanation = "In the head-elevated position, the hydrostatic hydrostatic column between the heart and the head causes cerebral arterial pressure to be lower than cardiac pressure. Leveling at the external auditory meatus ensures the measured MAP accurately reflects pressure at the Circle of Willis.",
                    examHighYieldTag = "ABA / SNACC Core"
                )
            ),
            references = listOf(
                DrugReference("Cottrell and Patel's Neuroanesthesia, 6th Edition", "2019", "Elsevier"),
                DrugReference("SNACC Consensus Guidelines for Intraoperative ICP Management", "2021", "JNA")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_nervous_system", "anatomy_respiratory_airway"),
            relatedDrugIds = listOf("drug_propofol", "drug_remifentanil", "drug_dexamethasone", "drug_labetalol"),
            relatedEquipmentIds = listOf("equip_capnography_analysis", "equip_anesthesia_machine_physics"),
            relatedCalculatorIds = listOf("calc_mean_arterial_pressure", "calc_bmi_ibw"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        )
    )
}
