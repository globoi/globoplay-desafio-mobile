package com.example.testeglobojeremias.adapters;

import static com.example.testeglobojeremias.utils.Keys.ytImageBaseURL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeglobojeremias.R;
import com.example.testeglobojeremias.models.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String videoID);
    }

    private final ArrayList<Video> videos;
    private final OnItemClickListener listener;

    public VideosAdapter(ArrayList<Video> videos, OnItemClickListener listener) {
        this.videos = videos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideosAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.VideoViewHolder holder, int position) {
        holder.bind(videos.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        private final ImageView videoImage;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImage = itemView.findViewById(R.id.video_list_image);
        }

        public void bind(final Video video, final OnItemClickListener listener){
            Picasso.get()
                    .load(ytImageBaseURL+"/"+video.getKey()+"/0.jpg")
                    .into(videoImage);
            itemView.setOnClickListener(view -> listener.onItemClick(video.getKey()));
        }
    }
}
