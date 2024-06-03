// SalidaRepository.java
package com.app.clubnautico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.clubnautico.models.SalidaModel;

@Repository
public interface SalidaRepository extends JpaRepository<SalidaModel, Integer> {

}