package hotel.service;

import java.util.List;
import java.util.Optional;

import hotel.entity.Hotel;
import hotel.entity.Room;

public interface RoomService {
    void add(Room room);
    List<Room> findByHotel(Hotel hotel);
    boolean deleteById(int id);
    void save(Room room);
    Optional<Room> findById(int id);
}
