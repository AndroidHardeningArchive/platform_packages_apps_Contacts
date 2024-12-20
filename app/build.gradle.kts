plugins {
    id("com.android.application")
    kotlin("android")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

android {
    compileSdk = 35
    buildToolsVersion = "35.0.0"

    namespace = "com.android.contacts"

    defaultConfig {
        minSdk = 34
        //noinspection OldTargetApi
        targetSdk = 34
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            val selfPkgName = android.namespace + applicationIdSuffix
            resValue("string", "applicationLabel", "Contacts d")
            resValue("string", "contacts_file_provider_authority", "$selfPkgName.files")
            resValue("string", "contacts_sdn_provider_authority", "$selfPkgName.sdn")
        }
    }

    sourceSets.getByName("main") {
        assets.srcDir("../assets")
        manifest.srcFile("../AndroidManifest.xml")
        java.srcDirs("../src", "../src-bind")
        res.srcDir("../res")
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.palette:palette:1.0.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.guava:guava:33.4.0-android")

    implementation(project(":lib:platform_external_libphonenumber"))
    implementation(project(":lib:platform_frameworks_ex:common"))
    implementation(project(":lib:platform_frameworks_opt_vcard"))
    implementation(project(":lib:platform_packages_apps_PhoneCommon"))
}
