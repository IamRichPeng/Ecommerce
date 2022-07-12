package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.MallException;
import com.example.ecommerce.common.ServiceResultEnum;
import com.example.ecommerce.dao.GoodsMapper;
import com.example.ecommerce.entity.Goods;
import com.example.ecommerce.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsById(Long id) {
        Goods goods = goodsMapper.findGoodsByGoodsId(id);
        if (goods == null){
            MallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        return goods;
    }

}
