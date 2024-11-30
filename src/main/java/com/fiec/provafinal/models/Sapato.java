package com.fiec.provafinal.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Sapato {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private double preco;
    private String imagem;
    private int tamanho;
    private String marca;

    @Override
    public String toString() {
        return "Sapato{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", imagem='" + imagem + '\'' +
                ", tamanho=" + tamanho +
                ", marca='" + marca + '\'' +
                '}';
    }
}