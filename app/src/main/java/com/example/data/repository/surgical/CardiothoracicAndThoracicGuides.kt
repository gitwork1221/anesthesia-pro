package com.example.data.repository.surgical

import com.example.data.model.DrugReference
import com.example.data.model.ReviewQuestion
import com.example.data.model.SurgicalGuide
import com.example.data.model.SurgicalSpecialty

object CardiothoracicAndThoracicGuides {

    val guides: List<SurgicalGuide> = listOf(
        SurgicalGuide(
            id = "guide_coronary_artery_bypass",
            procedureName = "Coronary Artery Bypass Grafting (CABG)",
            surgicalSpecialty = SurgicalSpecialty.CARDIOTHORACIC_VASCULAR.displayName,
            overview = "Surgical revascularization for multivessel coronary artery disease with cardiopulmonary bypass (CPB) and cardioplegic arrest.",
            preoperativeAssessment = "Review cardiac catheterization (left main stenosis, EF, regional wall motion), carotid duplex, renal function, antiplatelet/anticoagulant stop times.",
            anestheticTechnique = "High-narcotic or balanced general anesthesia. High-dose fentanyl/sufentanil + midazolam or volatile agent (myocardial preconditioning). Full neuromuscular blockade.",
            airwayConsiderations = "Smooth induction without tachycardia or hypertension to prevent myocardial oxygen supply/demand mismatch and ischemia.",
            positioningAndNerveSafety = "Supine, arms tucked. Protect ulnar and brachial nerves. Sternal retractor can stretch brachial plexus (C8-T1).",
            hemodynamicManagement = "Pre-CPB: Maintain coronary perfusion pressure (CPP = DBP - LVEDP). Post-CPB: Inotropic support (epinephrine/milrinone), vasopressor (norepinephrine/vasopressin), protamine reversal of heparin.",
            analgesiaPlan = "Multimodal: IV acetaminophen, dexmedetomidine, judicious opioids, erector spinae plane (ESP) or parasternal blocks.",
            potentialComplications = listOf(
                "Perioperative myocardial ischemia / infarction",
                "Failure to wean from cardiopulmonary bypass / vasoplegic syndrome",
                "Protamine anaphylactoid / catastrophic pulmonary hypertension reaction",
                "Coagulopathy and surgical mediastinal bleeding"
            ),
            postoperativeRecovery = "Transfer intubated to Cardiac Intensive Care Unit (CICU) with invasive hemodynamic lines, active warming, and paced rhythm support.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine position with arms tucked. Median sternotomy and sternal retraction can stretch the lower trunk of the brachial plexus (C8-T1) or compress the internal mammary artery.",
            relevantAnatomyPhysiology = "Coronary Perfusion Pressure (CPP = AoDP - LVEDP). Left ventricular subendocardial perfusion occurs almost exclusively during diastole. Cardiopulmonary bypass triggers profound systemic inflammatory response (SIRS), hemodilution (pump prime 1000-1500 mL crystalloid), and platelet dysfunction.",
            monitoringAndAccess = "Pre-induction arterial line (radial or femoral). Central venous catheter (right internal jugular) with pulmonary artery catheter (PAC) or Transesophageal Echocardiography (TEE). Near-Infrared Spectroscopy (NIRS) for cerebral oximetry. ACT machine in room.",
            drugAndFluidStrategy = "Heparinization: 300-400 units/kg (target ACT > 400-480 seconds before cannulation). Protamine reversal: 1 mg per 100 units heparin administered slowly over 10-15 minutes (rapid bolus causes severe pulmonary vasoconstriction and systemic hypotension). Inotropes: Epinephrine, Milrinone; Vasopressors: Norepinephrine, Vasopressin.",
            expectedBloodLossAndTransfusion = "Average EBL: 500-1200 mL. Transfusion guided by viscoelastic testing (TEG/ROTEM). Tranexamic Acid (TXA 30 mg/kg bolus + 16 mg/kg/hr infusion) standard of care.",
            highYieldExamPoints = listOf(
                "Activated Clotting Time (ACT) must exceed 400-480 seconds before opening aortic and venous cannulae for Cardiopulmonary Bypass.",
                "If ACT is refractory to high-dose heparin (> 500 units/kg), diagnose Antithrombin III (AT3) deficiency and administer Antithrombin concentrate or 2 units Fresh Frozen Plasma (FFP).",
                "Protamine adverse reactions: Type I (hypotension from rapid injection), Type II (anaphylactoid IgE/non-IgE), Type III (catastrophic pulmonary vasoconstriction with acute RV failure).",
                "During CPB cooling (hypothermia to 28-32°C), CMRO2 decreases by ~7% per 1°C decrease in brain temperature."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_cabg_guide_1",
                    question = "A patient fails to achieve an ACT > 400 seconds despite two successive boluses of Heparin totaling 600 units/kg prior to CPB cannulation. What is the definitive etiology and management?",
                    options = listOf(
                        "Antithrombin III (AT3) deficiency; administer Antithrombin III concentrate or Fresh Frozen Plasma",
                        "Heparin-Induced Thrombocytopenia; administer Protamine immediately",
                        "Protamine resistance; administer additional volatile anesthetic",
                        "Factor VII deficiency; administer recombinant Factor VIIa"
                    ),
                    correctIndex = 0,
                    explanation = "Heparin exerts its anticoagulant effect by accelerating Antithrombin III activity 1000-fold. In patients with hereditary or acquired AT3 deficiency (e.g. prolonged heparin infusion in CCU), heparin has negligible effect. Fresh Frozen Plasma contains AT3 and restores heparin sensitivity.",
                    examHighYieldTag = "ABA / SCA Core"
                )
            ),
            references = listOf(
                DrugReference("Kaplan's Cardiac Anesthesia, 7th Edition", "2019", "Elsevier"),
                DrugReference("SCA/STS Clinical Practice Guidelines on Patient Blood Management", "2021", "Annals of Thoracic Surgery")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_cardiovascular_hemodynamics", "anatomy_surgical_procedural"),
            relatedDrugIds = listOf("drug_fentanyl", "drug_epinephrine", "drug_norepinephrine", "drug_vasopressin"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_mean_arterial_pressure", "calc_bmi_ibw"),
            relatedCaseId = "case_post_spinal_hypotension"
        ),
        SurgicalGuide(
            id = "guide_carotid_endarterectomy",
            procedureName = "Carotid Endarterectomy (CEA)",
            surgicalSpecialty = SurgicalSpecialty.CARDIOTHORACIC_VASCULAR.displayName,
            overview = "Surgical plaque removal at the carotid bifurcation to prevent stroke. Requires critical assessment of cerebral collateral circulation via the Circle of Willis and tight perioperative hemodynamic control.",
            preoperativeAssessment = "Degree of internal carotid artery stenosis, contralateral carotid patency, recent TIA/stroke symptoms, baseline blood pressure range, and cardiac risk profile.",
            anestheticTechnique = "Two valid strategies:\n• Awake Regional Anesthesia: Superficial ± Intermediate Cervical Plexus Block (C2-C4) with 0.25% Bupivacaine or 0.5% Ropivacaine. Gold standard for real-time neurological assessment.\n• General Anesthesia: Smooth induction and emergence; EEG or Stump pressure monitoring (> 40-50 mmHg indicates adequate collateral flow, otherwise intraluminal shunt placed).",
            airwayConsiderations = "If regional block used, airway is shared and draped; patient must remain calm and compliant. Conversion to GA mid-case requires emergency airway management with un-draped, sterile field.",
            positioningAndNerveSafety = "Supine, head turned away from surgical side and slightly extended. Avoid extreme rotation to preserve contralateral vertebral artery flow.",
            hemodynamicManagement = "Pre-clamping: Maintain BP near baseline. Cross-clamping: Elevate MAP by 10-20% (with phenylephrine/norepinephrine) to promote collateral flow via Circle of Willis. Post-unclamping: Maintain SBP < 140 mmHg to prevent Cerebral Hyperperfusion Syndrome.",
            analgesiaPlan = "Superficial cervical plexus block provides outstanding perioperative analgesia with minimal systemic opioids. Local infiltration by surgeon along carotid sheath.",
            potentialComplications = listOf(
                "Acute intraoperative stroke / thromboembolism",
                "Cerebral Hyperperfusion Syndrome (severe ipsilateral headache, seizure, intracranial hemorrhage)",
                "Cranial nerve injuries (Hypoglossal XII: tongue deviation; Recurrent Laryngeal X: hoarseness; Marginal mandibular VII)",
                "Expanding neck hematoma causing acute airway compression / asphyxiation"
            ),
            postoperativeRecovery = "Meticulous neurological checks every 15 minutes in PACU. Keep SBP strictly within target window (typically 100-140 mmHg) using IV labetalol or clevidipine/nicardipine.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine with gentle neck extension and contralateral head rotation. Carotid sinus manipulation stimulates baroreceptors, producing abrupt bradycardia and hypotension; treat with surgical pause and 1% lidocaine infiltration into carotid bulb.",
            relevantAnatomyPhysiology = "Cervical plexus arises from anterior rami of C1-C4. Carotid bifurcation typically occurs at C3-C4 level. Collateral perfusion across the Circle of Willis depends on the anterior and posterior communicating arteries.",
            monitoringAndAccess = "Radial arterial line ipsilateral or contralateral (essential for beat-to-beat pressure control). 18G peripheral line. Cerebral oximetry (NIRS) or awake neurological testing (squeeze toy).",
            drugAndFluidStrategy = "Heparin 50-100 units/kg before cross-clamping (target ACT > 200-250 seconds). Phenylephrine on syringe pump for cross-clamp hypertension; Labetalol or Nicardipine ready for post-unclamping hypertension.",
            expectedBloodLossAndTransfusion = "Low (100-300 mL). Transfusion is very rare. Reversal of heparin with small-dose protamine (25-50 mg) is standard practice.",
            highYieldExamPoints = listOf(
                "Awake patient neurological examination (verbal response, contralateral hand grip) is the most sensitive and specific monitor of cerebral ischemia during carotid cross-clamping.",
                "Carotid sinus manipulation produces severe vagal bradycardia and hypotension; immediate management is asking the surgeon to infiltrate 1-2 mL of 1% lidocaine into the carotid bulb.",
                "Expanding neck hematoma post-CEA is a surgical emergency causing fatal tracheal compression; immediate management is bedside opening of surgical clips/sutures to evacuate clot BEFORE attempting intubation.",
                "Cerebral Hyperperfusion Syndrome presents 2-7 days postoperatively with severe unilateral headache, seizures, and intracerebral hemorrhage due to dysregulated autoregulation in chronically ischemic brain tissue."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_cea_guide_1",
                    question = "A patient develops acute respiratory stridor and neck swelling 2 hours after Carotid Endarterectomy. What is the immediate life-saving priority?",
                    options = listOf(
                        "Immediately open the surgical wound at bedside to decompress the hematoma, then secure the airway",
                        "Administer IV Dexamethasone 10 mg and nebulized racemic epinephrine",
                        "Perform emergency fiberoptic bronchoscopy in the radiology CT suite",
                        "Transfer patient to ICU for continuous positive airway pressure (CPAP)"
                    ),
                    correctIndex = 0,
                    explanation = "An expanding post-carotid hematoma compresses the internal jugular veins and larynx, producing rapid supraglottic edema and complete airway occlusion. Immediate bedside opening of the wound evacuation of the compressive clot relieves tracheal displacement and enables ventilation/intubation.",
                    examHighYieldTag = "ABA / Vascular Core"
                )
            ),
            references = listOf(
                DrugReference("Society for Vascular Surgery Practice Guidelines for Carotid Endarterectomy", "2022", "Journal of Vascular Surgery"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_nervous_system", "anatomy_cardiovascular_hemodynamics"),
            relatedDrugIds = listOf("drug_phenylephrine", "drug_norepinephrine", "drug_labetalol", "drug_bupivacaine"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_mean_arterial_pressure", "calc_local_anesthetic"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        ),
        SurgicalGuide(
            id = "guide_thoracotomy_olv",
            procedureName = "Thoracotomy & VATS with One-Lung Ventilation (OLV)",
            surgicalSpecialty = SurgicalSpecialty.THORACIC_SURGERY.displayName,
            overview = "Thoracic surgical procedures (lobectomy, wedge resection, esophagectomy) requiring lung isolation with a Double-Lumen Endotracheal Tube (DLT) or bronchial blocker and lateral decubitus positioning.",
            preoperativeAssessment = "Pulmonary function testing (FEV1, DLCO; predicted postoperative FEV1 > 40%), baseline ABG, cardiovascular reserve, exercise tolerance, and previous thoracic surgery.",
            anestheticTechnique = "General anesthesia with Left-sided Double-Lumen Tube (Left DLT size 35-37 Fr for females, 39-41 Fr for males) confirmed by fiberoptic bronchoscopy.\n• TIVA or low-MAC volatile (< 1 MAC to avoid inhibiting Hypoxic Pulmonary Vasoconstriction).\n• Thoracic Epidural (T4-T6) or ultrasound-guided Paravertebral / Erector Spinae Plane (ESP) block.",
            airwayConsiderations = "Left-sided DLT is standard regardless of operative side (except left pneumonectomy or left main bronchus distortion). Fiberoptic bronchoscopic verification is mandatory both supine and after lateral decubitus positioning.",
            positioningAndNerveSafety = "Lateral decubitus position. Axillary roll placed inferior to dependent axilla to protect brachial plexus. Dependent ear, eye, and common peroneal nerve padded. Dependent lung has reduced ventilation and increased perfusion (V/Q mismatch).",
            hemodynamicManagement = "Conservative fluid strategy (< 1.5-2 L crystalloid over entire case) to prevent postoperative acute lung injury (ALI) / post-pneumonectomy pulmonary edema.",
            analgesiaPlan = "Thoracic Epidural Anesthesia (TEA) with dilute local anesthetic and lipophilic opioid OR multi-level Thoracic Paravertebral Block (TPVB). IV Acetaminophen, NSAID if renal function normal.",
            potentialComplications = listOf(
                "Refractory hypoxemia during one-lung ventilation (transpulmonary right-to-left shunt)",
                "Double-lumen tube malposition or herniation into wrong bronchus",
                "Post-pneumonectomy acute pulmonary edema",
                "Tension pneumothorax of the dependent ventilated lung"
            ),
            postoperativeRecovery = "Early extubation in OR if hemodynamically stable and normothermic. Aggressive pulmonary toilet, incentive spirometry, and regional analgesia to prevent atelectasis.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Lateral decubitus position. In the anesthetized, paralyzed, open-chest lateral decubitus patient, the dependent lung receives 60% of cardiac output but reduced ventilation due to mediastinal compression, creating an inherent shunt even before OLV is initiated.",
            relevantAnatomyPhysiology = "One-lung ventilation creates an obligatory transpulmonary right-to-left shunt through the non-ventilated lung. Hypoxic Pulmonary Vasoconstriction (HPV) redistributes blood flow away from the hypoxic non-ventilated lung, reducing shunt fraction from ~50% to ~20-25%.",
            monitoringAndAccess = "Arterial line (radial; essential for serial blood gas monitoring). 16G or 18G peripheral IV. Central venous catheter if high bleeding risk or vasopressor requirement. Peak inspiratory pressure and driving pressure monitored.",
            drugAndFluidStrategy = "Restrictive fluid regimen (target net positive balance < 1000 mL at end of case). Volatile anesthetics > 1 MAC inhibit HPV; TIVA (Propofol) preserves HPV without inhibition.",
            expectedBloodLossAndTransfusion = "Average EBL: 200-500 mL (can exceed 1500 mL in dense pleural adhesions or pulmonary artery injury). Type and cross 2 units PRBC.",
            highYieldExamPoints = listOf(
                "Stepwise algorithm for hypoxemia during One-Lung Ventilation: (1) Confirm tube position with fiberoptic bronchoscope; (2) Increase FiO2 to 1.0; (3) Apply CPAP (2-5 cmH2O) to the non-ventilated operative lung; (4) Add PEEP (5 cmH2O) to the dependent ventilated lung; (5) Intermittent two-lung ventilation.",
                "Volatile halogenated anesthetics inhibit Hypoxic Pulmonary Vasoconstriction (HPV) in a dose-dependent manner above 1 MAC; intravenous anesthetics (Propofol) do NOT inhibit HPV.",
                "Left-sided double-lumen tube is preferred because the right main bronchus has an early takeoff of the right upper lobe bronchus (average 1.5-2 cm from carina), making right DLT alignment difficult.",
                "Post-pneumonectomy pulmonary edema carries > 50% mortality; strict perioperative fluid restriction (< 20 mL/kg in 24 hours) is the single most important preventative measure."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_olv_guide_1",
                    question = "During one-lung ventilation for a left lobectomy, the patient's SpO2 drops from 99% to 84%. Tube position is verified by bronchoscope. What is the most effective initial physiological maneuver?",
                    options = listOf(
                        "Apply Continuous Positive Airway Pressure (CPAP 2-5 cmH2O) with 100% O2 to the non-ventilated operative lung",
                        "Increase tidal volume on the ventilated lung to 12 mL/kg",
                        "Administer intravenous nitroprusside to dilate pulmonary vasculature",
                        "Discontinue mechanical ventilation and perform manual bag squeeze"
                    ),
                    correctIndex = 0,
                    explanation = "Applying CPAP (2-5 cmH2O) with 100% O2 to the non-dependent, non-ventilated operative lung oxygenates blood traversing the shunted pulmonary capillaries without expanding the lung enough to interfere with surgery, dramatically reducing shunt fraction.",
                    examHighYieldTag = "ABA / EACTA Core"
                )
            ),
            references = listOf(
                DrugReference("Cohen's Principles and Practice of Thoracic Anesthesia, 2nd Edition", "2021", "Springer"),
                DrugReference("EACTAIC Guidelines on Perioperative Management in Thoracic Surgery", "2023", "JCVA")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_respiratory_airway", "anatomy_surgical_procedural"),
            relatedDrugIds = listOf("drug_propofol", "drug_fentanyl", "drug_rocuronium", "drug_bupivacaine"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_alveolar_gas", "calc_mean_arterial_pressure"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        )
    )
}
