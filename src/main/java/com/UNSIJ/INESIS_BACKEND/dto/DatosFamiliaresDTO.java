package com.UNSIJ.INESIS_BACKEND.dto;
import lombok.Data;
import java.util.List;

@Data
public class DatosFamiliaresDTO {
    private List<PersonaDTO> personas;
    private Double ingresoTotal;
    private Integer personasDependen;
    private ReciboLuzDTO reciboLuz;
    private GastoDTO gastos;
}
