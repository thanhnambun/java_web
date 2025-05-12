package Controller;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Random;

@WebServlet(name = "PlayMiniGameServlet", value = "/PlayMiniGameServlet")
public class PlayMiniGameServlet extends HttpServlet {
    private final String[] choices = {"Búa", "Kéo", "Lá"};

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("/views/B5/B5.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading page");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int selectedChoice =Integer.parseInt(request.getParameter("playerChoice"));
        String playerChoice = choices[selectedChoice-1];

        if (playerChoice == null) {
            playerChoice = "Chưa chọn";
            request.setAttribute("playerChoice", playerChoice);
            request.setAttribute("computerChoice", "Chưa có");
            request.setAttribute("result", "Vui lòng chọn một tùy chọn hợp lệ");
        } else {

            String computerChoice = choices[new Random().nextInt(choices.length)];

            String result = determineResult(playerChoice, computerChoice);

            request.setAttribute("playerChoice", playerChoice);
            request.setAttribute("computerChoice", computerChoice);
            request.setAttribute("result", result);
        }

        try {
            request.getRequestDispatcher("/views/B5/B5.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }



    private String determineResult(String player, String computer) {
        if (player.equals(computer)) {
            return "Hòa";
        }
        if ((player.equals("Búa") && computer.equals("Kéo")) ||
                (player.equals("Kéo") && computer.equals("Lá")) ||
                (player.equals("Lá") && computer.equals("Búa"))) {
            return "Bạn thắng!";
        }
        return "Máy thắng!";
    }

}