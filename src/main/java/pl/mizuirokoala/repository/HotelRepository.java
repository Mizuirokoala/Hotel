package pl.mizuirokoala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mizuirokoala.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	Hotel findOneByEmail(String email);
	List<Hotel> findAllByAddressCity(String addressCity);
	Hotel findOneByAddressCity(String source);
}
