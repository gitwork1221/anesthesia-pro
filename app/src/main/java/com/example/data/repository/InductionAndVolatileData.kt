package com.example.data.repository

import com.example.data.model.Drug
import com.example.data.model.DrugCategory
import com.example.data.model.DrugDosing
import com.example.data.model.DrugReference

object InductionAndVolatileData {

    val drugs: List<Drug> = listOf(
        Drug(
            id = "drug_propofol",
            genericName = "Propofol",
            brandNames = "Diprivan, Fresofol",
            synonyms = listOf("2,6-diisopropylphenol", "Diprivan", "Propofol-Lipuro"),
            category = DrugCategory.IV_INDUCTION_SEDATIVES,
            drugClass = "Intravenous Hypnotic / Alkylphenol Derivative",
            mechanismOfAction = "Facilitates inhibitory neurotransmission via positive allosteric modulation of GABA-A receptors, increasing chloride conductance and hyperpolarizing postsynaptic neuronal membranes.",
            targetReceptor = "GABA-A receptor (beta-1 and beta-2 subunits)",
            indications = listOf(
                "Induction of general anesthesia",
                "Maintenance of general anesthesia (Total Intravenous Anesthesia - TIVA)",
                "Monitored Anesthesia Care (MAC) and procedural sedation",
                "ICU sedation for mechanically ventilated adult patients"
            ),
            onsetAndDuration = "Onset: 30 - 45 seconds (one arm-brain circulation time)\nPeak: 1 - 2 minutes\nDuration: 4 - 8 minutes after single bolus (rapid redistribution)",
            adultDosing = "Induction: 1.5 - 2.5 mg/kg IV (TBW; reduce to 1.0 - 1.5 mg/kg in elderly, debilitated, or ASA III/IV)\nSedation: 25 - 75 mcg/kg/min IV\nMaintenance (TIVA): 100 - 200 mcg/kg/min IV with opioid",
            pediatricDosing = "Induction: 2.5 - 3.5 mg/kg IV (higher volume of distribution and clearance in young children)",
            obstetricDosing = "Induction for emergency cesarean: 2.0 - 2.5 mg/kg IV; crosses placenta rapidly; clear neonatal airway promptly upon delivery",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "General Anesthesia Induction",
                    adultDose = "1.5 - 2.5 mg/kg IV",
                    pediatricDose = "2.5 - 3.5 mg/kg IV (infants/children)",
                    elderlyDose = "1.0 - 1.5 mg/kg IV (slow titration)",
                    maxDose = "200 - 250 mg initial induction bolus in healthy adults",
                    unitNotes = "Total Body Weight (TBW) in normal weight; Lean Body Weight (LBW) in morbid obesity"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Maintenance (TIVA)",
                    adultDose = "100 - 200 mcg/kg/min IV (6 - 12 mg/kg/hr)",
                    elderlyDose = "50 - 100 mcg/kg/min IV",
                    unitNotes = "Titrate with processed EEG / BIS monitor (target index 40 - 60)"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Procedural / MAC Sedation",
                    adultDose = "25 - 75 mcg/kg/min IV (titrated to response)",
                    elderlyDose = "15 - 40 mcg/kg/min IV"
                )
            ),
            formulationsAndRoutes = listOf("1% (10 mg/mL) injectable emulsion in soybean oil, glycerol, and egg lecithin", "2% (20 mg/mL) emulsion (ICU/specialized infusion)"),
            pharmacokinetics = "High lipophilicity (Vd 2-10 L/kg); extensive hepatic glucuronidation (CYP2B6) and extrahepatic clearance (pulmonary and renal); context-sensitive half-time remains < 40 min up to 8 hours.",
            onset = "30 - 45 seconds",
            peakEffect = "1 - 2 minutes",
            duration = "4 - 8 minutes",
            elimination = "Hepatic glucuronidation to inactive water-soluble metabolites; renal excretion > 88%",
            contraindications = listOf(
                "Known hypersensitivity to propofol or formulation emulsion ingredients",
                "Severe egg or soybean anaphylaxis with documented prior adverse reaction to lipid vehicles",
                "Disorders of lipid metabolism / severe hypertriglyceridemia (pancreatitis risk)"
            ),
            cautions = listOf(
                "Significant arterial hypotension via systemic vasodilation and mild direct myocardial depression",
                "Propofol Infusion Syndrome (PRIS) with high doses (> 4-5 mg/kg/hr for > 48 hours)",
                "Pain on injection (mitigate with lidocaine 20-40 mg IV or larger antecubital vein)",
                "Strict aseptic technique required: contains no antimicrobial preservatives (discard opened vial within 6-12 hours)"
            ),
            adverseReactions = listOf("Dose-dependent hypotension", "Apnea", "Pain on injection", "Myoclonus", "Green urine (phenolic metabolites)", "PRIS (rhabdomyolysis, metabolic acidosis, hyperkalemia, cardiac collapse)"),
            interactions = listOf("Synergistic respiratory depression with opioids and benzodiazepines", "Additive hypotension with volatile agents and antihypertensives"),
            renalAdjustment = "No dose reduction required for acute or chronic renal impairment; inactive metabolites cleared renally.",
            hepaticAdjustment = "Reduce induction dose and infusion rates by 20-40% in severe cirrhosis due to reduced protein binding and clearance.",
            pregnancyAndLactation = "FDA Category B; crosses placenta; safe for cesarean delivery induction. Breastfeeding may resume after recovery from anesthesia.",
            elderlyConsiderations = "Decreased central clearance and heightened pharmacodynamic sensitivity: reduce dose by 30-50% and inject slowly.",
            anesthesiaConsiderations = "Preserves hypoxic pulmonary vasoconstriction (HPV). Possesses antiemetic, antipruritic, and bronchodilatory properties. Decreases ICP and CMRO2.",
            reversalAgent = null,
            toxicityManagement = "Propofol Infusion Syndrome (PRIS): Immediately discontinue infusion, provide hemodynamic support, hemodialysis for refractory metabolic acidosis and hyperkalemia, ECMO for severe cardiogenic shock.",
            clinicalPearls = "Provides anti-emetic and antipruritic properties. Emulsion supports rapid microbial growth; discard opened vial within 6-12 hours.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Diprivan Package Insert & Safety Review", "FDA Approved", "AstraZeneca / Fresenius Kabi")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "GABA-A Receptor Beta-Subunit Allosteric Modulation",
            diagramType = "GABA_A_RECEPTOR",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_ketamine",
            genericName = "Ketamine Hydrochloride",
            brandNames = "Ketalar",
            synonyms = listOf("Ketalar", "Ketamine HCl", "Esketamine (S-enantiomer)"),
            category = DrugCategory.IV_INDUCTION_SEDATIVES,
            drugClass = "Dissociative Anesthetic / Phencyclidine Derivative / NMDA Antagonist",
            mechanismOfAction = "Non-competitive antagonism of the N-methyl-D-aspartate (NMDA) receptor phencyclidine binding site, inhibiting glutamate excitatory neurotransmission in the thalamocortical and limbic systems.",
            targetReceptor = "NMDA receptor (phencyclidine site), secondary opioid and monoaminergic interactions",
            indications = listOf(
                "Induction of anesthesia in hemodynamically unstable, shocked, or septic patients",
                "Severe bronchospasm, asthma, and reactive airway disease",
                "Pediatric procedural sedation and burn dressing changes",
                "Multimodal perioperative opioid-sparing analgesia and chronic neuropathic pain"
            ),
            onsetAndDuration = "Onset: 30 - 60 seconds IV; 3 - 4 minutes IM\nPeak: 1 - 2 minutes IV; 5 minutes IM\nDuration: 10 - 20 minutes IV; 20 - 30 minutes IM",
            adultDosing = "Induction IV: 1.0 - 2.0 mg/kg IV\nInduction IM: 4.0 - 6.0 mg/kg IM\nLow-dose Analgesia: 0.1 - 0.3 mg/kg IV bolus, then 2 - 5 mcg/kg/min infusion",
            pediatricDosing = "Induction IV: 1.5 - 2.0 mg/kg IV\nInduction IM: 4.0 - 5.0 mg/kg IM\nProcedural sedation: 0.5 - 1.0 mg/kg IV",
            obstetricDosing = "Induction in obstetric shock / hemorrhage: 0.5 - 1.0 mg/kg IV (doses > 2 mg/kg can increase uterine tone and compromise uteroplacental blood flow)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Induction of General Anesthesia",
                    adultDose = "1.0 - 2.0 mg/kg IV",
                    pediatricDose = "1.5 - 2.0 mg/kg IV",
                    elderlyDose = "0.5 - 1.0 mg/kg IV",
                    maxDose = "200 mg single bolus",
                    unitNotes = "Total Body Weight (TBW)"
                ),
                DrugDosing(
                    route = "IM Injection",
                    indication = "Uncooperative Pediatric / Rapid Induction",
                    adultDose = "4.0 - 6.0 mg/kg IM",
                    pediatricDose = "4.0 - 5.0 mg/kg IM",
                    maxDose = "500 mg IM total"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Multimodal Perioperative Analgesia",
                    adultDose = "0.15 - 0.25 mg/kg IV bolus, then 2 - 5 mcg/kg/min (0.12 - 0.3 mg/kg/hr)",
                    unitNotes = "Discontinue 30-60 min prior to emergence to prevent psychomimetic side effects"
                )
            ),
            formulationsAndRoutes = listOf("10 mg/mL (1%) multi-dose vial", "50 mg/mL (5%) vial", "100 mg/mL (10%) concentrated vial"),
            pharmacokinetics = "Hepatic metabolism by CYP3A4 and CYP2B6 to norketamine (1/3 active potency), subsequently glucuronidated and excreted in urine. High lipid solubility and Vd (3 L/kg).",
            onset = "30 - 60 seconds IV (3 - 4 min IM)",
            peakEffect = "1 - 2 minutes IV",
            duration = "10 - 20 minutes IV",
            elimination = "Hepatic N-demethylation to norketamine; renal elimination",
            contraindications = listOf(
                "Severe uncontrolled hypertension, severe coronary artery disease, or pheochromocytoma",
                "Significantly increased intracranial pressure (ICP) or open globe injury (relative caution)",
                "Active psychosis or severe untreated psychiatric illness"
            ),
            cautions = listOf(
                "Emergence delirium and vivid hallucinations (blunted by co-administration of midazolam 1-2 mg)",
                "Increases myocardial oxygen demand via sympathetic stimulation",
                "Direct myocardial depressant when endogenous catecholamines are severely depleted (e.g. end-stage septic shock)",
                "Hypersalivation (consider antisialagogue like glycopyrrolate)"
            ),
            adverseReactions = listOf("Hypertension & tachycardia", "Emergence dysphoria/nightmares", "Salivation", "Nystagmus", "Elevated intraocular and intracranial pressure", "Nausea/vomiting"),
            interactions = listOf("Prolongs neuromuscular blockade of non-depolarizing agents", "Theophylline co-administration increases seizure risk"),
            renalAdjustment = "Dose adjustment generally not required; norketamine metabolites accumulate in end-stage renal disease without major toxicity.",
            hepaticAdjustment = "Prolonged clearance in severe cirrhosis; decrease repeated doses and infusion rates by 30-50%.",
            pregnancyAndLactation = "Avoid high doses (> 2 mg/kg) in pregnancy due to increased uterine basal tone; safe for emergency shock.",
            elderlyConsiderations = "Higher incidence of postop delirium and confusion; reduce dose and use low-dose analgesia protocols.",
            anesthesiaConsiderations = "Potent bronchodilator. Preserves laryngeal reflexes and spontaneous ventilation relative to other IV hypnotics. Excellent induction agent for trauma and cardiac tamponade.",
            reversalAgent = null,
            toxicityManagement = "Supportive care. Manage acute hypertension/tachycardia with beta-blockers (esmolol) or vasodilators. Manage emergence agitation with midazolam 1-2 mg IV or propofol low-dose.",
            clinicalPearls = "Potent bronchodilator. Preserves laryngeal reflexes and spontaneous respiration relative to other IV anesthetics. Excellent induction agent for trauma and tamponade.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Consensus Guidelines on the Use of Intravenous Ketamine Infusions for Acute Pain Management", "2018", "ASRA / AAPM")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        Drug(
            id = "drug_etomidate",
            genericName = "Etomidate",
            brandNames = "Amidate",
            synonyms = listOf("Amidate", "R-(+)-ethyl-1-(1-phenylethyl)-1H-imidazole-5-carboxylate"),
            category = DrugCategory.IV_INDUCTION_SEDATIVES,
            drugClass = "Carboxylated Imidazole Hypnotic",
            mechanismOfAction = "Potentiates inhibitory GABA-A receptor transmission by binding directly to the beta subunit, opening transmembrane chloride channels.",
            targetReceptor = "GABA-A receptor (beta-2 and beta-3 subunits)",
            indications = listOf(
                "Rapid Sequence Induction (RSI) in hemodynamically unstable, shocked, or cardiac patients",
                "Induction in patients with severe valvular heart disease, tamponade, or cardiomyopathy",
                "Procedural sedation for cardioversion"
            ),
            onsetAndDuration = "Onset: 30 - 60 seconds (one arm-brain circulation time)\nPeak: 1 minute\nDuration: 3 - 5 minutes (rapid redistribution)",
            adultDosing = "Induction: 0.2 - 0.3 mg/kg IV (slow injection over 30-60 seconds)",
            pediatricDosing = "Induction (> 10 years): 0.2 - 0.3 mg/kg IV; safety in infants < 10 years not widely established",
            obstetricDosing = "0.2 - 0.3 mg/kg IV for crash induction when maternal cardiac disease or hypovolemic shock exists",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "General Anesthesia Induction / RSI",
                    adultDose = "0.2 - 0.3 mg/kg IV",
                    pediatricDose = "0.2 - 0.3 mg/kg IV (> 10 years)",
                    elderlyDose = "0.15 - 0.2 mg/kg IV",
                    maxDose = "0.3 mg/kg single bolus (typically 20 - 30 mg)",
                    unitNotes = "Total Body Weight (TBW)"
                )
            ),
            formulationsAndRoutes = listOf("2 mg/mL (0.2%) aqueous solution with 35% propylene glycol vehicle"),
            pharmacokinetics = "Rapidly hydrolyzed by hepatic and plasma esterases to inactive carboxylic acid metabolite; 85% eliminated in urine. Large Vd (2.5 - 4.5 L/kg).",
            onset = "30 - 60 seconds",
            peakEffect = "1 minute",
            duration = "3 - 5 minutes",
            elimination = "Plasma and hepatic esterase hydrolysis; renal elimination",
            contraindications = listOf(
                "Known hypersensitivity to etomidate or propylene glycol",
                "Acute porphyria (induces ALA synthetase)"
            ),
            cautions = listOf(
                "Transient adrenocortical suppression via reversible inhibition of 11-beta-hydroxylase (persists 6-24 hours after single bolus)",
                "High incidence of myoclonus (attenuated by pre-treatment with fentanyl 1-2 mcg/kg or midazolam)",
                "High incidence of postoperative nausea and vomiting (PONV)",
                "Pain on injection and thrombophlebitis due to propylene glycol vehicle"
            ),
            adverseReactions = listOf("Adrenocortical suppression", "Myoclonus (up to 50-70% if unprepared)", "Pain on injection", "PONV", "Thrombophlebitis", "Superficial cough/hiccups"),
            interactions = listOf("Opioids reduce myoclonus and blunts sympathetic response to laryngoscopy"),
            renalAdjustment = "No dose adjustment required for renal impairment.",
            hepaticAdjustment = "Mildly prolonged half-life in cirrhosis due to increased Vd; titrate carefully.",
            pregnancyAndLactation = "FDA Category C; use when maternal hemodynamic stability overrides potential transient neonatal adrenal blunting.",
            elderlyConsiderations = "Reduced initial dose (0.15-0.2 mg/kg) because of reduced clearance and smaller initial volume of distribution.",
            anesthesiaConsiderations = "Unmatched cardiovascular stability: minimal effect on heart rate, blood pressure, stroke volume, or myocardial contractility. Decreases cerebral blood flow, ICP, and CMRO2.",
            reversalAgent = null,
            toxicityManagement = "Supportive care. Monitor for signs of adrenal insufficiency in prolonged critical illness; administer hydrocortisone 100 mg IV if refractory shock occurs.",
            clinicalPearls = "Unmatched hemodynamic stability: minimal effect on heart rate, blood pressure, stroke volume, or myocardial oxygen consumption. Does not trigger histamine release.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_midazolam",
            genericName = "Midazolam Hydrochloride",
            brandNames = "Versed",
            synonyms = listOf("Versed", "Dormicum", "Hypnovel"),
            category = DrugCategory.IV_INDUCTION_SEDATIVES,
            drugClass = "Water-soluble Benzodiazepine / Sedative-Anxiolytic",
            mechanismOfAction = "Binds to benzodiazepine recognition site on GABA-A receptors, enhancing the inhibitory action of GABA and increasing the frequency of chloride channel opening.",
            targetReceptor = "GABA-A receptor (gamma-2 subunit / benzodiazepine receptor site)",
            indications = listOf(
                "Preoperative anxiolysis and anterograde amnesia",
                "Procedural and conscious sedation (endoscopy, cardioversion, regional blocks)",
                "General anesthesia co-induction / blunting emergence agitation",
                "Status epilepticus refractory to first-line agents"
            ),
            onsetAndDuration = "Onset: 1 - 2 minutes IV; 15 minutes oral/IM\nPeak: 3 - 5 minutes IV; 30 minutes oral\nDuration: 30 - 60 minutes IV",
            adultDosing = "Preop Anxiolysis: 1.0 - 2.0 mg IV titrated in 0.5-1.0 mg increments (Max: 2.5 - 5.0 mg total)\nProcedural Sedation: 0.5 - 2.0 mg IV every 2-3 minutes to effect\nInduction of Anesthesia: 0.15 - 0.35 mg/kg IV (slow co-induction)",
            pediatricDosing = "Premedication Oral: 0.25 - 0.5 mg/kg oral (Max: 15-20 mg) 30 min preop; IV sedation: 0.05 - 0.1 mg/kg IV",
            obstetricDosing = "Generally avoided prior to delivery due to neonatal hypotonia and respiratory depression (floppy infant syndrome)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Preoperative Anxiolysis / Sedation",
                    adultDose = "1.0 - 2.0 mg IV (slow titration)",
                    pediatricDose = "0.05 - 0.1 mg/kg IV (Max 2 mg)",
                    elderlyDose = "0.5 - 1.0 mg IV (reduce by 50% in patients > 60 yrs)",
                    maxDose = "5.0 mg for routine preop sedation",
                    unitNotes = "Observe for respiratory depression; always have supplemental O2 and suction available"
                ),
                DrugDosing(
                    route = "Oral Liquid",
                    indication = "Pediatric Premedication",
                    adultDose = "5.0 - 10.0 mg PO",
                    pediatricDose = "0.25 - 0.5 mg/kg PO (mixed in syrup/juice)",
                    maxDose = "20 mg PO"
                )
            ),
            formulationsAndRoutes = listOf("1 mg/mL (2 mL, 5 mL vials)", "5 mg/mL (1 mL, 2 mL vials)", "2 mg/mL oral syrup"),
            pharmacokinetics = "Water-soluble at acidic pH (ring open), highly lipid-soluble at physiologic pH (ring closes). Hepatic metabolism via CYP3A4 to 1-hydroxymidazolam (active metabolite; cleared renally).",
            onset = "1 - 2 minutes IV (15 - 30 min PO)",
            peakEffect = "3 - 5 minutes IV",
            duration = "30 - 60 minutes",
            elimination = "Hepatic CYP3A4 oxidation; glucuronide conjugates excreted in urine",
            contraindications = listOf(
                "Known hypersensitivity to benzodiazepines",
                "Acute narrow-angle glaucoma",
                "Severe respiratory depression without airway control or mechanical ventilation"
            ),
            cautions = listOf(
                "Profound synergistic respiratory depression when combined with opioids (fentanyl, morphine)",
                "Active metabolite 1-hydroxymidazolam accumulates in renal failure and prolonged ICU infusions",
                "Paradoxical agitation and disinhibition, particularly in elderly or pediatric patients",
                "Prolonged sedation in patients taking strong CYP3A4 inhibitors (e.g. diltiazem, macrolides, azoles)"
            ),
            adverseReactions = listOf("Hypoventilation / apnea", "Hypotension (especially with opioids)", "Paradoxical excitation", "Postoperative somnolence", "Anterograde amnesia"),
            interactions = listOf("Marked synergy with opioids and general anesthetics (reduces propofol/volatile requirement by up to 50%)"),
            renalAdjustment = "Caution with repeated doses or infusions in end-stage renal disease due to 1-hydroxymidazolam glucuronide accumulation.",
            hepaticAdjustment = "Reduce dose by 50% in cirrhosis due to impaired CYP3A4 clearance and reduced plasma protein binding.",
            pregnancyAndLactation = "FDA Category D; crosses placenta rapidly; avoid in first trimester and immediately before delivery.",
            elderlyConsiderations = "Extremely sensitive: reduce initial dose to 0.5 - 1.0 mg IV and allow full 3-5 min between doses.",
            anesthesiaConsiderations = "Provides reliable anterograde amnesia. Potent anticonvulsant. Decreases CMRO2 and ICP without cardiac depression.",
            reversalAgent = "Flumazenil: 0.2 mg IV over 15 seconds, followed by 0.1 mg every 60 seconds up to 1.0 mg total.",
            toxicityManagement = "Maintain patent airway and ventilation. Administer Flumazenil 0.2 mg IV incrementally. Caution: flumazenil half-life (50 min) is shorter than midazolam; watch for resedation. Do not use flumazenil in chronic benzodiazepine dependence or tricyclic overdose (seizure risk).",
            clinicalPearls = "Lipophilic conversion occurs at body pH. Excellent for relieving pediatric separation anxiety. Always titrate slowly when paired with opioids.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "GABA-A Alpha-Gamma Interface Allosteric Modulation",
            diagramType = "GABA_A_RECEPTOR",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_dexmedetomidine",
            genericName = "Dexmedetomidine Hydrochloride",
            brandNames = "Precedex",
            synonyms = listOf("Precedex", "Dexdor"),
            category = DrugCategory.IV_INDUCTION_SEDATIVES,
            drugClass = "Selective Alpha-2 Adrenergic Agonist / Non-GABAergic Sedative",
            mechanismOfAction = "Highly selective alpha-2 adrenergic receptor agonist (alpha-2:alpha-1 ratio of 1620:1, ~8x more selective than clonidine). Stimulates alpha-2A receptors in the locus coeruleus of the brainstem, inducing biomimetic non-REM sleep.",
            targetReceptor = "Alpha-2 adrenergic receptor (locus coeruleus)",
            indications = listOf(
                "Awake fiberoptic intubation (conscious sedation preserving spontaneous ventilation)",
                "ICU sedation for mechanically ventilated patients and post-extubation sedation",
                "Procedural sedation for non-intubated patients",
                "Adjunct for blunting sympathetic responses and emergence delirium"
            ),
            onsetAndDuration = "Onset: 5 - 15 minutes with loading infusion\nPeak: 15 minutes\nDuration: 60 - 120 minutes following infusion termination",
            adultDosing = "Loading Dose: 0.5 - 1.0 mcg/kg IV infused over 10 to 15 minutes (often omitted to prevent bradycardia/hypotension)\nMaintenance Infusion: 0.2 - 0.7 mcg/kg/hr IV (titrated up to 1.0 - 1.4 mcg/kg/hr in ICU)",
            pediatricDosing = "Loading: 0.5 - 1.0 mcg/kg IV over 10 min; Maintenance: 0.2 - 0.5 mcg/kg/hr IV",
            obstetricDosing = "Use limited in obstetrics; causes uterine contraction in animal models; reserve for specialized maternal sedation where respiratory drive must be preserved",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Awake Fiberoptic Intubation / Sedation",
                    adultDose = "0.5 - 1.0 mcg/kg IV over 10 - 15 minutes, followed by 0.5 - 0.7 mcg/kg/hr",
                    elderlyDose = "0.2 - 0.5 mcg/kg/hr (omit loading bolus)",
                    maxDose = "1.4 mcg/kg/hr",
                    unitNotes = "Dosing units are mcg/kg/hr (NOT mcg/kg/min!)"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "ICU / Postoperative Sedation",
                    adultDose = "0.2 - 0.7 mcg/kg/hr IV",
                    unitNotes = "Patients remain easily arousable to verbal stimuli without respiratory depression"
                )
            ),
            formulationsAndRoutes = listOf("100 mcg/mL concentrated vial (must be diluted prior to administration to 4 mcg/mL in 0.9% NaCl)", "Pre-mixed 4 mcg/mL in 0.9% NaCl (20 mL, 50 mL, 100 mL vials)"),
            pharmacokinetics = "High hepatic metabolism via direct glucuronidation and CYP2A6; 95% excreted in urine as inactive metabolites. Elimination half-life ~2 hours.",
            onset = "5 - 10 minutes",
            peakEffect = "15 minutes",
            duration = "60 - 120 minutes",
            elimination = "Hepatic glucuronidation and CYP2A6 hydroxylation; renal excretion",
            contraindications = listOf(
                "Advanced heart block (2nd or 3rd degree AV block without pacemaker)",
                "Severe ventricular dysfunction or severe uncorrected hypovolemia",
                "Known hypersensitivity"
            ),
            cautions = listOf(
                "Significant sinus bradycardia and sinus arrest",
                "Biphasic hemodynamic response: initial transient hypertension (peripheral alpha-2B vasoconstriction) followed by sustained hypotension (central sympatholysis)",
                "Omit or slow the 10-minute loading dose in elderly or cardiac patients to prevent severe bradycardia",
                "Dosing unit confusion: verify mcg/kg/HOUR vs mcg/kg/minute"
            ),
            adverseReactions = listOf("Bradycardia", "Hypotension", "Sinus arrest", "Transient hypertension during rapid bolus", "Dry mouth"),
            interactions = listOf("Additive bradycardia with beta-blockers, calcium channel blockers, and digoxin"),
            renalAdjustment = "No dose reduction required for renal impairment.",
            hepaticAdjustment = "Significant dose reduction (by 50%) required in severe hepatic impairment due to reduced hepatic clearance.",
            pregnancyAndLactation = "FDA Category C; crosses placenta; limited safety data; evaluate risk vs benefit.",
            elderlyConsiderations = "High risk of marked bradycardia and hypotension; omit loading bolus and start at 0.2 mcg/kg/hr.",
            anesthesiaConsiderations = "Unique 'cooperative sedation': patients are sleeping soundly but awaken promptly when addressed. No clinically meaningful respiratory depression. Reduces opioid and volatile MAC requirements by 30-50%.",
            reversalAgent = "Atipamezole (investigational / veterinary; not approved for humans). Manage with atropine, glycopyrrolate, or ephedrine.",
            toxicityManagement = "Treat bradycardia with Atropine (0.5 - 1.0 mg IV) or Glycopyrrolate. Treat hypotension with IV fluids and vasopressors (Ephedrine, Norepinephrine). Discontinue infusion.",
            clinicalPearls = "Premier agent for awake fiberoptic intubation because it maintains patient cooperativeness, preserves airway patency, and does not depress the hypoxic ventilatory response.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Precedex Prescribing Information", "FDA Approved", "Hospira / Pfizer")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        // VOLATILE AND INHALED AGENTS
        Drug(
            id = "drug_sevoflurane",
            genericName = "Sevoflurane",
            brandNames = "Ultane, Sevorane",
            synonyms = listOf("Fluoromethyl 2,2,2-trifluoro-1-(trifluoromethyl)ethyl ether", "Ultane", "Sevorane"),
            category = DrugCategory.VOLATILE_INHALED,
            drugClass = "Fluorinated Inhaled Methyl Isopropyl Ether / Volatile Anesthetic",
            mechanismOfAction = "Enhances inhibitory neurotransmission via GABA-A and glycine receptors, while inhibiting excitatory synaptic transmission at nicotinic acetylcholine, NMDA, and AMPA receptors in the central nervous system.",
            targetReceptor = "GABA-A and glycine receptors (enhancement); NMDA and 2-pore K+ channels",
            indications = listOf(
                "Inhalation induction of general anesthesia in pediatric and adult patients (non-pungent)",
                "Maintenance of general anesthesia for all surgical specialties",
                "Bronchodilation in severe status asthmaticus under mechanical ventilation"
            ),
            onsetAndDuration = "Onset: Rapid (1 - 2 minutes with 8% dial setting)\nRecovery: Rapid (low blood:gas partition coefficient 0.65)",
            adultDosing = "MAC (Minimum Alveolar Concentration) in 100% O2: ~2.0% (at 40 years)\nInhalation Induction: 4% to 8% with 100% O2\nMaintenance: 1.0% to 2.5% (titrated to clinical signs and processed EEG)",
            pediatricDosing = "MAC is higher in infants/children: Neonates 3.3%, Infants 6 months 3.2%, Children 2.5%. Inhalation induction with 6-8% in 100% O2.",
            obstetricDosing = "Maintenance at 0.5 - 1.0 MAC during cesarean under GA; concentrations > 1.0 MAC produce dose-dependent uterine relaxation and increase postpartum hemorrhage risk",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Inhalation",
                    indication = "Inhalation Induction (Pediatric & Adult)",
                    adultDose = "Up to 8% inspired with 100% O2 or 50:50 O2:N2O",
                    pediatricDose = "6% - 8% inspired",
                    unitNotes = "Non-pungent sweet odor; does not cause airway irritation or coughing"
                ),
                DrugDosing(
                    route = "Inhalation",
                    indication = "Maintenance of General Anesthesia",
                    adultDose = "1.0% - 2.5% inspired (0.7 - 1.3 MAC)",
                    elderlyDose = "0.8% - 1.8% inspired (MAC decreases ~6% per decade after 40)",
                    unitNotes = "Minimum fresh gas flow recommendation: >= 1 L/min for <= 2 MAC-hours (or >= 2 L/min) to prevent Compound A accumulation"
                )
            ),
            formulationsAndRoutes = listOf("Liquid volatile anesthetic for dedicated yellow Sevoflurane vaporizer (e.g. Tec 7, Aladin, Vapor 2000)"),
            pharmacokinetics = "Blood:gas partition coefficient 0.65; Brain:blood 1.7; Fat:blood 48. Approximately 5% metabolized by hepatic CYP2E1, producing inorganic fluoride ions and hexafluoroisopropanol (HFIP).",
            onset = "1 - 2 minutes",
            peakEffect = "3 - 5 minutes",
            duration = "Rapid wash-out dependent on alveolar ventilation and fresh gas flow",
            elimination = "Primarily pulmonary exhalation (> 95%); 5% hepatic CYP2E1 metabolism",
            contraindications = listOf(
                "Known or suspected personal or family susceptibility to Malignant Hyperthermia (MH)",
                "Known severe hypersensitivity to halogenated volatile anesthetics"
            ),
            cautions = listOf(
                "Potent trigger for Malignant Hyperthermia (along with succinylcholine and other volatiles)",
                "Reaction with desiccated carbon dioxide absorbents (barium hydroxide lime, soda lime) produces Compound A (vinyl ether, nephrotoxic in rats)",
                "Desiccated absorbents can produce exothermic reaction and carbon monoxide/canister fire",
                "Emergence agitation/delirium in pediatric patients (pretreated with fentanyl or dexmedetomidine)"
            ),
            adverseReactions = listOf("Dose-dependent vasodilation and hypotension", "Respiratory depression", "Malignant hyperthermia", "Emergence delirium in children", "Fluoride ion production"),
            interactions = listOf("Potentiates non-depolarizing neuromuscular blockers by 30-50%", "Additive MAC reduction with N2O, opioids, and benzodiazepines"),
            renalAdjustment = "Safe in renal disease; maintain fresh gas flow >= 1-2 L/min to prevent Compound A buildup.",
            hepaticAdjustment = "Safe in liver disease; minimal hepatic metabolism compared to halothane.",
            pregnancyAndLactation = "Dose-dependent uterine atony above 1.0 MAC; titrate down upon delivery of fetus and administer uterotonics.",
            elderlyConsiderations = "MAC decreases progressively with age: 1.5% at age 70. Reduce dial concentration.",
            anesthesiaConsiderations = "Agent of choice for inhalation induction in pediatric patients and adult difficult airways because of sweet smell and absence of airway pungency. Potent bronchodilator.",
            reversalAgent = "Discontinue vapor immediately, hyperventilate with 100% O2 at >= 10 L/min, switch to TIVA.",
            toxicityManagement = "Malignant Hyperthermia: Turn off volatile agent, hyperventilate with 100% O2 at >= 10 L/min, insert charcoal filters or change circle system, administer Dantrolene 2.5 mg/kg IV immediately, cool patient, treat hyperkalemia and acidosis.",
            clinicalPearls = "Premier agent for pediatric mask induction. Do not use fresh gas flows < 1 L/min with soda lime to prevent theoretical nephrotoxicity from Compound A.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("MHAUS Treatment Protocol for Malignant Hyperthermia", "2023", "MHAUS")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy", "guide_hip_fracture"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        Drug(
            id = "drug_desflurane",
            genericName = "Desflurane",
            brandNames = "Suprane",
            synonyms = listOf("Suprane", "2-(difluoromethoxy)-1,1,1,2-tetrafluoroethane"),
            category = DrugCategory.VOLATILE_INHALED,
            drugClass = "Fluorinated Inhaled Methyl Ethyl Ether / Volatile Anesthetic",
            mechanismOfAction = "Modulates inhibitory GABA-A and glycine receptors; inhibits excitatory synaptic transmission in spinal cord and cortex.",
            targetReceptor = "GABA-A and glycine receptors; two-pore domain potassium channels",
            indications = listOf(
                "Maintenance of general anesthesia in adults and children (especially long cases or morbidly obese patients requiring ultra-rapid wake-up)"
            ),
            onsetAndDuration = "Onset: Extremely rapid\nRecovery: Fastest recovery among all volatile agents (blood:gas partition coefficient 0.42)",
            adultDosing = "MAC in 100% O2: ~6.0% (at 40 years); ~6.6% at 20 years\nMaintenance: 3.0% to 7.0% inspired",
            pediatricDosing = "NOT recommended for inhalation induction in children due to high incidence of laryngospasm, coughing, and airway reactivity; maintenance only after IV induction.",
            obstetricDosing = "Causes dose-dependent uterine relaxation; keep < 0.5 - 1.0 MAC during cesarean.",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Inhalation",
                    indication = "Maintenance of General Anesthesia",
                    adultDose = "3.0% - 7.0% inspired",
                    elderlyDose = "2.5% - 5.0% inspired (MAC ~5.0% at age 70)",
                    unitNotes = "Requires electrically heated, pressurized vaporizer (Tec 6 / D-Vapor) heated to 39°C and 2 atmospheres pressure due to high vapor pressure (669 mmHg at 20°C, boiling point 22.8°C)"
                )
            ),
            formulationsAndRoutes = listOf("Liquid volatile anesthetic supplied in amber bottles with specific blue bottle adaptor for heated Tec 6 / D-Vapor vaporizers"),
            pharmacokinetics = "Lowest blood:gas solubility (0.42) of all potent volatiles. Negligible hepatic metabolism (0.02% CYP2E1). Context-sensitive recovery remains ultra-short even after 8+ hour surgeries.",
            onset = "1 - 2 minutes",
            peakEffect = "2 - 4 minutes",
            duration = "Rapid recovery within 3 - 5 minutes of discontinuation",
            elimination = "Exclusively pulmonary (> 99.98% exhaled unchanged)",
            contraindications = listOf(
                "Susceptibility to Malignant Hyperthermia",
                "Severe reactive airway disease / active severe bronchospasm (airway irritant)",
                "Inhalation induction in children (causes laryngospasm)"
            ),
            cautions = listOf(
                "Pungent airway irritant: rapid increases in concentration (> 1 MAC step change) induce sympathetic surge (tachycardia and hypertension) and coughing/laryngospasm",
                "Potent trigger for Malignant Hyperthermia",
                "Can generate carbon monoxide when passed through completely desiccated carbon dioxide absorbents containing strong bases (KOH/NaOH)"
            ),
            adverseReactions = listOf("Sympathetic activation (tachycardia, hypertension) with rapid dial increases", "Airway irritation (coughing, salivation, laryngospasm)", "Malignant hyperthermia"),
            interactions = listOf("Marked potentiation of neuromuscular blockers (reduces relaxant need by 50%)"),
            renalAdjustment = "Safe in renal disease; lowest metabolite burden of any halogenated agent.",
            hepaticAdjustment = "Safe in liver disease; virtually no hepatic metabolism.",
            pregnancyAndLactation = "Uterine relaxation at higher MAC; titrate down after delivery.",
            elderlyConsiderations = "MAC drops to ~4.5-5.0% in elderly; rapid emergence facilitates early neurocognitive assessment in elderly patients.",
            anesthesiaConsiderations = "Ideal for bariatric surgery due to low fat solubility (fat:blood 27) and predictable fast emergence. Avoid rapid concentration jumps to prevent sympathetic surges.",
            reversalAgent = "Discontinue vaporizer, hyperventilate with 100% O2.",
            toxicityManagement = "Malignant Hyperthermia rescue protocol: Dantrolene 2.5 mg/kg IV, 100% O2 hyperventilation.",
            clinicalPearls = "Fastest wash-in and wash-out among volatile agents. Boils near room temperature (22.8°C), requiring a specialized heated vaporizer.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy", "guide_hip_fracture")
        ),
        Drug(
            id = "drug_isoflurane",
            genericName = "Isoflurane",
            brandNames = "Forane",
            synonyms = listOf("Forane", "1-chloro-2,2,2-trifluoroethyl difluoromethyl ether"),
            category = DrugCategory.VOLATILE_INHALED,
            drugClass = "Halogenated Methyl Ethyl Ether / Volatile Anesthetic",
            mechanismOfAction = "Positive allosteric modulation of GABA-A receptors, inhibition of excitatory NMDA/AMPA signaling, and hyperpolarization via background two-pore potassium channels.",
            targetReceptor = "GABA-A receptors, potassium channels, NMDA receptors",
            indications = listOf(
                "Maintenance of general anesthesia (especially neuroanesthesia and cardiac anesthesia)",
                "Low-cost, highly stable volatile maintenance worldwide (WHO essential medicine)"
            ),
            onsetAndDuration = "Onset: Moderate\nRecovery: Slower than sevoflurane/desflurane (blood:gas partition coefficient 1.46)",
            adultDosing = "MAC in 100% O2: ~1.15% (at 40 years)\nMaintenance: 0.8% to 1.5%",
            pediatricDosing = "Pediatric MAC: Neonates 1.6%, Infants 1.9%, Children 1.6%. Pungency prevents use for mask induction.",
            obstetricDosing = "0.5 - 0.75 MAC for maintenance during cesarean; concentrations > 1 MAC relax myometrium.",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Inhalation",
                    indication = "Maintenance of General Anesthesia",
                    adultDose = "0.8% - 1.5% inspired",
                    elderlyDose = "0.6% - 1.0% inspired",
                    unitNotes = "Purple dedicated vaporizer (standard variable-bypass)"
                )
            ),
            formulationsAndRoutes = listOf("Liquid volatile anesthetic supplied in amber bottles for purple Isoflurane vaporizer"),
            pharmacokinetics = "Blood:gas partition coefficient 1.46; Fat:blood 45. ~0.2% metabolized by hepatic CYP2E1 to trifluoroacetic acid (TFA). Stable, resistance to degradation.",
            onset = "2 - 4 minutes",
            peakEffect = "5 - 8 minutes",
            duration = "Intermediate (longer recovery than sevoflurane after > 3-4 hours)",
            elimination = "Pulmonary exhalation (> 99.8%); 0.2% hepatic metabolism",
            contraindications = listOf("Susceptibility to Malignant Hyperthermia", "Known halogenated anesthetic allergy"),
            cautions = listOf(
                "Trigger for Malignant Hyperthermia",
                "Pungent odor causes breath-holding, coughing, and laryngospasm if used for mask induction",
                "Coronary steal phenomenon (historically described in animal models, but rarely of clinical significance when perfusion pressure is maintained)"
            ),
            adverseReactions = listOf("Hypotension via peripheral vasodilation", "Respiratory depression", "Malignant hyperthermia"),
            interactions = listOf("Potentiates neuromuscular blockade; MAC reduced by N2O and opioids"),
            renalAdjustment = "Safe in renal failure; minimal metabolism.",
            hepaticAdjustment = "Safe in liver disease; maintains hepatic arterial buffer response better than halothane.",
            pregnancyAndLactation = "Uterine relaxation at higher doses; titrate carefully.",
            elderlyConsiderations = "MAC decreases to ~0.9% in elderly patients.",
            anesthesiaConsiderations = "Gold standard volatile agent for neuroanesthesia: preserves cerebral autoregulation at <= 1 MAC and produces burst suppression on EEG at 2 MAC (cerebral protection during temporary clipping). Highly affordable worldwide.",
            reversalAgent = "Discontinue agent, 100% O2 high fresh gas flow.",
            toxicityManagement = "MHAUS Malignant Hyperthermia protocol: Dantrolene 2.5 mg/kg IV.",
            clinicalPearls = "WHO Essential Medicine. Produces burst suppression on EEG at ~2 MAC, conferring brain protection during carotid cross-clamping or aneurysm clipping.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_nitrous_oxide",
            genericName = "Nitrous Oxide (N2O)",
            brandNames = "Entonox (50:50 with O2), Laughing Gas",
            synonyms = listOf("Dinitrogen monoxide", "Laughing gas", "Entonox"),
            category = DrugCategory.VOLATILE_INHALED,
            drugClass = "Inhaled Inorganic Gas Anesthetic / NMDA Antagonist",
            mechanismOfAction = "Non-competitive NMDA receptor antagonism; activates two-pore domain potassium channels and stimulates endogenous opioid peptide release in the periaqueductal gray.",
            targetReceptor = "NMDA receptor (inhibition), opioid system (indirect)",
            indications = listOf(
                "Carrier gas adjunct to reduce volatile anesthetic requirement (second gas effect)",
                "Labor analgesia (50% N2O / 50% O2 premixed Entonox self-administered)",
                "Dental and emergency procedural analgesia"
            ),
            onsetAndDuration = "Onset: Very rapid (< 1 minute)\nRecovery: Very rapid (blood:gas partition coefficient 0.47)",
            adultDosing = "MAC: 104% (cannot achieve 1 MAC at 1 atm without severe hypoxia)\nAdjunct in GA: 50% to 70% with at least 30% O2 (FiO2 >= 0.30)\nLabor analgesia: 50% with 50% O2",
            pediatricDosing = "50% to 70% with >= 30% O2 for mask induction speed-up",
            obstetricDosing = "Self-administered 50:50 N2O/O2 during contractions (Entonox); does not impair uterine contractility or cause fetal depression",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Inhalation",
                    indication = "Adjunct to Volatile Anesthetic",
                    adultDose = "50% - 66% with >= 30% - 50% O2",
                    unitNotes = "Always maintain FiO2 >= 0.30 to prevent hypoxic mixture (fail-safe and proportioning systems required)"
                ),
                DrugDosing(
                    route = "Inhalation",
                    indication = "Labor Analgesia (Entonox)",
                    adultDose = "50% N2O / 50% O2 demand-valve mask"
                )
            ),
            formulationsAndRoutes = listOf("Compressed gas in blue cylinder (liquefied under 745 psig at 20°C); piped central medical gas"),
            pharmacokinetics = "Blood:gas partition coefficient 0.47. 34 times more soluble than nitrogen in blood, causing rapid diffusion into air-filled closed body cavities. Excreted 99.9% unchanged by lungs.",
            onset = "30 - 60 seconds",
            peakEffect = "1 - 2 minutes",
            duration = "Rapid wash-out (< 3 minutes)",
            elimination = "Pulmonary exhalation (> 99.9%)",
            contraindications = listOf(
                "Pneumothorax, tension pneumothorax, or pulmonary blebs",
                "Bowel obstruction, ileus, or acute mesenteric ischemia",
                "Venous air embolism or patent foramen ovale (risk of paradoxical embolus)",
                "Pneumocephalus or recent intracranial surgery",
                "Recent intraocular gas bubble placement (SF6 / C3F8 vitreoretinal surgery; wait 4-12 weeks)",
                "Severe pulmonary hypertension"
            ),
            cautions = listOf(
                "Closed gas space expansion: rapidly enters air pockets faster than nitrogen can leave, expanding gas volume or pressure",
                "Inactivation of methionine synthase via oxidation of cobalt in Vitamin B12 (megaloblastic bone marrow changes and peripheral neuropathy / SACD with prolonged exposure)",
                "Diffusion hypoxia on emergence: rapidly leaves blood into alveoli, diluting alveolar oxygen (administer 100% O2 for 5-10 min after turning off N2O)",
                "Increases postoperative nausea and vomiting (PONV)"
            ),
            adverseReactions = listOf("Expansion of air-filled spaces", "Diffusion hypoxia", "PONV", "Inhibition of Vitamin B12 / methionine synthase", "Teratogenicity in early pregnancy"),
            interactions = listOf("Second gas effect (hastens alveolar uptake of co-administered volatile like sevoflurane)"),
            renalAdjustment = "Safe in renal disease.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Avoid in 1st trimester due to anti-folate / methionine synthase inhibition; safe in labor (Entonox).",
            elderlyConsiderations = "Higher risk of Vitamin B12 deficiency; avoid in patients with suspected neuropathy or malnutrition.",
            anesthesiaConsiderations = "Second gas effect accelerates induction. Does NOT trigger malignant hyperthermia. Increases pulmonary vascular resistance mildly.",
            reversalAgent = "Discontinue N2O, administer 100% O2 for 5-10 minutes.",
            toxicityManagement = "Treat diffusion hypoxia with 100% O2. Treat subacute combined degeneration with high-dose Vitamin B12 (Cyanocobalamin) and folic acid.",
            clinicalPearls = "Causes closed cavity expansion: expands pneumothorax 2x-3x in 10-20 min. Always administer 100% O2 for 5 minutes after stopping N2O to prevent diffusion hypoxia.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        )
    )
}
