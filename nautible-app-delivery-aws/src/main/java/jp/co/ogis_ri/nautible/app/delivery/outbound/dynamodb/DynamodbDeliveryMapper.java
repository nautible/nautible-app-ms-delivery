package jp.co.ogis_ri.nautible.app.delivery.outbound.dynamodb;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import jp.co.ogis_ri.nautible.app.delivery.config.QuarkusMappingConfig;
import jp.co.ogis_ri.nautible.app.delivery.domain.Delivery;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryStatus;

/**
 * Dynamodb の Entity と Domain のマッピング定義
 */
@Mapper(config = QuarkusMappingConfig.class)
public interface DynamodbDeliveryMapper {

    DynamodbDeliveryMapper INSTANCE = Mappers.getMapper(DynamodbDeliveryMapper.class);

    DynamodbDelivery deliveryToDynamodbDelivery(Delivery delivery);

    Delivery dynamodbDeliveryToDelivery(DynamodbDelivery delivery);
    
    DeliveryStatus dynamodbDeliveryStatusToDeliveryStatus(DynamodbDeliveryStatus status);
    
    // 要る？
    List<Delivery> dynamodbDeliveryToDelivery(List<DynamodbDelivery> delivery);
    
}
