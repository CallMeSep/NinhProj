package hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotel.entity.*;
import hotel.repository.RoomBookingRepository;
import hotel.repository.UserRoleRepository;

@Controller
@RequestMapping("/admin/revenue")
public class AdminRevenueController {
	@Autowired
	private RoomBookingRepository roomBookingRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@GetMapping("")
    public String getRevenue(@Param("uid")Long uid,Model model) {
		if(uid != null) {
			UserRole userRole = userRoleRepository.findById(uid).get();
	        List<RoomBooking> roomBooking = roomBookingRepository.findByUserRole(userRole);
	        double sum = 0;
	        for(RoomBooking r : roomBooking) {
	        	long difference_In_Time
	            = r.getCheckedOutDate().getTime() - r.getCheckedInDate().getTime();
	        	long difference_In_Days
	            = (difference_In_Time
	               / (1000 * 60 * 60 * 24))
	              % 365;
	        	sum += r.getRoom().getPrice() * difference_In_Days;
	        }
	        model.addAttribute("sum", sum);
	        model.addAttribute("roombookings",roomBooking);
	        model.addAttribute("userroles", userRoleRepository.findAll());
		}
		else {
			List<RoomBooking> roomBooking = roomBookingRepository.findAll();
	        double sum = 0;
	        for(RoomBooking r : roomBooking) {
	        	long difference_In_Time
	            = r.getCheckedOutDate().getTime() - r.getCheckedInDate().getTime();
	        	long difference_In_Days
	            = (difference_In_Time
	               / (1000 * 60 * 60 * 24))
	              % 365;
	        	sum += r.getRoom().getPrice() * difference_In_Days;
	        }
	        model.addAttribute("sum", sum);
	        model.addAttribute("roombookings",roomBooking);
	        model.addAttribute("userroles", userRoleRepository.findAll());
		}
        
        return "revenue";
    }
//	@GetMapping("")
//    public String getRevenueUser(@Param("uid")String uid,Model model) {
//		UserRole userRole = userRoleRepository.findById(Long.parseLong(uid)).get();
//        List<RoomBooking> roomBooking = roomBookingRepository.findByUserRole(userRole);
//        double sum = 0;
//        for(RoomBooking r : roomBooking) {
//        	long difference_In_Time
//            = r.getCheckedOutDate().getTime() - r.getCheckedInDate().getTime();
//        	long difference_In_Days
//            = (difference_In_Time
//               / (1000 * 60 * 60 * 24))
//              % 365;
//        	sum += r.getRoom().getPrice() * difference_In_Days;
//        }
//        model.addAttribute("sum", sum);
//        model.addAttribute("roombookings",roomBooking);
//        return "revenue";
//    }
}
