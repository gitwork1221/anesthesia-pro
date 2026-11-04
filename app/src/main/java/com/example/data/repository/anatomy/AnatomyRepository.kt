package com.example.data.repository.anatomy

import com.example.data.model.AnatomySystemCategory
import com.example.data.model.AnatomyTopic

object AnatomyRepository {

    val allTopics: List<AnatomyTopic> = listOf(
        AirwayAndRespiratoryData.generalCellularTopic,
        AirwayAndRespiratoryData.respiratoryAirwayTopic,
        CardiovascularAndNervousData.cardiovascularTopic,
        CardiovascularAndNervousData.nervousSystemTopic,
        RenalHepaticEndocrineData.renalTopic,
        RenalHepaticEndocrineData.hepaticTopic,
        RenalHepaticEndocrineData.endocrineTopic,
        ObstetricNeonatalSurgicalData.pregnancyNeonatalTopic,
        ObstetricNeonatalSurgicalData.surgicalProceduralTopic
    )

    fun getTopicById(id: String): AnatomyTopic? {
        return allTopics.find { it.id == id }
    }

    fun getTopicsByCategory(category: AnatomySystemCategory?): List<AnatomyTopic> {
        return if (category == null) allTopics else allTopics.filter { it.category == category }
    }

    fun searchTopics(query: String): List<AnatomyTopic> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return allTopics
        return allTopics.filter { topic ->
            topic.title.lowercase().contains(q) ||
                    topic.subtitle.lowercase().contains(q) ||
                    topic.normalPhysiology.lowercase().contains(q) ||
                    topic.clinicalSignificance.lowercase().contains(q) ||
                    topic.anesthesiaImplications.lowercase().contains(q) ||
                    topic.learningObjectives.any { it.lowercase().contains(q) } ||
                    topic.commonExamDistinctions.any { it.lowercase().contains(q) } ||
                    topic.structures.any { struct ->
                        struct.name.lowercase().contains(q) ||
                                struct.description.lowercase().contains(q) ||
                                struct.clinicalPearl.lowercase().contains(q) ||
                                struct.relatedIntervention.lowercase().contains(q)
                    } ||
                    topic.assetMetadata.assetTitle.lowercase().contains(q)
        }
    }

    /**
     * Technical specification and status of the Interactive 3D Anatomy integration pipeline.
     */
    const val THREE_D_PIPELINE_STATUS =
        "Interactive 3D Engine Roadmap: Currently operating in 2D Interactive Landmark Mode. " +
        "True 3D model manipulation (yaw/pitch rotation, orbital camera zoom, reset view, and mesh hit-testing) " +
        "requires integrating the Google Sceneview / Filament Android library with GLTF/GLB textured polygon assets. " +
        "To comply with zero-mock standards, pseudo-3D animations and static renders have been excluded. " +
        "Full 2D interactive pinpoint visualization with verified clinical metadata and surgical links is active across all 9 systems."
}
