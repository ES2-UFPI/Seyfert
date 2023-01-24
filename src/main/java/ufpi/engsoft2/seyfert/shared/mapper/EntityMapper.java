package ufpi.engsoft2.seyfert.shared.mapper;

import java.util.List;

public interface EntityMapper<Dto, Entity, Form> {
    Entity toModel(Form form); 
    Dto    toDto(Entity entity);
    Form   toForm(Entity entity);

    List<Entity> toModel(List<Dto> dtoList);
    List<Dto> toDto(List<Entity> entityList);
    List<Form> toForm(List<Entity> entityList);
    
}