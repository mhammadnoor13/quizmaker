package fr.alma.quizmaker.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    List<Choice> validChoicesList;
    List<Choice> notEnoughChoicesList; //handles empty and one choice

    Choice choice1;
    Choice choice2;

    @BeforeEach
    public void setup(){
        choice1 = new Choice("First-choice",true);
        choice2 = new Choice("Second-choice",false);
        validChoicesList = List.of(choice1,choice2);
        notEnoughChoicesList = List.of(choice1);
    }


    @Test
    public void questionTextMustNotBeBlank(){
        assertThrows(IllegalArgumentException.class,()->new Question("", validChoicesList),
                "Question text must not be empty");
    }

    @Test
    public void questionTextMustNotBeNull(){
        assertThrows(IllegalArgumentException.class,()->new Question(null,validChoicesList),
                "Question text must not be empty");
    }
    @Test
    public void invalidQuestionCreated(){
        Question question = new Question("Choose the best of the following: ", notEnoughChoicesList);

        assertNotNull(question);

        assertNotNull(question.getId());

        assertEquals("Choose the best of the following:", question.getText());
        assertIterableEquals(notEnoughChoicesList, question.getChoices());

        assertFalse(question.isValid());
    }
    @Test
    public void questionCreatedSuccessfully(){
        Question question = new Question("Choose the best of the following: ", validChoicesList);

        assertNotNull(question);

        assertNotNull(question.getId());

        assertEquals("Choose the best of the following:", question.getText());
        assertIterableEquals(validChoicesList, question.getChoices());

        assertTrue(question.isValid());
    }
}
