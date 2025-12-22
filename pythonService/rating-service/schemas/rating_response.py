from pydantic import BaseModel

class RatingResponse(BaseModel):
    id: str
    user_id: str
    hotel_id: str
    rating: int
    feedback: str | None
