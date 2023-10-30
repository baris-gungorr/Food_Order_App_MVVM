# Food_Order_App_MVVM

📸 Screenshots
<table>
  <tr>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/3c63598c-abe9-4b78-bede-a8ac8885d131" alt="TO_DO_APP" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/2c9a8fd1-7c02-4500-b9f7-4d0388805370" alt="NOTLARIM" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/98383824-9fde-4035-bc03-afae0d4a5d18" alt="NOTLARIM" width="250">
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/e768a771-c065-4d9d-9517-5680cd7a2a36" alt="NOTLARIM" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/0559d08f-4038-4381-8b2d-d50f1daad734" alt="NOTLARIM" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/8815b336-66d2-4cf3-97b9-e68a3b18dc2c" alt="NOTLARIM" width="250">
    </td>
  </tr>
</table>


📸 Videos
 <!-- Video -->
<div align="left">

https://github.com/baris-gungorr/Food_Order_App_MVVM/assets/121685398/3cca073d-b2c0-4814-918c-003c0af88675


  <video src= "VIDEO.mp4"/>
</div>


👇 Structures Used

Application architecture: MVVM

- View Binding 
- Coroutine
- ViewModel
- Navigation
- Hilt
- Room
- Retrofit
- SDP/SSP Library
- Firebase
- Glide
- ViewModel

 For animation : Lottie used  

  ✏️ Dependency
 ```gradle
 // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")

    // Firebase
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")

    // Lottie
    implementation("com.airbnb.android:lottie:5.2.0")

    //ssp-dsp
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.activity:activity-ktx:1.6.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Room
    implementation ("androidx.room:room-runtime:2.5.0-beta02")
    kapt("androidx.room:room-compiler:2.5.0-beta02")
    implementation("androidx.room:room-ktx:2.4.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")


```

```groovy
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

```

