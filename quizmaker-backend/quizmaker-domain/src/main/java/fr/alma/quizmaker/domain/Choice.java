package fr.alma.quizmaker.domain;

import java.util.UUID;

public class Choice {
    private UUID id;
    private String text;
    private boolean correct;

    Choice(String text, boolean correct)
    {
        this.id = UUID.randomUUID();
        if (text == null || text.trim().isEmpty())
        {
            throw new IllegalArgumentException("Choice text must not empty!");
        }
        this.text = text.trim();
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public UUID getId() {
        return id;
    }

    public boolean isCorrect() {
        return correct;
    }
}
