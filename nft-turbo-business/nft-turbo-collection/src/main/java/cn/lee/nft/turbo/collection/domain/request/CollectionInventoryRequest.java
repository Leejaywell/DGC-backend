package cn.lee.nft.turbo.collection.domain.request;

import cn.lee.nft.turbo.base.request.BaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lee
 */
@Getter
@Setter
public class CollectionInventoryRequest extends BaseRequest {

    @NotNull(message = "collectionId is null")
    private String collectionId;

    private String identifier;

    private Integer inventory;
}
