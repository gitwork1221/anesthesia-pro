package com.example.ui.viewmodel

import com.example.data.repository.AnesthesiaRepository
import java.util.Locale

object SearchHelper {
    fun performSearch(repository: AnesthesiaRepository, query: String): List<SearchResultItem> {
        if (query.trim().isBlank()) return emptyList()

        val q = query.trim().lowercase(Locale.ROOT)
        val results = mutableListOf<SearchResultItem>()

        // Lessons
        repository.getAllLessons().forEach { lesson ->
            if (lesson.title.lowercase().contains(q) ||
                lesson.beginnerExplanation.lowercase().contains(q) ||
                lesson.category.displayName.lowercase().contains(q)
            ) {
                results.add(
                    SearchResultItem(
                        id = lesson.id,
                        title = lesson.title,
                        categoryName = lesson.category.displayName,
                        type = "Lesson",
                        destination = NavigationDestination.LessonDetail(lesson.id)
                    )
                )
            }
        }

        // Drugs
        repository.getAllDrugs().forEach { drug ->
            val matches = drug.genericName.lowercase().contains(q) ||
                    drug.brandNames.lowercase().contains(q) ||
                    drug.synonyms.any { it.lowercase().contains(q) } ||
                    drug.drugClass.lowercase().contains(q) ||
                    drug.targetReceptor.lowercase().contains(q) ||
                    drug.indications.any { it.lowercase().contains(q) }
            if (matches) {
                results.add(
                    SearchResultItem(
                        id = drug.id,
                        title = "${drug.genericName} (${drug.brandNames})",
                        categoryName = "${drug.category.displayName} • ${drug.drugClass}",
                        type = "Pharmacology",
                        destination = NavigationDestination.DrugDetail(drug.id)
                    )
                )
            }
        }

        // Surgical guides
        repository.getAllSurgicalGuides().forEach { guide ->
            if (guide.procedureName.lowercase().contains(q) || guide.surgicalSpecialty.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = guide.id,
                        title = guide.procedureName,
                        categoryName = guide.surgicalSpecialty,
                        type = "Surgical Guide",
                        destination = NavigationDestination.SurgicalGuideDetail(guide.id)
                    )
                )
            }
        }

        // Equipment
        repository.getAllEquipmentModules().forEach { equip ->
            if (equip.name.lowercase().contains(q) || equip.category.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = equip.id,
                        title = equip.name,
                        categoryName = equip.category,
                        type = "Equipment",
                        destination = NavigationDestination.EquipmentDetail(equip.id)
                    )
                )
            }
        }

        return results
    }
}
