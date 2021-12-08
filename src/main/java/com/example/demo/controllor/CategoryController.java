package com.example.demo.controllor;

import com.example.demo.mod.Category;
import com.example.demo.mod.LoginUser;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.AccountingNoteSerivce;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
@Autowired
    AccountingNoteSerivce accountingNoteSerivce;
    @PostMapping("/insertOneCategory")
    public String insertOneCategory(@RequestParam("title") String Title, @RequestParam("body") String Body, @RequestParam("id") String ID, RedirectAttributes redirectAttributes) {
        int categoryMathByUserIDTitle = 0;
        //檢查是否有重複

        categoryMathByUserIDTitle = categoryService.getCategoryMathByUserIDTitle(ID.trim(), Title.trim());

//        int maxOneCategoryID = categoryService.getMaxOneCategoryID();
        if (categoryMathByUserIDTitle > 0) {

            redirectAttributes.addFlashAttribute("insertOneCategory", "有重複的分類名稱");
            return "redirect:/CategoryAdd";
        }


        try {
            int i = categoryService.insertOneCategory(ID.trim(), Title.trim(), Body.trim());

            if (i > 0) {

                redirectAttributes.addFlashAttribute("insertOneCategory", "增加成功");
                int maxOneCategoryID = categoryService.getMaxOneCategoryID();
                return "redirect:/Category/goUpdate?CategoryID="+maxOneCategoryID;
            } else {

                redirectAttributes.addFlashAttribute("insertOneCategory", "增加失敗");
                return "redirect:/CategoryAdd";
            }
        } catch (Exception exception) {

            redirectAttributes.addFlashAttribute("insertOneCategory", "錯誤重新嘗試");
            return "redirect:/CategoryAdd";
        }



    }
    @GetMapping("/Category/goUpdate")
    public String goUpdateOneCategory(@RequestParam("CategoryID")int CategoryID,Model model){
        Category oneCategoryByCategoryID = categoryService.getOneCategoryByCategoryID(CategoryID);
        model.addAttribute("CategoryActdata",oneCategoryByCategoryID);
        return "CategoryAct";
    }

    @PostMapping("/Category/Update")
    public String updateOneCategory(@RequestParam("title") String Title, @RequestParam("body") String Body, @RequestParam("id") int ID, RedirectAttributes redirectAttributes, HttpSession httpSession){
        Category oneCategoryByCategoryID1 = categoryService.getOneCategoryByCategoryID(ID);

        int i = categoryService.updateOneCategoryByCategoryID(ID, Title.trim(), Body.trim());//oldCategory
        if(i>0){

            redirectAttributes.addFlashAttribute("UpdateCategory","更改成功");
            UserInfo userInfo  = (UserInfo) httpSession.getAttribute("LoginUser");
            accountingNoteSerivce.updatemany(userInfo.getID(), oneCategoryByCategoryID1.getTitle(), Title);//oldnewCategory
        }else {

            redirectAttributes.addFlashAttribute("UpdateCategory","沒有更改喔");
        }
        Category oneCategoryByCategoryID = categoryService.getOneCategoryByCategoryID(ID);

        redirectAttributes.addFlashAttribute("CategoryActdata",oneCategoryByCategoryID);
        return "redirect:/Category/goUpdate?CategoryID="+ID;
    }

    @PostMapping("/Category/Delete")
    public String deleteOneCategory(@RequestParam(value = "delete",required = false) int[] ID, RedirectAttributes redirectAttributes){




  if(ID==null){

      redirectAttributes.addFlashAttribute("CategoryDelete","沒有選擇刪除的選項，無法刪除");
      return "redirect:/CategoryList.html";
  }
try {

    //check
    for ( int id:
            ID) {

        int CategoryTitleMath = categoryService.getCategoryMathByCategoryIDTitle( Integer.toString(id), "未分類");
        if(CategoryTitleMath>0){
            redirectAttributes.addFlashAttribute("CategoryDelete","選到預設:未分類，無法刪除，請不要勾選未分類");
            return "redirect:/CategoryList.html";
        }
    }
    //check


    for (int id:
            ID) {
        //刪除AccountingNote關聯資料
        Category oneCategoryByCategoryID = categoryService.getOneCategoryByCategoryID(id);
        accountingNoteSerivce.deleteAccountingNote(oneCategoryByCategoryID.getUserID().trim(), oneCategoryByCategoryID.getTitle().trim());

        //刪除AccountingNote關聯資料
        categoryService.deleteManyCategorybyID(id);

    }
}catch (Exception exception){
    redirectAttributes.addFlashAttribute("CategoryDelete","嚴重錯誤，無法刪除");

}


        redirectAttributes.addFlashAttribute("CategoryDelete","刪除成功");



        return "redirect:/CategoryList.html";
    }


}
