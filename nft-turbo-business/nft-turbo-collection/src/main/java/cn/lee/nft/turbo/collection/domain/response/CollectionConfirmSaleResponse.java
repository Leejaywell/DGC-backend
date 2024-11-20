package cn.lee.nft.turbo.collection.domain.response;

import cn.lee.nft.turbo.base.response.BaseResponse;
import cn.lee.nft.turbo.collection.domain.entity.Collection;
import cn.lee.nft.turbo.collection.domain.entity.HeldCollection;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Setter
@Getter
public class CollectionConfirmSaleResponse extends BaseResponse {

    private Collection collection;

    private HeldCollection heldCollection;
}
