package com.sim.mvc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sim.mvc.models.entity.Articulo;

public interface IArticuloDAO extends JpaRepository<Articulo, Long> {
	@Query("FROM Articulo a WHERE a.usuarioVendedor.id = :id")
	List<Articulo> findByUsuarioVendedorId(@Param("id") Long id);
	
	@Query("FROM Articulo a WHERE a.usuarioComprador.id = :id")
	List<Articulo> findByUsuarioCompradorId(@Param("id") Long id);
	
	@Query("FROM Articulo a WHERE a.categoria.id = :id")
	List<Articulo> findByCategoriaId(@Param("id") Long id);
	
	@Query("FROM Articulo a ORDER BY a.precio ASC")
	List<Articulo> findAllOrderByLowerPrecio();
	
	@Query("FROM Articulo a ORDER BY a.precio DESC")
	List<Articulo> findAllOrderByHigherPrecio();
	
	@Query("FROM Articulo a WHERE a.titulo LIKE :titulo")
	List<Articulo> findByTitulo(@Param("titulo") String titulo);

}
