package hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hotel.entity.RoomType;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {

}
