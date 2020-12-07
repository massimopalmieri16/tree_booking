package application.tree_booking.entities;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface CookieDBRepository extends CrudRepository<CookieDB, Integer> {
	Optional<CookieDB> findByCookiedbUuid(UUID cookiedbUuid);

	@Query(value = "SELECT * FROM cookiedb WHERE cookiedb_uuid = :cookiedb_uuid AND cookiedb_expiration > localtimestamp LIMIT 1;", nativeQuery = true)
	Optional<CookieDB> findByCookiedbUuidExp(@Param("cookiedb_uuid") UUID cookiedbUuid);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM cookiedb WHERE localtimestamp - cookiedb_expiration > INTERVAL '1' HOUR;", nativeQuery = true)
	void wipeCookie();
}
