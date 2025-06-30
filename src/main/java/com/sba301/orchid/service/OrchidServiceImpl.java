package com.sba301.orchid.service;

import com.sba301.orchid.pojo.Orchid;
import com.sba301.orchid.repository.OrchidRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrchidServiceImpl implements OrchidService {
    private final OrchidRepository orchidRepository;

    @Override
    public Orchid createOrchid(Orchid orchid) {
        return orchidRepository.save(orchid);
    }

    @Override
    public Orchid updateOrchid(Orchid orchid) {
        return orchidRepository.save(orchid);
    }

    @Override
    @Transactional
    public void deleteOrchid(Integer id) {
        orchidRepository.deleteById(id);

    }

    @Override
    public Orchid getOrchidById(String id) {
        return orchidRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Orchid with ID " + id + " does not exist."));
    }

    @Override
    public List<Orchid> getAllOrchids() {
        return orchidRepository.findAll();
    }

    @Override
    public Page<Orchid> getOrchids(String name, String categoryId, Boolean isNatural, Integer from, Integer to, int page, int size) {
        Specification<Orchid> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("orchidName")), "%" + name.toLowerCase() + "%"));
            }
            if (categoryId != null && !categoryId.isEmpty()) {
                predicates.add(cb.equal(root.get("category").get("categoryId"), Integer.parseInt(categoryId)));
            }

            if (isNatural != null) {
                predicates.add(cb.equal(root.get("isNatural"), isNatural));
            }
            if (from != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), from));
            }
            if (to != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), to));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return orchidRepository.findAll(spec, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "orchidId")));
    }
}
