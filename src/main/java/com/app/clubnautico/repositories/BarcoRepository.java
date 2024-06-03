// BarcoRepository.java
package com.app.clubnautico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.clubnautico.models.BarcoModel;

@Repository
public interface BarcoRepository extends JpaRepository<BarcoModel, Integer> {

}