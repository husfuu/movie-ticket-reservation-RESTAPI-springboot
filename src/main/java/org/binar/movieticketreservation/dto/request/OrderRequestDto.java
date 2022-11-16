package org.binar.movieticketreservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequestDto {
    private String filmId;
    private String scheduleId;
    private String studioId;
    private String userId;
}
