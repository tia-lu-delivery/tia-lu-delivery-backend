package br.com.fooddelivery.controller; 

import br.com.fooddelivery.model.Delivery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final Map<Long, Delivery> deliveries = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public List<Delivery> listDeliveries() {
        return new ArrayList<>(deliveries.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable Long id) {
        Delivery delivery = deliveries.get(id);
        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delivery);
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        delivery.setId(idGenerator.incrementAndGet());
        if (delivery.getStatus() == null) {
            delivery.setStatus("pendente");
        }
        deliveries.put(delivery.getId(), delivery);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(delivery.getId())
            .toUri();
            
        return ResponseEntity.created(location).body(delivery);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        if (!deliveries.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        delivery.setId(id);
        deliveries.put(id, delivery);
        return ResponseEntity.ok(delivery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (!deliveries.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        deliveries.remove(id);
        return ResponseEntity.noContent().build();
    }
}