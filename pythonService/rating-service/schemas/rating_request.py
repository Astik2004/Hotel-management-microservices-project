from pydantic import BaseModel, Field

class RatingRequest(BaseModel):
    user_id: str 
    hotel_id: str 
    rating: int 
    feedback: str 
