package hotel.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotel.entity.Room;
import hotel.entity.RoomBooking;
import hotel.entity.UserRole;
import hotel.repository.RoomBookingRepository;
import hotel.repository.RoomRepository;
import hotel.repository.UserRoleRepository;


@Controller
public class BookingController {
    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RoomBookingRepository roomBookingRepo;
    @Autowired
    private UserRoleRepository userRoleRepo;
	@GetMapping(value={"/index","/",""})
    public String welcome(Model model) {
    	model.addAttribute("rooms", roomRepo.findAll());
        return "index";
    }
	
	@GetMapping("/bookingRoom/{id}")
	public String getRoomBooking(@PathVariable("id") Long rid, Model model) {
		RoomBooking roomBooking = new RoomBooking();
		model.addAttribute("rid", Long.toString(rid));
		model.addAttribute("roombooking", roomBooking);
		 Room room = roomRepo.getById(rid);
		 model.addAttribute("room", room);
		 
		 return "roomDetail";
	}
	
	@GetMapping("/roomIsNotAvailable")
	public String roomIsNotAvailable() {
		return "notAvailable";
	}
	
	@PostMapping("/bookingRoom/{id}")
	public String getThisRoom(@PathVariable("id" ) Long rid, @ModelAttribute("roombooking") RoomBooking roomBooking,Principal principal,Model model) {
		UserRole userRole = userRoleRepo.findByUsername(principal.getName()).get();
		Date checked_in_date = roomBooking.getCheckedInDate();
		Date checked_out_date = roomBooking.getCheckedOutDate();
		if(checked_out_date.after(checked_in_date)) {
			Room room = roomRepo.findById(rid).get();
			roomBooking.setRoom(room);
			roomBooking.setUserRole(userRole);

			roomBookingRepo.save(roomBooking);
		}else {
			model.addAttribute("message", "Wrong date");
			return "roomDetail";
		}
		
		return "redirect:/index";
//		}else 
//			return "redirect:/roomIsNotAvalable";
		
		
	}
	@GetMapping("/booking/history")
	public String viewHistory(Principal principal, Model model) {
		UserRole userRole = userRoleRepo.findByUsername(principal.getName()).get();
		List<RoomBooking> roomBookings = roomBookingRepo.findByUserRole(userRole);
		model.addAttribute("roombookings", roomBookings);
				return "bookinghistory";
	}
}
