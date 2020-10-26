package com.kobiguard.app.repository;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.Attribute;
import com.kobiguard.app.entity.KobiFirm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KobiFirmRepository extends CrudRepository<KobiFirm, String> {
}
