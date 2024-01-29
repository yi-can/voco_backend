package com.voco_task.service;

import com.voco_task.mapper.IRestaurantMapper;
import com.voco_task.rabbitmq.model.AuthRestaurantModel;
import com.voco_task.repository.IRestaurantRepository;
import com.voco_task.repository.entity.Restaurant;
import com.voco_task.utility.JwtTokenManager;
import com.voco_task.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService extends ServiceManager<Restaurant, String> {

    private final IRestaurantRepository restaurantRepository;
    private final JwtTokenManager jwtTokenManager;

    public RestaurantService(IRestaurantRepository restaurantRepository, JwtTokenManager jwtTokenManager) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
        this.jwtTokenManager = jwtTokenManager;
    }


    public void createRestaurant(AuthRestaurantModel model) {
        Restaurant restaurant = IRestaurantMapper.INSTANCE.toRegisterRestaurant(model);
        List<String> latitude = new ArrayList<>();
        latitude.add("dede");
        restaurant.setLatitude(latitude);
        save(restaurant);
    }
}
