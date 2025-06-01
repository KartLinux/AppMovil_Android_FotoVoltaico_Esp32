<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body style="background-color: #121212; color: #e0e0e0; font-family: Arial, sans-serif; line-height: 1.6;">

  <!-- Título principal -->
  <h1 style="font-size: 36px; font-weight: bold; color: #00ff00; text-align: center; text-transform: uppercase; letter-spacing: 2px;">
    📱 APP MÓVIL PARA CONTROL DE ESP32 CON BLUETOOTH ⚡
  </h1>
  <!-- Descripción del proyecto -->
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <p style="font-size: 18px; text-align: center;">
      Esta aplicación móvil desarrollada en Kotlin permite controlar un ESP32 mediante Bluetooth. La app incluye un joystick para mover servomotores manualmente y un botón para activar el modo automático, donde el ESP32 toma el control. Además, solicita permisos de ubicación.
    </p>
  </div>
  <!-- Imágen del proyecto arquitectura -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    Arquitectura del Proyecto e Imagenes de la App
  </h2>
  <div style="text-align: center; margin: 20px 0;">
    <table style="margin: 0 auto; border-collapse: collapse;">
      <tr>
        <td><img src="https://github.com/user-attachments/assets/410d32f9-9da1-4e8b-966f-613e8608ee4a" width="300" style="border: 2px solid #ccc; border-radius: 10px;" /></td>
        <td><img src="https://github.com/user-attachments/assets/bce5e53c-6e86-4582-a969-165d680df7e6" width="300" style="border: 2px solid #ccc; border-radius: 10px;" /></td>
        <td><img src="https://github.com/user-attachments/assets/1edb9ab4-0809-477c-8fb2-56674bba5d0d" width="300" style="border: 2px solid #ccc; border-radius: 10px;" /></td>
      </tr>
      <tr>
        <td style="text-align: center; color: #00ff00; font-size: 14px;">Arquitectura</td>
        <td style="text-align: center; color: #00ff00; font-size: 14px;">Pantalla Inicio App</td>
        <td style="text-align: center; color: #00ff00; font-size: 14px;">Pantalla Funciones App</td>
      </tr>
    </table>
  </div>
  <!-- Imágenes del proyecto -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    🖼️ IMÁGENES DE LAS TECNOLOGIA USADAS
  </h2>

  <div style="text-align: center; margin: 20px 0;">
    <table style="margin: 0 auto; border-collapse: collapse;">
      <tr>
        <td><img src="https://github.com/user-attachments/assets/b515796e-1147-4d64-a8e8-bdcbe5afd5af" width="300" style="border: 2px solid #ccc; border-radius: 10px;" /></td>
        <td><img src="https://github.com/user-attachments/assets/5fcc9459-83a8-4baa-b178-a80d9ef8468f" width="300" style="border: 2px solid #ccc; border-radius: 10px;" /></td>
        <td><img src="https://github.com/user-attachments/assets/7eee0327-8723-47c9-8f34-46922e6e3882" width="300" style="border: 2px solid #ccc; border-radius: 10px;" /></td>
      </tr>
      <tr>
        <td style="text-align: center; color: #00ff00; font-size: 14px;">APK</td>
        <td style="text-align: center; color: #00ff00; font-size: 14px;">Lenguaje de Programacion</td>
        <td style="text-align: center; color: #00ff00; font-size: 14px;">Conexion</td>
      </tr>
    </table>
  </div>

  <!-- Características principales -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    🛠️ CARACTERÍSTICAS PRINCIPALES
  </h2>
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <ul style="list-style-type: none; padding: 0;">
      <li><span style="color: #00ff00;">📱 Interfaz intuitiva:</span> Joystick para control manual de servos.</li>
      <li><span style="color: #00ff00;">🤖 Modo automático:</span> Activa el control automático en el ESP32.</li>
      <li><span style="color: #00ff00;">🔗 Conexión Bluetooth:</span> Se conecta al ESP32 con el nombre <code>ROOT_ESP32</code>.</li>
      <li><span style="color: #00ff00;">📍 Permiso de ubicación:</span> Solicita permisos de ubicación.</li>
    </ul>
  </div>

  <!-- Dependencias utilizadas -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    📚 DEPENDENCIAS UTILIZADAS
  </h2>
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <ul style="list-style-type: none; padding: 0;">
      <li><span style="color: #00ff00;">androidx-core-ktx:</span> Versión <code>1.13.1</code>.</li>
      <li><span style="color: #00ff00;">Kotlin:</span> Versión <code>1.9.0</code>.</li>
      <li><span style="color: #00ff00;">Compose BOM:</span> Versión <code>2024.04.01</code>.</li>
      <li><span style="color: #00ff00;">Play Services Location:</span> Versión <code>21.3.0</code>.</li>
      <li><span style="color: #00ff00;">Lifecycle ViewModel:</span> Versión <code>2.6.1</code>.</li>
      <li><span style="color: #00ff00;">MPAndroidChart:</span> Versión <code>3.1.0</code>.</li>
    </ul>
  </div>

  <!-- Configuración inicial -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    🔧 CONFIGURACIÓN INICIAL
  </h2>
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <ol style="list-style-type: decimal; padding-left: 20px;">
      <li>Configura el ESP32 con Bluetooth y el nombre <code>ROOT_ESP32</code>.</li>
      <li>Clona este repositorio y ábrelo en Android Studio.</li>
      <li>Asegúrate de tener las siguientes dependencias instaladas en tu archivo <code>build.gradle</code>:</li>
      <pre style="background-color: #21252b; color: #d4d4d4; padding: 10px; border-radius: 5px; font-family: 'Courier New', monospace;">
dependencies {
    implementation "androidx.core:core-ktx:1.13.1"
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.9.0"
    implementation "androidx.compose:compose-bom:2024.04.01"
    implementation "com.google.android.gms:play-services-location:21.3.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
    implementation "com.github.PhilJay:MPAndroidChart:v3.1.0"
}
      </pre>
      <li>Compila y ejecuta la app en un dispositivo Android.</li>
      <li>Conéctate al ESP32 desde la app y comienza a controlar los servos.</li>
    </ol>
  </div>

  <!-- Ejemplo de uso -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    🎮 EJEMPLO DE USO
  </h2>
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <p style="font-size: 16px;">
      Usa el joystick para mover los servos manualmente. Para activar el modo automático, presiona el botón correspondiente en la app.
    </p>
  </div>

  <!-- Contribuciones -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    🤝 CONTRIBUCIONES
  </h2>
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <p style="font-size: 16px;">
      ¡Las contribuciones son bienvenidas! Si encuentras errores o tienes ideas para mejorar este proyecto, sigue estos pasos:
    </p>
    <ol style="list-style-type: decimal; padding-left: 20px;">
      <li>Haz un fork del repositorio.</li>
      <li>Crea una nueva rama (<code>git checkout -b feature/nueva-funcionalidad</code>).</li>
      <li>Haz commit de tus cambios (<code>git commit -m "Agrega nueva funcionalidad"</code>).</li>
      <li>Sube tus cambios (<code>git push origin feature/nueva-funcionalidad</code>).</li>
      <li>Abre un pull request.</li>
    </ol>
  </div>

  <!-- Licencia -->
  <h2 style="font-size: 24px; font-weight: bold; color: #ff4500; text-align: center; margin-top: 30px;">
    📜 LICENCIA
  </h2>
  <div style="background-color: #282c34; color: #abb2bf; padding: 20px; border-radius: 10px; margin: 20px 0;">
    <p style="font-size: 16px; text-align: center;">
      Este proyecto está bajo la licencia <a href="LICENSE" style="color: #00ff00;">MIT</a>. ¡Usa y modifica el código como quieras!
    </p>
  </div>

</body>
</html>
