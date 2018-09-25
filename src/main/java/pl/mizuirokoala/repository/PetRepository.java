package pl.mizuirokoala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mizuirokoala.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	List<Pet> findByUserId(long id);

}
