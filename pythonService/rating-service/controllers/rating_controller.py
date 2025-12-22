from fastapi import APIRouter, status
from typing import List
import logging

from schemas.rating_request import RatingRequest
from schemas.rating_response import RatingResponse
from services.rating_service import RatingService

router = APIRouter(
    prefix="/api/v1/ratings",
    tags=["Ratings"]
)

logger = logging.getLogger(__name__)
rating_service = RatingService()


@router.post(
    "",
    response_model=RatingResponse,
    status_code=status.HTTP_201_CREATED
)
def create_rating(rating_request: RatingRequest):
    logger.info(f"REST request to create Rating for user: {rating_request.user_id}")
    return rating_service.create_rating(rating_request)


@router.get(
    "",
    response_model=List[RatingResponse]
)
def get_all_ratings():
    logger.info("REST request to get all Ratings")
    return rating_service.get_all_ratings()


@router.get(
    "/users/{user_id}",
    response_model=List[RatingResponse]
)
def get_ratings_by_user_id(user_id: str):
    logger.info(f"REST request to get Ratings for user ID: {user_id}")
    return rating_service.get_ratings_by_user_id(user_id)


@router.get(
    "/hotels/{hotel_id}",
    response_model=List[RatingResponse]
)
def get_ratings_by_hotel_id(hotel_id: str):
    logger.info(f"REST request to get Ratings for hotel ID: {hotel_id}")
    return rating_service.get_ratings_by_hotel_id(hotel_id)
