package org.md.service.netty;

import org.md.entity.FeedbackBean;
import org.md.entity.NoteBean;
import org.md.enums.MethodEnum;
import org.md.service.note.BasicService;
import org.md.service.note.NoteServiceImpl;
import org.md.util.ReqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * Md netty service handler
 *
 * <p>
 * 
 * @author huanghuapeng 2018年5月2日下午2:19:07
 *
 */
public class MdNettyServiceHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MdNettyServiceHandler.class);

	private BasicService<NoteBean> service = new NoteServiceImpl();

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info(cause.getMessage());
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest) {
			FullHttpRequest req = (FullHttpRequest) msg;
			ReqUtil reqUtil = new ReqUtil(req);
			if (isMdProjectURI(req.getUri())) {
				logger.info("URL[" + req.getUri() + "]->" + reqUtil.parse());
				MethodEnum method = getMethod(reqUtil);
				if (method == null) {
					FullHttpResponse feedback = feedback("The parameter of 'method' is illegal[" + method + "]");
					ctx.writeAndFlush(feedback).addListener(ChannelFutureListener.CLOSE);
					return;
				}

				if (method == MethodEnum.QUERY) {
					FullHttpResponse feedback = feedback(getMdPost(reqUtil).toString());
					ctx.writeAndFlush(feedback).addListener(ChannelFutureListener.CLOSE);
					return;
				}

				if (method == MethodEnum.QUERY_LIST) {
					FullHttpResponse feedback = feedback(getMdList().toString());
					ctx.writeAndFlush(feedback).addListener(ChannelFutureListener.CLOSE);
					return;
				}
			}
		}

		/*
		 * 这里面为毛还有HttpContent???
		 */
		if (msg instanceof HttpContent) {
			FullHttpResponse feedback = feedback("success");
			ctx.writeAndFlush(feedback).addListener(ChannelFutureListener.CLOSE);
			return;
		}
	}

	/**
	 * 获取请求里面的方法
	 * 
	 * @param reqUtil
	 *            请求参数解析工具类
	 * @return {@link MethodEnum}
	 */
	private MethodEnum getMethod(ReqUtil reqUtil) {
		String methodName = reqUtil.getVal("method");
		for (MethodEnum each : MethodEnum.values()) {
			if (each.getMethod().equals(methodName)) {
				return each;
			}
		}
		return null;
	}

	/**
	 * 获取文章内容Id
	 * 
	 * @param reqUtil
	 *            请求参数解析工具类
	 * @return {@link FeedbackBean}
	 */
	private FeedbackBean getMdPost(ReqUtil reqUtil) {
		String idOfNote = reqUtil.getVal("id");
		return service.queryOne(Integer.parseInt(idOfNote));
	}

	/**
	 * 获取文章查询列表
	 * 
	 * @return {@link FeedbackBean}
	 */
	private FeedbackBean getMdList() {
		return service.queryList(null);
	}

	/**
	 * 判断请求url是不是属于md项目
	 * 
	 * @param uri
	 *            请求路径
	 * @return boolean
	 */
	private boolean isMdProjectURI(String uri) {
		return null != uri && uri.contains("/note");
	}

	/**
	 * 创建回馈信息
	 * 
	 * @param message
	 *            信息主体
	 * @return {@link FullHttpResponse}
	 */
	private FullHttpResponse feedback(Object message) {
		ByteBuf msgBuff = Unpooled.copiedBuffer(String.valueOf(message), CharsetUtil.UTF_8);
		DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK,
				msgBuff);

		/*
		 * 响应内容格式
		 */
		response.headers().set("content_type", "text/plain;charset=UTF-8");
		/*
		 * 允许跨域访问
		 */
		response.headers().set("Access-Control-Allow-Origin", "*");

		return response;
	}

}
