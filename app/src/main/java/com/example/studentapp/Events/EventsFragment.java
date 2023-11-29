package com.example.studentapp.Events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    RecyclerView recyclerView;
    EventsUserAdapter adapter;
    ArrayList<Event> eventList;
    EventsModel model;

    public EventsFragment() {
         model = new EventsModel();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        eventList = new ArrayList<>();

        View view = inflater.inflate(R.layout.events_fragment, container, false);
        recyclerView = view.findViewById(R.id.event_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new EventsUserAdapter(getContext(), eventList, getActivity().getSupportFragmentManager());

        model.getUserEvents(MainActivity.currUser).thenAccept(res -> {
            try {
                setupEventList(res);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
    private void setupEventList(@NonNull ArrayList<String> eventIds) throws ExecutionException, InterruptedException {
        for(String id : eventIds) {
            Log.w("eventTest", "id: " + id);
            model.getEventFromId(id).thenAccept( res -> {
                eventList.add(res);
            });
        }
        recyclerView.setAdapter(adapter);
    }
}
