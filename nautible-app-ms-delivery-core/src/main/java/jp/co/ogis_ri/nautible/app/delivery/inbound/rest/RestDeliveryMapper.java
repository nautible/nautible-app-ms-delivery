package jp.co.ogis_ri.nautible.app.delivery.inbound.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import jp.co.ogis_ri.nautible.app.delivery.config.QuarkusMappingConfig;
import jp.co.ogis_ri.nautible.app.delivery.domain.Delivery;
import jp.co.ogis_ri.nautible.app.delivery.api.rest.RestCreateDeliveryRequest;
import jp.co.ogis_ri.nautible.app.delivery.api.rest.RestCreateDeliveryResponse;
import jp.co.ogis_ri.nautible.app.delivery.api.rest.RestDelivery;
import jp.co.ogis_ri.nautible.app.delivery.api.rest.RestGetByDeliveryIdResponse;
import jp.co.ogis_ri.nautible.app.delivery.api.rest.RestUpdateDeliveryRequest;
import jp.co.ogis_ri.nautible.app.delivery.api.rest.RestUpdateDeliveryResponse;

/**
 * REST API の Reqesut/Response オブジェクトと Domain のマッピング定義
 */
@Mapper(config = QuarkusMappingConfig.class)
public interface RestDeliveryMapper {

    RestDeliveryMapper INSTANCE = Mappers.getMapper(RestDeliveryMapper.class);

    default RestCreateDeliveryResponse deliveryToRestDeliveryResponse(Delivery delivery){
        return new RestCreateDeliveryResponse().delivery(deliveryToRestDelivery(delivery));
    }

    default RestUpdateDeliveryResponse deliveryToRestUpdateDeliveryResponse(Delivery delivery){
        return new RestUpdateDeliveryResponse().delivery(deliveryToRestDelivery(delivery));
    }

    default RestGetByDeliveryIdResponse deliveryToRestGetByDeliveryIdResponse(Delivery delivery){
        return new RestGetByDeliveryIdResponse().delivery(deliveryToRestDelivery(delivery));
    }

    RestDelivery deliveryToRestDelivery(Delivery delivery);

    @Mapping(target = "deliveryNo", ignore = true)
    Delivery restCreateDeliveryToDelivery(RestCreateDeliveryRequest delivery);

    Delivery restUpdateDelieryToDelivery(RestUpdateDeliveryRequest delivery);
}
