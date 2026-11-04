package com.example.data.repository

import com.example.data.model.Drug
import com.example.data.model.DrugCategory
import com.example.data.model.DrugDosing
import com.example.data.model.DrugReference

object EmergencyObstetricAndFluidsData {

    val drugs: List<Drug> = listOf(
        // EMERGENCY DRUGS
        Drug(
            id = "drug_epinephrine",
            genericName = "Epinephrine (Adrenaline)",
            brandNames = "Adrenalin, EpiPen",
            synonyms = listOf("Adrenaline", "Epi", "Adrenalin"),
            category = DrugCategory.EMERGENCY_DRUGS,
            drugClass = "Potent Direct Endogenous Sympathomimetic Catecholamine",
            mechanismOfAction = "Direct, non-selective agonist at alpha-1, alpha-2, beta-1, and beta-2 adrenergic receptors. Stimulates myocardial contractility (beta-1 inotropy), heart rate (beta-1 chronotropy), systemic vasoconstriction (alpha-1), bronchial smooth muscle relaxation (beta-2), and stabilizes mast cell membranes (inhibits mediator release).",
            targetReceptor = "Alpha-1, Alpha-2, Beta-1, Beta-2 adrenergic receptors",
            indications = listOf(
                "Cardiac arrest (VF, pulseless VT, Asystole, PEA - ACLS gold standard)",
                "Severe life-threatening anaphylaxis and anaphylactoid shock",
                "Severe bronchospasm / refractory status asthmaticus",
                "Severe cardiogenic or septic shock refractory to norepinephrine",
                "Local anesthetic vasoconstrictor additive (1:200,000 = 5 mcg/mL)"
            ),
            onsetAndDuration = "Onset: Immediate IV; 3 - 5 minutes IM; Duration: 5 - 10 minutes",
            adultDosing = "Cardiac Arrest (ACLS): 1.0 mg IV (1:10,000 = 0.1 mg/mL) every 3-5 minutes\nAnaphylaxis: 0.3 - 0.5 mg IM (1:1,000 = 1 mg/mL) in mid-anterolateral thigh; OR titrated IV boluses of 10 - 50 mcg (0.1 - 0.5 mL of 1:10,000 diluted in 10 mL) in anesthetized patients\nContinuous Infusion: 0.01 - 0.2 mcg/kg/min IV (titrated to hemodynamics)",
            pediatricDosing = "Cardiac Arrest: 0.01 mg/kg IV (0.1 mL/kg of 1:10,000) every 3-5 minutes; Anaphylaxis: 0.01 mg/kg IM (1:1,000; Max 0.3-0.5 mg)",
            obstetricDosing = "Safe in cardiac arrest and anaphylaxis; in pregnancy, causes uterine vasoconstriction, but maternal resuscitation always takes priority",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus (ACLS)",
                    indication = "Cardiac Arrest (VF/VT/PEA/Asystole)",
                    adultDose = "1.0 mg IV push (10 mL of 1:10,000 solution) every 3-5 minutes with 20 mL flush",
                    pediatricDose = "0.01 mg/kg IV (0.1 mL/kg of 1:10,000)",
                    unitNotes = "Verify concentration! 1:10,000 = 0.1 mg/mL = 100 mcg/mL. 1:1,000 = 1.0 mg/mL (IM use ONLY, fatal if injected rapid IV without dilution!)"
                ),
                DrugDosing(
                    route = "IM Injection",
                    indication = "Acute Severe Anaphylaxis",
                    adultDose = "0.3 - 0.5 mg IM (0.3 - 0.5 mL of 1:1,000) into mid-outer thigh (vastus lateralis)",
                    pediatricDose = "0.01 mg/kg IM (Max 0.3 mg in child; 0.5 mg in adolescent)"
                ),
                DrugDosing(
                    route = "IV Push Dose (OR Anaphylaxis)",
                    indication = "Severe Anaphylactic Collapse under General Anesthesia",
                    adultDose = "10 - 50 mcg IV boluses titrated every 1-2 min (prepared by taking 1 mL of 1:10,000 into 9 mL NS = 10 mcg/mL)"
                )
            ),
            formulationsAndRoutes = listOf("1:1,000 (1 mg/mL) ampules for IM injection", "1:10,000 (0.1 mg/mL = 100 mcg/mL in 10 mL pre-filled emergency syringes) for IV ACLS"),
            pharmacokinetics = "Rapidly cleared by neuronal uptake and metabolized by COMT and MAO in the liver and sympathetic terminals. Half-life < 1-2 minutes.",
            onset = "Immediate IV (3 - 5 min IM)",
            peakEffect = "1 - 2 minutes",
            duration = "5 - 10 minutes",
            elimination = "Neuronal uptake; COMT and MAO degradation",
            contraindications = listOf("Zero absolute contraindications in cardiac arrest or anaphylactic shock!"),
            cautions = listOf(
                "Fatal concentration errors: confusing 1:1,000 (1 mg/mL) with 1:10,000 (0.1 mg/mL); never give 1:1,000 IV undiluted",
                "Ventricular arrhythmias, tachycardia, and severe hypertension",
                "Severe myocardial ischemia / infarction in patients with underlying CAD"
            ),
            adverseReactions = listOf("Ventricular tachycardia / VF", "Severe hypertension", "Myocardial ischemia", "Pulmonary edema", "Tremor / anxiety"),
            interactions = listOf("Halogenated volatile anesthetics sensitize the myocardium to epinephrine arrhythmogenicity (safe limit with sevoflurane: 5 mcg/kg)"),
            renalAdjustment = "Safe; no adjustment in arrest/shock.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Maternal resuscitation is neonatal resuscitation: use without hesitation in maternal arrest or anaphylaxis.",
            elderlyConsiderations = "High risk of ischemia/arrhythmias; in non-arrest settings, titrate small 10-20 mcg IV doses.",
            anesthesiaConsiderations = "The undisputed foundation of anaphylaxis and cardiac arrest management. Stabilizes mast cells, restores vascular tone (alpha-1), dilates bronchi (beta-2), and supports heart (beta-1).",
            reversalAgent = "Short-acting beta-blockers (Esmolol) or vasodilators (Nitroglycerin, Phentolamine).",
            toxicityManagement = "Treat severe hypertension/tachyarrhythmias with Esmolol (10-30 mg IV) or Phentolamine 5 mg IV. Treat extravasation with local Phentolamine infiltration.",
            clinicalPearls = "Gold standard for anaphylaxis and ACLS. Never confuse 1:1,000 (IM only) with 1:10,000 (IV ACLS). For OR anaphylaxis, dilute to 10 mcg/mL and titrate 1-2 mL.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("AHA ACLS Guidelines", "2020/2023", "Circulation"),
                DrugReference("World Allergy Organization Anaphylaxis Guidelines", "2020", "WAO Journal"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "Adrenergic Receptor Pleiotropy (Alpha-1, Beta-1, Beta-2)",
            diagramType = "ADRENERGIC_ALPHA_BETA",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_amiodarone",
            genericName = "Amiodarone Hydrochloride",
            brandNames = "Cordarone, Pacerone",
            synonyms = listOf("Cordarone", "Nexterone"),
            category = DrugCategory.EMERGENCY_DRUGS,
            drugClass = "Class III Antiarrhythmic with Class I, II, and IV Properties",
            mechanismOfAction = "Multi-channel antiarrhythmic. Predominantly blocks voltage-gated potassium channels (Class III), prolonging phase 3 repolarization and action potential duration in cardiac tissue. Also blocks inactivated sodium channels (Class Ib), non-competitively antagonizes alpha and beta receptors (Class II), and blocks L-type calcium channels (Class IV).",
            targetReceptor = "Potassium channels, Sodium channels, Calcium channels, Beta receptors",
            indications = listOf(
                "Cardiac arrest: Pulseless Ventricular Tachycardia (pVT) and Ventricular Fibrillation (VF) refractory to defibrillation and epinephrine (ACLS first-line)",
                "Hemodynamically stable wide-complex ventricular tachycardia",
                "Rate control and cardioversion of acute atrial fibrillation with rapid ventricular response"
            ),
            onsetAndDuration = "Onset: Minutes IV for electrophysiologic effect; Peak: Variable; Duration: Long elimination half-life (up to 40 - 55 days due to massive adipose accumulation)",
            adultDosing = "Cardiac Arrest (Refractory VF/pVT): 300 mg IV push after 3rd shock; repeat 150 mg IV push after 5th shock\nStable VT / AFib: 150 mg IV infused over 10 minutes, followed by 1.0 mg/min for 6 hours (360 mg), then 0.5 mg/min for 18 hours (540 mg; Max 2.2 g/24 hr)",
            pediatricDosing = "Cardiac Arrest (PALS): 5.0 mg/kg IV push (Max: 300 mg); repeat up to 2 times for refractory VF/pVT",
            obstetricDosing = "Reserved for maternal refractory life-threatening ventricular arrhythmias; crosses placenta and can cause fetal hypothyroidism",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Push (ACLS)",
                    indication = "Refractory VF / Pulseless VT Cardiac Arrest",
                    adultDose = "300 mg IV rapid push diluted in 20 mL D5W after 3rd defibrillation; repeat 150 mg IV if VF persists",
                    pediatricDose = "5.0 mg/kg IV push (Max 300 mg)",
                    unitNotes = "Give rapid push in cardiac arrest. In non-arrest situations, MUST BE INFUSED SLOWLY (over 10 min) to avoid profound hypotension!"
                ),
                DrugDosing(
                    route = "IV Infusion",
                    indication = "Stable Monomorphic VT / Atrial Fibrillation",
                    adultDose = "150 mg IV over 10 minutes, then 1 mg/min x 6 hrs, then 0.5 mg/min x 18 hrs"
                )
            ),
            formulationsAndRoutes = listOf("50 mg/mL (3 mL ampules = 150 mg); Nexterone ready-to-use infusion bags (150 mg/100 mL, 360 mg/200 mL in D5W)"),
            pharmacokinetics = "Extreme lipophilicity (Vd 60 L/kg). Hepatic metabolism via CYP3A4 and CYP2C8 to active metabolite desethylamiodarone (DEA). Elimination half-life is 40 - 55 days.",
            onset = "Immediate IV in arrest (electrophysiologic effect)",
            peakEffect = "15 - 30 minutes",
            duration = "Days to weeks (terminal half-life 40-55 days)",
            elimination = "Hepatic metabolism; biliary and fecal excretion (< 1% renal)",
            contraindications = listOf(
                "Severe sinus node dysfunction causing severe sinus bradycardia (unless pacemaker present)",
                "Second or third-degree AV block (unless pacemaker present)",
                "Known iodine hypersensitivity (molecule contains ~37% iodine by weight)"
            ),
            cautions = listOf(
                "Profound hypotension and bradycardia when infused too rapidly in non-arrest patients (due to polysorbate 80 vehicle and alpha/calcium blockade)",
                "QT prolongation and Torsades de Pointes (lower incidence than other class III agents, but still significant)",
                "Pulmonary toxicity (pulmonary fibrosis) with chronic use; high intraoperative FiO2 may trigger acute lung injury",
                "Thyroid dysfunction (both hypo- and hyperthyroidism due to high iodine content)"
            ),
            adverseReactions = listOf("Hypotension (especially with rapid infusion)", "Bradycardia / AV block", "QT prolongation", "Phlebitis in peripheral lines", "Pulmonary fibrosis (chronic)"),
            interactions = listOf("Inhibits CYP2C9, CYP3A4, and P-glycoprotein: doubles serum digoxin levels and significantly increases warfarin effect"),
            renalAdjustment = "Safe in renal disease (< 1% renal excretion); zero adjustment needed.",
            hepaticAdjustment = "Cleared hepatically; use caution with prolonged maintenance infusions.",
            pregnancyAndLactation = "Crosses placenta; risk of fetal bradycardia, goiter, and hypothyroidism; use only for life-threatening maternal arrhythmias.",
            elderlyConsiderations = "Higher risk of bradycardia and conduction abnormalities; monitor ECG.",
            anesthesiaConsiderations = "First-line antiarrhythmic for shock-refractory VF/VT in ACLS. For stable patients, always infuse slowly over 10 minutes and use central line when possible to prevent severe phlebitis.",
            reversalAgent = "No specific reversal. Treat bradycardia with pacing or Isoproterenol; treat hypotension with vasopressors and fluids.",
            toxicityManagement = "Treat acute hypotension with IV fluid boluses and vasopressors (Norepinephrine, Phenylephrine). Treat bradycardia with Atropine or temporary transcutaneous/transvenous pacing.",
            clinicalPearls = "First-line antiarrhythmic for shock-refractory VF/VT (300 mg IV push after 3rd shock). In non-arrest patients, MUST infuse over 10 minutes to prevent profound hypotension.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("AHA ACLS Guidelines", "2020/2023", "Circulation"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_calcium_chloride",
            genericName = "Calcium Chloride 10%",
            brandNames = "Calcium Chloride",
            synonyms = listOf("CaCl2", "Calcium chloride 10%"),
            category = DrugCategory.EMERGENCY_DRUGS,
            drugClass = "Inorganic Electrolyte Salt / Myocardial Stabilizer & Inotrope",
            mechanismOfAction = "Provides elemental ionized calcium (Ca2+) ions to extracellular fluid, directly stabilizing excitable cardiac cell membranes by shifting the threshold potential away from the resting potential in hyperkalemia. Also increases intracellular calcium influx during phase 2 of the cardiac action potential, directly boosting myocardial contractility (inotropy) and vascular smooth muscle tone.",
            targetReceptor = "Cardiac and vascular excitation-contraction coupling pathways",
            indications = listOf(
                "Life-threatening severe hyperkalemia (serum K+ > 6.5 mEq/L or with ECG changes: peaked T-waves, widened QRS, sine-wave) - membrane stabilization",
                "Severe hypocalcemia (ionized calcium < 0.9 mmol/L) during massive transfusion (citrate toxicity)",
                "Calcium channel blocker and beta-blocker overdose resuscitation",
                "Severe hypermagnesemia antagonism (antidote for magnesium toxicity)",
                "Inotropic support during emergence or post-cardiopulmonary bypass"
            ),
            onsetAndDuration = "Onset: Immediate (< 1 minute IV); Duration: 15 - 30 minutes",
            adultDosing = "Emergency Hyperkalemia / Citrate Toxicity: 0.5 - 1.0 g IV (5 - 10 mL of 10% solution) slow push over 2-5 minutes; repeat in 5-10 minutes if ECG abnormalities persist",
            pediatricDosing = "10 - 20 mg/kg IV (0.1 - 0.2 mL/kg of 10% solution) slow push; Max single dose: 1.0 g",
            obstetricDosing = "1.0 g IV slow push for severe magnesium sulfate toxicity (respiratory depression / loss of deep tendon reflexes / cardiac arrest)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Slow Push",
                    indication = "Hyperkalemic Membrane Stabilization / Citrate Toxicity",
                    adultDose = "0.5 - 1.0 g IV (5 - 10 mL of 10% solution) over 2 - 5 minutes",
                    pediatricDose = "10 - 20 mg/kg IV slow push",
                    unitNotes = "Contains THREE TIMES MORE ELEMENTAL CALCIUM than calcium gluconate: 1 g CaCl2 = 27.2 mg (13.6 mEq) elemental Ca, whereas 1 g Ca gluconate = only 9.3 mg (4.6 mEq) elemental Ca!"
                )
            ),
            formulationsAndRoutes = listOf("10% solution (100 mg/mL = 1 g / 10 mL prefilled emergency syringes)"),
            pharmacokinetics = "100% bioavailable immediately upon IV injection. Ionized calcium is biologically active; 40-50% bound to plasma albumin. Homeostatically regulated by PTH, calcitonin, and vitamin D.",
            onset = "Immediate (< 1 minute)",
            peakEffect = "1 - 2 minutes",
            duration = "15 - 30 minutes",
            elimination = "Fecal and renal excretion",
            contraindications = listOf(
                "Digitalis / Digoxin toxicity (calcium precipitously triggers fatal ventricular arrhythmias in digitalized hearts - 'stone heart')",
                "Pre-existing hypercalcemia or severe ventricular fibrillation prior to defibrillation"
            ),
            cautions = listOf(
                "Severe chemical tissue necrosis: extravasation through a peripheral vein causes severe full-thickness tissue necrosis and skin sloughing (CENTRAL LINE PREFERRED; if peripheral, ensure reliable large-bore vein and aspirate blood frequently)",
                "In digitalized patients, use Calcium Gluconate with extreme caution or avoid",
                "Rapid injection causes severe bradycardia, peripheral vasodilation, hypotension, and syncope",
                "Precipitates when mixed with sodium bicarbonate (forms insoluble calcium carbonate chalk!)"
            ),
            adverseReactions = listOf("Severe extravasation necrosis / skin sloughing", "Bradycardia / asystole with rapid push", "Peripheral flushing / sensation of heat", "Arrhythmias in digitalized patients"),
            interactions = listOf("PRECIPITATES WITH SODIUM BICARBONATE: NEVER infuse in same line without thorough flushing! Insoluble calcium carbonate blocks IV tubing and causes microemboli!"),
            renalAdjustment = "Safe; monitor serum calcium in end-stage renal disease.",
            hepaticAdjustment = "Safe; does NOT require hepatic metabolism (unlike calcium gluconate, which requires liver conversion).",
            pregnancyAndLactation = "First-line antidote for magnesium sulfate toxicity in preeclampsia/eclampsia.",
            elderlyConsiderations = "Safe; monitor ECG during administration.",
            anesthesiaConsiderations = "Contains 3x the elemental calcium of calcium gluconate. Does NOT require liver metabolism to liberate ionized calcium (vital in anhepatic phase of liver transplant and massive trauma shock).",
            reversalAgent = "N/A",
            toxicityManagement = "Treat acute hypercalcemia with aggressive 0.9% NaCl saline hydration and loop diuretics (Furosemide). Treat extravasation with local hyaluronidase infiltration.",
            clinicalPearls = "Provides 3x more elemental calcium than calcium gluconate. NEVER run in the same IV line as Sodium Bicarbonate (forms chalk precipitate). Central line strongly preferred to avoid tissue necrosis.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("AHA ACLS Guidelines", "2020/2023", "Circulation"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        Drug(
            id = "drug_sodium_bicarbonate",
            genericName = "Sodium Bicarbonate 8.4%",
            brandNames = "Sodium Bicarbonate",
            synonyms = listOf("NaHCO3", "Sodium bicarb 8.4%"),
            category = DrugCategory.EMERGENCY_DRUGS,
            drugClass = "Systemic Alkalinizing Agent / Buffering Electrolyte",
            mechanismOfAction = "Dissociates into sodium (Na+) and bicarbonate (HCO3-) ions. Bicarbonate binds hydrogen ions (H+) to form carbonic acid (H2CO3), which rapidly dissociates via carbonic anhydrase into water (H2O) and carbon dioxide (CO2). Carbon dioxide must be eliminated by alveolar ventilation. Alkalinizes plasma and urine, shifting potassium intracellularly and overcoming tricyclic antidepressant sodium-channel blockade.",
            targetReceptor = "Extracellular carbonic acid / bicarbonate buffer system",
            indications = listOf(
                "Severe metabolic acidosis with arterial pH < 7.15 and base deficit > 10 mEq/L (in the presence of adequate mechanical ventilation)",
                "Hyperkalemic cardiac arrest / severe life-threatening hyperkalemia",
                "Tricyclic Antidepressant (TCA) overdose toxicity with widened QRS complex (> 100 ms)",
                "Urinary alkalinization for rhabdomyolysis / myoglobinuria and methotrexate clearance",
                "Buffering local anesthetics (1 mL 8.4% per 9 mL 1% lidocaine) to hasten onset and eliminate sting"
            ),
            onsetAndDuration = "Onset: < 1 - 2 minutes IV; Duration: 15 - 30 minutes",
            adultDosing = "Cardiac Arrest / Severe Hyperkalemia: 1.0 mEq/kg IV push (typically 50 mEq = 50 mL of 8.4% solution); repeat 0.5 mEq/kg every 10 minutes as guided by arterial blood gas (ABG)\nTCA Toxicity: 1 - 2 mEq/kg IV bolus until QRS narrows (< 100 ms) and arterial pH reaches 7.45 - 7.55\nLocal Anesthetic Buffering: 1 mL 8.4% NaHCO3 added to 9 mL 1% or 2% lidocaine",
            pediatricDosing = "1.0 mEq/kg IV slow push (in neonates and infants < 2 years, dilute 1:1 with sterile water to 4.2% solution to avoid intracranial hemorrhage from hyperosmolality!)",
            obstetricDosing = "Safe in severe maternal metabolic acidosis and cardiac arrest; ensure hyperventilation to clear generated CO2",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Severe Acidosis / Hyperkalemia / TCA Toxicity",
                    adultDose = "1.0 mEq/kg IV push (50 mL of 8.4% = 50 mEq)",
                    pediatricDose = "1.0 mEq/kg IV (use 4.2% in neonates)",
                    unitNotes = "MUST ENSURE ADEQUATE MINUTE VENTILATION! Each 50 mEq dose generates approximately 1 liter of CO2 gas that MUST be cleared by the lungs!"
                )
            ),
            formulationsAndRoutes = listOf("8.4% solution (1 mEq/mL in 50 mL pre-filled emergency syringes = 50 mEq)", "4.2% infant formulation (0.5 mEq/mL in 10 mL)"),
            pharmacokinetics = "Rapid buffering of extracellular protons. Generates CO2 gas that diffuses freely into cells, while bicarbonate crosses cellular membranes and the blood-brain barrier slowly. Renal excretion of excess bicarbonate.",
            onset = "< 1 - 2 minutes",
            peakEffect = "2 - 5 minutes",
            duration = "15 - 30 minutes",
            elimination = "Pulmonary exhalation of generated CO2; renal excretion of bicarbonate",
            contraindications = listOf(
                "Severe respiratory acidosis or hypoventilation (WITHOUT MECHANICAL AIRWAY CONTROL: generated CO2 cannot be cleared and worsens intracellular acidosis)",
                "Pre-existing severe metabolic or respiratory alkalosis",
                "Severe hypokalemia or hypocalcemia (alkalosis worsens both)"
            ),
            cautions = listOf(
                "Paradoxical Intracellular and CSF Acidosis: Bicarbonate cannot cross the blood-brain barrier or cell membranes quickly, but the generated CO2 diffuses across lipid membranes immediately, causing intracellular and cerebral CSF pH to DROP paradoxically if alveolar ventilation is inadequate!",
                "Extreme hyperosmolality: 8.4% solution has an osmolarity of ~2000 mOsm/L (rapid injection in neonates causes intraventricular hemorrhage; always dilute to 4.2% in infants)",
                "Hypokalemia and hypocalcemia: acute alkalosis shifts potassium into cells and decreases ionized calcium (tetany risk)",
                "NEVER MIX WITH CALCIUM CHLORIDE (causes chalk precipitate)"
            ),
            adverseReactions = listOf("Paradoxical intracellular/CSF acidosis", "Hypokalemia", "Hypocalcemia (tetany)", "Hypernatremia / hyperosmolality", "Metabolic alkalosis"),
            interactions = listOf("PRECIPITATES WITH CALCIUM SALTS (CaCl2, Ca gluconate); precipitates with catecholamines (inactivates epinephrine/norepinephrine at alkaline pH)"),
            renalAdjustment = "Caution with sodium retention in end-stage renal disease; effective for hyperkalemia.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Safe in maternal metabolic emergencies.",
            elderlyConsiderations = "Risk of sodium overload and pulmonary edema in heart failure.",
            anesthesiaConsiderations = "Never administer sodium bicarbonate without simultaneously ensuring adequate mechanical ventilation, because every 50 mEq generates 1 liter of CO2. Adding 1 mL to 9 mL lidocaine accelerates local block onset dramatically.",
            reversalAgent = "N/A",
            toxicityManagement = "Treat metabolic alkalosis with 0.9% NaCl hydration, potassium replacement, and supportive ventilation.",
            clinicalPearls = "Every 50 mEq dose produces ~1 L of CO2 gas: patient MUST be adequately ventilated to blow off CO2, otherwise paradoxical intracellular acidosis occurs. NEVER mix with calcium.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("AHA ACLS Guidelines", "2020/2023", "Circulation"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        Drug(
            id = "drug_dantrolene",
            genericName = "Dantrolene Sodium / Ryanodex",
            brandNames = "Dantrium, Revonto, Ryanodex",
            synonyms = listOf("Dantrium", "Revonto", "Ryanodex"),
            category = DrugCategory.EMERGENCY_DRUGS,
            drugClass = "Hydantoin Derivative / Ryanodine Receptor (RYR1) Antagonist / MH Rescue Antidote",
            mechanismOfAction = "Directly binds to the skeletal muscle ryanodine receptor type 1 (RYR1), blocking calcium release from the sarcoplasmic reticulum into the myoplasm. Halts the uncontrolled hypermetabolic cascade of intracellular calcium release in Malignant Hyperthermia (MH) without interfering with cardiac or smooth muscle excitation-contraction coupling.",
            targetReceptor = "Ryanodine receptor 1 (RYR1) in skeletal muscle sarcoplasmic reticulum",
            indications = listOf(
                "Malignant Hyperthermia (MH) crisis triggered by volatile anesthetics or succinylcholine (SPECIFIC LIFE-SAVING ANTIDOTE)",
                "Neuroleptic Malignant Syndrome (NMS)",
                "Severe MDMA / amphetamine hyperthermia with muscle rigidity"
            ),
            onsetAndDuration = "Onset: 2 - 5 minutes IV; Duration: 4 - 8 hours",
            adultDosing = "Malignant Hyperthermia Crisis: 2.5 mg/kg IV push immediately; repeat 1.0 - 2.5 mg/kg every 5-10 minutes until hypermetabolism resolves (hypercapnia, tachycardia, and rigidity subside). Average effective dose: 2.5 - 5.0 mg/kg; maximum dose: 10 mg/kg (can exceed 10 mg/kg if hypermetabolism continues unabated). Once stabilized, administer 1.0 mg/kg every 4-6 hours for 24-48 hours to prevent recrudescence",
            pediatricDosing = "2.5 mg/kg IV push; repeat as needed (identical to adult dosing on weight basis)",
            obstetricDosing = "2.5 mg/kg IV immediately for maternal MH; crosses placenta; safe in emergency",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Rapid Push",
                    indication = "Malignant Hyperthermia Crisis (MHAUS Protocol)",
                    adultDose = "2.5 mg/kg IV rapid push (repeat every 5-10 min up to 10 mg/kg)",
                    pediatricDose = "2.5 mg/kg IV rapid push",
                    unitNotes = "Total Body Weight (TBW). Ryanodex (nanocrystal formulation) dissolves in 5 mL sterile water in under 1 minute (250 mg/vial). Older formulations (Dantrium/Revonto 20 mg/vial) require 60 mL warm sterile water per vial (36 vials for 70 kg adult)!"
                )
            ),
            formulationsAndRoutes = listOf(
                "Ryanodex: 250 mg lyophilized nanocrystal powder per vial (reconstitutes in 5 mL sterile water in < 1 minute!)",
                "Dantrium / Revonto: 20 mg powder per vial containing 3.0 g mannitol (reconstitutes in 60 mL sterile water; requires 36 vials for 70 kg adult)"
            ),
            pharmacokinetics = "Hepatic metabolism to active metabolite 5-hydroxydantrolene and acetylamino-dantrolene. Elimination half-life ~6-12 hours; excreted in urine and bile.",
            onset = "2 - 5 minutes",
            peakEffect = "5 - 10 minutes",
            duration = "4 - 8 hours",
            elimination = "Hepatic metabolism; renal and biliary excretion",
            contraindications = listOf("Zero contraindications in life-threatening Malignant Hyperthermia!"),
            cautions = listOf(
                "Co-administration with Calcium Channel Blockers (verapamil, diltiazem) is STRICTLY CONTRAINDICATED during MH (causes severe hyperkalemia and fatal myocardial depression)",
                "Severe generalized skeletal muscle weakness and respiratory muscle fatigue (post-resuscitation monitoring in ICU mandatory)",
                "Thrombophlebitis due to high alkaline pH (pH ~9.5) of older reconstituted formulations",
                "Recrudescence of MH occurs in up to 25% of cases within 24 hours (continue maintenance dosing for 24-48 hours)"
            ),
            adverseReactions = listOf("Severe skeletal muscle weakness / respiratory depression", "Thrombophlebitis", "Severe hyperkalemia if given with calcium channel blockers", "Diuresis (older formulation contains 3 g mannitol per vial)"),
            interactions = listOf("STRICTLY CONTRAINDICATED WITH CALCIUM CHANNEL BLOCKERS (precipitates hyperkalemic cardiac arrest!)"),
            renalAdjustment = "Safe; monitor urine output.",
            hepaticAdjustment = "Safe in acute crisis; prolonged oral therapy can cause hepatotoxicity.",
            pregnancyAndLactation = "Life-saving for mother; administer without hesitation.",
            elderlyConsiderations = "Watch for profound post-crisis muscle weakness.",
            anesthesiaConsiderations = "Specific antidote for Malignant Hyperthermia. Full MHAUS protocol: (1) Turn off volatile and succinylcholine, (2) Hyperventilate with 100% O2 at >= 10 L/min, (3) Dantrolene 2.5 mg/kg IV push, (4) Cool patient (ice, cold saline), (5) Treat hyperkalemia (calcium, insulin/dextrose, bicarb), (6) Call MHAUS hotline (1-800-644-9737).",
            reversalAgent = "N/A",
            toxicityManagement = "Support ventilation in ICU for skeletal muscle weakness. NEVER administer calcium channel blockers.",
            clinicalPearls = "The specific antidote for Malignant Hyperthermia (2.5 mg/kg IV initial push). NEVER give calcium channel blockers with dantrolene (causes hyperkalemic cardiac collapse). Ryanodex reconstitutes 1 vial in 5 mL.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("MHAUS Treatment Protocol for Malignant Hyperthermia", "2023", "MHAUS"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "Sarcoplasmic Reticulum Ryanodine Receptor (RYR1) Blockade",
            diagramType = "NMJ_NICOTINIC_ACHR",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        // OBSTETRIC MEDICATIONS
        Drug(
            id = "drug_oxytocin",
            genericName = "Oxytocin",
            brandNames = "Pitocin, Syntocinon",
            synonyms = listOf("Pitocin", "Syntocinon"),
            category = DrugCategory.OBSTETRIC_MEDICATIONS,
            drugClass = "Synthetic Nonapeptide Uterotonic Hormone / Gq Agonist",
            mechanismOfAction = "Selectively stimulates G-protein coupled oxytocin receptors on myometrial smooth muscle cells, triggering phospholipase C, IP3 generation, and calcium influx to produce rhythmic, coordinated uterine contractions and sustained basal uterine tone, compressing bleeding uterine intramyometrial vessels.",
            targetReceptor = "Myometrial oxytocin receptor (OTR)",
            indications = listOf(
                "Prevention and treatment of postpartum hemorrhage (PPH) due to uterine atony following vaginal or Cesarean delivery (first-line)",
                "Induction and augmentation of labor",
                "Adjunctive management of incomplete or inevitable abortion"
            ),
            onsetAndDuration = "Onset: Immediate IV (< 1 minute); 3 - 5 minutes IM; Duration: 30 - 60 minutes IV",
            adultDosing = "Cesarean Delivery Prophylaxis ('Rule of 4s' / Slow Titration): 3 - 5 IU slow IV push over 1 - 2 minutes, followed by maintenance infusion of 5 - 10 IU/hr (e.g. 10 - 20 IU in 500 mL balanced crystalloid running at 250 - 500 mL/hr)\nPPH Treatment: Additional 3 - 5 IU slow IV over 2 minutes, increase infusion to 10 - 20 IU/hr",
            pediatricDosing = "N/A (Obstetric medication)",
            obstetricDosing = "3 - 5 IU slow IV push (never rapid push!) followed by 10 - 20 IU/500 mL infusion at 125-250 mL/hr; first-line uterotonic worldwide",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Slow Push & Infusion",
                    indication = "Cesarean Delivery PPH Prevention",
                    adultDose = "3 - 5 IU slow IV over >= 1 - 2 minutes, then 10 - 20 IU in 500 mL crystalloid infused at 125 - 250 mL/hr",
                    maxDose = "40 IU total cumulative infusion in routine setting",
                    unitNotes = "NEVER ADMINISTER RAPID UNDILUTED IV BOLUS! Rapid bolus causes catastrophic systemic vasodilation, profound hypotension, reflex tachycardia, myocardial ischemia, and cardiac arrest!"
                )
            ),
            formulationsAndRoutes = listOf("10 IU/mL (1 mL vials)"),
            pharmacokinetics = "Hydrolyzed rapidly by plasma and placental oxytocinase (tissue aminopeptidase) and cleared hepatically and renally. Elimination half-life ~3-5 minutes.",
            onset = "Immediate IV (< 1 minute)",
            peakEffect = "1 - 2 minutes",
            duration = "30 - 60 minutes",
            elimination = "Plasma oxytocinase hydrolysis; hepatic and renal clearance",
            contraindications = listOf(
                "Known hypersensitivity to oxytocin",
                "Non-reassuring fetal status prior to delivery (when used for induction)"
            ),
            cautions = listOf(
                "RAPID IV BOLUS COLLAPSE: Rapid push (> 5 IU in seconds) induces profound acute vasodilation, precipitous drop in SVR and blood pressure, severe reflex tachycardia, and myocardial ischemia (can be fatal in hypovolemic or cardiac mothers)",
                "Water intoxication and hyponatremia: shares structural homology with antidiuretic hormone (vasopressin); prolonged large-volume infusions (> 40-50 IU in hypotonic fluids) cause hyponatremic seizures and pulmonary edema",
                "Receptor desensitization: prolonged pre-delivery oxytocin infusion causes receptor downregulation, requiring second-line uterotonics (Methergine, Hemabate, TXA)"
            ),
            adverseReactions = listOf("Profound hypotension with rapid bolus", "Tachycardia", "Myocardial ischemia / ST-elevation", "Hyponatremia / water intoxication", "Nausea and vomiting"),
            interactions = listOf("Additive hypotension with general anesthetics; additive uterine tone with other uterotonics"),
            renalAdjustment = "Safe; watch for water retention.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "First-line uterotonic in obstetrics; compatible with breastfeeding.",
            elderlyConsiderations = "N/A (Obstetric use).",
            anesthesiaConsiderations = "First-line uterotonic for Cesarean delivery. Anesthesiologists MUST administer as a SLOW diluted push (3-5 IU over 1-2 min) followed by continuous infusion. NEVER slam an undiluted bolus.",
            reversalAgent = "Vasopressors (Phenylephrine) for acute vasodilation; Tocolytics (Nitroglycerin 50-100 mcg IV) for uterine hyperstimulation.",
            toxicityManagement = "Treat acute vasodilation/hypotension with Phenylephrine (100 mcg IV) and rapid crystalloid bolus. Treat water intoxication/seizures with 3% hypertonic saline.",
            clinicalPearls = "First-line for postpartum hemorrhage. NEVER give rapid IV bolus (causes massive vasodilation, hypotension, and cardiac arrest). Structurally similar to ADH (water intoxication risk with high doses in D5W).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology"),
                DrugReference("WHO Recommendations for the Prevention and Treatment of Postpartum Hemorrhage", "2022", "WHO Guidelines")
            ),
            relatedGuideIds = listOf("guide_cesarean_section"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        Drug(
            id = "drug_methylergonovine",
            genericName = "Methylergonovine Maleate (Methergine)",
            brandNames = "Methergine",
            synonyms = listOf("Methergine", "Methylergometrine"),
            category = DrugCategory.OBSTETRIC_MEDICATIONS,
            drugClass = "Ergot Alkaloid Derivative / Alpha-Adrenergic & Serotonergic Uterotonic",
            mechanismOfAction = "Acts as a partial agonist at alpha-adrenergic, serotonin (5-HT2), and dopaminergic receptors, producing intense, sustained, tetanic uterine smooth muscle contraction and peripheral arterial vasoconstriction.",
            targetReceptor = "Alpha-1 adrenergic, 5-HT2 serotonin, and dopamine receptors",
            indications = listOf(
                "Second-line management of refractory postpartum hemorrhage (PPH) due to uterine atony unresponsive to oxytocin"
            ),
            onsetAndDuration = "Onset: 2 - 5 minutes IM; Peak: 15 minutes; Duration: 2 - 4 hours (sustained tetanic contraction)",
            adultDosing = "0.2 mg INTRAMUSCULAR (IM) injection; repeat every 2 to 4 hours as needed (Max: 5 doses / 1.0 mg)\nNEVER ADMINISTER RAPID IV (can cause catastrophic cerebral hemorrhage / stroke / MI)",
            pediatricDosing = "N/A (Obstetric medication)",
            obstetricDosing = "0.2 mg IM in the deltoid or gluteus (or intramyometrial by surgeon); contra-indicated in hypertensive disorders of pregnancy",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IM Injection ONLY",
                    indication = "Refractory Postpartum Hemorrhage",
                    adultDose = "0.2 mg IM into deltoid or anterior thigh (vastus lateralis)",
                    maxDose = "1.0 mg (5 doses) total",
                    unitNotes = "ROUTE OF CHOICE IS INTRAMUSCULAR (IM). If emergency IV route is ever mandated, it MUST be diluted in 10 mL saline and infused over at least 5-10 minutes with continuous arterial BP monitoring!"
                )
            ),
            formulationsAndRoutes = listOf("0.2 mg/mL (1 mL ampules); must be refrigerated and protected from light"),
            pharmacokinetics = "Hepatic metabolism; elimination half-life ~2 hours; excreted in urine and bile.",
            onset = "2 - 5 minutes IM",
            peakEffect = "15 minutes",
            duration = "2 - 4 hours",
            elimination = "Hepatic metabolism; renal and fecal excretion",
            contraindications = listOf(
                "Hypertension, gestational hypertension, preeclampsia, or eclampsia (STRICTLY CONTRAINDICATED - causes catastrophic malignant hypertensive crisis and intracranial hemorrhage!)",
                "Coronary artery disease, Prinzmetal's angina, or peripheral vascular disease",
                "Known hypersensitivity to ergot alkaloids"
            ),
            cautions = listOf(
                "Catastrophic Hypertensive Crisis: intense peripheral alpha-1 vasoconstriction can precipitate acute intracranial hemorrhage, stroke, pulmonary edema, and aortic dissection",
                "Severe coronary vasospasm: can cause acute myocardial infarction in young parturients",
                "Never administer IV push; IM injection is the standard of care"
            ),
            adverseReactions = listOf("Severe hypertension", "Coronary artery vasospasm / myocardial infarction", "Severe headache / seizures", "Nausea and vomiting (prominent)", "Peripheral ischemia"),
            interactions = listOf("Strong CYP3A4 inhibitors (macrolides, azoles) increase ergot toxicity (ergotism)"),
            renalAdjustment = "Safe; monitor blood pressure.",
            hepaticAdjustment = "Safe; monitor blood pressure.",
            pregnancyAndLactation = "CONTRAINDICATED prior to delivery of the placenta (causes tetanic contraction trapping the placenta or fetus). Compatible with postpartum use.",
            elderlyConsiderations = "N/A (Obstetric use).",
            anesthesiaConsiderations = "Essential second-line uterotonic for uterine atony. Anesthesiologist MUST check the blood pressure before administration: IF BP >= 140/90 mmHg OR PREECLAMPSIA EXISTS, METHERGINE IS STRICTLY FORBIDDEN (use Carboprost or Misoprostol instead).",
            reversalAgent = "Vasodilators: Nitroglycerin, Nitroprusside, or Nicardipine for ergot-induced hypertensive crisis / coronary spasm.",
            toxicityManagement = "Treat acute severe hypertension / coronary vasospasm with IV Nitroglycerin (50-100 mcg boluses, then infusion) or Nicardipine IV. Treat seizures with Midazolam or Magnesium sulfate.",
            clinicalPearls = "STRICTLY CONTRAINDICATED IN HYPERTENSION OR PREECLAMPSIA (causes stroke and intracranial hemorrhage). Give IM only (never rapid IV). Always check blood pressure before giving.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ACOG Practice Bulletin on Postpartum Hemorrhage", "2017/2022", "Obstetrics & Gynecology"),
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology")
            ),
            relatedGuideIds = listOf("guide_cesarean_section"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        Drug(
            id = "drug_carboprost",
            genericName = "Carboprost Tromethamine (15-methyl PGF2-alpha / Hemabate)",
            brandNames = "Hemabate",
            synonyms = listOf("Hemabate", "15-methyl Prostaglandin F2-alpha"),
            category = DrugCategory.OBSTETRIC_MEDICATIONS,
            drugClass = "Synthetic Prostaglandin F2-alpha Analog Uterotonic",
            mechanismOfAction = "Directly binds to prostaglandin FP receptors on myometrial smooth muscle, stimulating intracellular calcium release and producing intense, sustained myometrial contractions to halt postpartum uterine hemorrhage.",
            targetReceptor = "Prostaglandin FP receptor",
            indications = listOf(
                "Refractory postpartum hemorrhage (PPH) due to uterine atony unresponsive to oxytocin and ergometrine"
            ),
            onsetAndDuration = "Onset: 15 - 30 minutes IM; Peak: 30 minutes; Duration: 2 - 4 hours",
            adultDosing = "250 mcg (0.25 mg) DEEP INTRAMUSCULAR (IM) injection into gluteus, vastus lateralis, or directly into myometrium by surgeon; repeat every 15 to 90 minutes as needed (Max cumulative dose: 2.0 mg / 8 doses)",
            pediatricDosing = "N/A (Obstetric medication)",
            obstetricDosing = "250 mcg deep IM; contra-indicated in active bronchial asthma",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Deep IM Injection ONLY",
                    indication = "Refractory Postpartum Hemorrhage",
                    adultDose = "250 mcg (0.25 mg) deep IM or intramyometrial",
                    maxDose = "2.0 mg (8 doses) total",
                    unitNotes = "NEVER ADMINISTER INTRAVENOUSLY! Deep IM injection only. Dose is in MICROGRAMS (250 mcg = 0.25 mg)"
                )
            ),
            formulationsAndRoutes = listOf("250 mcg/mL (0.25 mg/mL in 1 mL ampules); must be refrigerated at 2°C - 8°C"),
            pharmacokinetics = "Slowly metabolized by 15-hydroxyprostaglandin dehydrogenase (15-PGDH) in the lungs and liver due to the protective 15-methyl group. Elimination half-life ~2-3 hours.",
            onset = "15 - 30 minutes",
            peakEffect = "30 minutes",
            duration = "2 - 4 hours",
            elimination = "Pulmonary and hepatic enzymatic oxidation; renal excretion",
            contraindications = listOf(
                "Active bronchial asthma or history of reactive airway disease (STRICTLY CONTRAINDICATED - potent bronchoconstrictor that can trigger fatal status asthmaticus!)",
                "Severe hepatic, renal, or cardiovascular disease",
                "Known hypersensitivity to carboprost"
            ),
            cautions = listOf(
                "Severe Bronchospasm: stimulates bronchial smooth muscle FP receptors, causing intense bronchoconstriction and ventilation failure in asthmatics",
                "Severe Pulmonary Vasoconstriction: increases pulmonary vascular resistance (PVR) and right ventricular afterload (avoid in pulmonary hypertension)",
                "Severe Diarrhea and Vomiting: stimulates intestinal smooth muscle (occurs in up to 60-70% of patients; premedicate with loperamide / antiemetics)",
                "Drug-induced pyrexia / fever (temperature elevation up to 39°C within 1-2 hours)"
            ),
            adverseReactions = listOf("Severe bronchospasm / wheezing", "Explosive diarrhea (up to 70%)", "Nausea and vomiting", "Hypertension", "Pyrexia / flushing", "Elevated PVR / V/Q mismatch"),
            interactions = listOf("Synergistic uterine contraction with other uterotonics"),
            renalAdjustment = "Safe; monitor for volume depletion from diarrhea.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Second-line PPH drug of choice in hypertensive parturients where Methergine is contraindicated.",
            elderlyConsiderations = "N/A (Obstetric use).",
            anesthesiaConsiderations = "Premier second-line uterotonic in hypertensive/preeclamptic patients (where Methergine is banned). BUT STRICTLY BANNED IN ASTHMA (causes lethal bronchospasm). Anesthesiologist must ask: 'Does this patient have asthma?'",
            reversalAgent = "Beta-2 agonists (Albuterol) and Epinephrine for bronchospasm.",
            toxicityManagement = "Treat acute bronchospasm with high-dose nebulized Albuterol, Ipratropium, and IV Epinephrine (10-50 mcg). Treat severe diarrhea with Loperamide (2-4 mg).",
            clinicalPearls = "STRICTLY CONTRAINDICATED IN ASTHMA (causes lethal bronchospasm). The drug of choice for PPH in hypertensive/preeclamptic patients (where Methergine is forbidden). Expect diarrhea in 70% of patients.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ACOG Practice Bulletin on Postpartum Hemorrhage", "2017/2022", "Obstetrics & Gynecology"),
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology")
            ),
            relatedGuideIds = listOf("guide_cesarean_section")
        ),
        Drug(
            id = "drug_magnesium_sulfate",
            genericName = "Magnesium Sulfate (MgSO4)",
            brandNames = "Magnesium Sulfate 50%",
            synonyms = listOf("MgSO4", "Magnesium sulfate heptahydrate"),
            category = DrugCategory.OBSTETRIC_MEDICATIONS,
            drugClass = "Divalent Inorganic Mineral Salt / Anticonvulsant & NMDA Blocker",
            mechanismOfAction = "Acts as a physiologic calcium antagonist and non-competitive NMDA receptor blocker. Inhibits presynaptic voltage-gated calcium channels, blocking acetylcholine release at the neuromuscular junction and motor cortex. Decreases neuronal excitability, raises the seizure threshold, produces cerebral and peripheral vasodilation, and stabilizes excitable membranes.",
            targetReceptor = "NMDA receptor channel pore, presynaptic calcium channels",
            indications = listOf(
                "Prevention and treatment of eclamptic seizures in severe preeclampsia (Zuspan / Pritchard protocol - international gold standard)",
                "Fetal neuroprotection prior to preterm delivery (< 32 weeks gestation)",
                "Torsades de Pointes polymorphic ventricular tachycardia resuscitation",
                "Severe refractory status asthmaticus (smooth muscle bronchodilation)"
            ),
            onsetAndDuration = "Onset: Immediate IV (< 1 minute); Duration: 30 minutes following bolus; continuous during maintenance infusion",
            adultDosing = "Eclampsia Seizure Prophylaxis (Zuspan Regimen): Loading dose of 4.0 - 6.0 g IV infused over 15 to 20 minutes, followed by continuous maintenance infusion of 1.0 - 2.0 g/hr IV for 24 hours postpartum\nTorsades de Pointes: 1.0 - 2.0 g IV diluted in 10 mL D5W over 2 - 5 minutes",
            pediatricDosing = "Status Asthmaticus: 25 - 50 mg/kg IV over 20 minutes (Max: 2.0 g)",
            obstetricDosing = "4.0 - 6.0 g IV over 20 min, then 1.0 - 2.0 g/hr IV; mandatory hourly monitoring of: (1) deep tendon reflexes, (2) respiratory rate (>= 12/min), and (3) urine output (>= 30 mL/hr)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Infusion",
                    indication = "Severe Preeclampsia / Eclampsia Seizure Prophylaxis",
                    adultDose = "Loading: 4.0 - 6.0 g IV over 15 - 20 minutes; Maintenance: 1.0 - 2.0 g/hr IV infusion",
                    maxDose = "Target therapeutic serum magnesium level: 4.8 - 8.4 mg/dL (2.0 - 3.5 mmol/L)",
                    unitNotes = "HOURLY MONITORING MANDATORY: patellar reflexes must be present, respiratory rate >= 12 bpm, urine output >= 30 mL/hr (cleared renally). Have Calcium Gluconate/Chloride at bedside!"
                )
            ),
            formulationsAndRoutes = listOf("50% solution (500 mg/mL in 10 mL vials = 5 g); pre-mixed infusion bags (20 g in 500 mL or 40 g in 1000 mL 0.9% NaCl)"),
            pharmacokinetics = "Excreted exclusively by glomerular filtration in the kidneys (99%). Elimination half-life ~4 hours in normal renal function.",
            onset = "Immediate IV",
            peakEffect = "End of 20-min infusion",
            duration = "Continuous during infusion; 4 hours post-infusion",
            elimination = "Exclusively renal (glomerular filtration)",
            contraindications = listOf(
                "Myasthenia gravis (causes catastrophic myasthenic crisis and respiratory paralysis by blocking ACh release)",
                "Severe renal failure (CrCl < 20 mL/min; massive rapid toxicity unless infusion rate severely reduced)",
                "Heart block or severe myocardial damage"
            ),
            cautions = listOf(
                "MARKERS OF PROGRESSIVE TOXICITY:\n• 4 - 7 mEq/L: Therapeutic range\n• 8 - 10 mEq/L: Loss of deep tendon patellar reflexes (FIRST SIGN)\n• 10 - 12 mEq/L: Respiratory depression / hypoventilation\n• 12 - 15 mEq/L: Heart block, hypotension, altered mental status\n• > 15 - 20 mEq/L: Cardiac arrest in asystole",
                "POTENTIATION OF MUSCLE RELAXANTS: profoundly potentiates both depolarizing (succinylcholine) and non-depolarizing (rocuronium, vecuronium) neuromuscular blockers by 200-400%! (Reduce rocuronium intubating/maintenance dose and monitor with TOF)",
                "Renal clearance: since magnesium is cleared 100% by the kidneys, oliguria (urine output < 30 mL/hr) leads to rapid fatal accumulation"
            ),
            adverseReactions = listOf("Loss of deep tendon reflexes", "Respiratory depression", "Profound muscle weakness", "Severe hypotension / flushing", "Bradycardia / cardiac arrest"),
            interactions = listOf("PROFOUNDLY POTENTIATES NON-DEPOLARIZING MUSCLE RELAXANTS (rocuronium requirement cut in half; always use objective TOF monitoring!)"),
            renalAdjustment = "CONTRAINDICATED or drastically reduced (omit or give 50% maintenance: 0.5-1.0 g/hr with frequent serum levels) in renal failure.",
            hepaticAdjustment = "Safe in liver disease; 100% renally eliminated.",
            pregnancyAndLactation = "First-line anticonvulsant for preeclampsia/eclampsia (Magpie Trial); superior to phenytoin and diazepam.",
            elderlyConsiderations = "N/A (Obstetric use).",
            anesthesiaConsiderations = "Crucial anesthesia rule: magnesium cuts the required dose of muscle relaxants (rocuronium) in half. Always verify that 1 g IV Calcium Chloride or Calcium Gluconate is at the bedside.",
            reversalAgent = "Calcium Gluconate (1.0 g IV over 3-5 min) or Calcium Chloride (0.5 - 1.0 g IV).",
            toxicityManagement = "MAGNESIUM TOXICITY MANAGEMENT: (1) Immediately stop magnesium infusion! (2) Administer 10% Calcium Gluconate 1.0 g IV (10 mL) or 10% Calcium Chloride 0.5 - 1.0 g IV over 3-5 minutes. (3) Secure airway and ventilate with 100% O2 if respiratory depression occurs. (4) Hemodialysis if refractory or in acute renal failure.",
            clinicalPearls = "Gold standard for eclampsia seizure prevention (Magpie trial). Loss of patellar reflex is the FIRST sign of toxicity (8-10 mEq/L). Profoundly potentiates rocuronium. Have Calcium ready at bedside.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ACOG Practice Bulletin on Gestational Hypertension and Preeclampsia", "2020", "Obstetrics & Gynecology"),
                DrugReference("The Magpie Trial Collaborative Group", "2002", "Lancet"),
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology")
            ),
            relatedGuideIds = listOf("guide_cesarean_section"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        // ANTIEMETICS
        Drug(
            id = "drug_ondansetron",
            genericName = "Ondansetron Hydrochloride",
            brandNames = "Zofran",
            synonyms = listOf("Zofran", "Ondansetron"),
            category = DrugCategory.ANTIEMETICS,
            drugClass = "Selective Serotonin 5-HT3 Receptor Antagonist",
            mechanismOfAction = "Selectively and competitively antagonizes serotonin 5-HT3 receptors located peripherally on vagal nerve terminals in the gastrointestinal tract and centrally in the chemoreceptor trigger zone (CTZ) of the area postrema in the medulla oblongata.",
            targetReceptor = "Serotonin 5-HT3 receptor",
            indications = listOf(
                "Prophylaxis and treatment of postoperative nausea and vomiting (PONV - SAMBA Guidelines first-line)",
                "Prevention of nausea and vomiting associated with chemotherapy and radiation",
                "Spinal opioid-induced pruritus attenuation"
            ),
            onsetAndDuration = "Onset: < 5 minutes IV; Peak: 15 - 30 minutes; Duration: 4 - 8 hours",
            adultDosing = "PONV Prophylaxis: 4.0 mg IV administered at the END of surgical procedure (prior to emergence)\nPONV Treatment in PACU: 4.0 mg IV (if not received prophylactically)\nMax Dose: 8.0 mg IV per single dose",
            pediatricDosing = "0.1 mg/kg IV (Max single dose: 4.0 mg) at the end of surgery",
            obstetricDosing = "4.0 mg IV at delivery for post-cesarean nausea and intrathecal morphine pruritus; FDA Category B; widely used in obstetrics",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "PONV Prophylaxis (SAMBA Guidelines)",
                    adultDose = "4.0 mg IV slow push given 15-30 minutes BEFORE emergence at the end of surgery",
                    pediatricDose = "0.1 mg/kg IV (Max 4.0 mg)",
                    elderlyDose = "4.0 mg IV",
                    maxDose = "8.0 mg single dose; 16 mg/day",
                    unitNotes = "Timing matters! 5-HT3 antagonists are most effective when administered at the END OF SURGERY (unlike dexamethasone, which must be given at induction)"
                )
            ),
            formulationsAndRoutes = listOf("2 mg/mL (2 mL and 20 mL vials = 4 mg, 40 mg); 4 mg and 8 mg oral disintegrating tablets (ODT)"),
            pharmacokinetics = "Hepatic metabolism via CYP3A4, CYP1A2, and CYP2D6 to glucuronide and sulfate conjugates. Elimination half-life ~3-5 hours.",
            onset = "< 5 minutes",
            peakEffect = "15 - 30 minutes",
            duration = "4 - 8 hours",
            elimination = "Hepatic CYP3A4/CYP1A2/CYP2D6 metabolism; renal excretion (5%)",
            contraindications = listOf(
                "Congenital Long QT Syndrome (STRICTLY CONTRAINDICATED - prolongs QT interval)",
                "Concurrent administration of apomorphine (causes profound hypotension and loss of consciousness)",
                "Known hypersensitivity"
            ),
            cautions = listOf(
                "QTc prolongation and Torsades de Pointes: dose-dependent prolongation of the QT interval (caution with other QT-prolonging drugs: droperidol, volatile agents, methadone)",
                "Headache (most common side effect, in up to 10-20% of patients)",
                "Mild transient elevation of hepatic transaminases"
            ),
            adverseReactions = listOf("Headache (common)", "Constipation", "QTc interval prolongation", "Flushing / sensation of warmth"),
            interactions = listOf("Additive QTc prolongation with other antiarrhythmics or psychotropics"),
            renalAdjustment = "Safe; no dose adjustment needed.",
            hepaticAdjustment = "Severe hepatic impairment (Child-Pugh >= 10): maximum daily dose is 8.0 mg.",
            pregnancyAndLactation = "FDA Category B; widely used in obstetric anesthesia and hyperemesis gravidarum.",
            elderlyConsiderations = "Safe in elderly; check baseline QTc if cardiac history.",
            anesthesiaConsiderations = "Cornerstone of multimodal PONV prophylaxis (SAMBA guidelines). Administer at the END of surgery (30 min before wake-up). Pair with Dexamethasone (given at induction) for synergistic antiemetic efficacy.",
            reversalAgent = "N/A",
            toxicityManagement = "Supportive care. Monitor ECG for QTc prolongation and manage Torsades de Pointes with Magnesium sulfate (1-2 g IV).",
            clinicalPearls = "Most effective when given at the END of surgery (unlike Dexamethasone, which is given at induction). Watch for QTc prolongation. Pair with Dexamethasone for 80%+ PONV protection.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Fourth Consensus Guidelines for the Management of Postoperative Nausea and Vomiting", "2020", "Anesthesia & Analgesia (SAMBA)"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy", "guide_hip_fracture"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        Drug(
            id = "drug_dexamethasone",
            genericName = "Dexamethasone Sodium Phosphate",
            brandNames = "Decadron",
            synonyms = listOf("Decadron", "Dexamethasone"),
            category = DrugCategory.ANTIEMETICS,
            drugClass = "Long-acting Fluorinated Synthetic Glucocorticoid Corticosteroid",
            mechanismOfAction = "Potent anti-inflammatory and antiemetic glucocorticoid (zero mineralocorticoid activity; 25-30x more potent than hydrocortisone). Antiemetic action is mediated through central inhibition of prostaglandin synthesis in the CTZ, reduction of serotonin turnover in the brain, and suppression of systemic inflammatory mediators.",
            targetReceptor = "Intracellular glucocorticoid receptor (alters gene transcription)",
            indications = listOf(
                "Prophylaxis of postoperative nausea and vomiting (PONV - SAMBA Guidelines first-line)",
                "Reduction of airway edema and post-extubation stridor",
                "Prolongation of analgesia duration in peripheral nerve blocks (perineural or IV additive)",
                "Multimodal analgesia (reduces postoperative pain and opioid requirements)"
            ),
            onsetAndDuration = "Onset: Delayed (1 - 2 hours for antiemetic effect); Peak: 2 - 4 hours; Duration: Very long (24 - 72 hours biological half-life)",
            adultDosing = "PONV Prophylaxis: 4.0 - 8.0 mg IV administered AT THE TIME OF ANESTHESIA INDUCTION\nAirway Edema / Stridor: 8.0 - 12.0 mg IV\nPerineural Block Adjunct: 4.0 mg IV or perineural (prolongs block duration by 6-8 hours)",
            pediatricDosing = "0.15 mg/kg IV (Max: 4.0 - 8.0 mg) at induction",
            obstetricDosing = "4.0 - 8.0 mg IV at induction / following delivery; safe in pregnancy",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "PONV Prophylaxis (SAMBA Guidelines)",
                    adultDose = "4.0 - 8.0 mg IV administered immediately AFTER INDUCTION",
                    pediatricDose = "0.15 mg/kg IV (Max 4.0 mg)",
                    elderlyDose = "4.0 mg IV",
                    maxDose = "8.0 - 10.0 mg for routine PONV",
                    unitNotes = "TIMING IS CRITICAL! Dexamethasone requires 1-2 hours for genomic transcription: MUST BE GIVEN AT INDUCTION (giving it at the end of surgery is ineffective for early PACU nausea!)"
                )
            ),
            formulationsAndRoutes = listOf("4 mg/mL and 10 mg/mL preservative-free vials for injection"),
            pharmacokinetics = "Hepatic CYP3A4 metabolism to inactive metabolites. Plasma half-life is ~3-4 hours, but tissue biological effect persists for 24 - 72 hours.",
            onset = "1 - 2 hours (requires gene transcription)",
            peakEffect = "2 - 4 hours",
            duration = "24 - 72 hours",
            elimination = "Hepatic CYP3A4 metabolism; renal excretion of metabolites",
            contraindications = listOf(
                "Systemic fungal infections",
                "Known hypersensitivity"
            ),
            cautions = listOf(
                "Perineal / perianal burning or itching: rapid IV bolus in awake patients causes intense, distressing burning in the genital and perianal region (administer slowly over 1-2 minutes or AFTER patient is asleep under general anesthesia!)",
                "Transient hyperglycemia: raises blood glucose by ~20-40 mg/dL in diabetic patients (monitor blood glucose; does not preclude use in diabetics, but requires awareness)",
                "Immune suppression and wound healing concerns with repeated high doses (single dose does not increase surgical site infection)"
            ),
            adverseReactions = listOf("Perineal burning with rapid IV push in awake patients", "Transient hyperglycemia", "Mild facial flushing", "Insomnia / euphoria"),
            interactions = listOf("Enzyme inducers (phenytoin, carbamazepine) decrease dexamethasone levels"),
            renalAdjustment = "Safe; no adjustment needed.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Crosses placenta; promotes fetal lung maturity; safe single dose for PONV.",
            elderlyConsiderations = "Safe; watch for mild blood glucose elevation in diabetic elderly.",
            anesthesiaConsiderations = "TIMING MANDATE: Always give at induction because genomic mechanism requires 1-2 hours. In awake patients, warn of temporary perineal burning or inject slowly. Provides dual benefit: PONV prevention + opioid-sparing analgesia.",
            reversalAgent = "N/A",
            toxicityManagement = "Supportive care. Manage acute hyperglycemia with subcutaneous or IV regular insulin.",
            clinicalPearls = "MUST BE GIVEN AT INDUCTION (needs 1-2 hours to work). If given awake, inject very slowly to prevent severe perineal itching/burning. Single dose does NOT increase wound infection risk.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Fourth Consensus Guidelines for the Management of Postoperative Nausea and Vomiting", "2020", "Anesthesia & Analgesia (SAMBA)"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy", "guide_hip_fracture")
        ),
        // FLUIDS, ELECTROLYTES, AND BLOOD PRODUCTS
        Drug(
            id = "drug_lactated_ringers",
            genericName = "Lactated Ringer's Solution (Hartmann's)",
            brandNames = "Lactated Ringer's, Hartmann's Solution",
            synonyms = listOf("Hartmann's Solution", "RL", "LR", "Compound Sodium Lactate"),
            category = DrugCategory.FLUIDS_ELECTROLYTES_BLOOD,
            drugClass = "Balanced Isotonic Crystalloid Solution",
            mechanismOfAction = "Restores intravascular and extracellular fluid (ECF) volume. Contains physiologic electrolytes approximating plasma: Na+ 130 mEq/L, Cl- 109 mEq/L, K+ 4 mEq/L, Ca2+ 2.7 mEq/L, and Sodium Lactate 28 mEq/L (osmolarity ~273 mOsm/L). Lactate is metabolized in the liver to bicarbonate, consuming H+ and providing a balanced buffer against acidosis.",
            targetReceptor = "Extracellular fluid compartment expansion",
            indications = listOf(
                "First-line maintenance and resuscitation crystalloid in general surgery, obstetrics, and trauma",
                "Perioperative volume replacement, fasting deficit replacement, and third-space fluid loss",
                "Burn resuscitation (Parkland formula)"
            ),
            onsetAndDuration = "Onset: Immediate IV; Intravascular Retention: ~20 - 25% remains intravascular at 1 hour (75 - 80% redistributes into interstitial space)",
            adultDosing = "Maintenance: 1.5 - 2.5 mL/kg/hr IV\nGoal-Directed Resuscitation: 250 - 500 mL boluses guided by stroke volume variation (SVV) or pulse pressure variation (PPV)",
            pediatricDosing = "Maintenance: 4-2-1 rule (4 mL/kg for first 10 kg, 2 mL/kg for next 10 kg, 1 mL/kg thereafter); Resuscitation: 10 - 20 mL/kg bolus",
            obstetricDosing = "500 - 1000 mL co-load at time of spinal anesthesia placement for Cesarean delivery",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Infusion",
                    indication = "Perioperative Fluid Management & Resuscitation",
                    adultDose = "Titrated to hemodynamics (250 - 500 mL boluses; baseline 1 - 2 mL/kg/hr)",
                    unitNotes = "DO NOT CO-INFUSE WITH PACKED RED BLOOD CELLS (PRBCs)! Contains Calcium (2.7 mEq/L), which binds citrate anticoagulant in blood bags and causes clot formation inside IV tubing!"
                )
            ),
            formulationsAndRoutes = listOf("500 mL and 1000 mL sterile IV infusion bags"),
            pharmacokinetics = "Redistributes rapidly throughout the entire extracellular fluid compartment. Intravascular volume expansion ratio is ~1:3 to 1:4 (3-4 mL crystalloid required for every 1 mL of blood loss). Lactate is metabolized by the liver to bicarbonate within 1-2 hours.",
            onset = "Immediate",
            peakEffect = "End of infusion",
            duration = "Intravascular half-life ~30-45 minutes",
            elimination = "Renal excretion; hepatic conversion of lactate to bicarbonate",
            contraindications = listOf(
                "Co-infusion in the SAME IV line with Packed Red Blood Cells (PRBCs) - calcium triggers clotting in tubing!",
                "Severe traumatic brain injury (TBI) with cerebral edema (slightly hypotonic: osmolarity 273 mOsm/L, can increase ICP; 0.9% NaCl preferred in neurotrauma)",
                "Severe end-stage lactic acidosis with fulminant liver failure (liver cannot metabolize lactate)"
            ),
            cautions = listOf(
                "Slightly hypotonic compared to plasma (273 mOsm/L): avoid large volumes in acute head trauma/cerebral edema",
                "Contains potassium (4 mEq/L): use caution in severe hyperkalemia (though far less acidifying than 0.9% Normal Saline)",
                "Excessive fluid overload causes pulmonary edema, bowel edema, and impaired wound healing"
            ),
            adverseReactions = listOf("Peripheral and pulmonary edema with fluid overload", "Hypoosmolality in neurosurgical patients", "Clotting in IV line if mixed with PRBCs"),
            interactions = listOf("NEVER CO-ADMINISTER IN SAME IV LINE WITH CITRATED BLOOD PRODUCTS (PRBC, FFP, platelets) - calcium binds citrate and precipitates clot!"),
            renalAdjustment = "Preferred over 0.9% Normal Saline in renal surgery (does NOT cause hyperchloremic metabolic acidosis or renal vasoconstriction).",
            hepaticAdjustment = "In severe end-stage cirrhosis, lactate conversion to bicarbonate may be delayed.",
            pregnancyAndLactation = "First-line fluid for labor and cesarean section co-loading.",
            elderlyConsiderations = "Risk of diastolic heart failure and pulmonary edema; use conservative fluid boluses.",
            anesthesiaConsiderations = "Far superior to 0.9% Normal Saline for routine surgery because it prevents hyperchloremic metabolic acidosis and renal vasoconstriction. STRICTLY FORBIDDEN to run in the same tubing as citrated blood products.",
            reversalAgent = "Diuretics (Furosemide) for volume overload.",
            toxicityManagement = "Treat volume overload with loop diuretics (Furosemide 20-40 mg IV) and fluid restriction.",
            clinicalPearls = "First-line balanced crystalloid. Far safer than 0.9% Saline (avoids hyperchloremic acidosis). NEVER run through the same IV tubing as PRBCs (calcium in LR clots the blood).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy", "guide_hip_fracture"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        Drug(
            id = "drug_tranexamic_acid",
            genericName = "Tranexamic Acid (TXA)",
            brandNames = "Cyklokapron, Lysteda",
            synonyms = listOf("Cyklokapron", "TXA", "Trans-4-(aminomethyl)cyclohexanecarboxylic acid"),
            category = DrugCategory.FLUIDS_ELECTROLYTES_BLOOD,
            drugClass = "Antifibrinolytic Hemostatic Agent / Synthetic Lysine Analog",
            mechanismOfAction = "Competitively binds to the lysine-binding sites of plasminogen and plasmin, preventing plasminogen activation and blocking plasmin from binding to and degrading fibrin clots. Stabilizes pre-formed fibrin hemostatic plugs and prevents premature fibrinolysis.",
            targetReceptor = "Plasminogen and plasmin lysine-binding sites",
            indications = listOf(
                "Postpartum hemorrhage (WHO WOMAN Trial: reduces maternal death by 30% when given within 3 hours)",
                "Trauma-induced coagulopathy and massive bleeding (CRASH-2 Trial: reduces all-cause mortality when given within 3 hours)",
                "Perioperative blood conservation in major orthopedic (total hip/knee arthroplasty), spine, and cardiac surgery"
            ),
            onsetAndDuration = "Onset: 5 - 15 minutes IV; Peak: 15 minutes; Duration: 3 - 4 hours",
            adultDosing = "Postpartum Hemorrhage / Trauma: 1.0 g IV infused over 10 minutes within 3 hours of bleeding onset; repeat second 1.0 g IV dose if bleeding continues after 30 minutes (or if bleeding restarts within 24 hours)\nOrthopedic Arthroplasty: 10 - 15 mg/kg IV (or 1 g IV) prior to surgical incision, optionally repeated at closure",
            pediatricDosing = "10 - 20 mg/kg IV over 10 minutes (Max single dose: 1.0 g)",
            obstetricDosing = "WHO Protocol for PPH: 1.0 g IV in 100 mL NS over 10 minutes administered immediately upon diagnosis of PPH within 3 hours of delivery; repeat 1.0 g if bleeding persists at 30 min",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Infusion",
                    indication = "Postpartum Hemorrhage / Trauma Resuscitation",
                    adultDose = "1.0 g IV infused over 10 minutes within 3 hours of onset (repeat 1.0 g at 30 min if needed)",
                    pediatricDose = "10 - 20 mg/kg IV over 10 min",
                    elderlyDose = "1.0 g IV over 10 min",
                    maxDose = "2.0 g total in acute PPH / trauma setting",
                    unitNotes = "MUST BE GIVEN WITHIN 3 HOURS OF BLEEDING ONSET! In both CRASH-2 and WOMAN trials, administration after 3 hours provided zero benefit and increased thrombotic risk!"
                )
            ),
            formulationsAndRoutes = listOf("100 mg/mL (10 mL vials = 1000 mg / 1 g)"),
            pharmacokinetics = "Low plasma protein binding (~3%). Minimal hepatic metabolism. 95% excreted completely unchanged by glomerular filtration in urine. Elimination half-life ~2 hours.",
            onset = "5 - 15 minutes",
            peakEffect = "15 minutes",
            duration = "3 - 4 hours",
            elimination = "Exclusively renal (> 95% excreted unchanged in urine)",
            contraindications = listOf(
                "Active thromboembolic disease (active DVT, PE, acute ischemic stroke, active arterial thrombosis)",
                "Acquired defective color vision (cannot track toxicity)",
                "Subarachnoid hemorrhage (risk of cerebral vasospasm and secondary cerebral infarction)",
                "Severe hypersensitivity"
            ),
            cautions = listOf(
                "SEIZURES WITH HIGH DOSES OR RAPID BOLUS: competitive antagonism of inhibitory GABA-A and glycine receptors in the brain triggers generalized tonic-clonic seizures (avoid high cardiac doses > 50 mg/kg; always infuse over >= 10 minutes)",
                "Time window strictly <= 3 hours: mortality benefit is lost and harm increases if given > 3 hours after bleeding onset",
                "Renal dose reduction: accumulates in renal failure; reduce dose in elevated serum creatinine"
            ),
            adverseReactions = listOf("Seizures (high-dose or rapid injection)", "Hypotension if infused rapidly (< 10 minutes)", "Gastrointestinal upset (nausea, diarrhea)", "Thrombosis (rare at therapeutic doses)"),
            interactions = listOf("Additive thrombogenic risk with prothrombin complex concentrates (PCC) or factor VIIa"),
            renalAdjustment = "Dose reduction mandatory in renal impairment: CrCl 50-80: 10 mg/kg q12h; CrCl 10-50: 10 mg/kg q24h; CrCl < 10: 5 mg/kg q24h.",
            hepaticAdjustment = "Safe; zero hepatic metabolism.",
            pregnancyAndLactation = "WHO Essential Medicine for maternal health: reduces maternal mortality from postpartum hemorrhage by 30% without increasing thromboembolism.",
            elderlyConsiderations = "Safe in elderly; calculate creatinine clearance and adjust maintenance doses.",
            anesthesiaConsiderations = "One of the most impactful life-saving medications in trauma and obstetrics. Standard of care for total joint arthroplasty (reduces transfusion rate by > 60%). Always infuse over at least 10 minutes to avoid hypotension.",
            reversalAgent = "N/A",
            toxicityManagement = "Treat seizures with Benzodiazepines (Midazolam 2-5 mg IV) or Propofol. Support airway and oxygenation.",
            clinicalPearls = "Reduces maternal mortality by 30% in postpartum hemorrhage (WHO WOMAN trial). MUST be given within 3 hours of bleeding onset. Infuse over 10 minutes to prevent hypotension and seizures.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("WHO Recommendations for the Prevention and Treatment of Postpartum Hemorrhage", "2022", "WHO Guidelines"),
                DrugReference("The WOMAN Trial Collaborators", "2017", "Lancet"),
                DrugReference("CRASH-2 Trial Collaborators", "2010", "Lancet")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy", "guide_hip_fracture"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        )
    )
}
