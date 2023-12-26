package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.dto.PointDto;
import com.example.announcementservice.model.Zone;
import com.example.announcementservice.service.GeometryService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class GeometryServiceImpl implements GeometryService {
    @Override
    public Point createPoint(PointDto pointDto) {

        GeometryFactory geometryFactory = new GeometryFactory();
        return  geometryFactory.createPoint(new Coordinate(pointDto.getLongitude(), pointDto.getLatitude()));

    }

    @Override
    public Boolean isPointInZone(Point point, Zone zone) {

        Geometry zoneGeometry = zone.getGeometry();
        return zoneGeometry != null && zoneGeometry.contains(point);

    }
}
