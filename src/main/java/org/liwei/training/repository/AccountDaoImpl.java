/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 04/2019
 */

package org.liwei.training.repository;

import org.liwei.training.model.Account;
import org.liwei.training.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.liwei.training.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void save(Account account) {
        Transaction transaction = null;

        try  {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The account %s was inserted into the table.", account.toString()));
    }

    public List<Account> getAccounts() {
        String hql = "FROM Account";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery(hql);
            return query.list();
        }
    }

    public Account getAccountById(int id) {
        String hql = "FROM Account as act join fetch act.employee where act.id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery(hql);
            query.setParameter("id", id);

            return query.uniqueResult();
        }
    }
}