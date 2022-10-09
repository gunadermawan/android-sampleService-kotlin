# android-sampleService-kotlin 

## 1. background services
Background service dapat melakukan operasi yang tidak terlihat secara langsung oleh pengguna. Misalnya, ketika aplikasi menggunakan service untuk mengetahui lokasi pengguna secara realtime, itu biasanya dilakukan dalam background service.
![image](https://user-images.githubusercontent.com/53375007/194603074-bf894b57-3f39-4920-86fa-68b6793da7b9.png)

## 2. foreground services 
Foreground service dapat melakukan beberapa operasi yang terlihat oleh pengguna. Misalnya, aplikasi audio player yang menggunakan foreground service untuk memutar trek audio. Foreground service harus menampilkan pemberitahuan (Notification) selama service tersebut berjalan. Foreground service akan terus berjalan bahkan saat pengguna tidak berinteraksi dengan aplikasi, contohnya kembali ke halaman home atau membuka aplikasi lain.

![image](https://user-images.githubusercontent.com/53375007/194614092-8a8024af-05ff-4841-aeb8-534bff057b7e.png)
![image](https://user-images.githubusercontent.com/53375007/194614181-e4398eaa-1c08-46f9-8e4d-fd1ac09d78ea.png)

## 3. Bound services
Service jenis ini merupakan tipe service yang dijalankan oleh komponen lain, tetapi saling mengikat. Hubungan yang terjadi antara kedua komponen tersebut seperti client-server. Bisa saling menerima hasil dan menerima request yang ada. Pada service ini dimungkinkan terjadinya proses IPC (Interprocess Communication). Service ini akan tetap berjalan di background selama masih ada komponen lain yang mengikatnya. Jika tidak, service akan dimatikan oleh sistem. Aplikasi pemutar musik merupakan salah satu jenis aplikasi yang mengimplementasikan service jenis ini.
![image](https://user-images.githubusercontent.com/53375007/194760007-b5a34879-0116-44e0-a84d-91745f0f298b.png)
![image](https://user-images.githubusercontent.com/53375007/194760011-daa00666-357d-4480-92c6-df41ca78e959.png)

