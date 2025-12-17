package fr.alma.quizmaker.domain;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChoiceTest {

    @Test
    public void choiceTextMustNotBeBlank(){
        assertThrows(IllegalArgumentException.class,()->new Choice("",true),
                "Choice text must not be empty");
    }

    @Test
    public void choiceTextMustNotBeNull(){
        assertThrows(IllegalArgumentException.class,()->new Choice(null,true),
                "Choice text must not be empty");
    }

    @Test
    public void choiceCreatedSuccessfully(){
        Choice choice = new Choice("first-choice", true);

        assertNotNull(choice);

        assertNotNull(choice.getId());
        assertEquals("first-choice", choice.getText());
        assertTrue(choice.isCorrect());
    }
}
