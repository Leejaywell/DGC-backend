package cn.lee.nft.turbo.collection.facade.request;

import cn.lee.nft.turbo.api.collection.constant.CollectionEvent;

public record CollectionTrySaleRequest(String identifier, Long collectionId, Long quantity) {

    public CollectionEvent eventType() {
        return CollectionEvent.TRY_SALE;
    }
}
