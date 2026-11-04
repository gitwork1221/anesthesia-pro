package com.example.data.repository

import com.example.data.model.AcademicYear
import com.example.data.model.CurriculumCategory
import com.example.data.model.CurriculumMappingStatus
import com.example.data.model.CurriculumModule
import com.example.data.model.Lesson
import com.example.data.model.ReviewQuestion

object CurriculumData {

    /**
     * Ethiopian National Harmonized Modular Curriculum for Generic BSc in Anesthesia (Feb 2021 framework).
     * Modules are structured across Academic Years I to IV.
     * Where official course codes and ECTS are not confirmed from the primary document,
     * they are honestly marked as AWAITING_OFFICIAL_MAPPING.
     */
    val modules: List<CurriculumModule> = listOf(
        // YEAR I: Foundational Biomedical Sciences & General Modules
        CurriculumModule(
            id = "mod_anatomy_physiology",
            name = "Applied Anatomy & Physiology for Anesthesia",
            moduleCode = null, // Kept null until verified from official Ethiopian syllabus document
            ects = null,
            durationWeeks = 16,
            academicYear = AcademicYear.YEAR_1,
            semesterOrBlock = "Year I",
            category = CurriculumCategory.FOUNDATIONAL_SCIENCES,
            description = "Detailed structural and functional organization of respiratory, cardiovascular, nervous, and renal systems in relation to perioperative patient care.",
            learningOutcomes = listOf(
                "Describe functional airway anatomy and mechanisms of upper airway obstruction.",
                "Explain cardiopulmonary dynamics, ventricular pressure-volume loops, and coronary perfusion.",
                "Outline neuraxial anatomy from skin to thecal sac and spinal cord terminations.",
                "Detail renal fluid and electrolyte handling under surgical stress and volatile exposure."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = listOf("lesson_spinal_anesthesia_physiology")
        ),
        CurriculumModule(
            id = "mod_biomedical_sciences",
            name = "Biomedical Sciences, Pathology & Ethics",
            moduleCode = null,
            ects = null,
            durationWeeks = 16,
            academicYear = AcademicYear.YEAR_1,
            semesterOrBlock = "Year I",
            category = CurriculumCategory.FOUNDATIONAL_SCIENCES,
            description = "Fundamental cellular biology, medical biochemistry, general pathology, and professional health ethics in Ethiopian healthcare settings.",
            learningOutcomes = listOf(
                "Explain cell membrane potentials, ion gradients, and glycocalyx physiology.",
                "Identify pathological mechanisms of systemic inflammation, shock, and organ failure.",
                "Apply ethical principles of patient autonomy, informed consent, and professional vigilance."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = emptyList()
        ),

        // YEAR II: Pre-Clinical Anesthesia & Core Foundations
        CurriculumModule(
            id = "mod_airway_respiratory",
            name = "Clinical Airway Assessment & Management",
            moduleCode = null,
            ects = null,
            durationWeeks = 12,
            academicYear = AcademicYear.YEAR_2,
            semesterOrBlock = "Year II",
            category = CurriculumCategory.ANESTHESIA_FOUNDATIONS,
            description = "Preoperative airway examination (LEMON, Mallampati), prediction of difficult mask ventilation, direct/videolaryngoscopy, and emergency front-of-neck access.",
            learningOutcomes = listOf(
                "Perform systematic physical airway assessment using modified Mallampati and LEMON criteria.",
                "Select and size oral/nasal airways, supraglottic devices, and endotracheal tubes.",
                "Formulate structured algorithms for unanticipated difficult intubation and Cannot Intubate Cannot Oxygenate (CICO)."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = listOf("lesson_airway_assessment")
        ),
        CurriculumModule(
            id = "mod_principles_anesthesia",
            name = "Principles & Practice of General Anesthesia",
            moduleCode = null,
            ects = null,
            durationWeeks = 14,
            academicYear = AcademicYear.YEAR_2,
            semesterOrBlock = "Year II",
            category = CurriculumCategory.ANESTHESIA_FOUNDATIONS,
            description = "Triad of general anesthesia: hypnosis, analgesia, and muscle relaxation. Inhalational pharmacokinetics (MAC, blood:gas partition) and intravenous induction.",
            learningOutcomes = listOf(
                "Explain Guedel's stages of anesthesia and modern balanced anesthesia principles.",
                "Calculate and adjust age-specific Minimum Alveolar Concentration (MAC).",
                "Formulate intravenous induction and volatile maintenance dosing regimens."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = listOf("lesson_general_anesthesia_stages")
        ),
        CurriculumModule(
            id = "mod_anesthesia_equipment",
            name = "Anesthesia Machine Physics & Monitoring",
            moduleCode = null,
            ects = null,
            durationWeeks = 10,
            academicYear = AcademicYear.YEAR_2,
            semesterOrBlock = "Year II",
            category = CurriculumCategory.ANESTHESIA_FOUNDATIONS,
            description = "Workstation gas supply pipelines, Pin Index & Diameter Index safety systems, vaporizers, breathing circuits, capnography, and ASA standard monitoring.",
            learningOutcomes = listOf(
                "Trace gas flow from high-pressure cylinders through intermediate regulators to patient circuit.",
                "Perform standard pre-use machine checkout and identify circuit leaks.",
                "Interpret capnogram waveforms and troubleshoot breathing circuit disconnects."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = emptyList()
        ),

        // YEAR III: Clinical Anesthesia Specialty Blocks & Perioperative Care
        CurriculumModule(
            id = "mod_obstetric_gynecology",
            name = "Obstetric & Gynecologic Anesthesia",
            moduleCode = null,
            ects = null,
            durationWeeks = 10,
            academicYear = AcademicYear.YEAR_3,
            semesterOrBlock = "Year III",
            category = CurriculumCategory.SPECIALTY_ANESTHESIA,
            description = "Physiological adaptations of pregnancy, maternal-fetal pharmacology, spinal/epidural anesthesia for Cesarean delivery, preeclampsia, and obstetric hemorrhage.",
            learningOutcomes = listOf(
                "Describe aortocaval compression, supine hypotensive syndrome, and left uterine tilt.",
                "Administer subarachnoid spinal anesthesia with hyperbaric bupivacaine and opioid adjuvants.",
                "Manage acute post-spinal hypotension, postpartum hemorrhage, and uterotonic medications."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = listOf("lesson_spinal_anesthesia_physiology")
        ),
        CurriculumModule(
            id = "mod_pediatric_anesthesia",
            name = "Pediatric & Neonatal Anesthesia",
            moduleCode = null,
            ects = null,
            durationWeeks = 8,
            academicYear = AcademicYear.YEAR_3,
            semesterOrBlock = "Year III",
            category = CurriculumCategory.SPECIALTY_ANESTHESIA,
            description = "Pediatric developmental anatomy, airway differences, weight-based dosing, fluid requirements (4-2-1 rule), ETT sizing formulas, and pyloric stenosis repair.",
            learningOutcomes = listOf(
                "Contrast infant vs adult airway anatomy (large occiput, cephalad larynx C3-C4, omega epiglottis).",
                "Calculate pediatric cuffed/uncuffed endotracheal tube sizes and depth of insertion.",
                "Formulate perioperative fasting, fluid resuscitation, and blood conservation plans for neonates."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = emptyList()
        ),
        CurriculumModule(
            id = "mod_trauma_emergency",
            name = "Emergency Surgery, Trauma & Acute Care",
            moduleCode = null,
            ects = null,
            durationWeeks = 10,
            academicYear = AcademicYear.YEAR_3,
            semesterOrBlock = "Year III",
            category = CurriculumCategory.SPECIALTY_ANESTHESIA,
            description = "Rapid Sequence Induction (RSI), full stomach management, hemorrhagic shock resuscitation, acute peritonitis, and damage control anesthesia.",
            learningOutcomes = listOf(
                "Perform safe Rapid Sequence Induction with cricoid pressure and appropriate muscle relaxants.",
                "Differentiate massive transfusion protocols, balanced crystalloid use, and early vasopressor initiation.",
                "Safely manage patients presenting with acute bowel obstruction and peritoneal contamination."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = emptyList()
        ),

        // YEAR IV: Clinical Internship & Senior Professional Practice
        CurriculumModule(
            id = "mod_crisis_intensive_care",
            name = "Crisis Management, LAST & Critical Care",
            moduleCode = null,
            ects = null,
            durationWeeks = 12,
            academicYear = AcademicYear.YEAR_4,
            semesterOrBlock = "Year IV",
            category = CurriculumCategory.CRISIS_AND_ADVANCED,
            description = "Recognition and rapid management of perioperative emergencies: Local Anesthetic Systemic Toxicity (LAST), Malignant Hyperthermia (MH), and cardiac arrest.",
            learningOutcomes = listOf(
                "Execute the 20% Intravenous Lipid Emulsion protocol for acute bupivacaine toxicity.",
                "Identify early signs of Malignant Hyperthermia and administer Dantrolene according to protocol.",
                "Lead perioperative ACLS resuscitation and crisis team communication."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = listOf("lesson_malignant_hyperthermia_crisis", "lesson_last_toxicity_protocol")
        ),
        CurriculumModule(
            id = "mod_clinical_internship",
            name = "Comprehensive Clinical Anesthesia Internship",
            moduleCode = null,
            ects = null,
            durationWeeks = 24,
            academicYear = AcademicYear.YEAR_4,
            semesterOrBlock = "Year IV",
            category = CurriculumCategory.SPECIALTY_ANESTHESIA,
            description = "Final-year rotational internship across general, obstetric, orthopedic, and emergency surgical theaters in Ethiopian tertiary and district hospitals.",
            learningOutcomes = listOf(
                "Independently conduct comprehensive pre-anesthetic evaluation and risk stratification.",
                "Safely deliver balanced general and regional anesthesia across diverse surgical cases.",
                "Demonstrate professional ethics, vigil perioperative monitoring, and calm crisis response."
            ),
            mappingStatus = CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING,
            lessonIds = emptyList()
        )
    )

    val lessons: List<Lesson> = listOf(
        Lesson(
            id = "lesson_airway_assessment",
            title = "Comprehensive Airway Assessment & Mallampati Scoring",
            category = CurriculumCategory.ANESTHESIA_FOUNDATIONS,
            estimatedMinutes = 15,
            pathwayApplicability = "Ethiopian BSc Anesthesia & Clinical Practice",
            learningObjectives = listOf(
                "Perform systematic LEMON physical airway examination.",
                "Accurately assign modified Mallampati Class I to IV in sitting patients.",
                "Measure thyromental distance and evaluate mandibular protrusion.",
                "Identify multi-factor predictors of difficult face-mask ventilation and direct laryngoscopy."
            ),
            prerequisites = listOf("Head and neck anatomy", "Cervical spine kinematics"),
            beginnerExplanation = "Airway management is the cornerstone of safe anesthesia. Before administering general anesthesia, the practitioner must evaluate whether the patient can be ventilated with a bag-mask, whether a supraglottic airway can seat correctly, and whether endotracheal intubation will be straightforward or require advanced equipment.",
            physiologicalMechanisms = "The airway consists of the upper airway (nares, pharynx, larynx) and tracheobronchial tree. During induction of general anesthesia, loss of pharyngeal muscle tone causes the tongue and soft palate to collapse against the posterior pharyngeal wall. In patients with high BMI, short thyromental distance (< 6 cm), or restricted cervical spine extension, aligning the oral, pharyngeal, and laryngeal axes ('sniffing position') becomes physically difficult.",
            clinicalApplications = "Systematic LEMON Assessment:\n" +
                    "• L - Look externally (micrognathia, large tongue, prominent incisors, morbid obesity, facial trauma).\n" +
                    "• E - Evaluate 3-3-2 Rule:\n" +
                    "   - 3 finger breadths inter-incisor opening (> 4 cm)\n" +
                    "   - 3 finger breadths hyoid-mental distance\n" +
                    "   - 2 finger breadths thyroid notch to floor of mouth.\n" +
                    "• M - Mallampati Class (sitting, mouth wide open, tongue protruded without phonation):\n" +
                    "   - Class I: Soft palate, fauces, uvula, and pillars visible\n" +
                    "   - Class II: Soft palate, fauces, and uvula visible\n" +
                    "   - Class III: Soft palate and base of uvula visible\n" +
                    "   - Class IV: Only hard palate visible\n" +
                    "• O - Obstruction (epiglottitis, peritonsillar abscess, hematoma, stridor).\n" +
                    "• N - Neck mobility (normal flexion/extension is 35° or greater).",
            commonErrors = listOf(
                "Asking the patient to say 'Ahhh' during Mallampati assessment, which falsely arches the soft palate and elevates the score.",
                "Failing to assess predictors of difficult mask ventilation (MOANS: Mask seal, Obesity, Age > 55, No teeth, Stiff lungs/Stridor).",
                "Neglecting to check dental crowns and loose teeth prior to laryngoscopy."
            ),
            highYieldExamPoints = listOf(
                "Mallampati Class III and IV correlate with Cormack-Lehane Grade 3 and 4 views during direct laryngoscopy.",
                "Thyromental distance < 6 cm (less than 3 finger-breadths) indicates an acute angle between oral and pharyngeal axes.",
                "Severe micrognathia or ankylosing spondylitis mandates video laryngoscopy or awake fiberoptic setup before induction."
            ),
            clinicalCaseStudy = "A 48-year-old male presenting for emergency laparotomy in a regional hospital has BMI 38, beard, Mallampati Class III, and thyromental distance of 5.5 cm. The team prepares a two-person mask ventilation setup, video laryngoscope, and bougie before rapid sequence induction.",
            references = listOf(
                "Ethiopian National Harmonized Modular Curriculum for BSc in Anesthesia (Feb 2021).",
                "ASA Difficult Airway Algorithm 2022 Guidelines.",
                "Morgan & Mikhail's Clinical Anesthesiology, 7th Edition."
            ),
            academicYear = AcademicYear.YEAR_2,
            moduleId = "mod_airway_respiratory",
            knowledgeCheckQuestion = ReviewQuestion(
                id = "kc_airway_1",
                question = "During airway examination, a patient sitting upright with mouth opened and tongue protruded reveals only the soft palate and base of the uvula. What is the correct Mallampati Class?",
                options = listOf("Class I", "Class II", "Class III", "Class IV"),
                correctIndex = 2,
                explanation = "In Mallampati Class III, the soft palate and the base of the uvula are visible, but the rest of the uvula and tonsillar pillars are obscured. This strongly correlates with a Cormack-Lehane Grade 3 or 4 view on direct laryngoscopy.",
                examHighYieldTag = "BSc Core Exam"
            )
        ),
        Lesson(
            id = "lesson_spinal_anesthesia_physiology",
            title = "Applied Physiology of Neuraxial & Spinal Anesthesia",
            category = CurriculumCategory.APPLIED_PHYSIOLOGY,
            estimatedMinutes = 20,
            pathwayApplicability = "Ethiopian BSc Anesthesia & Clinical Practice",
            learningObjectives = listOf(
                "Trace anatomical layers penetrated during lumbar subarachnoid puncture.",
                "Differentiate differential nerve blockade (sympathetic vs sensory vs motor).",
                "Explain the cardiovascular mechanisms of post-spinal hypotension and bradycardia.",
                "Detail respiratory, gastrointestinal, and renal consequences of high spinal blockade."
            ),
            prerequisites = listOf("Vertebral column anatomy", "Autonomic nervous system physiology"),
            beginnerExplanation = "Spinal anesthesia involves injecting small volumes of local anesthetic into the cerebrospinal fluid (CSF) within the subarachnoid space (lumbar cistern). This temporarily blocks nerve conduction at the spinal nerve roots and cord, producing dense surgical sensory and motor block below the level of injection.",
            physiologicalMechanisms = "Nerve fibers have variable susceptibility to local anesthetic blockade based on diameter and myelination:\n" +
                    "1. B-fibers (preganglionic sympathetic fibers): most sensitive. Sympathetic block typically extends 2 to 4 dermatomes ABOVE the sensory block level.\n" +
                    "2. A-delta and C-fibers (pain and temperature): intermediate sensitivity (sensory block level).\n" +
                    "3. A-alpha and A-beta fibers (motor and proprioception): blocked last and typically 2 dermatomes BELOW sensory level.",
            clinicalApplications = "Cardiovascular Effects:\n" +
                    "• Venodilation: Sympathectomy causes peripheral pooling in venous capacitance vessels, reducing venous return and cardiac preload (decreased stroke volume and cardiac output).\n" +
                    "• Arterial vasodilation: Reduces systemic vascular resistance (SVR).\n" +
                    "• Cardioaccelerator fibers (T1-T4): If block reaches T4 or higher, sympathetic cardiac innervation is lost, leading to severe bradycardia and Bezold-Jarisch reflex.\n" +
                    "• Prevention & Treatment: Left uterine displacement in obstetrics, judicious crystalloid/colloid co-loading, prompt titration of alpha-1 agonists (Phenylephrine) and mixed agents (Ephedrine).",
            commonErrors = listOf(
                "Assuming sensory level equals sympathetic blockade level (sympathetic block is 2-4 segments higher).",
                "Injecting above L2 in adults, risking direct needle trauma to the conus medullaris.",
                "Administering large fluid boluses without active vasopressor prophylaxis in elective cesarean sections."
            ),
            highYieldExamPoints = listOf(
                "Adult spinal cord terminates at L1-L2 (conus medullaris), whereas dural sac ends at S2.",
                "In infants, spinal cord terminates at L3 and dural sac at S3.",
                "Anatomic landmarks: Tuffier's line connects superior iliac crests and crosses L4 vertebral body or L4-L5 interspace."
            ),
            clinicalCaseStudy = "5 minutes following subarachnoid injection of 12 mg hyperbaric bupivacaine for C-section, the patient feels nauseated and dizzy. Blood pressure drops from 122/75 to 78/42 mmHg, HR 58 bpm. Prompt left uterine displacement, IV phenylephrine 100 mcg bolus, and rapid co-load restore hemodynamics immediately.",
            references = listOf(
                "Ethiopian National Harmonized Modular Curriculum for BSc in Anesthesia (Feb 2021).",
                "Miller's Anesthesia, 9th Edition, Chapter on Spinal and Epidural Anesthesia.",
                "Barash's Clinical Anesthesia, Chapter on Autonomic Nervous System."
            ),
            academicYear = AcademicYear.YEAR_2,
            moduleId = "mod_anatomy_physiology",
            knowledgeCheckQuestion = ReviewQuestion(
                id = "kc_spinal_1",
                question = "Following subarachnoid injection of hyperbaric bupivacaine, where does the level of sympathetic block typically settle relative to the tested sensory loss level?",
                options = listOf(
                    "2 to 4 dermatomes ABOVE the sensory block",
                    "At the exact same dermatome level",
                    "2 to 4 dermatomes BELOW the sensory block",
                    "Only at the sacral dermatomes S1-S5"
                ),
                correctIndex = 0,
                explanation = "Small preganglionic sympathetic B-fibers are far more sensitive to local anesthetic blockade than sensory A-delta/C fibers; hence, sympathetic blockade typically extends 2 to 4 spinal dermatomes cephalad to the sensory pinprick level.",
                examHighYieldTag = "BSc Core Exam"
            )
        ),
        Lesson(
            id = "lesson_general_anesthesia_stages",
            title = "Principles of General Anesthesia & Inhalation Pharmacokinetics",
            category = CurriculumCategory.ANESTHESIA_FOUNDATIONS,
            estimatedMinutes = 18,
            pathwayApplicability = "Ethiopian BSc Anesthesia & Clinical Practice",
            learningObjectives = listOf(
                "Define the tri-part triad of general anesthesia: hypnosis, analgesia, and muscle relaxation.",
                "Describe Guedel's classical stages of ether anesthesia and modern analogs.",
                "Explain Minimum Alveolar Concentration (MAC) and factors altering it.",
                "Understand the blood-gas partition coefficient and speed of induction/emergence."
            ),
            prerequisites = listOf("Basic respiratory physiology", "Pharmacokinetics principles"),
            beginnerExplanation = "General anesthesia is a reversible drug-induced state comprising unconsciousness, amnesia, analgesia, attenuation of autonomic reflexes, and skeletal muscle relaxation. Modern balanced anesthesia utilizes specific combinations of intravenous and volatile agents rather than relying on high doses of a single drug.",
            physiologicalMechanisms = "Inhalation Anesthetics Pharmacokinetics (FA/FI Ratio):\n" +
                    "The rate of induction depends on the rate at which alveolar partial pressure (FA) approaches inspired partial pressure (FI). This equilibrium is governed by:\n" +
                    "1. Blood:Gas Partition Coefficient: Lower solubility (e.g. Desflurane 0.42, Sevoflurane 0.65) equals rapid rise in FA/FI and faster induction/emergence. Higher solubility (e.g. Isoflurane 1.4) yields slower kinetics.\n" +
                    "2. Cardiac Output: High cardiac output removes more anesthetic from alveoli, slowing the rise of FA/FI.\n" +
                    "3. Alveolar Ventilation: Increased minute ventilation accelerates the rise of FA/FI.",
            clinicalApplications = "MAC (Minimum Alveolar Concentration):\n" +
                    "The alveolar concentration of an inhalational agent at 1 atmosphere that prevents purposeful movement in 50% of patients in response to a standard surgical incision.\n" +
                    "• Sevoflurane: 2.05%\n" +
                    "• Isoflurane: 1.15%\n" +
                    "• Desflurane: 6.0%\n" +
                    "• MAC-awake: ~0.3-0.4 MAC\n" +
                    "• MAC-BAR (blunts autonomic response): ~1.3-1.5 MAC\n" +
                    "Factors decreasing MAC: hypothermia, acute alcohol ingestion, hyponatremia, older age, pregnancy, opioids, alpha-2 agonists.\n" +
                    "Factors increasing MAC: hyperthermia, chronic alcoholism, hypernatremia, young children, amphetamine toxicity.",
            commonErrors = listOf(
                "Failing to adjust volatile agent concentration for elderly patients who have reduced MAC requirements.",
                "Overventilating during emergence with insoluble agents without monitoring end-tidal concentration."
            ),
            highYieldExamPoints = listOf(
                "Desflurane has the lowest blood:gas partition coefficient (0.42) among volatile agents.",
                "Sevoflurane reacts with desiccated barium hydroxide lime to form Compound A (nephrotoxic in rats); fresh gas flow should be >= 1 L/min.",
                "Desflurane causes airway irritation and sympathetic stimulation (tachycardia, hypertension) when rapidly titrated."
            ),
            clinicalCaseStudy = "An 82-year-old female undergoes hip replacement in an Ethiopian referral hospital. The anesthetist titrates isoflurane to 0.8% combined with multimodal analgesia, avoiding delayed emergence in this geriatric patient.",
            references = listOf(
                "Ethiopian National Harmonized Modular Curriculum for BSc in Anesthesia (Feb 2021).",
                "Eger EI 2nd. The Pharmacology of Inhaled Anesthetics.",
                "Stoelting's Pharmacology & Physiology in Anesthetic Practice."
            ),
            academicYear = AcademicYear.YEAR_2,
            moduleId = "mod_principles_anesthesia",
            knowledgeCheckQuestion = ReviewQuestion(
                id = "kc_ga_1",
                question = "Which of the following volatile agents has the lowest blood:gas partition coefficient, resulting in the fastest alveolar rise (FA/FI) and most rapid emergence?",
                options = listOf("Halothane (2.5)", "Isoflurane (1.4)", "Sevoflurane (0.65)", "Desflurane (0.42)"),
                correctIndex = 3,
                explanation = "Desflurane has a blood:gas partition coefficient of 0.42, which is the lowest among modern volatile anesthetics. Minimal blood solubility ensures that alveolar and brain partial pressures equilibrate rapidly.",
                examHighYieldTag = "BSc Core Exam"
            )
        ),
        Lesson(
            id = "lesson_malignant_hyperthermia_crisis",
            title = "Malignant Hyperthermia: Pathophysiology & Crisis Protocol",
            category = CurriculumCategory.CRISIS_AND_ADVANCED,
            estimatedMinutes = 20,
            pathwayApplicability = "Ethiopian BSc Anesthesia & Clinical Practice",
            learningObjectives = listOf(
                "Identify genetic mutations (RYR1, CACNA1S) predisposing to MH.",
                "Distinguish early signs (unexplained hypercarbia, masseter spasm, tachycardia) from late signs (hyperthermia, myoglobinuria).",
                "Execute the complete step-by-step acute crisis management algorithm.",
                "State Dantrolene reconstitution, loading dose, and clinical mechanism."
            ),
            prerequisites = listOf("Skeletal muscle excitation-contraction coupling", "Anesthetic machine decontamination"),
            beginnerExplanation = "Malignant Hyperthermia (MH) is a life-threatening pharmacogenetic disorder of skeletal muscle calcium regulation. Exposure to triggering agents (volatile halogenated anesthetics and succinylcholine) unleashes uncontrolled intracellular calcium release from the sarcoplasmic reticulum, causing an acute hypermetabolic crisis.",
            physiologicalMechanisms = "Mutations in the Ryanodine receptor (RYR1 gene on chromosome 19q13.1) or dihydropyridine receptor cause loss of negative feedback. Unchecked Ca²⁺ entry into the myoplasm drives hyper-activation of actin-myosin cross-bridging and aerobic/anaerobic glycolysis. Massive ATP consumption creates profound hypercarbia, lactic acidosis, rigid contractures, cell membrane breakdown (rhabdomyolysis), and uncontrolled heat production.",
            clinicalApplications = "Step-by-Step Crisis Protocol:\n" +
                    "1. STOP triggering agents immediately. Turn off volatile vaporizers and remove them from the circuit.\n" +
                    "2. CALL FOR HELP and state: 'Malignant Hyperthermia Crisis! Bring the MH Cart and Dantrolene.'\n" +
                    "3. HYPERVENTILATE with 100% Oxygen at maximal fresh gas flow (>= 10 L/min) with 2-3 times normal minute ventilation to clear CO2.\n" +
                    "4. DANTROLENE: Administer initial IV bolus of 2.5 mg/kg rapidly. Repeat every 5-10 minutes (up to 10 mg/kg) until hypercarbia and rigidity abate.\n" +
                    "   • Ryanodex: 250 mg/vial reconstituted in 5 mL sterile water (single vial).\n" +
                    "   • Classic Dantrolene: 20 mg/vial with 3 grams mannitol, reconstituted in 60 mL sterile water per vial (35-40 vials needed for adult!).\n" +
                    "5. COOL the patient if core temperature > 38.5°C (iced saline IV, surface packs; stop cooling at 38°C to prevent overshoot hypothermia).\n" +
                    "6. TREAT HYPERKALEMIA: IV Calcium chloride (10 mg/kg), Regular Insulin (10 units) with Dextrose 50% (50 mL), Sodium bicarbonate 1-2 mEq/kg.\n" +
                    "7. DIURESIS: Maintain urine output > 2 mL/kg/hr with fluids, mannitol, or furosemide to protect kidneys from myoglobin precipitation.",
            commonErrors = listOf(
                "Waiting for fever/hyperthermia before suspecting MH. Hyperthermia is a LATE sign; early signs are rising EtCO2 refractory to hyperventilation and sinus tachycardia.",
                "Administering calcium channel blockers (e.g. Verapamil, Diltiazem) with Dantrolene, which can trigger fatal hyperkalemia and myocardial collapse."
            ),
            highYieldExamPoints = listOf(
                "Most sensitive and earliest sign of MH under general anesthesia is an unexplained, dramatic elevation in End-Tidal CO2.",
                "Dantrolene acts directly on Ryanodine Receptor 1 (RYR1) to inhibit sarcoplasmic calcium release.",
                "Gold standard diagnostic test is the Caffeine-Halothane Contracture Test (CHCT) performed on fresh muscle biopsy."
            ),
            clinicalCaseStudy = "During an appendectomy in an 18-year-old male under halothane and succinylcholine, EtCO2 climbs rapidly from 38 to 76 mmHg despite doubling minute ventilation. Heart rate is 142 bpm and core temp rises to 39.2°C. Vaporizer is immediately removed, charcoal filters placed, 100% O2 at 15 L/min delivered, and Dantrolene 2.5 mg/kg bolused. Within 12 minutes, EtCO2 normalizes.",
            references = listOf(
                "Ethiopian National Harmonized Modular Curriculum for BSc in Anesthesia (Feb 2021).",
                "MHAUS Emergency Protocol.",
                "Association of Anaesthetists Safety Guideline: Malignant Hyperthermia Crisis."
            ),
            academicYear = AcademicYear.YEAR_4,
            moduleId = "mod_crisis_intensive_care",
            knowledgeCheckQuestion = ReviewQuestion(
                id = "kc_mh_1",
                question = "What is the earliest and most sensitive clinical indicator of Malignant Hyperthermia under general anesthesia with endotracheal intubation?",
                options = listOf(
                    "Dramatic elevation in End-Tidal CO2 refractory to hyperventilation",
                    "Rapid core body temperature rise above 40°C",
                    "Grossly dark port-wine myoglobinuria",
                    "Sudden flaccid quadriplegia"
                ),
                correctIndex = 0,
                explanation = "Uncontrolled aerobic and anaerobic muscle metabolism produces enormous amounts of carbon dioxide. An abrupt, unexplained doubling or tripling of EtCO2 unresponsive to hyperventilation is the earliest, most sensitive sign of MH.",
                examHighYieldTag = "BSc Core Exam"
            )
        ),
        Lesson(
            id = "lesson_last_toxicity_protocol",
            title = "Local Anesthetic Systemic Toxicity (LAST) & Lipid Emulsion Rescue",
            category = CurriculumCategory.CRISIS_AND_ADVANCED,
            estimatedMinutes = 18,
            pathwayApplicability = "Ethiopian BSc Anesthesia & Clinical Practice",
            learningObjectives = listOf(
                "Recognize early central nervous system symptoms versus cardiovascular collapse in LAST.",
                "Understand the cardiac electrophysiology of bupivacaine toxicity.",
                "Formulate safe maximum dose limits for local anesthetics.",
                "Execute the 20% Intravenous Lipid Emulsion (ILE) rescue protocol."
            ),
            prerequisites = listOf("Local anesthetic pharmacology", "Cardiac conduction electrophysiology"),
            beginnerExplanation = "Local Anesthetic Systemic Toxicity (LAST) is a life-threatening systemic complication occurring from unintended intravascular injection or rapid systemic absorption of high doses of local anesthetics, notably potent lipophilic agents such as bupivacaine.",
            physiologicalMechanisms = "Local anesthetics block voltage-gated sodium channels in neurons and cardiac myocytes. At toxic systemic levels, they inhibit cardiac conduction, prolong the PR interval and QRS complex, impair myocardial contractility, and precipitate refractory ventricular arrhythmias and asystole. Bupivacaine binds avidly to cardiac sodium channels and dissociates slowly during diastole ('fast-in, slow-out' kinetics), making bupivacaine cardiotoxicity especially intractable.",
            clinicalApplications = "Clinical Progression:\n" +
                    "• CNS Excitation: Perioral numbness, metallic taste, tinnitus, lightheadedness, visual disturbances, tremors, followed by generalized tonic-clonic seizures.\n" +
                    "• CNS Depression: Coma, respiratory depression, apnea.\n" +
                    "• Cardiovascular: Hypertension & tachycardia early, progressing rapidly to conduction block, profound bradycardia, ventricular tachycardia/fibrillation, and asystole.\n\n" +
                    "ASRA LAST Treatment Protocol:\n" +
                    "1. STOP local anesthetic injection immediately and call for help + LAST rescue kit.\n" +
                    "2. AIRWAY & OXYGEN: Ventilate with 100% O2; avoid hypercarbia and acidosis (which worsen toxicity by increasing free drug fraction).\n" +
                    "3. SUPPRESS SEIZURES: Benzodiazepines (e.g. Midazolam 1-2 mg IV) preferred. Avoid propofol in hemodynamically unstable patients.\n" +
                    "4. 20% LIPID EMULSION THERAPY:\n" +
                    "   • Initial Bolus: 1.5 mL/kg IV over 2-3 minutes (~100 mL for 70 kg adult).\n" +
                    "   • Continuous Infusion: 0.25 mL/kg/min (~18 mL/min for 70 kg).\n" +
                    "   • Repeat bolus once or twice for persistent cardiovascular collapse; double infusion rate to 0.5 mL/kg/min.\n" +
                    "   • Continue infusion for at least 10 minutes after hemodynamic stability is restored (Max total dose: ~12 mL/kg in first hour).\n" +
                    "5. RESUSCITATION MODIFICATIONS: Reduce epinephrine doses to <= 1 mcg/kg (small boluses of 10-50 mcg) to avoid aggravating arrhythmias; AVOID vasopressin, beta-blockers, and calcium channel blockers.",
            commonErrors = listOf(
                "Administering large 1 mg epinephrine boluses as per standard ACLS; high-dose epinephrine impairs lipid rescue and triggers ventricular fibrillation in LAST.",
                "Administering local anesthetics without regular incremental aspiration for blood.",
                "Exceeding maximum calculated weight-based dosage limits."
            ),
            highYieldExamPoints = listOf(
                "Bupivacaine has high cardiotoxicity because it dissociates slowly from inactivated cardiac sodium channels.",
                "Epinephrine 1:200,000 (5 mcg/mL) in test doses serves as a vascular marker: a heart rate increase > 10 bpm or systolic BP rise > 15 mmHg indicates intravascular needle placement.",
                "Intralipid functions via 'lipid sink' (partitioning lipophilic drug into intravascular lipid droplets) and metabolic enhancement of myocardial fatty acid oxidation."
            ),
            clinicalCaseStudy = "During ultrasound-guided supraclavicular brachial plexus block with 25 mL 0.5% bupivacaine, a 34-year-old patient reports ringing in ears and metallic taste, followed immediately by tonic-clonic seizure and wide-complex ventricular tachycardia. 100% O2 initiated, midazolam 2 mg given, and 100 mL of 20% Lipid Emulsion bolused with continuous infusion. Normal sinus rhythm restored within 4 minutes.",
            references = listOf(
                "Ethiopian National Harmonized Modular Curriculum for BSc in Anesthesia (Feb 2021).",
                "ASRA LAST Checklist 2020.",
                "Association of Anaesthetists of Great Britain and Ireland (AAGBI) Guidelines."
            ),
            academicYear = AcademicYear.YEAR_4,
            moduleId = "mod_crisis_intensive_care",
            knowledgeCheckQuestion = ReviewQuestion(
                id = "kc_last_1",
                question = "What is the recommended initial IV bolus dose of 20% Lipid Emulsion for an adult experiencing cardiovascular collapse from bupivacaine toxicity?",
                options = listOf(
                    "1.5 mL/kg IV over 2 to 3 minutes",
                    "0.1 mL/kg IV push",
                    "10 mL/kg IV bolus",
                    "500 mL rapid pressure infusion"
                ),
                correctIndex = 0,
                explanation = "The standard ASRA and international guideline for Local Anesthetic Systemic Toxicity is an initial 20% Lipid Emulsion bolus of 1.5 mL/kg over 2 to 3 minutes (~100 mL for a 70 kg adult), followed by an infusion of 0.25 mL/kg/min.",
                examHighYieldTag = "BSc Core Exam"
            )
        )
    )

    fun getModulesByYear(year: AcademicYear): List<CurriculumModule> =
        modules.filter { it.academicYear == year }

    fun getLessonsByModule(moduleId: String): List<Lesson> =
        lessons.filter { it.moduleId == moduleId }

    fun getLessonsByYear(year: AcademicYear): List<Lesson> =
        lessons.filter { it.academicYear == year }
}
