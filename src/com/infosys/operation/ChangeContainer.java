package com.infosys.operation;

import com.infosys.model.Container;

import java.math.BigDecimal;

public class ChangeContainer {

    /**
     * change Shipment Quantity
     * child Shipment Quantity Synchronous modification
     */

    public void changeNodeQuantity(Container container, Integer changeQuantity) {
        if (container == null) {
            return;
        }
        container.setQuantity(container.getQuantity() + changeQuantity);
        if (container.getChildNode() != null) {
            container.getChildNode().forEach(childContainer -> {
                changeNodeQuantity(childContainer,
                        childContainer.getRatio().multiply(new BigDecimal(changeQuantity)).intValue());
            });
        }
    }
}
