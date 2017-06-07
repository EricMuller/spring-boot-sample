package com.emu.apps.sample;

import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.repositories.QuestionCrudRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuestionRepositoryTest {

    private static final String QUESTION= "a cool question";
    private static final String RESPONSE = "a cool response";

    @Autowired
    private QuestionCrudRepository questionRepository;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Question question = new Question();
        question.setQuestion(QUESTION);
        question.setResponse(RESPONSE);

        //when
        questionRepository.save(question);

        //then
        Assert.assertNotNull(question.getId());
        Question newQuestion = questionRepository.findOne(question.getId());
        Assert.assertEquals((Long) 1L, question.getId());
        Assert.assertEquals(QUESTION, newQuestion.getQuestion());
        Assert.assertEquals(RESPONSE, newQuestion.getResponse());

    }
}