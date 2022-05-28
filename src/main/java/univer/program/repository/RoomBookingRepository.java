package univer.program.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import univer.program.entity.RoomBooking;
import univer.program.entity.UserRole;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
	List<RoomBooking> findByUserRole(UserRole userRole);
	@Query( value = "SELECT * FROM room_booking \n"
			+ "   WHERE MONTH(checked_in_date) = ?1 AND YEAR(checked_in_date) = ?2", nativeQuery = true)
	List<RoomBooking> findRoomBookingIn(int month ,int year);
	
	@Query(
  		  value = "Select room_booking.* from room_booking, hotel,room\n"
  		  		+ "where room.hotel_id = hotel_id and room_id= room_booking.room_id and hotel.id = ?1 group by room_booking.id", 
  		  nativeQuery = true)
  		List<RoomBooking> findByKhachSan(Long hotelId);

	
}
