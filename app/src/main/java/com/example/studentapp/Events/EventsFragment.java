package com.example.studentapp.Events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.ThirdFragment;
import com.example.studentapp.databinding.EventsFragmentBinding;
import com.example.studentapp.databinding.FragmentThirdBinding;
import com.example.studentapp.objects.Event;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EventsFragment extends Fragment{
    private EventsFragmentBinding binding;
    RecyclerView recyclerView1, recyclerView2;
    EventsUserAdapter adapter1, adapter2;
    ArrayList<Event> eventList1, eventList2;
    private EventsModel model;

    public EventsFragment() {
         model = new EventsModel(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        eventList1 = new ArrayList<>();
        eventList2 = new ArrayList<>();

        View view = inflater.inflate(R.layout.events_fragment, container, false);

        recyclerView1 = view.findViewById(R.id.event_list);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView2 = view.findViewById(R.id.event_list_2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter1 = new EventsUserAdapter(getContext(), eventList1, this);
        adapter2 = new EventsUserAdapter(getContext(), eventList2, this);

        model.getUserEvents(MainActivity.currUser).thenAccept(res -> {
            setupEventList(res, eventList1, recyclerView1);
        });

        model.getAllEvents().thenAccept(res -> {
            setupEventList(res, eventList2, recyclerView2);
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // set up arraylist of Event objects for the recyclerview from given
    // list of event ids.
    private void setupEventList(@NonNull ArrayList<String> eventIds, ArrayList<Event> eventList, RecyclerView rv) {
        for(String id : eventIds) {
            model.getEventFromId(id).thenAccept( res -> {
                eventList.add(res);
                rv.setAdapter(new EventsUserAdapter(getContext(), eventList, this));
            });
        }
    }

    public EventsModel getEventsModel() {
        return model;
    }

    public void sendToast(String msg) {
        Toast toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
