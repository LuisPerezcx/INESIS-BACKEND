package com.UNSIJ.INESIS_BACKEND.DTO;

import lombok.Data;

@Data
public class GastoDTO {
private Double gastoAlimentacion;
private Double gastoRenta;
private Double gastoServicios;
private Double gastoEscolares;
private Double gastoRopa;
private Double gastoTransporte;
private Double gastoOtros;
private Double totalGastos;

}
