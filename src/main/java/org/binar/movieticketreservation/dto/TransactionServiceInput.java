package org.binar.movieticketreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionServiceInput {
    private String filmId;
    private String scheduleId;
    private String studioId;
    private String userId;
}
