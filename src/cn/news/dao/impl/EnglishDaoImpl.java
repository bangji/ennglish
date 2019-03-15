package cn.news.dao.impl;

import cn.news.dao.EnglishDao;
import cn.news.entity.CurrentAnswerProgress;
import cn.news.entity.English;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Clb
 * @Date: 2018/8/14 17:19
 * @Description:
 */
public class EnglishDaoImpl implements EnglishDao {
    @Override
    public CurrentAnswerProgress judge(CurrentAnswerProgress currentAnswerProgress) {
        if (currentAnswerProgress.getNowAProblemIndex() >= 0 &&
                currentAnswerProgress.getNowAProblemChineseIndex() >= 0) {
            if (currentAnswerProgress.answerJudge()) {
                currentAnswerProgress.setOnAProblemResult(true);
                currentAnswerProgress.readEnglish();
            } else {
                currentAnswerProgress.setOnAProblemResult(false);
                currentAnswerProgress.englishError();
            }
            currentAnswerProgress.setOnAProblemIndex();
            currentAnswerProgress.setOnAProblemChineseIndex();
        }
        if (currentAnswerProgress.geteList().size() > 0) {
            currentAnswerProgress.randomReadNowAProblemIndex();
            currentAnswerProgress.randomReadNowAProblemChineseIndex();
        }
        return currentAnswerProgress;
    }

    @Override
    public List<English> eList(String path) {
        String line = null;
        List<English> result = new LinkedList<>();
        try {
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(path), "utf-8");
            //FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] e = line.split(" ");
                if (e.length < 2) {
                    continue;
                }
                English english = new English();
                english.setEnglish(e[0]);
                for (int i = 1; i < e.length; i++) {
                    english.setChinese(e[i]);
                }
                result.add(english);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultSet jdbcQuery(String sql, Object[] objects) {
        return BaseDbDao.getQuery(sql,objects);
    }

    @Override
    public int jdbcAdd(String sql, Object[] objects) {
        return BaseDbDao.getResult(sql,objects);
    }

}
