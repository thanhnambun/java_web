package com.data.ss16.service;

import com.data.ss16.model.Ticket;
import com.data.ss16.responsitory.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public void bookTicket(Ticket ticket) {
        ticketDAO.insertTicket(ticket);
    }
    @Override
    public List<Ticket> findByUserId(Integer userId) {
        return ticketDAO.findByUserId(userId);
    }
    @Override
    public Ticket findById(Integer id) {
        return ticketDAO.findById(id);
    }
}