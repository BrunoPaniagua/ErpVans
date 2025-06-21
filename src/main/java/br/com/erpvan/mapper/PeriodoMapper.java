package br.com.erpvan.mapper;

import br.com.erpvan.controller.request.PeriodoRequest;
import br.com.erpvan.controller.response.PeriodoResponse;
import br.com.erpvan.entity.Periodo;

public class PeriodoMapper {

    public static Periodo toPeriodo(PeriodoRequest periodoRequest) {
        return Periodo
                .builder()
                .periodo(periodoRequest.periodo())
                .build();
    }

    public static PeriodoResponse toPeriodoResponde(Periodo periodo) {
        return PeriodoResponse
                .builder()
                .id(periodo.getId())
                .periodo(periodo.getPeriodo())
                .build();
    }

}
