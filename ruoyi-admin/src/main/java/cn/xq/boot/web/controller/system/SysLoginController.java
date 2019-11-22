package cn.xq.boot.web.controller.system;

import cn.xq.boot.common.core.controller.BaseController;
import cn.xq.boot.common.utils.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述
 * 登录验证
 * @author xieqiong
 * @date 2019/11/22
 */
@Controller
public class SysLoginController extends BaseController
{
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }
}
