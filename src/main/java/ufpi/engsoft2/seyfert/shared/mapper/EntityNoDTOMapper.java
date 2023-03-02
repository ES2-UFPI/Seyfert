package main.java.ufpi.engsoft2.seyfert.shared.mapper;

import java.util.List;

public interface EntityNoDTOMapper<Entity, Form> {
    Entity toModel(Form form);
    Form   toForm(Entity entity);

    List<Entity> toModel(List<Dto> dtoList);
    List<Form> toForm(List<Entity> entityList);
    
}