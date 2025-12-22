import uuid
from typing import List
from schemas.rating_request import RatingRequest
from schemas.rating_response import RatingResponse

class RatingService:

    def create_rating(self, request: RatingRequest) -> RatingResponse:
        return RatingResponse(
            id=str(uuid.uuid4()),
            user_id=request.user_id,
            hotel_id=request.hotel_id,
            rating=request.rating,
            feedback=request.feedback
        )

    def get_all_ratings(self) -> List[RatingResponse]:
        return []

    def get_ratings_by_user_id(self, user_id: str) -> List[RatingResponse]:
        return []

    def get_ratings_by_hotel_id(self, hotel_id: str) -> List[RatingResponse]:
        return []
