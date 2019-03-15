package cn.news.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Clb
 * @Date: 2019/3/15 10:06
 * @Description:
 */
public class CurrentAnswerProgressChoiceQuestion extends CurrentAnswerProgress {
    List<String> errorChoiceQuestion;
    List<String> nowChoiceQuestion;

    public List<String> getErrorChoiceQuestion() {
        return errorChoiceQuestion;
    }

    public CurrentAnswerProgressChoiceQuestion setErrorChoiceQuestion(List<String> errorChoiceQuestion) {
        this.errorChoiceQuestion = errorChoiceQuestion;
        return this;
    }

    public List<String> getNowChoiceQuestion() {
        return nowChoiceQuestion;
    }

    public CurrentAnswerProgressChoiceQuestion setNowChoiceQuestion(List<String> nowChoiceQuestion) {
        this.nowChoiceQuestion = nowChoiceQuestion;
        return this;
    }

    public void randomErrorChoiceQuestion(){
        nowChoiceQuestion =new LinkedList<>();
        int size=errorChoiceQuestion.size();
        int noe=(int)(Math.random()*(size/3));
        int two=(int)((size/3)+(Math.random()*(size/3)));
        int three=(int)((size/3*2)+(Math.random()*(size/3)));
        nowChoiceQuestion.add(errorChoiceQuestion.get(noe));
        nowChoiceQuestion.add(errorChoiceQuestion.get(two));
        nowChoiceQuestion.add(errorChoiceQuestion.get(three));

//        //移除当前英语题,并将该加入已读列表中
//        readEnglish();
//        //进行下一道索引(随机)
//        randomReadNowAProblemIndex();
//        //进行下一道中文提示索引(随机)
//        randomReadNowAProblemChineseIndex();

        String answer=geteList().get(getNowAProblemIndex()).getEnglish();
        int index=(int)(Math.random()*4);
        if (index==3){
            nowChoiceQuestion.add(answer);
        }else {
            String error = nowChoiceQuestion.get(index);
            nowChoiceQuestion.set(index, answer);
            nowChoiceQuestion.add(error);
        }
    }

    /**
     * 判断用户有没有回答正确
     *
     * @return
     */
    @Override
    public boolean answerJudge() {
        if (getUserAnswer() == null || getUserAnswer().equals("") || getNowAProblemIndex() < 0) {
            return false;
        }
        return nowChoiceQuestion.get(getUserAnswer().charAt(0)-97).equals(geteList().get(getNowAProblemIndex()).getEnglish());
    }

    public CurrentAnswerProgressChoiceQuestion(List<English> eList) {
        super(eList);
    }
}
