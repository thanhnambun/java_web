package com.example.bt2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "bt9", value = "/bt9")
public class bt9 extends HttpServlet {
    private final List<String> wordList = Arrays.asList(
            "java", "nam", "bun", "nguyen", "spring"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        Random rand = new Random();
        int index = rand.nextInt(wordList.size());
        String secretWord = wordList.get(index);
        String maskedWord = maskRandomLetter(secretWord);

        session.setAttribute("secretWord", secretWord);
        session.setAttribute("maskedWord", maskedWord);
        session.setAttribute("remaining", 6);
        session.setAttribute("status", "playing");

        req.setAttribute("masked", maskedWord);
        req.setAttribute("remaining", 6);
        req.setAttribute("message", "Hãy nhập từ bạn đoán!");

        RequestDispatcher rd = req.getRequestDispatcher("bt9.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String secretWord = (String) session.getAttribute("secretWord");
        String maskedWord = (String) session.getAttribute("maskedWord");
        int remaining = (int) session.getAttribute("remaining");
        String status = (String) session.getAttribute("status");

        String userGuess = req.getParameter("guess");

        String message;

        if (status.equals("win") || status.equals("lose")) {
            message = "Trò chơi đã kết thúc. Nhấn chơi lại!";
        } else if (userGuess == null || userGuess.trim().isEmpty()) {
            message = "Bạn chưa nhập từ!";
        } else if (userGuess.equalsIgnoreCase(secretWord)) {
            message = "Chúc mừng! Bạn đã đoán đúng!";
            status = "win";
            maskedWord = secretWord;
        } else {
            remaining--;
            if (remaining <= 0) {
                message = "Bạn đã thua! Từ đúng là: " + secretWord;
                status = "lose";
                maskedWord = secretWord;
            } else {
                message = "Sai rồi! Còn " + remaining + " lượt.";
            }
        }

        session.setAttribute("maskedWord", maskedWord);
        session.setAttribute("remaining", remaining);
        session.setAttribute("status", status);

        req.setAttribute("masked", maskedWord);
        req.setAttribute("remaining", remaining);
        req.setAttribute("message", message);

        RequestDispatcher rd = req.getRequestDispatcher("bt9.jsp");
        rd.forward(req, resp);
    }

    private String maskRandomLetter(String word) {
        Random rand = new Random();
        char[] chars = word.toCharArray();
        int index = rand.nextInt(chars.length);
        chars[index] = '_';
        return new String(chars);
    }
}
