package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface QuestionnaireCrudRepository extends CrudRepository<Questionnaire, Long> {


}
