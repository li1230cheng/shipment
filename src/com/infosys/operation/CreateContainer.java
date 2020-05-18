package com.infosys.operation;

import com.infosys.model.Container;
import com.infosys.util.VerifyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Split operation on a shipment,
 * would create more than one shipments with specified quantities.
 * Sum of all child shipment quantities should be equal to parent shipment quantity.
 */
public class CreateContainer {


    /**
     * Add child Shipments according to specified num
     *
     * @param num
     */
    public void createChildNode(Container container, int num) {
        generalVerification(container);
        container.setChildNode(new ArrayList<>());
        int quantity = container.getQuantity();
        int residueNum = num;

        for (int i = 0; i < num; i++) {
            //Average distribution, last equal to total - previous sum
            int childQuantity = quantity / (residueNum--);
            BigDecimal ratio = new BigDecimal(childQuantity).divide(new BigDecimal(container.getQuantity()));
            container.getChildNode().add(Container.newInstance(childQuantity, ratio));
            quantity = quantity - childQuantity;
        }
    }

    /**
     * Add child Shipments according to specified num
     *
     * @param quantity
     */
    public void createOneChildNode(List<Container> containerList, Integer quantity) {
        containerList.stream().forEach(container -> {
            generalVerification(container);
        });
        Container childContainer = Container.newInstance(quantity, BigDecimal.ONE);
        List<Container> list = new ArrayList();
        list.add(childContainer);
        containerList.stream().forEach(container -> {
            container.setChildNode(list);
        });
    }

    /**
     * CreateContainer General verification
     *
     * @param container
     */
    private void generalVerification(Container container) {
        VerifyUtil.isNotNull("container is null", container);
        VerifyUtil.verify("exists batch", container.getChildNode()==null);
    }


}
