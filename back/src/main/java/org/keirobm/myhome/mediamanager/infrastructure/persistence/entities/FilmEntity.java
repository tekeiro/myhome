package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "films")
public class FilmEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private int year;

    @Column
    private String folderPath;

    @Column
    private boolean watched;


}
