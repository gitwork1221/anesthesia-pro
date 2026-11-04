package com.example.data.repository.anatomy

import com.example.R
import com.example.data.model.*

object CardiovascularAndNervousData {

    val cardiovascularTopic = AnatomyTopic(
        id = "anatomy_cardiovascular_hemodynamics",
        title = "Cardiovascular Electrophysiology & Coronary Flow",
        subtitle = "Pressure-volume loops, Frank-Starling law & coronary perfusion pressure",
        category = AnatomySystemCategory.CARDIOVASCULAR_HEMODYNAMICS,
        difficulty = AnatomyDifficulty.INTERMEDIATE,
        learningObjectives = listOf(
            "Diagram cardiac action potentials: slow response (SA/AV nodal calcium dependent) vs fast response (Purkinje/myocyte sodium dependent).",
            "Calculate Coronary Perfusion Pressure (CPP = Aortic Diastolic Pressure - LVEDP) and identify phases of left vs right coronary perfusion.",
            "Analyze Pressure-Volume (PV) loops to diagnose changes in preload, afterload, inotropy, and compliance.",
            "Describe the baroreceptor reflex arc (carotid sinus vs aortic arch) and Bainbridge reflex."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "coronary_arteries_lad_rca",
                name = "Coronary Arterial Circulation (LAD, LCx, RCA)",
                normalizedCoordinateX = 0.45f,
                normalizedCoordinateY = 0.35f,
                description = "Coronary arteries arise from the sinus of Valsalva above aortic valve cusps. Left anterior descending (LAD) supplies anterior 2/3 of interventricular septum and anterior LV wall; Right coronary artery (RCA) supplies SA node (60%), AV node (90%), and inferior wall.",
                innervationOrVascular = "Autoregulation between MAP 60 - 140 mmHg. High basal myocardial oxygen extraction (70-75% O2 extraction).",
                clinicalPearl = "Left ventricular subendocardium is perfused almost exclusively during DIASTOLE due to high intramyocardial tissue pressure compressing capillaries during systole. Severe tachycardia shortens diastole disproportionately, causing subendocardial ischemia.",
                relatedIntervention = "Coronary Perfusion Pressure optimization: Avoid tachycardia (keep HR 60-80 in CAD); maintain diastolic BP > 50-60 mmHg"
            ),
            AnatomicalStructure(
                id = "cardiac_conduction_system",
                name = "SA Node, AV Node & His-Purkinje System",
                normalizedCoordinateX = 0.58f,
                normalizedCoordinateY = 0.28f,
                description = "SA node (crista terminalis of right atrium) initiates spontaneous action potentials via Phase 4 funny currents (If). AV node (triangle of Koch) provides 0.1-second delay for ventricular filling before conduction through bundle of His and Purkinje fibers.",
                innervationOrVascular = "Right vagus nerve innervates SA node; left vagus innervates AV node. Sympathetic fibers arise from T1-T4 cardiopulmonary splanchnics.",
                clinicalPearl = "Triangle of Koch landmarks: Coronary sinus ostium, tendon of Todaro, and tricuspid valve septal leaflet annulus. High-risk zone for complete heart block during catheterization or surgical repair.",
                relatedIntervention = "Cardiac pacing, cardioversion, and management of AV nodal re-entrant tachycardia with adenosine or beta-blockade"
            ),
            AnatomicalStructure(
                id = "ventricular_pressure_volume_loop",
                name = "Ventricular Pressure-Volume (PV) Loop",
                normalizedCoordinateX = 0.50f,
                normalizedCoordinateY = 0.65f,
                description = "Graphical representation of ventricular volume (x-axis) vs pressure (y-axis). Four corners represent: Mitral valve closure (EDV/EDP), Aortic valve opening, Aortic valve closure (ESV), and Mitral valve opening.",
                innervationOrVascular = "Slope of End-Systolic Pressure-Volume Relationship (ESPVR) represents contractility (Emax); slope of EDPVR represents passive ventricular stiffness.",
                clinicalPearl = "Increased afterload shifts loop taller and narrower with increased ESV and decreased stroke volume. Increased inotropy shifts ESPVR leftward, increasing stroke volume and ejection fraction.",
                relatedIntervention = "Inotropic titration (Epinephrine, Dobutamine) vs vasodilator titration (Nitroprusside, Nicardipine) to optimize cardiac output"
            )
        ),
        normalPhysiology = "Cardiac output is the product of Heart Rate and Stroke Volume (CO = HR * SV; normal ~4-6 L/min). Stroke volume is governed by Preload (Frank-Starling mechanism), Afterload (SVR / arterial elastance), and Contractility (inotropy). Myocardial oxygen consumption (MVO2) is driven by heart rate, wall tension (Laplace law: Tension = P * r / 2h), and contractility.",
        clinicalSignificance = "Coronary artery disease creates fixed flow limitations. When myocardial oxygen demand outstrips supply (tachycardia, hypertension, or severe hypotension), subendocardial ischemia develops, progressing to ventricular dysfunction and fatal arrhythmias.",
        anesthesiaImplications = "Neuraxial sympathectomy (T1-T4 cardioaccelerators blocked) leads to profound bradycardia and vasodilation (sympathectomy-induced hypotension). Volatile anesthetics cause dose-dependent myocardial depression and peripheral vasodilation.",
        commonExamDistinctions = listOf(
            "Coronary Perfusion Pressure (CPP) = Aortic Diastolic Pressure (AoDP) - Left Ventricular End-Diastolic Pressure (LVEDP). In severe aortic regurgitation, AoDP drops and LVEDP rises, causing profound subendocardial ischemia.",
            "Carotid sinus baroreceptor impulses travel via Hering's nerve to the glossopharyngeal nerve (CN IX); Aortic arch baroreceptor impulses travel via the vagus nerve (CN X).",
            "Bainbridge reflex: Increased venous return causing right atrial stretch triggers reflex tachycardia mediated by vagal afferents and sympathetic efferents (counteracts pooling).",
            "Aortic stenosis anesthesia goals: Maintain sinus rhythm, slow to normal heart rate (60-70 bpm), maintain high normal preload, maintain adequate SVR (avoid vasodilation). Sudden hypotension precipitously cuts coronary perfusion."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_cv_1",
                question = "At what phase of the cardiac cycle does the majority of left ventricular subendocardial perfusion take place?",
                options = listOf(
                    "Diastole",
                    "Isovolumetric contraction",
                    "Rapid systolic ejection",
                    "Reduced systolic ejection"
                ),
                correctIndex = 0,
                explanation = "During ventricular systole, high intramyocardial tissue pressures compress intramural vessels, almost completely halting left ventricular subendocardial blood flow. Thus, left ventricular perfusion is predominantly diastolic.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_cv_2",
                question = "A high-thoracic spinal block extending above T4 produces which classic hemodynamic response?",
                options = listOf(
                    "Bradycardia and hypotension due to sympathetic cardioaccelerator blockade",
                    "Tachycardia and hypertension due to adrenal catecholamine storm",
                    "Reflex hypertension from unopposed parasympathetic stimulation",
                    "Severe coronary vasospasm from alpha-1 receptor hyperstimulation"
                ),
                correctIndex = 0,
                explanation = "Cardioaccelerator sympathetic fibers originate from T1-T4. A high block anesthetizing these fibers eliminates sympathetic cardiac drive, leaving unopposed vagal tone that causes profound bradycardia and hypotension.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_coronary_artery_bypass", "guide_hip_fracture"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Coronary Artery Topography & Ventricular Pressure-Volume Dynamics",
            topicName = "Cardiovascular System & Hemodynamics",
            creatorOrInstitution = "OpenStax Anatomy and Physiology",
            sourceUrl = "https://commons.wikimedia.org/wiki/File:Cardiovascular_System.svg",
            licenseAndAttribution = "CC BY-SA 4.0 Creative Commons Attribution-ShareAlike",
            localDrawableRes = R.drawable.img_cardiac_hemodynamics,
            localFileName = "img_cardiac_hemodynamics.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Verified Active Clinical Diagram with Calibrated PV Loop Landmarks"
        )
    )

    val nervousSystemTopic = AnatomyTopic(
        id = "anatomy_nervous_system",
        title = "Neuraxial Anatomy & Cerebral Blood Flow",
        subtitle = "Spinal meninges, Artery of Adamkiewicz, Monro-Kellie doctrine & ICP",
        category = AnatomySystemCategory.NERVOUS_SYSTEM,
        difficulty = AnatomyDifficulty.INTERMEDIATE,
        learningObjectives = listOf(
            "Detail the 7 anatomic layers traversed during midline and paramedian lumbar puncture.",
            "Identify the termination of the adult vs pediatric spinal cord (conus medullaris) and dural sac (thecal sac).",
            "Map the arterial supply of the spinal cord and identify the clinical consequences of Artery of Adamkiewicz occlusion.",
            "Apply the Monro-Kellie doctrine to intracranial pressure (ICP) dynamics and cerebral perfusion pressure (CPP = MAP - ICP)."
        ),
        structures = listOf(
            AnatomicalStructure(
                id = "ligamentum_flavum_layers",
                name = "Ligamentum Flavum & Epidural Space",
                normalizedCoordinateX = 0.50f,
                normalizedCoordinateY = 0.42f,
                description = "The ligamentum flavum ('yellow ligament') connects laminae of adjacent vertebrae. It consists of >80% elastin fibers and feels distinctly gritty/resistant, giving the classic 'pop' and loss-of-resistance (LOR) to air or saline.",
                innervationOrVascular = "Epidural space contains Batson's valveless venous plexus, fat, lymphatics, and spinal nerve roots.",
                clinicalPearl = "In the midline, the ligamentum flavum may be fused or have a congenital gap in up to 10-15% of patients (most common in cervical/thoracic), which can cause false loss-of-resistance or accidental dural puncture.",
                relatedIntervention = "Epidural catheter placement (Tuohy 17G or 18G needle with loss-of-resistance technique)"
            ),
            AnatomicalStructure(
                id = "dura_subarachnoid_space",
                name = "Dura-Arachnoid Mater & Subarachnoid Space",
                normalizedCoordinateX = 0.52f,
                normalizedCoordinateY = 0.55f,
                description = "The dense dura mater and delicate avascular arachnoid mater form the thecal sac containing cerebrospinal fluid (CSF) and the cauda equina nerve roots below L1.",
                innervationOrVascular = "Adult spinal cord terminates at L1-L2 (conus medullaris); infant terminates at L3. Thecal sac terminates at S2 in adults (S3 in infants).",
                clinicalPearl = "Spinal anesthesia punctures the dura-arachnoid layer to enter the subarachnoid space, confirmed by free aspiration of clear CSF. Tuffier's line (connecting superior iliac crests) crosses at L4 or L4-L5 interspace, providing a safe entry level below the conus.",
                relatedIntervention = "Subarachnoid block / Spinal anesthesia (Whitacre or Sprotte 25-27G pencil-point needle to reduce PDPH)"
            ),
            AnatomicalStructure(
                id = "artery_of_adamkiewicz",
                name = "Great Radicular Artery (Artery of Adamkiewicz)",
                normalizedCoordinateX = 0.62f,
                normalizedCoordinateY = 0.68f,
                description = "Arises from a left intercostal or lumbar artery between T9 and L2 in 75% of individuals. Major contributor of blood flow to the anterior spinal artery supplying the lower 2/3 of the spinal cord.",
                innervationOrVascular = "Anterior spinal artery supplies anterior 2/3 of cord (motor tracts & spinothalamic pain/temperature); two posterior spinal arteries supply posterior 1/3 (dorsal columns: vibration & proprioception).",
                clinicalPearl = "Cross-clamping the thoracic aorta during open thoracic/thoracoabdominal aortic aneurysm repair can infarct this artery, producing Anterior Spinal Artery Syndrome: bilateral paraplegia, loss of bowel/bladder control, and loss of pain/temperature with preserved vibration and proprioception.",
                relatedIntervention = "Spinal cord protection protocols: Lumbar CSF drain (target CSF pressure < 10 mmHg), distal aortic perfusion, and hypothermia"
            ),
            AnatomicalStructure(
                id = "cerebral_perfusion_monro_kellie",
                name = "Monro-Kellie Intracranial Volume & CPP",
                normalizedCoordinateX = 0.40f,
                normalizedCoordinateY = 0.20f,
                description = "Rigid cranial vault contains fixed volume of Brain parenchyma (80%), Blood (10%), and CSF (10%). Any increase in volume of one component requires compensatory expulsion of another (CSF into spinal canal, venous blood into jugulars) to prevent catastrophic ICP rise.",
                innervationOrVascular = "Normal ICP: 5-15 mmHg. Normal Cerebral Blood Flow (CBF): 50 mL/100g/min. Autoregulation range: MAP 50 - 150 mmHg.",
                clinicalPearl = "Cerebral Perfusion Pressure (CPP) = MAP - ICP (or CVP, whichever is higher). Target CPP is 60-70 mmHg. Hypocapnia (hyperventilation to PaCO2 30-35 mmHg) produces rapid cerebral vasoconstriction to acutely lower ICP.",
                relatedIntervention = "ICP reduction: Elevate head 30°, avoid jugular compression, mannitol 0.5-1 g/kg, 3% hypertonic saline, TIVA with propofol"
            )
        ),
        normalPhysiology = "Spinal nerves exit through intervertebral foramina. Local anesthetics injected into the subarachnoid space bathe naked nerve roots, blocking conduction from small unmyelinated fibers (autonomic B and C fibers) first, followed by sensory fibers (A-delta and A-beta), and lastly large motor fibers (A-alpha).",
        clinicalSignificance = "Unrecognized epidural catheter migration into the subarachnoid space can result in accidental total spinal anesthesia (apnea, unconsciousness, severe hypotension, and pupillary dilation). Accidental intravascular migration into an epidural vein leads to Local Anesthetic Systemic Toxicity (LAST).",
        anesthesiaImplications = "Midline lumbar puncture structures traversed: Skin -> Subcutaneous fat -> Supraspinous ligament -> Interspinous ligament -> Ligamentum flavum -> Epidural space -> Dura mater -> Arachnoid mater -> Subarachnoid space with CSF.",
        commonExamDistinctions = listOf(
            "Adult spinal cord ends at L1-L2; pediatric ends at L3. Adult dural sac ends at S2; pediatric ends at S3.",
            "Paramedian approach bypasses the supraspinous and interspinous ligaments, traversing skin, subcutaneous fat, paraspinous muscle, ligamentum flavum, and epidural space.",
            "Anterior spinal artery syndrome produces motor loss (corticospinal) and pain/temperature loss (spinothalamic) but PRESERVES vibration and position sense (dorsal columns supplied by posterior spinal arteries).",
            "Cerebral blood flow changes by 1-2 mL/100g/min for each 1 mmHg change in PaCO2 between 20 and 80 mmHg. Volatile anesthetics > 1 MAC are potent cerebral vasodilators that uncouple CBF from CMRO2."
        ),
        reviewQuestions = listOf(
            ReviewQuestion(
                id = "q_neuro_1",
                question = "When performing a midline lumbar spinal block, which ligament provides the characteristic gritty resistance and 'pop' just prior to entering the epidural space?",
                options = listOf(
                    "Ligamentum flavum",
                    "Supraspinous ligament",
                    "Interspinous ligament",
                    "Posterior longitudinal ligament"
                ),
                correctIndex = 0,
                explanation = "The ligamentum flavum is composed predominantly of dense elastic fibers. Its high resistance and sudden loss of resistance ('pop') demarcate entry into the epidural space.",
                examHighYieldTag = "ABA / NBCRNA Core"
            ),
            ReviewQuestion(
                id = "q_neuro_2",
                question = "In Anterior Spinal Artery Syndrome following aortic cross-clamping, which sensory modality is typically spared?",
                options = listOf(
                    "Vibration and proprioception",
                    "Sharp pain sensation",
                    "Temperature discrimination",
                    "Somatic motor function"
                ),
                correctIndex = 0,
                explanation = "The anterior spinal artery supplies the anterior 2/3 of the cord (motor and spinothalamic tracts). The posterior columns (dorsal columns carrying proprioception and vibration) are perfused by the dual posterior spinal arteries and remain intact.",
                examHighYieldTag = "ABA Basic Exam"
            )
        ),
        relatedSurgicalGuideIds = listOf("guide_hip_fracture", "guide_craniotomy_tumor", "guide_cesarean_section"),
        assetMetadata = AnatomyAssetMetadata(
            assetTitle = "Lumbar Vertebral Column, Neuraxial Ligaments & Spinal Cord Architecture",
            topicName = "Central & Peripheral Nervous Systems",
            creatorOrInstitution = "Henry Gray / Anatomy of the Human Body",
            sourceUrl = "https://commons.wikimedia.org/wiki/File:Gray167.png",
            licenseAndAttribution = "Public Domain / CC0 Medical Reference Asset",
            localDrawableRes = R.drawable.img_neuraxial_anatomy,
            localFileName = "img_neuraxial_anatomy.jpg",
            intrinsicWidth = 1376,
            intrinsicHeight = 768,
            status = AssetVerificationStatus.CLINICALLY_REVIEWED,
            verificationDate = "November 2024",
            statusNote = "Verified Active Clinical Diagram with Calibrated Neuraxial Landmarks"
        )
    )
}
