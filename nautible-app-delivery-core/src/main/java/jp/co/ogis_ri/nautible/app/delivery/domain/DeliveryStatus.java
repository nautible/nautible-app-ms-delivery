package jp.co.ogis_ri.nautible.app.delivery.domain;

/**
 * 配送ステータス
 */
public enum DeliveryStatus {
    /** 配送依頼 */
    REQUEST_DELIVERY,
    /** 配送準備中 */
    DELIVERY_IN_PREPARATION,
    /** 配送中 */
    IN_DELIVERY,
    /** 配送完了 */
    DELIVERY_COMPLETE,
    /** キャンセル */
    CANCEL;
}
