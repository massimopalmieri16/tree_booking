package application.tree_booking.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventDBRepository extends CrudRepository<EventDB, Integer> {
	Optional<EventDB> findByEventdbUuid(UUID eventdbUuid);


}
