package com.jxnu.stras.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.*;
import com.jxnu.stras.service.*;
import com.jxnu.stras.utils.ArticleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Slf4j
@Controller
public class InfoController {

    @Resource
    InfoService infoService;

    @Resource
    VideoService videoService;

    @Resource
    SchoolService schoolService;



    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 跳转话题发布页面
     * @return
     */
    @GetMapping("/admin/putInfo")
    public String putInfo(){
        return "admin/put_info";
    }

    /**
     * 跳转视频发布页面
     * @return
     */
    @GetMapping("/admin/putVideo")
    public String putVideo(){
        return "admin/put_video";
    }

    /**
     * 后台信息详情页面跳转
     * @param pn
     * @param infoId
     * @param model
     * @return
     */
    @GetMapping("/admin/infoDetail")
    public String infoDetail(@RequestParam(value = "pn" ,defaultValue = "1")Integer pn,Integer infoId,Model model){
        Info info = infoService.getInfoById(infoId);
        model.addAttribute("infoDetail",info);
        model.addAttribute("pn",pn);
        return "admin/info_detail";
    }

    /**
     * 未审核信息详情页面跳转
     * @param pn
     * @param infoId
     * @param model
     * @return
     */
    @GetMapping("/admin/infoDetail2")
    public String infoDetail2(@RequestParam(value = "pn" ,defaultValue = "1")Integer pn,Integer infoId,Model model){
        Info info = infoService.getInfoById(infoId);
        model.addAttribute("infoDetail",info);
        model.addAttribute("pn",pn);
        return "admin/info_detail2";
    }

    /**
     * 跳转视频详情页
     * @param pnV
     * @param vid
     * @param model
     * @return
     */
    @GetMapping("/admin/videoDetail")
    public String videoDetail(@RequestParam(value = "pnV",defaultValue = "1")Integer pnV,Integer vid,Model model){
        Video video = videoService.getVideoById(vid);;
        model.addAttribute("video",video);
        model.addAttribute("pnV",pnV);
        return "admin/video_detail";
    }

    /**
     * 用户首页跳转
     * @param model
     * @return
     */
    @GetMapping("/")
    public String mainInfo(Model model){
        List<Info> mainRotate = infoService.mainRotate();
        model.addAttribute("mainRotate",mainRotate);
        List<Info> manModel1 = infoService.findInfoBytype("新时代楷模",0,1);
        model.addAttribute("manModel1",manModel1);
        List<Info> manModel2 = infoService.findInfoBytype("新时代楷模",1,2);
        model.addAttribute("manModel2",manModel2);
        List<Info> hotView = infoService.findInfoBytype("热点时事",0,3);
        model.addAttribute("hotView",hotView);
        List<Info> chinese = infoService.findInfoBytype("最美中国景",0,3);
        model.addAttribute("chinese",chinese);
        return "user/main";
    }


    /**
     * 爱我中华页面跳转
     * @param model
     * @param pn 页数
     * @return
     */
    @GetMapping("/user/chineseInfo")
    public String chinese(Model model,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        Page<Info> infoList = infoService.chinesePageInfo(pn);
        List<Info> chinese = infoService.findInfoBytype("最美中国景",0,4);
        model.addAttribute("chinese",chinese);
        model.addAttribute("chineseList",infoList);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        return "user/chinese";
    }

    /**
     * 信息审核通过
     * @param infoId
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/infoUpdate")
    public String infoUpdate(Integer infoId){
        System.out.println("infoId="+infoId);
        boolean isUpdate = infoService.updateInfo(infoId);
        if(isUpdate==true){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 信息审核不通过
     * @param infoId
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/infoUpdate2")
    public String infoUpdate2(Integer infoId){
        System.out.println("infoId="+infoId);
        boolean isUpdate = infoService.updateInfo2(infoId);
        if(isUpdate==true){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 文章删除
     * @param infoId
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/deleteInfo")
    public String deleteInfo(Integer infoId){
        System.out.println("id="+infoId);
        boolean isDel = infoService.deleteInfo(infoId);
        if(isDel==true){
            return "success";
        }
         return "false";
    }

    /**
     * 视频删除
     * @param vid
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/deleteVideo")
    public String deleteVideo(Integer vid){
        System.out.println("vid"+vid);
        boolean isDel = videoService.deleteVideo(vid);
        if(isDel==true){
            return "success";
        }
        return "false";
    }

    /**
     * 查看所有信息
     * @param pn 文章所在页，页数
     * @param pnV 视频所在页，页数
     * @param model
     * @return
     */
    @GetMapping("/admin/allInfo")
    public String allInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn ,@RequestParam(value="pnV",defaultValue = "1")Integer pnV, Model model){
        Page<Info> infoPage = new Page<>(pn,6);
        QueryWrapper<Info> wrapper = new QueryWrapper<>();
        wrapper.eq("info_del",0);
        Page<Info> infoList = infoService.page(infoPage,wrapper);
        Page<Video> videoPage = new Page<>(pnV,6);
        QueryWrapper<Video> wrapperV = new QueryWrapper<>();
        wrapperV.eq("Vstatus",0);
        Page<Video> videoList = videoService.page(videoPage,wrapperV);
        model.addAttribute("infoList",infoList);
        model.addAttribute("videoList",videoList);
        return "admin/all_info";
    }

    /*跳转热点时事界面*/
    @GetMapping("/user/hotInfo")
    public String hotInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        Page<Info> infoList = infoService.hotPageInfo(pn);
        model.addAttribute("hotInfo",infoList);
        List<Info> hotView = infoService.findInfoBytype("热点时事",0,1);
        model.addAttribute("hotTop",hotView);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        return "user/hotSth";
    }


    /**
     * 跳转榜样力量页面
     * @param pn 页数
     * @param model
     * @return
     */
    @GetMapping("/user/manModels")
    public String manModel(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        Page<Info> infoList = infoService.manPageInfo(pn);
        model.addAttribute("manList",infoList);
        List<Info> manTop = infoService.findInfoBytype("榜样力量",0,1);
        model.addAttribute("manTop",manTop);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        return "user/manModel";
    }


    /**
     * 全球战役
     * @param pn 页数
     * @param model
     * @return
     */
    @GetMapping("/user/allE")
    public String allEarth(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        Page<Info> infoList = infoService.allPageE(pn);
        model.addAttribute("eList",infoList);
        List<Info> manTop = infoService.findInfoBytype("全球战疫",0,1);
        model.addAttribute("eTop",manTop);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        return "user/allEarth";
    }

    @GetMapping("/user/videoDal")
    public String videoDal(Integer vid,Model model){
        boolean isUpdate = videoService.updateVideoView(vid);
        Video video = videoService.getVideoById(vid);
        model.addAttribute("videoD",video);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        return "user/videoDal";
    }

    /**
     * 所有未审核信息
     * @param pn
     * @param model
     * @return
     */
    @GetMapping("/admin/notInfo")
    public String notInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        Page<Info> infoPage = new Page<>(pn,6);
        QueryWrapper<Info> wrapper = new QueryWrapper<>();
        wrapper.eq("info_status","未审核");
        wrapper.eq("info_del",0);
        Page<Info> infoList = infoService.page(infoPage,wrapper);
        model.addAttribute("infoList",infoList);
        return "admin/not_info";
    }

    /**
     * 发布视频
     * @param video
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/addVideo")
    public String addVideo(Video video){
        System.out.println("video"+video);
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        video.setVdate(time);
        boolean isAdd = videoService.save(video);
        if(isAdd==true){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 发布文章
     * @param info
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/submitInfo")
    public String submitInfo(@RequestBody Info info, HttpServletRequest request){
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        info.setDate(time);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        info.setUphone(user.getPhone());
        info.setUtype(user.getUtype());
        System.out.println("info=="+info);
        if("admin".equals(user.getUtype())){
            info.setInfoStatus("已通过");
        }
       boolean isSub = infoService.save(info);
        if(isSub==true){
            return "success";
        }else{
            return "false";
        }
    }


    @GetMapping("/user/searchInfo")
    public String searchInfo(@RequestParam("key") String key,Model model){
        List<Info> infoList = infoService.searchInfo(key);
        model.addAttribute("infoList",infoList);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);
        return "user/search_info";
    }

    /**
     *跳转积分商城页面
     * @return
     */
    @GetMapping("/store/main")
    public ModelAndView storeMain(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("store/main");
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getPhone()));
        }
        return modelAndView;
    }
}
