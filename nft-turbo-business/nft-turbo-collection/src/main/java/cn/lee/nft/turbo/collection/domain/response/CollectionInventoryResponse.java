package cn.lee.nft.turbo.collection.domain.response;

import cn.lee.nft.turbo.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class CollectionInventoryResponse extends BaseResponse {

    private String collectionId;

    private String identifier;

    private Integer inventory;
}
