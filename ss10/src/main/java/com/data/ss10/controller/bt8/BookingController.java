package com.data.ss10.controller.bt8;

import com.data.ss10.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "/bt8/bookingForm";
    }

    @PostMapping("/book")
    public String bookTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        double pricePerSeat = 50000;
        double total = ticket.getSeats().size() * pricePerSeat;
        ticket.setTotalAmount(total);

        model.addAttribute("ticket", ticket);
        return "/bt8/bookingResult";
    }
}
