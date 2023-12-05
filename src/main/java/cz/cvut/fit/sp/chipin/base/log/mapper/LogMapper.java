package cz.cvut.fit.sp.chipin.base.log.mapper;

import cz.cvut.fit.sp.chipin.base.log.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogMapper {
    @Mapping(source = "userAccount.userEntity.id", target = "userId")
    @Mapping(source = "userAccount.userEntity.firstName", target = "userName")
    LogReadLogResponse entityToReadLogResponse(Log log);
}
