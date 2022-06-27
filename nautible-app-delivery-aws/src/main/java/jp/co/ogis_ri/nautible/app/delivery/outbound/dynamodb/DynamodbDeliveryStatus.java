package jp.co.ogis_ri.nautible.app.delivery.outbound.dynamodb;

/**
 * 配送ステータス
 */
public enum DynamodbDeliveryStatus {
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
