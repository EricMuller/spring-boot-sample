package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.model.Category;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuestionnaireRepositoryTest {

    private static final String QUESTION = "a cool question";
    private static final String RESPONSE = "a cool response";
    private static final String RESPONSE2 = "a cool response2";

    @Autowired
    private QuestionCrudRepository questionRepository;

    @Autowired
    private ResponseCrudRepository responseCrudRepository;

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Autowired
    private QuestionnaireCrudRepository questionnaireCrudRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testPersistence() {

        Response response = new Response();
        response.setNumber(1l);
        response.setResponse(RESPONSE);

        responseCrudRepository.save(response);

        Response response2 = new Response();
        response2.setNumber(2l);
        response2.setResponse(RESPONSE2);

        responseCrudRepository.save(response2);

        Category category = new Category();
        category.setLibelle("Test");
        categoryCrudRepository.save(category);

        //given
        Question question = new Question();
        question.setQuestion(QUESTION);
        question.setType(Type.FREE_TEXT);
        question.setNumber(2L);

        question.setResponses(Lists.newArrayList(response, response2));

        //when
        questionRepository.save(question);


        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle("test");

        questionnaire.setQuestions(Lists.newArrayList(question));

        questionnaireCrudRepository.save(questionnaire);

        //then
        Assert.assertNotNull(questionnaire.getId());

        Questionnaire newQuestionnaire = questionnaireCrudRepository.findOne(questionnaire.getId());

        Assert.assertNotNull(newQuestionnaire.getId());

        Assert.assertEquals(1, newQuestionnaire.getQuestions().size());


        Question question1 = Iterables.getFirst(newQuestionnaire.getQuestions(), null);

        Assert.assertNotNull(question1);
        Assert.assertEquals(2, question1.getResponses().size());

        Response response1 = Iterables.getFirst(question1.getResponses(), null);

        Assert.assertEquals(RESPONSE, response1.getResponse());


    }

}