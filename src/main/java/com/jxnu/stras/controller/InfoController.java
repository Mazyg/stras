package com.jxnu.stras.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.domin.Video;
import com.jxnu.stras.service.InfoService;
import com.jxnu.stras.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class InfoController {

    @Resource
    InfoService infoService;

    @Resource
    VideoService videoService;

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
     * 信息详情页面跳转
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
        System.out.println("pn="+pnV+"vid="+vid);
        Video video = videoService.getVideoById(vid);
        System.out.println("v==="+video);
        model.addAttribute("video",video);
        model.addAttribute("pnV",pnV);
        return "admin/video_detail";
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
    public String submitInfo(Info info, HttpServletRequest request){
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        info.setDate(time);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        System.out.println("user"+user);
        info.setUphone(user.getPhone());
        info.setUtype(user.getUtype());
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
}