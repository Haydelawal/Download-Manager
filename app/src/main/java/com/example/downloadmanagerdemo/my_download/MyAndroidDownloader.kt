package com.example.downloadmanagerdemo.my_download

import android.app.DownloadManager
import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Environment
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import java.io.File

class MyAndroidDownloader(
    private val context: Context,
    private val title: String,
    private val main_path: String,
    private val sub_path: String
) : MyDownloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {

//        val fileName= getFileNameFromUri(url);


        val request = DownloadManager.Request(url.toUri())
            .setMimeType(parseMimeType(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(title)
            .addRequestHeader("Authorization", "Bearer <Token>")
            .setDestinationInExternalFilesDir(context, main_path, "$sub_path/$title")
//            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "myme/$title")

        return downloadManager.enqueue(request)

    }

    private fun parseMimeType(url: String): String {
        val file = File(url)
        val map = MimeTypeMap.getSingleton()
        val ext = MimeTypeMap.getFileExtensionFromUrl(file.name)
        var type = map.getMimeTypeFromExtension(ext)
        type = type ?: "*/*"
        return type
    }

//    private fun getFileNameFromUri(url: String): String {
//        return url.substring( url.lastIndexOf('/')+1, url.length);
//    }
}