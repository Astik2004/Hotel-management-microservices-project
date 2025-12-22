from uuid import uuid4
from schemas.rating_request import RatingRequest
from schemas.rating_response import RatingResponse

class RatingService:
    # 👇 shared in-memory storage
    _ratings = []

    def create_rating(self, rating_request: RatingRequest) -> RatingResponse:
        rating = RatingResponse(
            id=str(uuid4()),
            user_id=rating_request.user_id,
            hotel_id=rating_request.hotel_id,
            rating=rating_request.rating,
            feedback=rating_request.feedback
        )

        self._ratings.append(rating)
        return rating

    def get_all_ratings(self):
        return self._ratings

    def get_ratings_by_user_id(self, user_id: str):
        return [r for r in self._ratings if r.user_id == user_id]

    def get_ratings_by_hotel_id(self, hotel_id: str):
        return [r for r in self._ratings if r.hotel_id == hotel_id]
