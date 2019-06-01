package app.servlets;

import app.model.Account;
import app.model.AccountStatus;
import app.model.Developer;
import app.repository.AccountRepository;
import app.repository.DeveloperRepository;
import app.repository.implementations.AccountRepositoryImplementation;
import app.repository.implementations.DeveloperRepositoryImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends javax.servlet.http.HttpServlet {
    private DeveloperRepository developerRepository = new DeveloperRepositoryImplementation();
    private AccountRepository accountRepository = new AccountRepositoryImplementation();
    private Account accountToSave = new Account();
       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void listAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Account> accountList = accountRepository.findAll();
        request.setAttribute("accountsList", accountList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("app/view/account.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNameToSave = request.getParameter("accountNameToSave");
        String stringAccountStatusToSave = request.getParameter("stringAccountStatusToSave");
        AccountStatus accountStatusToSave = AccountStatus.valueOf(stringAccountStatusToSave);
        int developerId = Integer.parseInt(request.getParameter("developerIdToAccount"));
        Developer developerToSave = developerRepository.getById(developerId);

        accountToSave.setAccountName(accountNameToSave);
        accountToSave.setAccountStatus(accountStatusToSave);
        accountToSave.setDeveloper(developerToSave);
        accountRepository.save(accountToSave);
        response.sendRedirect("account");
    }

    private void editAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("app/view/editAccountForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountToUpdateId = Integer.parseInt(request.getParameter("accountToUpdateId"));
        String accountNameToUpdate = request.getParameter("accountNameToUpdate");
        AccountStatus accountStatusToUpdate = AccountStatus.valueOf(request.getParameter("accountStatusToUpdate"));
        int developerToUpdateId = Integer.parseInt(request.getParameter("developerToUpdateId"));
        Developer developerToSave = developerRepository.getById(developerToUpdateId);


        accountToSave.setId(accountToUpdateId);
        accountToSave.setAccountName(accountNameToUpdate);
        accountToSave.setAccountStatus(accountStatusToUpdate);
        accountToSave.setDeveloper(developerToSave);
        accountRepository.update(accountToSave);
        response.sendRedirect("account");
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           int accountIdToDelete = Integer.parseInt(request.getParameter("accountIdToDelete"));
           accountRepository.delete(accountIdToDelete);
           response.sendRedirect("account");
    }


}
