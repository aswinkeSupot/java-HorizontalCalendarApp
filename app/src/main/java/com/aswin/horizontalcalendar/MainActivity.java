package com.aswin.horizontalcalendar;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aswin.horizontalcalendar.adapter.DatesAdapter;
import com.aswin.horizontalcalendar.databinding.ActivityMainBinding;
import com.aswin.horizontalcalendar.utils.LMTBaseActivity;
import com.aswin.horizontalcalendar.utils.utilities.CommonUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends LMTBaseActivity {

    private ActivityMainBinding binding;

    private int selectedDatePos = 0;
    private String selectedDate = "";
    List<Calendar> calendars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        selectedDate = "2024-12-15"; // Eg:- 2024-10-17

        setCalendarAdapter();
    }

    private void setCalendarAdapter() {
        List<String> dateList = new ArrayList<>();
        dateList.addAll(getDates(0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.dateRecyclerView.setLayoutManager(linearLayoutManager);
        DatesAdapter datesAdapter = new DatesAdapter(getApplicationContext(), dateList, selectedDatePos, new DatesAdapter.RecyclerItemClickListener() {
            @Override
            public void onItemClick(int datePos) {
                selectedDate = calendars.get(datePos).get(Calendar.YEAR) + "-" + formatedMonthOrDate(calendars.get(datePos).get(Calendar.MONTH) + 1) + "-" + formatedMonthOrDate(calendars.get(datePos).get(Calendar.DATE));

                // Do something while Clicking on date.
            }
        });
        binding.dateRecyclerView.setAdapter(datesAdapter);

        // Scroll to the selected date position after the adapter is set
        binding.dateRecyclerView.post(() -> linearLayoutManager.scrollToPositionWithOffset(selectedDatePos, 0));

        binding.dateRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisiblePosition != RecyclerView.NO_POSITION) {
                    int month = calendars.get(firstVisiblePosition).get(Calendar.MONTH) + 1;
                    binding.tvMonth.setText(CommonUtils.getEnglishMonthShort(String.valueOf(month)).toUpperCase());
                }
            }
        });

    }

    private List<String> getDates(int pos) {
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(Calendar.YEAR, Integer.parseInt(selectedDate.split("-")[0]));
        selectedCalendar.set(Calendar.MONTH, Integer.parseInt(selectedDate.split("-")[1]) -1 );
        selectedCalendar.set(Calendar.DATE, Integer.parseInt(selectedDate.split("-")[2]));

        for (int i = 0; i < 120; i++) {
            dates.add(calendar.get(Calendar.DATE) + ", " + CommonUtils.getWeekDay(calendar.get(Calendar.DAY_OF_WEEK)));
            if (calendar.get(Calendar.YEAR) == selectedCalendar.get(Calendar.YEAR) &&
                    calendar.get(Calendar.MONTH) == selectedCalendar.get(Calendar.MONTH) &&
                    calendar.get(Calendar.DATE) == selectedCalendar.get(Calendar.DATE)) {
                selectedDatePos = i;
            }
            Calendar tempCal = Calendar.getInstance();
            tempCal.add(Calendar.DATE, i);
            calendars.add(tempCal);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

}