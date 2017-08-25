package org.redrock.play;

public class Words {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord_one() {
        return word_one;
    }

    public void setWord_one(String word_one) {
        this.word_one = word_one;
    }

    public String getWord_two() {
        return word_two;
    }

    public void setWord_two(String word_two) {
        this.word_two = word_two;
    }

    private Integer id;
    private String word_one;
    private String word_two;
}
