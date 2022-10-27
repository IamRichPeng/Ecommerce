package com.example.ecommerce.api.vo;

import lombok.Data;

@Data
public class GoodsSearchVO {

    private Long goodsId;

    private String goodsName;

    private String goodsIntro;

    private String goodsCoverImg;

    private Integer sellingPrice;
}
