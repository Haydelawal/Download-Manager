package com.example.downloadmanagerdemo.my_download

interface MyDownloader {
    fun downloadFile(url: String): Long
}