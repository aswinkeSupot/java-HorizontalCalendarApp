package com.aswin.horizontalcalendar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aswin.horizontalcalendar.R;
import java.util.List;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.AdapterViewHolder> {

    private Context context;
    private List<String> dates;
    private int selectedDatePos;
    private RecyclerItemClickListener mListener;

    public DatesAdapter(Context context, List<String> dates, int selectedDatePos, RecyclerItemClickListener mListener) {
        this.context = context;
        this.dates = dates;
        this.selectedDatePos = selectedDatePos;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_dates, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvDate.setText(dates.get(position));
        if(selectedDatePos==position){
            holder.llMain.setBackgroundColor(context.getResources().getColor(R.color.grayDark));
        }else{
            holder.llMain.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLite));
        }
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDatePos=position;
                mListener.onItemClick(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        LinearLayout llMain;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            llMain = itemView.findViewById(R.id.llMain);
        }
    }

    public interface RecyclerItemClickListener {
        void onItemClick(int datePos);
    }

}
