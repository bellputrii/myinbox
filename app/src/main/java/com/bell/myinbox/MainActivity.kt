package com.bell.myinbox

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bell.myinbox.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EmailAdapter
    private lateinit var emailList: List<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailList = listOf(
            DataClass("John Doe", "Meeting Reminder: Project Review", "Don't forget about our project review meeting at 10 AM.", "20 Oct"),
            DataClass("Admin Cafe", "Pemberitahuan: Sarapan Gratis!", "Sarapan gratis untuk semua karyawan akan dimulai dalam 30 menit.", "20 Oct"),
            DataClass("Cristiano Ronaldo", "GOAT Talks", "Aku adalah yang terbaik sepanjang masa. Fakta!", "19 Oct"),
            DataClass("Lionel Messi", "Clarification", "Hati-hati dengan klaim sepihak. Kemenangan butuh kerja tim.", "18 Oct"),
            DataClass("PT INDONESIA JAYA", "Penerimaan Kerja", "Selamat! Anda diterima untuk posisi yang Anda lamar.", "18 Oct"),
            DataClass("HR Department", "Interview Schedule", "Wawancara Anda dijadwalkan pada tanggal 25 Oktober pukul 14:00.", "17 Oct"),
            DataClass("Tech Updates", "New Features Released!", "Cek fitur terbaru di aplikasi versi terbaru kami.", "17 Oct"),
            DataClass("Finance Dept.", "Laporan Keuangan Bulanan", "Laporan keuangan bulan ini sudah tersedia. Silakan periksa di dashboard.", "16 Oct"),
            DataClass("Travel Agency", "Promo Liburan Akhir Tahun!", "Nikmati diskon hingga 50% untuk paket liburan keluarga.", "15 Oct"),
            DataClass("Security Alert", "Aktivitas Mencurigakan Terdeteksi", "Kami mendeteksi login yang tidak biasa di akun Anda.", "15 Oct"),
            DataClass("Admin Cafe", "Pemberitahuan: Sarapan Gratis!", "Sarapan gratis untuk semua karyawan akan dimulai dalam 30 menit.", "15 Oct"),
            DataClass("HR Department", "Interview Schedule", "Wawancara Anda dijadwalkan pada tanggal 25 Oktober pukul 14:00.", "14 Oct"),
            DataClass("Tech Updates", "New Features Released!", "Cek fitur terbaru di aplikasi versi terbaru kami.", "14 Oct"),
            DataClass("Finance Dept.", "Laporan Keuangan Bulanan", "Laporan keuangan bulan ini sudah tersedia. Silakan periksa di dashboard.", "13 Oct")
        )

        adapter = EmailAdapter(emailList)
        binding.recyclerViewEmails.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEmails.adapter = adapter

        // Search functionality
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterEmails(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Bottom navigation handling
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_email -> {

                    true
                }
                R.id.navigation_video -> {

                    true
                }
                else -> false
            }
        }
    }

    private fun filterEmails(query: String) {
        val filteredList = emailList.filter { it.subject.contains(query, ignoreCase = true) || it.sender.contains(query, ignoreCase = true) }
        adapter.updateList(filteredList)
    }
}