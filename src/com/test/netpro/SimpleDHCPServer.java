package com.test.netpro;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SimpleDHCPServer {

    public static void main(String[] args) {
        //采用Scanner获取输入数据 Scanner in = new Scanner(System.in);
        //通过Scanner的方法获取输入数据
        //String lineStr = in.nextLine();
        //int varIn = in.nextInt();
        //通过 System.out.println() 或者 System.out.print()输出结果。
        Scanner in = new Scanner(System.in);
        int lenOfOrders = in.nextInt();
        in.nextLine();
        SimpleDHCP dchpObj = new SimpleDHCP();
        for(int i = 0 ;i < lenOfOrders ;i++ ){
            String strOfOrder = in.nextLine();
            String[] contentOfOrder = strOfOrder.split("=");
            if(contentOfOrder[0].equals("request")){//the order is about "request"
                System.out.println(dchpObj.request(contentOfOrder[1]));
            }else{//the order is about "release"
                dchpObj.release(contentOfOrder[1]);
            }
        }
        in.close();
    }


}

class SimpleDHCP{
    private Set<String> firstSet;//此集合用来存放256个第一次请求IP的mac地址。
    private String[] macArray;//对于此数组，数组的下标对应IP地址最后一位，如2下标对应6.0.6.2;该数组存放的数据是该IP分配的或最近一次分配过的MAC地址。
    private boolean[] flagIsUsed;//该数组与macArray数组对应，代表下标对应的IP是否正在占有
    int rangSize;
    String preIPStr;
    public SimpleDHCP(){
        firstSet = new HashSet<String>();
        macArray = new String[256];
        flagIsUsed = new boolean[256];
        rangSize = 256;
        preIPStr = "6.0.6.";
    }

    public String request(String macAddress){//此处书写代码
        //根据firstSet的大小来判断是否有从未被分配过的IP
        int szieOfSet = firstSet.size();
        if(szieOfSet == rangSize){//IP都被分配过
            int i = 0;
            for (; i < rangSize; i++) {
                if(macArray[i].equals(macAddress)){//这个mac最近一次有被分配过
                    flagIsUsed[i]=true;
                    return preIPStr+i;
                }
            }
            if (i == rangSize){//这个mac最近无分配记录，则从空闲IP中予以分配
                int j = 0;
                for (; j < rangSize; j++) {
                    if(flagIsUsed[j] == false){
                        macArray[j] = macAddress;
                        flagIsUsed[j] = true;
                        return preIPStr+j;
                    }
                }
                if(j == rangSize){//没有空闲的IP可以供分配
                    return "NA";
                }
            }
        }else{//有从未被分配过的IP
            //先检查是否之前有被分配过
            if(firstSet.contains(macAddress)){//有被初次分配过
                for (int i = 0; i < rangSize; i++) {
                    if (macArray[i].equals(macAddress)){
                        flagIsUsed[i] = true;
                        return preIPStr+i;
                    }
                }
            }else{//未被初次分配过
                firstSet.add(macAddress);
                macArray[szieOfSet] = macAddress;
                flagIsUsed[szieOfSet] = true;
                return preIPStr+szieOfSet;
            }
        }
        return "NA";
    }

    public void release(String macAddress){//此处书写代码
        for (int i = 0; i < rangSize; i++) {
            if (macArray[i] != null && macArray[i].equals(macAddress)){
                flagIsUsed[i] = false;
            }
        }
    }
}

