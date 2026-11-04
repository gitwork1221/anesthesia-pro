package com.example.data.repository.surgical

import com.example.data.model.DrugReference
import com.example.data.model.ReviewQuestion
import com.example.data.model.SurgicalGuide
import com.example.data.model.SurgicalSpecialty

object GeneralAndObstetricGuides {

    val guides: List<SurgicalGuide> = listOf(
        SurgicalGuide(
            id = "guide_cesarean_section",
            procedureName = "Cesarean Delivery (Elective & Emergency)",
            surgicalSpecialty = SurgicalSpecialty.OBSTETRICS_GYNECOLOGY.displayName,
            overview = "One of the most frequently performed surgical operations worldwide. Anesthetic management requires dual vigilance for maternal safety (altered maternal physiology, aspiration risk, difficult airway) and neonatal well-being.",
            preoperativeAssessment = "Assess airway (edema increased in pregnancy; short neck, full breasts), last oral intake, signs of preeclampsia/eclampsia, platelet count, maternal hemodynamics, and fetal heart rate.",
            anestheticTechnique = "Neuraxial anesthesia (Spinal anesthesia) is the gold standard for non-emergent cases.\n• Subarachnoid Block: 1.6 - 2.2 mL (8 - 11 mg) 0.5% Hyperbaric Bupivacaine with 10 - 20 mcg Fentanyl and 100 mcg preservative-free Morphine.\n• Target sensory level: T4 dermatome (nipple line) to block peritoneal traction.\n• General Anesthesia (Crash C-Section / Severe fetal distress / Platelets < 50k): Rapid Sequence Induction with gentle cricoid guidance, Propofol/Ketamine + Succinylcholine 1.5 mg/kg (TBW) or Rocuronium 1.2 mg/kg.",
            airwayConsiderations = "Obstetric patients are 8 to 10 times more likely to encounter failed intubation due to capillary engorgement of the upper airway, friable tissues, reduced functional residual capacity (FRC), and rapid arterial desaturation.",
            positioningAndNerveSafety = "Left Uterine Displacement (LUD) of 15° with wedge under right hip is MANDATORY to alleviate aortocaval compression by the gravid uterus (supine hypotensive syndrome).",
            hemodynamicManagement = "Pre-emptive / prophylactic co-loading with balanced crystalloids (500-1000 mL) and phenylephrine infusion (25-50 mcg/min) or intermittent boluses (50-100 mcg) to keep systolic BP >= 90% of baseline.",
            analgesiaPlan = "Intrathecal preservative-free morphine (Duramorph 100 mcg) provides up to 18-24 hours of profound post-op analgesia; supplement with IV Paracetamol (1 g) and Ketorolac (15-30 mg) after uterine closure.",
            potentialComplications = listOf(
                "High / total spinal anesthesia with respiratory arrest and loss of consciousness",
                "Severe post-spinal hypotension with fetal bradycardia",
                "Post-Dural Puncture Headache (PDPH)",
                "Postpartum hemorrhage due to uterine atony (prepare oxytocin, ergometrine, misoprostol, carboprost, TXA)"
            ),
            postoperativeRecovery = "Monitor respiratory rate hourly for 12-24 hours if neuraxial morphine was administered. Check uterine tone, vaginal lochia, and ensure early maternal mobilization and breast-feeding.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine position with 15° Left Uterine Displacement (LUD). Unrelieved supine position causes the gravid uterus to compress the IVC and abdominal aorta, decreasing venous return to the heart by up to 70%, lowering cardiac output and precipitating fetal hypoperfusion.",
            relevantAnatomyPhysiology = "Maternal plasma volume increases by 45-50%, minute ventilation increases by 50% (chronic respiratory alkalosis, normal PaCO2 ~30-32 mmHg), and FRC decreases by 20-30%. The T4 sensory dermatome corresponds to the nipple line and must be blocked to suppress peritoneal exteriorization discomfort.",
            monitoringAndAccess = "Standard ASA monitors + continuous fetal heart rate monitoring up to surgical prep. Two large-bore IV lines (16G or 18G) required for emergency deliveries or high hemorrhage risk. Automatic non-invasive BP set to cycle every 1 minute during initial post-spinal period.",
            drugAndFluidStrategy = "Balanced crystalloid co-loading (1000 mL at the time of spinal block). Phenylephrine is the first-line vasopressor because it preserves umbilical cord blood gas pH superiorly compared to ephedrine. First-line uterotonic: Oxytocin infusion (Carbetocin where available). Second-line uterotonics: Methylergonovine (avoid in hypertension) or Carboprost / Hemabate (avoid in asthma).",
            expectedBloodLossAndTransfusion = "Average EBL: 500-1000 mL. Transfusion trigger: Hgb < 7 g/dL in stable patient, or dynamic blood loss with instability. Active type & screen mandatory. If massive obstetric hemorrhage occurs, activate Obstetric Massive Transfusion Protocol (1:1:1 PRBC, FFP, Platelets) + Tranexamic Acid (TXA 1 g IV over 10 min).",
            highYieldExamPoints = listOf(
                "Maternal PaCO2 normally decreases to 30-32 mmHg at term due to progesterone stimulation of central respiratory drive; a PaCO2 of 40 mmHg indicates maternal hypoventilation.",
                "Phenylephrine is preferred over ephedrine for post-spinal hypotension because ephedrine crosses the placenta readily, increasing fetal metabolic rate and causing fetal acidosis.",
                "Intrathecal morphine (100 mcg) acts on substantia gelatinosa mu-opioid receptors; watch for biphasic or delayed respiratory depression at 6-18 hours due to rostral CSF spread.",
                "Ergometrine / Methergine causes intense vasoconstriction: STRICTLY CONTRAINDICATED in preeclampsia and chronic hypertension. Carboprost (PGF2a) causes bronchoconstriction: CONTRAINDICATED in reactive airway disease."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_ob_guide_1",
                    question = "Why is Phenylephrine the first-choice vasopressor for post-spinal hypotension during Cesarean delivery rather than Ephedrine?",
                    options = listOf(
                        "Phenylephrine maintains superior umbilical arterial pH without stimulating fetal adrenergic metabolism",
                        "Ephedrine causes uterine atony and increases postpartum hemorrhage",
                        "Phenylephrine crosses the placenta and produces fetal tachycardia",
                        "Ephedrine is contraindicated with all volatile halogenated anesthetics"
                    ),
                    correctIndex = 0,
                    explanation = "Ephedrine crosses the placenta freely, stimulating fetal beta-adrenergic receptors and accelerating fetal metabolic rate, producing higher fetal lactate and lower umbilical arterial pH. Phenylephrine maintains maternal cardiac output while preserving superior fetal acid-base balance.",
                    examHighYieldTag = "ABA / SOAP Core"
                )
            ),
            references = listOf(
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology / SOAP"),
                DrugReference("Chestnut's Obstetric Anesthesia: Principles and Practice, 6th Edition", "2020", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_pregnancy_neonatal", "anatomy_nervous_system"),
            relatedDrugIds = listOf("drug_bupivacaine", "drug_oxytocin", "drug_phenylephrine", "drug_fentanyl"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_local_anesthetic", "calc_mean_arterial_pressure"),
            relatedCaseId = "case_post_spinal_hypotension"
        ),
        SurgicalGuide(
            id = "guide_emergency_laparotomy",
            procedureName = "Emergency Laparotomy / Perforated Viscus & Acute Abdomen",
            surgicalSpecialty = SurgicalSpecialty.EMERGENCY_SURGERY.displayName,
            overview = "High-risk acute abdomen presentation with peritonitis, sepsis, severe intra-abdominal contamination, hypovolemia, and delayed gastric emptying.",
            preoperativeAssessment = "Assess intravascular volume deficit, shock index (HR/SBP > 1 indicates occult shock), lactic acidosis, electrolyte derangements, and septic encephalopathy. Optimize resuscitation before induction whenever possible.",
            anestheticTechnique = "General Anesthesia with Rapid Sequence Induction (RSI) using cuffed endotracheal tube.\n• Induction: Ketamine (1-2 mg/kg) or Etomidate (0.2-0.3 mg/kg) to preserve hemodynamics; Succinylcholine (1.5 mg/kg) or Rocuronium (1.2 mg/kg).\n• Avoid nitrous oxide (causes bowel distension). Maintenance with low-MAC volatile + opioid or TIVA.",
            airwayConsiderations = "Full stomach protocol mandatory due to ileus, peritoneal irritation, and pain slowing peristalsis. Place nasogastric tube (NGT) and aspirate prior to induction, then remove/unclamp before RSI.",
            positioningAndNerveSafety = "Supine with arms tucked or abducted < 90° on padded armboards to prevent brachial plexus stretch injury. Pad heels to avoid pressure necrosis.",
            hemodynamicManagement = "Target MAP >= 65 mmHg. Early balanced crystalloid resuscitation (Plasmalyte or Hartmann's). Titrate norepinephrine infusion early if vasoplegic shock persists despite fluid responsiveness.",
            analgesiaPlan = "Multimodal analgesia: IV Paracetamol, low-dose ketamine infusion, wound catheters or bilateral Transversus Abdominis Plane (TAP) blocks. Avoid NSAIDs in acute renal injury or active bleeding.",
            potentialComplications = listOf(
                "Aspiration pneumonitis (Mendelson's syndrome)",
                "Profound hemodynamic collapse upon induction from vasodilation in an under-resuscitated patient",
                "Intra-abdominal hypertension and abdominal compartment syndrome upon closure",
                "Postoperative respiratory failure / prolonged mechanical ventilation"
            ),
            postoperativeRecovery = "Transfer to surgical high-dependency unit (HDU) or ICU if high inotropic requirement or severe acidosis. Delayed extubation if severe hypothermia, ongoing acidosis, or massive fluid shifts.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine position. Upon decompression of a tense pneumoperitoneum or distended abdomen, sudden release of intra-abdominal pressure can cause acute venous pooling in splanchnic beds and abrupt hypotension.",
            relevantAnatomyPhysiology = "Sepsis causes systemic endothelial glycocalyx disruption and capillary leak. Splanchnic hypoperfusion triggers bacterial translocation. Diaphragmatic splinting reduces FRC and worsens V/Q mismatch.",
            monitoringAndAccess = "Pre-induction arterial line strongly recommended if septic or hemodynamically unstable. Two large-bore peripheral lines (14G or 16G). Core temperature probe (esophageal). Central venous catheter placed post-induction if vasopressor dependent.",
            drugAndFluidStrategy = "Balanced crystalloids preferred over 0.9% normal saline to prevent hyperchloremic metabolic acidosis. Norepinephrine is the first-line vasopressor for septic vasoplegia. Stress-dose steroids (Hydrocortisone 100 mg IV) if septic shock refractory to vasopressors.",
            expectedBloodLossAndTransfusion = "Variable (200-1500 mL depending on etiology: bowel ischemia vs ruptured abdominal aneurysm vs trauma). Have 2-4 units PRBC typed and crossed. Consider cell salvage unless frank enteric contamination is extensive.",
            highYieldExamPoints = listOf(
                "Induction of an unresuscitated septic patient with propofol causes catastrophic cardiovascular collapse; Ketamine or Etomidate with push-dose vasopressor on standby is the safest induction technique.",
                "Nitrous oxide is strictly contraindicated in bowel obstruction because it diffuses into closed gas-filled cavities 34 times faster than nitrogen leaves, doubling bowel gas volume rapidly.",
                "In abdominal compartment syndrome (IAP > 20 mmHg), surgical decompression can cause transient post-decompression shock, hyperkalemia, and reperfusion arrhythmias.",
                "Succinylcholine is contraindicated if intra-abdominal sepsis or immobilization exceeds 48-72 hours due to denervation/immature acetylcholine receptor up-regulation and fatal hyperkalemia."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_lap_guide_1",
                    question = "Why is Nitrous Oxide contraindicated in patients undergoing emergency laparotomy for small bowel obstruction?",
                    options = listOf(
                        "N2O diffuses into air-filled bowel lumens much faster than nitrogen diffuses out, markedly distending the bowel",
                        "N2O irreversibly inhibits pseudocholinesterase enzyme",
                        "N2O induces severe malignant hyperthermia in acute abdomen",
                        "N2O inhibits hepatic Cytochrome P450 enzymes"
                    ),
                    correctIndex = 0,
                    explanation = "Because nitrous oxide is approximately 34 times more soluble in blood than nitrogen, it diffuses into compliant closed gas spaces far faster than nitrogen exits, causing rapid distension of obstructed bowel loops, compromising mucosal perfusion and obstructing surgical closure.",
                    examHighYieldTag = "ABA Basic Exam"
                )
            ),
            references = listOf(
                DrugReference("Surviving Sepsis Campaign International Guidelines", "2021", "Critical Care Medicine"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_general_cellular", "anatomy_respiratory_airway"),
            relatedDrugIds = listOf("drug_propofol", "drug_rocuronium", "drug_norepinephrine", "drug_succinylcholine"),
            relatedEquipmentIds = listOf("equip_capnography_analysis", "equip_anesthesia_machine_physics"),
            relatedCalculatorIds = listOf("calc_fluid_maintenance", "calc_mean_arterial_pressure"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        ),
        SurgicalGuide(
            id = "guide_lap_chole",
            procedureName = "Laparoscopic Cholecystectomy (Pneumoperitoneum)",
            surgicalSpecialty = SurgicalSpecialty.GENERAL_SURGERY.displayName,
            overview = "Elective or urgent gallbladder removal requiring CO2 insufflation to 12-15 mmHg and reverse Trendelenburg positioning. Causes distinct cardiopulmonary pathophysiology.",
            preoperativeAssessment = "Assess for acute cholecystitis, sepsis, cholangitis, hepatic function panel, and baseline cardiopulmonary reserve.",
            anestheticTechnique = "General endotracheal anesthesia with cuffed ETT and controlled mechanical ventilation. Place orogastric tube (OGT) to decompress the stomach prior to trocar insertion.",
            airwayConsiderations = "Secured cuffed endotracheal tube is mandatory due to elevated intra-abdominal pressure displacing diaphragm cephalad and promoting regurgitation.",
            positioningAndNerveSafety = "Reverse Trendelenburg with left tilt. Secure footboard to prevent sliding. Ensure armboards < 90° to protect brachial plexus.",
            hemodynamicManagement = "CO2 insufflation compresses IVC reducing venous return, but increases SVR via neurohumoral activation (vasopressin/renin). Beware peritoneal stretch vagal bradycardia on initial insufflation.",
            analgesiaPlan = "Multimodal: IV Paracetamol, NSAID/Ketorolac, port-site local anesthetic infiltration (0.25% Bupivacaine), and bilateral subcostal TAP blocks.",
            potentialComplications = listOf(
                "Severe vagal bradycardia / asystole during peritoneal insufflation",
                "Subcutaneous emphysema and pneumothorax / capnothorax",
                "Carbon dioxide gas embolism (sudden mill-wheel murmur, drop in EtCO2, desaturation)",
                "Trocar visceral or major vascular injury (aorta/iliac)"
            ),
            postoperativeRecovery = "Post-op shoulder tip pain from diaphragmatic peritoneal irritation by carbonic acid; antiemetic prophylaxis (PONV incidence 40-70% if unmanaged).",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Reverse Trendelenburg with leftward lateral roll. Cephalad diaphragmatic shift from CO2 pneumoperitoneum decreases functional residual capacity (FRC) by 20-30% and reduces thoracic compliance.",
            relevantAnatomyPhysiology = "Carbon dioxide is rapidly absorbed across the peritoneal membrane into the bloodstream, producing respiratory acidosis unless minute ventilation is augmented by 20-30%. Calot's triangle anatomy: cystic duct, cystic artery, and common hepatic duct.",
            monitoringAndAccess = "Standard ASA monitors with continuous capnography. Peak airway pressures monitored carefully; add PEEP 5-8 cmH2O to prevent base atelectasis. Peripheral 18G or 20G IV line.",
            drugAndFluidStrategy = "Conservative fluid administration to avoid bowel edema. Dual antiemetic prophylaxis (Dexamethasone 4-8 mg at induction + Ondansetron 4 mg prior to emergence). Glycopyrrolate or Atropine immediately available for peritoneal stretch bradycardia.",
            expectedBloodLossAndTransfusion = "Minimal (< 50 mL). Transfusion is extremely rare unless unexpected injury to cystic artery or aberrant hepatic artery occurs.",
            highYieldExamPoints = listOf(
                "Rapid CO2 peritoneal insufflation triggers vagal stretch receptors, eliciting sudden profound sinus bradycardia or sinus arrest; immediate treatment is asking surgeon to vent gas.",
                "Carbon dioxide gas embolism: Sudden precipitous drop in EtCO2 with arterial hypotension and a 'mill-wheel' cardiac murmur; management: desufflate abdomen, 100% O2, left lateral decubitus Trendelenburg position (Durant maneuver).",
                "Post-laparoscopic shoulder tip pain is caused by phrenic nerve C3-C5 sensory irritation from residual carbonic acid gas against the diaphragm.",
                "Trocar insertion injuries to retroperitoneal vessels (aorta, IVC, iliacs) present as sudden unexplained occult hypotension during initial port placement."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_lap_chole_guide_1",
                    question = "During laparoscopic insufflation, what is the initial management if the patient develops profound sinus bradycardia (HR 25 bpm)?",
                    options = listOf(
                        "Ask the surgeon to IMMEDIATELY release the pneumoperitoneum gas to relieve peritoneal stretch",
                        "Administer 1 mg IV Epinephrine bolus immediately",
                        "Increase volatile anesthetic depth to abolish surgical stimulation",
                        "Administer 100% Nitrous Oxide to hasten awakening"
                    ),
                    correctIndex = 0,
                    explanation = "The immediate reflex arc is driven by tension on peritoneal vagal afferents. The most rapid, definitive treatment is immediately deflating the abdomen to release stretch, followed by Atropine or Glycopyrrolate if bradycardia does not promptly recover.",
                    examHighYieldTag = "ABA / NBCRNA Core"
                )
            ),
            references = listOf(
                DrugReference("SAGES Guidelines for Laparoscopic Surgery", "2022", "SAGES"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_surgical_procedural", "anatomy_hepatic_metabolism"),
            relatedDrugIds = listOf("drug_propofol", "drug_rocuronium", "drug_sugammadex", "drug_ondansetron"),
            relatedEquipmentIds = listOf("equip_capnography_analysis", "equip_anesthesia_machine_physics"),
            relatedCalculatorIds = listOf("calc_bmi_ibw", "calc_mean_arterial_pressure"),
            relatedCaseId = "case_post_spinal_hypotension"
        )
    )
}
