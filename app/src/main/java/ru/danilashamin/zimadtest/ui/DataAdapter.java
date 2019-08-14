package ru.danilashamin.zimadtest.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.danilashamin.zimadtest.R;
import ru.danilashamin.zimadtest.model.Response;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    @NonNull
    private final DataAdapterListener listener;

    @Nullable
    private List<Response.Data> data;

    public DataAdapter(@NonNull DataAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        if (data == null) {
            return;
        }
        Response.Data dataElement = data.get(position);
        holder.bind(dataElement, listener);
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void setData(List<Response.Data> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPhoto)
        AppCompatImageView ivPhoto;
        @BindView(R.id.tvNumber)
        AppCompatTextView tvNumber;
        @BindView(R.id.tvText)
        AppCompatTextView tvText;
        @BindView(R.id.llDataElement)
        LinearLayout llDataElement;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Response.Data dataElement, DataAdapterListener listener) {
            int number = getAdapterPosition() + 1;
            tvNumber.setText(String.valueOf(number));
            tvText.setText(dataElement.getTitle());

            Glide.with(ivPhoto)
                    .load(dataElement.getPhotoUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPhoto);

            dataElement.setNumber(number);

            llDataElement.setOnClickListener(v -> listener.onDataElementClicked(dataElement));
        }
    }

    public interface DataAdapterListener {
        void onDataElementClicked(Response.Data data);
    }
}
