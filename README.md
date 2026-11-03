# ☕ Kopi dan Nyemil

Aplikasi pemesanan kopi dan makanan ringan berbasis Android, dibuat dengan **Kotlin** dan **Jetpack Compose**. Proyek ini adalah tugas UTS mata kuliah **Pengembangan Aplikasi Mobile**.


## Deskripsi Aplikasi

**Kopi dan Nyemil** memungkinkan pengguna mendaftar atau masuk, melihat daftar menu makanan dan minuman, memilih ukuran dan jumlah pesanan, mengisi alamat pengiriman, lalu mengonfirmasi pesanan. Seluruh tampilan dibuat berdasarkan wireframe tugas (Splash, Sign Up, Login, Register, Home, Profile, Alamat Pengiriman, dan Konfirmasi Pesanan), lalu dikembangkan agar lebih modern dengan palet warna karamel, kartu membulat, dan animasi.

Aplikasi ini **tidak menggunakan database**. Seluruh data (menu, akun, keranjang) bersifat statis atau disimpan sementara di memori, sesuai ketentuan tugas.

## Fitur

**Autentikasi (dummy)**
- Splash screen dengan latar gradien yang bergerak dan logo aplikasi
- Sign Up sebagai pintu masuk menuju Register atau Login
- Register (nama lengkap, username, password) dan Login (username, password)
- Tombol aksi baru aktif setelah semua kolom terisi
- Tombol kembali di layar yang membutuhkan

**Menu dan Pemesanan**
- Daftar 6 menu makanan dan minuman lengkap dengan foto, deskripsi, dan harga
- Halaman Detail Item: pilih ukuran (S, M, L) untuk minuman, atur jumlah, dan lihat subtotal
- Keranjang pesanan: ubah jumlah, hapus item, dan total pembayaran otomatis
- Badge jumlah item pada tab Order
- Form alamat pengiriman (nama lengkap, alamat, patokan atau catatan kurir)
- Layar konfirmasi pesanan berisi ringkasan dan total yang dibayar

**Profil**
- Ubah nama pengguna dan simpan
- Logout

**Tampilan dan Animasi**
- Bottom navigation (Home, Order, Profile) dengan indikator _sliding pill_
- Transisi antar layar (geser dan memudar), termasuk transisi searah antar tab
- Animasi tekan pada tombol (mengecil lalu kembali)
- Latar splash bergradien animasi

## Daftar Menu

| Menu | Harga |
|---|---|
| Kopi Susu Gula Aren | Rp 22.000 |
| Donat Cokelat | Rp 12.000 |
| Sourdough Loaf | Rp 35.000 |
| Cappuccino | Rp 25.000 |
| Matcha Latte | Rp 27.000 |
| Roti Jala | Rp 15.000 |

Untuk menu minuman, ukuran M menambah Rp 3.000 dan ukuran L menambah Rp 6.000 dari harga dasar (ukuran S). Donat, sourdough, dan roti jala tidak memiliki pilihan ukuran.

## Teknologi yang Digunakan

| Komponen | Keterangan |
|---|---|
| Bahasa | Kotlin 2.2.10 |
| UI | Jetpack Compose + Material 3 (Compose BOM 2026.02.01) |
| Navigasi | Navigation Compose 2.10.2 |
| Ikon | Material Icons Core |
| Build | Android Gradle Plugin 9.4.1, Gradle Version Catalog |
| Min SDK / Target SDK | 27 / 37 |
| Penyimpanan data | Tanpa database, data statis (dummy) dan state di memori |

## Struktur Proyek

```
app/src/main/java/lat/pam/kopidannyemil/
├── MainActivity.kt          # Titik masuk aplikasi
├── data/
│   ├── MenuItem.kt          # Model dan data dummy menu
│   ├── CartState.kt         # Keranjang, ukuran, jumlah, total
│   └── UserSession.kt       # Nama pengguna yang sedang aktif
├── navigation/
│   ├── routes.kt            # Daftar rute layar
│   └── AppNavHost.kt        # Peta navigasi dan transisi
├── screens/                 # Seluruh layar dan komponen UI
│   ├── SplashScreen.kt
│   ├── SignUpScreen.kt
│   ├── LoginScreen.kt
│   ├── RegisterScreen.kt
│   ├── HomeScreen.kt
│   ├── DetailScreen.kt
│   ├── OrderScreen.kt
│   ├── ConfirmScreen.kt
│   ├── ProfileScreen.kt
│   ├── AppBottomBar.kt      # Bottom navigation + sliding pill
│   └── Components.kt        # Komponen bersama (tombol, input, top bar)
└── ui/theme/                # Warna, tipografi, tema
```

## Alur Aplikasi

```
Splash → Sign Up ─┬→ Register ─┐
                  └→ Login ────┴→ Home ─┬→ Detail Item → Order (keranjang + alamat) → Konfirmasi → Home
                                        ├→ Order
                                        └→ Profile (simpan nama / logout)
```

## Cara Menjalankan Aplikasi

**Prasyarat**
- Android Studio versi terbaru yang mendukung Android Gradle Plugin 9.4.1
- Emulator atau perangkat Android dengan API 27 (Android 8.1) atau lebih baru
- Koneksi internet saat sinkronisasi Gradle pertama kali

**Langkah**
1. Clone repositori ini:
   ```bash
   git clone https://github.com/riffq1122/PAM-kopi-dan-nyemil.git
   ```
2. Buka folder hasil clone di **Android Studio** (File → Open).
3. Tunggu proses **Gradle Sync** sampai selesai.
4. Pilih emulator atau hubungkan perangkat Android.
5. Klik tombol **Run** (▶) atau tekan `Shift + F10`.

**Catatan penggunaan:** karena tidak memakai database, akun bersifat dummy. Login dan Register hanya mensyaratkan seluruh kolom terisi, dan data pesanan akan hilang saat aplikasi ditutup.


## Pengembang

| |                              |
|---|------------------------------|
| Nama | _Rifqy Muhamad Ibrahim_      |
| NIM | 1247050132                   |
| Kelas | _D_                          |
| Mata Kuliah | Pengembangan Aplikasi Mobile |


## Catatan

- Foto produk dan logo hanya digunakan untuk keperluan tugas perkuliahan.
- Desain antarmuka mengambil inspirasi gaya visual dari desain komunitas "Coffee Shop Mobile App Design" (Figma Community), yaitu palet warna dan gaya kartu, lalu disesuaikan dengan wireframe tugas.
