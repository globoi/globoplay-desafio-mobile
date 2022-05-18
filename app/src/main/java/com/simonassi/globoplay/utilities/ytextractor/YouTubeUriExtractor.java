package com.simonassi.globoplay.utilities.ytextractor;

import android.content.Context;
import android.util.SparseArray;

/**
 * This class was extracted from the android-youtubeExtractor lib
 * to meet the video play requirements of this project.
 */
@Deprecated
public abstract class YouTubeUriExtractor extends YouTubeExtractor {

    public YouTubeUriExtractor(Context con) {
        super(con);
    }

    @Override
    protected void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta videoMeta) {
        onUrisAvailable(videoMeta.getVideoId(), videoMeta.getTitle(), ytFiles);
    }

    public abstract void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles);
}
