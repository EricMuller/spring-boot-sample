package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileJpaRepository extends SimpleJpaBulkRepository<Profile, Integer> {
    Profile findByPrincipalId(String principalId);
}