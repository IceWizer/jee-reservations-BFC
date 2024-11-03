package com.bfv.reservation.repository.flight;

import com.bfv.reservation.model.domain.flight.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository utile que pour l'importation des données
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
}
