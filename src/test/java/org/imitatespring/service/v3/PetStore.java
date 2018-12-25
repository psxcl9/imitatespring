package org.imitatespring.service.v3;

import org.imitatespring.dao.v3.AccountDao;
import org.imitatespring.dao.v3.ItemDao;

public class PetStore {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private int age;

    public PetStore(AccountDao accountDao, ItemDao itemDao, int age) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.age = age;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public int getAge() {
        return age;
    }
}
