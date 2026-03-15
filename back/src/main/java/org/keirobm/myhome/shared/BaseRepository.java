package org.keirobm.myhome.shared;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<ENTITY, ID> extends CrudRepository<ENTITY, ID>, PagingAndSortingRepository<ENTITY, ID> {

}
