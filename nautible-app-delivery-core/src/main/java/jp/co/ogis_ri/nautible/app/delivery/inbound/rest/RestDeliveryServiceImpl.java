package jp.co.ogis_ri.nautible.app.delivery.inbound.rest;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import jp.co.ogis_ri.nautible.app.delivery.domain.Delivery;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryService;
import jp.co.ogis_ri.nautible.ms.app.delivery.api.rest.DaprSubscribe;
import jp.co.ogis_ri.nautible.ms.app.delivery.api.rest.RestCreateDeliveryRequest;
import jp.co.ogis_ri.nautible.ms.app.delivery.api.rest.RestDeliveryService;
import jp.co.ogis_ri.nautible.ms.app.delivery.api.rest.RestUpdateDeliveryRequest;

public class RestDeliveryServiceImpl implements RestDeliveryService{

    Logger LOG = Logger.getLogger(RestDeliveryServiceImpl.class.getName());

    /** delivery の pubsub 名 */
    private static final String DELIVERY_PUBSUB_NAME = "delivery-pubsub";

    @Inject
    DeliveryService service;
    @Inject
    RestDeliveryMapper mapper;

    @Override
    public Response create(@Valid RestCreateDeliveryRequest restCreateDeliveryRequest) {
        final Delivery delivery = service.create(mapper.restCreateDeliveryToDelivery(restCreateDeliveryRequest));
        return Response.ok(mapper.deliveryToRestDelivery(delivery)).build();
    }

    @Override
    public Response deleteByDeliveryNo(String deliveryNo) {
        service.deleteByDeliveryNo(deliveryNo);
        return Response.status(Status.NO_CONTENT).build();
    }

    @Override
    public Response getByDeliveryNo(String deliveryNo) {
        Delivery delivery = service.getByDeliveryNo(deliveryNo);
        return delivery == null ? Response.status(Status.NOT_FOUND).build()
                : Response.ok(mapper.deliveryToRestGetByDeliveryIdResponse(delivery)).build();
    }

    @Override
    public Response update(@Valid RestUpdateDeliveryRequest restUpdateDeliveryRequest) {
        Delivery delivery = service.update(mapper.restUpdateDelieryToDelivery(restUpdateDeliveryRequest));
        return delivery == null ? Response.status(Status.NOT_FOUND).build()
                : Response.ok(mapper.deliveryToRestUpdateDeliveryResponse(delivery)).build();
    }

    @Override
    public Response confirmShipping() {
        // 処理はすべて service 側で実施
        // レスポンスも何もかえさない
        service.confirmShipping();
        return Response.ok().build();
    }
    
    @Override
    public Response daprSubscribe() {
        // https://docs.dapr.io/developing-applications/building-blocks/pubsub/howto-publish-subscribe/#programmatic-subscriptions
        return Response.ok(List.of(
                new DaprSubscribe().pubsubname(DELIVERY_PUBSUB_NAME).topic("create-order-reply")
                        .route("/order/createOrderReply")))
                .build();
    }
}
