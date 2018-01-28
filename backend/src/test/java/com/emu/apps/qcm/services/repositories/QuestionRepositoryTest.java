package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.*;
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
public class QuestionRepositoryTest {

    private static final String QUESTION= "a cool question";

    private static final String RESPONSE = "a cool response";

    @Autowired
    private QuestionJpaRepository questionRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    @Transactional
    public void testPersistence() {

        questionRepository.deleteAll();

        //given
        Question question = new Question();
        question.setQuestion(QUESTION);
        question.setType(Type.FREE_TEXT);
        question.setNumber(1L);

        //when
        questionRepository.save(question);

        //then
        Assert.assertNotNull(question.getId());
        Question newQuestion = questionRepository.findOne(question.getId());
        Assert.assertNotNull(question.getId());
        Assert.assertEquals(QUESTION, newQuestion.getQuestion());
        //Assert.assertEquals(RESPONSE, newQuestion.getResponse());

    }

}