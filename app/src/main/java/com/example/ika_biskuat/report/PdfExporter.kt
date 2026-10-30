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
        val pageInfo = PdfDocument.PageInfo.Builder(592, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        val titlePaint = Paint().apply {
            color = Color.BLACK
            textSize = 18f
            isBoldText = true
        }
        val headerPaint = Paint().apply {
            color = Color.DKGRAY
            textSize = 12f
            isBoldText = true
        }
        val rowPaint = Paint().apply {
            color = Color.BLACK
            textSize = 11f
        }
        val linePaint = Paint().apply {
            color = Color.GRAY
            strokeWidth = 1f
        }

        var startY = 50f
        val startX = 40f

        canvas.drawText(title, startX, startY, titlePaint)
        startY += 30f

        var startXCol = startX
        val colWidths = floatArrayOf(100f, 110f, 180f, 110f)
        for (i in headers.indices) {
            canvas.drawText(headers[i], startXCol, startY, headerPaint)
            startXCol += colWidths[i]
        }

        startY += 10f
        canvas.drawLine(startX, startY, startX + 500f, startY, linePaint)
        startY += 20f

        for (row in dataRows) {
            startXCol = startX
            for (i in row.indices) {
                canvas.drawText(row[i], startXCol, startY, rowPaint)
                startXCol += colWidths[i]
            }
            startY += 25f
        }

        pdfDocument.finishPage(page)

        try {
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
            putExtra(Intent.EXTRA_SUBJECT, "Laporan PDF SEMAWIT")
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(intent, "Bagikan Laporan PDF via"))
    }
}