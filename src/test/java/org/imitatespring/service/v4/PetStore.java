package org.imitatespring.service.v4;

import org.imitatespring.beans.factory.annotation.Autowired;
import org.imitatespring.dao.v4.AccountDao;
import org.imitatespring.dao.v4.ItemDao;
import org.imitatespring.stereotype.Component;

@Component(value = "petStore")
public class PetStore {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
