import random
from datetime import datetime, timezone
from fastapi import FastAPI

from model.responses import TemperatureResponse

app = FastAPI()


@app.get("/temperature")
def get_temperature_for_location(location: str):
    match location:
        case "Living Room":
            sensor_id = "1"
        case "Bedroom":
            sensor_id = "2"
        case "Kitchen":
            sensor_id = "3"
        case _:
            sensor_id = "0"

    return TemperatureResponse(
        value=round(random.uniform(0, 100), 2),
        unit="°C",
        timestamp=datetime.now(timezone.utc).isoformat(),
        location=location,
        status="inactive",
        sensor_id=sensor_id,
        sensor_type="temperature",
        description="description",
    )


@app.get("/temperature/{sensor_id}")
def get_temperature_for_sensor(sensor_id: str):
    match sensor_id:
        case "1":
            location = "Living Room"
        case "2":
            location = "Bedroom"
        case "3":
            location = "Kitchen"
        case _:
            location = "Unknown"

    return TemperatureResponse(
        value=round(random.uniform(0, 100), 2),
        unit="°C",
        timestamp=datetime.now(timezone.utc).isoformat(),
        location=location,
        status="inactive",
        sensor_id=sensor_id,
        sensor_type="temperature",
        description="description",
    )
