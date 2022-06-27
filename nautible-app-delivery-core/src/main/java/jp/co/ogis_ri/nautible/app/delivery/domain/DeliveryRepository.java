package jp.co.ogis_ri.nautible.app.delivery.domain;

public interface DeliveryRepository {

    /** 
     * 配送を取得する
     * @param deliveryNo 配送No
     * @return {@link Delivery}
     */
    Delivery getByDeliveryNo(String deliveryNo);

    /**
     * 配送を作成する
     * @param delivery 配送
     * @return {@link Delivery}
     */
    Delivery create(Delivery delivery);
    
    /**
     * 配送を削除する
     * @param deliveryNo 配送No
     */
    void delete(String deliveryNo);

    /**
     * 配送を更新する
     * @param delivery 配送
     * @return {@link Delivery}
     */
    Delivery update(Delivery delivery);
    
    /**
     * 配送を配送状況を指定して取得する。
     * @param deliveryStatus
     * @return
     */
    Delivery findByDeliveryStatus(DeliveryStatus deliveryStatus);

}
