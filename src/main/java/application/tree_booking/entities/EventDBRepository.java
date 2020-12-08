package application.tree_booking.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventDBRepository extends CrudRepository<EventDB, Integer> {
	Optional<EventDB> findByEventdbUuid(UUID eventdbUuid);

	@Query(value = "SELECT e.eventdb_pk,e.eventdb_capacity,e.eventdb_date,e.eventdb_date,e.eventdb_name,e.eventdb_place,e.eventdb_uuid,e.eventdb_creator_userdb_pk\n" +
			"FROM eventdb AS e\n" +
			"INNER JOIN eventdb_eventdb_participants AS ep ON ep.userdb_events_joined_eventdb_pk = e.eventdb_pk\n" +
			"WHERE ep.eventdb_participants_userdb_pk = :userPK", nativeQuery = true)
	List<EventDB> findUserEvents(@Param("userPK") int userPK);
}
