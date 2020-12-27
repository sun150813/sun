package entity;




import com.sunjiayuan.pojo.TbCar;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;

/**
 * Created by Administrator on 2019/11/14.
 */
public class HiglightUtil {

    public static HighlightPage<TbCar> higlight(int currentPage, int pageSize, SolrTemplate solrTemplate, String searchname){
        HighlightQuery query=new SimpleHighlightQuery();
        HighlightOptions highlightOptions=new HighlightOptions().addField("item_title");//设置高亮的域
                highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀
                highlightOptions.setSimplePostfix("</em>");//高亮后缀
                query.setHighlightOptions(highlightOptions);//设置高亮选项
                //按照关键字查询
                Criteria criteria=new Criteria("search_words").is(searchname);
                query.addCriteria(criteria);
                query.setOffset((currentPage-1)*pageSize);//开始索引（默认0）
                query.setRows(pageSize);//每页记录数(默认10)

                HighlightPage<TbCar> page = solrTemplate.queryForHighlightPage(query, TbCar.class);
                for(HighlightEntry<TbCar> h: page.getHighlighted()){//循环高亮入口集合
                    TbCar item = h.getEntity();//获取原实体类
                    if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
                        item.setName(h.getHighlights().get(0).getSnipplets().get(0));//#####设置高亮的结果
                    }
        }
        return page;
    }
}
