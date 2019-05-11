package com.allegrasoftware.dynjms.util;

public enum MsgAction {

    FalhaComunicacao(0),
    Imagem(1),
    PassagemProcessada(2),
    Passagem(3),
    RequisitaImagem(4),
    SequencialTag(5),
    Tag(6),
    Tarifa(7),
    VeiculosGrupo(8);

    private int value;

    MsgAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
