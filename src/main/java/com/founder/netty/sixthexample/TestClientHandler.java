package com.founder.netty.sixthexample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        if (randomInt == 0) {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType).
                    setPerson(MyDataInfo.Person.newBuilder().setName("张三").setAge(20).setAddress("北京").build()).build();
        } else if (randomInt == 1) {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().setName("小狗").setAge(3).build()).build();
        }else {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().setName("小猫").setCity("上海").build()).build();
        }
        ctx.writeAndFlush(myMessage);
    }
}
