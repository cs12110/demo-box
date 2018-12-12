package org.md.service.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Md netty service
 *
 * <p>
 * 
 * @author huanghuapeng 2018年5月2日下午2:19:07
 *
 */
public class MdNettyService {

	private static final Logger logger = LoggerFactory.getLogger(MdNettyService.class);

	/**
	 * 启动md service
	 * 
	 * @param port
	 *            占用端口
	 * @throws InterruptedException
	 *             抛出异常
	 */
	public void startup(int port) throws InterruptedException {
		EventLoopGroup boss = new NioEventLoopGroup(1);
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();

			b.group(boss, worker)
					/*
					 * 
					 */
					.channel(NioServerSocketChannel.class)
					/*
					 * 
					 */
					.localAddress(port)
					/*
					 * 
					 */
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							/*
							 * server端发送的是httpResponse,
							 * 所以要使用HttpResponseEncoder进行编码
							 */
							ch.pipeline().addLast(new HttpResponseEncoder());
							/*
							 * server端接收到的是httpRequest,
							 * 所以要使用HttpRequestDecoder进行解码
							 */
							ch.pipeline().addLast(new HttpRequestDecoder());
							/*
							 * HttpObjectAggregator解码器
							 * 将多个消息转换为单一的FullHttpRequest或FullHttpResponse对象
							 * 
							 * 字节数最大长度: 65535
							 */
							ch.pipeline().addLast(new HttpObjectAggregator(65535));
							ch.pipeline().addLast(new MdNettyServiceHandler());
						}
					});

			ChannelFuture cf = b.bind().sync();
			logger.info("Start up[" + this.getClass().getName() + "], using port:" + cf.channel());

			cf.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully().sync();
			worker.shutdownGracefully().sync();
		}
	}

}
