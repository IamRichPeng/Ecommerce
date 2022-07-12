package com.example.ecommerce.dao;

import com.example.ecommerce.entity.Goods;
import org.springframework.data.repository.CrudRepository;

public interface GoodsMapper extends CrudRepository<Goods,Long>{
    Goods findGoodsByGoodsId(Long goodsId) ;
}
