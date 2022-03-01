package com.SimpleOrderManagementApp.OrderManagement.mapper;

import com.SimpleOrderManagementApp.OrderManagement.domain.OrderQuantity;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderQuantityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OrderQuantityMapper {
    @Mapping(target="itemDto",source="item")
    OrderQuantityDto toOrderQuantityDto(OrderQuantity orderQuantity);

    @Mapping(target="item",source="itemDto")
    OrderQuantity toOrderQuantityEntity(OrderQuantityDto orderQuantityDto);

    @Mapping(target="itemDto",source="item")
    List<OrderQuantityDto> toOrderQuantitDtos(List<OrderQuantity> orderQuantity);

    @Mapping(target="item",source="itemDto")
    List<OrderQuantity> toOrderQuantityEntities(List<OrderQuantityDto> orderQuantityDto);
}
