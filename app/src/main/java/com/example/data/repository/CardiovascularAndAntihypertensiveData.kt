package com.example.data.repository

import com.example.data.model.Drug
import com.example.data.model.DrugCategory
import com.example.data.model.DrugDosing
import com.example.data.model.DrugReference

object CardiovascularAndAntihypertensiveData {

    val drugs: List<Drug> = listOf(
        // VASOPRESSORS & INOTROPES
        Drug(
            id = "drug_phenylephrine",
            genericName = "Phenylephrine Hydrochloride",
            brandNames = "Neo-Synephrine, Vazculep",
            synonyms = listOf("Neo-Synephrine", "Phenylephrine HCl"),
            category = DrugCategory.VASOPRESSORS_INOTROPES,
            drugClass = "Direct-acting Synthetic Selective Alpha-1 Adrenergic Agonist",
            mechanismOfAction = "Directly binds and stimulates post-synaptic alpha-1 adrenergic receptors on vascular smooth muscle, activating phospholipase C and inositol trisphosphate (IP3) to trigger intracellular calcium release. Induces arteriolar vasoconstriction, elevating systemic vascular resistance (SVR), systolic and diastolic blood pressure, and venous return.",
            targetReceptor = "Alpha-1 adrenergic receptor (pure selective agonist)",
            indications = listOf(
                "First-line vasopressor for post-spinal hypotension during Cesarean delivery (gold standard)",
                "Anesthesia-induced systemic vasodilation with normal or elevated baseline heart rate",
                "Hypertrophic cardiomyopathy (HOCM) or severe aortic stenosis hypotension (avoids tachycardia)"
            ),
            onsetAndDuration = "Onset: < 1 minute IV; Peak: 1 - 2 minutes; Duration: 5 - 15 minutes",
            adultDosing = "IV Bolus: 50 - 100 mcg IV bolus titrated to blood pressure\nContinuous Infusion: 25 - 100 mcg/min IV (0.25 - 1.5 mcg/kg/min)",
            pediatricDosing = "1.0 - 5.0 mcg/kg IV bolus",
            obstetricDosing = "First-choice vasopressor in obstetric spinal hypotension: 50 - 100 mcg IV boluses or prophylactic infusion at 25 - 50 mcg/min; preserves umbilical arterial pH significantly better than ephedrine",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Post-Spinal / Post-Induction Hypotension",
                    adultDose = "50 - 100 mcg IV bolus (repeat every 2-5 min as needed)",
                    pediatricDose = "1.0 - 5.0 mcg/kg IV",
                    elderlyDose = "25 - 50 mcg IV initial bolus",
                    maxDose = "200 mcg single bolus",
                    unitNotes = "Verify concentration! Standard OR syringe dilution is 100 mcg/mL (1 vial 10 mg diluted into 100 mL saline)"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Obstetric Spinal Prophylaxis / OR Vasoplegia",
                    adultDose = "25 - 100 mcg/min IV (titrated to systolic BP >= 90% baseline)"
                )
            ),
            formulationsAndRoutes = listOf("10 mg/mL (1 mL ampules; MUST BE DILUTED prior to bolus use)", "Pre-filled ready-to-use syringes (100 mcg/mL in 5 mL or 10 mL)"),
            pharmacokinetics = "Metabolized by monoamine oxidase (MAO) in liver and intestine; inactive metabolites excreted in urine. Elimination half-life ~5 minutes.",
            onset = "< 1 minute",
            peakEffect = "1 - 2 minutes",
            duration = "5 - 15 minutes",
            elimination = "Hepatic and intestinal monoamine oxidase (MAO); renal excretion",
            contraindications = listOf(
                "Severe bradycardia (heart rate < 50 bpm; reflex baroreceptor bradycardia will worsen low cardiac output)",
                "Severe heart failure with poor systolic function (abruptly increasing afterload precipitates acute decompensation)"
            ),
            cautions = listOf(
                "Baroreceptor reflex bradycardia: elevating SVR triggers carotid and aortic baroreceptors, reducing stroke rate and potentially decreasing cardiac output",
                "Extravasation necrosis: severe local vasoconstriction causes ischemic tissue sloughing (treat immediately with local phentolamine 5-10 mg infiltration)",
                "Avoid if patient is already bradycardic (use Ephedrine instead)"
            ),
            adverseReactions = listOf("Reflex bradycardia", "Decreased cardiac output", "Hypertension", "Headache", "Extravasation ischemia"),
            interactions = listOf("Markedly exaggerated pressor response with MAO inhibitors and tricyclic antidepressants", "Atropine pre-treatment prevents reflex bradycardia and causes severe hypertension"),
            renalAdjustment = "Safe; watch for excessive renal vasoconstriction at massive doses.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Gold standard in obstetrics: does NOT cross placenta significantly and does not cause fetal acidosis (unlike high-dose ephedrine).",
            elderlyConsiderations = "More sensitive to afterload increase; start with lower boluses (25-50 mcg).",
            anesthesiaConsiderations = "The premier vasopressor in obstetric anesthesia. Whenever hypotension is accompanied by normal or high heart rate, phenylephrine is the drug of choice.",
            reversalAgent = "Phentolamine (alpha-blocker) or short-acting vasodilators (Nitroglycerin, Nitroprusside).",
            toxicityManagement = "Treat severe hypertension with Nitroglycerin IV or Phentolamine (5 mg IV). Treat extravasation immediately by infiltrating Phentolamine 5 - 10 mg diluted in 10 mL saline through the existing IV cannula and surrounding subcutaneous tissue.",
            clinicalPearls = "Gold standard for post-spinal hypotension in Cesarean section because it preserves fetal umbilical pH. Never use if patient is already bradycardic (reflex bradycardia will worsen cardiac output).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ASA Practice Guidelines for Obstetric Anesthesia", "2023", "Anesthesiology"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_post_spinal_hypotension"),
            diagramTitle = "Vascular Alpha-1 Adrenergic Receptor & IP3/Ca2+ Vasoconstriction",
            diagramType = "ADRENERGIC_ALPHA_BETA",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_ephedrine",
            genericName = "Ephedrine Hydrochloride",
            brandNames = "Ephedrine, Corphedra",
            synonyms = listOf("Ephedrine", "Ephedrine sulfate"),
            category = DrugCategory.VASOPRESSORS_INOTROPES,
            drugClass = "Mixed-acting Sympathomimetic Vasopressor",
            mechanismOfAction = "Dual mechanism: predominantly INDIRECT stimulation of adrenergic receptors by displacing endogenous norepinephrine from presynaptic neuronal storage vesicles, plus weak DIRECT stimulation of alpha-1, beta-1, and beta-2 adrenergic receptors.",
            targetReceptor = "Alpha-1, Beta-1, Beta-2 receptors (indirect > direct)",
            indications = listOf(
                "Hypotension from sympathetic blockade during spinal or epidural anesthesia accompanied by low or low-normal heart rate",
                "Anesthesia-induced vasodilation with concomitant bradycardia",
                "Temporary inotropic and chronotropic support during induction"
            ),
            onsetAndDuration = "Onset: 30 - 60 seconds IV; Peak: 2 - 5 minutes; Duration: 10 - 20 minutes",
            adultDosing = "5.0 - 10.0 mg IV boluses titrated every 3-5 minutes (Max: 50 mg in acute setting)",
            pediatricDosing = "0.1 - 0.2 mg/kg IV",
            obstetricDosing = "5.0 - 10.0 mg IV boluses; ideal when post-spinal hypotension is accompanied by bradycardia (HR < 60 bpm). Note: large cumulative doses (> 30-50 mg) cause fetal metabolic acidosis due to placental transfer and fetal beta-stimulation",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Hypotension with Bradycardia",
                    adultDose = "5.0 - 10.0 mg IV bolus every 3-5 minutes",
                    pediatricDose = "0.1 - 0.2 mg/kg IV",
                    elderlyDose = "2.5 - 5.0 mg IV (cautious titration)",
                    maxDose = "50 mg total cumulative dose in acute setting",
                    unitNotes = "Verify concentration! Standard OR dilution is 5 mg/mL (1 mL 50 mg ampule diluted into 10 mL saline)"
                )
            ),
            formulationsAndRoutes = listOf("50 mg/mL (1 mL ampules; MUST BE DILUTED to 5 mg/mL)", "Pre-filled 5 mg/mL syringes (10 mL)"),
            pharmacokinetics = "Resistant to COMT and MAO breakdown due to alpha-methyl group. Elimination half-life ~3-6 hours; largely excreted unchanged in urine (60-80%).",
            onset = "30 - 60 seconds",
            peakEffect = "2 - 5 minutes",
            duration = "10 - 20 minutes",
            elimination = "Renal excretion unchanged (60-80%); minor hepatic deamination",
            contraindications = listOf(
                "Severe coronary artery disease / acute myocardial infarction (markedly increases myocardial oxygen consumption)",
                "Concurrent MAO inhibitors (risk of fatal hyperadrenergic crisis)",
                "Uncontrolled tachydysrhythmias"
            ),
            cautions = listOf(
                "Tachyphylaxis: repeated doses rapidly produce diminished clinical response due to progressive depletion of presynaptic norepinephrine stores",
                "Ineffective in end-stage chronic shock or denervated hearts (heart transplant) where catecholamine stores are exhausted (direct pressors like epinephrine/norepinephrine required)",
                "Crosses placenta readily; excessive dosing (> 30 mg) in obstetrics induces fetal tachycardia and metabolic acidosis (phenylephrine preferred when HR is normal)"
            ),
            adverseReactions = listOf("Tachycardia / palpitations", "Arrhythmias (ventricular / supraventricular)", "Hypertension", "Tachyphylaxis", "Fetal acidosis (high cumulative doses)"),
            interactions = listOf("Catastrophic hypertensive crisis with MAO inhibitors", "Decreased efficacy in reserpine or chronic cocaine-depleted states"),
            renalAdjustment = "Safe; minor extension of half-life.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Preferred when maternal hypotension is paired with maternal bradycardia (< 60 bpm); switch to phenylephrine once HR > 70-80 bpm.",
            elderlyConsiderations = "Can provoke myocardial ischemia or tachyarrhythmias; titrate in small 2.5-5.0 mg increments.",
            anesthesiaConsiderations = "The vasopressor of choice when hypotension is accompanied by bradycardia, as it boosts both contractility/HR (beta-1) and peripheral vascular resistance (alpha-1).",
            reversalAgent = "Short-acting beta-blockers (Esmolol) or vasodilators.",
            toxicityManagement = "Treat severe tachycardia/hypertension with Esmolol (10-30 mg IV) or Nitroglycerin.",
            clinicalPearls = "First choice when hypotension is accompanied by bradycardia. Tachyphylaxis occurs quickly due to depletion of presynaptic norepinephrine stores.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_cesarean_section", "guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_post_spinal_hypotension")
        ),
        Drug(
            id = "drug_norepinephrine",
            genericName = "Norepinephrine Bitartrate",
            brandNames = "Levophed",
            synonyms = listOf("Levophed", "Noradrenaline", "Levarterenol"),
            category = DrugCategory.VASOPRESSORS_INOTROPES,
            drugClass = "Potent Direct Endogenous Catecholamine / First-line Vasopressor",
            mechanismOfAction = "Potent direct agonist at vascular alpha-1 and alpha-2 adrenergic receptors (intense vasoconstriction), with modest beta-1 adrenergic agonist activity (inotropic and mild chronotropic support). Minimal beta-2 activity.",
            targetReceptor = "Alpha-1 > Alpha-2 > Beta-1 >>> Beta-2 adrenergic receptors",
            indications = listOf(
                "First-line vasopressor for septic shock, distributive shock, and vasoplegic syndrome (Surviving Sepsis Guidelines)",
                "Refractory hypotension unresponsive to fluid boluses and phenylephrine",
                "Cardiogenic shock with low SVR (combined with inodilator)"
            ),
            onsetAndDuration = "Onset: 1 - 2 minutes IV; Duration: 2 - 10 minutes following infusion cessation",
            adultDosing = "Continuous IV Infusion: 0.02 - 0.3 mcg/kg/min (titrated to target MAP >= 65 mmHg; severe shock may require up to 1.0 mcg/kg/min)\nEmergency Push-Dose Bolus: 4.0 - 8.0 mcg IV (0.5 - 1.0 mL of 8 mcg/mL dilution)",
            pediatricDosing = "0.05 - 0.2 mcg/kg/min IV continuous infusion",
            obstetricDosing = "Emerging evidence as an outstanding alternative to phenylephrine for post-spinal hypotension in Cesarean section (0.02 - 0.05 mcg/kg/min or 4 mcg boluses) because it preserves maternal heart rate and cardiac output better than phenylephrine",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Septic Shock / Vasoplegic Syndrome",
                    adultDose = "0.02 - 0.3 mcg/kg/min IV (titrated to MAP >= 65 mmHg)",
                    pediatricDose = "0.05 - 0.2 mcg/kg/min IV",
                    elderlyDose = "0.02 mcg/kg/min initial rate",
                    maxDose = "1.0 - 1.5 mcg/kg/min in refractory septic shock",
                    unitNotes = "Central venous catheter preferred! Administering via peripheral vein requires large antecubital vein, dedicated line, frequent site checks, and rapid dilution"
                ),
                DrugDosing(
                    route = "IV Push Dose (Emergency)",
                    indication = "Acute Intraoperative Vasoplegia",
                    adultDose = "4 - 8 mcg IV push (prepared by diluting 1 mg into 250 mL D5W/NS = 4 mcg/mL)",
                    unitNotes = "Verify concentration and label syringe with extreme care"
                )
            ),
            formulationsAndRoutes = listOf("4 mg/4 mL (1 mg/mL) ampules; must be diluted in D5W or D5NS (dextrose protects against oxidation) to 4 - 16 mcg/mL infusion"),
            pharmacokinetics = "Rapidly cleared from plasma by neuronal reuptake (uptake 1) and extraneuronal uptake (uptake 2), followed by enzymatic breakdown by COMT and MAO in the liver and kidneys. Elimination half-life ~1-2 minutes.",
            onset = "1 - 2 minutes",
            peakEffect = "2 - 3 minutes",
            duration = "2 - 10 minutes",
            elimination = "Neuronal reuptake; enzymatic degradation by COMT and MAO",
            contraindications = listOf("Hypovolemic shock prior to adequate intravascular volume resuscitation (relative; except as emergency temporizing bridge)"),
            cautions = listOf(
                "Peripheral extravasation necrosis: intense alpha-1 vasoconstriction causes severe ischemic gangrene (central line strongly recommended; infiltrate Phentolamine 5-10 mg if extravasation occurs)",
                "Excessive vasoconstriction: can produce peripheral digital ischemia, mesenteric ischemia, and renal hypoperfusion at massive doses",
                "Ensure adequate volume resuscitation prior to or concurrently with infusion"
            ),
            adverseReactions = listOf("Peripheral digital ischemia", "Arrhythmias", "Hypertension", "Extravasation tissue sloughing"),
            interactions = listOf("Marked potentiation by MAO inhibitors and tricyclic antidepressants"),
            renalAdjustment = "Safe; improves renal perfusion pressure and glomerular filtration in septic vasoplegia when MAP < 65 mmHg.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Safe; growing international use for post-spinal hypotension in Cesarean section.",
            elderlyConsiderations = "Start low (0.02 mcg/kg/min) and titrate carefully to MAP 65 mmHg.",
            anesthesiaConsiderations = "The gold standard first-line vasopressor for septic and vasoplegic shock worldwide. Its modest beta-1 activity prevents the reflex bradycardia seen with pure alpha-1 agonists (phenylephrine), maintaining cardiac output.",
            reversalAgent = "Phentolamine or short-acting vasodilators.",
            toxicityManagement = "Treat extravasation immediately by infiltrating Phentolamine 5 - 10 mg diluted in 10 mL saline around the site. Discontinue or titrate down infusion if excessive hypertension occurs.",
            clinicalPearls = "First-line vasopressor in septic and vasoplegic shock (Surviving Sepsis Campaign). Central line preferred. Infiltrate phentolamine immediately if extravasation occurs.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Surviving Sepsis Campaign International Guidelines", "2021", "Critical Care Medicine"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy"),
            relatedCaseIds = listOf("case_cannot_intubate_cannot_oxygenate"),
            diagramTitle = "Alpha-1 / Beta-1 Adrenergic Sympathomimetic Signaling",
            diagramType = "ADRENERGIC_ALPHA_BETA",
            pkCurveType = "MULTI_COMPARTMENT_DISTRIBUTION"
        ),
        Drug(
            id = "drug_vasopressin",
            genericName = "Vasopressin (Arginine Vasopressin / AVP)",
            brandNames = "Pitressin, Vasostrict",
            synonyms = listOf("Pitressin", "Vasostrict", "Antidiuretic Hormone", "ADH", "AVP"),
            category = DrugCategory.VASOPRESSORS_INOTROPES,
            drugClass = "Exogenous Non-adrenergic Peptide Hormone / V1 Agonist",
            mechanismOfAction = "Stimulates vascular smooth muscle V1a receptors coupled to Gq proteins, activating phospholipase C and IP3 to cause potent arteriolar vasoconstriction completely independent of adrenergic receptors. Also inhibits ATP-sensitive potassium channels (K-ATP) and blunts nitric oxide-mediated vasodilation.",
            targetReceptor = "Vasopressin V1a receptor (vascular smooth muscle); V2 receptor (renal collecting duct)",
            indications = listOf(
                "Refractory vasoplegic shock / vasodilatory shock unresponsive to high-dose catecholamines (norepinephrine)",
                "ACE-inhibitor / ARB-induced refractory intraoperative hypotension",
                "Post-cardiopulmonary bypass vasoplegia",
                "Cardiac arrest (historical ACLS adjunct / PEA)"
            ),
            onsetAndDuration = "Onset: < 1 - 2 minutes IV; Peak: 5 minutes; Duration: 15 - 30 minutes following discontinuation",
            adultDosing = "Continuous IV Infusion: 0.01 - 0.04 units/min (FIXED non-titrated rate; do NOT exceed 0.04 units/min to avoid coronary/mesenteric ischemia)\nEmergency Push Bolus: 1.0 - 2.0 units IV (used cautiously for refractory ACE-inhibitor vasoplegia)",
            pediatricDosing = "0.0003 - 0.002 units/kg/min IV continuous infusion",
            obstetricDosing = "Avoid during pregnancy unless refractory cardiovascular collapse; causes intense uterine contractions",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Vasoplegic Shock / Sepsis Second-Line Adjunct",
                    adultDose = "0.01 - 0.03 units/min IV fixed rate",
                    elderlyDose = "0.01 - 0.02 units/min IV",
                    maxDose = "0.04 units/min (STRICT CEILING; higher doses cause severe coronary and splanchnic ischemia!)",
                    unitNotes = "Units: UNITS/MIN (not units/hr!). Do NOT titrate like norepinephrine; run at fixed 0.03 units/min as a catecholamine-sparing adjunct"
                ),
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Refractory ACE-Inhibitor Vasoplegia Under Anesthesia",
                    adultDose = "0.5 - 1.0 unit IV bolus (diluted in 10 mL saline)",
                    unitNotes = "Use when ephedrine and phenylephrine have completely failed"
                )
            ),
            formulationsAndRoutes = listOf("20 units/mL (1 mL vials); must be diluted (e.g. 20 units in 100 mL NS = 0.2 units/mL)"),
            pharmacokinetics = "Hydrolyzed by tissue peptidases in the liver and kidneys. Elimination half-life ~10-20 minutes.",
            onset = "< 1 - 2 minutes",
            peakEffect = "5 minutes",
            duration = "15 - 30 minutes",
            elimination = "Renal and hepatic tissue peptidase cleavage",
            contraindications = listOf("Severe coronary artery disease / acute coronary ischemia (potent coronary vasoconstrictor)"),
            cautions = listOf(
                "Coronary vasoconstriction and myocardial ischemia: can precipitate acute myocardial infarction",
                "Splanchnic and mesenteric vasoconstriction: risk of bowel ischemia and digital necrosis",
                "Dosing ceiling strictly 0.04 units/min; NEVER titrate to high doses",
                "Hyponatremia / water retention (V2 effect) with prolonged infusions"
            ),
            adverseReactions = listOf("Myocardial ischemia / angina", "Mesenteric ischemia", "Peripheral digital gangrene", "Severe bradycardia", "Hyponatremia"),
            interactions = listOf("Synergistic with norepinephrine in restoring vascular tone in vasoplegia"),
            renalAdjustment = "Safe; restores renal filtration pressure in vasoplegic shock.",
            hepaticAdjustment = "Safe in liver disease.",
            pregnancyAndLactation = "Avoid prior to delivery; stimulates V1a/oxytocin receptors causing intense uterine contractions.",
            elderlyConsiderations = "High risk of occult coronary ischemia; keep dose <= 0.03 units/min.",
            anesthesiaConsiderations = "Essential rescue vasopressor for refractory vasoplegia caused by pre-op ACE inhibitors, ARBs, or sepsis where adrenergic receptors are desensitized and downregulated. Works in severe acidosis (pH < 7.2) where catecholamines fail.",
            reversalAgent = "Vasodilators (Nitroglycerin, Nitroprusside).",
            toxicityManagement = "Immediately discontinue or reduce infusion rate. Administer IV Nitroglycerin for coronary ischemia.",
            clinicalPearls = "Non-adrenergic vasopressor. Essential rescue for refractory ACE-inhibitor vasoplegia. Works in severe acidosis where catecholamines fail. NEVER exceed 0.04 units/min (causes bowel and coronary ischemia).",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Surviving Sepsis Campaign International Guidelines", "2021", "Critical Care Medicine"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        // ANTIHYPERTENSIVES
        Drug(
            id = "drug_labetalol",
            genericName = "Labetalol Hydrochloride",
            brandNames = "Trandate, Normodyne",
            synonyms = listOf("Trandate", "Normodyne", "Labetalol HCl"),
            category = DrugCategory.ANTIHYPERTENSIVES,
            drugClass = "Combined Selective Alpha-1 and Non-selective Beta-1/Beta-2 Adrenergic Receptor Blocker",
            mechanismOfAction = "Competitive antagonist at alpha-1, beta-1, and beta-2 adrenergic receptors. The ratio of alpha to beta blockade is approximately 1:7 after IV administration (and 1:3 orally). Produces peripheral vasodilation (alpha-1) while blunting reflex tachycardia (beta-1).",
            targetReceptor = "Alpha-1, Beta-1, Beta-2 adrenergic receptors",
            indications = listOf(
                "Acute hypertensive emergencies and perioperative hypertension",
                "Severe preeclampsia and eclampsia acute hypertension (ACOG first-line)",
                "Hypertension during emergence or laryngoscopy",
                "Acute aortic dissection (in combination with vasodilator)"
            ),
            onsetAndDuration = "Onset: 2 - 5 minutes IV; Peak: 5 - 15 minutes; Duration: 2 - 6 hours",
            adultDosing = "IV Bolus: 10 - 20 mg IV slow push over 2 minutes; repeat 20 - 40 mg every 10 minutes until target BP reached (Max cumulative dose: 300 mg)\nContinuous Infusion: 0.5 - 2.0 mg/min IV",
            pediatricDosing = "0.2 - 1.0 mg/kg IV slow push (Max single dose: 20 mg)",
            obstetricDosing = "First-line agent in severe preeclampsia/eclampsia: 20 mg IV over 2 minutes; repeat 40 mg, then 80 mg every 10-20 min up to 220-300 mg max; preserves uteroplacental blood flow",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Severe Perioperative Hypertension / Preeclampsia",
                    adultDose = "10 - 20 mg IV slow push over 2 minutes (repeat 20-40 mg every 10 min)",
                    elderlyDose = "5 - 10 mg IV initial bolus",
                    maxDose = "300 mg total cumulative dose",
                    unitNotes = "Wait full 5-10 minutes between boluses to observe peak effect before stacking doses!"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Refractory Hypertension",
                    adultDose = "0.5 - 2.0 mg/min IV titrated"
                )
            ),
            formulationsAndRoutes = listOf("5 mg/mL (20 mL, 40 mL vials = 100 mg, 200 mg)"),
            pharmacokinetics = "Hepatic glucuronidation by UGT1A1 and UGT2B7 to inactive metabolites. Elimination half-life ~5-8 hours.",
            onset = "2 - 5 minutes",
            peakEffect = "5 - 15 minutes",
            duration = "2 - 6 hours",
            elimination = "Hepatic glucuronidation; renal excretion of inactive metabolites",
            contraindications = listOf(
                "Severe reactive airway disease, bronchial asthma, or severe COPD (beta-2 blockade induces bronchospasm)",
                "Second or third-degree heart block (without permanent pacemaker)",
                "Severe sinus bradycardia (HR < 50 bpm)",
                "Overt cardiogenic shock or decompensated heart failure"
            ),
            cautions = listOf(
                "Bronchospasm: non-selective beta-2 antagonism can precipitate fatal status asthmaticus in asthmatics",
                "Severe bradycardia: watch for excessive heart rate reduction",
                "Peak effect takes 5-15 minutes; do not re-dose too quickly to avoid profound hypotension"
            ),
            adverseReactions = listOf("Bradycardia", "Orthostatic hypotension", "Bronchospasm", "Heart block", "Fatigue / dizziness"),
            interactions = listOf("Additive bradycardia with verapamil, diltiazem, and digoxin"),
            renalAdjustment = "Safe; no dose adjustment required.",
            hepaticAdjustment = "Hepatic clearance reduced; decrease maintenance doses.",
            pregnancyAndLactation = "First-line antihypertensive in pregnancy and severe preeclampsia (ACOG Guidelines); does not reduce uteroplacental blood flow.",
            elderlyConsiderations = "Start with lower bolus (5-10 mg); heightened risk of bradycardia and postural hypotension.",
            anesthesiaConsiderations = "First-line agent for acute severe hypertension in pregnancy/preeclampsia (target BP < 160/110 mmHg). Lowers blood pressure smoothly without reflex tachycardia.",
            reversalAgent = "Glucagon (3 - 5 mg IV), Epinephrine, Atropine, Isoproterenol for severe beta-blocker overdose.",
            toxicityManagement = "Treat severe bradycardia/hypotension with Atropine 0.5 - 1.0 mg IV, Epinephrine infusion, and IV Glucagon 3 - 5 mg bolus (bypasses beta-receptors via direct adenylate cyclase activation).",
            clinicalPearls = "First-line drug for severe preeclampsia (ACOG). 1:7 alpha-to-beta ratio IV lowers BP without reflex tachycardia. Wait 10 minutes between doses to avoid overshoot.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ACOG Practice Bulletin on Gestational Hypertension and Preeclampsia", "2020", "Obstetrics & Gynecology"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section")
        ),
        Drug(
            id = "drug_hydralazine",
            genericName = "Hydralazine Hydrochloride",
            brandNames = "Apresoline",
            synonyms = listOf("Apresoline", "Hydralazine"),
            category = DrugCategory.ANTIHYPERTENSIVES,
            drugClass = "Direct Arteriolar Smooth Muscle Vasodilator",
            mechanismOfAction = "Direct relaxation of arteriolar vascular smooth muscle (selective pre-capillary resistance vessels; zero effect on venous capacitance vessels), likely via interference with intracellular calcium mobilization and potassium channel activation.",
            targetReceptor = "Arteriolar vascular smooth muscle (calcium flux inhibition)",
            indications = listOf(
                "Severe preeclampsia and eclampsia acute hypertension (ACOG first-line alternative to labetalol)",
                "Acute perioperative hypertension refractory to beta-blockers",
                "Hypertension in patients with bradycardia (where beta-blockers are contraindicated)"
            ),
            onsetAndDuration = "Onset: 10 - 20 minutes IV (SLOW!); Peak: 20 - 30 minutes; Duration: 3 - 8 hours (unpredictably prolonged)",
            adultDosing = "5.0 - 10.0 mg IV slow push every 20-30 minutes as needed (Max single dose: 20 mg; Max daily: 200 mg)",
            pediatricDosing = "0.1 - 0.2 mg/kg IV every 4-6 hours (Max single dose: 10 mg)",
            obstetricDosing = "5.0 - 10.0 mg IV slow push for severe preeclamptic hypertension; repeat in 20 minutes if SBP >= 160 or DBP >= 110 mmHg (ACOG protocol); wait full 20 minutes before repeating!",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Severe Preeclampsia / Perioperative Hypertension",
                    adultDose = "5.0 - 10.0 mg IV slow push",
                    elderlyDose = "2.5 - 5.0 mg IV",
                    maxDose = "20 mg per episode; 200 mg/day",
                    unitNotes = "SLOW ONSET (15-20 min)! The most common clinical error is dose stacking (giving repeated doses at 5 min intervals), resulting in catastrophic delayed hypotension and fetal distress!"
                )
            ),
            formulationsAndRoutes = listOf("20 mg/mL (1 mL vials)"),
            pharmacokinetics = "Hepatic N-acetyltransferase 2 (NAT2) acetylation. Slow acetylators achieve higher plasma levels and have longer durations. Elimination half-life ~2-4 hours, but vascular effect lasts up to 6-8 hours due to tight tissue binding.",
            onset = "10 - 20 minutes (slow)",
            peakEffect = "20 - 30 minutes",
            duration = "3 - 8 hours",
            elimination = "Hepatic acetylation (NAT2); renal excretion of metabolites",
            contraindications = listOf(
                "Severe coronary artery disease / acute myocardial infarction (intense reflex tachycardia precipitates myocardial ischemia)",
                "Mitral valve rheumatic heart disease / dissecting aortic aneurysm"
            ),
            cautions = listOf(
                "Marked reflex tachycardia and palpitations: baroreceptor response to arteriolar vasodilation increases heart rate, stroke volume, and cardiac output",
                "Delayed peak (20-30 min): NEVER re-dose sooner than 20 minutes to prevent catastrophic dose stacking and fetal bradycardia",
                "Drug-induced lupus syndrome with chronic high-dose oral therapy (> 200 mg/day)"
            ),
            adverseReactions = listOf("Reflex tachycardia", "Headache (cerebral vasodilation)", "Flushing", "Hypotension", "Fetal distress if maternal BP drops precipitously"),
            interactions = listOf("Beta-blockers prevent reflex tachycardia and enhance antihypertensive effect"),
            renalAdjustment = "Safe; extend dosing interval in severe renal impairment.",
            hepaticAdjustment = "Slow acetylators have higher plasma concentrations; reduce dose.",
            pregnancyAndLactation = "First-line agent for acute hypertension in pregnancy/preeclampsia (ACOG); safe for fetus when administered with strict 20-min observation intervals.",
            elderlyConsiderations = "Risk of myocardial ischemia from reflex tachycardia; start with 2.5-5.0 mg.",
            anesthesiaConsiderations = "ACOG first-line drug for severe preeclampsia. ALWAYS wait 20 minutes before re-dosing to prevent maternal hypotension and resultant fetal decelerations.",
            reversalAgent = "Vasopressors (Phenylephrine, Norepinephrine) and IV fluids.",
            toxicityManagement = "Treat precipitous hypotension with IV crystalloid bolus and Phenylephrine boluses (50-100 mcg). Treat reflex tachycardia with low-dose Esmolol or Metoprolol if coronary disease is present.",
            clinicalPearls = "Onset is 15-20 minutes (do NOT re-dose before 20 minutes, or catastrophic delayed hypotension occurs). Causes prominent reflex tachycardia. First-line for preeclampsia.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("ACOG Practice Bulletin on Gestational Hypertension and Preeclampsia", "2020", "Obstetrics & Gynecology"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_cesarean_section")
        ),
        Drug(
            id = "drug_nicardipine",
            genericName = "Nicardipine Hydrochloride",
            brandNames = "Cardene IV",
            synonyms = listOf("Cardene", "Cardene IV"),
            category = DrugCategory.ANTIHYPERTENSIVES,
            drugClass = "Second-Generation Dihydropyridine Calcium Channel Blocker",
            mechanismOfAction = "Selectively blocks L-type voltage-gated calcium channels in vascular smooth muscle cells, inhibiting inward calcium ion influx and producing potent peripheral and coronary arterial vasodilation. Highly vascular selective (minimal negative inotropic or chronotropic effect on cardiac myocardium).",
            targetReceptor = "L-type Voltage-gated Calcium Channel (Cav1.2 in vascular smooth muscle)",
            indications = listOf(
                "Short-term treatment of acute perioperative hypertension",
                "Neurosurgical hypertension (aneurysmal subarachnoid hemorrhage, intracranial hemorrhage, acute ischemic stroke)",
                "Severe preeclampsia hypertension refractory to labetalol and hydralazine"
            ),
            onsetAndDuration = "Onset: 1 - 2 minutes IV; Peak: 10 - 15 minutes; Duration: 1 - 4 hours following infusion cessation",
            adultDosing = "Continuous IV Infusion: Start at 5.0 mg/hr; titrate up by 2.5 mg/hr every 5 to 15 minutes to target BP (Max: 15.0 mg/hr). Once target BP achieved, reduce to maintenance rate of 3.0 mg/hr\nEmergency IV Bolus: 0.1 - 0.2 mg IV push (specialized OR titrations)",
            pediatricDosing = "0.5 - 2.0 mcg/kg/min IV continuous infusion",
            obstetricDosing = "5.0 mg/hr infusion titrated up to 15.0 mg/hr for severe preeclampsia refractory to first-line agents",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Acute Hypertensive Crisis / Neuroanesthesia BP Control",
                    adultDose = "Start at 5.0 mg/hr; titrate by 2.5 mg/hr every 5-15 min to desired BP (Max 15 mg/hr)",
                    elderlyDose = "Start at 2.5 - 5.0 mg/hr",
                    maxDose = "15.0 mg/hr",
                    unitNotes = "Dedicated IV line required; change peripheral infusion site every 12 hours to avoid local thrombophlebitis"
                )
            ),
            formulationsAndRoutes = listOf("25 mg/10 mL (2.5 mg/mL) ampules (must be diluted); ready-to-use infusion bags (20 mg in 200 mL or 40 mg in 200 mL 0.9% NaCl)"),
            pharmacokinetics = "Highly protein-bound (> 95%); extensive hepatic metabolism by CYP3A4 to inactive metabolites. Elimination half-life ~14 hours (terminal), but clinical redistribution half-life is ~40-60 minutes.",
            onset = "1 - 2 minutes",
            peakEffect = "10 - 15 minutes",
            duration = "1 - 4 hours",
            elimination = "Hepatic CYP3A4 metabolism; 60% excreted in urine, 35% in feces",
            contraindications = listOf(
                "Advanced aortic stenosis (severe fixed left ventricular outflow obstruction prevents adaptation to decreased afterload)",
                "Known hypersensitivity"
            ),
            cautions = listOf(
                "Peripheral venous irritation: causes thrombophlebitis if infused in small peripheral veins (change site every 12 hours or use central line)",
                "Titrate every 5-15 minutes; abrupt rapid escalations cause overshoot hypotension",
                "Reflex tachycardia can occur mildly"
            ),
            adverseReactions = listOf("Hypotension", "Headache", "Flushing", "Local thrombophlebitis", "Mild reflex tachycardia"),
            interactions = listOf("CYP3A4 inhibitors increase plasma nicardipine levels", "Additive hypotension with general anesthetics"),
            renalAdjustment = "Safe; no dose adjustment needed; ideal in renal hypertensive emergencies.",
            hepaticAdjustment = "Hepatic metabolism reduced; titrate carefully.",
            pregnancyAndLactation = "Safe second-line agent for refractory preeclampsia; relaxes myometrium (tocolytic effect).",
            elderlyConsiderations = "Start low at 2.5 - 5.0 mg/hr; monitor for postural hypotension.",
            anesthesiaConsiderations = "Premier agent for neuroanesthesia and acute intracranial hemorrhage: highly predictable, titratable, lowers SVR without increasing intracranial pressure (ICP), and preserves cerebral perfusion.",
            reversalAgent = "IV Calcium Chloride (1 g) or Calcium Gluconate, fluids, vasopressors.",
            toxicityManagement = "Discontinue infusion immediately. Administer Calcium Chloride (1 g IV over 5 min) or Calcium Gluconate (3 g IV), crystalloid boluses, and vasopressors (Norepinephrine, Phenylephrine).",
            clinicalPearls = "Premier agent in neurovascular surgery and stroke. Zero negative inotropic depression (unlike diltiazem/verapamil). Change peripheral IV sites every 12 hours to avoid phlebitis.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier"),
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy")
        ),
        Drug(
            id = "drug_esmolol",
            genericName = "Esmolol Hydrochloride",
            brandNames = "Brevibloc",
            synonyms = listOf("Brevibloc", "Esmolol HCl"),
            category = DrugCategory.ANTIHYPERTENSIVES,
            drugClass = "Ultra-short-acting Cardioselective Beta-1 Adrenergic Receptor Antagonist",
            mechanismOfAction = "Selectively competes for and blocks cardiac beta-1 adrenergic receptors, decreasing heart rate, myocardial contractility, and AV node conduction velocity. Possesses an ester linkage that allows ultra-rapid red blood cell esterase hydrolysis.",
            targetReceptor = "Beta-1 adrenergic receptor (cardioselective)",
            indications = listOf(
                "Acute blunting of hemodynamic response (tachycardia & hypertension) during laryngoscopy and intubation",
                "Tachycardia and hypertension during emergence and extubation",
                "Acute supraventricular tachycardia (SVT), rapid atrial fibrillation, or sinus tachycardia",
                "Acute aortic dissection (first-line heart rate control to decrease dP/dt)"
            ),
            onsetAndDuration = "Onset: Very rapid (1 - 2 minutes)\nPeak: 5 minutes\nDuration: 9 - 10 minutes (ultra-short elimination half-life of 9 minutes!)",
            adultDosing = "Intubation / Extubation Blunting: 0.5 - 1.0 mg/kg IV bolus over 30-60 seconds (typically 50 - 100 mg)\nAcute SVT / Tachycardia: Loading dose of 500 mcg/kg (0.5 mg/kg) over 1 minute, followed by maintenance infusion of 50 - 200 mcg/kg/min IV",
            pediatricDosing = "Loading dose: 0.5 mg/kg IV over 1 minute; Infusion: 50 - 250 mcg/kg/min IV",
            obstetricDosing = "Safe for acute maternal tachycardia blunting; minimal fetal transfer due to ultra-short 9-minute half-life",
            dosingProtocols = listOf(
                DrugDosing(
                    route = "IV Bolus",
                    indication = "Blunting Laryngoscopy / Extubation Tachycardia",
                    adultDose = "0.5 - 1.0 mg/kg IV over 30 - 60 seconds given 90 seconds prior to intubation/extubation",
                    pediatricDose = "0.5 mg/kg IV",
                    elderlyDose = "0.25 - 0.5 mg/kg IV",
                    maxDose = "100 mg single bolus",
                    unitNotes = "Total Body Weight (TBW)"
                ),
                DrugDosing(
                    route = "IV Continuous Infusion",
                    indication = "Supraventricular Tachyarrhythmias / Aortic Dissection",
                    adultDose = "50 - 200 mcg/kg/min IV (titrated up in 50 mcg/kg/min increments)",
                    maxDose = "300 mcg/kg/min IV"
                )
            ),
            formulationsAndRoutes = listOf("10 mg/mL (10 mL vials = 100 mg ready-to-use)", "20 mg/mL (5 mL vials)"),
            pharmacokinetics = "Ultra-rapid hydrolysis by cytoplasmic esterases in the cytosol of red blood cells to acid metabolite (1,500x less potent; excreted in urine) and methanol. Completely independent of renal or hepatic function. Elimination half-life is 9 minutes.",
            onset = "1 - 2 minutes",
            peakEffect = "5 minutes",
            duration = "9 - 10 minutes",
            elimination = "Red blood cell cytosol esterase hydrolysis (NOT plasma pseudocholinesterase)",
            contraindications = listOf(
                "Severe sinus bradycardia (HR < 50 bpm)",
                "Second or third-degree AV block (without pacemaker)",
                "Overt cardiogenic shock or decompensated heart failure",
                "Severe bronchospastic disease"
            ),
            cautions = listOf(
                "Hypotension and bradycardia: resolves within 10-15 minutes of stopping the infusion due to ultra-short half-life",
                "Relative cardioselectivity is lost at high infusion rates (> 200 mcg/kg/min; can cause bronchospasm)"
            ),
            adverseReactions = listOf("Hypotension (dose-dependent)", "Bradycardia", "Heart block", "Local venous irritation"),
            interactions = listOf("Enhances neuromuscular blockade; additive bradycardia with digoxin, verapamil, and diltiazem"),
            renalAdjustment = "Safe; red blood cell clearance is completely independent of renal function.",
            hepaticAdjustment = "Safe; red blood cell clearance is completely independent of hepatic function.",
            pregnancyAndLactation = "Preferred beta-blocker for acute intraoperative use due to ultra-short 9-minute half-life.",
            elderlyConsiderations = "Use lower initial bolus (0.25-0.5 mg/kg) to avoid sudden hypotension.",
            anesthesiaConsiderations = "The ultimate 'test-drive' beta-blocker: if adverse bradycardia or hypotension occurs, its effect vanishes in under 10 minutes. Perfect for blunting intubation and extubation hemodynamics in CAD patients.",
            reversalAgent = "Discontinuation (half-life 9 min). Atropine, Glucagon, Isoproterenol if emergency reversal needed.",
            toxicityManagement = "Stop infusion immediately (recovers within 10 minutes). If profound bradycardia persists, administer Atropine 0.5 - 1.0 mg IV, Epinephrine, or Glucagon 3 - 5 mg IV.",
            clinicalPearls = "Ultra-short half-life of 9 minutes (metabolized by red blood cell esterases). The safest beta-blocker in anesthesia because any hypotension or bradycardia disappears within 10 minutes of stopping.",
            reviewStatus = "Clinically Reviewed - Tier 1 Authoritative Guidelines",
            references = listOf(
                DrugReference("Morgan & Mikhail's Clinical Anesthesiology, 7th Edition", "2022", "McGraw-Hill"),
                DrugReference("Miller's Anesthesia, 9th Edition", "2020", "Elsevier")
            ),
            relatedGuideIds = listOf("guide_emergency_laparotomy", "guide_hip_fracture")
        )
    )
}
