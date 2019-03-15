package cn.news.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Clb
 * @Date: 2018/8/20 11:23
 * @Description:
 */
public class CurrentAnswerProgress {
    /**
     * 英语列表
     */
    private List<English> eList;
    /**
     * 英语已通过列表
     */
    private List<English> eAlreadyList = new LinkedList<>();
    /**
     * 当前答题英语下标
     */
    private int nowAProblemIndex = -1;
    /**
     * 当前答题英语出示中文下标
     */
    private int nowAProblemChineseIndex = -1;
    /**
     * 用户给出的回答
     */
    private String userAnswer;
    /**
     * 上一道答题英语下标
     */
    private int onAProblemIndex = -1;
    /**
     * 上一道答题英语出示中文下标
     */
    private int onAProblemChineseIndex = -1;
    /**
     * 上一道答题结果
     */
    private boolean onAProblemResult;
    /**
     * 本次答题错误总次数
     */
    private double errorNumber;
    /**
     * 本次答题总题数
     */
    public double topicNumber;

    public CurrentAnswerProgress(List<English> eList) {
        this.eList = eList;
        topicNumber = eList.size();
    }

    public double getTopicNumber() {
        return topicNumber;
    }

    public double getErrorNumber() {
        return errorNumber;
    }

    public List<English> geteAlreadyList() {
        return eAlreadyList;
    }

    /**
     * 移除当前英语题,并将该加入已读列表中
     *
     * @return
     */
    public CurrentAnswerProgress readEnglish() {
        this.eAlreadyList.add(eList.get(nowAProblemIndex));
        this.eList.remove(nowAProblemIndex);
        return this;
    }

    public CurrentAnswerProgress setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        return this;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public List<English> geteList() {
        return eList;
    }

    /**
     * 当前题错误次数+1
     *
     * @return
     */
    public List<English> englishError() {
        errorNumber++;
        eList.get(nowAProblemIndex).setErrorNumberIncrement();
        return eList;
    }

    public int getNowAProblemIndex() {
        return nowAProblemIndex;
    }

    /**
     * 进行下一道索引(随机)
     *
     * @return
     */
    public CurrentAnswerProgress randomReadNowAProblemIndex() {
        if (eList.size() > 0) {
            if (eList.size() == 1) {
                this.nowAProblemIndex = 0;
            } else {
                this.nowAProblemIndex = (int) Math.round(Math.random() *
                        (0 - (eList.size() - 1)) + (eList.size() - 1));
            }
        }
        return this;
    }

    public int getNowAProblemChineseIndex() {
        return nowAProblemChineseIndex;
    }

    /**
     * 进行下一道中文提示索引(随机)
     *
     * @return
     */
    public CurrentAnswerProgress randomReadNowAProblemChineseIndex() {
        if (eList.get(nowAProblemIndex).getChinese().size() > 0) {
            if (eList.get(nowAProblemIndex).getChinese().size() == 1) {
                this.nowAProblemChineseIndex = 0;
            } else {
                this.nowAProblemChineseIndex = (int) Math.round(Math.random() *
                        (0 - (eList.get(nowAProblemIndex).getChinese().size() - 1)) + (
                        eList.get(nowAProblemIndex).getChinese().size() - 1));
            }
        }
        return this;
    }

    public int getOnAProblemIndex() {
        return onAProblemIndex;
    }

    public CurrentAnswerProgress setOnAProblemIndex() {
        this.onAProblemIndex = this.nowAProblemIndex;
        return this;
    }

    public CurrentAnswerProgress setOnAProblemChineseIndex() {
        this.onAProblemChineseIndex = this.nowAProblemChineseIndex;
        return this;
    }

    public boolean isOnAProblemResult() {
        return onAProblemResult;
    }

    public CurrentAnswerProgress setOnAProblemResult(boolean onAProblemResult) {
        this.onAProblemResult = onAProblemResult;
        return this;
    }

    /**
     * 判断用户有没有回答正确
     *
     * @return
     */
    public boolean answerJudge() {
        if (userAnswer == null || userAnswer.equals("") || nowAProblemIndex < 0) {
            return false;
        }
        return userAnswer.equals(eList.get(nowAProblemIndex).getEnglish());
    }
}
