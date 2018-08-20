package myspring.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
*以动手实践为荣,以只看不练为耻.
*以打印日志为荣,以出错不报为耻.
*以局部变量为荣,以全局变量为耻.
*以单元测试为荣,以手工测试为耻.
*以代码重用为荣,以复制粘贴为耻.
*以多态应用为荣,以分支判断为耻.
*以定义常量为荣,以魔法数字为耻.
*以总结思考为荣,以不求甚解为耻.
*
* @author LvZheng
* 创建时间：2018年8月3日 下午4:43:48
*/
//@Component  等于@bean
public class AccessFilter extends ZuulFilter{
	  private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	 /*filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
    pre：可以在请求被路由之前调用
    routing：在路由请求时候被调用
    post：在routing和error过滤器之后被调用
    error：处理请求时发生错误时被调用
    filterOrder：通过int值来定义过滤器的执行顺序
    shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
    run：过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
    */
	@Override
	public Object run() {
		 RequestContext ctx = RequestContext.getCurrentContext();
	        HttpServletRequest request = ctx.getRequest();
	        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
	        Object accessToken = request.getParameter("accessToken");
	        if(accessToken == null) {
	            log.warn("token is empty");
	            ctx.setSendZuulResponse(false);
	            ctx.setResponseStatusCode(401);
	            try {
	                ctx.getResponse().getWriter().write("401");
	            }catch (Exception e){}
	            return null;
	        }
	        log.info("ok");
	        return null;
	    }
	

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
