
# WatchList 

Aplikasi pencatat daftar tontonan (film dan drama) sederhana berbasis **Jetpack Compose** yang menerapkan **Adaptive Layout**, arsitektur **MVVM**, **UI State**, **StateFlow**, dan **Unidirectional Data Flow (UDF)**.

---

## Anggota Kelompok dan Kontribusi

1. Anjalin Aida Faza2 (24523003)= Perancangan masalah & solusi, Model Data (`Movie`), dan `WatchListUiState` 
2. Annisa Ramadhani (24523018)= Implementasi `WatchListViewModel`, pengelolaan `StateFlow`, dan validasi input
3. Aulia Fakhirah Hendraini (24523020)= Implementasi UI Compose (`WatchListScreen`), Adaptive Layout, dan dokumentasi

---

## Masalah, Pengguna Sasaran, dan Solusi

- **Pengguna Sasaran:** Mahasiswa dan penikmat film/serial drama yang sering mendapat rekomendasi tontonan dari teman atau media sosial.
- **Masalah:** Pengguna sering lupa judul film atau drama yang ingin ditonton, atau lupa mana judul yang sudah selesai ditonton dan mana yang belum karena dicatat terpencar di aplikasi catatan biasa.
- **Solusi:** Aplikasi **WatchList** menyediakan satu layar utama yang ringkas dan responsif untuk mencatat judul tontonan, menandai status sudah/belum ditonton dengan satu ketukan, serta menghapus judul yang tidak lagi diperlukan.

---

## Fitur Inti

1. **Tambah Judul Tontonan (beserta Validasi Input):** Menambahkan judul film atau drama baru ke dalam daftar. Jika kolom input kosong saat tombol ditekan, aplikasi menampilkan pesan validasi error.
2. **Tandai Status Ditonton (`Sudah ditonton ✓` / `Belum ditonton`):** Mengubah status tontonan secara langsung untuk melacak progres tontonan.
3. **Hapus Judul Tontonan:** Menghapus item film atau drama dari daftar tontonan.
4. **Adaptive Layout:**
   - **Layar Sempit (`< 600.dp` / Portrait):** Susunan bertumpuk (*vertical column*) — form input di bagian atas dan daftar tontonan di bagian bawah.
   - **Layar Lebar (`>= 600.dp` / Landscape & Tablet):** Susunan berdampingan (*two-pane row*) — form input di sisi kiri dan daftar tontonan di sisi kanan tanpa konten terpotong.

---

## Contoh Alur UDF (Unidirectional Data Flow)

Alur Data Satu Arah (UDF) Saat Menambah Film Baru:

1. Event UI (Layar → ViewModel):
Pengguna mengetik judul film lalu menekan tombol "Tambah ke WatchList". Aksi ini memicu fungsi addMovie() di ViewModel.

2. Pemrosesan di ViewModel:
ViewModel memeriksa judul yang dimasukkan:
- Jika kosong: Menampilkan pesan error peringatan.
- Jika valid: Membuat data film baru, menambahkannya ke dalam daftar, mengosongkan kolom input teks, dan menghapus pesan error.

3. Pembaruan Tampilan UI (ViewModel → Layar):
Layar selalu memantau perubahan data. Begitu data baru masuk, Jetpack Compose otomatis memperbarui tampilan layar secara instan: kotak ketikan kembali kosong dan kartu film baru langsung muncul di daftar.

---

## Screenshot Dua Ukuran Layar

### 1. Layar Sempit (Portrait)
<img width="630" height="476" alt="portrait" src="https://github.com/user-attachments/assets/91f3d814-7b30-4532-8ece-a091746c1564" />


### 2. Layar Lebar (Landscape)
<img width="650" height="498" alt="landscape" src="https://github.com/user-attachments/assets/4eb4ed44-f26b-4765-bafd-1823cefb5693" />

---


## Cara Menjalankan Proyek

1. **Clone** repositori ini ke komputer lokal:
   ```bash
   git clone <url-repository-github-kelompok>
   ```
2. Buka **Android Studio**, pilih **Open**, lalu arahkan ke folder proyek **`WatchList`**.
3. Tunggu hingga proses **Gradle Sync** selesai.
4. Pilih perangkat **Emulator** (atau perangkat Android fisik) pada toolbar atas, lalu klik tombol **Run ▶ (`app`)**.

---
