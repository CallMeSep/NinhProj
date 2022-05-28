package univer.program.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import univer.program.entity.*;
import univer.program.repository.HotelRepository;
import univer.program.repository.RoomBookingRepository;

@Controller
public class AdminNumberOfCheckedIn {
	@Autowired
	private RoomBookingRepository roomBookingRepository;
	@Autowired
	private HotelRepository hotelRepo;
	
	@GetMapping("/admin/roombooking")
	public String countingBookingByMonth(@Param("month_year") String month_year,Model model) {
		if(month_year == null) {

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

		}else {
			String year = month_year.substring(0, 4);
			String month = month_year.substring(5);
			List<RoomBooking> roomBooking = roomBookingRepository.findRoomBookingIn(Integer.parseInt(month), Integer.parseInt(year));
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
		}
			
		return "countingBooking";
	}
	@GetMapping("/admin/revenuehotel")
	public String revenueByHotel(@Param("hid") Long hid, Model model) {
		model.addAttribute("hotels", hotelRepo.findAll());
		if(hid == null) {

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

		}else {
			Hotel hotel = hotelRepo.findById(hid).get();
			List<RoomBooking> roomBooking = roomBookingRepository.findByKhachSan(hid);
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
		}
			
		return "RevenueByHotel";
	}
}
