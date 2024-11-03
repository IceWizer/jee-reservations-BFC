package com.bfv.reservation.repository.hotel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.model.domain.hotel.RoomTypeReservation;

public interface RoomTypeReservationRepository extends JpaRepository<RoomTypeReservation, String> {

    @Query("SELECT COALESCE(rt.number - COUNT(r), rt.number) FROM RoomType rt LEFT OUTER JOIN RoomTypeReservation r ON r.roomType.id = rt.id AND r.fromDate < ?3 AND r.toDate > ?2 AND r.state != 'cancelled' WHERE rt.id = ?1 GROUP BY rt.id")
    public int getRoomTypeNumberAvailable(String roomTypeId, LocalDate fromDate, LocalDate toDate);

    public List<RoomTypeReservation> findByUser(User user);
}
