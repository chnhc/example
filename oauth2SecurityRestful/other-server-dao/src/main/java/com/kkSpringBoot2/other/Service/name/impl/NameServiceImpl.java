package com.kkSpringBoot2.other.Service.name.impl;

import com.kkSpringBoot2.other.DAOS.dao.name.NameDAO;
import com.kkSpringBoot2.other.DAOS.entity.name.Name;
import com.kkSpringBoot2.other.Service.name.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * author: ckk
 * create: 2019-06-29 11:18
 */

@Service(value = "nameService")
public class NameServiceImpl implements NameService {

    @Autowired
    NameDAO nameDAO;

    @Override
    public Name selectNameById() {
        return nameDAO.selectNameById();
    }
}
