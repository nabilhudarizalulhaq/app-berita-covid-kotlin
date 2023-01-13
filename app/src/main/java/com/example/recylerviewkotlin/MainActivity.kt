package com.example.recylerviewkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<News>
    private lateinit var tempArrayList : ArrayList<News>
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var news : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        imageId = arrayOf(
                R.drawable.ppkm_darurat_diperpanjang,
                R.drawable.luhut_ungkap_faktor,
                R.drawable.sebaran1266,
                R.drawable.update_covid19,
                R.drawable.orang_yang_sudah,
                R.drawable.kasus_kematian,
                R.drawable.orang_meninggal_covid,
                R.drawable.sering_terasa,
                R.drawable.kondisi_terkini,
                R.drawable.update_corona_juli
        )

        heading = arrayOf(
                "PPKM Level 4 Berakhir Hari Ini, Diperpanjang Lagi atau Dilonggarkan? Ini Data Kasus Covid-19 Sepekan",
                "Luhut Ungkap Faktor Penyebab Kematian Pasien Covid-19 Meningkat",
                "Sebaran 1.266 Kasus Kematian Corona 25 Juli 2021: Jawa Timur Sumbang Angka Tertinggi",
                "Update Covid-19 Indonesia: 10 Provinsi dengan Kasus Harian Tertinggi",
                "Orang yang Sudah Divaksin Sumbang Kasus Covid-19 Singapura, Ini Alasannya",
                "Kasus Kematian Covid Masih Tinggi, Jateng-Jatim Mendominasi",
                "1.266 Orang Meninggal Akibat Covid-19 Hari Ini, Jawa Timur Terbanyak",
                "Sering Terasa Sesak, Begini Kondisi Paru-Paru Ketika Terkena Covid-19",
                "Kondisi Terkini Pasien Covid-19 yang Dianiaya Warga, Dirawat di RS dan Mendapat Penanganan Khusus",
                "Update Corona 25 Juli: Angka Kesembuhan di Indonesia Meningkat | Varian Delta Menyebar di AS"
        )

        news = arrayOf(

                getString(R.string.news_a),
                getString(R.string.news_b),
                getString(R.string.news_c),
                getString(R.string.news_d),
                getString(R.string.news_e),
                getString(R.string.news_f),
                getString(R.string.news_g),
                getString(R.string.news_h),
                getString(R.string.news_i),
                getString(R.string.news_j)
        )

        newRecylerview =findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)

        newArrayList = arrayListOf<News>()
        tempArrayList = arrayListOf<News>()
        getUserdata()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            setMode(item.itemId)
            return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about -> {
                val aboutactivity = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutactivity)
            }
        }
    }

    private fun getUserdata() {
        for (i in imageId.indices) {
            val news = News(imageId[i], heading[i])
            newArrayList.add(news)
        }
        tempArrayList.addAll(newArrayList)
        val adapter = MyAdapter(tempArrayList)
        newRecylerview.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
               Toast.makeText(this@MainActivity,"Anda Mengklik item no. $position",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity,NewsActivity::class.java)
                intent.putExtra("heading",newArrayList[position].heading)
                intent.putExtra("imageId",newArrayList[position].titleImage)
                intent.putExtra("news",news[position])
                startActivity(intent)
            }
        })

    }

}