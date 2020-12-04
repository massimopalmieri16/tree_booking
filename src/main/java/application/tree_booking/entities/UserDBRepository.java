package application.tree_booking.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDBRepository extends CrudRepository<UserDB, Integer> {
	Optional<UserDB> findByUserdbUsername(String userdbUsername);
	Optional<UserDB> findByUserdbUsernameAndUserdbPassword(String UserdbUsername, String userdbPassword);

}
