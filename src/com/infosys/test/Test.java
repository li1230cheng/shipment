package com.infosys.test;

import com.infosys.model.Container;
import com.infosys.service.ContainerService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static ContainerService containerService=new ContainerService();
    public static void main(String[] args) {
        Container container=init();
        //test split
        System.out.println(splitTest1(container,5));
        //test Merge
        mergeTest1(container,container.getChildNode().get(1),container.getChildNode().get(2),container.getChildNode().get(3));

        changeRootQuantity(container,50);
    }

    /**
     * Test whether the total quantity of shipment is equal to root quantity
     * @param container change Container
     * @param i After change quantity
     */
    private static void changeRootQuantity(Container container, int i) {

        containerService.changeNodeQuantity(container,i);
        List<Container>  leafNodesValue=outputLeafNode(container,null);
        boolean flag1=container.getQuantity().intValue()==listValueSum(leafNodesValue);
        System.out.println(flag1);


    }

    /**
     *
     *  test mergeTest
     *  Three cases
     *
     *  1. Judge whether the shipment quantity after consolidation = previous quantity - consolidated quantity + 1
     *
     *  2. Judge the sum of shipment quantity after consolidation = the sum of shipment quantity to be consolidated
     *
     *  3.Test whether the total quantity of shipment is equal to root quantity
     *
     *
     * @param root
     * @param containers
     */
    private static void mergeTest1(Container root,Container... containers) {
        //Judge whether the shipment quantity after consolidation = previous quantity - consolidated quantity + 1
        List<Container> beforeMergeleafNodesValue=outputLeafNode(root,null);
        containerService.merge(Arrays.asList(containers));
        List<Container> leafNodesValue=outputLeafNode(root,null);
        boolean flag1=beforeMergeleafNodesValue.size()-leafNodesValue.size()==containers.length-1;
        //Judge the sum of shipment quantity after consolidation = the sum of shipment quantity to be consolidated
        Integer containersSum=Arrays.asList(containers).stream().map(Container::getQuantity).reduce(Integer::sum).get();
        boolean flag2=containersSum.intValue()==containers[0].getChildNode().get(0).getQuantity().intValue();
        //Test whether the total quantity of shipment is equal to root quantity
        leafNodesValue=outputLeafNode(root,null);
        boolean flag3=root.getQuantity().intValue()==listValueSum(leafNodesValue);
        System.out.println(flag1&&flag2&&flag3);

    }

    public static Container init(){
        return Container.newInstance(1000,new BigDecimal(100));
    }

    /**
     * Two cases
     *
     * 1. Test whether the number of shipment is equal to num
     *
     * 2. Test whether the total quantity of shipment is equal to root quantity
     * @param root
     * @return
     */
    public static boolean splitTest1(Container root,Integer num) {
        //Test whether the number of shipment is equal to num
        containerService.createChildNode(root,num);
        List<Container> leafNodesValue=outputLeafNode(root,null);
        boolean flag1=leafNodesValue.size()==num;

        //Test whether the total quantity of shipment is equal to root quantity
        boolean flag2=root.getQuantity().intValue()==listValueSum(leafNodesValue);
        return flag1&&flag2;

    }



    /**
     * out put container leaf Shipment For testing
     * @param container
     * @param leafNodeList
     * @return
     */
    public static List<Container> outputLeafNode(Container container,List<Container> leafNodeList){
        if(leafNodeList==null){
            leafNodeList=new ArrayList<>();
        }
        if(container==null){
            return leafNodeList;
        }
        if(container.getChildNode()!=null){
            for (Container childContainer:container.getChildNode()){
                outputLeafNode(childContainer,leafNodeList);
            }

        }else{
            if(!leafNodeList.contains(container)) {

                leafNodeList.add(container);
            }
        }
        return leafNodeList;

    }


    /**
     * output List sum
     */
    public static long listValueSum(List<Container> list){
        return list.stream().map(Container::getQuantity).reduce(Integer::sum).get();
    }

}
