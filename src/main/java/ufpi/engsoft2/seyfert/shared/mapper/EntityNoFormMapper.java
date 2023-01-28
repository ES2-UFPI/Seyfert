package ufpi.engsoft2.seyfert.shared.mapper;

import java.text.Normalizer.Form;
import java.util.List;

public interface EntityNoFormMapper<Dto, Entity> {
    Entity toModel(Dto form); 
    Dto    toDto(Entity entity);

    List<Entity> toModel(List<Dto> dtoList);
    List<Dto> toDto(List<Entity> entityList);
}