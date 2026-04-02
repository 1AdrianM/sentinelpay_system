package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudIncidentEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.FraudIncidentEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.FraudIncidentMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FraudIncidentAdapter extends BasePersistenceAdapter<FraudIncident, FraudIncidentEntity, UUID> implements FraudIncidentRepository {

  private final  FraudIncidentMapper entityMapper;
   private final FraudIncidentEntityRepository entityRepository;
    public FraudIncidentAdapter(FraudIncidentMapper entityMapper, FraudIncidentEntityRepository entityRepository) {
        super(entityMapper, entityRepository);
       this.entityMapper= entityMapper;
        this.entityRepository=entityRepository;
    }

    @Override
    public List<FraudIncident> findOpenByTransactionId(TransactionId transactionId) {
      var incidents=  this.entityRepository.findIncidentsByTransactionIdAndStatus(transactionId.id(), FraudIncidentStatus.OPEN);
      return incidents.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<FraudIncident> findOpenByAccountId(AccountId accountId) {

      var incidents=  this.entityRepository.findByAccountIdAndStatus(accountId.id(),FraudIncidentStatus.OPEN);
     return incidents.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }


    @Override
    public FraudIncident resolve(FraudIncident incident) {
        return update(incident.getIncidentId().id(),incident);
    }

    @Override
    public List<FraudIncident> findAllByClientAccountId(ClientAccountId clientAccountId) {
      var incidentList=  this.entityRepository.findAllByClientAccountId(clientAccountId.id());
        return incidentList.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public FraudIncident findByFraudIncidentId(FraudIncidentId incidentId) {
        return findById(incidentId.id()).orElseThrow(()-> new RuntimeException("fraud incident not found"));
    }

    @Override
    public int findAllConfirmedFraudIncidentsCount(ClientAccountId clientAccountId) {
        return this.entityRepository.findAllConfirmedFraudIncidentsCount(clientAccountId.id());
    }

    @Override
    public int findOpenIncidentCount(ClientAccountId clientAccountId) {
        return this.entityRepository.findOpenIncidentCount(clientAccountId.id());
    }

    @Override
    public List<FraudIncident> findAllConfirmedFraudIncidents(ClientAccountId clientAccountId) {
       var fraudIncidentList= this.entityRepository.findAllConfirmedFraudIncidents(clientAccountId.id());
       return fraudIncidentList.stream()
             .map(entityMapper::EntityModelToDomainEntity)
             .collect(Collectors.toList());
    }

    @Override
    public List<FraudIncident> findAllByAccountId(AccountId accountID, Pageable pageable) {
        return this.entityRepository
                .findAllByAccountId(accountID,pageable)
                .stream()
                .map(entityMapper::EntityModelToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FraudIncident findIncidentOpenByTransactionId(TransactionId transactionId) {
        
        FraudIncidentEntity incident=  this.entityRepository.findIncidentByTransactionIdAndStatus(transactionId.id(), FraudIncidentStatus.OPEN);
       return entityMapper.EntityModelToDomainEntity(incident);
    }

    @Override
    public List<FraudIncident> findIncidentsByDecisionId(FraudDecisionId id) {
        var incidents= this.entityRepository.findByDecision_Id(id.id());
        return incidents.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<FraudIncident> findAllOpenFraudIncidents(ClientAccountId clientAccountId) {
        var fraudIncidentList= this.entityRepository.findAllOpenFraudIncidents(clientAccountId.id());
       return fraudIncidentList.stream()
             .map(entityMapper::EntityModelToDomainEntity)
             .collect(Collectors.toList());
    }
}
