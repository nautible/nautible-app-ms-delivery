package jp.co.ogis_ri.nautible.app.delivery.outbound.cosmosdb;

import java.time.LocalDate;
import java.util.Objects;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * 配送ドメイン
 */
@MongoEntity(collection = "Delivery")
public class CosmosdbDelivery {
    /** id */
    @BsonId // メソッドに定義すると有効にならないAPIがある。
    private Integer id = null;
    /** 配送番号 */
    @BsonProperty("DeliveryNo")
    private String deliveryNo;
    /** 配送予定日 */
    @BsonProperty("DeliveryPlanDate")
    private LocalDate deliveryPlanDate;
    /** 配送金額 */
    @BsonProperty("DeliveryPrice")
    private Integer deliveryPrice;
    /** 配送先宛名 */
    @BsonProperty("DeliveryName")
    private String deliveryName;
    /** 配送先郵便番号 */
    @BsonProperty("DeliveryPostCode")
    private String deliveryPostCode;
    /** 配送先住所1 */
    @BsonProperty("DeliveryAddress1")
    private String deliveryAddress1;
    /** 配送先住所2 */
    @BsonProperty("DeliveryAddress2")
    private String deliveryAddress2;
    /** 配送先電話番号 */
    @BsonProperty("DeliveryTel")
    private String deliveryTel;
    /** 配送状況 */
    @BsonProperty("DeliveryStatus")
    private CosmosdbDeliveryStatus deliveryStatus;

    /**
     * Idを設定する
     * @param id Id
     * @return {@link CosmosdbDelivery}
     */
    public CosmosdbDelivery id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Idを取得する
     * @return Id
    **/
    public Integer getId() {
        return id;
    }

    /**
     * Idを設定する
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 配送番号を取得する
     *
     * @return 配送番号
     */
    public String getDeliveryNo() {
        return this.deliveryNo;
    }

    /**
     * 配送番号を設定する
     *
     * @param deliveryNo 配送番号
     */
    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    /**
     * 配送予定日を取得する
     *
     * @return 配送予定日
     */
    public LocalDate getDeliveryPlanDate() {
        return this.deliveryPlanDate;
    }

    /**
     * 配送予定日を設定する
     *
     * @param deliveryPlanDate 配送予定日
     */
    public void setDeliveryPlanDate(LocalDate deliveryPlanDate) {
        this.deliveryPlanDate = deliveryPlanDate;
    }

    /**
     * 配送金額を取得する
     *
     * @return 配送金額
     */
    public Integer getDeliveryPrice() {
        return this.deliveryPrice;
    }

    /**
     * 配送金額を設定する
     *
     * @param deliveryPrice 配送金額
     */
    public void setDeliveryPrice(Integer deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    /**
     * 配送先宛名を取得する
     *
     * @return 配送先宛名
     */
    public String getDeliveryName() {
        return this.deliveryName;
    }

    /**
     * 配送先宛名を設定する
     *
     * @param deliveryName 配送先宛名
     */
    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    /**
     * 配送先郵便番号を取得する
     *
     * @return 配送先郵便番号
     */
    public String getDeliveryPostCode() {
        return this.deliveryPostCode;
    }

    /**
     * 配送先郵便番号を設定する
     *
     * @param deliveryPostCode 配送先郵便番号
     */
    public void setDeliveryPostCode(String deliveryPostCode) {
        this.deliveryPostCode = deliveryPostCode;
    }

    /**
     * 配送先住所1 を取得する
     *
     * @return 配送先住所1
     */
    public String getDeliveryAddress1() {
        return this.deliveryAddress1;
    }

    /**
     * 配送先住所1 を設定する
     *
     * @param deliveryAddress1 配送先住所1
     */
    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    /**
     * 配送先住所2 を取得する
     *
     * @return 配送先住所2
     */
    public String getDeliveryAddress2() {
        return this.deliveryAddress2;
    }

    /**
     * 配送先住所2 を設定する
     *
     * @param deliveryAddress2 配送先住所2
     */
    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    /**
     * 配送先電話番号を取得する
     *
     * @return
     */
    public String getDeliveryTel() {
        return this.deliveryTel;
    }

    /**
     * 配送先電話番号を設定する
     *
     * @param deliveryTel 配送先電話番号
     */
    public void setDeliveryTel(String deliveryTel) {
        this.deliveryTel = deliveryTel;
    }

    /**
     * 配送状況を取得する
     *
     * @return 配送状況
     */
    public CosmosdbDeliveryStatus getDeliveryStatus() {
        return this.deliveryStatus;
    }

    /**
     * 配送状況を設定する
     *
     * @param deliveryStatus 配送状況
     */
    public void setDeliveryStatus(CosmosdbDeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CosmosdbDelivery delivery = (CosmosdbDelivery) o;
        return Objects.equals(this.deliveryNo, delivery.deliveryNo)
                && Objects.equals(this.deliveryPlanDate, delivery.deliveryPlanDate)
                && Objects.equals(this.deliveryPrice, delivery.deliveryPrice)
                && Objects.equals(this.deliveryName, delivery.deliveryName)
                && Objects.equals(this.deliveryPostCode, delivery.deliveryPostCode)
                && Objects.equals(this.deliveryAddress1, delivery.deliveryAddress1)
                && Objects.equals(this.deliveryAddress2, delivery.deliveryAddress2)
                && Objects.equals(this.deliveryTel, delivery.deliveryTel)
                && Objects.equals(this.deliveryStatus, delivery.deliveryStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryNo, deliveryPlanDate, deliveryPrice, deliveryName,
                deliveryPostCode, deliveryAddress1, deliveryAddress2, deliveryTel, deliveryStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CosmosdbDelivery {\n");
        sb.append("    deliveryNo: ").append(toIndentedString(deliveryNo)).append("\n");
        sb.append("    deliveryPlanDate: ").append(toIndentedString(deliveryPlanDate)).append("\n");
        sb.append("    deliveryPrice: ").append(toIndentedString(deliveryPrice)).append("\n");
        sb.append("    deliveryName: ").append(toIndentedString(deliveryName)).append("\n");
        sb.append("    deliveryPostCode: ").append(toIndentedString(deliveryPostCode)).append("\n");
        sb.append("    deliveryAddress1: ").append(toIndentedString(deliveryAddress1)).append("\n");
        sb.append("    deliveryAddress2: ").append(toIndentedString(deliveryAddress2)).append("\n");
        sb.append("    deliveryTel: ").append(toIndentedString(deliveryTel)).append("\n");
        sb.append("    deliveryStatus: ").append(toIndentedString(deliveryStatus)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
