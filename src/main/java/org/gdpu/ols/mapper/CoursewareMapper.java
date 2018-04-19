package org.gdpu.ols.mapper;

import org.apache.ibatis.annotations.Param;
import org.gdpu.ols.model.Courseware;

public interface CoursewareMapper extends org.gdpu.ols.core.Mapper<Courseware> {
    int addSingleCourseware(Courseware courseware);
    int updateCourseware(@Param("status")String status, @Param("id") Integer id,@Param("examiner") int examiner);
}
