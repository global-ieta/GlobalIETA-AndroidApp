package com.example.ieta.data.repository

import com.example.ieta.domain.model.Industry
import com.example.ieta.domain.model.Product
import com.example.ieta.domain.model.ProductStatus
import com.example.ieta.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class MockProductRepository : ProductRepository {

    private val industries = listOf(
        Industry(
            id = "gaming",
            name = "Gaming & Interactive",
            code = "IND-GAM-01",
            description = "High-fidelity metaverse rendering and low-latency multiplayer mesh.",
            iconName = "SportsEsports",
            accentColorHex = "#8B5CF6",
            productCount = 4
        ),
        Industry(
            id = "education",
            name = "EduTech & Research",
            code = "IND-EDU-02",
            description = "Immersive academic simulation labs and collaborative VR classrooms.",
            iconName = "School",
            accentColorHex = "#2979FF",
            productCount = 3
        ),
        Industry(
            id = "equine",
            name = "Equine & Sports Analytics",
            code = "IND-EQU-03",
            description = "Biomechanical tracking, gait diagnostics, and digital twin equine health.",
            iconName = "Pets",
            accentColorHex = "#21C88A",
            productCount = 2
        ),
        Industry(
            id = "business",
            name = "Enterprise Operations",
            code = "IND-BUS-04",
            description = "Cross-platform data connector pipelines and quantum cloud workspace.",
            iconName = "BusinessCenter",
            accentColorHex = "#00D4FF",
            productCount = 5
        ),
        Industry(
            id = "marketplace",
            name = "Global Marketplace",
            code = "IND-MKT-05",
            description = "DeCentralized spatial asset exchange and smart licensing hub.",
            iconName = "Storefront",
            accentColorHex = "#FF8A3D",
            productCount = 6
        ),
        Industry(
            id = "legal",
            name = "Legal & Compliance",
            code = "IND-LGL-06",
            description = "Smart contract audit trails, IP registry, and global regulatory guardrails.",
            iconName = "Gavel",
            accentColorHex = "#D9A93A",
            productCount = 2
        )
    )

    private val products = listOf(
        Product(
            id = "arin",
            name = "ARIN Engine",
            code = "PROD-ARIN-01",
            tagline = "Spatial Rendering & AR Core",
            description = "Unified spatial computing engine offering sub-millisecond pose tracking, real-time occlusion, and universal XR headset interoperability.",
            industry = "gaming",
            iconName = "ViewInAr",
            accentColorHex = "#00D9FF",
            status = ProductStatus.ACTIVE,
            features = listOf(
                "6DoF Spatial Pose Estimation",
                "Neural Mesh Reconstruction",
                "Photorealistic Shader Pipelines",
                "Multi-User Spatial Anchor Sync"
            ),
            metrics = mapOf("FPS" to "120 target", "Latency" to "< 2.4ms", "Uptime" to "99.99%"),
            rating = 4.9,
            downloads = 340000
        ),
        Product(
            id = "aero",
            name = "AERO Mobility",
            code = "PROD-AERO-02",
            tagline = "Aviation & Drone Fleet Telemetry",
            description = "Autonomous airspace orchestration system with live 3D radar tracking and automated conflict prediction.",
            industry = "business",
            iconName = "FlightTakeoff",
            accentColorHex = "#2578FF",
            status = ProductStatus.ENTERPRISE,
            features = listOf(
                "ADS-B & Radar Data Fusion",
                "Automated Flight Path Optimization",
                "Weather Hazard AI Warnings",
                "Encrypted Drone Command Mesh"
            ),
            metrics = mapOf("Active Craft" to "12,450", "Safety Record" to "100%", "Coverage" to "Global"),
            rating = 4.85,
            downloads = 88000
        ),
        Product(
            id = "campus",
            name = "IETA CAMPUS",
            code = "PROD-CMP-03",
            tagline = "Immersive Virtual University",
            description = "Full-scale virtual campus environment for higher education, remote surgical training, and engineering physics labs.",
            industry = "education",
            iconName = "School",
            accentColorHex = "#2979FF",
            status = ProductStatus.ACTIVE,
            features = listOf(
                "Haptic Tele-Presence Labs",
                "Interactive 3D Anatomy & Physics Models",
                "Multi-Student Lecture Auditoriums",
                "AI Tutors with AURA Integration"
            ),
            metrics = mapOf("Active Students" to "450k", "Universities" to "85", "Satisfaction" to "98%"),
            rating = 4.92,
            downloads = 510000
        ),
        Product(
            id = "connector",
            name = "IETA CONNECTOR",
            code = "PROD-CON-04",
            tagline = "Enterprise Interoperability Matrix",
            description = "High-speed API gateway bridging legacy SQL/SAP databases with modern spatial metaverse nodes.",
            industry = "business",
            iconName = "Hub",
            accentColorHex = "#00D4FF",
            status = ProductStatus.ACTIVE,
            features = listOf(
                "Zero-Trust Data Protocol",
                "REST / gRPC / WebSockets Bridges",
                "Auto-Schema Converter",
                "Low-Code Integration Canvas"
            ),
            metrics = mapOf("Throughput" to "1.2M req/sec", "Latency" to "< 0.8ms", "Bridges" to "50+"),
            rating = 4.88,
            downloads = 195000
        ),
        Product(
            id = "rideos",
            name = "RIDEOS Core",
            code = "PROD-RDS-05",
            tagline = "Autonomous Fleet Operating System",
            description = "Real-time operating mesh for autonomous vehicles, shuttles, and urban mobility transport fleets.",
            industry = "business",
            iconName = "DirectionsCar",
            accentColorHex = "#21C88A",
            status = ProductStatus.BETA,
            features = listOf(
                "V2X Vehicle Communication",
                "Dynamic Dispatch Algorithm",
                "LiDAR / Camera Fusion Middleware",
                "Remote Operator Tele-Override"
            ),
            metrics = mapOf("Connected Shuttles" to "3,200", "Avg Delay" to "0.1s", "Efficiency" to "+34%"),
            rating = 4.78,
            downloads = 64000
        ),
        Product(
            id = "workspace",
            name = "IETA WORKSPACE",
            code = "PROD-WSP-06",
            tagline = "Quantum Spatial Collaboration",
            description = "Spatial office suite bringing multi-screen spatial monitors, whiteboards, and digital avatars together.",
            industry = "business",
            iconName = "Work",
            accentColorHex = "#00CFFF",
            status = ProductStatus.ACTIVE,
            features = listOf(
                "Unlimited Virtual Monitors",
                "Spatial Audio Isolation Pods",
                "Real-time Document Co-editing",
                "End-to-End Biometric Auth"
            ),
            metrics = mapOf("Daily Users" to "820k", "Teams" to "14,000", "Prod Gain" to "+42%"),
            rating = 4.95,
            downloads = 620000
        )
    )

    private val productsFlow = MutableStateFlow(products)
    private val industriesFlow = MutableStateFlow(industries)

    override fun getProducts(): Flow<List<Product>> = productsFlow

    override fun getProductById(id: String): Flow<Product?> =
        productsFlow.map { list -> list.find { it.id.equals(id, ignoreCase = true) } }

    override fun getProductsByIndustry(industryId: String): Flow<List<Product>> =
        productsFlow.map { list -> list.filter { it.industry.equals(industryId, ignoreCase = true) } }

    override fun getIndustries(): Flow<List<Industry>> = industriesFlow

    override fun searchProducts(query: String): Flow<List<Product>> =
        productsFlow.map { list ->
            if (query.isBlank()) list
            else list.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.tagline.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
}
