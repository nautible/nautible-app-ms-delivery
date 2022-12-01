package jp.co.ogis_ri.nautible.app.delivery.outbound.cosmosdb;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import jp.co.ogis_ri.nautible.app.delivery.config.QuarkusMappingConfig;
import jp.co.ogis_ri.nautible.app.delivery.domain.Delivery;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryStatus;

/**
 * CosmosdbのEntityとDomainのマッピング定義
 */
@Mapper(config = QuarkusMappingConfig.class)
public interface CosmosdbDeliveryMapper {
    CosmosdbDeliveryMapper INSTANCE = Mappers.getMapper(CosmosdbDeliveryMapper.class);

    @Mapping(target = "id", expression = "java(Integer.parseInt(delivery.getDeliveryNo().substring(1)))")
    CosmosdbDelivery deliveryToCosmosdbDelivery(Delivery delivery);

    Delivery cosmosdbDeliveryToDelivery(CosmosdbDelivery delivery);

    DeliveryStatus cosmosdbDeliveryStatusToDeliveryStatus(CosmosdbDeliveryStatus status);

    List<Delivery> cosmosdbDeliveryToDelivery(List<CosmosdbDelivery> delivery);
}
