package com.simonassi.globoplay.utilities;

import android.content.Context;
import android.util.SparseArray;

import androidx.lifecycle.MutableLiveData;

import com.simonassi.globoplay.utilities.ytextractor.VideoMeta;
import com.simonassi.globoplay.utilities.ytextractor.YouTubeExtractor;
import com.simonassi.globoplay.utilities.ytextractor.YtFile;

public class WatchUrlExtractor {

    public static void getLink(Context context, String key, UrlExtractorCallback callback) {
        if (key == null || key.isEmpty()) {
            callback.onGetUrl("");
            return;
        }

        String youtubeLink = "http://youtube.com/watch?v=" + key;

        try{
            new YouTubeExtractor(context) {
                @Override
                public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                    if (ytFiles != null) {
                        int itag = 22;
                        String downloadUrl = ytFiles.get(itag).getUrl();
                        callback.onGetUrl(downloadUrl);
                    }
                }
            }.extract(youtubeLink);
        }catch (Exception e){
            callback.onGetUrl("");
        }
    }

}
