package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.ImgText;
import com.ComeOnBaby.model.Notice;
import com.ComeOnBaby.service.ImgTextService;
import com.ComeOnBaby.service.NoteService;
import com.ComeOnBaby.service.NoticeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Макс on 24.02.2017.
 */

@Controller
@RequestMapping("/ajax")
@SessionAttributes("roles")
public class AjaxController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    ImgTextService imgTextService;

    @ResponseBody
    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public String getAnswer(@RequestParam(value="operation") String operation, @RequestParam(value="code") String code) {
        System.out.println("hello ajax/action, operation="+operation+", code="+code);
        try {
            String[] answer = null;
            Notice notice = null;

            if (operation != null && operation.equals("saveTitleBlock")) {
                String title = "";
                Set<ImgText> imgTexts = null;
                System.out.println("imgTexts=" + imgTexts);
                if (imgTexts == null) imgTexts = new HashSet<>();

                JSONObject jsonObject = new JSONObject(code);
                try {
                    notice = noticeService.get(Long.parseLong(jsonObject.getString("notice")));
                    title = jsonObject.getString("title");
                } catch (Exception e) {
                }
                System.out.println("notice=" + notice);

                if (notice != null) {
                    notice.setTitle(title);
                    System.out.println("notice=" + notice);

                    imgTexts = notice.getImgTexts();

                    JSONArray jsonArray = jsonObject.getJSONArray("texts");
                    System.out.println("jsonArray=" + jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        System.out.println("\t JSONObject, i = " + i + ", json=" + json);

                        String strCode = "?";
                        try {
                            strCode = json.getString("code");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String value = json.getString("value");
                        System.out.print("\t\t strCode=" + strCode + ", value=" + value);
                        int sort = 0;
                        int type = 0;
                        try {
                            sort = Integer.parseInt(json.getString("sort"));
                            type = Integer.parseInt(json.getString("type"));
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                        System.out.println(", sort=" + sort + ", type=" + type);

                        ImgText imgT = null;
                        if (strCode != "" && !strCode.equals("0")) {
                            for (ImgText imgText : imgTexts) {
                                if (imgText.getId() != null && strCode.equals(imgText.getId().intValue() + "")) {
                                    System.out.print("\t\t finded");
                                    imgT = imgText;
                                    //imgT.setNotice(notice);
                                    imgT.setSort(sort);
                                    imgT.setText(value);
                                    System.out.println(", imgT=" + imgT);
                                }
                            }
                        }
                        System.out.println("\t\t need create imgT, imgT=" + imgT);
                        if (imgT == null) {
                            try {
                                imgT = new ImgText();
                                imgT.setNotice(notice);
                                imgT.setSort(sort);
                                imgT.setImg("");
                                imgT.setText(value);
                                imgT.setType(type);
                                imgTexts.add(imgT);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("\t\t new imgT=" + imgT);
                        } else {
                            System.out.println("\t\t not new, imgT=" + imgT);
                        }
                        System.out.println("\n\n");

                    }

                }
                notice.rewriteHtml();
                noticeService.updateNotice(notice);
                //
                List<ImgText> copy = new ArrayList<>(notice.getImgTexts());
                Collections.sort(copy, (a, b) -> {
                    return (int) (a.getSort() - b.getSort());
                });
                answer = new String[copy.size()];
                for (int i = 0; i < copy.size(); i++) {
                    answer[i] = copy.get(i).getId().intValue() + "";
                }
            }
        /*
        if(1==0 && operation!=null && operation.equals("addImgText")){

            Notice notice = null;
            try{
                notice = noticeService.get(Long.parseLong(code));
            }catch(Exception e){
                System.out.println("code="+code);
                e.printStackTrace();
            }
            System.out.println("notice="+notice);

            int sort = 0;
            try{
                sort = Integer.parseInt(type);
            }catch(Exception e){
            }
            for(ImgText item:notice.getImgTexts()) {
                if(item.getSort()>sort){
                    item.setSort(item.getSort()+1);
                }
            }

            ImgText imgT = new ImgText();
            imgT.setSort(sort);
            imgT.setNotice(notice);

            if(operation!=null && operation.equals("textarea")) {
                imgT.setText(value);
                imgT.setImg("");
            } else {
                imgT.setText("");
                imgT.setImg(value);
            }

            //
            notice.getImgTexts().add(imgT);

            noticeService.updateNotice(notice);
        }
        */

            JSONObject json = new JSONObject();
            json.append("result", "ok");
            json.append("answer", answer);
            if (notice != null) json.append("html", notice.getHtml());
            System.out.println(Arrays.toString(answer));

            return json.toString();
        }catch(Exception er){
            er.printStackTrace();
        }
        return "";
    }
}

