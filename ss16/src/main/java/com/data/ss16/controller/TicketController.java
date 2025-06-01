package com.data.ss16.controller;

import com.data.ss16.model.BusTrip;
import com.data.ss16.model.Ticket;
import com.data.ss16.model.User;
import com.data.ss16.service.BusTripService;
import com.data.ss16.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private BusTripService busTripService;

    @GetMapping("/book/{tripId}")
    public String showBookingForm(@PathVariable Integer tripId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/login";
        BusTrip trip = busTripService.findById(tripId);
        model.addAttribute("trip", trip);
        model.addAttribute("ticket", new Ticket());
        return "ticket-book";
    }

    @PostMapping("/book/{tripId}")
    public String bookTicket(@PathVariable Integer tripId,
                             @ModelAttribute("ticket") @Valid Ticket ticket,
                             BindingResult result,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/login";
        BusTrip trip = busTripService.findById(tripId);
        if (result.hasErrors()) {
            model.addAttribute("trip", trip);
            return "ticket-book";
        }
        ticket.setUserId(user.getId());
        ticket.setTripBusId(tripId);
        ticketService.bookTicket(ticket);
        return "redirect:/ticket/my";
    }

    @GetMapping("/my")
    public String myTickets(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/login";
        List<Ticket> tickets = ticketService.findByUserId(user.getId());
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }

    @GetMapping("/detail/{id}")
    public String ticketDetail(@PathVariable Integer id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/login";
        Ticket ticket = ticketService.findById(id);
        if (ticket == null || !ticket.getUserId().equals(user.getId())) {
            return "redirect:/ticket/my";
        }
        model.addAttribute("ticket", ticket);
        return "ticket-detail";
    }
}