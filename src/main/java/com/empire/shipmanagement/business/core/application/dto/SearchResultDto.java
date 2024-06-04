package com.empire.shipmanagement.business.core.application.dto;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class SearchResultDto<T> {
    private List<T> resultados;
    private int totalResultados;

    public SearchResultDto(List<T> resultados, int totalResultados) {
        this.resultados = resultados;
        this.totalResultados = totalResultados;
    }

    public List<T> getResultados() {
        return resultados;
    }

    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }

    public int getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(int totalResultados) {
        this.totalResultados = totalResultados;
    }

    public static <T> SearchResultDto<T> toDTO(Page<T> page) {
        SearchResultDto<T> dto = new SearchResultDto<>();
        dto.setResultados(page.getContent());
        dto.setTotalResultados((int) page.getTotalElements());
        return dto;
    }
}
