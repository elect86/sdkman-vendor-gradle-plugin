//import io.sdkman.vendors

plugins {
//    id "groovy"
    id("com.gradle.plugin-publish") version "0.10.1"
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    distribution
    `maven-publish`
    `java-library`
}

group = "io.sdkman"
base.archivesBaseName = "sdkman-vendor-gradle-plugin"

tasks {
    compileKotlin {
        kotlinOptions { jvmTarget = "1.8" }
        sourceCompatibility = "1.8"
    }
}

repositories {
    jcenter()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.beust:klaxon:5.2")
    implementation("khttp:khttp:1.0.0")
    implementation(gradleApi())
    implementation("org.hibernate:hibernate-validator:5.1.3.Final")
    implementation("javax.el:javax.el-api:2.2.4")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.web:javax.el:2.2.4")

    testImplementation("cglib:cglib-nodep:3.1")
    testImplementation("com.github.tomakehurst:wiremock:1.46")
    listOf("runner-junit5", "assertions-core", "runner-console"/*, "property"*/).forEach {
        testImplementation("io.kotest:kotest-$it-jvm:4.1.0.293-SNAPSHOT")
    }
}

tasks.withType<Test> { useJUnitPlatform() }

pluginBundle {
    website = "http://sdkman.io/"
    vcsUrl = "https://github.com/sdkman/gradle-sdkman-vendor-plugin"
    description = "The SDKMAN Vendor Gradle Plugin."
    tags = listOf("sdkman", "sdk", "gvm", "gvmtool")

//    plugins {
//        sdkmanVendorPlugin {
////            id = "io.sdkman.vendors"
////            displayName = "SDKMAN! Vendor Plugin"
//        }
//    }
}

distributions {
    main {
        distributionBaseName.set(base.archivesBaseName)
        contents {
            from("libsDir")
        }
    }
}

object bintray {
    val baseUrl = "https://api.bintray.com/maven"
    val username = "sdkman"
    val repository = "gradle-plugins"
    val `package` = "sdkman-vendor-gradle-plugin"
}

java {
//    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            artifact("sourceJar")

            pom.withXml {
                asNode().apply {
                    appendNode("name", "SDKMAN! Vendor Gradle plugin")
                    appendNode("description", "Gradle plugin for SDKMAN! Vendors to publish Releases.")
                    appendNode("inceptionYear", "2018")

                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", "The Apache Software License, Version 2.0")
                        appendNode("url", "http://www.apache.org/licenses/LICENSE-2.0.txt")
                        appendNode("distribution", "repo")
                    }

                    appendNode("developers").appendNode("developer").apply {
                        appendNode("name", "Marco Vermeulen")
                        appendNode("email", "marco@sdkman.io")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "Bintray"
            url = uri("${bintray.baseUrl}/${bintray.username}/${bintray.repository}/${bintray.`package`}")

            credentials {
                username = System.getenv("BINTRAY_USERNAME") ?: "invalid_user"
                password = System.getenv("BINTRAY_API_KEY") ?: "invalid_key"
            }
        }
    }
}
