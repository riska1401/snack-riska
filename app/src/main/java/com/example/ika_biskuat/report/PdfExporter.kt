package com.example.ika_biskuat.report

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object PdfExporter {

    fun exportToPdf(context: Context, fileName: String, title: String, headers: List<String>, dataRows: List<List<String>>) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(592, 842, 1).create() // Ukuran Standar A4
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        // --- PALET WARNA PROFESIONAL KUD SEMAWIT ---
        val primaryGreen = Color.rgb(12, 98, 55)   // Hijau Utama SEMAWIT
        val goldAccent = Color.rgb(184, 134, 11)   // Warna Emas Sawit
        val darkText = Color.rgb(30, 30, 30)       // Hitam Gelap untuk Teks
        val grayText = Color.rgb(100, 100, 100)    // Abu-abu untuk Subtitle
        val tableHeaderBg = Color.rgb(235, 245, 238) // Hijau Sangat Muda untuk Header Tabel

        // Deklarasi Objek Cat (Paint)
        val logoBoxPaint = Paint().apply {
            color = primaryGreen
        }
        val logoTextPaint = Paint().apply {
            color = Color.WHITE
            textSize = 14f
            isFakeBoldText = true
            textAlign = Paint.Align.CENTER
        }
        val brandPaint = Paint().apply {
            color = primaryGreen
            textSize = 13f
            isFakeBoldText = true
        }
        val subBrandPaint = Paint().apply {
            color = grayText
            textSize = 9f
        }
        val titlePaint = Paint().apply {
            color = darkText
            textSize = 15f
            isFakeBoldText = true
        }
        val headerBgPaint = Paint().apply { color = tableHeaderBg }
        val headerTextPaint = Paint().apply {
            color = primaryGreen
            textSize = 10f
            isFakeBoldText = true
        }
        val rowPaint = Paint().apply {
            color = darkText
            textSize = 10f
        }
        val linePaint = Paint().apply {
            color = Color.LTGRAY
            strokeWidth = 1f
        }
        val thickLinePaint = Paint().apply {
            color = primaryGreen
            strokeWidth = 2.5f
        }
        val goldLinePaint = Paint().apply {
            color = goldAccent
            strokeWidth = 1.5f
        }

        var startY = 40f
        val startX = 40f
        val pageWidth = 512f // Lebar area efektif (592 - 80 margin kiri/kanan)

        // --- 1. KOTAK LOGO KUD DI KOP SURAT ---
        val logoBoxSize = 40f
        canvas.drawRect(startX, startY, startX + logoBoxSize, startY + logoBoxSize, logoBoxPaint)
        // Huruf inisial "KUD" di dalam kotak logo
        canvas.drawText("KUD", startX + (logoBoxSize / 2f), startY + 25f, logoTextPaint)

        // Teks Kop Surat di Sebelah Kanan Logo
        val textStartX = startX + logoBoxSize + 12f
        canvas.drawText("KUD SAWIT MAKMUR SEJAHTERA (SEMAWIT)", textStartX, startY + 14f, brandPaint)
        canvas.drawText("Pusat Pengelolaan Data & Keuangan Sawit Rakyat Berbasis Digital", textStartX, startY + 28f, subBrandPaint)
        canvas.drawText("Kawasan Agribisnis Rumbai, Pekanbaru - Riau", textStartX, startY + 40f, subBrandPaint)

        startY += 52f

        // Garis Kop Surat Ganda (Hijau & Emas)
        canvas.drawLine(startX, startY, startX + pageWidth, startY, thickLinePaint)
        startY += 3f
        canvas.drawLine(startX, startY, startX + pageWidth, startY, goldLinePaint)
        startY += 25f

        // --- 2. JUDUL LAPORAN ---
        canvas.drawText(title, startX, startY, titlePaint)
        startY += 22f

        // --- 3. TABEL DATA LAPORAN ---
        val colWidths = floatArrayOf(90f, 110f, 202f, 110f) // Total lebar kolom = 512f
        val tableRowHeight = 22f

        // Latar Belakang Header Tabel
        canvas.drawRect(startX, startY, startX + pageWidth, startY + tableRowHeight, headerBgPaint)

        // Teks Header Tabel
        var startXCol = startX + 8f
        for (i in headers.indices) {
            canvas.drawText(headers[i], startXCol, startY + 15f, headerTextPaint)
            startXCol += colWidths[i]
        }
        startY += tableRowHeight
        canvas.drawLine(startX, startY, startX + pageWidth, startY, thickLinePaint)

        // Baris Data Tabel
        for (row in dataRows) {
            startY += tableRowHeight
            startXCol = startX + 8f
            for (i in row.indices) {
                canvas.drawText(row[i], startXCol, startY - 5f, rowPaint)
                startXCol += colWidths[i]
            }
            // Garis tipis pembatas antar baris
            canvas.drawLine(startX, startY + 3f, startX + pageWidth, startY + 3f, linePaint)
        }

        // --- 4. BLOK TANDA TANGAN & PENGESAHAN ---
        startY += 65f
        val signatureX = 330f // Posisi blok tanda tangan di sisi kanan bawah

        canvas.drawText("Pekanbaru, 30 Oktober 2026", signatureX, startY, rowPaint)
        startY += 15f
        canvas.drawText("Disahkan Oleh,", signatureX, startY, rowPaint)
        startY += 15f
        canvas.drawText("Pengurus KUD SEMAWIT", signatureX, startY, headerTextPaint)

        // Ruang kosong untuk tanda tangan fisik / stempel
        startY += 50f

        // Nama Pejabat / Pengurus
        canvas.drawText("( Bpk. H. Ahmad Fauzi, S.E. )", signatureX - 5f, startY, rowPaint)
        startY += 14f
        canvas.drawText("Manajer Operasional & Kemitraan", signatureX - 8f, startY, subBrandPaint)

        pdfDocument.finishPage(page)

        try {
            // Menyimpan file PDF ke dalam folder cache aplikasi
            val exportDir = File(context.cacheDir, "exports")
            if (!exportDir.exists()) exportDir.mkdirs()

            val file = File(exportDir, "$fileName.pdf")
            val fos = FileOutputStream(file)
            pdfDocument.writeTo(fos)
            pdfDocument.close()
            fos.close()

            sharePdfFile(context, file)
        } catch (e: Exception) {
            e.printStackTrace()
            pdfDocument.close()
        }
    }

    private fun sharePdfFile(context: Context, file: File) {
        val contentUri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_SUBJECT, "Laporan Resmi KUD SEMAWIT")
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(intent, "Bagikan Laporan PDF via"))
    }
}