package cn.news.dao;

import cn.news.entity.CurrentAnswerProgress;
import cn.news.entity.English;

import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: Clb
 * @Date: 2018/8/14 17:10
 * @Description:
 */
public interface EnglishDao {
    List<English> eList(String path);
    CurrentAnswerProgress judge(CurrentAnswerProgress currentAnswerProgress);
    ResultSet jdbcQuery(String sql,Object[]objects);
    int jdbcAdd(String sql,Object[]objects);
}
