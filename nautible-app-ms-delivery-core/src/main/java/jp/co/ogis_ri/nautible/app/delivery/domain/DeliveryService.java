package jp.co.ogis_ri.nautible.app.delivery.domain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class DeliveryService {

    Logger LOG = Logger.getLogger(DeliveryService.class.getName());

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

    /**
     * 「発送完了」状態の配送を、注文に通知し、配送の状態を「配送完了」にする
     */
    public void confirmShipping() {

        List<Delivery> deliveryList =
                deliveryRepository.get().findByDeliveryStatus(DeliveryStatus.SHIPMENT_COMPLETE);

        deliveryList.stream().forEach(d -> {
            // TODO 注文に通知
            LOG.log(Level.INFO, "change status : " + d.getDeliveryNo());
            deliveryRepository.get().update(d.status(DeliveryStatus.DELIVERY_COMPLETE));
        });
        
    }

}
