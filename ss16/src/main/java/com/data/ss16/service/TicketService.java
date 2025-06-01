package com.data.ss16.service;


import com.data.ss16.model.Ticket;

import java.util.List;

public interface TicketService {
    void bookTicket(Ticket ticket);
    List<Ticket> findByUserId(Integer userId);
    Ticket findById(Integer id);
}
