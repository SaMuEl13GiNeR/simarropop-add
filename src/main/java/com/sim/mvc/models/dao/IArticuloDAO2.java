package com.sim.mvc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sim.mvc.models.entity.Articulo2;

public interface IArticuloDAO2 extends JpaRepository<Articulo2, Long> {
	
}
