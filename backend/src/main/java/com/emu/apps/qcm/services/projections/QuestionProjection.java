package com.emu.apps.qcm.services.projections;

import com.emu.apps.qcm.model.Questionnaire;
import com.emu.apps.qcm.model.Response;
import com.emu.apps.qcm.model.Type;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Date;
import java.util.List;

@Immutable
public interface QuestionProjection  {

    public Long getId();

    public Long getVersion();

    public Date getDate();

    public Boolean getMandatory();

    public Long getNumber();

    public Questionnaire getQuestionnaire();

    public String getQuestion();

    public Type getType();

    public List<Response> getResponses();

    public String getUuid();

}
