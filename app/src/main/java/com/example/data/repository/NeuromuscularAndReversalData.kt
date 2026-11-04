package com.example.data.repository

import com.example.data.model.Drug
import com.example.data.model.DrugCategory
import com.example.data.model.DrugDosing
import com.example.data.model.DrugReference

object NeuromuscularAndReversalData {

    val drugs: List<Drug> = listOf(
        // NEUROMUSCULAR BLOCKING AGENTS & REVERSAL
        Drug(
            id = "drug_succinylcholine",
            genericName = "Succinylcholine Chloride (Suxamethonium)",
            brandNames = "Anectine, Quelicin",
            synonyms = listOf("Suxamethonium", "Anectine", "Quelicin", "Diacetylcholine"),
            category = DrugCategory.NEUROMUSCULAR_BLOCKERS_REVERSAL,
            drugClass = "Depolarizing Neuromuscular Blocker / Bis-quaternary Ammonium Compound",
            mechanismOfAction = "Mimics acetylcholine by binding to nicotinic ACh receptors (alpha-1 subunits) at the motor endplate, causing prolonged depolarization. This produces initial muscle fasciculations followed by flaccid paralysis because voltage-gated sodium channels in the perijunctional membrane remain in an inactivated state.",
            targetReceptor = "Nicotinic acetylcholine receptor (nAChR)",
            indications = listOf(
                "Rapid Sequence Induction (RSI) for emergency airway management / full stomach",
                "Emergency airway rescue requiring immediate profound flaccid paralysis",
                "Laryngospasm management in adults and pediatrics",
                "Short procedures requiring profound brief paralysis (e.g. electroconvulsive therapy - ECT)"
            ),
            onsetAndDuration = "Onset: 30 - 60 seconds (fastest of all paralytics)\nDuration: 5 - 10 minutes (ultrashort)",
            adultDosing = "Intubation: 1.0 - 1.5 mg/kg IV (dosed on Total Body Weight - TBW!)\nLaryngospasm rescue: 0.1 - 0.5 mg/kg IV or 3.0 - 4.0 mg/kg IM (with atropine in children)\nECT: 0.5 - 1.0 mg/kg IV",
            pediatricDosing = "Infants < 1 yr: 2.0 - 3.0 mg/kg IV (higher volume of extracellular fluid; ALWAYS co-administer Atropine 0.02 mg/kg to prevent severe sinus bradycardia/asystole); Children: 1.0 - 1.5 mg/kg IV; IM rescue: 3.0 - 4.0 mg/kg IM",
            obstetricDosing = "1.0 - 1.5 mg/kg IV (TBW) for crash cesarean general anesthesia induction; minimal placental transfer due to high ionization",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Emergency RSI Intubation",
                    adultDose = "1.0 - 1.5 mg/kg IV",
                    pediatricDose = "1.5 - 2.0 mg/kg IV (children); 2.0 - 3.0 mg/kg IV (infants with atropine)",
                    elderlyDose = "1.0 mg/kg IV",
                    maxDose = "150 - 200 mg single bolus",
                    unitNotes = "MUST DOSE ON TOTAL BODY WEIGHT (TBW) in obese patients to ensure adequate intubating conditions!"
                ),
                DrugDosing(
                    route = "IV / IM",
                    indication = "Severe Laryngospasm Rescue",
                    adultDose = "0.1 - 0.5 mg/kg IV (10 - 40 mg) or 3 - 4 mg/kg IM in deltoid/tongue",
                    pediatricDose = "0.5 mg/kg IV or 4 mg/kg IM (with Atropine 0.02 mg/kg)"
                )
            ),
            formulationsAndRoutes = listOf("20 mg/mL multi-dose vials (10 mL); must be refrigerated (stable at room temp for up to 14 days)"),
            pharmacokinetics = "Rapidly hydrolyzed in plasma by pseudocholinesterase (butyrylcholinesterase / plasma cholinesterase) to succinylmonocholine (very weak) and choline. Only a small fraction reaches the motor endplate. Elimination half-life ~1 minute.",
            onset = "30 - 60 seconds",
            peakEffect = "1 - 2 minutes",
            duration = "5 - 10 minutes",
            elimination = "Plasma pseudocholinesterase (butyrylcholinesterase) hydrolysis",
            contraindications = listOf(
                "Personal or family history of Malignant Hyperthermia (potent trigger!)",
                "Denervation injury > 48-72 hours (stroke, spinal cord transection, ALS, paraplegia/quadriplegia)",
                "Major burn injury > 24-48 hours",
                "Severe pre-existing hyperkalemia (serum K+ > 5.5 mEq/L)",
                "Muscular dystrophies (e.g. Duchenne / Becker muscular dystrophy - risk of fatal hyperkalemic cardiac arrest)",
                "Severe intra-abdominal sepsis / crush injury > 24-48 hours",
                "Known atypical or deficient pseudocholinesterase (homozygous atypical gene)"
            ),
            cautions = listOf(
                "Hyperkalemia: normal transient serum potassium rise of 0.5 - 1.0 mEq/L in healthy individuals; catastrophic, life-threatening hyperkalemia (up to 8-10 mEq/L) occurs in up-regulated extrajunctional receptor states (burns, denervation, sepsis)",
                "Sinus bradycardia, nodal rhythms, and asystole, especially after a second dose in adults or after a single dose in pediatrics (pretreat with atropine)",
                "Transiently increases intragastric pressure, intraocular pressure (5-10 mmHg), and intracranial pressure",
                "Severe post-operative myalgias and muscle stiffness (mitigated by defasciculating dose of rocuronium 0.03 mg/kg)",
                "Phase II block (dual block) after prolonged infusions or large cumulative doses (> 4-5 mg/kg)"
            ),
            adverseReactions = listOf("Fatal hyperkalemic cardiac arrest", "Malignant Hyperthermia trigger", "Severe bradycardia / asystole", "Masseter muscle spasm", "Fasciculations & severe myalgias", "Elevated IOP / ICP"),
            interactions = listOf("Anticholinesterases (neostigmine, organophosphates) inhibit pseudocholinesterase and significantly PROLONG succinylcholine duration!"),
            renalAdjustment = "Safe in chronic stable renal failure if baseline serum potassium is strictly <= 5.0 mEq/L. Contraindicated if hyperkalemic.",
            hepaticAdjustment = "Severe cirrhosis reduces pseudocholinesterase synthesis; may mildly prolong duration by 5-10 minutes.",
            pregnancyAndLactation = "Safe in crash cesarean delivery (does not cross placenta in significant amounts); pseudocholinesterase levels decrease by ~25% in pregnancy.",
            elderlyConsiderations = "Safe in elderly; watch for bradycardia.",
            anesthesiaConsiderations = "The historical gold standard for crash RSI due to incomparable speed (30-60 sec) and short duration (5-10 min). If prolonged apnea occurs (> 15 min), continue mechanical ventilation and order Dibucaine Number test to evaluate for atypical pseudocholinesterase.",
            reversalAgent = "NO direct chemical reversal agent. Anticholinesterases (neostigmine) worsen and prolong blockade. Time and controlled mechanical ventilation are required.",
            toxicityManagement = "Hyperkalemic arrest: Calcium chloride 1 g IV immediately to stabilize myocardium, followed by 10 units regular insulin + 50 mL 50% Dextrose, hyperventilation, sodium bicarbonate, and dialysis. Malignant Hyperthermia: Dantrolene 2.5 mg/kg IV.",
            clinicalPearls = "Dosed on TOTAL body weight. Never give neostigmine to reverse succinylcholine (it prolongs the block). If patient does not breathe after 15 min, suspect pseudocholinesterase deficiency.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("MHAUS Guidelines on Malignant Hyperthermia Triggers", "2023", "MHAUS")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "Nicotinic AChR Depolarizing Phase I/II Block",
            diagramType = "NMJ_NICOTINIC_ACHR",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_rocuronium",
            genericName = "Rocuronium Bromide",
            brandNames = "Zemuron, Esmeron",
            synonyms = listOf("Zemuron", "Esmeron", "Org 9426"),
            category = DrugCategory.NEUROMUSCULAR_BLOCKERS_REVERSAL,
            drugClass = "Intermediate-acting Aminosteroid Non-depolarizing Neuromuscular Blocker",
            mechanismOfAction = "Competitive antagonist at postjunctional nicotinic acetylcholine receptors at the motor endplate, preventing acetylcholine binding and channel opening.",
            targetReceptor = "Nicotinic acetylcholine receptor (alpha-1 subunit)",
            indications = listOf(
                "Endotracheal intubation (routine and modified RSI)",
                "Maintenance of surgical skeletal muscle relaxation",
                "Facilitation of mechanical ventilation in critical care"
            ),
            onsetAndDuration = "Standard dose (0.6 mg/kg): Onset 60 - 90 seconds, Duration 30 - 45 minutes\nRSI dose (1.0 - 1.2 mg/kg): Onset 45 - 60 seconds, Duration 60 - 90 minutes",
            adultDosing = "Standard Intubation: 0.6 mg/kg IV (2x ED95)\nRSI Intubation: 1.0 - 1.2 mg/kg IV (dosed on Ideal Body Weight)\nMaintenance Bolus: 0.1 - 0.15 mg/kg IV\nContinuous Infusion: 5 - 12 mcg/kg/min IV",
            pediatricDosing = "0.6 - 1.0 mg/kg IV (similar fast onset; slightly longer recovery in neonates and infants)",
            obstetricDosing = "1.0 - 1.2 mg/kg IV for rapid sequence induction in cesarean delivery when succinylcholine is contraindicated; immediate reversal with Sugammadex 16 mg/kg available",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Standard Intubation",
                    adultDose = "0.6 mg/kg IV",
                    pediatricDose = "0.6 mg/kg IV",
                    elderlyDose = "0.6 mg/kg IV (duration prolonged)",
                    maxDose = "50 - 70 mg standard bolus",
                    unitNotes = "Ideal Body Weight (IBW) in obesity"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Rapid Sequence Induction (RSI)",
                    adultDose = "1.0 - 1.2 mg/kg IV (provides intubating conditions in 45-60 seconds, comparable to succinylcholine)",
                    unitNotes = "Duration is significantly prolonged (60 - 90 minutes). Ensure Sugammadex is available for rescue reversal"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Maintenance Relaxation",
                    adultDose = "0.1 - 0.15 mg/kg IV guided by Train-of-Four (TOF) monitoring"
                )
            ),
            formulationsAndRoutes = listOf("10 mg/mL vials (5 mL, 10 mL); stable at room temp (up to 25°C) for 60 days"),
            pharmacokinetics = "Hepatic uptake and biliary excretion (~70%), renal elimination (~30%). Minimal hepatic metabolism (< 1% to 17-desacetylrocuronium).",
            onset = "45 - 90 seconds (dose-dependent)",
            peakEffect = "1.5 - 2 minutes",
            duration = "30 - 45 minutes (0.6 mg/kg); 60 - 90 minutes (1.2 mg/kg)",
            elimination = "Primarily biliary and hepatic excretion (70%); renal excretion (30%)",
            contraindications = listOf("Known severe hypersensitivity to rocuronium or aminosteroid neuromuscular blocking agents"),
            cautions = listOf(
                "Prolonged duration in severe hepatic cirrhosis or biliary obstruction",
                "Prolonged duration in severe renal failure",
                "Does NOT cause fasciculations or hyperkalemia",
                "Does NOT trigger Malignant Hyperthermia",
                "No histamine release at therapeutic clinical doses"
            ),
            adverseReactions = listOf("Anaphylaxis / anaphylactoid reaction (most common NMBA allergen)", "Prolonged neuromuscular blockade"),
            interactions = listOf("Volatile anesthetics potentiate blockade by 30-50%", "Aminoglycosides and magnesium enhance blockade"),
            renalAdjustment = "Duration prolonged by ~20-30% in renal failure due to 30% renal excretion.",
            hepaticAdjustment = "Significantly prolonged duration in severe cirrhosis (decreased biliary excretion and larger Vd); use TOF monitoring.",
            pregnancyAndLactation = "Safe for cesarean RSI (negligible placental transfer); preferred alternative to succinylcholine in obstetric emergencies.",
            elderlyConsiderations = "Duration prolonged by 15-30 minutes due to decreased liver/kidney perfusion; titrate maintenance doses carefully.",
            anesthesiaConsiderations = "The primary non-depolarizing agent for RSI. At 1.2 mg/kg, onset speed rivals succinylcholine. Specific reversal with Sugammadex allows instant rescue reversal even after an RSI dose.",
            reversalAgent = "Sugammadex (specific encapsulating cyclodextrin) OR Neostigmine with Glycopyrrolate.",
            toxicityManagement = "Sugammadex: 2 mg/kg for moderate block (T2 reappearance), 4 mg/kg for deep block (1-2 PTC), 16 mg/kg for immediate emergency reversal after 1.2 mg/kg rocuronium. If sugammadex unavailable, support ventilation until spontaneous recovery to >= 2-4 twitches on TOF, then give Neostigmine (0.05 mg/kg) + Glycopyrrolate (0.01 mg/kg).",
            clinicalPearls = "Premier non-depolarizing RSI agent. Dose 1.0-1.2 mg/kg for fast 45-second intubating conditions. Dose on IDEAL body weight. Can be instantly reversed with Sugammadex 16 mg/kg.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "Competitive Nicotinic Antagonism & Sugammadex Encapsulation",
            diagramType = "NMJ_NICOTINIC_ACHR",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_cisatracurium",
            genericName = "Cisatracurium Besylate",
            brandNames = "Nimbex",
            synonyms = listOf("Nimbex", "1R-cis,1'R-cis isomer of atracurium"),
            category = DrugCategory.NEUROMUSCULAR_BLOCKERS_REVERSAL,
            drugClass = "Intermediate-acting Benzylisoquinolinium Non-depolarizing Neuromuscular Blocker",
            mechanismOfAction = "Competitive antagonist at postjunctional nicotinic acetylcholine receptors at the neuromuscular junction.",
            targetReceptor = "Nicotinic acetylcholine receptor (alpha-1 subunit)",
            indications = listOf(
                "Skeletal muscle relaxation during surgery in patients with severe renal failure or hepatic failure",
                "Continuous infusion for ARDS (Acute Respiratory Distress Syndrome) in ICU",
                "Patients susceptible to laudanosine toxicity"
            ),
            onsetAndDuration = "Onset: 2 - 3 minutes (slower than rocuronium)\nDuration: 35 - 50 minutes (completely independent of renal or liver function!)",
            adultDosing = "Intubation: 0.15 - 0.2 mg/kg IV (3x ED95)\nMaintenance: 0.03 mg/kg IV bolus\nContinuous Infusion: 1.0 - 2.0 mcg/kg/min IV",
            pediatricDosing = "0.15 mg/kg IV (infants 1-23 months have slightly faster recovery)",
            obstetricDosing = "0.15 mg/kg IV; organ-independent clearance makes it safe in preeclampsia with renal/hepatic impairment",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Intubation / Surgical Relaxation",
                    adultDose = "0.15 - 0.2 mg/kg IV",
                    elderlyDose = "0.15 mg/kg IV",
                    unitNotes = "Total Body Weight (TBW)"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "ICU ARDS / Prolonged OR Relaxation",
                    adultDose = "1.0 - 2.0 mcg/kg/min IV (titrated to TOF 1-2 twitches)",
                    unitNotes = "Zero organ accumulation; identical recovery profile regardless of duration"
                )
            ),
            formulationsAndRoutes = listOf("2 mg/mL (5 mL, 10 mL vials)", "10 mg/mL (20 mL concentrated vial for ICU infusion)"),
            pharmacokinetics = "Organ-independent clearance: 77% cleared by spontaneous non-enzymatic Hofmann elimination (chemical degradation at physiologic body pH and temperature) and 23% by non-specific plasma esterases. Does NOT depend on renal or hepatic clearance.",
            onset = "2 - 3 minutes",
            peakEffect = "3 - 5 minutes",
            duration = "35 - 50 minutes",
            elimination = "Spontaneous non-enzymatic Hofmann elimination and non-specific esterases",
            contraindications = listOf("Known severe hypersensitivity to cisatracurium or benzylisoquinolinium compounds"),
            cautions = listOf(
                "Slower onset (2-3 min); NOT suitable for crash RSI",
                "Hypothermia (core temp < 35°C) and acidosis (pH < 7.25) slow Hofmann elimination and prolong duration",
                "Hyperthermia and alkalosis accelerate elimination and shorten duration",
                "Cannot be reversed by Sugammadex (Sugammadex only binds aminosteroids like rocuronium/vecuronium!)"
            ),
            adverseReactions = listOf("No clinically significant histamine release at up to 8x ED95 (unlike parent drug atracurium)"),
            interactions = listOf("Volatile anesthetics potentiate neuromuscular blockade"),
            renalAdjustment = "ZERO dose adjustment required! Premier neuromuscular blocker in end-stage renal disease / dialysis patients.",
            hepaticAdjustment = "ZERO dose adjustment required! Premier neuromuscular blocker in end-stage cirrhosis / fulminant hepatic failure.",
            pregnancyAndLactation = "Safe; organ-independent elimination ideal in preeclamptic toxemia with multiorgan dysfunction.",
            elderlyConsiderations = "No alteration in elimination pharmacokinetics; safe in frail elderly.",
            anesthesiaConsiderations = "The undisputed drug of choice for skeletal muscle relaxation in severe renal or hepatic failure, and prolonged ARDS infusions. CANNOT BE REVERSED WITH SUGAMMADEX (requires Neostigmine + Glycopyrrolate).",
            reversalAgent = "Neostigmine (0.04 - 0.07 mg/kg) + Glycopyrrolate (0.01 mg/kg) IV when spontaneous recovery is present (>= 2-4 twitches on TOF). Sugammadex is completely ineffective!",
            toxicityManagement = "Controlled ventilation. Wait for spontaneous recovery of >= 2 twitches on TOF, then administer Neostigmine 0.05 mg/kg + Glycopyrrolate 0.01 mg/kg. Sugammadex does NOT bind benzylisoquinolines.",
            clinicalPearls = "Organ-independent Hofmann elimination (cleared at body pH and temperature). Premier choice in renal/hepatic failure. SUGAMMADEX DOES NOT WORK (must use Neostigmine).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_sugammadex",
            genericName = "Sugammadex Sodium",
            brandNames = "Bridion",
            synonyms = listOf("Bridion", "Modified gamma-cyclodextrin", "Org 25969"),
            category = DrugCategory.NEUROMUSCULAR_BLOCKERS_REVERSAL,
            drugClass = "Selective Relaxant Binding Agent (SRBA) / Modified Gamma-Cyclodextrin",
            mechanismOfAction = "Selectively encapsulates free rocuronium (and vecuronium) molecules in plasma in a 1:1 tight molecular inclusion complex, dropping free intravascular concentration to zero and creating a steep concentration gradient that rapidly pulls rocuronium molecules off motor endplate nicotinic receptors.",
            targetReceptor = "Free Rocuronium and Vecuronium molecules in plasma",
            indications = listOf(
                "Routine reversal of moderate neuromuscular blockade (reappearance of T2 on Train-of-Four)",
                "Reversal of deep neuromuscular blockade (1-2 post-tetanic counts, 0 twitches on TOF)",
                "Immediate emergency reversal of high-dose rocuronium (1.2 mg/kg) in a 'Cannot Intubate, Cannot Oxygenate' (CICO) crisis"
            ),
            onsetAndDuration = "Onset: < 2 - 3 minutes to Train-of-Four (TOF) ratio > 0.9 (full recovery)",
            adultDosing = "Moderate Block (reappearance of T2): 2.0 mg/kg IV\nDeep Block (1 - 2 Post-Tetanic Counts, 0 twitches on TOF): 4.0 mg/kg IV\nImmediate Emergency Reversal (after 1.2 mg/kg rocuronium): 16.0 mg/kg IV (dosed on Total Body Weight - TBW!)",
            pediatricDosing = "Children >= 2 years: 2.0 mg/kg IV for moderate block (T2 reappearance)",
            obstetricDosing = "Safe and widely used post-cesarean delivery (2 - 4 mg/kg IV); binds progesterone (theoretical minor effect on hormonal contraception)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Moderate Block Reversal (T2 visible)",
                    adultDose = "2.0 mg/kg IV",
                    pediatricDose = "2.0 mg/kg IV (>= 2 years)",
                    unitNotes = "Total Body Weight (TBW)"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Deep Block Reversal (PTC 1-2, TOF 0)",
                    adultDose = "4.0 mg/kg IV",
                    unitNotes = "Total Body Weight (TBW)"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Immediate CICO Rescue (3 min post-1.2 mg/kg rocuronium)",
                    adultDose = "16.0 mg/kg IV single rapid bolus",
                    unitNotes = "Restores neuromuscular function and spontaneous ventilation in under 3 minutes!"
                )
            ),
            formulationsAndRoutes = listOf("100 mg/mL solution (2 mL and 5 mL vials)"),
            pharmacokinetics = "Excreted completely unchanged by the kidneys (glomerular filtration); biologically inert complex. Half-life ~2 hours in normal renal function.",
            onset = "< 2 - 3 minutes",
            peakEffect = "2 - 3 minutes",
            duration = "Permanent encapsulation (does not dissociate in body)",
            elimination = "Exclusively renal excretion of unchanged sugammadex and sugammadex-rocuronium complex",
            contraindications = listOf(
                "Known severe hypersensitivity to sugammadex",
                "Severe renal impairment (CrCl < 30 mL/min) or patients on hemodialysis (complex cannot be renally excreted; use is not FDA recommended)"
            ),
            cautions = listOf(
                "Hormonal Contraceptive Binding: binds and inactivates hormonal oral contraceptives equivalent to missing one daily pill. Counsel female patients to use additional non-hormonal (barrier) contraception for the next 7 days",
                "Marked bradycardia and rare cardiac arrest: monitor heart rate closely; have atropine immediately available",
                "Transient prolongation of coagulation parameters (PT/INR and aPTT increased by ~25% for 30-60 min; rarely causes clinical bleeding)",
                "Re-curarization if under-dosed (always match dose to objective neuromuscular monitoring)",
                "If re-paralysis is needed after sugammadex: use benzylisoquinolines (cisatracurium) or succinylcholine"
            ),
            adverseReactions = listOf("Marked bradycardia / cardiac arrest", "Anaphylaxis / hypersensitivity (up to 0.3%)", "Transient metallic taste (dysgeusia)", "Inactivation of oral contraceptives"),
            interactions = listOf("Inactivates oral contraceptives (barrier method for 7 days)", "Displaced by flucloxacillin or toremifene at high concentrations"),
            renalAdjustment = "Not recommended by FDA in severe renal impairment (CrCl < 30 mL/min) or dialysis due to prolonged retention of complex.",
            hepaticAdjustment = "Safe in liver disease; elimination is independent of hepatic metabolism.",
            pregnancyAndLactation = "Crosses placenta in minimal amounts; safe post-delivery; compatible with breastfeeding.",
            elderlyConsiderations = "Safe in elderly; recovery may take ~1 minute longer due to slower circulation time.",
            anesthesiaConsiderations = "Revolutionized neuromuscular reversal: completely bypasses cholinergic biology, eliminating all muscarinic side effects of neostigmine (no bradycardia, bronchospasm, or salivation). Reverses deep blocks that neostigmine cannot touch.",
            reversalAgent = "N/A",
            toxicityManagement = "Treat bradycardia with Atropine 0.5 - 1.0 mg IV. If re-curarization occurs due to under-dosing, administer additional sugammadex (2-4 mg/kg IV).",
            clinicalPearls = "Dosed on TOTAL body weight. Patient MUST be counseled to use backup barrier contraception for 7 days. Only reverses rocuronium/vecuronium (does NOT reverse cisatracurium).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Bridion (Sugammadex) FDA Prescribing Information", "FDA Approved", "Merck")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        Drug(
            id = "drug_neostigmine",
            genericName = "Neostigmine Methylsulfate",
            brandNames = "Prostigmin, Bloxiverz",
            synonyms = listOf("Prostigmin", "Bloxiverz", "Neostigmine"),
            category = DrugCategory.NEUROMUSCULAR_BLOCKERS_REVERSAL,
            drugClass = "Quaternary Ammonium Anticholinesterase / Acetylcholinesterase Inhibitor",
            mechanismOfAction = "Reversibly carbamylates the esteratic site of acetylcholinesterase, inhibiting the breakdown of endogenous acetylcholine at the synaptic cleft. The resulting surge in acetylcholine outcompetes non-depolarizing relaxants (rocuronium, cisatracurium) at postjunctional nicotinic receptors.",
            targetReceptor = "Acetylcholinesterase enzyme",
            indications = listOf(
                "Reversal of residual non-depolarizing neuromuscular blockade (rocuronium, vecuronium, cisatracurium) when spontaneous recovery has already begun (minimum 2 to 4 twitches on TOF)",
                "Myasthenia gravis treatment",
                "Ogilvie's syndrome (acute colonic pseudo-obstruction)"
            ),
            onsetAndDuration = "Onset: 3 - 5 minutes; Peak: 7 - 10 minutes; Duration: 60 - 90 minutes",
            adultDosing = "0.03 - 0.07 mg/kg IV (Max Dose: 5.0 mg or 70 mcg/kg). MUST ALWAYS be co-administered with Glycopyrrolate (0.2 mg per 1 mg neostigmine) or Atropine (0.4 - 0.6 mg per 1 mg neostigmine) to block muscarinic toxicity!",
            pediatricDosing = "0.03 - 0.05 mg/kg IV with Glycopyrrolate 0.01 mg/kg",
            obstetricDosing = "0.04 - 0.05 mg/kg IV with Glycopyrrolate 0.01 mg/kg (glycopyrrolate does not cross placenta, preventing fetal tachycardia)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Reversal of Non-depolarizing Blockade (>= 2-4 TOF twitches)",
                    adultDose = "0.03 - 0.07 mg/kg IV (typical 2.5 - 5.0 mg) with Glycopyrrolate 0.4 - 1.0 mg IV",
                    pediatricDose = "0.03 - 0.05 mg/kg IV with Glycopyrrolate 0.01 mg/kg",
                    maxDose = "5.0 mg (70 mcg/kg) total",
                    unitNotes = "NEVER give when TOF is 0 twitches! Ceiling effect: excess neostigmine causes depolarizing weakness and respiratory collapse"
                )
            ),
            formulationsAndRoutes = listOf("0.5 mg/mL (10 mL) and 1.0 mg/mL (10 mL) vials"),
            pharmacokinetics = "Quaternary ammonium compound (ionized; does not cross blood-brain barrier). 50% metabolized by hepatic microsomes, 50% excreted unchanged in urine. Elimination half-life ~60-90 min.",
            onset = "3 - 5 minutes",
            peakEffect = "7 - 10 minutes",
            duration = "60 - 90 minutes",
            elimination = "50% renal excretion unchanged; 50% hepatic metabolism",
            contraindications = listOf(
                "Mechanical intestinal or urinary obstruction",
                "Known hypersensitivity",
                "Zero twitches on TOF (complete block - neostigmine CANNOT reverse deep block and will cause paradoxical weakness)"
            ),
            cautions = listOf(
                "Muscarinic Crisis: severe bradycardia, sinus arrest, bronchospasm, hypersecretion, miosis, nausea, abdominal cramping, and increased bowel motility",
                "MUST ALWAYS be paired with Glycopyrrolate (preferred; matched peak at 7-10 min) or Atropine",
                "Neostigmine-induced weakness: giving neostigmine when patient has already fully recovered (TOF ratio > 0.9) causes paradoxical muscle weakness and upper airway collapse",
                "Ceiling effect: doses > 5 mg produce no additional reversal and worsen cholinergic weakness"
            ),
            adverseReactions = listOf("Severe bradycardia / asystole", "Bronchospasm / copious airway secretions", "Nausea and vomiting", "Abdominal cramping", "Paradoxical muscle weakness"),
            interactions = listOf("Prolongs succinylcholine duration by inhibiting plasma pseudocholinesterase"),
            renalAdjustment = "Half-life prolonged in renal failure; fortunately, NMBA half-lives are also prolonged, preventing recurarization.",
            hepaticAdjustment = "Safe; mild prolongation.",
            pregnancyAndLactation = "Does not cross placenta significantly (quaternary amine); pair with Glycopyrrolate.",
            elderlyConsiderations = "Prolonged elimination half-life; monitor for postop residual block.",
            anesthesiaConsiderations = "Always verify objective TOF recovery (minimum 2, ideally 4 twitches) before giving neostigmine. Glycopyrrolate (0.2 mg per 1 mg neostigmine) matches neostigmine's 7-10 min peak effect far better than atropine.",
            reversalAgent = "Atropine or Glycopyrrolate for muscarinic cholinergic crisis.",
            toxicityManagement = "Cholinergic Crisis (SLUDGE syndrome): Administer Atropine 1 - 2 mg IV every 3-5 minutes until muscarinic symptoms (bronchospasm, bradycardia, secretions) resolve. Support airway and ventilation.",
            clinicalPearls = "Never give if 0 twitches on TOF. Always administer with glycopyrrolate (0.2 mg glyco per 1 mg neostigmine). Neostigmine peak is 7-10 minutes (give patient time to recover).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        // ANTICHOLINERGICS & ADJUNCTS
        Drug(
            id = "drug_glycopyrrolate",
            genericName = "Glycopyrrolate",
            brandNames = "Robinul",
            synonyms = listOf("Robinul", "Glycopyrronium bromide"),
            category = DrugCategory.ANTICHOLINERGICS_ADJUNCTS,
            drugClass = "Quaternary Ammonium Antimuscarinic / Anticholinergic",
            mechanismOfAction = "Competitive antagonist at muscarinic acetylcholine receptors (M1, M2, M3), blocking parasympathetic cholinergic transmission in cardiac, bronchial, and secretory tissues.",
            targetReceptor = "Muscarinic acetylcholine receptors (M1, M2, M3)",
            indications = listOf(
                "Co-administration with neostigmine to block muscarinic side effects during reversal",
                "Antisialagogue (reducing oral, pharyngeal, and tracheal secretions prior to awake fiberoptic intubation or ketamine)",
                "Treatment of vagally mediated intraoperative bradycardia (peritoneal traction, oculocardiac reflex)"
            ),
            onsetAndDuration = "Onset: 1 minute IV; Peak: 5 - 10 minutes IV; Duration: 2 - 4 hours (vagolytic); up to 7 hours (antisialagogue)",
            adultDosing = "Reversal with Neostigmine: 0.2 mg glycopyrrolate per 1.0 mg neostigmine IV (typical 0.4 - 1.0 mg)\nBradycardia: 0.1 - 0.2 mg IV bolus every 2-3 minutes\nAntisialagogue: 0.1 - 0.2 mg IV/IM 30 min prior to procedure",
            pediatricDosing = "0.005 - 0.01 mg/kg IV (0.01 mg/kg with neostigmine)",
            obstetricDosing = "0.1 - 0.2 mg IV; DOES NOT CROSS THE PLACENTA (quaternary amine), making it the premier antimuscarinic in obstetric patients when maternal bradycardia must be treated without causing fetal tachycardia",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Neuromuscular Reversal Companion",
                    adultDose = "0.2 mg per 1 mg neostigmine IV (e.g. 0.6 mg glycopyrrolate with 3.0 mg neostigmine)",
                    pediatricDose = "0.01 mg/kg IV with neostigmine",
                    unitNotes = "Matches onset and peak of neostigmine (7-10 minutes) perfectly"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Vagal Bradycardia Rescue",
                    adultDose = "0.1 - 0.2 mg IV every 2-3 minutes (Max 1.0 mg)"
                )
            ),
            formulationsAndRoutes = listOf("0.2 mg/mL (1 mL, 2 mL, 5 mL, 20 mL vials)"),
            pharmacokinetics = "Quaternary ammonium compound (fully ionized at physiologic pH); does NOT cross the blood-brain barrier or the placenta. ~80% excreted unchanged in urine.",
            onset = "1 minute",
            peakEffect = "5 - 10 minutes",
            duration = "2 - 4 hours (cardiac); 7 hours (antisialagogue)",
            elimination = "Renal excretion unchanged (> 80%)",
            contraindications = listOf(
                "Closed-angle glaucoma (relative caution; much safer than atropine)",
                "Mechanical gastrointestinal or urinary tract obstruction",
                "Severe tachyarrhythmias"
            ),
            cautions = listOf(
                "Tachycardia (less extreme than atropine, but still occurs)",
                "Dry mouth and thickened secretions",
                "Inability to sweat (hyperthermia risk in warm environments or pediatrics)"
            ),
            adverseReactions = listOf("Tachycardia", "Dry mouth", "Urinary retention", "Blurred vision / mild mydriasis"),
            interactions = listOf("Additive anticholinergic effects with antihistamines and tricyclic antidepressants"),
            renalAdjustment = "Safe; mild reduction in dose in severe renal failure.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Does NOT cross placenta (unlike atropine). Premier agent for obstetric reversal and bradycardia without causing fetal distress.",
            elderlyConsiderations = "Zero central anticholinergic syndrome risk because it does not cross the blood-brain barrier (avoids postop delirium).",
            anesthesiaConsiderations = "Key advantage over atropine: quaternary structure prevents crossing the blood-brain barrier (no central anticholinergic syndrome or delirium) and placenta (no fetal tachycardia). Its 5-10 min peak perfectly aligns with neostigmine.",
            reversalAgent = "Physostigmine (rarely needed since it lacks CNS effects).",
            toxicityManagement = "Supportive care. Beta-blockers (esmolol) if refractory dangerous tachycardia occurs.",
            clinicalPearls = "Does NOT cross blood-brain barrier (no delirium) or placenta (no fetal tachycardia). Perfectly matches neostigmine's 7-10 min peak (0.2 mg glyco per 1 mg neostigmine).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_atropine",
            genericName = "Atropine Sulfate",
            brandNames = "Atropine",
            synonyms = listOf("Atropine", "dl-hyoscyamine"),
            category = DrugCategory.ANTICHOLINERGICS_ADJUNCTS,
            drugClass = "Tertiary Amine Belladonna Alkaloid / Potent Antimuscarinic",
            mechanismOfAction = "Competitive antagonist at central and peripheral muscarinic acetylcholine receptors (M1 through M5), blocking vagal cholinergic tone at the sinoatrial (SA) node and AV node.",
            targetReceptor = "Muscarinic acetylcholine receptors (M1 - M5)",
            indications = listOf(
                "Acute hemodynamic instability due to severe bradycardia (ACLS first-line)",
                "Oculocardiac reflex bradycardia and peritoneal traction bradycardia",
                "Premedication in infants prior to succinylcholine to prevent profound bradycardia",
                "Antidote for organophosphate and nerve agent poisoning"
            ),
            onsetAndDuration = "Onset: < 1 minute IV; Peak: 2 - 4 minutes IV; Duration: 30 - 60 minutes (cardiac); up to 4 hours (secretory)",
            adultDosing = "Symptomatic Bradycardia (ACLS): 0.5 - 1.0 mg IV push every 3-5 minutes (Max: 3.0 mg total vagolytic dose)\nPediatric Pre-succinylcholine / Bradycardia: 0.02 mg/kg IV (Minimum dose: 0.1 mg; Max single dose: 0.5 mg in child, 1.0 mg in adolescent)",
            pediatricDosing = "0.02 mg/kg IV (Minimum 0.1 mg to prevent paradoxical central bradycardia; Max single dose: 0.5 mg)",
            obstetricDosing = "Crosses placenta rapidly; causes marked fetal tachycardia; use glycopyrrolate instead unless severe maternal arrest/hemodynamic collapse",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Symptomatic Bradycardia (ACLS)",
                    adultDose = "0.5 - 1.0 mg IV push every 3-5 minutes",
                    pediatricDose = "0.02 mg/kg IV (Minimum 0.1 mg; Max 0.5 mg)",
                    elderlyDose = "0.5 mg IV increments",
                    maxDose = "3.0 mg total (full vagolytic block)",
                    unitNotes = "NEVER give < 0.1 mg in pediatrics or < 0.4 mg in adults (low doses stimulate central medullary vagal nuclei, causing PARADOXICAL BRADYCARDIA!)"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Organophosphate / Cholinergic Poisoning",
                    adultDose = "2.0 - 4.0 mg IV every 5-10 minutes until secretions clear and bronchospasm resolves"
                )
            ),
            formulationsAndRoutes = listOf("0.4 mg/mL and 1.0 mg/mL vials; 1.0 mg / 10 mL prefilled emergency syringes (0.1 mg/mL)"),
            pharmacokinetics = "Tertiary amine; lipid-soluble; readily crosses the blood-brain barrier and the placenta. Hepatic metabolism (50%) and renal elimination unchanged (50%).",
            onset = "< 1 minute",
            peakEffect = "2 - 4 minutes",
            duration = "30 - 60 minutes",
            elimination = "50% hepatic metabolism; 50% renal excretion",
            contraindications = listOf(
                "Acute narrow-angle glaucoma (mydriasis and cycloplegia increase intraocular pressure dramatically)",
                "Tachycardia secondary to thyrotoxicosis or cardiac ischemia"
            ),
            cautions = listOf(
                "Paradoxical bradycardia with sub-therapeutic doses (< 0.4 mg in adult, < 0.1 mg in child) due to blockade of presynaptic inhibitory M1 receptors on postganglionic parasympathetic neurons",
                "Central Anticholinergic Syndrome (restlessness, hallucinations, delirium, coma) because tertiary structure crosses blood-brain barrier",
                "Crosses placenta causing rapid fetal tachycardia and loss of fetal heart rate variability",
                "Significantly increases myocardial oxygen consumption (caution in coronary artery disease)"
            ),
            adverseReactions = listOf("Tachycardia / ventricular arrhythmias", "Central Anticholinergic Syndrome (delirium)", "Mydriasis / photophobia", "Severe dry mouth", "Hyperthermia", "Urinary retention"),
            interactions = listOf("Additive anticholinergic toxicity with tricyclic antidepressants, phenothiazines, and antihistamines"),
            renalAdjustment = "Safe; mild reduction in max dose.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Crosses placenta rapidly; causes sustained fetal tachycardia. Avoid in obstetrics unless maternal emergency.",
            elderlyConsiderations = "High risk of postoperative delirium (Central Anticholinergic Syndrome); prefer glycopyrrolate.",
            anesthesiaConsiderations = "First-line ACLS bradycardia drug. Faster onset and more potent chronotropic effect than glycopyrrolate, but crosses blood-brain barrier and placenta.",
            reversalAgent = "Physostigmine (1 - 2 mg slow IV over 5 min) specifically reverses Central Anticholinergic Syndrome because it crosses the blood-brain barrier.",
            toxicityManagement = "Central Anticholinergic Syndrome ('blind as a bat, mad as a hatter, red as a beet, hot as a hare, dry as a bone'): Administer Physostigmine 1 - 2 mg IV slowly over 5 minutes. Have atropine ready at bedside in case of physostigmine-induced bradycardia.",
            clinicalPearls = "Never give less than 0.5 mg in adults or 0.1 mg in pediatrics (causes paradoxical bradycardia). Crosses blood-brain barrier (can cause delirium; reverse with Physostigmine).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("AHA ACLS Guidelines", "2020/2023", "Circulation"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        )
    )
}
