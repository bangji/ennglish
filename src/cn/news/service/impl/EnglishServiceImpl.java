package cn.news.service.impl;

import cn.news.dao.EnglishDao;
import cn.news.dao.impl.BaseDbDao;
import cn.news.dao.impl.EnglishDaoImpl;
import cn.news.entity.CurrentAnswerProgress;
import cn.news.entity.CurrentAnswerProgressChoiceQuestion;
import cn.news.entity.English;
import cn.news.service.EnglishService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Clb
 * @Date: 2018/8/16 10:05
 * @Description:
 */
public class EnglishServiceImpl implements EnglishService {
    EnglishDao englishDao = new EnglishDaoImpl();

    @Override
    public List<English> getEnglishList(String path) {
        return new EnglishDaoImpl().eList(path);
    }

    @Override
    public CurrentAnswerProgress getJudge(CurrentAnswerProgress currentAnswerProgress) {
        return new EnglishDaoImpl().judge(currentAnswerProgress);
    }

    @Override
    public int getEnglishAdd(String english, String china) {
        try {
            if (!englishDao.jdbcQuery("select * from EnglishWord where text=?", new Object[]{english}).next()) {
                englishDao.jdbcAdd("insert EnglishWord values(null,?,0)", new Object[]{english});
            }
            //省略中文意思是否存在判断,和id线程安全以及dao类规范问题
            String id = null;
            ResultSet resultSet = englishDao.jdbcQuery("select * from EnglishWord where text=?", new Object[]{english});
            if (resultSet.next()) {
                id = resultSet.getString("id");
            } else {
                System.out.println("出错");
                return -1;
            }
            String[] chinas = china.split(" ");
            for (String c : chinas) {
                englishDao.jdbcAdd("insert chinameaning values(null,?,?)", new Object[]{c, id});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public CurrentAnswerProgress getJudgeChoiceQuestion(CurrentAnswerProgressChoiceQuestion currentAnswerProgressChoiceQuestion) {
        new EnglishDaoImpl().judge(currentAnswerProgressChoiceQuestion);
        currentAnswerProgressChoiceQuestion.randomErrorChoiceQuestion();
        return currentAnswerProgressChoiceQuestion;
    }

    @Override
    public List<String> englishErrorSelectionQuery() {
        ResultSet resultSet = englishDao.jdbcQuery("select id,text from englisherrorselection", null);
        List<String> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(resultSet.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDbDao.closeAll();
        }
        return result;
    }
}
