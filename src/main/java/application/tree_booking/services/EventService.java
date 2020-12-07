package application.tree_booking.services;

import application.tree_booking.entities.EventDB;
import application.tree_booking.entities.EventDBRepository;
import application.tree_booking.entities.UserDB;
import application.tree_booking.views.EventInputView;
import application.tree_booking.views.EventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

		// tolgo eventi in cui l'utente è già registrato
		eventDBList =  eventDBList.stream()
				.filter(e -> !e.getEventdbParticipants().contains(userDBLogged))
				.collect(Collectors.toList());

		return parseListFromEventDBToEventView(eventDBList, userDBLogged);
	}

	public List<EventView> getUserEvents(UserDB userDBLogged){
		List<EventDB> eventDBList = StreamSupport
				.stream(eventDBRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		// tolgo eventi in cui l'utente è già registrato e quelli passati
		eventDBList =  eventDBList.stream()
				.filter(e -> e.getEventdbDate().isAfter(LocalDateTime.now()))
				.filter(e -> e.getEventdbParticipants().contains(userDBLogged))
				.collect(Collectors.toList());

		return parseListFromEventDBToEventView(eventDBList, userDBLogged);
	}

	public EventView getEventDetails(UUID idEvent, UserDB userDBLogged){
		Optional<EventDB> optionalEventDB = eventDBRepository.findByEventdbUuid(idEvent);
		if(optionalEventDB.isPresent()){
			return parseFromEventDBToEventView(optionalEventDB.get(), userDBLogged);
		}else {
			// se nessun evento presente con id in input ritorno null
			return null;
		}
	}

	public EventView cancelEvent(UUID idEvent, UserDB userDBLogged){
		Optional<EventDB> optionalEventDB = eventDBRepository.findByEventdbUuid(idEvent);
		if(optionalEventDB.isPresent()){
			// controllo se utente loggato è il creatore dell'evento
			if(userDBLogged.equals(optionalEventDB.get().getEventdbCreator())) {
				eventDBRepository.delete(optionalEventDB.get());
				return parseFromEventDBToEventView(optionalEventDB.get(), userDBLogged);
			}else{
				// se utente loggato non è il creatore ritorno null
				return null;
			}
		}else {
			// se nessun evento presente con id in input ritorno null
			return null;
		}
	}

	// Metodo per creare un evento
	public EventView createEvent(EventInputView eventInputView, UserDB userDBLogged){
		if(eventInputView.getDate().isBefore(LocalDateTime.now())){
			return null;	// se la data passata in input è passata
		}

		EventDB eventDB = new EventDB(
				eventInputView.getName(),
				eventInputView.getDate(),
				eventInputView.getPlace(),
				eventInputView.getCapacity(),
				userDBLogged);
		eventDBRepository.save(eventDB);	// salvo evento nel database

		// join automatico dell'evento
		joinEvent(eventDB.getEventdbUuid(), userDBLogged);

		return parseFromEventDBToEventView(eventDB, userDBLogged);
	}

	public EventView joinEvent(UUID idEvent, UserDB userDBLogged) {
		Optional<EventDB> optionalEventDB = eventDBRepository.findByEventdbUuid(idEvent);
		if(optionalEventDB.isPresent()){
			if(optionalEventDB.get().addPartecipants(userDBLogged)){
				eventDBRepository.save(optionalEventDB.get());
				return parseFromEventDBToEventView(optionalEventDB.get(), userDBLogged);
			}else {
				return null;
			}
		}else {
			// se nessun evento presente con id in input ritorno null
			return null;
		}
	}

	public EventView unjoinEvent(UUID idEvent, UserDB userDBLogged) {
		Optional<EventDB> optionalEventDB = eventDBRepository.findByEventdbUuid(idEvent);
		if(optionalEventDB.isPresent()){
			optionalEventDB.get().removePartecipants(userDBLogged);
			eventDBRepository.save(optionalEventDB.get());
			return parseFromEventDBToEventView(optionalEventDB.get(), userDBLogged);
		}else {
			// se nessun evento presente con id in input ritorno null
			return null;
		}
	}

	private List<EventView> parseListFromEventDBToEventView(List<EventDB> eventDBList, UserDB userDBLogged){
		List<EventView> eventViewList = new ArrayList<>();

		for (EventDB eventDB : eventDBList) {
			eventViewList.add(parseFromEventDBToEventView(eventDB, userDBLogged));
		}

		return eventViewList;
	}

	private EventView parseFromEventDBToEventView(EventDB eventDB, UserDB userDBLogged){
		return new EventView(eventDB.getEventdbUuid(),
				userDBLogged.equals(eventDB.getEventdbCreator()),
				eventDB.getEventdbParticipants().contains(userDBLogged),
				eventDB.getEventdbName(),
				eventDB.getEventdbDate(),
				eventDB.getEventdbPlace(),
				eventDB.getEventdbCapacity());
	}
}
