package com.Xsupport.Repo;

import com.Xsupport.Entity.Category;
import com.Xsupport.Entity.Status;
import com.Xsupport.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Locale;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByStatus(Status status);

    @Query("SELECT t FROM Ticket t WHERE " +
            "(:category IS NULL OR t.category = :category) AND " +
            "(:status IS NULL OR t.status = :status) " +
            "ORDER BY t.createdTime DESC")
    List<Ticket> findTicketsWithFilter(
            @Param("category") Category category,
            @Param("status") Status status);

    @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId " +
            "ORDER BY t.createdTime DESC")
    List<Ticket> findByUser(@Param("userId") Long userId);
}
