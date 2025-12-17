package fr.alma.quizmaker.domain;

import java.util.List;
import java.util.UUID;

public class Question {
    private UUID id;
    private String text;
    private List<Choice> choices;

    public Question(String text, List<Choice> choices) {
        this.id = UUID.randomUUID();
        if (text == null || text.trim().isEmpty())
        {
            throw new IllegalArgumentException("Question text must not empty!");
        }
        this.text = text.trim();
        this.choices = choices;
    }

    public void addChoice(Choice choice){

        choices.add(choice);
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public boolean isValid(){
        if (choices.size()<2){return false;}
        return true;
    }
}
