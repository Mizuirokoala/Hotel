package pl.mizuirokoala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mizuirokoala.entity.Month;

public interface MonthRepository extends JpaRepository<Month, Long> {

}
