package cn.lee.nft.turbo.api.goods.service;

import cn.lee.nft.turbo.api.goods.constant.GoodsType;
import cn.lee.nft.turbo.api.goods.model.BaseGoodsVO;

/**
 * 商品服务
 *
 * @author lee
 */
public interface GoodsFacadeService {

    /**
     * 获取商品
     *
     * @param goodsId
     * @param goodsType
     * @return
     */
    public BaseGoodsVO getGoods(String goodsId, GoodsType goodsType);

}
