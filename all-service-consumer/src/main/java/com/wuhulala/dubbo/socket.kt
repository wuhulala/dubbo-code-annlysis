package com.wuhulala.dubbo

import com.alibaba.dubbo.common.io.Bytes
import com.alibaba.dubbo.common.logger.LoggerFactory
import com.alibaba.dubbo.common.serialize.Cleanable
import com.alibaba.dubbo.common.serialize.hessian2.Hessian2ObjectOutput
import com.alibaba.dubbo.common.serialize.hessian2.Hessian2Serialization
import com.alibaba.dubbo.common.utils.ReflectUtils
import com.alibaba.dubbo.remoting.buffer.ChannelBuffer
import com.alibaba.dubbo.remoting.buffer.ChannelBufferOutputStream
import com.alibaba.dubbo.remoting.exchange.Request
import com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec
import com.alibaba.dubbo.rpc.RpcInvocation
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*
import kotlin.experimental.or


/**
 * 功能

 * @author xueah20964 2018/11/18 Create 1.0  <br></br>
 * *
 * @version 1.0
 */
class KtSocketConnectDubbo {

    @Throws(IOException::class)
    fun mmm(buffer: ChannelBuffer, req: Request) {
        val serialization = Hessian2Serialization()
        val header = ByteArray(HEADER_LENGTH)
        // set magic number.
        Bytes.short2bytes(MAGIC, header)

        // set request and serialization flag.
        header[2] = (FLAG_REQUEST or serialization.contentTypeId).toByte()

        if (req.isTwoWay) header[2] = header[2] or FLAG_TWOWAY
        if (req.isEvent) header[2] = header[2] or FLAG_EVENT

        // set request id.
        Bytes.long2bytes(req.id, header, 4)

        // encode request data.
        val savedWriteIndex = buffer.writerIndex()
        buffer.writerIndex(savedWriteIndex + HEADER_LENGTH)
        val bos = ChannelBufferOutputStream(buffer)
        val url = "dubbo://10.0.75.1:20880/com.wuhulala.dubbo.user.service.MyGenericService?anyhost=true&application=generic-reference-app&check=false&codec=dubbo&connections=10&dispatcher=direct&dubbo=2.6.1&generic=true&heartbeat=60000&interface=com.wuhulala.dubbo.user.service.MyGenericService&methods=sayHello&pid=2516&register.ip=10.0.75.1&remote.timestamp=1542814047717&retries=0&side=consumer&timestamp=1542814409630"

        val out = Hessian2ObjectOutput(bos)

        out.writeObject(req.data)

        out.flushBuffer()
        if (out is Cleanable) {
            out.cleanup()
        }
        bos.flush()
        bos.close()
        val len = bos.writtenBytes()
        //checkPayload(channel, len);
        Bytes.int2bytes(len, header, 12)

        // write
        buffer.writerIndex(savedWriteIndex)
        buffer.writeBytes(header) // write header.
        buffer.writerIndex(savedWriteIndex + HEADER_LENGTH + len)
    }

    companion object {

        @Throws(IOException::class)
        @JvmStatic fun main(args: Array<String>) {
            for (j in 50..50) {

                val socket = Socket()
                try {
                    socket.connect(InetSocketAddress(20880))
                    socket.keepAlive = true
                    //socket.set
                    val input = DataInputStream(socket.getInputStream())
                    val out = DataOutputStream(socket.getOutputStream())
                    //DubboCodec.
                    //out.writeUTF("");
                    val inv = RpcInvocation()
                    inv.methodName = "\$invoke"
                    inv.parameterTypes = arrayOf<Class<*>>(String::class.java, String::class.java, Any::class.java)
                    inv.arguments = arrayOf("sayHello", "java.lang.String", "world")
                    val attachments = HashMap<String, String>()
                    attachments.put("path", "com.wuhulala.dubbo.user.service.MyGenericService")
                    attachments.put("interface", "com.wuhulala.dubbo.user.service.MyGenericService")
                    attachments.put("version", "0.0.0")
                    attachments.put("generic", "true")

                    inv.attachments = attachments
                    val req = Request()
                    req.version = "2.0.0"
                    req.isTwoWay = true
                    req.data = inv
                    //DefaultFuture future = new DefaultFuture(channel, req, timeout);

                    val header = ByteArray(HEADER_LENGTH)
                    // set magic number.
                    Bytes.short2bytes(MAGIC, header)
                    val serialization = Hessian2Serialization()

                    // set request and serialization flag.
                    header[2] = (FLAG_REQUEST or serialization.contentTypeId).toByte()

                    if (req.isTwoWay) header[2] = header[2] or FLAG_TWOWAY
                    if (req.isEvent) header[2] = header[2] or FLAG_EVENT


                    // set request id.
                    Bytes.long2bytes(req.id, header, 4)
                    // 设置消息长度
                    Bytes.int2bytes(98, header, 12)
                    out.write(header)
                    //out.write("2S".getBytes("UTF-8"));
                    val out111 = Hessian2ObjectOutput(socket.getOutputStream())
                    //out111.writeBytes(header);
                    out111.writeUTF("2.6.1")
                    out111.writeUTF("com.wuhulala.dubbo.user.service.MyGenericService")
                    out111.writeUTF("0.0.0")
                    out111.writeUTF("sayHello")
                    out111.writeUTF(ReflectUtils.getDesc(arrayOf<Class<*>>(String::class.java)))
                    out111.writeObject("world")
                    out111.writeObject(HashMap<Any, Any>())
                    out111.flushBuffer()


                    val request = StringBuffer(2048)

                    var i = 0
                    val buffer = ByteArray(20480)

                    try {
                        i = input.read(buffer)
                    } catch (e: IOException) {
                        println(e.message)
                    }

                    for (k in 0..i - 1) {
                        request.append(buffer[k].toChar())
                    }
                    println(j.toString() + ">>>>>>>>>>>>>" + request.toString())


                } catch (e: IOException) {

                } finally {
                    socket.close()
                }
            }
        }

        // header length.
        protected val HEADER_LENGTH = 16
        // magic header.
        protected val MAGIC = 0xdabb.toShort()
        protected val MAGIC_HIGH = Bytes.short2bytes(MAGIC)[0]
        protected val MAGIC_LOW = Bytes.short2bytes(MAGIC)[1]
        // message flag.
        protected val FLAG_REQUEST = 0x80.toByte()
        protected val FLAG_TWOWAY = 0x40.toByte()
        protected val FLAG_EVENT = 0x20.toByte()
        protected val SERIALIZATION_MASK = 0x1f
        private val logger = LoggerFactory.getLogger(ExchangeCodec::class.java!!)
    }
}
