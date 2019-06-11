package app.servlets;

import app.model.Skill;
import app.repository.SkillRepository;
import app.repository.implementations.SkillRepositoryImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SkillServlet extends javax.servlet.http.HttpServlet {
    private SkillRepository skillRepository =  new SkillRepositoryImplementation();
    private Skill skillToSave = new Skill();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listSkills(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("button");

        switch (input) {
            case "add":
                addSkill(req, resp);
                break;
            case "edit":
                editSkill(req, resp);
                break;
            case "update":
                updateSkill(req, resp);
                break;
            case "delete":
                deleteSkill(req, resp);
                break;
        }
    }

    private void listSkills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Skill> skillList = skillRepository.findAll();
        request.setAttribute("SkillsList", skillList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/skill.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addSkill(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String skillName = request.getParameter("SkillNameToSave");
        skillToSave.setName(skillName);
        skillRepository.save(skillToSave);
        response.sendRedirect("skill");
    }

    private void editSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editSkill.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skillId = request.getParameter("skillId");
        String newSkillName = request.getParameter("newSkillName");
        int skillIntId = Integer.parseInt(skillId);
        skillToSave.setId(skillIntId);
        skillToSave.setName(newSkillName);
        skillRepository.update(skillToSave);
        response.sendRedirect("skill");
    }

    private void deleteSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int skillIdToDelete = Integer.parseInt(request.getParameter("skillToDelete"));
        skillRepository.delete(skillIdToDelete);
        response.sendRedirect("skill");
    }
}
