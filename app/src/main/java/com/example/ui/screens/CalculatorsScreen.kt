package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.CalculatorsEngine
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import kotlin.math.roundToInt

enum class CalculatorType(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    BMI_IBW("BMI & IBW", Icons.Filled.FitnessCenter),
    MAP("MAP", Icons.Filled.Speed),
    BLOOD_LOSS("EBV & MABL", Icons.Filled.Bloodtype),
    PEDIATRIC_ETT("Pediatric ETT", Icons.Filled.ChildCare),
    LOCAL_ANESTHETIC("LA Max Dose", Icons.Filled.MedicalServices),
    FLUID_421("4-2-1 Fluid", Icons.Filled.WaterDrop),
    O2_CYLINDER("O₂ Tank Duration", Icons.Filled.Air),
    ANION_GAP("Anion Gap", Icons.Filled.Science),
    INFUSION_RATE("Infusion Rate", Icons.Filled.Vaccines),
    CORRECTED_SODIUM("Corrected Na⁺", Icons.Filled.WaterDrop),
    ALVEOLAR_GAS("PAO₂ & A-a", Icons.Filled.Air)
}

@Composable
fun CalculatorsScreen(modifier: Modifier = Modifier) {
    var selectedCalculator by remember { mutableStateOf(CalculatorType.BMI_IBW) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Clinical Anesthesia Calculators",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Deterministic equations, verified clinical references, and safety alerts",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Calculator Selector Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(CalculatorType.entries.toTypedArray()) { calc ->
                FilterChip(
                    selected = selectedCalculator == calc,
                    onClick = { selectedCalculator = calc },
                    leadingIcon = {
                        Icon(
                            imageVector = calc.icon,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (selectedCalculator == calc) DeepObsidian else SurgicalCyan
                        )
                    },
                    label = { Text(calc.title, style = MaterialTheme.typography.labelSmall) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SurgicalCyan,
                        selectedLabelColor = DeepObsidian,
                        containerColor = NavySurface,
                        labelColor = TextSecondary
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = selectedCalculator == calc,
                        borderColor = BorderSubtle,
                        selectedBorderColor = SurgicalCyan
                    ),
                    modifier = Modifier.testTag("calc_chip_${calc.name.lowercase()}")
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                when (selectedCalculator) {
                    CalculatorType.BMI_IBW -> BmiIbwCalculatorView()
                    CalculatorType.MAP -> MapCalculatorView()
                    CalculatorType.BLOOD_LOSS -> MablCalculatorView()
                    CalculatorType.PEDIATRIC_ETT -> PediatricEttCalculatorView()
                    CalculatorType.LOCAL_ANESTHETIC -> LocalAnestheticCalculatorView()
                    CalculatorType.FLUID_421 -> Fluid421CalculatorView()
                    CalculatorType.O2_CYLINDER -> OxygenCylinderCalculatorView()
                    CalculatorType.ANION_GAP -> AnionGapCalculatorView()
                    CalculatorType.INFUSION_RATE -> InfusionRateCalculatorView()
                    CalculatorType.CORRECTED_SODIUM -> CorrectedSodiumCalculatorView()
                    CalculatorType.ALVEOLAR_GAS -> AlveolarGasCalculatorView()
                }
            }
        }
    }
}

// 1. BMI & IBW & Adjusted BW View
@Composable
private fun BmiIbwCalculatorView() {
    var weightText by remember { mutableStateOf("75") }
    var heightText by remember { mutableStateOf("175") }
    var isMale by remember { mutableStateOf(true) }

    val weight = weightText.toDoubleOrNull() ?: 0.0
    val height = heightText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculateBmiAndIbw(weight, height, isMale)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_bmi_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "BMI, Ideal & Adjusted Body Weight",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Devine Formula (1974) • Hydrophilic vs Lipophilic Dosing",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        weightText = "75"
                        heightText = "175"
                        isMale = true
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_bmi_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Gender Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { isMale = true },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isMale) SurgicalCyan else NavySurfaceVariant,
                        contentColor = if (isMale) DeepObsidian else TextSecondary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Male Patient")
                }
                Button(
                    onClick = { isMale = false },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isMale) SurgicalCyan else NavySurfaceVariant,
                        contentColor = if (!isMale) DeepObsidian else TextSecondary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Female Patient")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = weightText,
                    onValueChange = { weightText = it },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_weight")
                )
                OutlinedTextField(
                    value = heightText,
                    onValueChange = { heightText = it },
                    label = { Text("Height (cm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_height")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Calculated Weight Parameters",
                    items = listOf(
                        "Body Mass Index (BMI)" to "${result.bmi} kg/m²",
                        "WHO Classification" to result.bmiCategory,
                        "Ideal Body Weight (IBW)" to "${result.ibwKg} kg",
                        "Adjusted Body Weight (AdjBW)" to "${result.adjBwKg} kg"
                    ),
                    equation = result.equationUsed,
                    note = result.clinicalNote
                )
            } else {
                Text("Enter valid positive weight (>0 kg) and height (>0 cm)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 2. Mean Arterial Pressure (MAP) View
@Composable
private fun MapCalculatorView() {
    var sbpText by remember { mutableStateOf("115") }
    var dbpText by remember { mutableStateOf("70") }

    val sbp = sbpText.toDoubleOrNull() ?: 0.0
    val dbp = dbpText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculateMap(sbp, dbp)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_map_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Mean Arterial Pressure (MAP)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Organ Perfusion Target (Brain, Kidneys, Coronaries)",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        sbpText = "115"
                        dbpText = "70"
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_map_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = sbpText,
                    onValueChange = { sbpText = it },
                    label = { Text("Systolic BP (mmHg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_sbp")
                )
                OutlinedTextField(
                    value = dbpText,
                    onValueChange = { dbpText = it },
                    label = { Text("Diastolic BP (mmHg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_dbp")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "MAP Perfusion Result",
                    items = listOf(
                        "Calculated MAP" to "${result.mapMmhg} mmHg",
                        "Pulse Pressure (SBP - DBP)" to "${result.pulsePressure} mmHg"
                    ),
                    equation = result.equationUsed,
                    note = result.clinicalStatus,
                    noteColor = if (result.mapMmhg < 65.0) AlarmRose else VitalGreen
                )
            } else {
                Text("Enter valid positive blood pressures (SBP must exceed DBP)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 3. Estimated Blood Volume & MABL View
@Composable
private fun MablCalculatorView() {
    var weightText by remember { mutableStateOf("70") }
    var hctInitialText by remember { mutableStateOf("38") }
    var hctTargetText by remember { mutableStateOf("28") }
    var selectedCategory by remember { mutableStateOf(CalculatorsEngine.PatientCategory.ADULT_MALE) }

    val weight = weightText.toDoubleOrNull() ?: 0.0
    val hctInit = hctInitialText.toDoubleOrNull() ?: 0.0
    val hctTarget = hctTargetText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculateMabl(weight, selectedCategory, hctInit, hctTarget)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_mabl_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Estimated Blood Volume & MABL",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Bourke-Smith / Gross Hemodilution Formula",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        weightText = "70"
                        hctInitialText = "38"
                        hctTargetText = "28"
                        selectedCategory = CalculatorsEngine.PatientCategory.ADULT_MALE
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_mabl_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Patient Category Chips
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(CalculatorsEngine.PatientCategory.entries.toTypedArray()) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { selectedCategory = cat },
                        label = { Text(cat.label.split("(").first().trim(), style = MaterialTheme.typography.labelSmall) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ClinicalTeal,
                            selectedLabelColor = DeepObsidian,
                            containerColor = NavySurfaceVariant,
                            labelColor = TextSecondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = weightText,
                    onValueChange = { weightText = it },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_mabl_weight")
                )
                OutlinedTextField(
                    value = hctInitialText,
                    onValueChange = { hctInitialText = it },
                    label = { Text("Start Hct (%)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_mabl_hct_start")
                )
                OutlinedTextField(
                    value = hctTargetText,
                    onValueChange = { hctTargetText = it },
                    label = { Text("Target Hct (%)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_mabl_hct_target")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Circulating Volume & Transfusion Boundary",
                    items = listOf(
                        "Estimated Blood Volume (EBV)" to "${result.ebvMl} mL (Total circulating)",
                        "Maximum Allowable Blood Loss (MABL)" to "${result.mablMl} mL before transfusion"
                    ),
                    equation = result.equationUsed,
                    note = result.clinicalNote
                )
            } else {
                Text("Enter valid weight and starting Hct (> target Hct)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 4. Pediatric ETT Calculator View
@Composable
private fun PediatricEttCalculatorView() {
    var ageText by remember { mutableStateOf("4") }
    val age = ageText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculatePediatricEtt(age)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_ett_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Pediatric ETT Size & Insertion Depth",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Khine / Motoyama Formula • Neonatal & Infant Rules",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = { ageText = "4" },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_ett_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = ageText,
                onValueChange = { ageText = it },
                label = { Text("Patient Age (Years, 0-18; use 0.08 for 1 mo)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = calcTextFieldColors(),
                modifier = Modifier.fillMaxWidth().testTag("input_pediatric_age")
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Endotracheal Tube Specifications (Estimated)",
                    items = listOf(
                        "Cuffed ETT Size" to "${result.cuffedSizeMm} mm ID",
                        "Uncuffed ETT Size" to "${result.uncuffedSizeMm} mm ID",
                        "Oral Insertion Depth" to "${result.oralDepthCm} cm at mid-incisors",
                        "Nasal Insertion Depth" to "${result.nasalDepthCm} cm at naris"
                    ),
                    equation = result.backupSizes,
                    note = result.clinicalNote
                )
            } else {
                Text("Enter valid pediatric age between 0 and 18 years", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 5. Local Anesthetic Maximum Dose Calculator View
@Composable
private fun LocalAnestheticCalculatorView() {
    var weightText by remember { mutableStateOf("70") }
    var ibwText by remember { mutableStateOf("") }
    var selectedDrug by remember { mutableStateOf(CalculatorsEngine.LocalAnestheticType.LIDOCAINE_PLAIN) }
    var selectedConcPercent by remember { mutableStateOf(1.0) }

    val weight = weightText.toDoubleOrNull() ?: 0.0
    val ibw = ibwText.toDoubleOrNull()
    val result = CalculatorsEngine.calculateLocalAnestheticLimit(weight, selectedDrug, selectedConcPercent, ibw)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_la_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Local Anesthetic Maximum Safe Dose & Volume",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "ASRA & FDA Guidelines • LAST Lipid Protocol",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        weightText = "70"
                        ibwText = ""
                        selectedDrug = CalculatorsEngine.LocalAnestheticType.LIDOCAINE_PLAIN
                        selectedConcPercent = 1.0
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_la_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Drug Type Selector
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(CalculatorsEngine.LocalAnestheticType.entries.toTypedArray()) { drug ->
                    FilterChip(
                        selected = selectedDrug == drug,
                        onClick = {
                            selectedDrug = drug
                            selectedConcPercent = drug.typicalConcentrationPercent
                        },
                        label = { Text(drug.displayName.split("(").first().trim(), style = MaterialTheme.typography.labelSmall) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AlertAmber,
                            selectedLabelColor = DeepObsidian,
                            containerColor = NavySurfaceVariant,
                            labelColor = TextSecondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = weightText,
                    onValueChange = { weightText = it },
                    label = { Text("Actual Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_la_weight")
                )

                OutlinedTextField(
                    value = ibwText,
                    onValueChange = { ibwText = it },
                    label = { Text("IBW kg (optional)") },
                    placeholder = { Text("If obese") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_la_ibw")
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Concentration chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Drug Concentration (%):", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    val concs = if (selectedDrug.name.contains("LIDO")) listOf(0.5, 1.0, 2.0) else listOf(0.25, 0.5, 0.75)
                    concs.forEach { c ->
                        FilterChip(
                            selected = selectedConcPercent == c,
                            onClick = { selectedConcPercent = c },
                            label = { Text("$c%", fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SurgicalCyan,
                                selectedLabelColor = DeepObsidian
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                val displayItems = mutableListOf(
                    "Selected Agent" to result.drugName,
                    "Reference Standard" to selectedDrug.referenceSource,
                    "Total Body Weight Max Dose" to "${result.maxDoseMg} mg (${result.doseMgPerKg} mg/kg)",
                    "Total Body Weight Max Volume" to "${result.maxVolumeMl} mL of $selectedConcPercent%",
                    "Absolute Ceiling Cap" to "${result.ceilingCapMg} mg"
                )
                if (result.ibwMaxDoseMg != null && result.ibwMaxVolumeMl != null) {
                    displayItems.add("Ideal Body Weight Cap (Obesity)" to "${result.ibwMaxDoseMg} mg (${result.ibwMaxVolumeMl} mL)")
                }

                ResultDisplayCard(
                    title = "Safe Infiltration & Block Ceilings",
                    items = displayItems,
                    equation = "${result.weightBasisGuidance}\n\nCalculation: Min(Weight × ${result.doseMgPerKg} mg/kg, ${result.ceilingCapMg} mg)",
                    note = result.toxicityWarning,
                    noteColor = AlarmRose
                )
            }
        }
    }
}

// 6. Maintenance IV Fluid Rate (4-2-1 Rule) View
@Composable
private fun Fluid421CalculatorView() {
    var weightText by remember { mutableStateOf("65") }
    val weight = weightText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculate421Fluid(weight)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_fluid_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Maintenance IV Fluid (4-2-1 Rule)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Holliday-Segar Method • Fasting & Third Space Sizing",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = { weightText = "65" },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_fluid_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = weightText,
                onValueChange = { weightText = it },
                label = { Text("Patient Weight (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = calcTextFieldColors(),
                modifier = Modifier.fillMaxWidth().testTag("input_fluid_weight")
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Maintenance Fluid Rates",
                    items = listOf(
                        "Hourly Infusion Rate" to "${result.hourlyRateMlHr} mL/hr",
                        "24-Hour Total Maintenance" to "${result.dailyVolumeMlDay} mL/day"
                    ),
                    equation = result.explanation,
                    note = result.clinicalCaveat
                )
            } else {
                Text("Enter valid positive weight (>0 kg)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 7. Oxygen Cylinder Remaining Duration View
@Composable
private fun OxygenCylinderCalculatorView() {
    var psiText by remember { mutableStateOf("1500") }
    var flowText by remember { mutableStateOf("4") }
    var selectedCylinder by remember { mutableStateOf(CalculatorsEngine.OxygenCylinderType.E_CYLINDER) }

    val psi = psiText.toDoubleOrNull() ?: 0.0
    val flow = flowText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculateOxygenDuration(psi, flow, selectedCylinder)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_o2_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Oxygen Cylinder Remaining Duration",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "200 PSI Safety Reserve • E, H/K, D, Jumbo D Cylinders",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        psiText = "1500"
                        flowText = "4"
                        selectedCylinder = CalculatorsEngine.OxygenCylinderType.E_CYLINDER
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_o2_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Cylinder Type Selector
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(CalculatorsEngine.OxygenCylinderType.entries.toTypedArray()) { cyl ->
                    FilterChip(
                        selected = selectedCylinder == cyl,
                        onClick = { selectedCylinder = cyl },
                        label = { Text(cyl.displayName.split("(").first().trim(), style = MaterialTheme.typography.labelSmall) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SurgicalCyan,
                            selectedLabelColor = DeepObsidian,
                            containerColor = NavySurfaceVariant,
                            labelColor = TextSecondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = psiText,
                    onValueChange = { psiText = it },
                    label = { Text("Gauge Pressure (PSI)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_o2_psi")
                )
                OutlinedTextField(
                    value = flowText,
                    onValueChange = { flowText = it },
                    label = { Text("O₂ Flow Rate (L/min)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_o2_flow")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Oxygen Reserve Status",
                    items = listOf(
                        "Cylinder Type" to result.cylinderName,
                        "Estimated Remaining Time" to "${result.remainingMinutes} min (${result.remainingHours} hrs)",
                        "Usable Oxygen (Above 200 psi buffer)" to "${result.usableLiters} Liters"
                    ),
                    equation = result.formulaUsed,
                    note = result.alert,
                    noteColor = if (result.remainingMinutes < 15.0) AlarmRose else if (result.remainingMinutes < 30.0) AlertAmber else VitalGreen
                )
            } else {
                Text("Enter valid positive gauge pressure (≤ 3000 psi) and flow rate (>0 L/min)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 8. Anion Gap Calculator View
@Composable
private fun AnionGapCalculatorView() {
    var naText by remember { mutableStateOf("140") }
    var clText by remember { mutableStateOf("102") }
    var hco3Text by remember { mutableStateOf("24") }

    val na = naText.toDoubleOrNull() ?: 0.0
    val cl = clText.toDoubleOrNull() ?: 0.0
    val hco3 = hco3Text.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculateAnionGap(na, cl, hco3)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_ag_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Serum Anion Gap & Acidosis Differentials",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "GOLD MARK / MUDPILES vs HARDUP Hyperchloremic",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        naText = "140"
                        clText = "102"
                        hco3Text = "24"
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_ag_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = naText,
                    onValueChange = { naText = it },
                    label = { Text("Na⁺ (mEq/L)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_ag_na")
                )
                OutlinedTextField(
                    value = clText,
                    onValueChange = { clText = it },
                    label = { Text("Cl⁻ (mEq/L)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_ag_cl")
                )
                OutlinedTextField(
                    value = hco3Text,
                    onValueChange = { hco3Text = it },
                    label = { Text("HCO₃⁻ (mEq/L)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_ag_hco3")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Anion Gap Interpretation",
                    items = listOf(
                        "Calculated Anion Gap" to "${result.anionGap} mEq/L (Normal: 4 - 12)",
                        "Diagnostic Classification" to result.interpretation
                    ),
                    equation = result.formulaUsed,
                    note = result.differentials,
                    noteColor = if (result.anionGap > 12.0) AlertAmber else ClinicalTeal
                )
            } else {
                Text("Enter valid serum electrolytes (Na: 70-190, Cl: 40-150, HCO3: 1-60 mEq/L)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 9. Infusion Rate Converter View (Bidirectional IV Pump & Drug Delivery)
@Composable
private fun InfusionRateCalculatorView() {
    var doseRateText by remember { mutableStateOf("0.1") }
    var selectedUnit by remember { mutableStateOf(CalculatorsEngine.InfusionDoseUnit.MCG_KG_MIN) }
    var weightText by remember { mutableStateOf("70") }
    var drugAmountText by remember { mutableStateOf("4") } // mg
    var bagVolumeText by remember { mutableStateOf("250") } // mL

    val doseRate = doseRateText.toDoubleOrNull() ?: 0.0
    val weight = weightText.toDoubleOrNull()
    val drugAmount = drugAmountText.toDoubleOrNull() ?: 0.0
    val bagVolume = bagVolumeText.toDoubleOrNull() ?: 0.0

    val result = CalculatorsEngine.calculateInfusionRate(
        doseRate = doseRate,
        doseUnit = selectedUnit,
        weightKg = weight,
        drugAmountMg = drugAmount,
        bagVolumeMl = bagVolume
    )

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_infusion_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Infusion Rate & Drug Delivery Converter",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Bidirectional pump flow (mL/hr) & delivery calculation",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        doseRateText = "0.1"
                        selectedUnit = CalculatorsEngine.InfusionDoseUnit.MCG_KG_MIN
                        weightText = "70"
                        drugAmountText = "4"
                        bagVolumeText = "250"
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_infusion_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Presets
            Text("Bedside Drug Presets:", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(vertical = 4.dp)) {
                item {
                    AssistChip(
                        onClick = {
                            drugAmountText = "4"
                            bagVolumeText = "250"
                            selectedUnit = CalculatorsEngine.InfusionDoseUnit.MCG_KG_MIN
                            doseRateText = "0.08"
                        },
                        label = { Text("Norepinephrine (4mg/250mL)", fontSize = 11.sp) }
                    )
                }
                item {
                    AssistChip(
                        onClick = {
                            drugAmountText = "4"
                            bagVolumeText = "250"
                            selectedUnit = CalculatorsEngine.InfusionDoseUnit.MCG_MIN
                            doseRateText = "4.0"
                        },
                        label = { Text("Epinephrine (4mg/250mL mcg/min)", fontSize = 11.sp) }
                    )
                }
                item {
                    AssistChip(
                        onClick = {
                            drugAmountText = "1000"
                            bagVolumeText = "100"
                            selectedUnit = CalculatorsEngine.InfusionDoseUnit.MCG_KG_MIN
                            doseRateText = "100.0"
                        },
                        label = { Text("Propofol (1% 1000mg/100mL)", fontSize = 11.sp) }
                    )
                }
                item {
                    AssistChip(
                        onClick = {
                            drugAmountText = "0.2"
                            bagVolumeText = "50"
                            selectedUnit = CalculatorsEngine.InfusionDoseUnit.MCG_KG_HR
                            doseRateText = "0.5"
                        },
                        label = { Text("Dexmedetomidine (200mcg/50mL)", fontSize = 11.sp) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Unit Selector
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(CalculatorsEngine.InfusionDoseUnit.entries.toTypedArray()) { unit ->
                    FilterChip(
                        selected = selectedUnit == unit,
                        onClick = { selectedUnit = unit },
                        label = { Text(unit.label, style = MaterialTheme.typography.labelSmall) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SurgicalCyan,
                            selectedLabelColor = DeepObsidian,
                            containerColor = NavySurfaceVariant,
                            labelColor = TextSecondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = doseRateText,
                    onValueChange = { doseRateText = it },
                    label = { Text("Dose (${selectedUnit.label})") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_infusion_dose")
                )
                if (selectedUnit.isWeightBased) {
                    OutlinedTextField(
                        value = weightText,
                        onValueChange = { weightText = it },
                        label = { Text("Weight (kg)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = calcTextFieldColors(),
                        modifier = Modifier.weight(1f).testTag("input_infusion_weight")
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = drugAmountText,
                    onValueChange = { drugAmountText = it },
                    label = { Text("Total Drug (mg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_infusion_drug_mg")
                )
                OutlinedTextField(
                    value = bagVolumeText,
                    onValueChange = { bagVolumeText = it },
                    label = { Text("Bag Volume (mL)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_infusion_bag_ml")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                val items = mutableListOf(
                    "Infusion Pump Rate" to "${result.rateMlHr} mL/hr",
                    "Mass Delivery (mg/hr)" to "${result.drugDeliveryMgHr} mg/hr",
                    "Mass Delivery (mcg/min)" to "${result.drugDeliveryMcgMin} mcg/min",
                    "Solution Concentration" to "${result.concentrationMgMl} mg/mL (${result.concentrationMcgMl} mcg/mL)"
                )
                if (result.drugDeliveryMcgKgMin != null) {
                    items.add("Weight-normalized Delivery" to "${result.drugDeliveryMcgKgMin} mcg/kg/min")
                }

                ResultDisplayCard(
                    title = "Target Infusion Pump Delivery",
                    items = items,
                    equation = result.calculationSteps,
                    note = result.safetyWarning,
                    noteColor = AlertAmber
                )
            } else {
                Text("Enter valid positive numbers for dose, drug mass, and bag volume (and weight if weight-based)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 10. Corrected Sodium for Hyperglycemia View
@Composable
private fun CorrectedSodiumCalculatorView() {
    var naText by remember { mutableStateOf("130") }
    var glucoseText by remember { mutableStateOf("500") }
    var isMmolL by remember { mutableStateOf(false) }

    val na = naText.toDoubleOrNull() ?: 0.0
    val glucose = glucoseText.toDoubleOrNull() ?: 0.0
    val result = CalculatorsEngine.calculateCorrectedSodium(na, glucose, isMmolL)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_corrected_na_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Corrected Sodium for Hyperglycemia",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Katz (1.6) & Hillier (2.4) Osmotic Translocation",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        naText = "130"
                        glucoseText = if (isMmolL) "27.7" else "500"
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_corrected_na_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Unit toggle (mg/dL vs mmol/L)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (isMmolL) {
                            val currVal = glucoseText.toDoubleOrNull()
                            if (currVal != null) glucoseText = ((currVal * 18.0182 * 10).roundToInt() / 10.0).toString()
                            isMmolL = false
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isMmolL) SurgicalCyan else NavySurfaceVariant,
                        contentColor = if (!isMmolL) DeepObsidian else TextSecondary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Glucose in mg/dL")
                }
                Button(
                    onClick = {
                        if (!isMmolL) {
                            val currVal = glucoseText.toDoubleOrNull()
                            if (currVal != null) glucoseText = ((currVal / 18.0182 * 10).roundToInt() / 10.0).toString()
                            isMmolL = true
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isMmolL) SurgicalCyan else NavySurfaceVariant,
                        contentColor = if (isMmolL) DeepObsidian else TextSecondary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Glucose in mmol/L")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = naText,
                    onValueChange = { naText = it },
                    label = { Text("Measured Na⁺ (mEq/L)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_measured_na")
                )
                OutlinedTextField(
                    value = glucoseText,
                    onValueChange = { glucoseText = it },
                    label = { Text(if (isMmolL) "Blood Glucose (mmol/L)" else "Blood Glucose (mg/dL)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_glucose")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                ResultDisplayCard(
                    title = "Corrected Serum Sodium Estimates",
                    items = listOf(
                        "Hillier et al. (Recommended)" to "${result.hillierCorrectedNa} mEq/L (+2.4 / 100 mg/dL)",
                        "Katz Formula (Classic)" to "${result.katzCorrectedNa} mEq/L (+1.6 / 100 mg/dL)",
                        "Measured Serum Sodium" to "${result.measuredNa} mEq/L",
                        "Osmotic Dilutional Drop" to "-${result.osmoticShiftDelta} mEq/L"
                    ),
                    equation = result.formulaExplanation,
                    note = result.clinicalGuidance,
                    noteColor = ClinicalTeal
                )
            } else {
                Text("Enter valid serum sodium (80-190 mEq/L) and positive glucose level", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// 11. Alveolar Gas Equation (PAO2) and A-a Gradient View
@Composable
private fun AlveolarGasCalculatorView() {
    var fio2Text by remember { mutableStateOf("21") }
    var paco2Text by remember { mutableStateOf("40") }
    var pao2Text by remember { mutableStateOf("95") }
    var ageText by remember { mutableStateOf("45") }
    var pbText by remember { mutableStateOf("760") }

    val fio2 = fio2Text.toDoubleOrNull() ?: 21.0
    val paco2 = paco2Text.toDoubleOrNull() ?: 40.0
    val pao2 = pao2Text.toDoubleOrNull()
    val age = ageText.toDoubleOrNull()
    val pb = pbText.toDoubleOrNull() ?: 760.0

    val result = CalculatorsEngine.calculateAlveolarGas(
        fio2Percent = fio2,
        paco2 = paco2,
        measuredPao2 = pao2,
        patientAge = age,
        barometricPressureMmhg = pb
    )

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("calc_alv_gas_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Alveolar Gas Equation & A-a Gradient",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "PAO₂ = PiO₂ - (PaCO₂ / 0.8) • Hypoxemia Differential",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
                OutlinedButton(
                    onClick = {
                        fio2Text = "21"
                        paco2Text = "40"
                        pao2Text = "95"
                        ageText = "45"
                        pbText = "760"
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgicalCyan),
                    modifier = Modifier.testTag("reset_alv_gas_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = fio2Text,
                    onValueChange = { fio2Text = it },
                    label = { Text("FiO₂ (%)") },
                    placeholder = { Text("21 - 100") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_fio2")
                )
                OutlinedTextField(
                    value = paco2Text,
                    onValueChange = { paco2Text = it },
                    label = { Text("PaCO₂ (mmHg)") },
                    placeholder = { Text("35 - 45") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_paco2")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = pao2Text,
                    onValueChange = { pao2Text = it },
                    label = { Text("Arterial PaO₂ (mmHg)") },
                    placeholder = { Text("Optional") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_pao2")
                )
                OutlinedTextField(
                    value = ageText,
                    onValueChange = { ageText = it },
                    label = { Text("Age (Years)") },
                    placeholder = { Text("For normal A-a") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = calcTextFieldColors(),
                    modifier = Modifier.weight(1f).testTag("input_alv_age")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = pbText,
                onValueChange = { pbText = it },
                label = { Text("Barometric Pressure (mmHg, default 760 sea level)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = calcTextFieldColors(),
                modifier = Modifier.fillMaxWidth().testTag("input_pb")
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (result != null) {
                val items = mutableListOf(
                    "Calculated Alveolar O₂ (PAO₂)" to "${result.pao2Calculated} mmHg",
                    "Inspired O₂ Tension (PiO₂)" to "${result.pio2} mmHg"
                )
                if (result.aaGradient != null) {
                    items.add("A-a Oxygen Gradient" to "${result.aaGradient} mmHg")
                }
                if (result.expectedAaGradient != null) {
                    items.add("Expected Normal for Age" to "≤ ${result.expectedAaGradient} mmHg (Age/4 + 4)")
                }

                ResultDisplayCard(
                    title = "Alveolar Gas Equation Results",
                    items = items,
                    equation = result.formulaSteps,
                    note = result.clinicalDifferentials,
                    noteColor = if (result.isElevatedAaGradient == true) AlarmRose else VitalGreen
                )
            } else {
                Text("Enter valid FiO2 (21-100%), PaCO2 (5-150 mmHg), and Barometric Pressure (400-850 mmHg)", color = AlarmRose, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun ResultDisplayCard(
    title: String,
    items: List<Pair<String, String>>,
    equation: String,
    note: String,
    noteColor: Color = ClinicalTeal
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = NavySurfaceVariant,
        border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
            Spacer(modifier = Modifier.height(8.dp))

            items.forEach { (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = label, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = BorderSubtle)
            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Formula & Mathematical Steps:", style = MaterialTheme.typography.labelSmall, color = TextMuted)
            Text(text = equation, style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp)

            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = noteColor.copy(alpha = 0.12f),
                border = androidx.compose.foundation.BorderStroke(0.5.dp, noteColor.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = note,
                    style = MaterialTheme.typography.bodySmall,
                    color = noteColor,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun calcTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = SurgicalCyan,
    unfocusedBorderColor = BorderSubtle,
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    focusedContainerColor = NavySurfaceVariant,
    unfocusedContainerColor = NavySurfaceVariant
)
