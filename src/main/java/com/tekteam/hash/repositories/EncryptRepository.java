package com.tekteam.hash.repositories;

import com.tekteam.hash.entity.EncryptMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptRepository extends JpaRepository<EncryptMessage, Long> {

    EncryptMessage save(EncryptMessage encryptedDataEntity);
}
