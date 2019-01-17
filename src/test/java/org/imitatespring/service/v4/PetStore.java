package org.imitatespring.service.v4;

import org.imitatespring.beans.factory.annotation.Autowired;
import org.imitatespring.dao.v3.AccountDao;
import org.imitatespring.dao.v3.ItemDao;
import org.imitatespring.stereotype.Component;

@Component(value = "petStore")
public class PetStore {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public PetStore(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
