package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.Profile;
import com.emu.apps.qcm.services.repositories.ProfileJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProfileJpaRepository userRepository;

    public Profile findByPrincipalId(String principalId){
        return userRepository.findByPrincipalId(principalId);
    }

    public Profile save(Profile user){
        return userRepository.save(user);
    }

}
