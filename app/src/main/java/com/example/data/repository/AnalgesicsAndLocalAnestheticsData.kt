package com.example.data.repository

import com.example.data.model.Drug
import com.example.data.model.DrugCategory
import com.example.data.model.DrugDosing
import com.example.data.model.DrugReference

object AnalgesicsAndLocalAnestheticsData {

    val drugs: List<Drug> = listOf(
        // OPIOID AND NON-OPIOID ANALGESICS
        Drug(
            id = "drug_fentanyl",
            genericName = "Fentanyl Citrate",
            brandNames = "Sublimaze",
            synonyms = listOf("Sublimaze", "N-(1-(2-phenylethyl)-4-piperidinyl)-N-phenylpropanamide"),
            category = DrugCategory.OPIOID_NON_OPIOID_ANALGESICS,
            drugClass = "Synthetic Phenylpiperidine Opioid Agonist",
            mechanismOfAction = "Potent, selective agonist at mu-opioid receptors (G-protein coupled), inhibiting adenylyl cyclase, decreasing presynaptic voltage-gated calcium influx, and opening postsynaptic inward-rectifying potassium channels to hyperpolarize neurons along nociceptive pathways.",
            targetReceptor = "Mu-opioid receptor (MOR)",
            indications = listOf(
                "Analgesia component of balanced general anesthesia",
                "Attenuation of hemodynamic stress response to laryngoscopy and surgical incision",
                "Sedation analgesia in mechanically ventilated patients",
                "Neuraxial labor and postoperative analgesia (synergy with local anesthetics)"
            ),
            onsetAndDuration = "Onset: 1 - 2 minutes IV; Peak: 3 - 5 minutes; Duration: 30 - 60 minutes (prolonged with repeat boluses or continuous infusion)",
            adultDosing = "Analgesia: 1.0 - 2.0 mcg/kg IV bolus\nInduction intubation blunting: 2.0 - 5.0 mcg/kg IV\nCardiac anesthesia high-dose: 20 - 50 mcg/kg IV\nNeuraxial: 10 - 25 mcg (spinal) or 50 - 100 mcg (epidural)",
            pediatricDosing = "1.0 - 2.0 mcg/kg IV (slow injection over 1-2 minutes)",
            obstetricDosing = "Spinal: 10 - 20 mcg with bupivacaine for cesarean; Epidural: 50 - 100 mcg for labor analgesia; safe at these dilute doses with minimal neonatal depression",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Intraoperative Analgesia / Stress Blunting",
                    adultDose = "1.0 - 2.0 mcg/kg IV (titrated every 30-45 min)",
                    pediatricDose = "1.0 - 2.0 mcg/kg IV",
                    elderlyDose = "0.5 - 1.0 mcg/kg IV (reduce by 50%)",
                    maxDose = "500 mcg in routine non-cardiac surgery without planned postop ventilation",
                    unitNotes = "Total Body Weight (TBW); dose in MICROGRAMS (mcg), not milligrams (mg)!"
                ),
                DrugDosing(
                    route = "Subarachnoid (Spinal)",
                    indication = "Cesarean Section / Orthopedic Spinal Additive",
                    adultDose = "10 - 20 mcg intrathecal (0.2 - 0.4 mL of 50 mcg/mL solution)",
                    unitNotes = "Synergistic with bupivacaine; markedly enhances sensory block quality without increasing motor block"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "ICU Mechanical Ventilation Sedation",
                    adultDose = "0.7 - 2.0 mcg/kg/hr IV (1 - 3 mcg/min)"
                )
            ),
            formulationsAndRoutes = listOf("50 mcg/mL (2 mL, 5 mL, 20 mL, 50 mL glass ampules / vials)", "100 mcg / 2 mL pre-filled syringes"),
            pharmacokinetics = "Highly lipophilic (~1000x more than morphine; ~100x more potent). Rapid redistribution from vessel-rich group terminates clinical effect after single bolus. Hepatic CYP3A4 N-dealkylation to inactive norfentanyl; context-sensitive half-time increases significantly after infusions > 2-3 hours.",
            onset = "1 - 2 minutes",
            peakEffect = "3 - 5 minutes",
            duration = "30 - 60 minutes",
            elimination = "Hepatic CYP3A4 to norfentanyl; renal excretion of inactive metabolites",
            contraindications = listOf(
                "Known severe hypersensitivity to fentanyl or phenylpiperidine derivatives",
                "Severe respiratory depression in non-intubated patients without monitored airway control",
                "Acute severe asthma exacerbation"
            ),
            cautions = listOf(
                "Dose-dependent respiratory depression (shifts CO2 response curve to the right)",
                "Chest wall rigidity ('wooden chest syndrome') caused by rapid IV bolus administration, impairing mask ventilation and requiring muscle relaxants or naloxone",
                "Bradycardia via central vagal nucleus stimulation (manage with glycopyrrolate/atropine)",
                "Context-sensitive half-time increases markedly after prolonged infusion"
            ),
            adverseReactions = listOf("Respiratory depression / hypoventilation", "Bradycardia", "Chest wall rigidity", "Pruritus", "Nausea and vomiting", "Urinary retention", "Miosis"),
            interactions = listOf("Marked synergy with propofol, benzodiazepines, and inhaled anesthetics", "CYP3A4 inhibitors (clarithromycin, ritonavir, ketoconazole) increase plasma fentanyl levels"),
            renalAdjustment = "Safe in renal failure; inactive norfentanyl is non-toxic (preferred over morphine).",
            hepaticAdjustment = "Reduce maintenance and infusion rates by 25-50% in severe cirrhosis due to reduced CYP3A4 clearance.",
            pregnancyAndLactation = "FDA Category C; safe in neuraxial labor doses; neonatal respiratory depression if large IV bolus administered close to delivery.",
            elderlyConsiderations = "Decreased clearance and increased brain sensitivity: reduce initial bolus by 50% and titrate slowly.",
            anesthesiaConsiderations = "Does NOT cause histamine release (unlike morphine), preserving systemic vascular resistance and bronchomotor tone. Excellent hemodynamic stability in cardiac anesthesia.",
            reversalAgent = "Naloxone: 0.04 - 0.4 mg IV titrated slowly in 0.04 mg increments every 2 minutes.",
            toxicityManagement = "Support ventilation with 100% O2 bag-valve-mask. If chest wall rigidity impairs ventilation, administer succinylcholine (1 mg/kg) or rocuronium, or naloxone 0.4 mg IV. Titrate naloxone carefully to avoid acute pulmonary edema and severe catecholamine surge.",
            clinicalPearls = "Does not release histamine (unlike morphine). Rapid IV administration can cause chest wall rigidity ('wooden chest') preventing ventilation. Treat wooden chest with paralytic or naloxone.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "Mu-Opioid Receptor (MOR) Gi-Protein Signaling",
            diagramType = "MU_OPIOID_GPCR",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_remifentanil",
            genericName = "Remifentanil Hydrochloride",
            brandNames = "Ultiva",
            synonyms = listOf("Ultiva", "GI87084B"),
            category = DrugCategory.OPIOID_NON_OPIOID_ANALGESICS,
            drugClass = "Ultra-short-acting Esterase-metabolized Opioid Agonist",
            mechanismOfAction = "Selective mu-opioid receptor agonist with ester linkage that renders it susceptible to rapid hydrolysis by non-specific blood and tissue esterases.",
            targetReceptor = "Mu-opioid receptor (MOR)",
            indications = listOf(
                "Analgesic component during general anesthesia induction and maintenance (especially TIVA)",
                "Neurosurgical procedures requiring intraoperative motor evoked potential (MEP) monitoring and rapid emergence",
                "High-stress procedural stimulation (rigid bronchoscopy, skull pin placement, retrobulbar block)"
            ),
            onsetAndDuration = "Onset: 1 - 2 minutes\nPeak: 1.5 - 2 minutes\nDuration: 5 - 10 minutes (context-sensitive half-time is 3-4 minutes, INDEPENDENT of infusion duration!)",
            adultDosing = "Induction: 0.5 - 1.0 mcg/kg IV over 60 seconds (or continuous infusion)\nMaintenance Infusion: 0.05 - 0.25 mcg/kg/min IV (titrated up to 0.5 mcg/kg/min with volatile or propofol)",
            pediatricDosing = "Maintenance: 0.1 - 0.25 mcg/kg/min IV; boluses can cause severe bradycardia and chest wall rigidity in neonates",
            obstetricDosing = "Used as Patient-Controlled Analgesia (PCA) for labor when neuraxial analgesia is contraindicated (e.g. coagulopathy): bolus 20-40 mcg with 2-3 min lockout; continuous 1:1 midwifery/nursing monitoring and pulse oximetry required",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Maintenance of General Anesthesia",
                    adultDose = "0.05 - 0.25 mcg/kg/min IV",
                    elderlyDose = "0.025 - 0.1 mcg/kg/min IV (50% reduction)",
                    maxDose = "0.5 mcg/kg/min IV",
                    unitNotes = "Lean Body Weight (LBW) in obesity. Units: mcg/kg/MIN (verify pump programming!)"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Blunting Acute Surgical Stimulation (Skull Pins / Laryngoscopy)",
                    adultDose = "0.5 - 1.0 mcg/kg IV over >= 60 seconds",
                    unitNotes = "Rapid bolus causes severe bradycardia and chest wall rigidity"
                )
            ),
            formulationsAndRoutes = listOf("Lyophilized powder: 1 mg, 2 mg, 5 mg vials (reconstituted to 20 - 50 mcg/mL solution; NEVER administer epidurally or intrathecally due to glycine excipient)"),
            pharmacokinetics = "Unique pharmacokinetics: broken down by circulating and tissue non-specific esterases to remifentanil acid (4,600x less potent; eliminated renally). Context-sensitive half-time is 3 - 4 minutes regardless of infusion duration (even after 12+ hours).",
            onset = "1 - 2 minutes",
            peakEffect = "1.5 - 2 minutes",
            duration = "5 - 10 minutes",
            elimination = "Non-specific blood and tissue esterase hydrolysis (NOT pseudocholinesterase)",
            contraindications = listOf(
                "Neuraxial (spinal or epidural) administration (contraindicated due to glycine excipient, which produces neurotoxicity and transient flaccid paralysis)",
                "Known hypersensitivity"
            ),
            cautions = listOf(
                "Immediate offset of analgesia: transition to longer-acting analgesic (morphine, hydromorphone, acetaminophen, local block) MUST be planned 20-30 min prior to emergence, or severe acute postop pain occurs",
                "Opioid-Induced Hyperalgesia (OIH) with high-dose infusions (> 0.25 mcg/kg/min)",
                "Marked bradycardia and hypotension (blunted with glycopyrrolate/atropine)",
                "Chest wall rigidity with rapid bolus injection"
            ),
            adverseReactions = listOf("Profound bradycardia", "Hypotension", "Apnea", "Chest wall rigidity", "Acute postoperative rebound pain if unbridged", "Hyperalgesia"),
            interactions = listOf("Profound MAC reduction of volatile anesthetics (up to 75% reduction at 0.2 mcg/kg/min)"),
            renalAdjustment = "Safe in renal failure; elimination of active drug is completely independent of renal function (inactive metabolite remifentanil acid accumulates safely).",
            hepaticAdjustment = "Safe in liver disease; clearance is completely independent of hepatic function.",
            pregnancyAndLactation = "Crosses placenta; rapidly metabolized by fetus/neonate; safe in labor PCA with 1:1 dedicated monitoring.",
            elderlyConsiderations = "Pharmacodynamic sensitivity is doubled and clearance is decreased by 50%: reduce doses by 50% or more.",
            anesthesiaConsiderations = "Enables pinpoint control of intraoperative analgesia and immobility. Ideal for neuromonitoring (MEPs) and awake craniotomies.",
            reversalAgent = "Naloxone, though stopping the infusion resolves clinical effect within 5-10 minutes.",
            toxicityManagement = "Discontinue infusion immediately; clinical effect resolves in 5-10 minutes. Support ventilation and treat bradycardia with Glycopyrrolate (0.2-0.4 mg) or Atropine (0.5-1.0 mg IV).",
            clinicalPearls = "Zero context-sensitive accumulation (half-time 3-4 minutes after 10 hours). MUST provide longer-acting analgesia (multimodal / regional / morphine) before turning off remifentanil.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_morphine",
            genericName = "Morphine Sulfate",
            brandNames = "MS Contin, Duramorph (preservative-free)",
            synonyms = listOf("Morphine", "Duramorph", "Astramorph PF", "Infumorph"),
            category = DrugCategory.OPIOID_NON_OPIOID_ANALGESICS,
            drugClass = "Natural Phenanthrene Opioid Alkaloid",
            mechanismOfAction = "Agonist at mu-opioid receptors in the substantia gelatinosa of the dorsal horn of the spinal cord and periaqueductal gray, suppressing ascending nociceptive transmission.",
            targetReceptor = "Mu-opioid receptor (MOR)",
            indications = listOf(
                "Moderate to severe acute postoperative pain management",
                "Neuraxial analgesia (intrathecal Duramorph for 18-24 hour post-cesarean / major surgery analgesia)",
                "Acute pulmonary edema (reduces preload and patient anxiety)",
                "Myocardial infarction analgesia"
            ),
            onsetAndDuration = "Onset: 5 - 10 minutes IV; Peak: 15 - 30 minutes IV; Duration: 3 - 5 hours IV; 18 - 24 hours intrathecal / epidural",
            adultDosing = "IV Bolus: 2.0 - 5.0 mg IV every 2-4 hours titrated to pain score\nIntrathecal (Duramorph PF): 100 - 200 mcg (0.1 - 0.2 mg) single injection for 18-24 hr analgesia\nEpidural (Duramorph PF): 2.0 - 4.0 mg",
            pediatricDosing = "0.05 - 0.1 mg/kg IV every 2-4 hours titrated slowly",
            obstetricDosing = "Intrathecal: 100 - 150 mcg preservative-free (Duramorph) at the time of spinal for cesarean section; the international gold standard for post-cesarean pain control",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Postoperative Pain Titration",
                    adultDose = "2.0 - 5.0 mg IV titrated slowly",
                    pediatricDose = "0.05 - 0.1 mg/kg IV",
                    elderlyDose = "1.0 - 2.5 mg IV (reduce by 50%)",
                    maxDose = "10 - 15 mg total acute titration without monitoring",
                    unitNotes = "Peak effect is delayed (15-30 min) due to low lipophilicity; avoid rapid dose stacking to prevent late apnea"
                ),
                DrugDosing(
                    route = "Subarachnoid (Spinal)",
                    indication = "Post-Cesarean / Major Arthroplasty Long-acting Analgesia",
                    adultDose = "100 - 150 mcg (0.1 - 0.15 mg) preservative-free intrathecal",
                    unitNotes = "MUST use preservative-free (Duramorph). Monitor respiratory rate hourly for 18-24 hours due to delayed rostral spread and late respiratory depression"
                )
            ),
            formulationsAndRoutes = listOf("2 mg/mL, 4 mg/mL, 10 mg/mL IV ampules", "0.5 mg/mL (10 mL) and 1 mg/mL (10 mL) preservative-free Duramorph vials for neuraxial use"),
            pharmacokinetics = "Low lipophilicity (slow crossing of blood-brain barrier). Hepatic glucuronidation by UGT2B7 to morphine-3-glucuronide (M3G, 90%, neurotoxic / allodynic) and morphine-6-glucuronide (M6G, 10%, potent active analgesic). M6G and M3G are renally eliminated.",
            onset = "5 - 10 minutes IV (slow)",
            peakEffect = "15 - 30 minutes IV",
            duration = "3 - 5 hours IV (18 - 24 hours neuraxial)",
            elimination = "Hepatic glucuronidation; active metabolite M6G and inactive M3G excreted in urine",
            contraindications = listOf(
                "Known hypersensitivity to morphine",
                "Severe renal failure (CrCl < 30 mL/min; high risk of fatal M6G accumulation)",
                "Acute bronchial asthma or severe upper airway obstruction"
            ),
            cautions = listOf(
                "Histamine release: causes vasodilation, flushing, tachycardia, and bronchospasm (avoid rapid IV boluses in asthmatics)",
                "Renal failure risk: active metabolite morphine-6-glucuronide (M6G) accumulates, causing profound, protracted, and potentially fatal narcosis/apnea",
                "Neuraxial delayed respiratory depression: hydrophilic nature allows morphine to remain in CSF, traveling rostrally to respiratory centers 6-12 hours post-injection",
                "Biliary spasm (sphincter of Oddi spasm)"
            ),
            adverseReactions = listOf("Histamine release (itching, hypotension, wheezing)", "Delayed respiratory depression", "Nausea and vomiting", "Pruritus", "Urinary retention", "Sedation"),
            interactions = listOf("CNS depressants (sedatives, alcohol) worsen respiratory arrest"),
            renalAdjustment = "AVOID or significantly reduce dose (by 50-75%) in severe renal impairment (CrCl < 30 mL/min). Prefer fentanyl or hydromorphone instead.",
            hepaticAdjustment = "Reduce dose and extend dosing intervals in cirrhosis.",
            pregnancyAndLactation = "Intrathecal Duramorph 100 mcg is standard of care for cesarean; low systemic levels in breast milk.",
            elderlyConsiderations = "Decreased GFR in elderly leads to M6G accumulation: use lower doses (1-2 mg IV).",
            anesthesiaConsiderations = "Intrathecal Duramorph provides unmatched duration of analgesia (18-24 hours) for cesarean section and major abdominal surgery. Protocolized respiratory monitoring required.",
            reversalAgent = "Naloxone: 0.04 - 0.2 mg IV increments every 2 minutes.",
            toxicityManagement = "Maintain airway. Administer Naloxone in small incremental doses (0.04 - 0.08 mg IV) to reverse respiratory depression without losing all analgesia or precipitating acute pulmonary edema.",
            clinicalPearls = "Low lipophilicity causes delayed peak (15-30 min) and delayed rostral CSF spread (respiratory monitoring required for 24 hours after spinal morphine). NEVER use in severe renal failure due to M6G accumulation.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_hip_fracture"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        Drug(
            id = "drug_hydromorphone",
            genericName = "Hydromorphone Hydrochloride",
            brandNames = "Dilaudid",
            synonyms = listOf("Dilaudid", "Dihydromorphinone"),
            category = DrugCategory.OPIOID_NON_OPIOID_ANALGESICS,
            drugClass = "Semisynthetic Phenanthrene Opioid Agonist",
            mechanismOfAction = "Potent agonist at mu-opioid receptors (approximately 5 to 7 times more potent than IV morphine).",
            targetReceptor = "Mu-opioid receptor (MOR)",
            indications = listOf(
                "Moderate to severe acute postoperative pain management",
                "Patient-Controlled Analgesia (IV PCA)",
                "Opioid analgesia in patients with renal impairment (preferred over morphine)"
            ),
            onsetAndDuration = "Onset: 2 - 5 minutes IV\nPeak: 10 - 20 minutes\nDuration: 2 - 4 hours",
            adultDosing = "0.2 - 1.0 mg IV every 2-4 hours as needed (typical starting dose: 0.2 - 0.5 mg IV; 1.0 mg is a large dose in opioid-naive patients!)",
            pediatricDosing = "0.01 - 0.02 mg/kg IV every 3-4 hours (Max single dose: 1.0 mg)",
            obstetricDosing = "0.2 - 0.5 mg IV for postop breakthrough pain",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Acute Postoperative Pain",
                    adultDose = "0.2 - 0.5 mg IV titrated every 15-30 minutes",
                    elderlyDose = "0.1 - 0.25 mg IV",
                    maxDose = "1.0 - 2.0 mg in acute PACU setting for opioid-naive patients",
                    unitNotes = "Hydromorphone is 5-7x MORE POTENT than morphine (0.5 mg IV hydromorphone ~= 3.5 mg IV morphine). Common dosing error: confusing 1 mg hydromorphone with 1 mg morphine!"
                ),
                DrugDosing(
                    route = "IV PCA",
                    indication = "Postoperative Patient-Controlled Analgesia",
                    adultDose = "Demand dose: 0.1 - 0.2 mg; Lockout interval: 6 - 10 minutes; No continuous basal infusion in opioid-naive patients",
                    unitNotes = "Total 4-hour limit: 2 - 4 mg"
                )
            ),
            formulationsAndRoutes = listOf("1 mg/mL, 2 mg/mL, 4 mg/mL glass ampules / vials"),
            pharmacokinetics = "Hepatic glucuronidation by UGT2B7 to hydromorphone-3-glucuronide (H3G; inactive as analgesic, possesses minor neuroexcitatory properties at massive doses). Does NOT form a 6-glucuronide metabolite (unlike morphine).",
            onset = "2 - 5 minutes",
            peakEffect = "10 - 20 minutes",
            duration = "2 - 4 hours",
            elimination = "Hepatic glucuronidation; excreted in urine",
            contraindications = listOf("Known severe hypersensitivity to hydromorphone", "Significant respiratory depression without monitored airway"),
            cautions = listOf(
                "High potency: 1 mg IV hydromorphone is equivalent to ~7 mg IV morphine; never confuse 1:1 dosing with morphine",
                "Respiratory depression, sedation, and apnea",
                "Produces significantly less histamine release than morphine, but hypotension can still occur"
            ),
            adverseReactions = listOf("Respiratory depression", "Sedation", "Nausea/vomiting", "Constipation", "Pruritus", "Urinary retention"),
            interactions = listOf("Additive sedation and respiratory depression with other CNS depressants"),
            renalAdjustment = "Preferred over morphine in renal insufficiency because it does not form the active M6G metabolite. Still titrate cautiously (H3G may accumulate).",
            hepaticAdjustment = "Reduce initial dose by 50% in hepatic impairment.",
            pregnancyAndLactation = "FDA Category C; use caution.",
            elderlyConsiderations = "Start with very low dose (0.1 - 0.2 mg IV) due to high potency and enhanced sensitivity.",
            anesthesiaConsiderations = "Cleaner profile than morphine with less histamine release, less pruritus, and safer in patients with borderline renal clearance.",
            reversalAgent = "Naloxone: 0.04 - 0.2 mg IV titrated.",
            toxicityManagement = "Ventilatory support, Naloxone 0.04 - 0.1 mg IV increments every 2 minutes.",
            clinicalPearls = "5-7 times more potent than morphine. Far lower risk of neurotoxic metabolite accumulation in renal dysfunction compared to morphine.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy", "guide_hip_fracture")
        ),
        Drug(
            id = "drug_ketorolac",
            genericName = "Ketorolac Tromethamine",
            brandNames = "Toradol",
            synonyms = listOf("Toradol", "Ketorolac"),
            category = DrugCategory.OPIOID_NON_OPIOID_ANALGESICS,
            drugClass = "Non-Steroidal Anti-Inflammatory Drug (NSAID) / Pyrrolizine Carboxylic Acid Derivative",
            mechanismOfAction = "Non-selective, competitive, and reversible inhibition of cyclooxygenase enzymes (COX-1 and COX-2), halting the conversion of arachidonic acid to pro-inflammatory prostaglandins and thromboxanes.",
            targetReceptor = "Cyclooxygenase-1 (COX-1) and Cyclooxygenase-2 (COX-2) enzymes",
            indications = listOf(
                "Short-term management (<= 5 days total) of moderate to severe acute postoperative pain",
                "Opioid-sparing multimodal analgesia (major orthopedic, gynecologic, and abdominal surgery)",
                "Renal colic analgesia"
            ),
            onsetAndDuration = "Onset: 10 minutes IV; Peak: 30 - 60 minutes; Duration: 4 - 6 hours",
            adultDosing = "Age < 65 and normal renal function: 15 - 30 mg IV every 6 hours (Max: 120 mg/day)\nAge >= 65, weight < 50 kg, or moderately elevated serum creatinine: 15 mg IV every 6 hours (Max: 60 mg/day)",
            pediatricDosing = "0.5 mg/kg IV single dose (Max single dose: 15 mg); safe after age 6 months in approved surgical protocols",
            obstetricDosing = "Avoid during pregnancy / prior to delivery (causes premature closure of ductus arteriosus and impairs uterine contractility); excellent post-cesarean analgesia AFTER delivery and surgical hemostasis are complete (15-30 mg IV)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Multimodal Postoperative Analgesia",
                    adultDose = "15 - 30 mg IV every 6 hours (Max 120 mg/day; ceiling effect at 15-30 mg)",
                    pediatricDose = "0.5 mg/kg IV (Max 15 mg)",
                    elderlyDose = "15 mg IV every 6 hours (Max 60 mg/day)",
                    maxDose = "Total duration of combined IV and oral therapy MUST NOT EXCEED 5 DAYS",
                    unitNotes = "Analgesic ceiling is 15-30 mg; doses > 30 mg do NOT provide greater analgesia, but significantly increase renal and GI toxicity!"
                )
            ),
            formulationsAndRoutes = listOf("15 mg/mL, 30 mg/mL prefilled syringes / glass vials for IV or IM injection"),
            pharmacokinetics = "High plasma protein binding (> 99%); hepatic hydroxylation; > 90% eliminated in urine.",
            onset = "10 minutes",
            peakEffect = "30 - 60 minutes",
            duration = "4 - 6 hours",
            elimination = "Renal excretion (> 90%); hepatic metabolism",
            contraindications = listOf(
                "Active peptic ulcer disease or history of gastrointestinal bleeding/perforation",
                "Advanced renal impairment or patients at risk for renal failure due to volume depletion",
                "Suspected or confirmed cerebrovascular bleeding, hemorrhagic diathesis, or incomplete surgical hemostasis",
                "Prior allergic reaction / aspirin-induced asthma / triad asthma (Samter's triad)",
                "Third-trimester pregnancy and labor/delivery",
                "Prophylactic analgesia prior to major surgery with high bleeding risk (e.g. tonsillectomy, spinal fusion)"
            ),
            cautions = listOf(
                "Black Box Warning: 5-day maximum duration due to severe gastrointestinal ulceration and bleeding risk",
                "Inhibits platelet aggregation via COX-1 inhibition of thromboxane A2 (prolongs bleeding time)",
                "Acute kidney injury via inhibition of renal vasodilatory prostaglandins (PGE2, PGI2) maintaining afferent arteriolar perfusion",
                "Always ensure patient is euvolemic prior to administration"
            ),
            adverseReactions = listOf("Gastrointestinal bleeding/ulceration", "Acute renal failure", "Platelet dysfunction / increased surgical bleeding", "Fluid retention", "Nausea"),
            interactions = listOf("ACE inhibitors / ARBs + Ketorolac significantly increase acute renal failure risk ('triple whammy' with diuretics)", "Anticoagulants (heparin, warfarin, DOACs) increase severe hemorrhage risk"),
            renalAdjustment = "CONTRAINDICATED in moderate to severe renal impairment. In mild impairment, cap dose at 15 mg every 6 hours (Max 60 mg/day). Ensure adequate hydration.",
            hepaticAdjustment = "Use caution; high protein binding.",
            pregnancyAndLactation = "CONTRAINDICATED in late pregnancy (premature closure of ductus arteriosus, oligohydramnios). Compatible with breastfeeding post-delivery.",
            elderlyConsiderations = "Dose cap: 15 mg IV every 6 hours (Max 60 mg/24 hr). Heightened risk of GI bleeding and renal decompensation.",
            anesthesiaConsiderations = "Outstanding opioid-sparing effect (reduces morphine requirement by 30-50%), decreasing PONV and ileus. Administer only AFTER meticulous surgical hemostasis is verified.",
            reversalAgent = "None. Discontinue drug; supportive care and platelet transfusion if life-threatening hemorrhage occurs.",
            toxicityManagement = "For GI bleeding: IV proton pump inhibitors, endoscopic intervention, fluid/blood resuscitation. For acute renal failure: aggressive IV crystalloid hydration, hold all nephrotoxic agents.",
            clinicalPearls = "Analgesic ceiling is 15-30 mg (higher doses only add toxicity, not analgesia). Maximum 5 days therapy strictly enforced. Do not give to dehydrated or bleeding patients.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("FDA Toradol (Ketorolac Tromethamine) Prescribing Information & Black Box Warning", "FDA", "Roche / Hospira")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_hip_fracture")
        ),
        Drug(
            id = "drug_acetaminophen_iv",
            genericName = "Acetaminophen IV (Paracetamol IV)",
            brandNames = "Ofirmev, Perfalgan",
            synonyms = listOf("Paracetamol IV", "APAP", "Ofirmev", "Perfalgan"),
            category = DrugCategory.OPIOID_NON_OPIOID_ANALGESICS,
            drugClass = "Non-Opioid Central Analgesic & Antipyretic",
            mechanismOfAction = "Centrally acts by inhibiting prostaglandin synthesis in the brain and spinal cord (likely COX-3 or peroxidase enzyme inhibition), stimulating descending serotonergic inhibitory pathways, and active cannabinoid active metabolite (AM404) interaction with TRPV1 receptors.",
            targetReceptor = "Central COX pathways, descending serotonergic pathways, CB1/TRPV1 receptors",
            indications = listOf(
                "Management of mild to moderate postoperative pain",
                "Adjunct to opioids in moderate to severe postoperative pain (opioid-sparing multimodal analgesia)",
                "Reduction of fever / antipyresis"
            ),
            onsetAndDuration = "Onset: 5 - 15 minutes IV; Peak: 30 minutes; Duration: 4 - 6 hours",
            adultDosing = "Weight >= 50 kg: 1000 mg IV every 6 hours or 650 mg IV every 4 hours (Max: 4000 mg/day from all sources)\nWeight < 50 kg: 15 mg/kg IV every 6 hours or 12.5 mg/kg IV every 4 hours (Max: 75 mg/kg/day)",
            pediatricDosing = "Infants & Children: 15 mg/kg IV every 6 hours (Max single dose: 1000 mg; Max daily: 75 mg/kg/day)",
            obstetricDosing = "1000 mg IV every 6 hours; Category B; the safest non-opioid analgesic in pregnancy and postpartum lactation",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Infusion",
                    indication = "Multimodal Perioperative Analgesia",
                    adultDose = "1000 mg IV infused over 15 minutes every 6 hours",
                    pediatricDose = "15 mg/kg IV every 6 hours",
                    elderlyDose = "1000 mg IV every 6 hours (ensure < 3000 mg/day in frail elderly)",
                    maxDose = "4000 mg (4 g) in 24 hours across ALL routes (IV + PO + PR)",
                    unitNotes = "MUST account for all acetaminophen-containing combination products (e.g. Percocet, Vicodin) to prevent fatal cumulative hepatotoxicity"
                )
            ),
            formulationsAndRoutes = listOf("10 mg/mL (1000 mg in 100 mL glass vial / flexible bag) for IV infusion over 15 minutes"),
            pharmacokinetics = "Hepatic metabolism (90-95%) primarily by glucuronidation and sulfation to non-toxic conjugates. 5% metabolized by CYP2E1 to N-acetyl-p-benzoquinone imine (NAPQI), which is normally detoxified by endogenous glutathione.",
            onset = "5 - 15 minutes",
            peakEffect = "30 minutes",
            duration = "4 - 6 hours",
            elimination = "Hepatic glucuronide and sulfate conjugation; renal excretion",
            contraindications = listOf(
                "Known severe hypersensitivity to acetaminophen",
                "Severe hepatic impairment or severe active liver disease"
            ),
            cautions = listOf(
                "Hepatotoxicity: doses exceeding 4 g/day deplete glutathione, allowing toxic NAPQI accumulation and centrilobular hepatic necrosis",
                "Chronic alcoholism, malnutrition, or prolonged fasting severely depletes glutathione and induces CYP2E1 (reduce max dose to 2-3 g/day)",
                "Accidental dosing errors: ensure pediatric doses are calculated strictly in mg and volume in mL verified"
            ),
            adverseReactions = listOf("Hepatotoxicity (dose-dependent)", "Mild transient nausea", "Rash / rare Stevens-Johnson syndrome"),
            interactions = listOf("Enzyme inducers (carbamazepine, phenytoin, chronic alcohol) increase toxic NAPQI formation"),
            renalAdjustment = "Severe renal impairment (CrCl < 30 mL/min): extend dosing interval to every 8 hours.",
            hepaticAdjustment = "CONTRAINDICATED in severe hepatic failure. Reduce dose to 2000 mg/day in chronic mild/moderate liver disease.",
            pregnancyAndLactation = "FDA Category B; preferred analgesic in all trimesters of pregnancy and during lactation.",
            elderlyConsiderations = "Safe in elderly; maximum recommended dose 3000 mg/day in frail elderly patients.",
            anesthesiaConsiderations = "Zero platelet inhibition, zero gastrointestinal ulceration, zero renal arteriolar vasoconstriction. Safest non-opioid baseline analgesic across all surgical disciplines.",
            reversalAgent = "N-acetylcysteine (NAC / Acetadote) for overdose.",
            toxicityManagement = "Acetaminophen Overdose: Check serum level on Rumack-Matthew nomogram. Administer N-acetylcysteine (NAC) IV or PO immediately within 8-10 hours to restore intracellular glutathione stores.",
            clinicalPearls = "Does not affect platelets, stomach lining, or kidneys (unlike NSAIDs). Infuse over 15 minutes. Check that patient is not receiving oral APAP combinations simultaneously.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy", "guide_hip_fracture")
        ),
        // LOCAL ANESTHETICS
        Drug(
            id = "drug_bupivacaine",
            genericName = "Bupivacaine Hydrochloride",
            brandNames = "Marcaine, Sensorcaine",
            synonyms = listOf("Marcaine", "Sensorcaine", "1-butyl-N-(2,6-dimethylphenyl)piperidine-2-carboxamide"),
            category = DrugCategory.LOCAL_ANESTHETICS,
            drugClass = "Long-acting Pipecoloxylidide Aminoamide Local Anesthetic",
            mechanismOfAction = "Reversibly blocks voltage-gated sodium channels (Nav1.5 cardiac and neuronal channels) from the intracellular side of the membrane, preventing inward sodium ion flux and halting action potential generation and propagation along nerve axons.",
            targetReceptor = "Voltage-gated Sodium Channel (Nav1.5, Nav1.7)",
            indications = listOf(
                "Subarachnoid (spinal) anesthesia for lower abdominal, cesarean, pelvic, and lower extremity surgery",
                "Epidural anesthesia and labor analgesia (low-concentration infusions)",
                "Peripheral nerve blocks (brachial plexus, sciatic, femoral, transversus abdominis plane - TAP)",
                "Infiltration and wound soaker catheters for prolonged postoperative analgesia"
            ),
            onsetAndDuration = "Onset: Intermediate (5 - 15 minutes for peripheral nerve block; 2 - 5 minutes for spinal)\nDuration: Long (3 - 8 hours for blocks; 90 - 150 minutes for spinal)",
            adultDosing = "Spinal (Hyperbaric 0.5% with 8.25% dextrose): 7.5 - 15 mg (1.5 - 3.0 mL)\nEpidural: 10 - 20 mL of 0.25% - 0.5%\nLabor Epidural Infusion: 0.0625% - 0.125% at 8 - 12 mL/hr\nMax Safe Dose Plain: 2.0 mg/kg (Absolute Ceiling: 175 mg)\nMax Safe Dose with Epinephrine: 2.5 mg/kg (Absolute Ceiling: 225 mg)",
            pediatricDosing = "Spinal infant: 0.3 - 0.5 mg/kg of 0.5% hyperbaric; Caudal block: 0.5 - 1.0 mL/kg of 0.125% - 0.2% (Max 2 mg/kg)",
            obstetricDosing = "Hyperbaric 0.5%: 8.0 - 12.0 mg (1.6 - 2.4 mL) with 10-20 mcg fentanyl and 100 mcg Duramorph for cesarean section (target T4 level)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Subarachnoid (Spinal)",
                    indication = "Cesarean Delivery / Lower Extremity Surgery",
                    adultDose = "8.0 - 12.0 mg (1.6 - 2.4 mL of 0.5% hyperbaric)",
                    pediatricDose = "0.3 - 0.5 mg/kg (infants)",
                    elderlyDose = "7.5 - 10.0 mg (reduced CSF volume in elderly)",
                    maxDose = "15 mg (3.0 mL of 0.5%)",
                    unitNotes = "Total Body Weight (TBW) in lean; Ideal Body Weight (IBW) in obese"
                ),
                DrugDosing(
                    route = "Peripheral Nerve Block / Infiltration",
                    indication = "Regional Anesthesia & Analgesia",
                    adultDose = "Max 2.0 mg/kg plain (Ceiling 175 mg); Max 2.5 mg/kg with epinephrine (Ceiling 225 mg)",
                    elderlyDose = "Reduce maximum calculated dose by 20-30%",
                    maxDose = "175 mg plain / 225 mg with epi",
                    unitNotes = "Dose based on IDEAL BODY WEIGHT (IBW) in obese patients to avoid LAST toxicity!"
                )
            ),
            formulationsAndRoutes = listOf(
                "0.25% (2.5 mg/mL), 0.5% (5 mg/mL), 0.75% (7.5 mg/mL) preservative-free vials for injection",
                "0.5% Hyperbaric with 8.25% Dextrose (2 mL ampules) for spinal anesthesia",
                "0.25% and 0.5% with Epinephrine 1:200,000"
            ),
            pharmacokinetics = "Highly protein-bound (95% to alpha-1 acid glycoprotein). High lipid solubility (octanol:water partition ratio 28). Slow dissociation from cardiac sodium channels ('fast-in, slow-out' kinetics). Hepatic metabolism by CYP3A4 to pipecoloxylidide (PPX).",
            onset = "Intermediate (5 - 15 minutes)",
            peakEffect = "15 - 30 minutes",
            duration = "Long (3 - 8 hours)",
            elimination = "Hepatic CYP3A4 metabolism; renal excretion of metabolites",
            contraindications = listOf(
                "Intravenous regional anesthesia (Bier Block - STRICTLY CONTRAINDICATED due to high fatality from rapid systemic cardiotoxicity upon tourniquet release)",
                "Severe heart block or severe myocardial disease",
                "Known amide local anesthetic allergy"
            ),
            cautions = listOf(
                "Highest potential for intractable cardiotoxicity among common local anesthetics",
                "Local Anesthetic Systemic Toxicity (LAST): refractory ventricular fibrillation, ventricular tachycardia, electromechanical dissociation, and asystole",
                "Slow dissociation from cardiac Nav1.5 channels makes resuscitation prolonged and resistant to standard ACLS",
                "Lipid Emulsion 20% (Intralipid) MUST BE IMMEDIATELY AVAILABLE wherever bupivacaine is injected",
                "Aspirate before every injection and use fractionated small doses (3-5 mL every 15-30 sec)"
            ),
            adverseReactions = listOf("Cardiotoxicity (refractory VF/cardiac arrest)", "LAST CNS symptoms (circumoral numbness, tinnitus, seizures, coma)", "Hypotension", "High spinal / total spinal block"),
            interactions = listOf("Additive toxicity with other local anesthetics (e.g. lidocaine, ropivacaine) - toxic doses are additive!"),
            renalAdjustment = "Safe; mild reduction in max dose recommended in uremic acidosis due to increased free drug fraction.",
            hepaticAdjustment = "Reduce maximum dose by 25-50% in severe cirrhosis due to reduced alpha-1 acid glycoprotein and hepatic clearance.",
            pregnancyAndLactation = "Higher sensitivity in pregnancy (hormonal progesterone effects enhance neural susceptibility and engorged epidural veins decrease effective space; reduce dose by 25-30%).",
            elderlyConsiderations = "Reduced clearance and lower cardiac reserve: reduce maximum dose by 25%.",
            anesthesiaConsiderations = "Differential sensory-motor block: sensory block is significantly more pronounced than motor block at dilute concentrations (0.0625% - 0.125%), making it the premier choice for walking labor analgesia.",
            reversalAgent = "20% Intravenous Lipid Emulsion (Intralipid) is the definitive rescue antidote for LAST.",
            toxicityManagement = "ASRA LAST Protocol: (1) Stop injecting, CALL FOR HELP & LAST RESCUE CART. (2) Secure airway with 100% O2, hyperventilate, suppress seizures with Midazolam or Propofol (avoid propofol if hemodynamically unstable). (3) 20% LIPID EMULSION: Bolus 1.5 mL/kg IV over 1 minute (~100 mL in 70 kg adult). Immediately start infusion at 0.25 mL/kg/min. Repeat bolus up to 2x for persistent collapse. Max total lipid dose: 10 - 12 mL/kg. (4) ACLS modifications: REDUCE epinephrine boluses to < 1 mcg/kg (e.g. 10 - 100 mcg increments); AVOID vasopressin, beta-blockers, and calcium-channel blockers.",
            clinicalPearls = "STRICTLY FORBIDDEN in IV regional anesthesia (Bier block). In LAST, standard high-dose 1 mg epinephrine worsens outcomes; use small <= 1 mcg/kg doses and 20% lipid emulsion.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASRA Practice Advisory on Local Anesthetic Systemic Toxicity", "2020", "Regional Anesthesia & Pain Medicine"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_hip_fracture"),
            relatedCaseIds = listOf("case_post_spinal_hypotension"),
            diagramTitle = "Voltage-Gated Sodium Channel Block & LAST Risk",
            diagramType = "VOLTAGE_GATED_SODIUM_CHANNEL",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_lidocaine",
            genericName = "Lidocaine Hydrochloride",
            brandNames = "Xylocaine",
            synonyms = listOf("Xylocaine", "Lignocaine", "2-(diethylamino)-N-(2,6-dimethylphenyl)acetamide"),
            category = DrugCategory.LOCAL_ANESTHETICS,
            drugClass = "Intermediate-acting Aminoamide Local Anesthetic & Class Ib Antiarrhythmic",
            mechanismOfAction = "Reversible blockade of voltage-gated sodium channels in neuronal axons and cardiac Purkinje fibers. Blocks open and inactivated sodium channels, shortening action potential duration and refractoriness.",
            targetReceptor = "Voltage-gated Sodium Channel (neuronal and cardiac)",
            indications = listOf(
                "Local infiltration and peripheral nerve blockade",
                "Intravenous regional anesthesia (Bier block - agent of choice)",
                "Intravenous blunting of airway reflexes (laryngoscopy, extubation, bronchoscopy)",
                "Attenuation of propofol injection pain (20 - 40 mg IV)",
                "Systemic perioperative IV infusion (multimodal analgesia / bowel motility in laparoscopic surgery)",
                "Treatment of ventricular arrhythmias (VT / VF - ACLS Class Ib)"
            ),
            onsetAndDuration = "Onset: Rapid (1 - 3 minutes)\nDuration: 60 - 120 minutes plain; extended to 180 - 240 minutes with epinephrine 1:200,000",
            adultDosing = "Infiltration Plain: Up to 4.5 mg/kg (Absolute Ceiling: 300 mg)\nInfiltration with Epinephrine: Up to 7.0 mg/kg (Absolute Ceiling: 500 mg)\nIV Bolus for Airway Reflex Blunting: 1.0 - 1.5 mg/kg IV (give 90 sec prior to intubation/extubation)\nSystemic IV Infusion for Analgesia: 1.0 - 1.5 mg/kg bolus, then 1.0 - 2.0 mg/kg/hr IV\nAntiarrhythmic (VT/VF): 1.0 - 1.5 mg/kg IV push; repeat 0.5 - 0.75 mg/kg every 5-10 min (Max 3 mg/kg)",
            pediatricDosing = "Max dose plain: 3.0 - 4.0 mg/kg; with epinephrine: 5.0 - 7.0 mg/kg",
            obstetricDosing = "Epidural: 2% lidocaine with epinephrine 1:200,000 (15 - 20 mL) for urgent surgical conversion of labor epidural to cesarean anesthesia",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Infiltration / Nerve Block",
                    indication = "Local / Regional Infiltration",
                    adultDose = "Max 4.5 mg/kg plain (Ceiling 300 mg); Max 7.0 mg/kg with epi (Ceiling 500 mg)",
                    pediatricDose = "Max 3.0 - 4.0 mg/kg plain; Max 7.0 mg/kg with epi",
                    elderlyDose = "Reduce maximum calculated dose by 20%",
                    maxDose = "300 mg plain / 500 mg with epi",
                    unitNotes = "Dose on IDEAL BODY WEIGHT (IBW) in obese patients"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Attenuation of Laryngoscopy / Intubation Response",
                    adultDose = "1.0 - 1.5 mg/kg IV administered 90 seconds prior to airway manipulation"
                ),
                DrugDosing(
                    route = "IV Regional (Bier Block)",
                    indication = "Forearm / Hand Surgery Bier Block",
                    adultDose = "30 - 50 mL of 0.5% preservative-free lidocaine plain (150 - 250 mg)",
                    maxDose = "250 mg total; minimum tourniquet inflation time is 20 minutes to prevent sudden systemic surge"
                )
            ),
            formulationsAndRoutes = listOf(
                "0.5% (5 mg/mL), 1% (10 mg/mL), 2% (20 mg/mL) preservative-free vials for injection",
                "1% and 2% with Epinephrine 1:200,000",
                "2% Viscous Lidocaine (topical oral/airway)",
                "4% Topical Solution (airway topicalization)",
                "5% Hyperbaric with 7.5% Dextrose (spinal - caution: Transient Neurologic Symptoms)"
            ),
            pharmacokinetics = "Intermediate protein binding (65% to alpha-1 acid glycoprotein). Rapid hepatic dealkylation by CYP1A2 and CYP3A4 to monoethylglycinexylidide (MEGX) and glycinexylidide (GX), both having antiarrhythmic and neurotoxic properties. Half-life ~90-120 min.",
            onset = "1 - 3 minutes",
            peakEffect = "5 - 10 minutes",
            duration = "60 - 120 minutes plain (180 - 240 min with epinephrine)",
            elimination = "Extensive hepatic CYP3A4/CYP1A2 metabolism; < 10% excreted unchanged by kidneys",
            contraindications = listOf(
                "Severe heart block (2nd or 3rd degree AV block without artificial pacemaker)",
                "Known true allergy to aminoamide local anesthetics",
                "Wolff-Parkinson-White (WPW) syndrome with pre-excited atrial fibrillation"
            ),
            cautions = listOf(
                "LAST neurotoxicity: initial tongue numbness, metallic taste, lightheadedness, tinnitus, visual disturbances, progressing to myoclonus, tonic-clonic seizures, and coma at serum levels > 5 mcg/mL",
                "Transient Neurologic Symptoms (TNS): severe buttock and radiating leg pain occurring 12-24 hours after spinal lidocaine (high incidence up to 30%, especially in lithotomy and ambulatory patients; spinal lidocaine is now largely avoided)",
                "Cardiac depression and hypotension at toxic levels (> 10 mcg/mL)"
            ),
            adverseReactions = listOf("Circumoral numbness / metallic taste", "Tinnitus and auditory changes", "Seizures", "Hypotension", "Bradycardia / heart block", "TNS (after spinal)"),
            interactions = listOf("Beta-blockers and cimetidine decrease hepatic blood flow, reducing lidocaine clearance by 30%"),
            renalAdjustment = "Safe; active metabolites MEGX and GX accumulate in end-stage renal disease; avoid prolonged infusions > 24 hours.",
            hepaticAdjustment = "Clearance directly reflects hepatic blood flow and metabolic capacity: reduce doses and infusion rates by 50% in severe cirrhosis or congestive heart failure.",
            pregnancyAndLactation = "FDA Category B; crosses placenta rapidly; higher fetal binding with fetal acidosis (ion trapping); safe for labor epidural and cesarean.",
            elderlyConsiderations = "Reduce dose by 20-30% due to reduced hepatic blood flow and clearance.",
            anesthesiaConsiderations = "Premier agent for Bier block (safest safety margin if accidental tourniquet leak occurs). Buffering lidocaine with 1 mL 8.4% Sodium Bicarbonate per 9-10 mL lidocaine raises uncharged fraction, significantly speeding onset and eliminating burning pain on injection.",
            reversalAgent = "Lipid Emulsion 20% (Intralipid) in severe LAST resuscitation.",
            toxicityManagement = "LAST management: Stop injection, call for LAST cart. Hyperventilate 100% O2, stop seizures with Midazolam 1-2 mg IV. If cardiovascular instability occurs: 20% Lipid Emulsion 1.5 mL/kg bolus, infusion 0.25 mL/kg/min.",
            clinicalPearls = "Agent of choice for IV regional (Bier block). Keep tourniquet inflated for at least 20 minutes. Adding 1 mL 8.4% sodium bicarbonate per 9 mL lidocaine accelerates onset and eliminates injection sting.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASRA Practice Advisory on Local Anesthetic Systemic Toxicity", "2020", "Regional Anesthesia & Pain Medicine"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate")
        ),
        Drug(
            id = "drug_ropivacaine",
            genericName = "Ropivacaine Hydrochloride",
            brandNames = "Naropin",
            synonyms = listOf("Naropin", "(S)-N-(2,6-dimethylphenyl)-1-propylpiperidine-2-carboxamide"),
            category = DrugCategory.LOCAL_ANESTHETICS,
            drugClass = "Long-acting Pure S-Enantiomer Aminoamide Local Anesthetic",
            mechanismOfAction = "Pure S-(-)-enantiomer pipecoloxylidide local anesthetic. Reversibly blocks voltage-gated sodium channels in neuronal membranes, halting action potential transmission.",
            targetReceptor = "Voltage-gated Sodium Channel",
            indications = listOf(
                "Peripheral nerve blockade and continuous catheter infusions (brachial plexus, femoral, sciatic, TAP, fascia iliaca)",
                "Epidural labor analgesia and postoperative epidural infusions",
                "Cesarean section epidural anesthesia",
                "Wound infiltration and surgical field infiltration"
            ),
            onsetAndDuration = "Onset: Intermediate (10 - 20 minutes for blocks)\nDuration: Long (4 - 10 hours depending on concentration and site)",
            adultDosing = "Nerve Block / Infiltration: 0.2% - 0.5% solution\nMax Safe Dose: 3.0 mg/kg (Absolute Ceiling: 225 - 250 mg for nerve blocks; up to 300 mg for wound infiltration)\nLabor Epidural Infusion: 0.1% - 0.2% at 8 - 12 mL/hr\nCesarean Epidural: 15 - 25 mL of 0.75% (112.5 - 187.5 mg)",
            pediatricDosing = "Caudal / Peripheral block: 0.2% (1 mL/kg, Max 2 mg/kg)",
            obstetricDosing = "0.1% - 0.2% for labor epidural; provides equal analgesia to bupivacaine with less dense motor block (enabling maternal mobilization)",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Peripheral Nerve Block / Infiltration",
                    indication = "Regional Anesthesia & Analgesia",
                    adultDose = "Max 3.0 mg/kg (Ceiling: 250 mg)",
                    pediatricDose = "Max 2.0 mg/kg (0.2% solution)",
                    elderlyDose = "Reduce maximum dose by 20%",
                    maxDose = "250 mg (nerve block) / 300 mg (surgical infiltration)",
                    unitNotes = "Calculate dose using Ideal Body Weight (IBW) in obese patients"
                ),
                DrugDosing(
                    route = "Epidural Infusion",
                    indication = "Labor Analgesia / Postoperative Thoracic Epidural",
                    adultDose = "0.1% - 0.2% solution at 6 - 12 mL/hr with PCEA boluses",
                    unitNotes = "Significantly less motor blockade than equivalent bupivacaine"
                )
            ),
            formulationsAndRoutes = listOf("0.2% (2 mg/mL), 0.5% (5 mg/mL), 0.75% (7.5 mg/mL), 1.0% (10 mg/mL) preservative-free ampules / infusion bags"),
            pharmacokinetics = "Pure S-(-)-enantiomer (less lipophilic than bupivacaine; octanol:water ratio 115 vs 28 for bupivacaine). 94% bound to alpha-1 acid glycoprotein. Faster dissociation from cardiac sodium channels than bupivacaine. Hepatic CYP1A2 (major) and CYP3A4 to 3-hydroxy-ropivacaine.",
            onset = "10 - 20 minutes",
            peakEffect = "20 - 40 minutes",
            duration = "Long (4 - 10 hours)",
            elimination = "Hepatic CYP1A2 and CYP3A4 metabolism; renal excretion",
            contraindications = listOf("Known allergy to amide local anesthetics", "Intravenous regional anesthesia (Bier block)"),
            cautions = listOf(
                "Although significantly less cardiotoxic than bupivacaine, LAST can still occur with accidental intravascular injection or massive overdose",
                "Always aspirate incrementally and use ultrasound visualization",
                "Lipid Emulsion 20% rescue must still be immediately available"
            ),
            adverseReactions = listOf("LAST (CNS and cardiac)", "Hypotension", "Bradycardia", "Paresthesias"),
            interactions = listOf("CYP1A2 inhibitors (fluvoxamine) prolong ropivacaine elimination"),
            renalAdjustment = "Safe in renal disease; metabolites cleared renally without major toxicity.",
            hepaticAdjustment = "Reduce maximum doses and continuous infusion rates by 30-50% in severe cirrhosis.",
            pregnancyAndLactation = "FDA Category B; lower fetal toxicity profile than bupivacaine; premier choice for labor epidural.",
            elderlyConsiderations = "Reduce dose by 20% in elderly patients.",
            anesthesiaConsiderations = "Distinct clinical advantage over bupivacaine: widened margin between sensory and motor block (greater sensory block with motor sparing) and lower arrhythmogenic potential (CC/CNS toxicity ratio 2.2 vs 1.6 for bupivacaine). Possesses intrinsic vasoconstrictor properties at clinical concentrations, eliminating need for epinephrine additives in many blocks.",
            reversalAgent = "20% Intravenous Lipid Emulsion (Intralipid).",
            toxicityManagement = "LAST management: Airway 100% O2, Midazolam for seizures, 20% Lipid Emulsion 1.5 mL/kg bolus, 0.25 mL/kg/min infusion.",
            clinicalPearls = "Pure S-enantiomer with greater safety margin and less cardiotoxicity than bupivacaine. Provides excellent sensory block while sparing motor function, perfect for walking labor epidurals and postop continuous nerve catheters.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASRA Practice Advisory on Local Anesthetic Systemic Toxicity", "2020", "Regional Anesthesia & Pain Medicine"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_hip_fracture")
        ),
        Drug(
            id = "drug_chloroprocaine",
            genericName = "2-Chloroprocaine Hydrochloride",
            brandNames = "Nesacaine, Clorotekal",
            synonyms = listOf("Nesacaine", "Nesacaine-MPF", "Clorotekal"),
            category = DrugCategory.LOCAL_ANESTHETICS,
            drugClass = "Ultra-short-acting Aminoester Local Anesthetic",
            mechanismOfAction = "Reversibly blocks voltage-gated sodium channels, halting action potential transmission along neuronal axons.",
            targetReceptor = "Voltage-gated Sodium Channel",
            indications = listOf(
                "Emergency surgical extension of labor epidural for urgent / crash cesarean delivery (fastest onset)",
                "Ultra-short ambulatory spinal anesthesia (Clorotekal 1%) for outpatient day-case surgeries",
                "Peripheral infiltration and short-duration nerve block"
            ),
            onsetAndDuration = "Onset: Fastest of all local anesthetics (2 - 5 minutes epidural / spinal)\nDuration: Very short (30 - 60 minutes epidural; 45 - 90 minutes spinal)",
            adultDosing = "Epidural for Emergency Cesarean: 15 - 25 mL of 3% Nesacaine-MPF (450 - 750 mg) in 5 mL increments\nSpinal (Clorotekal 1% preservative-free): 30 - 50 mg (3 - 5 mL) for outpatient procedures\nMax Safe Dose Plain: 11 mg/kg (Absolute Ceiling: 800 mg)\nMax Safe Dose with Epinephrine: 14 mg/kg (Absolute Ceiling: 1000 mg)",
            pediatricDosing = "Limited use in pediatrics; max 11 mg/kg",
            obstetricDosing = "Premier local anesthetic for urgent surgical conversion of labor epidural to cesarean section: 3% Nesacaine-MPF gives dense T4 surgical sensory level in 3 - 6 minutes",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "Epidural",
                    indication = "Urgent / Crash Cesarean Section Conversion",
                    adultDose = "15 - 20 mL of 3% preservative-free solution (Nesacaine-MPF) given in 5 mL divided boluses",
                    maxDose = "800 mg plain / 1000 mg with epi",
                    unitNotes = "MUST use Preservative-Free (MPF) formulation without EDTA or bisulfites"
                ),
                DrugDosing(
                    route = "Subarachnoid (Spinal)",
                    indication = "Day-case Ambulatory Surgery",
                    adultDose = "30 - 50 mg (3 - 5 mL of 1% preservative-free Clorotekal)",
                    unitNotes = "Rapid recovery to unassisted ambulation and micturition within 90-120 minutes with negligible TNS risk"
                )
            ),
            formulationsAndRoutes = listOf("2% and 3% Nesacaine-MPF (Methylparaben-Free, EDTA-free) vials", "1% Clorotekal for spinal injection"),
            pharmacokinetics = "Ultra-rapid hydrolysis in plasma by pseudocholinesterase (butyrylcholinesterase) to inactive 4-amino-2-chlorobenzoic acid. Plasma half-life is under 30 seconds! Lowest systemic toxicity of all local anesthetics.",
            onset = "Fastest (2 - 5 minutes)",
            peakEffect = "5 - 10 minutes",
            duration = "Very short (30 - 60 minutes)",
            elimination = "Plasma pseudocholinesterase hydrolysis (half-life < 30 seconds); renal elimination of metabolites",
            contraindications = listOf(
                "Known true hypersensitivity to aminoester local anesthetics or PABA (para-aminobenzoic acid)",
                "Use of formulations containing preservatives (EDTA or sodium bisulfite) in the epidural or subarachnoid space (historical formulations caused neurotoxicity)"
            ),
            cautions = listOf(
                "Antagonizes subsequent epidural opioids (fentanyl, morphine) and bupivacaine via receptor competition or local tissue pH alterations; if planning long-acting neuraxial morphine, note that chloroprocaine may reduce its effectiveness",
                "Short duration: for long surgery, repeat boluses every 30-45 minutes or transition to bupivacaine/ropivacaine"
            ),
            adverseReactions = listOf("Hypotension via sympathetic blockade", "Transient backache (with older EDTA formulations; absent with modern MPF)", "PABA allergic sensitization"),
            interactions = listOf("May reduce efficacy of subsequent epidural opioids (morphine/fentanyl)"),
            renalAdjustment = "Safe in renal disease; rapid plasma breakdown.",
            hepaticAdjustment = "Safe in liver disease; cleared in plasma by pseudocholinesterase.",
            pregnancyAndLactation = "Premier emergency drug for crash cesarean conversion due to lightning-fast onset and zero fetal accumulation (metabolized before significant placental transfer).",
            elderlyConsiderations = "Safe in elderly patients; ideal for fast-track outpatient surgery.",
            anesthesiaConsiderations = "The undisputed fastest-onset local anesthetic for emergency crash cesarean section via an existing labor epidural catheter. Plasma half-life < 30 seconds ensures virtually zero fetal accumulation.",
            reversalAgent = "Time (plasma half-life < 30 seconds); supportive care.",
            toxicityManagement = "Toxicity is exceedingly rare due to sub-30-second plasma half-life. If massive accidental IV bolus occurs: stop injection, 100% O2, supportive hemodynamic care, Lipid Emulsion 20% if needed.",
            clinicalPearls = "Fastest-acting local anesthetic available (onset 2-5 min). Essential in obstetric anesthesia for crash C-section epidural conversion. Ultra-rapid plasma esterase clearance (< 30 sec half-life).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section")
        )
    )
}
