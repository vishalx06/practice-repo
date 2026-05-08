import java.util.*;

enum OrderStatus {
    CREATED, ASSIGNED, PICKED_UP, IN_TRANSIT, DELIVERED, FAILED
}

class Order {
    String orderId;
    double pickupLat;
    double pickupLon;
    OrderStatus status;

    Order(String orderId, double pickupLat, double pickupLon) {
        this.orderId = orderId;
        this.pickupLat = pickupLat;
        this.pickupLon = pickupLon;
        this.status = OrderStatus.CREATED;
    }
}

class DeliveryPartner {
    String partnerId;
    String name;
    double currentLat;
    double currentLon;
    boolean available;

    DeliveryPartner(String partnerId, String name, double currentLat, double currentLon, boolean available) {
        this.partnerId = partnerId;
        this.name = name;
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.available = available;
    }
}

class RankedPartner {
    DeliveryPartner partner;
    double distance;

    RankedPartner(DeliveryPartner partner, double distance) {
        this.partner = partner;
        this.distance = distance;
    }
}

interface DeliveryPartnerAdapter {
    boolean assignOrder(Order order, DeliveryPartner partner);
}

class UberAdapter implements DeliveryPartnerAdapter {
    public boolean assignOrder(Order order, DeliveryPartner partner) {
        System.out.println("Sending order " + order.orderId + " to Uber partner " + partner.partnerId);
        return true;
    }
}

class DunzoAdapter implements DeliveryPartnerAdapter {
    public boolean assignOrder(Order order, DeliveryPartner partner) {
        System.out.println("Sending order " + order.orderId + " to Dunzo partner " + partner.partnerId);
        return false; // assume rejected
    }
}

class DHLAdapter implements DeliveryPartnerAdapter {
    public boolean assignOrder(Order order, DeliveryPartner partner) {
        System.out.println("Sending order " + order.orderId + " to DHL partner " + partner.partnerId);
        return true;
    }
}

class PartnerAdapterFactory {
    private final Map<String, DeliveryPartnerAdapter> adapters = new HashMap<>();

    PartnerAdapterFactory() {
        adapters.put("UBER", new UberAdapter());
        adapters.put("DUNZO", new DunzoAdapter());
        adapters.put("DHL", new DHLAdapter());
    }

    DeliveryPartnerAdapter getAdapter(String partnerName) {
        return adapters.get(partnerName.toUpperCase());
    }
}

class GeoService {
    private static final double EARTH_RADIUS_KM = 6371;

    List<RankedPartner> findPartnersWithinRadius(
            Order order,
            List<DeliveryPartner> partners,
            double radiusInKm
    ) {
        List<RankedPartner> result = new ArrayList<>();

        for (DeliveryPartner partner : partners) {
            if (!partner.available) continue;

            double distance = calculateDistance(
                    order.pickupLat,
                    order.pickupLon,
                    partner.currentLat,
                    partner.currentLon
            );

            if (distance <= radiusInKm) {
                result.add(new RankedPartner(partner, distance));
            }
        }

        return result;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDiff = Math.toRadians(lat2 - lat1);
        double lonDiff = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDiff / 2)
                * Math.sin(lonDiff / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}

class RankingService {
    PriorityQueue<RankedPartner> rankPartners(List<RankedPartner> partners) {
        PriorityQueue<RankedPartner> queue =
                new PriorityQueue<>(Comparator.comparingDouble(p -> p.distance));

        queue.addAll(partners);
        return queue;
    }
}

class DeliveryOrchestrator {
    private final GeoService geoService;
    private final RankingService rankingService;
    private final PartnerAdapterFactory adapterFactory;

    DeliveryOrchestrator(
            GeoService geoService,
            RankingService rankingService,
            PartnerAdapterFactory adapterFactory
    ) {
        this.geoService = geoService;
        this.rankingService = rankingService;
        this.adapterFactory = adapterFactory;
    }

    void assignOrder(Order order, List<DeliveryPartner> partners, double radiusInKm) {
        List<RankedPartner> nearbyPartners =
                geoService.findPartnersWithinRadius(order, partners, radiusInKm);

        PriorityQueue<RankedPartner> rankedQueue =
                rankingService.rankPartners(nearbyPartners);

        while (!rankedQueue.isEmpty()) {
            RankedPartner rankedPartner = rankedQueue.poll();
            DeliveryPartner partner = rankedPartner.partner;

            System.out.println("Trying partner: " + partner.name +
                    ", distance: " + rankedPartner.distance + " km");

            DeliveryPartnerAdapter adapter = adapterFactory.getAdapter(partner.name);

            if (adapter == null) {
                System.out.println("No adapter found for " + partner.name);
                continue;
            }

            boolean accepted = adapter.assignOrder(order, partner);

            if (accepted) {
                order.status = OrderStatus.ASSIGNED;
                System.out.println("Order assigned to " + partner.name);
                return;
            }

            System.out.println(partner.name + " rejected. Trying next partner.");
        }

        order.status = OrderStatus.FAILED;
        System.out.println("No partner accepted the order.");
    }
}

public class NielsenPlugNPlayDeliverySystem {
    public static void main(String[] args) {
        Order order = new Order("ORDER_101", 28.6139, 77.2090);

        List<DeliveryPartner> partners = Arrays.asList(
                new DeliveryPartner("P1", "DUNZO", 28.6145, 77.2100, true),
                new DeliveryPartner("P2", "UBER", 28.6200, 77.2150, true),
                new DeliveryPartner("P3", "DHL", 28.7000, 77.3000, true),
                new DeliveryPartner("P4", "UBER", 28.6150, 77.2085, false)
        );

        DeliveryOrchestrator orchestrator = new DeliveryOrchestrator(
                new GeoService(),
                new RankingService(),
                new PartnerAdapterFactory()
        );

        orchestrator.assignOrder(order, partners, 5.0);

        System.out.println("Final order status: " + order.status);
    }
}