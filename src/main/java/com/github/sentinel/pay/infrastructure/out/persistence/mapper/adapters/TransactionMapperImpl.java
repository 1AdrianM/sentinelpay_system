package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.Channel;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.TransactionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public TransactionEntity domainEntityToEntityModel(Transaction domainEntity) {
        return TransactionEntity.builder()
                .clientAccountId(domainEntity.getClientAccountId().id())
                .city(domainEntity.getLocation().city())
                .country(domainEntity.getLocation().country())
                .id(domainEntity.getTransactionId().id())
                .accountId(domainEntity.getAccountId().id())
               .channel(domainEntity.getChannel().name())
                .amount(domainEntity.getMoney().amount())
                 .currency(domainEntity.getMoney().currency().name())
                .timestamp(domainEntity.getTimestamp())
                .transactionType(domainEntity.getTransactionType())
                .build();
    }

    @Override
    public Transaction EntityModelToDomainEntity(TransactionEntity entityModel) {
        return Transaction.builder()
                .clientAccountId(ClientAccountId.of(entityModel.getClientAccountId()))
                .transactionId( TransactionId.of(entityModel.getId()))
                .location(Location.of(entityModel.getCity(),entityModel.getCountry()))
                .accountId(AccountId.of(entityModel.getAccountId()))
                .money(Money.of(entityModel.getAmount(), Currency.valueOf(entityModel.getCurrency())))
                .channel(Channel.valueOf(entityModel.getChannel()))
                .timestamp(entityModel.getTimestamp())
                .transactionType(entityModel.getTransactionType())
                .build();
    }
}
