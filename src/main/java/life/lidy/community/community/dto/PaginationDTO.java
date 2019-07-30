package life.lidy.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPreviews;//前一页
    private boolean showFirstPage;//到第一页的按钮
    private boolean showNext;//后一页
    private boolean showEndPage;//到尾页按钮

    private Integer page;//当前展示的页面
    private List<Integer> pages=new ArrayList<>();
    public void setPagination(Integer totalCount,Integer page,Integer size){
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount%size;
        }else{
            totalPage=totalCount%size+1;
        }

        pages.add(page);
        for(int i=1;page+i<=totalPage && i<=3;i++){
            pages.add(page+i);
        }
        for(int i=1;page-i>=1 && i<=3;i++){
            pages.add(0,page-i);
        }




        if(page==1){
            showFirstPage=false;
        }else {
            showFirstPage=false;
        }
        if(page==totalPage){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
    }
}

