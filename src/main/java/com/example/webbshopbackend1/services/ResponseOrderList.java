package com.example.webbshopbackend1.services;

import com.example.webbshopbackend1.models.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderList {

    Iterable<Orders> ordersList;
}
