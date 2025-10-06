package com.microservices.user.service.User.Service;

import com.microservices.user.service.User.Service.entity.Rating;
import com.microservices.user.service.User.Service.external.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private RatingService ratingService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveRating() {
		Rating rating = Rating.builder()
				.userId("user-123")
				.hotelId("hotel-456")
				.rating(5)
				.feedback("Created using Feign Client")
				.build();

		Rating savedRating = ratingService.saveRating(rating).getBody();
		assertNotNull(savedRating);
		log.info("New rating created: {}", savedRating);
	}

	@Test
	void getRating() {
		// First, create a new rating to ensure it exists
		Rating rating = Rating.builder()
				.userId("test-user")
				.hotelId("test-hotel")
				.rating(4)
				.feedback("Test Rating")
				.build();

		Rating savedRating = ratingService.saveRating(rating).getBody();

		// Now fetch it
		Rating fetchedRating = ratingService.getRating(savedRating.getRatingId()).getBody();

		assertNotNull(fetchedRating, "Rating should not be null");
		log.info("Fetched rating: {}", fetchedRating);
	}


	@Test
	void updateRating() {
		// First, create a new rating to ensure it exists
		Rating newRating = Rating.builder()
				.userId("user-123")
				.hotelId("hotel-456")
				.rating(3)
				.feedback("Initial feedback")
				.build();

		Rating savedRating = ratingService.saveRating(newRating).getBody();

		// Now, update the saved rating
		Rating updatedDetails = Rating.builder()
				.userId(savedRating.getUserId())
				.hotelId(savedRating.getHotelId())
				.rating(4)
				.feedback("Updated feedback using Feign Client")
				.build();

		Rating updatedRating = ratingService.updateRating(savedRating.getRatingId(), updatedDetails).getBody();

		assertNotNull(updatedRating);
		assertEquals(4, updatedRating.getRating());
		log.info("Updated rating: {}", updatedRating);
	}


	@Test
	void deleteRating() {
		Long ratingId = 5L;

		// Mocking Feign client response correctly
		when(ratingService.deleteRating(ratingId)).thenReturn(ResponseEntity.ok("Rating Deleted"));

		// Call the mock service
		ResponseEntity<String> response = ratingService.deleteRating(ratingId);

		// Validate the response
		assertNotNull(response);
		assertEquals("Rating Deleted", response.getBody());
		log.info("Deleted rating with ID: {}", ratingId);
	}


}
