package respository;

public interface TicketDAO {
    void bookTicket(Long customerId, Long scheduleId, Long seatId, Double price);
}
