package com.example.ecommerce.api;

import com.example.ecommerce.api.vo.GoodsVO;
import com.example.ecommerce.common.Constants;
import com.example.ecommerce.common.MallException;
import com.example.ecommerce.common.ServiceResultEnum;
import com.example.ecommerce.config.annotation.TokenToUser;
import com.example.ecommerce.entity.Goods;
import com.example.ecommerce.entity.MallUser;
import com.example.ecommerce.service.GoodsService;
import com.example.ecommerce.util.Result;
import com.example.ecommerce.util.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")

public class GoodsAPI {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("goods/detail/{goodsId}")
    public Result<GoodsVO> goodsDetail (@PathVariable Long goodsId, @TokenToUser MallUser mallUser){
        log.info("goods detail api,goodsId={},userId={}", goodsId, mallUser.getUserId());
        if (goodsId < 1) {
            return ResultGenerator.genFailResult("goods number error");
        }

        Goods goods = goodsService.getGoodsById(goodsId);

        if(Constants.SELL_STATUS_DOWN == goods.getGoodsSellStatus()){
            MallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }

        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods,goodsVO);
        //make the graph URL into an array of list
        goodsVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));

        return ResultGenerator.genSuccessResult(goodsVO);
    }


}
