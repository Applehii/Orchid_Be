package com.sba301.orchid.controller;

import com.sba301.orchid.pojo.Orchid;
import com.sba301.orchid.service.OrchidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orchids")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class OrchidController {
    private final OrchidService orchidService;

    @GetMapping
    public ResponseEntity<Page<Orchid>> getOrchids(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) Boolean isNatural,
            @RequestParam(required = false) Integer from,
            @RequestParam(required = false) Integer to,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
            orchidService.getOrchids(name, categoryId, isNatural, from, to, page, size)
        );
    }

    @PostMapping
    public ResponseEntity<Orchid> createOrchid(@RequestBody Orchid orchid) {
        return ResponseEntity.ok(orchidService.createOrchid(orchid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orchid> getOrchidById(@PathVariable String id) {
        return ResponseEntity.ok(orchidService.getOrchidById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Orchid> updateOrchid(@PathVariable String id, @RequestBody Orchid orchid) {
        return ResponseEntity.ok(orchidService.updateOrchid(orchid));
    }
}

