package com.example.data.repository.surgical

import com.example.data.model.DrugReference
import com.example.data.model.ReviewQuestion
import com.example.data.model.SurgicalGuide
import com.example.data.model.SurgicalSpecialty

object PediatricUrologyAndSpecialtyGuides {

    val guides: List<SurgicalGuide> = listOf(
        SurgicalGuide(
            id = "guide_pyloromyotomy",
            procedureName = "Pyloromyotomy (Infantile Pyloric Stenosis)",
            surgicalSpecialty = SurgicalSpecialty.PEDIATRIC_SURGERY.displayName,
            overview = "Surgical division of hypertrophied pyloric circular muscle in infants (typically 2-8 weeks old). Pyloric stenosis is a MEDICAL EMERGENCY requiring metabolic resuscitation before surgery, NEVER a surgical emergency.",
            preoperativeAssessment = "Assess electrolyte panel, chloride, potassium, serum bicarbonate, venous/arterial pH, and hydration status (tear production, anterior fontanelle, capillary refill, urine output).",
            anestheticTechnique = "General endotracheal anesthesia with cuffed or uncuffed ETT.\n• Pre-induction: Suction stomach with 10-14 Fr orogastric catheter in three positions (supine, left lateral, right lateral).\n• Induction: Rapid Sequence Induction with cricoid pressure or gentle Awake Intubation in experienced hands.\n• Muscle relaxant: Rocuronium (0.6-1.0 mg/kg) or Succinylcholine (2-3 mg/kg IV with Atropine 0.02 mg/kg).",
            airwayConsiderations = "Full stomach protocol mandatory regardless of fasting time because the obstructed pylorus retains gastric secretions and milk curds.",
            positioningAndNerveSafety = "Supine on active warming mattress. Avoid hypothermia in neonates (large surface area to body mass ratio).",
            hemodynamicManagement = "Dehydration correction prior to OR with 0.9% Normal Saline + 5% Dextrose + KCl (20-30 mEq/L once urine output confirmed). Target serum Cl > 100 mEq/L, K > 3.5 mEq/L, HCO3 < 30 mEq/L, and urine output > 1-2 mL/kg/hr.",
            analgesiaPlan = "Local anesthetic infiltration at surgical incision by surgeon (0.25% Bupivacaine or 0.2% Ropivacaine, max 2 mg/kg) + IV Acetaminophen (15 mg/kg). Minimize opioids to prevent postoperative apnea.",
            potentialComplications = listOf(
                "Aspiration of retained gastric contents during induction",
                "Postoperative respiratory depression / apnea (exacerbated by residual CSF metabolic alkalosis)",
                "Accidental duodenal mucosal perforation by surgeon (test with air insufflation through OGT)",
                "Neonatal hypothermia and hypoglycemia"
            ),
            postoperativeRecovery = "Extubate fully awake, vigorous, and breathing spontaneously with intact airway reflexes. Monitor in PACU with continuous pulse oximetry and apnea monitoring for at least 12-24 hours.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine position. Persistent non-bilious projectile vomiting causes loss of gastric hydrochloric acid (HCl), leading to classic Hypochloremic, Hypokalemic Metabolic Alkalosis with paradoxical aciduria.",
            relevantAnatomyPhysiology = "The kidney conserves intravascular volume (sodium) at the expense of potassium and hydrogen ions in the collecting duct via aldosterone. As potassium is depleted, the kidney obligatorily secretes hydrogen ions into the urine, producing paradoxical aciduria.",
            monitoringAndAccess = "Standard ASA monitors + precordial stethoscope for real-time heart tone monitoring. 22G or 24G peripheral IV. Temperature probe (axillary/esophageal).",
            drugAndFluidStrategy = "Never take infant to surgery until metabolic alkalosis is corrected! Operating while alkalotic produces fatal postoperative central apnea because high CSF bicarbonate blunts respiratory drive.",
            expectedBloodLossAndTransfusion = "Negligible (< 5-10 mL). Transfusion is not required.",
            highYieldExamPoints = listOf(
                "Pyloric stenosis is a MEDICAL, not a surgical emergency; surgical repair must be deferred until dehydration and hypochloremic metabolic alkalosis are completely corrected.",
                "Paradoxical aciduria develops in late dehydration when profound hypokalemia forces renal tubular cells to exchange H+ instead of K+ for reabsorbed Na+.",
                "Before induction, the stomach must be emptied with a large-bore catheter in three positions (supine, left lateral, right lateral) to evacuate milk curds.",
                "Postoperative central apnea is frequent because corrected blood pH does not instantly normalize CSF bicarbonate, which crosses the blood-brain barrier slowly."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_pyloric_guide_1",
                    question = "Why does an infant with advanced pyloric stenosis develop 'paradoxical aciduria' despite profound systemic metabolic alkalosis?",
                    options = listOf(
                        "Severe hypokalemia and volume contraction force the collecting duct to excrete H+ ions to reabsorb Na+",
                        "Direct renal excretion of accumulated gastric lactic acid",
                        "Aldosterone deficiency impairs sodium retention",
                        "Bacterial contamination of the urinary bladder"
                    ),
                    correctIndex = 0,
                    explanation = "Under intense aldosterone stimulation to conserve intravascular volume, the distal nephron reabsorbs sodium in exchange for potassium. When potassium is severely depleted, the kidney has no choice but to excrete hydrogen ions instead, paradoxically acidifying the urine in the face of worsening systemic alkalosis.",
                    examHighYieldTag = "ABA / SPA Core"
                )
            ),
            references = listOf(
                DrugReference("Smith's Anesthesia for Infants and Children, 10th Edition", "2021", "Elsevier"),
                DrugReference("Cote and Lerman's A Practice of Anesthesia for Infants and Children, 6th Edition", "2019", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_pregnancy_neonatal", "anatomy_respiratory_airway"),
            relatedDrugIds = listOf("drug_atropine", "drug_propofol", "drug_succinylcholine", "drug_acetaminophen_iv"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_pediatric_ett", "calc_fluid_maintenance"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        ),
        SurgicalGuide(
            id = "guide_turp",
            procedureName = "Transurethral Resection of the Prostate (TURP)",
            surgicalSpecialty = SurgicalSpecialty.UROLOGY.displayName,
            overview = "Endoscopic resection of obstructing prostate tissue using a resectoscope. Irrigation fluid under hydrostatic pressure can enter open prostatic venous sinuses, causing systemic fluid absorption (TURP Syndrome).",
            preoperativeAssessment = "Cardiovascular reserve (elderly males), renal function, baseline electrolyte panel (serum sodium), and prostate gland size (> 60 g increases absorption risk).",
            anestheticTechnique = "Neuraxial anesthesia (Spinal anesthesia to a T10 sensory dermatome level) is the preferred gold standard.\n• Spinal with 8-10 mg 0.5% Bupivacaine.\n• T10 level provides anesthesia for the bladder and prostate without blocking abdominal pain: if bladder perforation occurs, the awake patient will immediately complain of severe abdominal pain or shoulder pain.",
            airwayConsiderations = "Spontaneous breathing with light sedation under spinal anesthesia. If general anesthesia is used, signs of TURP syndrome and bladder perforation are masked.",
            positioningAndNerveSafety = "Lithotomy position. Pad fibular heads carefully to prevent common peroneal nerve compression against stirrups (foot drop). Pad medial knee to protect saphenous nerve.",
            hemodynamicManagement = "Continuous irrigation height kept < 60 cm above operating table to minimize hydrostatic driving pressure into venous sinuses. Limit resection time to < 60 minutes.",
            analgesiaPlan = "Neuraxial block provides complete surgical anesthesia. Post-op bladder spasms treated with oral antispasmodics (Oxybutynin) or small-dose belladonna and opium (B&O) suppositories.",
            potentialComplications = listOf(
                "TURP Syndrome: acute hypervolemic water intoxication with profound dilutional hyponatremia (Na < 120 mEq/L), pulmonary edema, confusion, and seizures",
                "Glycine toxicity: transient blindness (amaurosis) and encephalopathy",
                "Intra- or extraperitoneal bladder perforation (awake patient: sudden sharp pain, abdominal rigidity, nausea)",
                "Bacteremia / septic shock from infected prostatic tissue"
            ),
            postoperativeRecovery = "Check post-op serum electrolytes (sodium) immediately. PACU monitoring for confusion, restlessness, shivering, and continuous bladder irrigation drainage color.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Lithotomy position. Elevating legs increases central blood volume by 500-800 mL (preload autotransfusion); lowering legs at the end of surgery causes sudden peripheral venous pooling and hypotension.",
            relevantAnatomyPhysiology = "The prostate capsule is surrounded by a dense plexus of low-pressure pelvic veins (Santorini's plexus). When hydrostatic irrigation pressure exceeds venous pressure (10-15 mmHg), rapid systemic fluid absorption occurs (average 20 mL/min).",
            monitoringAndAccess = "Standard ASA monitoring. 18G peripheral line. In patients with large prostates or severe cardiac disease, continuous arterial line enables rapid serial point-of-care sodium and blood gas checks.",
            drugAndFluidStrategy = "Modern bipolar resection uses 0.9% Normal Saline, eliminating classic hyponatremic TURP syndrome. Monopolar resection uses non-electrolytic solutions (1.5% Glycine, Mannitol, Sorbitol). Hypertonic 3% Saline (100 mL IV over 10 min) is the specific rescue antidote for symptomatic hyponatremic seizures.",
            expectedBloodLossAndTransfusion = "Average EBL: 200-500 mL (difficult to measure due to mixing with irrigation effluent). Transfusion is guided by clinical hemodynamics and postoperative hematocrit.",
            highYieldExamPoints = listOf(
                "A sensory block level of T10 (umbilicus) is ideal for TURP because it provides adequate anesthesia while allowing an awake patient to detect pain from extraperitoneal or intraperitoneal bladder perforation.",
                "Classic triad of TURP Syndrome: Dilutional hyponatremia (serum Na < 120 mEq/L), fluid overload / acute pulmonary edema, and neurological symptoms (restlessness, confusion, seizures, coma).",
                "Glycine irrigation is an inhibitory neurotransmitter in the retina; massive absorption causes transient, reversible bilateral blindness (amaurosis).",
                "Treatment of severe hyponatremic encephalopathy / seizures (Na < 120 mEq/L): Stop surgery immediately, restrict fluids, IV Furosemide, and administer Hypertonic 3% Saline at 1-2 mL/kg/hr to correct Na by no more than 8-10 mEq/L in 24 hours (prevents Central Pontine Myelinolysis)."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_turp_guide_1",
                    question = "Forty minutes into a monopolar TURP under spinal anesthesia, the patient becomes agitated, hypertensive (BP 185/105), bradycardic (HR 42), and complains of blurred vision. What is the immediate diagnosis?",
                    options = listOf(
                        "TURP Syndrome from massive non-electrolytic irrigation fluid absorption with dilutional hyponatremia",
                        "High spinal block ascending to the cervical levels",
                        "Acute coronary syndrome from surgical stress",
                        "Malignant hyperthermia triggered by local anesthetic"
                    ),
                    correctIndex = 0,
                    explanation = "Hydrostatic absorption of hypotonic glycine irrigation into unsealed prostatic venous sinuses causes acute intravascular volume overload (hypertension, reflex bradycardia) and dilutional hyponatremia with cerebral edema (agitation, confusion, blurred vision). This is classic TURP Syndrome.",
                    examHighYieldTag = "ABA / AUA Core"
                )
            ),
            references = listOf(
                DrugReference("Campbell-Walsh-Wein Urology, 12th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_renal_electrolytes", "anatomy_nervous_system"),
            relatedDrugIds = listOf("drug_bupivacaine", "drug_ondansetron", "drug_fentanyl", "drug_midazolam"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_local_anesthetic", "calc_mean_arterial_pressure"),
            relatedCaseId = "case_post_spinal_hypotension"
        ),
        SurgicalGuide(
            id = "guide_bleeding_tonsil",
            procedureName = "Post-Tonsillectomy Hemorrhage",
            surgicalSpecialty = SurgicalSpecialty.ENT_MAXILLOFACIAL.displayName,
            overview = "Life-threatening pediatric and adult ENT emergency. Occurs primarily within 24 hours (primary hemorrhage) or 5-10 days postoperatively when eschar sloughs (secondary hemorrhage). Patient has occult hypovolemia, swallowed blood, and an actively bleeding, obscured airway.",
            preoperativeAssessment = "Assess volume status (tachycardia, delayed capillary refill, orthostasis), quantity of bleeding, and coagulation history. Do NOT examine or instrument the pharynx without full resuscitation and OR readiness.",
            anestheticTechnique = "General endotracheal anesthesia with Rapid Sequence Induction (RSI).\n• Induction: Ketamine (1-2 mg/kg) or Etomidate (0.2-0.3 mg/kg) to maintain perfusion pressure in hypovolemia.\n• Muscle relaxant: Succinylcholine (1.5-2.0 mg/kg) or Rocuronium (1.2 mg/kg).\n• Intubation: Large-bore cuffed oral ETT with video laryngoscope (GlideScope) and two functioning rigid Yankauer suction catheters running simultaneously.",
            airwayConsiderations = "Classified as an 'obligatory full stomach' with an actively bleeding airway. Blood obscures vocal cords rapidly. Pre-oxygenate with 100% O2 in slight head-up or left lateral Trendelenburg position.",
            positioningAndNerveSafety = "Slight reverse Trendelenburg or left lateral tilt during pre-oxygenation, transitioning to supine with shoulder roll for tonsillectomy mouth gag (Crowe-Davis).",
            hemodynamicManagement = "Intravascular resuscitation with balanced crystalloids and blood BEFORE induction whenever possible. Induction in a severely hypovolemic child will precipitate cardiac arrest.",
            analgesiaPlan = "IV Paracetamol (15 mg/kg) + IV Dexamethasone. Avoid NSAIDs (Ketorolac / Ibuprofen) due to platelet inhibition and risk of re-bleeding.",
            potentialComplications = listOf(
                "Aspiration of large blood clots causing acute complete airway obstruction",
                "Cannot intubate, cannot oxygenate (CICO) due to blood obscuring laryngeal structures",
                "Severe hypovolemic cardiac arrest on induction of anesthesia",
                "Post-extubation laryngospasm and pulmonary edema"
            ),
            postoperativeRecovery = "Extubate fully awake in lateral recovery position ('tonsil position') with airway reflexes fully returned. Monitor in PACU / pediatric unit for re-bleeding.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine with slight Trendelenburg or left lateral during induction. Swallowed blood is an emetic: child may look hemodynamically stable while harboring 500 mL of clotted blood in the stomach.",
            relevantAnatomyPhysiology = "Palatine tonsil blood supply: Tonsillar branch of facial artery, ascending pharyngeal, dorsal lingual, and descending palatine arteries. Sensory innervation via glossopharyngeal nerve (CN IX).",
            monitoringAndAccess = "Standard ASA monitoring. Two working IV lines (essential for rapid volume resuscitation). Have active suction canisters and rigid Yankauers tested.",
            drugAndFluidStrategy = "Resuscitation with warmed crystalloid (20 mL/kg bolus) or PRBC (10 mL/kg) prior to induction. Avoid excessive opioids to maintain spontaneous respiratory drive post-extubation.",
            expectedBloodLossAndTransfusion = "Significant (200-800 mL swallowed blood). Type & screen / crossmatch must be verified immediately.",
            highYieldExamPoints = listOf(
                "A child with a bleeding tonsil is ALWAYS considered hypovolemic and full stomach; the stomach must be emptied with an OGT under direct visualization after the airway is secured.",
                "Always have TWO suction sources powered on and tested with rigid Yankauer tips before inducing anesthesia for a bleeding tonsil.",
                "Extubation must be performed with the patient wide awake in the lateral 'tonsil position' (head slightly dependent) so residual blood drains out of the mouth rather than into the trachea.",
                "Secondary post-tonsillectomy hemorrhage peaks at postoperative days 5-10 when the fibrin slough detaches from the healing tonsillar fossa."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_tonsil_guide_1",
                    question = "What is the most critical preparation before inducing anesthesia in a 7-year-old child presenting with secondary post-tonsillectomy hemorrhage?",
                    options = listOf(
                        "Establish intravenous access, initiate volume resuscitation, and verify TWO functioning rigid suction catheters",
                        "Administer high-dose oral sedation to reduce agitation",
                        "Perform indirect mirror laryngoscopy in the preoperative holding area",
                        "Administer IV Ketorolac to provide preemptive analgesia"
                    ),
                    correctIndex = 0,
                    explanation = "Children with bleeding tonsils have occult hypovolemia from swallowed blood. Inducing anesthesia without volume resuscitation precipitates cardiovascular collapse. Two high-flow suction units are mandatory because blood clots rapidly clog suction tips, blinding the operator during intubation.",
                    examHighYieldTag = "ABA / ASPO Core"
                )
            ),
            references = listOf(
                DrugReference("AAO-HNS Clinical Practice Guideline: Tonsillectomy in Children", "2019", "Otolaryngology–Head and Neck Surgery"),
                DrugReference("Smith's Anesthesia for Infants and Children, 10th Edition", "2021", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_respiratory_airway", "anatomy_pregnancy_neonatal"),
            relatedDrugIds = listOf("drug_ketamine", "drug_rocuronium", "drug_succinylcholine", "drug_atropine"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_pediatric_ett", "calc_fluid_maintenance"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        ),
        SurgicalGuide(
            id = "guide_open_globe",
            procedureName = "Open Globe Repair / Ophthalmic Trauma",
            surgicalSpecialty = SurgicalSpecialty.OPHTHALMIC_SURGERY.displayName,
            overview = "Surgical repair of a ruptured or penetrated eyeball. The primary anesthetic dilemma is preventing an increase in Intraocular Pressure (IOP) that would extrude intraocular contents (vitreous loss, permanent blindness) while managing a full-stomach aspiration risk in trauma.",
            preoperativeAssessment = "Mechanism of eye injury, presence of foreign bodies, fasting status, associated head/facial trauma, and cardiovascular co-morbidities.",
            anestheticTechnique = "General endotracheal anesthesia with modified Rapid Sequence Induction.\n• Rocuronium (1.0-1.2 mg/kg) with Sugammadex available is the modern first choice (avoids succinylcholine-induced IOP spike).\n• Retrobulbar and peribulbar nerve blocks are STRICTLY CONTRAINDICATED (causes globe rupture and intraorbital pressure spike).",
            airwayConsiderations = "Smooth, deep induction and intubation without coughing, bucking, or straining. Intravenous Lidocaine (1.5 mg/kg) and Opioid (Fentanyl 2-3 mcg/kg) blunts airway reflexes.",
            positioningAndNerveSafety = "Supine with head slightly elevated (15-20°) to promote ophthalmic venous drainage and lower IOP. Avoid any external pressure on the injured eye (use rigid Fox eye shield until prep).",
            hemodynamicManagement = "Maintain normal to low-normal blood pressure. Avoid severe hypertension (raises choroidal blood volume and IOP). Beware Oculocardiac Reflex (OCR: trigeminal-vagal reflex causing severe bradycardia).",
            analgesiaPlan = "Multimodal: IV Acetaminophen, low-dose opioids. Avoid nausea and vomiting aggressively: triple antiemetic prophylaxis (Dexamethasone + Ondansetron + Droperidol/Metoclopramide).",
            potentialComplications = listOf(
                "Extrusion of intraocular contents and permanent blindness from coughing or IOP spike",
                "Oculocardiac Reflex (OCR: profound bradycardia, asystole during extraocular muscle traction)",
                "Pulmonary aspiration in unfasted trauma patient",
                "Postoperative vomiting causing surgical dehiscence"
            ),
            postoperativeRecovery = "Smooth emergence without coughing (extubate deep in select patients or use IV lidocaine / remifentanil wake-up). Strict antiemetic control in PACU.",
            reviewStatus = "Clinically Reviewed - Guidelines Aligned",
            surgicalPositionAndPhysiology = "Supine, head slightly elevated. Normal IOP is 10-21 mmHg. Intraocular pressure is governed by aqueous humor production/drainage, choroidal arterial volume, and central venous pressure (CVP). Coughing or bucking raises CVP to > 40-50 mmHg, immediately transmitting pressure to the eye.",
            relevantAnatomyPhysiology = "Oculocardiac Reflex (Aschner reflex): Afferent limb via ophthalmic branch of Trigeminal Nerve (CN V1) -> Gasserian ganglion -> visceral sensory nucleus; Efferent limb via Vagus Nerve (CN X) -> sinoatrial node, producing profound sinus bradycardia, nodal rhythms, or ventricular asystole.",
            monitoringAndAccess = "Standard ASA monitoring with continuous ECG tone. 18G or 20G IV line. Atropine or Glycopyrrolate immediately available at the bedside for OCR.",
            drugAndFluidStrategy = "Succinylcholine transiently increases IOP by 6-10 mmHg for 5-8 minutes due to tonic contraction of extraocular muscles. High-dose Rocuronium (1.2 mg/kg) produces identical 45-second intubating conditions WITHOUT increasing IOP.",
            expectedBloodLossAndTransfusion = "Negligible (< 10 mL). Transfusion is not required.",
            highYieldExamPoints = listOf(
                "Oculocardiac Reflex (OCR): Trigeminal (CN V1) afferent and Vagal (CN X) efferent arc triggered by traction on extraocular muscles (especially medial rectus); first-line treatment: ask surgeon to immediately stop traction.",
                "Succinylcholine increases IOP by 6-10 mmHg for 5-8 minutes; high-dose Rocuronium (1.2 mg/kg) is preferred in an open globe to avoid extrusion of vitreous humor.",
                "Retrobulbar and peribulbar local anesthetic blocks are STRICTLY CONTRAINDICATED in an open globe injury because volume injection into the closed orbit increases intraorbital pressure and expels intraocular contents.",
                "Postoperative nausea and vomiting (PONV) must be aggressively prevented because retching spikes central venous pressure and intraocular pressure, rupturing fresh corneal sutures."
            ),
            caseQuestions = listOf(
                ReviewQuestion(
                    id = "q_eye_guide_1",
                    question = "During surgical repair of a ruptured globe, the surgeon pulls traction on the medial rectus muscle. The patient's ECG abruptly slows from 75 to 22 bpm. What is the immediate first action?",
                    options = listOf(
                        "Ask the surgeon to IMMEDIATELY release traction on the extraocular muscle",
                        "Administer 1 mg IV Epinephrine push",
                        "Perform immediate chest compressions",
                        "Deepen volatile anesthetic to 2 MAC"
                    ),
                    correctIndex = 0,
                    explanation = "The Oculocardiac Reflex (OCR) is triggered by traction on the extraocular muscles (medial rectus is most sensitive) via the trigeminal-vagal reflex arc (CN V1 to CN X). Releasing traction immediately breaks the reflex arc in the vast majority of cases. If bradycardia persists, administer Atropine (0.01-0.02 mg/kg IV).",
                    examHighYieldTag = "ABA / Ophthalmic Core"
                )
            ),
            references = listOf(
                DrugReference("Kumar and Dodds' Ophthalmic Anaesthesia, 4th Edition", "2020", "Oxford University Press"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedAnatomyTopicIds = listOf("anatomy_nervous_system", "anatomy_respiratory_airway"),
            relatedDrugIds = listOf("drug_rocuronium", "drug_sugammadex", "drug_propofol", "drug_atropine"),
            relatedEquipmentIds = listOf("equip_anesthesia_machine_physics", "equip_capnography_analysis"),
            relatedCalculatorIds = listOf("calc_pediatric_ett", "calc_mean_arterial_pressure"),
            relatedCaseId = "case_cannot_intubate_cannot_oxygenate"
        )
    )
}
