package com.example.downloadmanagerdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.downloadmanagerdemo.databinding.ActivityMainBinding
import com.example.downloadmanagerdemo.my_download.MyAndroidDownloader
import com.example.downloadmanagerdemo.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //  val videoDataList = myViewModel.myVideoData[0].media_url
        //  val videoDataTitle = myViewModel.myVideoData[4].title


        val main_path = "downloaded_media"
        val sub_path = "video"

        lifecycleScope.launchWhenCreated {
            myViewModel.myVideoData.forEach { myVideoData ->



                downloadVideos(
                    this@MainActivity,
                    myVideoData.title,
                    myVideoData.media_url,
                    main_path,
                    sub_path
                )
            }
        }


    }

    private fun downloadVideos(
        context: Context, videoTitle: String, videoUrl: String,
        main_path: String,
        sub_path: String
    ) {


        val downloader = MyAndroidDownloader(context, videoTitle, main_path, sub_path)
        downloader.downloadFile(videoUrl)
    }
}