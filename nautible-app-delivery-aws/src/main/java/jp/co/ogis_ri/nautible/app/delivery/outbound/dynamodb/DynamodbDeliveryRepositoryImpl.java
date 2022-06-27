package jp.co.ogis_ri.nautible.app.delivery.outbound.dynamodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jp.co.ogis_ri.nautible.app.delivery.domain.Delivery;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryRepository;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryStatus;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

@Named("DynamodbDeliveryRepositoryImpl")
@ApplicationScoped
public class DynamodbDeliveryRepositoryImpl implements DeliveryRepository {

    /** 配送テーブル名 */
    private static final String DELIVERY_TABLE_NAME = "Delivery";

    @Inject
    DynamoDbClient dynamoDB;
    @Inject
    DynamodbDeliveryMapper dynamodbDeliveryMapper;

    @Override
    public Delivery getByDeliveryNo(String deliveryNo) {
        Key key = Key.builder().partitionValue(deliveryNo).build();
        DynamodbDelivery result =
                getTable(DELIVERY_TABLE_NAME, DynamodbDelivery.class).getItem(r -> r.key(key));
        return dynamodbDeliveryMapper.dynamodbDeliveryToDelivery(result);
    }

    @Override
    public Delivery create(Delivery delivery) {
        int sequence = getSequenceNumber(DELIVERY_TABLE_NAME);
        delivery.setDeliveryNo("D" + String.format("%010d", sequence));
        getTable(DELIVERY_TABLE_NAME, DynamodbDelivery.class)
                .putItem(dynamodbDeliveryMapper.deliveryToDynamodbDelivery(delivery));
        return delivery;
    }

    @Override
    public void delete(String deliveryNo) {
        Key key = Key.builder().partitionValue(deliveryNo).build();
        getTable(DELIVERY_TABLE_NAME, DynamodbDelivery.class).deleteItem(key);
    }

    @Override
    public Delivery update(Delivery delivery) {
        getTable(DELIVERY_TABLE_NAME, DynamodbDelivery.class)
                .updateItem(dynamodbDeliveryMapper.deliveryToDynamodbDelivery(delivery));
        return delivery;
    }

    @Override
    public List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus) {
        // full scan して該当するものを選ぶ

        return dynamodbDeliveryMapper
                .dynamodbDeliveryToDelivery(getTable(DELIVERY_TABLE_NAME, DynamodbDelivery.class)
                        .scan().items().stream().filter(deliveryItem -> {
                            if (deliveryStatus != null && deliveryStatus.equals(
                                    dynamodbDeliveryMapper.dynamodbDeliveryStatusToDeliveryStatus(deliveryItem.getDeliveryStatus())) == false) {
                                return false;
                            }
                            return true;
                        }).collect(Collectors.toList()));
    }

    /**
     * DynamoDbのアトミックカウンタを利用してシーケンスを発番する
     * 
     * @param tableName テーブル名
     * @return シーケンス
     */
    private int getSequenceNumber(String tableName) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("Name", AttributeValue.builder().s(tableName).build());
        Map<String, AttributeValueUpdate> update = new HashMap<>();
        update.put("SequenceNumber",
                AttributeValueUpdate.builder().value(AttributeValue.builder().n("1").build())
                        .action(AttributeAction.ADD).build());
        UpdateItemRequest updateRequest = UpdateItemRequest.builder().tableName("Sequence").key(key)
                .attributeUpdates(update).returnValues(ReturnValue.UPDATED_NEW).build();
        UpdateItemResponse updateResponse = dynamoDB.updateItem(updateRequest);
        return Integer.parseInt(updateResponse.attributes().get("SequenceNumber").n());
    }

    /**
     * DynamoDbTableを取得する
     * 
     * @param <E> Mappingする型
     * @param tableName テーブル名
     * @param type Mappingする型
     * @return {@link DynamoDbTable}
     */
    private <E> DynamoDbTable<E> getTable(String tableName, Class<E> type) {
        DynamoDbEnhancedClient enhancedClient =
                DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDB).build();
        DynamoDbTable<E> mappedTable = enhancedClient.table(tableName, TableSchema.fromBean(type));
        return mappedTable;
    }
}
