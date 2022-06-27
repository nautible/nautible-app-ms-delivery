package jp.co.ogis_ri.nautible.app.delivery.domain;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class DeliveryService {

    @Inject
    Instance<DeliveryRepository> deliveryRepository;

    /**
     * 配送データを永続化する
     * 
     * @param delivery 配送
     * @return {@link Delivery}
     */
    public Delivery create(Delivery delivery) {
        delivery.setDeliveryStatus(DeliveryStatus.REQUEST_DELIVERY);
        delivery = deliveryRepository.get().create(delivery);
        return delivery;
    }

    /**
     * 配送を取得する
     * 
     * @param deliveryNo 配送No
     * @return {@link Delivery}
     */
    public Delivery getByDeliveryNo(String deliveryNo) {
        return deliveryRepository.get().getByDeliveryNo(deliveryNo);
    }

    /**
     * 配送を更新する
     * 
     * @param delivery 配送
     * @return {@link Delivery}
     */
    public Delivery update(Delivery delivery) {
        return deliveryRepository.get().update(delivery);
    }

    /**
     * 配送を削除する
     * 
     * @param deliveryNo 配送No
     */
    public void deleteByDeliveryNo(String deliveryNo) {
        deliveryRepository.get().delete(deliveryNo);
    }

    public void confirmShipping(){

        List<Delivery> deliveryList = deliveryRepository.get().findByDeliveryStatus(DeliveryStatus.SHIPMENT_COMPLETE);
        // TODO 更新処理
        // 取得結果
        deliveryList.stream().forEach(System.out::println);
    }

}
