package com.github.sentinel.pay.infrastructure.out.persistence.base;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para operaciones CRUD en un repositorio de infra.
 *
 * @param <D> El tipo de entidad de dominio que manejara el repositorio.
 * @param <I> El tipo del identificador único de la entidad.
 */
public interface GenericPersistenceRepository<D,I> {
   D save(D domainEntity);//
    List<D> saveAll(List<D> entities);//
    boolean existsById(I id); // Verifica si una entidad existe
    D update(I id, D entity); // Actualiza una entidad específica
    Optional<D> findById(I ID);//
    void deleteAll(List<D> entities); // Elimina varias entidades
    void delete(D domainEntity);//
   void deleteById(I id);
   List<D> findAll();//
}
