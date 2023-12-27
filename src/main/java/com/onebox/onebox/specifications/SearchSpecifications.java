package com.onebox.onebox.specifications;

import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class SearchSpecifications<T> {
	public static Specification specification(JSONObject searchSpecs) {
		
    	
		return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            for (String fieldName : searchSpecs.keySet()) {
            	
                Path<Object> fieldPath = root.get(fieldName);
                JSONObject criteria=searchSpecs.getJSONObject(fieldName);
                String condition=criteria.getString("condition");
                Object value=criteria.get("value");
                
                
                String logicalOperator;
                try {
                	logicalOperator=criteria.getString("logical_operator");
                }catch(Exception e) {
                	logicalOperator="AND";
                }
                
                if(condition.equals(SpecificationConstants.EQUALS)) {
                	predicate= criteriaBuilder.equal(fieldPath, value);
                }else if(condition.equals(SpecificationConstants.NOT_EQUALS)) {
                	predicate= criteriaBuilder.notEqual(fieldPath, value);
                }
//              TODO: ##ISSUE## Need to case arguments and add support for greater and lesser.
                else if(condition.equals(SpecificationConstants.GREATER_THAN)) {
                	Expression<? extends Number> newfieldPath=root.get(fieldName);
                	Expression<? extends Number> newValue=criteriaBuilder.literal((Integer) value);
                	predicate=criteriaBuilder.gt(newfieldPath, newValue);
                }else if(condition.equals(SpecificationConstants.LESS_THAN)) {
                	Expression<? extends Number> newfieldPath=root.get(fieldName);
                	predicate=criteriaBuilder.lt(newfieldPath, (Expression<? extends Number>) value);
                }
                
                if(logicalOperator.equals("AND")) {
                	predicate = criteriaBuilder.and(predicate, predicate);
                }else if(logicalOperator.equals("OR")) {
                	predicate = criteriaBuilder.or(predicate, predicate);
                }
            }

            return predicate;
        };
	}
	
    public static Specification withAdditionalCondition(String additionalField, Object additionalValue) {
        return (root, query, criteriaBuilder) -> {
            // Additional condition
            Predicate additionalPredicate = criteriaBuilder.equal(root.get(additionalField), additionalValue);

            return additionalPredicate;
        };
    }
	
}
