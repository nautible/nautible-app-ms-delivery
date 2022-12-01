package jp.co.ogis_ri.nautible.app.delivery.outbound.cosmosdb;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jp.co.ogis_ri.nautible.app.delivery.domain.Delivery;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryRepository;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryStatus;

@Named("CosmosdbDeliveryRepositoryImpl")
@ApplicationScoped
public class CosmosdbDeliveryRepositoryImpl
        implements DeliveryRepository, PanacheMongoRepositoryBase<CosmosdbDelivery, Integer> {

    private static final String DELIVERY_TABLE_NAME = "Delivery";

    @Inject
    MongoClient mongoClient;
    @Inject
    CosmosdbDeliveryMapper cosmosdbDeliveryMapper;

    @Override
    public Delivery getByDeliveryNo(String deliveryNo) {
        return cosmosdbDeliveryMapper.cosmosdbDeliveryToDelivery(
                (CosmosdbDelivery) find("DeliveryNo", deliveryNo).firstResult());
    }

    @Override
    public Delivery create(Delivery delivery) {
        int sequence = getSequenceNumber(DELIVERY_TABLE_NAME);
        delivery.setDeliveryNo("D" + String.format("%010d", sequence));
        persist(cosmosdbDeliveryMapper.deliveryToCosmosdbDelivery(delivery));
        return delivery;
    }

    @Override
    public void delete(String deliveryNo) {
        delete("DeliveryNo", deliveryNo);
    }

    @Override
    public Delivery update(Delivery delivery) {
        update(cosmosdbDeliveryMapper.deliveryToCosmosdbDelivery(delivery));
        return delivery;
    }

    @Override
    public List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus) {
        return cosmosdbDeliveryMapper
                .cosmosdbDeliveryToDelivery(find("DeliveryStatus", deliveryStatus).list());
    }

    /**
     * Mongodbのfunctionでシーケンスを発番する。
     *
     * @param tableName テーブル名
     * @return シーケンス
     */
    private int getSequenceNumber(String tableName) {
        // 本来はマイクロサービスの管理単位を跨ぐような（データベースを跨ぐような）DBアクセスは禁止。
        // サービス毎にシーケンスCollectionを作成するとCosmosdbのコストが高くなる、また作業簡略化のためCommonDBへのアクセスを行う。
        // 時間ができたら共通サービスを作成して発番機能を作る。
        Document result = mongoClient.getDatabase("Common").getCollection("Sequence")
                .findOneAndUpdate(Filters.eq("_id", tableName),
                        new Document("$inc", new Document("SequenceNumber", 1)),
                        new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER));
        return result.getInteger("SequenceNumber");
    }
}
