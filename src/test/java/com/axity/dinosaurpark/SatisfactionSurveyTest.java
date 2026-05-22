package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.SatisfactionSurvey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SatisfactionSurveyTest {

    @Test
    void testTouristId() {
        SatisfactionSurvey survey = new SatisfactionSurvey(1, "Encierro 1", 4);
        assertEquals(1, survey.getTouristId());
    }

    @Test
    void testEnclosureName() {
        SatisfactionSurvey survey = new SatisfactionSurvey(1, "Encierro 1", 4);
        assertEquals("Encierro 1", survey.getEnclosureName());
    }

    @Test
    void testScore() {
        SatisfactionSurvey survey = new SatisfactionSurvey(1, "Encierro 1", 4);
        assertEquals(4, survey.getScore());
    }

    @Test
    void testScoreMinValue() {
        SatisfactionSurvey survey = new SatisfactionSurvey(1, "Encierro 1", 1);
        assertEquals(1, survey.getScore());
    }

    @Test
    void testScoreMaxValue() {
        SatisfactionSurvey survey = new SatisfactionSurvey(1, "Encierro 1", 5);
        assertEquals(5, survey.getScore());
    }
}