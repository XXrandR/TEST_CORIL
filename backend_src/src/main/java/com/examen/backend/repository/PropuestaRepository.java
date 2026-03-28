package com.examen.backend.repository;

import com.examen.backend.dto.IPropuesta;
import com.examen.backend.entity.Propuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropuestaRepository extends JpaRepository<Propuesta,Long> {
    @Query(value = "CALL SP_propuesta_Q01(:bandejaId)", nativeQuery = true)
    List<IPropuesta> propuestaByBandeja(@Param("bandejaId") Integer bandejaId);
}
