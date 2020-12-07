package application.tree_booking.services;

import application.tree_booking.entities.EventDB;
import application.tree_booking.entities.EventDBRepository;
import application.tree_booking.entities.UserDB;
import application.tree_booking.views.EventView;
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

	public List<EventView> getActiveEvents(UserDB userDBLogged){

		List<EventDB> eventDBList = StreamSupport
				.stream(eventDBRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return parseFromEventDBToEventView(eventDBList, userDBLogged);
	}


	private List<EventView> parseFromEventDBToEventView(List<EventDB> eventDBList, UserDB userDBLogged){
		List<EventView> eventViewList = new ArrayList<>();

		for (EventDB eventDB : eventDBList) {
			eventViewList.add(new EventView(eventDB.getEventdbUuid(),
					userDBLogged.equals(eventDB.getEventdbCreator()),
					eventDB.getEventdbName(),
					eventDB.getEventdbDate(),
					eventDB.getEventdbPlace(),
					eventDB.getEventdbCapacity()));
		}

		return eventViewList;
	}
}
