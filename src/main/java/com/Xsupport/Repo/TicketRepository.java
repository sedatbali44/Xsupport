package com.Xsupport.Repo;

import com.Xsupport.Entity.Status;
import com.Xsupport.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByStatus(Status status);

    @Query("SELECT t FROM Ticket t WHERE " +
            "(:status IS NULL OR t.status = :status) " +
            "ORDER BY t.createdTime DESC")
    List<Ticket> findTicketsWithFilter(@Param("status") Status status);

    @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId " +
            "ORDER BY t.createdTime DESC")
    List<Ticket> findByUserIdOrderBycreatedTimeDesc(@Param("userId") Long userId);
}
