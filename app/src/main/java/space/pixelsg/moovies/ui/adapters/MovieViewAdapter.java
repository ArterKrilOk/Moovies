package space.pixelsg.moovies.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import space.pixelsg.moovies.R;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.databinding.MovieListItemBinding;

public class MovieViewAdapter extends PagedListAdapter<Movie, MovieViewAdapter.MovieViewHolder> {
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final MovieListItemBinding binding;
        private final OnMovieItemClickListener onMovieItemClickListener;

        public MovieViewHolder(MovieListItemBinding binding, OnMovieItemClickListener onMovieItemClickListener) {
            super(binding.getRoot());

            this.binding = binding;
            this.onMovieItemClickListener = onMovieItemClickListener;
        }

        public void setMovie(Movie movie) {
            binding.textView.setText(movie.title);
            binding.posterView.loadImage(movie.posterPath);

            binding.layout.setOnClickListener(v -> onMovieItemClickListener.onClick(v, movie));
        }
    }

    private final OnMovieItemClickListener onMovieItemClickListener;

    public MovieViewAdapter(OnMovieItemClickListener onMovieItemClickListener) {
        super(new DiffUtil.ItemCallback<Movie>() {
            @Override
            public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.onMovieItemClickListener = onMovieItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(binding, onMovieItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.setMovie(Objects.requireNonNull(getItem(position)));
    }

    public interface OnMovieItemClickListener {
        void onClick(View v, Movie movie);
    }
}
