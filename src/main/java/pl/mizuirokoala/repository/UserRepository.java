package pl.mizuirokoala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mizuirokoala.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);
}
