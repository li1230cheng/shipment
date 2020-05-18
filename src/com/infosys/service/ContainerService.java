package com.infosys.service;

import com.infosys.model.Container;
import com.infosys.operation.ChangeContainer;
import com.infosys.operation.CreateContainer;
import com.infosys.util.VerifyUtil;

import java.util.List;

public class ContainerService {
    private CreateContainer createContainer=new CreateContainer();
    private ChangeContainer changeContainer=new ChangeContainer();

    /**
     * Split operation on a shipment, would create more than one shipments with specified quantities.
     * Sum of all child shipment quantities should be equal to parent shipment quantity.
     * @param container shipment
     * @param num  specified quantities.
     */
     public void createChildNode(Container container, int num){
         createContainer.createChildNode(container,num);
     }

    /**
     * Merge operation on more than one shipment, would create one child shipment with summed up quantity.
     *     Sum of all parent shipment quantities should be equal to child shipment quantity.
     * @param containerList child shipment
     */
    public void merge(List<Container> containerList){
         VerifyUtil.isNotNull("containerList is null",containerList);
         Integer sum=containerList.stream().map(Container::getQuantity).reduce(Integer::sum).get();
         createContainer.createOneChildNode(containerList,sum);
     }

    /**
     *  When the transaction quantity of the root Shipment changes,
     *     update the quantity of all child Shipments in proportion to ensure that the transaction quantity of the root node = the sum of the quantity of all leaf nodes
     * @param container root Shipment
     * @param updateQuantity change after quantity
     */
    public void changeNodeQuantity(Container container,Integer updateQuantity){

        changeContainer.changeNodeQuantity(container,updateQuantity-container.getQuantity());
    }
}
