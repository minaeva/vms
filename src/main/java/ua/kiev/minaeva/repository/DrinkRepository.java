package ua.kiev.minaeva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.minaeva.entity.Drink;
import ua.kiev.minaeva.entity.Machine;

import java.util.List;
import java.util.Optional;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

    Optional<List<Drink>> findByTitle(String title);

    Optional<List<Drink>> findByMachine(Machine machine);

    Optional<List<Drink>> findByTitleAndMachine(String title, Machine machine);

}

