import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.RemotingException;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/12<br>
 */
public class FormatCode {
    /**
     * 连接事件
     */
    public void connected(Channel channel) throws RemotingException {
    }

    /**
     * 断开事件
     */
    public void disconnected(Channel channel) throws RemotingException {
    }

    /**
     * 响应
     */
    public void sent(Channel channel, Object message) throws RemotingException {
    }

    /**
     * 接收
     */
    public void received(Channel channel, Object message) throws RemotingException {
    }

    /**
     * 异常
     */
    public void caught(Channel channel, Throwable exception) throws RemotingException {
    }
}
