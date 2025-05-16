package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import respository.TicketDAO;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public void bookTicket(Long customerId, Long scheduleId, Long seatId, Double price) {
        ticketDAO.bookTicket(customerId, scheduleId, seatId, price);
    }
}