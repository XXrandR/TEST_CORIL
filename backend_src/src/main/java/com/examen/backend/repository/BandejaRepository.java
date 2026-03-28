package com.examen.backend.repository;

import com.examen.backend.entity.Bandeja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandejaRepository extends JpaRepository<Bandeja,Long> {
    @Query(value = "SELECT b.id FROM bandeja b GROUP BY b.id", nativeQuery = true)
    List<Long> idsOfBandejas();
}
