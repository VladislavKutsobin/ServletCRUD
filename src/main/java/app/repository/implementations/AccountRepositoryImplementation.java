package app.repository.implementations;

import app.model.Account;
import app.model.AccountStatus;
import app.repository.AccountRepository;
import app.util.ConnectionUtil;
import org.hibernate.Session;

import java.util.List;

public class AccountRepositoryImplementation implements AccountRepository {
    @Override
    public void save(Account account) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Account> findAll() {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        String hql = "from Account";
        List<Account> accountList = session.createQuery(hql, Account.class).getResultList();
        session.close();
        return accountList;
    }

    @Override
    public void update(Account account) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        int id = account.getId();
        String newAccountName = account.getAccountName();
        AccountStatus newAccountStatus = account.getAccountStatus();
        session.beginTransaction();
        Account accountToUpdate = session.get(Account.class, id);
        accountToUpdate.setAccountName(newAccountName);
        accountToUpdate.setAccountStatus(newAccountStatus);
        session.update(accountToUpdate);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer integer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Account accountToDelete = session.get(Account.class, integer);
        session.delete(accountToDelete);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Account getById(Integer integer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        Account accountToShow = session.get(Account.class, integer);
        session.close();
        return accountToShow;
    }
}
