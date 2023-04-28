package com.techelevator.dao;

import com.techelevator.model.Flashcard;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcFlashcardDaoTests extends BaseDaoTests {

    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER");
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER");
    protected static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER");

    private final Flashcard FLASHCARD_1 = new Flashcard(1, "Why does Tony cough?", "He is sick", "Sick", 1);
    private final Flashcard FLASHCARD_2 = new Flashcard(2, "Why can't Cory type?", "He's bad.", "Handicaps", 2);
    private final Flashcard FLASHCARD_3 = new Flashcard(3, "Why is Chris an idiot?", "He likes to eat lead paint", "Handicaps", 3);
    private final Flashcard FLASHCARD_4 = new Flashcard(4, "Why is Abdi single?", "He can't find the right one.", "Loveless", 1);

    private JdbcFlashcardDao sut;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcFlashcardDao(jdbcTemplate);
    }


    @Test
    public void create_flashcard_gets_new_flashcard_id(){
        Flashcard flashcard = new Flashcard();
        flashcard.setCardId(FLASHCARD_1.getCardId());
        flashcard.setQuestion(FLASHCARD_1.getQuestion());
        flashcard.setAnswer(FLASHCARD_1.getAnswer());
        flashcard.setTags(FLASHCARD_1.getTags());
        flashcard.setUserId(FLASHCARD_1.getUserId());

        Flashcard result = sut.createFlashCard(flashcard);

        Assert.assertTrue("Creating a new Flashcard should give you a new Id greater than 0", result.getCardId() > 0);

    }

    @Test
    public void create_new_flashcard_gets_correct_values(){

        Flashcard flashcard = new Flashcard();
        flashcard.setCardId(FLASHCARD_2.getCardId());
        flashcard.setQuestion(FLASHCARD_2.getQuestion());
        flashcard.setAnswer(FLASHCARD_2.getAnswer());
        flashcard.setTags(FLASHCARD_2.getTags());
        flashcard.setUserId(FLASHCARD_2.getUserId());

        Flashcard result = sut.createFlashCard(flashcard);

        Assert.assertEquals(1, result.getCardId());
        Assert.assertEquals("Why can't Cory type?", result.getQuestion());
        Assert.assertEquals("He's bad.", result.getAnswer());
        Assert.assertEquals("Handicaps", result.getTags());
        Assert.assertEquals(2, result.getUserId());

    }

    @Test
    public void get_Flashcard_By_Id(){

        Flashcard result = sut.getFlashCardById(FLASHCARD_3.getCardId());

        Assert.assertEquals(1, result.getCardId());
        Assert.assertEquals("Why is Chris an idiot?", result.getQuestion());
        Assert.assertEquals("He likes to eat lead paint", result.getAnswer());
        Assert.assertEquals("Handicaps", result.getTags());
        Assert.assertEquals(3, result.getUserId());

    }

    @Test
    public void get_Flashcard_By_Tag(){
        String tag = FLASHCARD_3.getTags();

        List<Flashcard> cards = sut.getFlashCardByTag(tag);

        Assert.assertNotNull(cards);
        Assert.assertEquals(2, cards.size());
        Assert.assertEquals(FLASHCARD_2, cards.get(1));
        Assert.assertEquals(FLASHCARD_3, cards.get(2));


    }

    @Test
    public void edit_flashcard_by_id(){

        sut.editFlashCardById(FLASHCARD_4);


    }




}
