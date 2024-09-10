package kz.ai.assist.api.service;

import jakarta.inject.Singleton;
import kz.ai.assist.api.repository.ChartDataRepository;
import kz.ai.assist.api.repository.OrganizationPlanRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class OrganizationPlanService {

    private OrganizationPlanRepository organizationPlanRepository;
    private ChartDataRepository chartDataRepository;

    public OrganizationPlanService(OrganizationPlanRepository organizationPlanRepository,
                                   ChartDataRepository chartDataRepository) {
        this.organizationPlanRepository = organizationPlanRepository;
        this.chartDataRepository = chartDataRepository;
    }

    public Map<String, Double> planPerformance(Long organizationId) {
        Long owner = 1l;
        var plans = organizationPlanRepository.findAll(owner);
        Map<String, Double> map = new HashMap<>();
        plans.stream().forEach(plan -> {
            var sum = chartDataRepository.findSumByOrganizationAndCode(organizationId, plan.code());
            var amount = plan.amount() != null ? plan.amount(): BigDecimal.ONE;
            var performancePercentage = sum.multiply(BigDecimal.valueOf(100)).divide(amount, RoundingMode.HALF_EVEN).doubleValue();
            map.put(plan.code(), performancePercentage);
        });
        return map;
    }
}
