package com.swap.transformer.interest;

import java.util.List;

import com.swap.entity.interest.InterestEntity;
import com.swap.models.interest.InterestRequest;
import com.swap.models.interest.InterestResponse;

public interface InterestTransformer {

	List<InterestEntity> createEntityList(InterestRequest request);
	InterestResponse createResponseFromEntity(InterestEntity entity);
	List<InterestResponse> createResponseListFromEntityList(List<InterestEntity> entities);
	InterestEntity createUpdateEntity(InterestRequest request);
}
