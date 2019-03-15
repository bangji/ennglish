package cn.news.entity;

import java.util.ArrayList;
import java.util.List;

public class English {
    private String english;
    private List<String> chinese = new ArrayList<>();
    private int errorNumber;

    public English() {
    }

    public English(String english, List<String> chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public English setEnglish(String english) {
        this.english = english;
        return this;
    }

    public List<String> getChinese() {
        return chinese;
    }

    public English setChinese(List<String> chinese) {
        this.chinese = chinese;
        return this;
    }

    public English setChinese(String chinese) {
        this.chinese.add(chinese);
        return this;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public English setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
        return this;
    }

    public English setErrorNumberIncrement() {
        this.errorNumber++;
        return this;
    }
}
