UnitConverterUsingJetpack

UnitConverterUsingJetpack is a modern, lightweight Android app built with Kotlin and Jetpack Compose. It provides fast, accurate unit conversions across common categories (length, mass, temperature, volume, etc.) with a clean Material3 UI and a modular architecture — designed for scalability, maintainability, and rapid iteration.

🚀 Key Features

Simple, intuitive Compose-based UI (Material3)
Multiple conversion categories: Length, Mass, Temperature, Volume, Area, Speed, etc.
Responsive — works on phones and tablets
Compose-native Splash Screen (customizable)
Modular code structure for easy extension
No runtime-heavy dependencies — fast cold start

📦 Repo Structure (high level)
app/
 ├─ src/main/java/com/example/unitconverterusingjetpack/
 │   ├─ MainActivity.kt
 │   ├─ SplashScreen.kt
 │   ├─ ui/
 │   │   ├─ theme/                # Theme & styling (Material3)
 │   │   └─ components/           # Reusable composables
 │   └─ features/                 # Conversion logic & screens
 ├─ res/
 │   ├─ drawable/                 # logos, pngs & vectors
 │   ├─ layout/
 │   └─ values/
 └─ build.gradle

🛠️ Tech Stack

Language: Kotlin
UI: Jetpack Compose (Material3)
Min SDK: 21+ (adjustable)
Gradle: Android Gradle Plugin (compatible version)
Optional: androidx.core:core-splashscreen for Android 12+ control (recommended but not required)
⚙️ Installation & Run (Quick Start)

Clone the repo:

git clone https://github.com/mohdazadchaudhary/UnitConverterUsingJetpack.git
cd UnitConverterUsingJetpack

Open the project in Android Studio.
Gradle sync and build:
From Android Studio: Build > Clean Project, then Build > Rebuild Project

Or CLI:
./gradlew assembleDebug
Run on device/emulator:
Use the Run configuration in Android Studio or:
./gradlew installDebug

🧭 How It Works (Usage)

Launch the app — you’ll see the splash screen (customizable in SplashScreen.kt).
Select the conversion category (Length, Mass, Temperature, ...).
Choose input and output units from the dropdowns.
Enter the value to convert — result updates in real-time.
Buttons/UI controls for clearing, swapping units, or copying result.

🔧 Customizing Splash Screen

If you want to change the splash behaviour:
SplashScreen.kt contains the Compose implementation and delay.
For system-managed splash (Android 12+), add the core-splashscreen dependency and install it in MainActivity:
implementation "androidx.core:core-splashscreen:1.0.1"
Then call installSplashScreen() in MainActivity.onCreate() and manage setKeepOnScreenCondition as needed.
If using a bitmap (PNG) logo, use Image() with painterResource(R.drawable.your_logo) — do not use Icon() for bitmaps. For vectors, Icon() is fine but set tint = Color.Unspecified if you want original colors preserved.

✅ Example: Show Splash then Main UI (Compose)
setContent {
    UnitConverterUsingJetpackTheme {
        var showSplash by remember { mutableStateOf(true) }

        if (showSplash) {
            SplashScreen(onTimeout = { showSplash = false })
        } else {
            Scaffold { UnitConvertUI() }
        }
    }
}

📌 Best Practices & Tips

Use Image() for PNG/JPG bitmaps, Icon() for vector drawables (XML).
Keep resource names lowercase and underscore-separated: app_logo.png, ic_converter.xml.
Use Color.Unspecified for Icon.tint when you need original vector colors.
Make conversion logic unit-tested (pure functions) so you can validate correctness easily.
Keep UI stateless where possible, lift state to view models for complex flows.

🧪 Testing

Unit-test conversion functions (Kotlin/JUnit).
For UI, use Compose UI tests to validate screens and user flows.

🤝 Contribution

Contributions are welcome. Please follow these steps:

Fork the repo.
Create a feature branch: git checkout -b feat/my-feature
Make changes and add tests where appropriate.
Submit a PR with a clear description and changelog.
Coding standards: consistent Kotlin style, simple functions, clear naming, small commits.

📝 License

This project is available under the MIT License — feel free to reuse and adapt.
(Replace this with your preferred license if needed.)

👤 Author

Mohd Azad Chaudhary
Android Developer — Jetpack Compose enthusiast

🔗 Contact / Support

If you need help or want features added:
Open an issue in the repository
Or contact: mohdazadchaudhary5 on LinkedIn
