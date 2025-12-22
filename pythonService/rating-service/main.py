import py_eureka_client.eureka_client as eureka_client
from fastapi import FastAPI
from controllers.rating_controller import router as rating_router

app = FastAPI(title="Rating Service")


@app.on_event("startup")
async def startup_event():
    await eureka_client.init_async(
        eureka_server="http://localhost:8761/eureka",
        app_name="RATING-SERVICE",
        instance_port=8083,
        instance_ip="127.0.0.1",
        prefer_ip_address=True
    )


@app.on_event("shutdown")
async def shutdown_event():
    await eureka_client.stop()


app.include_router(rating_router)
