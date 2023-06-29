package com.Aninfo.Proyectos.Enum;

public enum EstadoProyecto {
    INICIO, DESARROLLO, IMPLEMENTACION, FINALIZACION;


    public EstadoProyecto siguienteEstado() {
        EstadoProyecto[] estados = values();
        int index = ordinal() + 1;
        if (index >= estados.length) {
            // Si estás en el estado final, regresar al mismo estado
            index = ordinal();
        }
        return estados[index];
    }

    public EstadoProyecto estadoAnterior() {
        EstadoProyecto[] estados = values();
        int index = ordinal() - 1;
        if (index < 0) {
            index = ordinal();
        }
        return estados[index];
    }
}
