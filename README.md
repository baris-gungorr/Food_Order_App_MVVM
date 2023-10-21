# Food_Order_App_MVVM

📸 Screenshots
<table>
  <tr>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/blob/main/app/src/main/res/orderApp/si.png" alt="TO_DO_APP" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/blob/main/app/src/main/res/orderApp/su.png" alt="NOTLARIM" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/blob/main/app/src/main/res/orderApp/main.png" alt="NOTLARIM" width="250">
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/blob/main/app/src/main/res/orderApp/fav.png" alt="NOTLARIM" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/blob/main/app/src/main/res/orderApp/or.png" alt="NOTLARIM" width="250">
    </td>
    <td align="center">
      <img src="https://github.com/baris-gungorr/Food_Order_App_MVVM/blob/main/app/src/main/res/orderApp/sea.png" alt="NOTLARIM" width="250">
    </td>
  </tr>
</table>

📸 Videos

 [https://drive.google.com/file/d/1UfvVho9a1OpOMq8Q72P9oJNV9BIjTH_M/view?usp=share_link]


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

