package com.github.sentinel.pay.infrastructure.out.persistence.base;

import com.github.sentinel.pay.infrastructure.out.persistence.mapper.EntityMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class BasePersistenceAdapter<D,E,I> implements GenericPersistenceRepository<D,I> {
private final EntityMapper<D,E> entityMapper;
private final EntityRepository<E,I> entityRepository;


  public D save(D domainEntity){
     E entity=   entityMapper.domainEntityToEntityModel(domainEntity);
     return entityMapper.EntityModelToDomainEntity(entityRepository.save(entity));

  }//
   public List<D> saveAll(List<D> entities){
  List<E> entityList= entities.stream().map(entityMapper::domainEntityToEntityModel).toList();
  List<E> savedList= entityRepository.saveAll(entityList);
   return savedList.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList()) ;}//
  public  boolean existsById(I id){
 return entityRepository.existsById(id);

  } // Verifica si una entidad existe
  public  D update(I id, D entity){
      if (!entityRepository.existsById(id)) {
          throw new EntityNotFoundException("Entity not found with id: " + id);
      }
      // Convert domain → entity
      E mappedEntity = entityMapper.domainEntityToEntityModel(entity);


      // save = merge (JPA)
      E updated = entityRepository.save(mappedEntity);

      return entityMapper.EntityModelToDomainEntity(updated);

  } // Actualiza una entidad específica
   public Optional<D> findById(I ID){
 Optional<E> entity = entityRepository.findById(ID);
 return entity.map(entityMapper::EntityModelToDomainEntity);
  }//

   public void deleteAll(List<D> entities){
    List<E> eList=  entities.stream().map(entityMapper::domainEntityToEntityModel).collect(Collectors.toList());
    entityRepository.deleteAll(eList);
   }
   // Elimina varias entidades
   public void deleteById(I id){
      entityRepository.deleteById(id);
    }

    public void delete(D domainEntity){
   E entity=  entityMapper.domainEntityToEntityModel(domainEntity);
   entityRepository.delete(entity);
   }//
  public  List<D> findAll(){
  List<E> entityList= entityRepository.findAll();
  return entityList.stream()
          .map(entityMapper::EntityModelToDomainEntity)
          .collect(Collectors.toList());
  }//
}
