package cn.news.service;

import cn.news.entity.CurrentAnswerProgress;
import cn.news.entity.CurrentAnswerProgressChoiceQuestion;
import cn.news.entity.English;

import java.util.List;

/**
 * @Author: Clb
 * @Date: 2018/8/16 10:02
 * @Description:
 */
public interface EnglishService {
    /**
     * 根据文件地址查找文件,并且将内容转换为英语列表
     * @param path 文件地址
     * @return 英语列表
     */
    List<English> getEnglishList(String path);

    /**
     * 答题判定并且随机给出下一道题
     * @param currentAnswerProgress 当前答题进度列表
     * @return 当前答题进度列表
     */
    CurrentAnswerProgress getJudge(CurrentAnswerProgress currentAnswerProgress);

    /**
     * 添加英语单词
     * @param english
     * @param china
     * @return
     */
    int getEnglishAdd(String english,String china);

    /**
     * 答题判定并且随机给出下一道题(选择题)
     * @param currentAnswerProgressChoiceQuestion 当前答题进度列表
     * @return 当前答题进度列表
     */
    CurrentAnswerProgress getJudgeChoiceQuestion(CurrentAnswerProgressChoiceQuestion currentAnswerProgressChoiceQuestion);

    /**
     * 查询错误选择项
     * @return
     */
    List<String> englishErrorSelectionQuery();
}
