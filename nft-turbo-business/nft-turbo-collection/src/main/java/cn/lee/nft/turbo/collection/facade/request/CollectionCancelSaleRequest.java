package cn.lee.nft.turbo.collection.facade.request;

import cn.lee.nft.turbo.api.collection.constant.CollectionEvent;

public record CollectionCancelSaleRequest(String identifier, Long collectionId,Long quantity) {

    public CollectionEvent eventType() {
        return CollectionEvent.CANCEL_SALE;
    }
}
