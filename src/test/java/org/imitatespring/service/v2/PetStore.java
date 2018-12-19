package org.imitatespring.service.v2;

import org.imitatespring.dao.v2.AccountDao;
import org.imitatespring.dao.v2.ItemDao;

public class PetStore {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private String author;

    private int age;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
