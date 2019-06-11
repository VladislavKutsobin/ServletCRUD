package app.servlets;

import app.model.Developer;
import app.model.Skill;
import app.repository.DeveloperRepository;
import app.repository.SkillRepository;
import app.repository.implementations.DeveloperRepositoryImplementation;
import app.repository.implementations.SkillRepositoryImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperServlet extends javax.servlet.http.HttpServlet {
    private DeveloperRepository developerRepository = new DeveloperRepositoryImplementation();
    private Developer developerToSave = new Developer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listDevelopers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("button");

        switch (input) {
            case "add":
                addDeveloper(req, resp);
                break;
            case "edit":
                editDeveloper(req, resp);
                break;
            case "update":
                updateDeveloper(req, resp);
                break;
            case "delete":
                deleteDeveloper(req, resp);
                break;
        }
    }

    private void listDevelopers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Developer> developerList = developerRepository.findAll();
        request.setAttribute("developersList", developerList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/developer.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SkillRepository skillRepository = new SkillRepositoryImplementation();
        String firstNameToAdd = request.getParameter("firstName");
        String lastNameToAdd = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");
        Set<Skill> skillSet = new HashSet<>();

        String[] idSkillList = request.getParameterValues("names");
        Set<String> setWithId = new HashSet<>(Arrays.asList(idSkillList));
        Set<Integer> intSetId = new HashSet<>();
        for(String id : setWithId) {
            intSetId.add(Integer.parseInt(id));
        }

        for(int skillid : intSetId) {
            Skill skillToAdd = skillRepository.getById(skillid);
            skillSet.add(skillToAdd);
        }

        developerToSave.setFirstName(firstNameToAdd);
        developerToSave.setLastName(lastNameToAdd);
        developerToSave.setSpecialty(specialty);
        developerToSave.setSkills(skillSet);
        developerRepository.save(developerToSave);
        response.sendRedirect("developer");
    }

    private void updateDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("developerId"));
        String newFirstName = request.getParameter("firstName");
        String newLastName = request.getParameter("lasName");
        String newSpecialty = request.getParameter("specialty");

        developerToSave.setId(id);
        developerToSave.setFirstName(newFirstName);
        developerToSave.setLastName(newLastName);
        developerToSave.setSpecialty(newSpecialty);
        developerRepository.update(developerToSave);
        response.sendRedirect("developer");
    }

    private void editDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editDeveloper.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("developerId"));
        developerRepository.delete(id);
        response.sendRedirect("developer");
    }
}
