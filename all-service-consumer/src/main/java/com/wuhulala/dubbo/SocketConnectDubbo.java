package com.wuhulala.dubbo;

import com.alibaba.dubbo.common.io.Bytes;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.serialize.Cleanable;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.serialize.hessian2.Hessian2ObjectOutput;
import com.alibaba.dubbo.common.serialize.hessian2.Hessian2Serialization;
import com.alibaba.dubbo.common.utils.ReflectUtils;
import com.alibaba.dubbo.remoting.buffer.ChannelBuffer;
import com.alibaba.dubbo.remoting.buffer.ChannelBufferOutputStream;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec;
import com.alibaba.dubbo.rpc.RpcInvocation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能
 *
 * @author xueah20964 2018/11/18 Create 1.0  <br>
 * @version 1.0
 */
public class SocketConnectDubbo {

    public static void main(String[] args) throws IOException {
        for (int j = 50; j < 51; j++) {

            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(20880));
                socket.setKeepAlive(true);
                //socket.set
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                //DubboCodec.
                //out.writeUTF("");
                RpcInvocation inv = new RpcInvocation();
                inv.setMethodName("$invoke");
                inv.setParameterTypes(new Class[]{String.class, String.class, Object.class});
                inv.setArguments(new String[]{"sayHello", "java.lang.String", "world"});
                Map<String, String> attachments = new HashMap<>();
                attachments.put("path", "com.wuhulala.dubbo.user.service.MyGenericService");
                attachments.put("interface", "com.wuhulala.dubbo.user.service.MyGenericService");
                attachments.put("version", "0.0.0");
                attachments.put("generic", "true");

                inv.setAttachments(attachments);
                Request req = new Request();
                req.setVersion("2.0.0");
                req.setTwoWay(true);
                req.setData(inv);
                //DefaultFuture future = new DefaultFuture(channel, req, timeout);

                byte[] header = new byte[HEADER_LENGTH];
                // set magic number.
                Bytes.short2bytes(MAGIC, header);
                Serialization serialization = new Hessian2Serialization();

                // set request and serialization flag.
                header[2] = (byte) (FLAG_REQUEST | serialization.getContentTypeId());

                if (req.isTwoWay()) header[2] |= FLAG_TWOWAY;
                if (req.isEvent()) header[2] |= FLAG_EVENT;


                // set request id.
                Bytes.long2bytes(req.getId(), header, 4);
                // 设置消息长度
                Bytes.int2bytes(98, header, 12);
                out.write(header);
                //out.write("2S".getBytes("UTF-8"));
                ObjectOutput out111 = new Hessian2ObjectOutput(socket.getOutputStream());
                //out111.writeBytes(header);
                out111.writeUTF("2.6.1");
                out111.writeUTF("com.wuhulala.dubbo.user.service.MyGenericService");
                out111.writeUTF("0.0.0");
                out111.writeUTF("sayHello");
                out111.writeUTF(ReflectUtils.getDesc(new Class[]{String.class}));
                out111.writeObject("world");
                out111.writeObject(new HashMap<>());
                out111.flushBuffer();


                StringBuffer request = new StringBuffer(2048);

                int i = 0;
                byte[] buffer = new byte[20480];

                try {
                    i = input.read(buffer);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                for (int k = 0; k < i; k++) {
                    request.append((char) buffer[k]);
                }
                System.out.println(j + ">>>>>>>>>>>>>" + request.toString());


            } catch (IOException e) {

            } finally {
                socket.close();
            }
        }
    }

    // header length.
    protected static final int HEADER_LENGTH = 16;
    // magic header.
    protected static final short MAGIC = (short) 0xdabb;
    protected static final byte MAGIC_HIGH = Bytes.short2bytes(MAGIC)[0];
    protected static final byte MAGIC_LOW = Bytes.short2bytes(MAGIC)[1];
    // message flag.
    protected static final byte FLAG_REQUEST = (byte) 0x80;
    protected static final byte FLAG_TWOWAY = (byte) 0x40;
    protected static final byte FLAG_EVENT = (byte) 0x20;
    protected static final int SERIALIZATION_MASK = 0x1f;
    private static final Logger logger = LoggerFactory.getLogger(ExchangeCodec.class);

    public void mmm(ChannelBuffer buffer, Request req) throws IOException {
        Serialization serialization = new Hessian2Serialization();
        byte[] header = new byte[HEADER_LENGTH];
        // set magic number.
        Bytes.short2bytes(MAGIC, header);

        // set request and serialization flag.
        header[2] = (byte) (FLAG_REQUEST | serialization.getContentTypeId());

        if (req.isTwoWay()) header[2] |= FLAG_TWOWAY;
        if (req.isEvent()) header[2] |= FLAG_EVENT;

        // set request id.
        Bytes.long2bytes(req.getId(), header, 4);

        // encode request data.
        int savedWriteIndex = buffer.writerIndex();
        buffer.writerIndex(savedWriteIndex + HEADER_LENGTH);
        ChannelBufferOutputStream bos = new ChannelBufferOutputStream(buffer);
        String url = "dubbo://10.0.75.1:20880/com.wuhulala.dubbo.user.service.MyGenericService?anyhost=true&application=generic-reference-app&check=false&codec=dubbo&connections=10&dispatcher=direct&dubbo=2.6.1&generic=true&heartbeat=60000&interface=com.wuhulala.dubbo.user.service.MyGenericService&methods=sayHello&pid=2516&register.ip=10.0.75.1&remote.timestamp=1542814047717&retries=0&side=consumer&timestamp=1542814409630";

        ObjectOutput out = new Hessian2ObjectOutput(bos);

        out.writeObject(req.getData());

        out.flushBuffer();
        if (out instanceof Cleanable) {
            ((Cleanable) out).cleanup();
        }
        bos.flush();
        bos.close();
        int len = bos.writtenBytes();
        //checkPayload(channel, len);
        Bytes.int2bytes(len, header, 12);

        // write
        buffer.writerIndex(savedWriteIndex);
        buffer.writeBytes(header); // write header.
        buffer.writerIndex(savedWriteIndex + HEADER_LENGTH + len);
    }
}
