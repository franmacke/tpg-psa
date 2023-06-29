package com.Aninfo.Proyectos.Enum;

public enum EstadoTarea {

    NO_INICIADO, EN_CURSO, COMPLETADO;
    public EstadoTarea siguienteEstado()
    {
        EstadoTarea[] estados = values();
        int index = ordinal() + 1;
        if (index >= estados.length) {
            index = ordinal();
        }
        return estados[index];
    }

    public EstadoTarea estadoAnterior() {
        EstadoTarea[] estados = values();
        int index = ordinal() - 1;
        if (index < 0) {
            index = ordinal();
        }
        return estados[index];
    }
}
