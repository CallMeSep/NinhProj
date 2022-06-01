package hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hotel.entity.Hotel;

import java.util.Collection;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	@Query("SELECT h FROM Hotel h WHERE h.city = ?1 ORDER BY h.id DESC")
    List<Hotel> findByCity(String city);
    @Query("SELECT h FROM Hotel h WHERE  h.xoa = ?1")
    List<Hotel> findAllAndXoa(boolean xoa);
}
