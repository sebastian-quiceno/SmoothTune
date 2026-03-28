# Requisitos Smooth Tune

## Requisitos funcionales 

### 1.	Gestión de usuarios
-	RF-01: El sistema debe permitir el registro de nuevos usuarios 
-	RF-02: El sistema debe permitir autenticación (login/logout) 
-	RF-03: El sistema debe almacenar información básica del usuario 
-	RF-04: El usuario debe poder ver y editar su perfil

---

### 2. Gestión de canciones (colección global)
*	RF-05: El sistema debe permitir listar todas las canciones disponibles 
*	RF-06: El sistema debe permitir reproducir una canción 
*	RF-07: El sistema debe permitir subir nuevas canciones a la colección global
* RF-08: Cada canción debe tener:

  * Titulo
  * Artista
  * Género
  * Ruta del archivo (filePath)
 
 *	RF-09: El sistema debe validar el formato de los archivos (ej: MP3, WAV)

---

### 4. Biblioteca personal (UserSong)
-	RF-10: El usuario debe poder guardar canciones de la colección global 
-	RF-11: El usuario debe poder eliminar canciones de su biblioteca 
-	RF-12: El usuario debe poder listar sus canciones guardadas 

--- 

### 5. Playlists
-	RF-13: El usuario debe poder crear playlists 
-	RF-14: El usuario debe poder eliminar playlists 
-	RF-15: El usuario debe poder editar el nombre de una playlist 
-	RF-16: El usuario debe poder agregar canciones a una playlist 
-	RF-17: El usuario debe poder quitar canciones de una playlist 
-	RF-18: El usuario debe poder visualizar las canciones de una playlist 

---

### 6. Géneros
-	RF-19: El sistema debe permitir clasificar canciones por género 
-	RF-20: El usuario debe poder filtrar canciones por género 
-	RF-21: El sistema debe permitir administrar géneros (crear/editar/eliminar) 

---

### 7. Búsqueda y filtrado
-	RF-22: El usuario debe poder buscar canciones por nombre 
-	RF-23: El usuario debe poder buscar por género 

---

### 8. Reproducción
-	RF-24: El sistema debe permitir reproducir audio en streaming 
-	RF-25: El usuario debe poder pausar y reanudar canciones 
-	RF-26: El usuario debe poder avanzar/retroceder la reproducción 

---
 
### 9. Gestión de cola
-	RF-27: El sistema debe mantener una cola de reproducción por usuario 
-	RF-28: El sistema debe iniciar la reproducción desde la cola 
-	RF-29: Cuando una canción termina, debe reproducirse automáticamente la siguiente

---

## Requisitos no funcionales

### 1. Rendimiento
-	RNF-01: El sistema debe cargar canciones en menos de 2 segundos 
-	RNF-02: El streaming debe ser fluido sin interrupciones 

---

### 2. Seguridad
-	RNF-03: Autenticación segura (JWT Spring Secutiry) 
-	RNF-04: Control de acceso (usuarios solo acceden a sus playlists) 
-	RNF-05: Validación de subida de archivos 

---

### 3. Almacenamiento
-	RNF-06: Los archivos deben almacenarse en: 
-	Sistema local o 
-	Servicio externo (ej: S3, recomendado para escalabilidad) 

---

### 4. Usabilidad
-	RNF-07: Interfaz web intuitiva 
-	RNF-08: Compatible con navegadores modernos 

---

### 5. Escalabilidad
-	RNF-09: El sistema debe soportar múltiples usuarios concurrentes 

---

## Reglas de negocio

-	RN-01: Un usuario solo puede modificar sus propias playlists 
-	RN-02: Una canción subida pasa automáticamente a la colección global 
-	RN-03: No se pueden duplicar canciones en una misma playlist 
-	RN-04: Un usuario no puede guardar la misma canción más de una vez 
-	RN-05: Cada canción debe pertenecer a al menos un género 


