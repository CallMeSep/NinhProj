package univer.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import univer.program.entity.RoomType;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {

}
