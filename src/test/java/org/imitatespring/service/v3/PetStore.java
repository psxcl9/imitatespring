package org.imitatespring.service.v3;

import org.imitatespring.dao.v3.AccountDao;
import org.imitatespring.dao.v3.ItemDao;

public class PetStore {

    private AccountDao accountDao;

    private ItemDao itemDao;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
