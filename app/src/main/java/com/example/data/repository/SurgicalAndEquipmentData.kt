package com.example.data.repository

import com.example.data.model.EquipmentModule
import com.example.data.model.SurgicalGuide
import com.example.data.repository.surgical.CardiothoracicAndThoracicGuides
import com.example.data.repository.surgical.GeneralAndObstetricGuides
import com.example.data.repository.surgical.OrthopedicAndNeuroGuides
import com.example.data.repository.surgical.PediatricUrologyAndSpecialtyGuides

object SurgicalAndEquipmentData {

    val surgicalGuides: List<SurgicalGuide> = (
        GeneralAndObstetricGuides.guides +
        OrthopedicAndNeuroGuides.guides +
        CardiothoracicAndThoracicGuides.guides +
        PediatricUrologyAndSpecialtyGuides.guides
    ).distinctBy { it.id }

    val equipmentModules: List<EquipmentModule> = listOf(
        EquipmentModule(
            id = "equip_anesthesia_machine_physics",
            name = "Anesthesia Workstation: Gas Supply & Safety Systems",
            category = "Workstation Engineering",
            description = "The modern anesthesia delivery workstation receives medical gases under pressure, measures flows accurately, vaporizes volatile liquid anesthetics, delivers gas mixtures to the patient breathing circuit, and scavenges waste anesthetic gases (WAG).",
            mechanism = "High-Pressure System (Cylinders at 2000 psi) -> First-stage regulator reduces pressure to ~45 psi -> Intermediate-Pressure System (Pipeline gases at 50-55 psi, fail-safe valves, oxygen flush delivering 35-75 L/min at 50 psi) -> Low-Pressure System (Flowmeters, vaporizers, common gas outlet at near-atmospheric pressure).",
            normalValuesAndWaveforms = "Pipeline pressure: 50 ± 5 psi. Oxygen cylinder full: 2000-2200 psi (660 L). Nitrous oxide cylinder full: 745 psi (gas above liquid until 75% exhausted, then pressure drops).",
            troubleshootingAndFailures = listOf(
                "Oxygen Pipeline Crossover: If nitrous oxide or medical air is accidentally crossed into the O2 line, the Oxygen Analyzer is the ONLY safety device that detects hypoxic gas mixture! Disconnect pipeline immediately and switch to backup O2 cylinder.",
                "Oxygen Failure Safety Valve (Fail-Safe): Cuts off or proportionally reduces N2O flow if O2 supply pressure drops below 25-30 psi. DOES NOT prevent hypoxic mixture if O2 pipeline contains wrong gas.",
                "Oxygen Flush Valve Activation: Bypasses flowmeters and delivers 35-75 L/min of 100% O2 directly to common gas outlet. NEVER press during inspiratory phase of mechanical ventilation (causes massive barotrauma / pneumothorax)."
            ),
            clinicalPearls = "The Proportioning System (Link-25 or Ohmeda Link) mechanically or electronically prevents delivery of an FiO2 < 0.25 when mixing N2O and O2. Oxygen is always the last gas added to the manifold before the common gas outlet to minimize risk of hypoxic mixture if a flow tube breaks."
        ),
        EquipmentModule(
            id = "equip_capnography_analysis",
            name = "Capnography & End-Tidal CO2 Waveform Interpretation",
            category = "Monitoring & Diagnostics",
            description = "Continuous graphical monitoring of the concentration of carbon dioxide in exhaled respiratory gases. It is the single most vital monitor for confirming endotracheal tube placement, detecting circuit disconnections, assessing ventilation, and evaluating pulmonary perfusion.",
            mechanism = "Infrared absorption spectroscopy (Beer-Lambert law): CO2 molecules selectively absorb infrared light at 4.26 micrometers.",
            normalValuesAndWaveforms = "Normal End-Tidal CO2 (EtCO2): 35 - 45 mmHg (typically 2-5 mmHg lower than arterial PaCO2 due to alveolar dead space ventilation).\n" +
                    "Normal Waveform Phases:\n" +
                    "• Phase I (A-B): Inspiratory baseline (anatomical dead space gas, CO2 = 0)\n" +
                    "• Phase II (B-C): Expiratory upstroke (mixing of dead space gas and alveolar gas)\n" +
                    "• Phase III (C-D): Alveolar plateau (pure alveolar gas exhalation)\n" +
                    "• Point D: End-Tidal CO2 reading at the end of exhalation\n" +
                    "• Phase IV (D-E): Rapid inspiratory downstroke to zero baseline.",
            troubleshootingAndFailures = listOf(
                "Shark-Fin Waveform (Sloped Phase III with lost alpha angle): Hallmarks of bronchospasm, asthma, severe COPD, or mechanical tube kinking.",
                "Curare Cleft: Notch in the latter half of Phase III plateau indicates patient attempting spontaneous breathing or fighting ventilator (muscle relaxant wearing off).",
                "Elevated Inspiratory Baseline (> 0 mmHg): Indicates rebreathing of CO2 (exhausted soda lime / CO2 absorber, or faulty expiratory valve).",
                "Sudden Loss of Waveform to Zero: Accidental extubation, circuit disconnection, esophageal intubation, or complete ventilator failure.",
                "Exponential Drop in EtCO2 with Intact Ventilation: Massive pulmonary embolism (sudden loss of pulmonary perfusion), severe shock, or sudden cardiac arrest."
            ),
            clinicalPearls = "In CPR, EtCO2 is the most reliable real-time guide to chest compression quality and earliest sign of Return of Spontaneous Circulation (ROSC), marked by an immediate spike in EtCO2 > 35-40 mmHg."
        ),
        EquipmentModule(
            id = "equip_airway_lma_vs_ett",
            name = "Airway Management: Laryngeal Mask (LMA) vs Endotracheal Tube",
            category = "Airway Devices",
            description = "Comparison of supraglottic airway devices (SADs) versus cuffed endotracheal tubes (ETTs) in elective and difficult airway management.",
            mechanism = "Supraglottic Airway: Seats over the laryngeal inlet, creating an airtight peri-laryngeal seal against the base of the tongue, piriform fossae, and upper esophageal sphincter without entering the vocal cords.\nEndotracheal Tube: Directly traverses the vocal cords into the mid-trachea; inflatable cuff seals the tracheal lumen, preventing pulmonary aspiration.",
            normalValuesAndWaveforms = "LMA Seal Pressures: 1st Generation (Classic) 15-20 cmH2O; 2nd Generation (ProSeal, Supreme, i-gel) 25-35 cmH2O. ETT cuff pressure: 20 - 30 cmH2O (minimizes mucosal ischemia).",
            troubleshootingAndFailures = listOf(
                "LMA contraindications: Full stomach, severe GERD, morbid obesity (BMI > 40), high airway resistance / low compliance requiring high peak pressures > 25-30 cmH2O, prone position without airway access.",
                "Laryngeal spasm during insertion: Ensure adequate hypnotic depth prior to placement.",
                "Unrecognized esophageal intubation with ETT: Capnography absence is proof of esophageal placement until proven otherwise!"
            ),
            clinicalPearls = "Second-generation supraglottic airways feature a dedicated gastric drainage port that vents esophageal gases and permits gastric decompression with an NGT, greatly lowering aspiration risk."
        )
    )
}
