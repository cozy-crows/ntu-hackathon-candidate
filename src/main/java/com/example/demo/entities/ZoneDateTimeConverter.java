package com.example.demo.entities;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by jerry on 2017/7/17.
 * 用來處理 AbstractEntity 在 Persistence(準備要存入 Database 的狀態)
 * ZonedDateTime 與 Timestamp 的轉換 Mapping
 */
@Converter(autoApply = true)
public class ZoneDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    private String zone = "Asia/Taipei";

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        if (Objects.nonNull(zonedDateTime)) {
            return Timestamp.from(zonedDateTime.toInstant());
        }
        return Timestamp.from(ZonedDateTime.now(ZoneId.of(zone)).toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        if (Objects.nonNull(timestamp)) {
            return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(zone));
        }
        return ZonedDateTime.now(ZoneId.of(zone));
    }
}
