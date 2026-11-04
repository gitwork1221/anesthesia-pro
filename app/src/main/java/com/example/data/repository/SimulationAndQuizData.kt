package com.example.data.repository

import com.example.data.model.CaseStage
import com.example.data.model.ClinicalCase
import com.example.data.model.QuizQuestion

object SimulationAndQuizData {

    val clinicalCases: List<ClinicalCase> = listOf(
        ClinicalCase(
            id = "case_post_spinal_hypotension",
            title = "Severe Post-Spinal Hypotension in Emergency Cesarean",
            category = "Obstetric & Hemodynamics",
            patientBrief = "A 26-year-old G1P0 at 39 weeks gestation undergoes spinal anesthesia for urgent Cesarean section due to failure to progress. Baseline BP: 122/74 mmHg, HR: 78 bpm. 1.8 mL hyperbaric 0.5% bupivacaine with 15 mcg fentanyl is successfully placed at L3-L4.",
            stages = listOf(
                CaseStage(
                    stageTitle = "Stage 1: Acute Precipitous Pressure Drop",
                    scenario = "4 minutes post-injection, the sensory level reaches T4 (nipple level). The patient abruptly complains of profound nausea, lightheadedness, and difficulty keeping her eyes open.",
                    vitals = "BP: 74/38 mmHg | HR: 62 bpm | SpO2: 98% | Fetal HR: 105 bpm (late decelerations)",
                    options = listOf(
                        "Immediately inject 4 mg IV Ondansetron for nausea",
                        "Apply Left Uterine Displacement (LUD), open IV fluids wide, and administer Phenylephrine 100 mcg IV",
                        "Administer 100 mcg IV Fentanyl to calm the patient",
                        "Tilt the operating table head-down into deep Trendelenburg"
                    ),
                    correctIndex = 1,
                    immediateFeedback = "Correct! The nausea is the cardinal symptom of acute cerebral hypoperfusion secondary to sympathetic blockade and aortocaval compression. Left uterine displacement relieves inferior vena cava compression, while alpha-1 vasoconstriction restores venous return and uterine perfusion.",
                    rationale = "Treating nausea with anti-emetics alone ignores the life-threatening hypotension. Deep Trendelenburg increases cephalad spread of hyperbaric local anesthetic in the first 15-20 minutes, potentially causing high spinal blockade and respiratory paralysis."
                ),
                CaseStage(
                    stageTitle = "Stage 2: Persistent Bradycardia & Refractory Hypotension",
                    scenario = "2 minutes after initial phenylephrine, BP remains 80/42 mmHg and heart rate decreases further to 46 bpm due to cardioaccelerator blockade (T1-T4) and baroreceptor reflex.",
                    vitals = "BP: 80/42 mmHg | HR: 46 bpm | SpO2: 96% | Fetal HR: 95 bpm",
                    options = listOf(
                        "Give another 200 mcg of pure Phenylephrine",
                        "Administer Ephedrine 10 mg IV or Atropine 0.5 mg IV to restore heart rate and cardiac output",
                        "Start Nitroglycerin infusion",
                        "Induce general anesthesia immediately"
                    ),
                    correctIndex = 1,
                    immediateFeedback = "Correct! When post-spinal hypotension is coupled with significant bradycardia (< 50 bpm), pure alpha-1 agonists like phenylephrine worsen bradycardia via baroreceptor reflex. Ephedrine (mixed alpha/beta) or Atropine restores heart rate, contractility, and cardiac output.",
                    rationale = "Phenylephrine causes reflex bradycardia which further impairs cardiac output when the heart rate is already low. Ephedrine provides the required inotropic and chronotropic boost."
                ),
                CaseStage(
                    stageTitle = "Stage 3: Stabilization & Delivery",
                    scenario = "Following Ephedrine 10 mg and Atropine 0.5 mg, BP stabilizes to 118/72 mmHg and HR increases to 88 bpm. Fetal heart rate recovers to 140 bpm. The infant is delivered vigorously with APGAR scores of 9 and 10.",
                    vitals = "BP: 120/75 mmHg | HR: 84 bpm | SpO2: 99% | Uterus firm",
                    options = listOf(
                        "Infuse Oxytocin 3-5 IU slow IV push followed by continuous infusion (10-20 IU in 500 mL)",
                        "Administer rapid IV bolus of 20 IU concentrated Oxytocin",
                        "Immediately discontinue all IV fluids",
                        "Give Ergometrine 0.5 mg rapid IV"
                    ),
                    correctIndex = 0,
                    immediateFeedback = "Correct! Oxytocin should be administered as a dilute slow injection followed by maintenance infusion. Rapid bolus causes marked peripheral vasodilation, tachycardia, hypotension, and myocardial ischemia.",
                    rationale = "Rapid bolus injection of concentrated oxytocin is a recognized cause of sudden maternal cardiovascular collapse. Ergometrine IV can cause severe hypertension and is contraindicated without dilution or in hypertensive disease."
                )
            ),
            debriefSummary = "Clinical Takeaways:\n1. Nausea in obstetric spinal anesthesia is a hallmark indicator of cerebral and brainstem hypoperfusion—treat the blood pressure first!\n2. Left uterine displacement must be instituted immediately.\n3. Phenylephrine is first-line when HR is preserved; switch to or add Ephedrine/Atropine if bradycardia emerges.\n4. Dilute, controlled oxytocin administration protects against sudden vasoplegia after delivery."
        ),
        ClinicalCase(
            id = "case_cannot_intubate_cannot_oxygenate",
            title = "Cannot Intubate, Cannot Oxygenate (CICO) Airway Crisis",
            category = "Emergency Airway Management",
            patientBrief = "A 54-year-old male with a history of Ludwig's angina and neck radiation presents for emergency incision and drainage. Following intravenous induction and rocuronium administration, the vocal cords cannot be visualized.",
            stages = listOf(
                CaseStage(
                    stageTitle = "Stage 1: Failed Laryngoscopy & Mask Ventilation",
                    scenario = "Direct laryngoscopy with Macintosh 3 reveals Cormack-Lehane Grade 4 (only epiglottis tip visible against posterior wall). Bag-mask ventilation is attempted with one hand, but chest excursion is absent and no capnography waveform appears.",
                    vitals = "HR: 125 bpm | SpO2: 89% and falling | EtCO2: No wave detected",
                    options = listOf(
                        "Continue single-person mask ventilation with higher inspiratory pressure (> 40 cmH2O)",
                        "Declare Difficult Airway, CALL FOR HELP, optimize two-person mask ventilation with oropharyngeal airway and jaw-thrust",
                        "Attempt blind nasal intubation",
                        "Wait for muscle relaxant to wear off spontaneously"
                    ),
                    correctIndex = 1,
                    immediateFeedback = "Correct! Step 1 of the ASA and DAS difficult airway algorithm is to declare the difficulty, mobilize the team, call for difficult airway cart, and optimize mask ventilation using two hands (VE technique) with an oral airway.",
                    rationale = "Excessive mask pressure (> 20-25 cmH2O) forces gas into the stomach, drastically increasing regurgitation risk. Waiting for rocuronium without ventilation leads to fatal hypoxia in minutes."
                ),
                CaseStage(
                    stageTitle = "Stage 2: Supraglottic Airway Rescue",
                    scenario = "Two-person mask ventilation yields only marginal air entry. SpO2 falls to 82%. A second-generation supraglottic airway (LMA Supreme size 5) is placed immediately.",
                    vitals = "HR: 138 bpm | SpO2: 78% | High resistance, no exhaled tidal volume",
                    options = listOf(
                        "Attempt repeated direct laryngoscopy with larger blade",
                        "Remove and re-seat SAD once; if ventilation fails, declare CICO (Cannot Intubate, Cannot Oxygenate) and prepare emergency Front-of-Neck Access",
                        "Administer IV Succinylcholine on top of Rocuronium",
                        "Give Naloxone IV"
                    ),
                    correctIndex = 1,
                    immediateFeedback = "Correct! In the Difficult Airway Society guidelines: limit supraglottic device attempts to maximum 2. If oxygenation cannot be restored, the scenario transitions to Cannot Intubate, Cannot Oxygenate (CICO). Emergency front-of-neck access must be performed without delay.",
                    rationale = "Repeated forceful intubation attempts cause edema, hemorrhage, and complete upper airway occlusion. Rocuronium is already blocking the patient; succinylcholine adds no benefit."
                ),
                CaseStage(
                    stageTitle = "Stage 3: Emergency Front-of-Neck Access (eFONA)",
                    scenario = "The supraglottic airway fails to provide ventilation. SpO2 plummets to 64%, patient has severe bradycardia (HR 38 bpm). The anesthetist declares CICO.",
                    vitals = "HR: 38 bpm (agonal) | SpO2: 58% | Cyanotic, pupils dilating",
                    options = listOf(
                        "Perform emergency scalpel-bougie-tube cricothyroidotomy (horizontal stab through cricothyroid membrane, bougie inserted, 6.0 mm cuffed tube railroaded)",
                        "Wait 5 minutes for Sugammadex to reverse rocuronium",
                        "Attempt another video laryngoscope view",
                        "Call ENT and wait for their arrival in OR"
                    ),
                    correctIndex = 0,
                    immediateFeedback = "Correct! Life-saving eFONA: Scalpel-Bougie-Tube technique through the cricothyroid membrane must be performed immediately by the anesthetist. Do not wait for ENT arrival or delayed reversal when SpO2 is < 65% and bradycardia is present.",
                    rationale = "Severe hypoxia with bradycardia indicates imminent cardiac arrest. The scalpel-bougie-cricothyroidotomy technique takes under 40 seconds in trained hands and bypasses the obstructed upper airway directly."
                )
            ),
            debriefSummary = "Key Takeaways:\n1. Early declaration and calling for help prevents fixation errors.\n2. Limit laryngoscopy attempts to 3 (or 4 by expert) and SAD attempts to 2.\n3. Scalpel-Bougie-Tube is the recommended technique for surgical cricothyroidotomy.\n4. Oxygenation takes absolute precedence over intubation."
        )
    )

    val quizQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "quiz_1",
            stem = "Which of the following local anesthetics has the highest potential for intractable cardiotoxicity and cardiac sodium-channel dissociation resistance?",
            options = listOf("Lidocaine", "Procaine", "Bupivacaine", "Mepivacaine"),
            correctIndex = 2,
            explanation = "Bupivacaine exhibits 'fast-in, slow-out' kinetics at cardiac voltage-gated sodium channels (Nav1.5). It dissociates very slowly during diastole, causing conduction block, wide QRS, ventricular tachycardia/fibrillation, and electromechanical dissociation resistant to standard resuscitation.",
            topic = "Pharmacology & Toxicology"
        ),
        QuizQuestion(
            id = "quiz_2",
            stem = "What is the earliest and most sensitive sign of Malignant Hyperthermia during general anesthesia with volatile agents?",
            options = listOf(
                "Rapid rise in body temperature to > 40°C",
                "Unexplained, persistent increase in End-Tidal CO2 (EtCO2)",
                "Massive hyperkalemia on arterial blood gas",
                "Gross reddish-brown myoglobinuria in urine bag"
            ),
            correctIndex = 1,
            explanation = "Unexplained, dramatic elevation in End-Tidal CO2 refractory to substantial increases in minute ventilation is the earliest and most consistent sign of MH, reflecting hypermetabolism. Temperature elevation is a late sign and myoglobinuria occurs hours later.",
            topic = "Crisis Management"
        ),
        QuizQuestion(
            id = "quiz_3",
            stem = "When performing lumbar subarachnoid puncture in an adult, which anatomical structure marks the termination of the adult spinal cord (conus medullaris)?",
            options = listOf("L1 - L2 interspace", "L4 - L5 interspace", "S2 level", "T10 level"),
            correctIndex = 0,
            explanation = "In normal adults, the spinal cord ends as the conus medullaris at the lower border of L1 or upper L2. The dural sac continues downward to terminate at S2. Spinal anesthesia is safely performed at L3-L4 or L4-L5 to prevent mechanical cord damage.",
            topic = "Anatomy & Regional Anesthesia"
        ),
        QuizQuestion(
            id = "quiz_4",
            stem = "What is the correct initial intravenous bolus dose of 20% Lipid Emulsion for treating Local Anesthetic Systemic Toxicity (LAST)?",
            options = listOf("0.25 mL/kg", "1.5 mL/kg", "5.0 mL/kg", "10.0 mL/kg"),
            correctIndex = 1,
            explanation = "The standard ASRA guidelines specify an initial bolus of 1.5 mL/kg IV of 20% Lipid Emulsion over 2-3 minutes (~100 mL for a 70 kg patient), followed by a continuous infusion of 0.25 mL/kg/min.",
            topic = "Pharmacology & Safety"
        ),
        QuizQuestion(
            id = "quiz_5",
            stem = "A 'curare cleft' observed on an End-Tidal CO2 capnogram waveform signifies which clinical event?",
            options = listOf(
                "Exhaustion of the soda lime CO2 absorbent",
                "Bronchospasm or airway resistance",
                "Spontaneous respiratory effort by the patient during mechanical ventilation",
                "Accidental esophageal intubation"
            ),
            correctIndex = 2,
            explanation = "A curare cleft is a small dip or notch appearing in the latter half of the alveolar plateau (Phase III). It indicates the diaphragm contracting as muscle relaxant wears off, causing a brief spontaneous inspiratory effort.",
            topic = "Equipment & Monitoring"
        ),
        QuizQuestion(
            id = "quiz_6",
            stem = "Using the Devine formula, what is the Ideal Body Weight (IBW) of a 6-foot-tall (72 inches) male patient?",
            options = listOf("68.5 kg", "73.0 kg", "77.6 kg", "82.4 kg"),
            correctIndex = 2,
            explanation = "Devine formula for males: 50 kg + 2.3 kg per inch over 5 feet (60 inches). Height = 72 inches (12 inches over 60). IBW = 50 + (2.3 × 12) = 50 + 27.6 = 77.6 kg.",
            topic = "Calculators & Dosing"
        )
    )
}
