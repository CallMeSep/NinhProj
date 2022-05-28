package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import univer.program.entity.Hotel;
import univer.program.repository.HotelRepository;
//import univer.program.service.HotelService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/hotel")
public class AdminHotelController {
//	@Autowired
//    private HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepo;
//    @Autowired
//    public AdminHotelController(HotelService hotelService) {
//        this.hotelService = hotelService;
//    }

    @GetMapping(value={"", "/"})
    public String welcome(Model model) {
        model.addAttribute("hotels", hotelRepo.findAllAndXoa(false));
        return "hotelList.html";
    }

    @GetMapping("/add")
    public String addHotelForm() {
        return "hotel_add.html";
    }

    @PostMapping("/save")
    public String addHotel(@RequestParam("image") MultipartFile multipartFile,
                           @RequestParam("name") String name,
                           @RequestParam("country") String country,
                           @RequestParam("city") String city,
                           @RequestParam("address") String address,
                           @RequestParam("phone_number") String phone_number) throws IOException {
        Hotel hotel = new Hotel(name, country, city, address, phone_number);
        hotel.setImage(multipartFile.getBytes());
        hotelRepo.save(hotel);
        return "redirect:/admin/hotel";
    }

    @GetMapping("/edit/{id}")
    public String editHotel(@PathVariable("id") Long id, Model model) {
        Optional<Hotel> hotel = hotelRepo.findById(id);

//        if (hotel.isEmpty())
//            return "404";

        model.addAttribute("hotel", hotel.get());
        return "hotel_edit.html";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteHotel(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
    	Optional<Hotel> hotel = hotelRepo.findById(id);
        if (!hotel.isPresent())
            return new RedirectView("/404");
        Hotel newHotel = hotel.get();
        newHotel.setXoa(true);
        hotelRepo.save(newHotel);
        return new RedirectView("/admin/hotel");
    }

    @GetMapping("/image")
    public String getImage(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping(value="/save/{id}")
    public String saveHotel(@PathVariable("id") Long id, @RequestParam("image") MultipartFile multipartFile,
                            @RequestParam("name") String name,
                            @RequestParam("country") String country,
                            @RequestParam("city") String city,
                            @RequestParam("address") String address,
                            @RequestParam("phone_number") String phone_number)
            throws IOException {
        Hotel hotel = hotelRepo.findById(id).get();
        hotel.setData(name, country, city, address, phone_number);
        hotel.setImage(multipartFile.getBytes());
        hotelRepo.save(hotel);

        return "redirect:/admin/hotel";
    }

    @PostMapping(value="/delete/{id}")
    public String deleteHotel(@PathVariable("id") Long id) {
        hotelRepo.deleteById(id);

        return "redirect:/admin/hotel";
    }
}
