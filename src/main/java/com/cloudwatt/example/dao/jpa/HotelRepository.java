package com.cloudwatt.example.dao.jpa;

import com.cloudwatt.example.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Hotel findByCity(String city);

//  List<Hotel> findAll(Pageable pageable);

    List<Hotel> findByNameAndCityOrderById(String name, String city);
}
