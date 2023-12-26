package com.example.announcementservice.service;

import com.example.announcementservice.dto.PointDto;
import com.example.announcementservice.model.Zone;
import org.locationtech.jts.geom.Point;

public interface GeometryService {

    public Point createPoint(PointDto pointDto);

    public Boolean isPointInZone(Point point , Zone zone);
}
