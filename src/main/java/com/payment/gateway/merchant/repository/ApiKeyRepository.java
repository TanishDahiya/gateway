package com.payment.gateway.merchant.repository;

import com.payment.gateway.merchant.entity.APIKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<APIKey, UUID> {

    List<APIKey> findByMerchant_Id(UUID merchantId);

    Optional<APIKey> findByIdAndMerchantId(UUID keyId, UUID merchantId);

    @Modifying
    @Query("""
    update APIKey a
    set a.enabled = false
    where a.id = :keyId
    and a.merchant.id = :merchantId
    """)
    int revokeApiKey(UUID keyId, UUID merchantId);
}
