package com.swap.service.interest;

import com.swap.models.interest.InterestRequest;
import com.swap.models.interest.InterestResponse;
import com.swap.models.interest.InterestsResponse;

public interface InterestService {

	void createInterest(InterestRequest request);
	void updateInterest(InterestRequest request);
	void deleteInterest(Long interestId);
	InterestResponse getInterestById(Long id);
	InterestsResponse getInterestsByInterestedUser(Long userId);
	InterestsResponse getInterestedByOriginalUser(Long userId);
	InterestsResponse getInterestsAndOffers(Long userId);
	InterestsResponse getInterests(String userId, String itemId, boolean isOwner);
}
