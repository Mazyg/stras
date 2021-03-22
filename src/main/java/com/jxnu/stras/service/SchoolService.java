package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.School;

public interface SchoolService extends IService<School> {

    public School findSchool();
}
