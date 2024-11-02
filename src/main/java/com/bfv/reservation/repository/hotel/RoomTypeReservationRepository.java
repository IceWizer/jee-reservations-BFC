package com.bfv.reservation.repository.hotel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;

public interface RoomTypeReservationRepository extends JpaRepository<RoomTypeReservation, String> {

    @Query("SELECT rt.number - COUNT(r.roomType.id) FROM RoomTypeReservation r JOIN RoomType rt ON r.roomType.id = rt.id WHERE r.roomType.id = ?1 AND r.fromDate < ?3 AND r.toDate > ?2 AND r.state != \"cancelled\" GROUP BY r.roomType.id")
    public int getRoomTypeNumberAvailable(String roomTypeId, LocalDate fromDate, LocalDate toDate);

    public List<RoomTypeReservation> findByUser(User user);
}
