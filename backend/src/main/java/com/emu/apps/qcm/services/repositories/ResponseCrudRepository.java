package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.Response;
import org.springframework.stereotype.Repository;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface ResponseCrudRepository extends SimpleJpaBulkRepository<Response, Long> {


}
