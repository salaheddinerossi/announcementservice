package com.example.announcementservice.serialization;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import java.io.IOException;

public class PolygonSerializer extends JsonSerializer<Polygon> {

    @Override
    public void serialize(Polygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("coordinates");
        gen.writeStartArray();
        for (Coordinate coordinate : value.getCoordinates()) {
            gen.writeStartArray();
            gen.writeNumber(coordinate.getX());
            gen.writeNumber(coordinate.getY());
            gen.writeEndArray();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
