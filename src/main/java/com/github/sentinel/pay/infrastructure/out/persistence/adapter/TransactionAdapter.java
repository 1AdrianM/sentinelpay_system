package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.repository.TransactionRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.TransactionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.TransactionEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Component
public class TransactionAdapter extends BasePersistenceAdapter<Transaction, TransactionEntity, UUID> implements TransactionRepository {
      private final  TransactionEntityRepository entityRepository;
     private final TransactionMapper entityMapper;
    public TransactionAdapter(TransactionMapper entityMapper, TransactionEntityRepository entityRepository) {
        super(entityMapper, entityRepository);
        this.entityRepository = entityRepository;
        this.entityMapper= entityMapper;

    }

    @Override
    public Transaction findByAccountId(AccountId accountId) {
      var transaction=  entityRepository.findByAccountId(accountId.id());
      return entityMapper.EntityModelToDomainEntity(transaction);
    }

    @Override
    public List<Transaction> findConfirmedFraudulentTransactions() {
        var entityList =entityRepository.findAllFraudulentTransactions();
        return entityList.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }


    @Override
    public Transaction findByIncidentId(FraudIncidentId incidentId) {
        TransactionEntity transactionEntity=  this.entityRepository.findByFraudIncidentId(incidentId.id());
        return entityMapper.EntityModelToDomainEntity(transactionEntity);
    }

    @Override
    public int findTransactionCountThisDay(Instant now, ClientAccountId clientAccountId) {
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = startOfDay.plus(1, ChronoUnit.DAYS).minusMillis(1);

        return this.entityRepository.findTransactionCountThisDay(startOfDay,endOfDay, clientAccountId.id());
    }
}
