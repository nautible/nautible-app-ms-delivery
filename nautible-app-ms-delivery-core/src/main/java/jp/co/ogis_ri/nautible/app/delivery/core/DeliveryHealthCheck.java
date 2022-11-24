package jp.co.ogis_ri.nautible.app.delivery.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import jp.co.ogis_ri.nautible.app.delivery.domain.DeliveryRepository;


/**
 * ヘルスチェック。 Dynamodbへの接続を検証する.{@link HealthCheck}。
 */
@Readiness
@ApplicationScoped
public class DeliveryHealthCheck implements HealthCheck {

    Logger LOG = Logger.getLogger(DeliveryHealthCheck.class.getName());
    @Inject
    Instance<DeliveryRepository> deliveryRepository;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("Dynamodb connection");
        
        try {
            deliveryRepository.get().getByDeliveryNo("deliveryNo");
            return builder.up().build();
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, "Dynamodb connection error", e);
            return builder.down().build();
        }
    }

}
