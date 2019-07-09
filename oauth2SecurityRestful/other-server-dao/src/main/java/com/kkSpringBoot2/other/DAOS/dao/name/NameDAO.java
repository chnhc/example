package com.kkSpringBoot2.other.DAOS.dao.name;

import com.kkSpringBoot2.other.DAOS.entity.name.Name;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository(value = "nameDAO")
public interface NameDAO {

    Name selectNameById();

}
