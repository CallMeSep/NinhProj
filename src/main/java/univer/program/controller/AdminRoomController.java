package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import univer.program.entity.Hotel;
import univer.program.entity.Room;
import univer.program.form.AddRoomForm;
import univer.program.repository.HotelRepository;
import univer.program.repository.RoomRepository;
import univer.program.repository.RoomTypeRepo;
//import univer.program.service.HotelService;
//import univer.program.validator.RoomValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/room")
public class AdminRoomController {

    @Autowired
    private RoomTypeRepo roomTypeRepo;
    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private HotelRepository hotelRepo;

    @GetMapping("")
    public String getRoomList(@RequestParam("hid") Long id , Model model) {
    	
        Optional<Hotel> hotel = hotelRepo.findById(id);

        if (!hotel.isPresent())
            return "redirect:/404";

        model.addAttribute("hid", id);
        model.addAttribute("rooms",roomRepo.findByIdandXoa(hotel.get().getId(), false));
        return "roomList";
    }

    @GetMapping("/edit")
    public String editRoom(@RequestParam("id") Long id, Model model) {

        Optional<Room> room = roomRepo.findById(id);
        if (!room.isPresent())
            return "redirect:/404";
        AddRoomForm addRoomForm = new AddRoomForm();
        addRoomForm.setHotel(room.get().getHotel());
        addRoomForm.setCapacity(room.get().getCapacity());
        addRoomForm.setPrice(room.get().getPrice());
        addRoomForm.setRoomType(room.get().getRoomType());
        addRoomForm.setRoomNum(room.get().getRoomNum());
        addRoomForm.setRoomType(room.get().getRoomType());
        addRoomForm.setRoomId(room.get().getId());
        model.addAttribute("addRoomForm", addRoomForm);
        model.addAttribute("room", room);
        return "room_edit";
    }

    @GetMapping("/add")
    public String addRoomFrm(@RequestParam("hid") Long id, Model model) {
        Room room = new Room();
        AddRoomForm addRoomForm = new AddRoomForm();
        model.addAttribute("hid", id);
//        model.addAttribute("room", room);
        model.addAttribute("room", addRoomForm);
        model.addAttribute("roomTypes", roomTypeRepo.findAll());
        return "room_add";
    }

    @PostMapping(value={"/saveedit"})
    public RedirectView editRoom(@ModelAttribute("addRoomForm") AddRoomForm room, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {
    	Room room1 = new Room();

    	room1.setPrice(room.getPrice());
    	room1.setRoomType(room.getRoomType());
    	room1.setRoomNum(room.getRoomNum());
    	room1.setCapacity(room.getCapacity());
    	room1.setId(room.getRoomId());
    	room1.setXoa(false);

        roomRepo.save(room1);
//        redirectAttributes.addAttribute("hid", room1.getHotel().getId());
        return new RedirectView("/admin/room?hid=" + room1.getHotel().getId());
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteRoom(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        
        Optional<Room> room = roomRepo.findById(id);
        if (!room.isPresent())
            return new RedirectView("/404");
        Room newRoom = room.get();
        newRoom.setXoa(true);
        roomRepo.save(newRoom);
        Long hid = room.get().getHotel().getId();
        redirectAttributes.addAttribute("hid", hid);
        return new RedirectView("/admin/room");
    }

    @PostMapping("/save/{id}")
    public RedirectView addRoom(@ModelAttribute("room") AddRoomForm room, BindingResult bindingResult,
                                @PathVariable("id") Long hid, RedirectAttributes redirectAttributes) {
    	Room room1 = new Room();
    	room1.setRoomNum(room.getRoomNum());
    	room1.setCapacity(room.getCapacity());
    	room1.setPrice(room.getPrice());
    	room1.setRoomType(room.getRoomType());
        room1.setHotel(hotelRepo.findById(hid).get());

        roomRepo.save(room1);
        redirectAttributes.addAttribute("hid", hid);
        return new RedirectView("/admin/room");
    }
}
