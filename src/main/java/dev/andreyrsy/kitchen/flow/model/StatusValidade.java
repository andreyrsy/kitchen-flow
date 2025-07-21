package dev.andreyrsy.kitchen.flow.model;

public enum StatusValidade {
    NORMAL,  // Para itens com validade segura
    ATENCAO, // Para itens que precisam de atenção
    URGENTE, // Para itens que precisam ser usados imediatamente
    VENCIDO  // Para itens que já passaram da validade
}
