package com.example.data.repository.anatomy

import com.example.R
import com.example.data.model.*

object RenalHepaticEndocrineData {

    val renalTopic = AnatomyTopic(
        id = "anatomy_renal_electrolytes",
        title = "Renal Microanatomy & Fluid-Electrolyte Balance",
        subtitle = "Glomerular filtration, countercurrent multiplier & acid-base buffer systems",
        category = AnatomySystemCategory.RENAL_ELECTROLYTES,
        difficulty = AnatomyDifficulty.INTERMEDIATE,
        learningObjectives = listOf(
            "Trace the microanatomy of the nephron from Bowman's capsule to the collecting duct and identify sites of drug action.",
            "Explain the countercurrent multiplier mechanism in the loop of Henle and medullary hypertonicity.",
            "Analyze the Renin-Angiotensin-Aldosterone System (RAAS) and anesthetic-induced decreases in renal blood flow.",
            "Calculate serum anion gap and interpret metabolic acid-base disorders using Stewart's physicochemical approach."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "glomerulus_bowman",
                name = "Glomerulus & Afferent/Efferent Arterioles",
                normalizedCoordinateX = 0.35f,
                normalizedCoordinateY = 0.28f,
                description = "Capillary tuft invaginating into Bowman's capsule. Receives ~20-25% of cardiac output (1.0-1.2 L/min renal blood flow). Glomerular filtration rate (GFR) averages 125 mL/min (~180 L/day).",
                innervationOrVascular = "Afferent arteriole tone regulated by prostaglandins and myogenic reflex; efferent arteriole tone regulated by Angiotensin II.",
                clinicalPearl = "NSAIDs block prostaglandin-mediated afferent arteriolar vasodilation, while ACE-inhibitors/ARBs block angiotensin II-mediated efferent arteriolar vasoconstriction. Combined use during hypotensive anesthesia causes acute severe drops in intraglomerular pressure and acute kidney injury (AKI).",
                relatedIntervention = "Withhold ACE-inhibitors/ARBs 24 hours prior to major surgeries with expected blood loss or fluid shifts"
            ),
            AnatomicalStructure(
                id = "loop_of_henle_countercurrent",
                name = "Loop of Henle (Countercurrent Multiplier)",
                normalizedCoordinateX = 0.48f,
                normalizedCoordinateY = 0.65f,
                description = "U-shaped hairpin loop dipping into the hypertonic renal medulla. Descending limb is permeable to water but impermeable to NaCl; thick ascending limb (TAL) is impermeable to water and actively pumps Na+/K+/2Cl- into the medullary interstitium.",
                innervationOrVascular = "Vasa recta capillaries function as countercurrent exchangers to maintain the osmotic gradient without washing away solutes.",
                clinicalPearl = "Loop diuretics (Furosemide, Bumetanide) bind the Na+/K+/2Cl- cotransporter in the thick ascending limb, abolishing the medullary concentration gradient and producing profound naturesis and hypokalemic metabolic alkalosis.",
                relatedIntervention = "Administration of loop diuretics for acute oliguria requires confirmation of euvolemia; never use diuretics as a substitute for volume resuscitation"
            ),
            AnatomicalStructure(
                id = "distal_tubule_collecting_duct",
                name = "Distal Convoluted Tubule & Collecting Duct",
                normalizedCoordinateX = 0.65f,
                normalizedCoordinateY = 0.35f,
                description = "Aldosterone acts on principal cells to reabsorb Na+ and secrete K+ and H+ via epithelial sodium channels (ENaC). Antidiuretic Hormone (ADH / Vasopressin) binds V2 receptors to insert aquaporin-2 water channels, enabling water reabsorption.",
                innervationOrVascular = "Juxtaglomerular apparatus (macula densa) monitors tubular sodium delivery and releases renin when perfusion drops.",
                clinicalPearl = "Surgical stress, pain, hypovolemia, and positive pressure ventilation stimulate massive non-osmotic ADH release (SIADH-like state), causing oliguria. Serum sodium should be monitored during long infusions of hypotonic fluids.",
                relatedIntervention = "Electrolyte management: Hyperkalemia (> 6.0 mEq/L) treatment: Calcium gluconate 1-2 g (stabilizes cardiac membrane), Insulin 10 units + D50W, Beta-2 agonists, hyperventilation"
            )
        ),
        normalPhysiology = "The kidneys maintain extracellular volume, osmolarity, electrolyte concentrations, and acid-base balance. The bicarbonate buffer system handles volatile acid (CO2) elimination via the lungs and non-volatile acid (H+) excretion via the kidneys. Normal Anion Gap = [Na+] - ([Cl-] + [HCO3-]) = 8 to 12 mEq/L.",
        clinicalSignificance = "Acute tubular necrosis (ATN) is a devastating perioperative complication triggered by prolonged hypotension, hypovolemia, nephrotoxic agents (contrast, aminoglycosides, NSAIDs), and myoglobinuria from rhabdomyolysis.",
        anesthesiaImplications = "General and regional anesthesia decrease renal blood flow and GFR by 15-40% through systemic vasodilation, cardiac depression, and positive pressure ventilation decreasing venous return. Maintain MAP >= 65 mmHg to preserve renal autoregulation.",
        commonExamDistinctions = listOf(
            "High Anion Gap Metabolic Acidosis mnemonic (MUDPILES): Methanol, Uremia, Diabetic ketoacidosis, Paraldehyde/Propylene glycol, Iron/Isoniazid, Lactic acidosis, Ethylene glycol, Salicylates.",
            "Normal Anion Gap (Hyperchloremic) Metabolic Acidosis causes: Diarrhea, renal tubular acidosis (RTA), carbonic anhydrase inhibitors (Acetazolamide), and massive 0.9% Normal Saline resuscitation.",
            "Potassium shifts: Acidosis shifts K+ OUT of cells (hyperkalemia: ~0.6 mEq/L K+ increase per 0.1 pH decrease); Alkalosis shifts K+ INTO cells (hypokalemia).",
            "Sevoflurane degradation by desiccated carbon dioxide absorbents produces Compound A (vinyl ether), which causes nephrotoxicity in rats at high concentrations; minimum fresh gas flow is recommended at 1 L/min for <= 2 hours or 2 L/min for longer cases."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_renal_1",
                question = "A patient with severe intra-abdominal sepsis receives 6 liters of 0.9% Normal Saline over 4 hours. Which acid-base disturbance is most likely to develop?",
                options = listOf(
                    "Normal anion gap (hyperchloremic) metabolic acidosis",
                    "High anion gap lactic acidosis",
                    "Hypochloremic metabolic alkalosis",
                    "Respiratory alkalosis with compensatory metabolic acidosis"
                ),
                correctIndex = 0,
                explanation = "0.9% NaCl has a chloride concentration of 154 mEq/L (much higher than plasma chloride of ~100-104 mEq/L). Infusing large volumes expands plasma with excess chloride, reducing the strong ion difference (SID) and producing normal anion gap hyperchloremic metabolic acidosis.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_renal_2",
                question = "What is the primary mechanism of action of loop diuretics such as furosemide?",
                options = listOf(
                    "Inhibition of the Na+/K+/2Cl- cotransporter in the thick ascending limb of the loop of Henle",
                    "Inhibition of the Na+/Cl- cotransporter in the distal convoluted tubule",
                    "Antagonism of mineralocorticoid receptors in the cortical collecting tubule",
                    "Inhibition of carbonic anhydrase in the proximal convoluted tubule"
                ),
                correctIndex = 0,
                explanation = "Furosemide reversibly inhibits the luminal Na+/K+/2Cl- cotransporter in the thick ascending limb of Henle's loop, blocking active solute reabsorption and disrupting the hypertonic medullary osmotic gradient.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_emergency_laparotomy"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Renal Nephron Architecture & Countercurrent Concentration System",
            topicName = "Renal Physiology & Fluid/Electrolyte Balance",
            creatorOrInstitution = "OpenStax Anatomy and Physiology",
            sourceUrl = "https://commons.wikimedia.org/wiki/File:Physiology_of_Nephron.svg",
            licenseAndAttribution = "CC BY-SA 4.0 Creative Commons Attribution-ShareAlike",
            localDrawableRes = R.drawable.img_renal_nephron,
            localFileName = "img_renal_nephron.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Verified Active Clinical Diagram with Calibrated Nephron Segment Landmarks"
        )
    )

    val hepaticTopic = AnatomyTopic(
        id = "anatomy_hepatic_metabolism",
        title = "Hepatic Microcirculation & Drug Biotransformation",
        subtitle = "Dual blood supply, Cytochrome P450, Phase I/II metabolism & cirrhosis",
        category = AnatomySystemCategory.HEPATIC_METABOLISM,
        difficulty = AnatomyDifficulty.INTERMEDIATE,
        learningObjectives = listOf(
            "Describe the dual hepatic blood supply: Portal Vein (75% flow, 50% O2) vs Hepatic Artery (25% flow, 50% O2).",
            "Explain the hepatic arterial buffer response (HABR) and its attenuation by volatile anesthetics.",
            "Distinguish Phase I (CYP450 oxidation, reduction, hydrolysis) from Phase II (glucuronidation, sulfation) reactions.",
            "Analyze the impact of end-stage liver disease (ESLD), portosystemic shunting, and coagulopathy on anesthetic pharmacokinetics."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "hepatic_portal_triad",
                name = "Portal Triad & Hepatic Acinus (Zones 1, 2, 3)",
                normalizedCoordinateX = 0.26f,
                normalizedCoordinateY = 0.85f,
                description = "The portal triad consists of the Portal Vein, Proper Hepatic Artery, and Common Bile Duct running in the hepatoduodenal ligament (foramen of Winslow). Blood flows into hepatic sinusoids toward the central vein.",
                innervationOrVascular = "Liver receives 25% of cardiac output (~1500 mL/min). Zone 1 (periportal) has highest oxygen tension; Zone 3 (centrilobular) surrounds central vein and has lowest oxygen tension.",
                clinicalPearl = "Zone 3 has the highest concentration of Cytochrome P450 enzymes and is the most susceptible to ischemic injury, hypoxia, and toxic metabolite necrosis (e.g. acetaminophen toxicity, halothane hepatitis).",
                relatedIntervention = "Pringle maneuver (temporary clamping of hepatoduodenal ligament during liver trauma or resection): limits warm ischemia to < 60 minutes"
            ),
            AnatomicalStructure(
                id = "cyp450_microsomal_enzymes",
                name = "Cytochrome P450 Enzyme System (CYP3A4, CYP2D6)",
                normalizedCoordinateX = 0.88f,
                normalizedCoordinateY = 0.25f,
                description = "Heme-containing monooxygenases located in the smooth endoplasmic reticulum of hepatocytes. CYP3A4 metabolizes >50% of perioperative drugs (fentanyl, midazolam, lidocaine, ropivacaine, methadone).",
                innervationOrVascular = "Enzyme inducers: Rifampin, phenytoin, carbamazepine, St. John's wort. Enzyme inhibitors: Cimetidine, erythromycin, diltiazem, ketoconazole, grapefruit juice.",
                clinicalPearl = "Enzyme inhibition leads to acute drug accumulation and toxicity (e.g. delayed emergence from midazolam or prolonged opioid narcosis). Induction accelerates clearance, causing apparent drug resistance.",
                relatedIntervention = "Dose titration of sedatives and analgesics based on liver synthetic function and medication history"
            ),
            AnatomicalStructure(
                id = "cirrhotic_coagulopathy_shunts",
                name = "Classic Hepatic Lobule & Central Vein",
                normalizedCoordinateX = 0.26f,
                normalizedCoordinateY = 0.48f,
                description = "Hexagonal functional lobule centered around the terminal hepatic venule (central vein). Radiating cords of hepatocytes and sinusoids lined with fenestrated endothelium and Kupffer macrophages.",
                innervationOrVascular = "Liver synthesizes all coagulation factors EXCEPT Factor VIII and von Willebrand factor (synthesized by endothelial cells).",
                clinicalPearl = "End-stage liver disease patients have a 'rebalanced' coagulopathy: reduced procoagulant factors (II, VII, IX, X, fibrinogen) are balanced by reduced anticoagulant proteins (protein C, protein S, antithrombin III). Standard PT/INR overestimates bleeding risk; use viscoelastic testing (TEG/ROTEM) to guide transfusion.",
                relatedIntervention = "RSI intubation without nasogastric tube trauma in esophageal varices; maintain euvolemic status to avoid variceal rupture"
            ),
            AnatomicalStructure(
                id = "hepatic_acinus_zonation",
                name = "Hepatic Acinus Oxygen Gradient (Zones 1-3)",
                normalizedCoordinateX = 0.75f,
                normalizedCoordinateY = 0.45f,
                description = "Diamond-shaped physiological unit oriented around terminal distributing vessels. Zone 1 (periportal) receives blood richest in oxygen and nutrients; Zone 3 (centrilobular) experiences lowest physiological PO2.",
                innervationOrVascular = "Dual blood supply: Portal Vein supplies 75% of volume and 50% of oxygen; Hepatic Artery supplies 25% of volume and 50% of oxygen.",
                clinicalPearl = "Hepatic Arterial Buffer Response (HABR): When portal flow decreases (e.g. during abdominal insufflation or caval compression), adenosine washes out slower, causing compensatory hepatic arterial vasodilation.",
                relatedIntervention = "Maintain mean arterial pressure > 65 mmHg to preserve hepatic arterial buffer capacity"
            )
        ),
        normalPhysiology = "The liver maintains glucose homeostasis, bile acid synthesis, albumin production, amino acid transamination, and elimination of endogenous toxins (ammonia converted to urea). High-clearance drugs (propofol, fentanyl, lidocaine) depend on hepatic blood flow (perfusion-limited); low-clearance drugs (diazepam, rocuronium) depend on intrinsic enzyme capacity and protein binding (capacity-limited).",
        clinicalSignificance = "Cirrhosis increases the volume of distribution (Vd) of hydrophilic neuromuscular blockers (rocuronium, vecuronium) due to ascites, requiring larger loading doses, but prolonged elimination half-life requires less frequent redosing.",
        anesthesiaImplications = "All volatile anesthetics cause dose-dependent reductions in portal vein blood flow. Isoflurane and desflurane preserve the Hepatic Arterial Buffer Response (HABR—where hepatic artery dilates when portal flow drops) better than older agents.",
        commonExamDistinctions = listOf(
            "Factor VIII and von Willebrand factor are synthesized by vascular endothelial cells, NOT by hepatocytes; therefore, Factor VIII levels are normal or elevated in severe liver cirrhosis.",
            "Pseudocholinesterase (plasma butyrylcholinesterase) is synthesized exclusively by the liver; severe hepatic failure markedly prolongs the duration of succinylcholine and mivacurium.",
            "Halothane hepatitis: immune-mediated hypersensitivity reaction against trifluoroacetylated liver microsomal proteins formed by CYP2E1 oxidation (1 in 35,000 cases). Modern fluorinated volatiles produce far less trifluoroacetylated intermediates.",
            "Hepatic Arterial Buffer Response (HABR): When portal venous flow decreases, adenosine accumulates in the space of Mall, producing compensatory hepatic arterial vasodilation."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_hepatic_1",
                question = "Which coagulation factor is NOT synthesized by hepatocytes and remains normal or elevated in end-stage liver disease?",
                options = listOf(
                    "Factor VIII",
                    "Factor VII",
                    "Factor II (Prothrombin)",
                    "Factor X"
                ),
                correctIndex = 0,
                explanation = "Factor VIII (along with von Willebrand factor) is synthesized by vascular endothelial cells throughout the body rather than by hepatocytes. In severe cirrhosis, Factor VIII levels are often markedly elevated due to systemic endothelial activation.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_hepatic_2",
                question = "Centrilobular necrosis (Zone 3 of the hepatic acinus) is most commonly caused by which pathophysiological mechanism?",
                options = listOf(
                    "Severe arterial hypoperfusion and toxic metabolite accumulation from CYP450 activity",
                    "Direct viral replication in high-oxygen periportal capillaries",
                    "Primary portal venous thrombosis sparing the hepatic artery",
                    "Mechanical compression of the common bile duct"
                ),
                correctIndex = 0,
                explanation = "Zone 3 (surrounding the central vein) is furthest from the supplying portal triad and operates at the lowest physiological oxygen tension. It also contains the highest concentration of Cytochrome P450 enzymes, making it maximally vulnerable to ischemia-hypoxia and toxic metabolites.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_lap_chole", "guide_emergency_laparotomy"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Hepatic Acinar Architecture, Portal Triad & Metabolic Zonation",
            topicName = "Hepatic Function & Drug Metabolism",
            creatorOrInstitution = "Anesthesia Pro Medical Illustration Team / OpenStax Clinical Reference",
            sourceUrl = "https://commons.wikimedia.org/wiki/File:Hepatic_structure.svg",
            licenseAndAttribution = "Creative Commons CC BY-SA 4.0 / Anesthesia Clinical Reference",
            localDrawableRes = R.drawable.img_hepatic_metabolism,
            localFileName = "img_hepatic_metabolism.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Verified Active Clinical Diagram with Calibrated Acinar Landmarks"
        )
    )

    val endocrineTopic = AnatomyTopic(
        id = "anatomy_endocrine_system",
        title = "Endocrine Axis Physiology & Perioperative Stress",
        subtitle = "HPA axis suppression, thyroid storm, pheochromocytoma & perioperative diabetes",
        category = AnatomySystemCategory.ENDOCRINE_SYSTEM,
        difficulty = AnatomyDifficulty.ADVANCED,
        learningObjectives = listOf(
            "Identify the hypothalamic-pituitary-adrenal (HPA) axis feedback loops and determine indications for stress-dose corticosteroid coverage.",
            "Differentiate thyroid storm from malignant hyperthermia and pheochromocytoma.",
            "Formulate the pharmacological preparation for pheochromocytoma (alpha-blockade preceding beta-blockade).",
            "Manage intraoperative hyperglycemia, diabetic ketoacidosis (DKA), and insulin kinetics."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "adrenal_cortex_hpa_axis",
                name = "Adrenal Cortex (Glomerulosa, Fasciculata, Reticularis)",
                normalizedCoordinateX = 0.52f,
                normalizedCoordinateY = 0.40f,
                description = "Zona glomerulosa produces mineralocorticoids (aldosterone, regulated by RAAS); Zona fasciculata produces glucocorticoids (cortisol, regulated by ACTH); Zona reticularis produces androgens ('Salt, Sugar, Sex: GFR').",
                innervationOrVascular = "Splanchnic sympathetic innervation to medulla; cortex is regulated by humoral ACTH and potassium.",
                clinicalPearl = "Exogenous prednisone > 5 mg/day for > 3 weeks within the past year can suppress the HPA axis. Acute surgical stress without supplemental hydrocortisone can trigger life-threatening acute adrenal crisis (refractory vasodilation, hypoglycemia, hyperkalemia, hyponatremia).",
                relatedIntervention = "Stress-dose steroids: Minor surgery = take normal home dose; Moderate = 50 mg IV hydrocortisone; Major surgery = 100 mg IV hydrocortisone followed by 50 mg q8h for 24 hours"
            ),
            AnatomicalStructure(
                id = "adrenal_medulla_pheochromocytoma",
                name = "Adrenal Medulla & Chromaffin Catecholamine Cascade",
                normalizedCoordinateX = 0.60f,
                normalizedCoordinateY = 0.70f,
                description = "Modified sympathetic postganglionic chromaffin cells releasing Epinephrine (80%) and Norepinephrine (20%) directly into systemic circulation upon preganglionic acetylcholine stimulation.",
                innervationOrVascular = "Abundant vascularity draining via right adrenal vein directly into IVC (left drains into left renal vein).",
                clinicalPearl = "Pheochromocytoma rule of 10s: 10% bilateral, 10% extra-adrenal, 10% malignant, 10% familial. Roizen criteria: Phenoxybenzamine (alpha-1 non-selective antagonist) must be initiated 10-14 days BEFORE beta-blockers. Administering a beta-blocker first leaves alpha-mediated vasoconstriction unopposed, causing malignant hypertensive crisis and pulmonary edema.",
                relatedIntervention = "Preoperative prep: Alpha-blockade (Phenoxybenzamine/Doxazosin) -> Beta-blockade for reflex tachycardia -> Volume expansion -> Intraoperative nitroprusside / phentolamine ready"
            ),
            AnatomicalStructure(
                id = "hpa_hypothalamic_axis",
                name = "Hypothalamic-Pituitary-Adrenal (HPA) Axis",
                normalizedCoordinateX = 0.16f,
                normalizedCoordinateY = 0.24f,
                description = "Corticotropin-releasing hormone (CRH) from the paraventricular nucleus of the hypothalamus stimulates anterior pituitary ACTH release, driving adrenocortical cortisol synthesis with negative feedback control.",
                innervationOrVascular = "Hypophyseal portal venous circulation connects median eminence to adenohypophysis.",
                clinicalPearl = "Surgical trauma overrides normal negative feedback, generating an obligatory neuroendocrine hypercatabolic stress state proportional to operative invasiveness.",
                relatedIntervention = "Preoperative evaluation of chronic exogenous steroid consumption"
            )
        ),
        normalPhysiology = "The neuroendocrine surgical stress response stimulates the sympathetic nervous system and HPA axis, releasing cortisol, epinephrine, glucagon, and growth hormone while suppressing insulin. This creates catabolism, gluconeogenesis, lipolysis, and insulin resistance, elevating blood glucose.",
        clinicalSignificance = "Uncontrolled intraoperative hyperglycemia (> 180-200 mg/dL) impairs neutrophil phagocytosis, increases surgical site infection risk, worsens ischemic neuronal injury, and causes osmotic diuresis.",
        anesthesiaImplications = "Etomidate causes temporary adrenocortical suppression by dose-dependently inhibiting 11-beta-hydroxylase, the terminal enzyme in cortisol synthesis. While safe for single-dose induction in stable patients, continuous infusions or use in septic shock carries significant controversy.",
        commonExamDistinctions = listOf(
            "Pheochromocytoma: ALWAYS alpha-block before beta-block! Beta-blockade alone blocks beta-2 vasodilation, leaving systemic alpha-1 vasoconstriction unopposed -> hypertensive cerebral hemorrhage.",
            "Etomidate inhibits 11-beta-hydroxylase, which converts 11-deoxycortisol to cortisol and 11-deoxycorticosterone to corticosterone.",
            "Thyroid storm vs Malignant Hyperthermia: MH features severe generalized muscle rigidity, rapid severe hypercapnia (EtCO2 > 60-80), massive respiratory and metabolic acidosis, and hyperkalemia. Thyroid storm features hyperthermia and tachycardia but NO muscle rigidity.",
            "Diabetic autonomic neuropathy (DAN): Hallmarks are resting tachycardia, lack of heart rate variability with respiration, gastroparesis (full stomach aspiration risk), and severe post-induction hypotension with blunted response to indirect vasopressors (ephedrine; use direct phenylephrine or norepinephrine)."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_endo_1",
                question = "Why must alpha-adrenergic receptor blockade strictly precede beta-adrenergic blockade in patients with pheochromocytoma?",
                options = listOf(
                    "Beta-blockade first abolishes beta-2 mediated vasodilation, leaving unopposed alpha-1 vasoconstriction and causing fatal hypertensive crisis",
                    "Beta-blockade inhibits adrenal catecholamine release, preventing diagnostic localization",
                    "Alpha-blockers are rapidly cleared by catechol-O-methyltransferase unless preceded by beta antagonists",
                    "Beta-blockers precipitate severe hypoglycemia by preventing hepatic glycogenolysis"
                ),
                correctIndex = 0,
                explanation = "In pheochromocytoma, excessive circulating catecholamines stimulate both alpha and beta receptors. If a beta-blocker is administered alone, beta-2 mediated skeletal muscle vasodilation is abolished while alpha-1 vasoconstriction remains unopposed, precipitating severe hypertensive crisis and acute pulmonary edema.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_endo_2",
                question = "Which enzyme in the adrenocortical steroidogenesis pathway is reversibly inhibited by the intravenous induction agent etomidate?",
                options = listOf(
                    "11-beta-hydroxylase",
                    "21-hydroxylase",
                    "17-alpha-hydroxylase",
                    "Aromatase"
                ),
                correctIndex = 0,
                explanation = "Etomidate contains an imidazole ring that reversibly inhibits 11-beta-hydroxylase, preventing the conversion of 11-deoxycortisol to active cortisol, causing transient adrenal suppression lasting 6 to 24 hours.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_emergency_laparotomy", "guide_lap_chole"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Adrenal Gland Corticosteroid & Catecholamine Endocrine Microanatomy",
            topicName = "Endocrine Physiology",
            creatorOrInstitution = "Anesthesia Pro Medical Illustration Team / NCI SEER Reference",
            sourceUrl = "https://commons.wikimedia.org/wiki/File:Illu_adrenal_gland.jpg",
            licenseAndAttribution = "Public Domain (United States Government Work) / Clinical Reference",
            localDrawableRes = R.drawable.img_endocrine_physiology,
            localFileName = "img_endocrine_physiology.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Verified Active Clinical Diagram with Calibrated Endocrine Landmarks"
        )
    )
}
