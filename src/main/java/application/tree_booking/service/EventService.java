package application.tree_booking.service;

import application.tree_booking.entities.EventDB;
import application.tree_booking.entities.EventDBRepository;
import application.tree_booking.view.EventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventService {
	@Autowired
	EventDBRepository eventDBRepository;

	public List<EventView> getActiveEvents(){

		List<EventDB> eventDBList = StreamSupport
				.stream(eventDBRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return parseFromEventDBToEventView(eventDBList);
	}


	private List<EventView> parseFromEventDBToEventView(List<EventDB> eventDBList){
		List<EventView> eventViewList = new ArrayList<>();

		for (EventDB eventDB : eventDBList) {
			eventViewList.add(new EventView(eventDB.getEventdbUuid(),
					false,
					eventDB.getEventdbName(),
					eventDB.getEventdbDate(),
					eventDB.getEventdbPlace(),
					eventDB.getEventdbCapacity()));
		}
		return eventViewList;
	}
}
