package com.example.data.repository

import com.example.data.model.Drug
import com.example.data.model.DrugCategory

object PharmacologyData {

    val drugs: List<Drug> = (
        InductionAndVolatileData.drugs +
        AnalgesicsAndLocalAnestheticsData.drugs +
        NeuromuscularAndReversalData.drugs +
        CardiovascularAndAntihypertensiveData.drugs +
        EmergencyObstetricAndFluidsData.drugs
    ).distinctBy { it.id }

    fun getDrugsByCategory(category: DrugCategory): List<Drug> =
        drugs.filter { it.category == category }

    fun searchDrugs(query: String, categoryFilter: DrugCategory? = null): List<Drug> {
        val q = query.trim().lowercase()
        return drugs.filter { drug ->
            val matchesCategory = categoryFilter == null || drug.category == categoryFilter
            val matchesQuery = q.isBlank() ||
                drug.genericName.lowercase().contains(q) ||
                drug.brandNames.lowercase().contains(q) ||
                drug.synonyms.any { it.lowercase().contains(q) } ||
                drug.drugClass.lowercase().contains(q) ||
                drug.targetReceptor.lowercase().contains(q) ||
                drug.indications.any { it.lowercase().contains(q) }
            matchesCategory && matchesQuery
        }
    }
}
