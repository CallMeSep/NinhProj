package univer.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import univer.program.entity.Hotel;
import univer.program.entity.Room;

import java.sql.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByHotel(Hotel hotel);
    List<Room> findByXoa(boolean xoa);
    @Query(
  		  value = "SELECT * FROM room r WHERE r.hotel_id = ?1 AND r.xoa = ?2", 
  		  nativeQuery = true)
  		List<Room> findByIdandXoa(Long hotelId, boolean xoa);
    @Query(
    		  value = "SELECT * FROM room r WHERE r.hotel_id = ?1 AND r.xoa = ?2", 
    		  nativeQuery = true)
    		List<Room> findByIdandXoaKhachSan(Long hotelId, boolean xoa);
    @Query( value = "select * from room where room.id not in (select room_booking.room_id from room_booking"
    		+ "where ((room_booking.checked_out_date >=  ?1"
    		+ "and room_booking.checked_in_date <= ?1) OR (room_booking.checked_in_date >= ?2"
    		+ "and room_booking.checked_out_date <= ?2)))", nativeQuery = true)
	List<Room> findEmptyRoomByCheckedInDate(Date checked_in_date , Date checked_out_date);
}
