package org.mawaaw.springbootportadapterarchitecture.application.dto;

import java.util.List;

public record ReservationHistoryDTO(String reservationId, int currentPage, int totalPages, int pageSize, List<ReservationDTO> reservationDTO) {
}
