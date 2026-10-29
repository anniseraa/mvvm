
# WatchList 

Aplikasi pencatat daftar tontonan (film dan drama) sederhana berbasis **Jetpack Compose** yang menerapkan **Adaptive Layout**, arsitektur **MVVM**, **UI State**, **StateFlow**, dan **Unidirectional Data Flow (UDF)**.

---

## Anggota Kelompok dan Kontribusi

1. Anjalin Aida Faza (24523003)= Perancangan masalah & solusi, Model Data (`Movie`), dan `WatchListUiState` 
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


1. Event UI: Saat pengguna menekan tombol 'Tambah ke WatchList', layar memicu fungsi addMovie() di ViewModel.

2. ViewModel: ViewModel memeriksa input tersebut. Karena judul valid, ViewModel membuat objek Movie baru.

3. Perubahan UiState: ViewModel memperbarui WatchListUiState dengan memasukkan film baru ke list movies dan mengosongkan kembali titleInput.

4. Pembaruan UI: Layar yang memantau uiState via collectAsStateWithLifecycle() langsung melakukan recomposition secara otomatis—kotak input kembali bersih dan kartu film baru seketika muncul di daftar tontonan tanpa perlu refresh manual."

---

## Screenshot Dua Ukuran Layar

### 1. Layar Sempit (Portrait)
<img width="1080" height="2400" alt="Screenshot_20261029_114454" src="https://github.com/user-attachments/assets/c2b7509a-755f-47bd-97aa-c79a42471c92" />

### 2. Layar Lebar (Landscape)
<img width="2400" height="1080" alt="Screenshot_20261029_114510" src="https://github.com/user-attachments/assets/56b582e9-1f28-4964-a974-8cb7b928e3ed" />

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
