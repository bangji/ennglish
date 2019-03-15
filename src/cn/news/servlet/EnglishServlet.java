package cn.news.servlet;

import cn.news.entity.CurrentAnswerProgress;
import cn.news.entity.CurrentAnswerProgressChoiceQuestion;
import cn.news.entity.English;
import cn.news.service.impl.EnglishServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @Author: Clb
 * @Date: 2018/8/16 10:13
 * @Description:
 */
public class EnglishServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //out.print("<script>alert('编码成功');</script>");
        switch (request.getParameter("action").toString()) {
            case "fileIO":
                fileIOChoiceQuestion(request,response);
                break;
            case "englishSwitch":
                englishSwitchChoiceQuestion(request,response);
                break;
            case "EnglishAdd":
                addEnglish(request,response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    private void fileIO(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String fileName = null;
        String uploadFilePath = request.getSession().getServletContext().getRealPath("\\WEB-INF\\englishFile");
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                Iterator<FileItem> iter = upload.parseRequest(request).iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        fileName = item.getName();
                        if (fileName != null && !fileName.equals("")) {
                            item.write(new File(uploadFilePath + "\\" + fileName));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileName == null || fileName.indexOf(".txt") < 0) {
            response.sendRedirect("index.jsp");
        } else {
            CurrentAnswerProgress currentAnswerProgress = new CurrentAnswerProgress(new EnglishServiceImpl().
                    getEnglishList(uploadFilePath + "\\" + fileName));
            new EnglishServiceImpl().getJudge(currentAnswerProgress);
            request.getSession().setAttribute("cap", currentAnswerProgress);
            request.getRequestDispatcher("/EnglishExercise.jsp").forward(request, response);
        }
    }

    public void englishSwitch(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        CurrentAnswerProgress currentAnswerProgress=(CurrentAnswerProgress)
                request.getSession().getAttribute("cap");
        currentAnswerProgress.setUserAnswer(request.getParameter("answer"));
        new EnglishServiceImpl().getJudge(currentAnswerProgress);
        request.setAttribute("cap", currentAnswerProgress);
        if (currentAnswerProgress.geteList().size()>0) {
            request.getRequestDispatcher("/EnglishExercise.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/EnglishExerciseResult.jsp").forward(request, response);
        }
    }

    public void addEnglish(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException{
        String english=request.getParameter("english");
        String chinas=request.getParameter("china");
        new EnglishServiceImpl().getEnglishAdd(english,chinas);
    }

    private void fileIOChoiceQuestion(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String fileName = null;
        String uploadFilePath = request.getSession().getServletContext().getRealPath("\\WEB-INF\\englishFile");
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                Iterator<FileItem> iter = upload.parseRequest(request).iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        fileName = item.getName();
                        if (fileName != null && !fileName.equals("")) {
                            item.write(new File(uploadFilePath + "\\" + fileName));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileName == null || fileName.indexOf(".txt") < 0) {
            response.sendRedirect("index.jsp");
        } else {
            CurrentAnswerProgressChoiceQuestion currentAnswerProgress = new CurrentAnswerProgressChoiceQuestion(new EnglishServiceImpl().
                    getEnglishList(uploadFilePath + "\\" + fileName));
            new EnglishServiceImpl().getJudge(currentAnswerProgress);
            currentAnswerProgress.setErrorChoiceQuestion(new EnglishServiceImpl().englishErrorSelectionQuery());
            currentAnswerProgress.randomErrorChoiceQuestion();
            request.getSession().setAttribute("cap", currentAnswerProgress);
            request.getRequestDispatcher("/EnglishExercise2.jsp").forward(request, response);
        }
    }

    public void englishSwitchChoiceQuestion(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        CurrentAnswerProgressChoiceQuestion currentAnswerProgress=(CurrentAnswerProgressChoiceQuestion)
                request.getSession().getAttribute("cap");
        currentAnswerProgress.setUserAnswer(request.getParameter("answer"));
        new EnglishServiceImpl().getJudge(currentAnswerProgress);
        currentAnswerProgress.randomErrorChoiceQuestion();
        request.setAttribute("cap", currentAnswerProgress);
        if (currentAnswerProgress.geteList().size()>0) {
            request.getRequestDispatcher("/EnglishExercise2.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/EnglishExerciseResult.jsp").forward(request, response);
        }
    }
}
