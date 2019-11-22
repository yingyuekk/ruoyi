package cn.xq.boot.web.controller.system;

import cn.xq.boot.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述
 * 首页 业务处理
 * @author xieqiong
 * @date 2019/11/22
 */
@Controller
public class SysIndexController extends BaseController
{
    // 系统首页
    @GetMapping("/index")
    public String index()
    {
        return "index";
    }
}
