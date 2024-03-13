package web.securityspring.mapper;

public interface Mapper <F, T>{
    T map (F baseType);
}
